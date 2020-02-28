package com.bank.service.mapper;


import com.bank.domain.*;
import com.bank.service.dto.AppointmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Appointment} and its DTO {@link AppointmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrgMapper.class})
public interface AppointmentMapper extends EntityMapper<AppointmentDTO, Appointment> {

    @Mapping(source = "org.id", target = "orgId")
    AppointmentDTO toDto(Appointment appointment);

    @Mapping(source = "orgId", target = "org")
    Appointment toEntity(AppointmentDTO appointmentDTO);

    default Appointment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setId(id);
        return appointment;
    }
}
