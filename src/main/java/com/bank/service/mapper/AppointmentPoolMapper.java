package com.bank.service.mapper;


import com.bank.domain.*;
import com.bank.service.dto.AppointmentPoolDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppointmentPool} and its DTO {@link AppointmentPoolDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AppointmentPoolMapper extends EntityMapper<AppointmentPoolDTO, AppointmentPool> {



    default AppointmentPool fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppointmentPool appointmentPool = new AppointmentPool();
        appointmentPool.setId(id);
        return appointmentPool;
    }
}
