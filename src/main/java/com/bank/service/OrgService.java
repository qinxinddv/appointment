package com.bank.service;

import com.bank.service.dto.OrgDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.bank.domain.Org}.
 */
public interface OrgService {

    /**
     * Save a org.
     *
     * @param orgDTO the entity to save.
     * @return the persisted entity.
     */
    OrgDTO save(OrgDTO orgDTO);

    /**
     * Get all the orgs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrgDTO> findAll(Pageable pageable);

    public List<OrgDTO> findAll();

    /**
     * Get the "id" org.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrgDTO> findOne(Long id);

    /**
     * Delete the "id" org.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
