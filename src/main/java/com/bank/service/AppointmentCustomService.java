package com.bank.service;

import com.bank.service.dto.custom.AppointmentApplyDto;

public interface AppointmentCustomService {
    public boolean apply(AppointmentApplyDto applyDto);
    public void check(AppointmentApplyDto applyDto);
}
