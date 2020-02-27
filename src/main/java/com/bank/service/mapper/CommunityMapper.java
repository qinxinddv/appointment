package com.bank.service.mapper;


import com.bank.domain.*;
import com.bank.service.dto.CommunityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Community} and its DTO {@link CommunityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommunityMapper extends EntityMapper<CommunityDTO, Community> {


    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "removeAppointment", ignore = true)
    Community toEntity(CommunityDTO communityDTO);

    default Community fromId(Long id) {
        if (id == null) {
            return null;
        }
        Community community = new Community();
        community.setId(id);
        return community;
    }
}
