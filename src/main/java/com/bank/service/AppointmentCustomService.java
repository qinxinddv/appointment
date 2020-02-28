package com.bank.service;

import com.bank.domain.Appointment;
import com.bank.service.dto.custom.AppointmentApplyDto;
import com.bank.service.dto.custom.AppointmentCustomDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppointmentCustomService {
    public boolean apply(AppointmentApplyDto applyDto);
    public void check(AppointmentApplyDto applyDto);
    public Page<AppointmentCustomDTO> findByMobile(String mobile, Pageable pageable);
}
