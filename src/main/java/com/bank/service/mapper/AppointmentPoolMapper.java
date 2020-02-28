package com.bank.service.mapper;


import com.bank.domain.*;
import com.bank.service.dto.AppointmentPoolDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppointmentPool} and its DTO {@link AppointmentPoolDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrgMapper.class})
public interface AppointmentPoolMapper extends EntityMapper<AppointmentPoolDTO, AppointmentPool> {

    @Mapping(source = "org.id", target = "orgId")
    AppointmentPoolDTO toDto(AppointmentPool appointmentPool);

    @Mapping(source = "orgId", target = "org")
    AppointmentPool toEntity(AppointmentPoolDTO appointmentPoolDTO);

    default AppointmentPool fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppointmentPool appointmentPool = new AppointmentPool();
        appointmentPool.setId(id);
        return appointmentPool;
    }
}
