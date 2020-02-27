package com.bank.service.mapper;


import com.bank.domain.*;
import com.bank.service.dto.BlackKeyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BlackKey} and its DTO {@link BlackKeyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BlackKeyMapper extends EntityMapper<BlackKeyDTO, BlackKey> {



    default BlackKey fromId(Long id) {
        if (id == null) {
            return null;
        }
        BlackKey blackKey = new BlackKey();
        blackKey.setId(id);
        return blackKey;
    }
}
