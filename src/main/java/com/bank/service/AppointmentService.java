package com.bank.service;

import com.bank.service.dto.AppointmentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.bank.domain.Appointment}.
 */
public interface AppointmentService {

    /**
     * Save a appointment.
     *
     * @param appointmentDTO the entity to save.
     * @return the persisted entity.
     */
    AppointmentDTO save(AppointmentDTO appointmentDTO);

    /**
     * Get all the appointments.
     *
     * @return the list of entities.
     */
    List<AppointmentDTO> findAll();

    /**
     * Get the "id" appointment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AppointmentDTO> findOne(Long id);

    /**
     * Delete the "id" appointment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
