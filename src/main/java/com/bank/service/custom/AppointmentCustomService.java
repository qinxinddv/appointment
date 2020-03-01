package com.bank.service.custom;

import com.bank.domain.Appointment;
import com.bank.domain.enumeration.AppointStateEnum;
import com.bank.service.dto.custom.AppointmentApplyDto;
import com.bank.service.dto.custom.AppointmentCustomDTO;
import com.bank.service.dto.custom.AppointmentOverDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppointmentCustomService {
    public boolean apply(AppointmentApplyDto applyDto);
    public void check(AppointmentApplyDto applyDto);
    public void over(AppointmentOverDto overDto);
    public Page<AppointmentCustomDTO> findByMobile(String mobile, Pageable pageable);
    public Page<AppointmentCustomDTO> findByOrgId(long orgId, Pageable pageable);
    public Page<AppointmentCustomDTO> customFind(String openId, Long orgId, String mobile, String idCard, AppointStateEnum state, String date, Pageable pageable);
}
