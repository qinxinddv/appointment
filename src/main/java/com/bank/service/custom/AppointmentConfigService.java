package com.bank.service.custom;

import com.bank.service.dto.AppointmentConfigDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AppointmentConfigDTO> findAll(Pageable pageable);

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
