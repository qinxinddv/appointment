package com.bank.service.impl.custom;

import com.bank.domain.AppointmentPool;
import com.bank.domain.enumeration.BusiTypeEnum;
import com.bank.repository.AppointmentPoolRepository;
import com.bank.service.custom.AppointmentPoolCustomService;
import com.bank.service.dto.AppointmentPoolDTO;
import com.bank.service.mapper.AppointmentMapper;
import com.bank.service.mapper.AppointmentPoolMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
}
