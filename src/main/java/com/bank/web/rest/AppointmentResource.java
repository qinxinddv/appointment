package com.bank.web.rest;

import com.bank.service.AppointmentService;
import com.bank.web.rest.errors.BadRequestAlertException;
import com.bank.service.dto.AppointmentDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.bank.domain.Appointment}.
 */
@RestController
@RequestMapping("/api")
public class AppointmentResource {

    private final Logger log = LoggerFactory.getLogger(AppointmentResource.class);

    private static final String ENTITY_NAME = "appointment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppointmentService appointmentService;

    public AppointmentResource(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * {@code POST  /appointments} : Create a new appointment.
     *
     * @param appointmentDTO the appointmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appointmentDTO, or with status {@code 400 (Bad Request)} if the appointment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/appointments")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) throws URISyntaxException {
        log.debug("REST request to save Appointment : {}", appointmentDTO);
        if (appointmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new appointment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppointmentDTO result = appointmentService.save(appointmentDTO);
        return ResponseEntity.created(new URI("/api/appointments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /appointments} : Updates an existing appointment.
     *
     * @param appointmentDTO the appointmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appointmentDTO,
     * or with status {@code 400 (Bad Request)} if the appointmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appointmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/appointments")
    public ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody AppointmentDTO appointmentDTO) throws URISyntaxException {
        log.debug("REST request to update Appointment : {}", appointmentDTO);
        if (appointmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AppointmentDTO result = appointmentService.save(appointmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, appointmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /appointments} : get all the appointments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appointments in body.
     */
    @GetMapping("/appointments")
    public List<AppointmentDTO> getAllAppointments() {
        log.debug("REST request to get all Appointments");
        return appointmentService.findAll();
    }

    /**
     * {@code GET  /appointments/:id} : get the "id" appointment.
     *
     * @param id the id of the appointmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appointmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/appointments/{id}")
    public ResponseEntity<AppointmentDTO> getAppointment(@PathVariable Long id) {
        log.debug("REST request to get Appointment : {}", id);
        Optional<AppointmentDTO> appointmentDTO = appointmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(appointmentDTO);
    }

    /**
     * {@code DELETE  /appointments/:id} : delete the "id" appointment.
     *
     * @param id the id of the appointmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        log.debug("REST request to delete Appointment : {}", id);
        appointmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
