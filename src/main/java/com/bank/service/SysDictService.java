package com.bank.service;

import com.bank.service.dto.SysDictDTO;

import com.bank.service.dto.custom.SysDictTreeNodeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.bank.domain.SysDict}.
 */
public interface SysDictService {

    /**
     * Save a sysDict.
     *
     * @param sysDictDTO the entity to save.
     * @return the persisted entity.
     */
    SysDictDTO save(SysDictDTO sysDictDTO);

    /**
     * Get all the sysDicts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysDictDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysDict.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysDictDTO> findOne(Long id);

    /**
     * Delete the "id" sysDict.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * 根据type查询字典信息
     * @return
     */
    List<SysDictTreeNodeDto> findDistinctByType(String type);
}
