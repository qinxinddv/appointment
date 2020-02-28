package com.bank.service;

import com.bank.service.dto.AppointmentPoolDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.bank.domain.AppointmentPool}.
 */
public interface AppointmentPoolService {

    /**
     * Save a appointmentPool.
     *
     * @param appointmentPoolDTO the entity to save.
     * @return the persisted entity.
     */
    AppointmentPoolDTO save(AppointmentPoolDTO appointmentPoolDTO);

    /**
     * Get all the appointmentPools.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AppointmentPoolDTO> findAll(Pageable pageable);

    /**
     * Get the "id" appointmentPool.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AppointmentPoolDTO> findOne(Long id);

    /**
     * Delete the "id" appointmentPool.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
