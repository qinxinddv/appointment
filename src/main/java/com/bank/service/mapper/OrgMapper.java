package com.bank.service.mapper;


import com.bank.domain.*;
import com.bank.service.dto.OrgDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Org} and its DTO {@link OrgDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrgMapper extends EntityMapper<OrgDTO, Org> {


    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "removeAppointment", ignore = true)
    @Mapping(target = "appointmentPools", ignore = true)
    @Mapping(target = "removeAppointmentPool", ignore = true)
    Org toEntity(OrgDTO orgDTO);

    default Org fromId(Long id) {
        if (id == null) {
            return null;
        }
        Org org = new Org();
        org.setId(id);
        return org;
    }
}
