package com.bank.web.rest;

import com.bank.service.AppointmentConfigService;
import com.bank.web.rest.errors.BadRequestAlertException;
import com.bank.service.dto.AppointmentConfigDTO;

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
 * REST controller for managing {@link com.bank.domain.AppointmentConfig}.
 */
@RestController
@RequestMapping("/api")
public class AppointmentConfigResource {

    private final Logger log = LoggerFactory.getLogger(AppointmentConfigResource.class);

    private static final String ENTITY_NAME = "appointmentConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppointmentConfigService appointmentConfigService;

    public AppointmentConfigResource(AppointmentConfigService appointmentConfigService) {
        this.appointmentConfigService = appointmentConfigService;
    }

    /**
     * {@code POST  /appointment-configs} : Create a new appointmentConfig.
     *
     * @param appointmentConfigDTO the appointmentConfigDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appointmentConfigDTO, or with status {@code 400 (Bad Request)} if the appointmentConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/appointment-configs")
    public ResponseEntity<AppointmentConfigDTO> createAppointmentConfig(@RequestBody AppointmentConfigDTO appointmentConfigDTO) throws URISyntaxException {
        log.debug("REST request to save AppointmentConfig : {}", appointmentConfigDTO);
        if (appointmentConfigDTO.getId() != null) {
            throw new BadRequestAlertException("A new appointmentConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppointmentConfigDTO result = appointmentConfigService.save(appointmentConfigDTO);
        return ResponseEntity.created(new URI("/api/appointment-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /appointment-configs} : Updates an existing appointmentConfig.
     *
     * @param appointmentConfigDTO the appointmentConfigDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appointmentConfigDTO,
     * or with status {@code 400 (Bad Request)} if the appointmentConfigDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appointmentConfigDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/appointment-configs")
    public ResponseEntity<AppointmentConfigDTO> updateAppointmentConfig(@RequestBody AppointmentConfigDTO appointmentConfigDTO) throws URISyntaxException {
        log.debug("REST request to update AppointmentConfig : {}", appointmentConfigDTO);
        if (appointmentConfigDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AppointmentConfigDTO result = appointmentConfigService.save(appointmentConfigDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, appointmentConfigDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /appointment-configs} : get all the appointmentConfigs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appointmentConfigs in body.
     */
    @GetMapping("/appointment-configs")
    public List<AppointmentConfigDTO> getAllAppointmentConfigs() {
        log.debug("REST request to get all AppointmentConfigs");
        return appointmentConfigService.findAll();
    }

    /**
     * {@code GET  /appointment-configs/:id} : get the "id" appointmentConfig.
     *
     * @param id the id of the appointmentConfigDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appointmentConfigDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/appointment-configs/{id}")
    public ResponseEntity<AppointmentConfigDTO> getAppointmentConfig(@PathVariable Long id) {
        log.debug("REST request to get AppointmentConfig : {}", id);
        Optional<AppointmentConfigDTO> appointmentConfigDTO = appointmentConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(appointmentConfigDTO);
    }

    /**
     * {@code DELETE  /appointment-configs/:id} : delete the "id" appointmentConfig.
     *
     * @param id the id of the appointmentConfigDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/appointment-configs/{id}")
    public ResponseEntity<Void> deleteAppointmentConfig(@PathVariable Long id) {
        log.debug("REST request to delete AppointmentConfig : {}", id);
        appointmentConfigService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
