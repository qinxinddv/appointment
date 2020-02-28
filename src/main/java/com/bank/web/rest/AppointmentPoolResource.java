package com.bank.web.rest;

import com.bank.service.AppointmentPoolService;
import com.bank.web.rest.errors.BadRequestAlertException;
import com.bank.service.dto.AppointmentPoolDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.bank.domain.AppointmentPool}.
 */
@RestController
@RequestMapping("/api")
public class AppointmentPoolResource {

    private final Logger log = LoggerFactory.getLogger(AppointmentPoolResource.class);

    private static final String ENTITY_NAME = "appointmentPool";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppointmentPoolService appointmentPoolService;

    public AppointmentPoolResource(AppointmentPoolService appointmentPoolService) {
        this.appointmentPoolService = appointmentPoolService;
    }

    /**
     * {@code POST  /appointment-pools} : Create a new appointmentPool.
     *
     * @param appointmentPoolDTO the appointmentPoolDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appointmentPoolDTO, or with status {@code 400 (Bad Request)} if the appointmentPool has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/appointment-pools")
    public ResponseEntity<AppointmentPoolDTO> createAppointmentPool(@RequestBody AppointmentPoolDTO appointmentPoolDTO) throws URISyntaxException {
        log.debug("REST request to save AppointmentPool : {}", appointmentPoolDTO);
        if (appointmentPoolDTO.getId() != null) {
            throw new BadRequestAlertException("A new appointmentPool cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppointmentPoolDTO result = appointmentPoolService.save(appointmentPoolDTO);
        return ResponseEntity.created(new URI("/api/appointment-pools/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /appointment-pools} : Updates an existing appointmentPool.
     *
     * @param appointmentPoolDTO the appointmentPoolDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appointmentPoolDTO,
     * or with status {@code 400 (Bad Request)} if the appointmentPoolDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appointmentPoolDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/appointment-pools")
    public ResponseEntity<AppointmentPoolDTO> updateAppointmentPool(@RequestBody AppointmentPoolDTO appointmentPoolDTO) throws URISyntaxException {
        log.debug("REST request to update AppointmentPool : {}", appointmentPoolDTO);
        if (appointmentPoolDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AppointmentPoolDTO result = appointmentPoolService.save(appointmentPoolDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, appointmentPoolDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /appointment-pools} : get all the appointmentPools.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appointmentPools in body.
     */
    @GetMapping("/appointment-pools")
    public ResponseEntity<List<AppointmentPoolDTO>> getAllAppointmentPools(Pageable pageable) {
        log.debug("REST request to get a page of AppointmentPools");
        Page<AppointmentPoolDTO> page = appointmentPoolService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /appointment-pools/:id} : get the "id" appointmentPool.
     *
     * @param id the id of the appointmentPoolDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appointmentPoolDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/appointment-pools/{id}")
    public ResponseEntity<AppointmentPoolDTO> getAppointmentPool(@PathVariable Long id) {
        log.debug("REST request to get AppointmentPool : {}", id);
        Optional<AppointmentPoolDTO> appointmentPoolDTO = appointmentPoolService.findOne(id);
        return ResponseUtil.wrapOrNotFound(appointmentPoolDTO);
    }

    /**
     * {@code DELETE  /appointment-pools/:id} : delete the "id" appointmentPool.
     *
     * @param id the id of the appointmentPoolDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/appointment-pools/{id}")
    public ResponseEntity<Void> deleteAppointmentPool(@PathVariable Long id) {
        log.debug("REST request to delete AppointmentPool : {}", id);
        appointmentPoolService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
