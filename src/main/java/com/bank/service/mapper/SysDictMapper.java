package com.bank.service.mapper;


import com.bank.domain.*;
import com.bank.service.dto.SysDictDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysDict} and its DTO {@link SysDictDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysDictMapper extends EntityMapper<SysDictDTO, SysDict> {



    default SysDict fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysDict sysDict = new SysDict();
        sysDict.setId(id);
        return sysDict;
    }
}
