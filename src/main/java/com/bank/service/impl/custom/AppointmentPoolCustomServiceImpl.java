package com.bank.service.impl.custom;

import com.bank.domain.AppointmentPool;
import com.bank.domain.enumeration.BusiTypeEnum;
import com.bank.repository.AppointmentPoolRepository;
import com.bank.service.custom.AppointmentPoolCustomService;
import com.bank.service.dto.AppointmentPoolDTO;
import com.bank.service.dto.custom.DateDto;
import com.bank.service.mapper.AppointmentMapper;
import com.bank.service.mapper.AppointmentPoolMapper;
import com.bank.web.rest.errors.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentPoolCustomServiceImpl implements AppointmentPoolCustomService {
    private final AppointmentPoolRepository appointmentPoolRepository;
    private final AppointmentPoolMapper appointmentPoolMapper;

    public AppointmentPoolCustomServiceImpl(AppointmentPoolRepository appointmentPoolRepository, AppointmentPoolMapper appointmentPoolMapper) {
        this.appointmentPoolRepository = appointmentPoolRepository;
        this.appointmentPoolMapper = appointmentPoolMapper;
    }


    @Override
    public List<AppointmentPoolDTO> findByOrgIdAndBusiType(long orgId, BusiTypeEnum type) {
        return appointmentPoolRepository.findByOrg_idAndBusiType(orgId,type).stream().map(appointmentPoolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AppointmentPoolDTO findByOrgIdAndBusiTypeAndDateAndPeriod(long orgId, BusiTypeEnum type, String date, String period) {
        AppointmentPool appointmentPool = appointmentPoolRepository.findByOrg_IdAndBusiTypeAndDateAndPeriod(orgId,type,date,period).orElseThrow(() -> new BusinessException("未查到数据"));
        return appointmentPoolMapper.toDto(appointmentPool);
    }

    @Override
    public Set<DateDto> findByOrgIdAndBusiTypeDistinctDate(long orgId, BusiTypeEnum type) {
        return appointmentPoolRepository.findDistinctByOrg_IdAndBusiType(orgId,type).stream().map(bean -> {return new DateDto(bean.getDate());}).collect(Collectors.toSet());
    }
}
