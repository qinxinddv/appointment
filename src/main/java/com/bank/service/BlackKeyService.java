package com.bank.service;

import com.bank.service.dto.BlackKeyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BlackKeyDTO> findAll(Pageable pageable);

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
