package com.bank.service.mapper;


import com.bank.domain.*;
import com.bank.service.dto.AppointmentConfigDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppointmentConfig} and its DTO {@link AppointmentConfigDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AppointmentConfigMapper extends EntityMapper<AppointmentConfigDTO, AppointmentConfig> {



    default AppointmentConfig fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppointmentConfig appointmentConfig = new AppointmentConfig();
        appointmentConfig.setId(id);
        return appointmentConfig;
    }
}
