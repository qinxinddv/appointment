package com.bank.service.custom;

import com.bank.domain.AppointmentPool;
import com.bank.domain.enumeration.BusiTypeEnum;
import com.bank.service.dto.AppointmentPoolDTO;

import java.util.List;

public interface AppointmentPoolCustomService {
    public List<AppointmentPoolDTO> findByOrgIdAndBusiType(long orgId, BusiTypeEnum type);
}
