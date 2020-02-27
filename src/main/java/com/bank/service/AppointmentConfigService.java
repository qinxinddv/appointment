package com.bank.service;

import com.bank.service.dto.AppointmentConfigDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.bank.domain.AppointmentConfig}.
 */
public interface AppointmentConfigService {

    /**
     * Save a appointmentConfig.
     *
     * @param appointmentConfigDTO the entity to save.
     * @return the persisted entity.
     */
    AppointmentConfigDTO save(AppointmentConfigDTO appointmentConfigDTO);

    /**
     * Get all the appointmentConfigs.
     *
     * @return the list of entities.
     */
    List<AppointmentConfigDTO> findAll();

    /**
     * Get the "id" appointmentConfig.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AppointmentConfigDTO> findOne(Long id);

    /**
     * Delete the "id" appointmentConfig.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
