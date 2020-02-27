package com.bank.service;

import com.bank.service.dto.SysDictDTO;

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
     * @return the list of entities.
     */
    List<SysDictDTO> findAll();

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
}
