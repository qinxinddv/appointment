package com.bank.service.mapper.custom;


import com.bank.domain.Appointment;
import com.bank.service.dto.AppointmentDTO;
import com.bank.service.dto.custom.AppointmentCustomDTO;
import com.bank.service.mapper.EntityMapper;
import com.bank.service.mapper.OrgMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper for the entity {@link Appointment} and its DTO {@link AppointmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrgMapper.class})
public interface AppointmentCustomMapper extends EntityMapper<AppointmentCustomDTO, Appointment> {

    @Mappings({
        @Mapping(source = "org.id", target = "orgId"),
        @Mapping(source = "org.name", target = "orgName"),
        @Mapping(source = "org.addr", target = "orgAddr"),
        @Mapping(source = "org.coordinate", target = "orgCoordinate")})
    AppointmentCustomDTO toDto(Appointment appointment);

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
