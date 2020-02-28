package com.bank.service.impl;

import com.bank.domain.Appointment;
import com.bank.domain.AppointmentPool;
import com.bank.domain.BlackKey;
import com.bank.domain.enumeration.LockEnum;
import com.bank.repository.AppointmentPoolRepository;
import com.bank.repository.AppointmentRepository;
import com.bank.repository.BlackKeyRepository;
import com.bank.service.AppointmentCustomService;
import com.bank.service.dto.custom.AppointmentApplyDto;
import com.bank.service.dto.custom.AppointmentCustomDTO;
import com.bank.service.mapper.custom.AppointmentCustomMapper;
import com.bank.web.rest.errors.BusinessException;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;

@Service
public class AppointmentCustomServiceImpl implements AppointmentCustomService {
    private final Logger log = LoggerFactory.getLogger(AppointmentCustomServiceImpl.class);
    @Autowired
    private HazelcastInstance hazelcastInstance;
    @Autowired
    private AppointmentPoolRepository appointmentPoolRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private BlackKeyRepository blackKeyRepository;
    @Autowired
    private AppointmentCustomMapper appointmentCustomMapper;

    /**
     * 预约申请
     *
     * @param applyDto
     * @return
     */
    @Override
    public boolean apply(AppointmentApplyDto applyDto) {
        AppointmentPool pool = appointmentPoolRepository.findByOrg_IdAndBusiTypeAndDateAndPeriod(applyDto.getOrgId(), applyDto.getBusiType(), applyDto.getDate(), applyDto.getTimePeriodCode()).orElseThrow(() -> new BusinessException("未查到预约号资源"));
        if (pool.getLeftNum() < 1) {
            throw new BusinessException("已约满");
        }
        Appointment appointment = new Appointment(applyDto, pool.getOrg());
        appointmentRepository.save(appointment);
        log.info("创建预约申请成功，{}", appointment);

        return false;
    }

    /**
     * 前置校验
     *
     * @param applyDto
     */
    @Override
    public void check(AppointmentApplyDto applyDto) {
        //校验身份证号或手机号统一业务类型下，是否重复预约
        if (appointmentRepository.countByDateAndIdCardOrMobile(applyDto.getDate(), applyDto.getIdCard(), applyDto.getMobile()) > 0) {
            throw new BusinessException("不能重复预约");
        }
        //黑名单验证
        checkBlack(applyDto.getAddr());
    }

    @Override
    public Page<AppointmentCustomDTO> findByMobile(String mobile, Pageable pageable) {
        return appointmentRepository.findByMobile(mobile,pageable).map(appointmentCustomMapper::toDto);
    }

    /**
     * 黑名单校验
     *
     * @param addr
     */
    public void checkBlack(String addr) {
        blackKeyRepository.findAll().stream().forEach(blackKey -> {
            if (addr.indexOf(blackKey.getKey()) != -1) {
                throw new BusinessException("您所在地址因疫情影响禁止外出");
            }
        });
    }

}
