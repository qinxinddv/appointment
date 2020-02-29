package com.bank.service.custom;

import com.bank.domain.AppointmentPool;
import com.bank.domain.enumeration.BusiTypeEnum;
import com.bank.service.dto.AppointmentPoolDTO;
import com.bank.service.dto.custom.DateDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AppointmentPoolCustomService {
    public List<AppointmentPoolDTO> findByOrgIdAndBusiType(long orgId, BusiTypeEnum type);
    public AppointmentPoolDTO findByOrgIdAndBusiTypeAndDateAndPeriod(long orgId, BusiTypeEnum type,String date,String period);
    public Set<DateDto> findByOrgIdAndBusiTypeDistinctDate(long orgId, BusiTypeEnum type);
}
