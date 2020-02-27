package com.bank.service;

import com.bank.service.dto.CommunityDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.bank.domain.Community}.
 */
public interface CommunityService {

    /**
     * Save a community.
     *
     * @param communityDTO the entity to save.
     * @return the persisted entity.
     */
    CommunityDTO save(CommunityDTO communityDTO);

    /**
     * Get all the communities.
     *
     * @return the list of entities.
     */
    List<CommunityDTO> findAll();

    /**
     * Get the "id" community.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommunityDTO> findOne(Long id);

    /**
     * Delete the "id" community.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
