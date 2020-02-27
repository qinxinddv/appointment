package com.bank.service;

import com.bank.service.dto.BlackKeyDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.bank.domain.BlackKey}.
 */
public interface BlackKeyService {

    /**
     * Save a blackKey.
     *
     * @param blackKeyDTO the entity to save.
     * @return the persisted entity.
     */
    BlackKeyDTO save(BlackKeyDTO blackKeyDTO);

    /**
     * Get all the blackKeys.
     *
     * @return the list of entities.
     */
    List<BlackKeyDTO> findAll();

    /**
     * Get the "id" blackKey.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BlackKeyDTO> findOne(Long id);

    /**
     * Delete the "id" blackKey.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
