package com.bank.service.impl;

import com.bank.domain.Appointment;
import com.bank.domain.AppointmentPool;
import com.bank.domain.enumeration.AppointStateEnum;
import com.bank.repository.AppointmentPoolRepository;
import com.bank.repository.AppointmentRepository;
import com.bank.repository.BlackKeyRepository;
import com.bank.service.custom.AppointmentCustomService;
import com.bank.service.dto.custom.AppointmentApplyDto;
import com.bank.service.dto.custom.AppointmentCustomDTO;
import com.bank.service.dto.custom.AppointmentOverDto;
import com.bank.service.mapper.custom.AppointmentCustomMapper;
import com.bank.web.rest.errors.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Service
@Transactional
public class AppointmentCustomServiceImpl implements AppointmentCustomService {
    private final Logger log = LoggerFactory.getLogger(AppointmentCustomServiceImpl.class);
    private final AppointmentPoolRepository appointmentPoolRepository;
    private final AppointmentRepository appointmentRepository;
    private final BlackKeyRepository blackKeyRepository;
    private final AppointmentCustomMapper appointmentCustomMapper;

    public AppointmentCustomServiceImpl(AppointmentPoolRepository appointmentPoolRepository, AppointmentRepository appointmentRepository, BlackKeyRepository blackKeyRepository,AppointmentCustomMapper appointmentCustomMapper) {
        this.appointmentPoolRepository = appointmentPoolRepository;
        this.appointmentRepository = appointmentRepository;
        this.blackKeyRepository = blackKeyRepository;
        this.appointmentCustomMapper = appointmentCustomMapper;
    }

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
        //修改预约池剩余数量
        int leftNum = pool.getLeftNum()-1;
        log.info("剩余可预约数量：{}",leftNum);
        pool.setLeftNum(leftNum);
        appointmentPoolRepository.save(pool);
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
        if ((appointmentRepository.countByDateAndIdCard(applyDto.getDate(), applyDto.getIdCard()) + appointmentRepository.countByDateAndMobile(applyDto.getDate(),applyDto.getMobile())) > 0) {
            throw new BusinessException("不能重复预约");
        }
        //黑名单验证
        checkBlack(applyDto.getAddr());
    }

    /**
     * 处理预约
     * @param overDto
     */
    @Override
    public void over(AppointmentOverDto overDto) {
        Appointment appointment = appointmentRepository.findById(overDto.getId()).orElseThrow(() -> new RuntimeException("未查到预约"));
        appointment.setState(AppointStateEnum.DO);
        appointment.setOpnion(overDto.getOpnion());
        appointment.setOpnionTime(ZonedDateTime.now());
        appointmentRepository.save(appointment);
    }

    @Override
    public Page<AppointmentCustomDTO> findByMobile(String mobile, Pageable pageable) {
        return appointmentRepository.findByMobile(mobile,pageable).map(appointmentCustomMapper::toDto);
    }

    @Override
    public Page<AppointmentCustomDTO> findByOrgId(long orgId, Pageable pageable) {
        return appointmentRepository.findByOrg_Id(orgId,pageable).map(appointmentCustomMapper::toDto);
    }

    @Override
    public Page<AppointmentCustomDTO> customFind(long orgId, String mobile, String idCard,String state,String date, Pageable pageable) {
        return appointmentRepository.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                //创建条件集合
                ArrayList<Predicate> list = new ArrayList<>();
                list.add((Predicate) cb.equal(root.get("org").get("id"), orgId));
                if(StringUtils.isNotBlank(mobile)){
                    list.add((Predicate) cb.equal(root.get("mobile"), mobile));
                }
                if (StringUtils.isNotBlank(idCard)) {
                    list.add((Predicate) cb.equal(root.get("idCard"), idCard));
                }
                if (StringUtils.isNotBlank(state)) {
                    list.add((Predicate) cb.equal(root.get("state"), state));
                }
                if (StringUtils.isNotBlank(date)) {
                    list.add((Predicate) cb.equal(root.get("date"), date));
                }

                if(list.isEmpty()){
                    return null;
                }
                Predicate[] predicates = list.toArray(new Predicate[0]);
                return cb.and(predicates);
            }
        },pageable);
    }

    /**
     * 黑名单校验
     *
     * @param addr
     */
    public void checkBlack(String addr) {
        blackKeyRepository.findAll().stream().forEach(blackKey -> {
            if (addr.indexOf(blackKey.getKey()) != -1) {
                throw new BusinessException("因疫情原因，建议您登录征信中心官网查询");
            }
        });
    }

}
