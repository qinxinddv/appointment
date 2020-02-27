package com.bank.web.rest;

import com.bank.service.BlackKeyService;
import com.bank.web.rest.errors.BadRequestAlertException;
import com.bank.service.dto.BlackKeyDTO;

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
 * REST controller for managing {@link com.bank.domain.BlackKey}.
 */
@RestController
@RequestMapping("/api")
public class BlackKeyResource {

    private final Logger log = LoggerFactory.getLogger(BlackKeyResource.class);

    private static final String ENTITY_NAME = "blackKey";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BlackKeyService blackKeyService;

    public BlackKeyResource(BlackKeyService blackKeyService) {
        this.blackKeyService = blackKeyService;
    }

    /**
     * {@code POST  /black-keys} : Create a new blackKey.
     *
     * @param blackKeyDTO the blackKeyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new blackKeyDTO, or with status {@code 400 (Bad Request)} if the blackKey has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/black-keys")
    public ResponseEntity<BlackKeyDTO> createBlackKey(@RequestBody BlackKeyDTO blackKeyDTO) throws URISyntaxException {
        log.debug("REST request to save BlackKey : {}", blackKeyDTO);
        if (blackKeyDTO.getId() != null) {
            throw new BadRequestAlertException("A new blackKey cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BlackKeyDTO result = blackKeyService.save(blackKeyDTO);
        return ResponseEntity.created(new URI("/api/black-keys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /black-keys} : Updates an existing blackKey.
     *
     * @param blackKeyDTO the blackKeyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blackKeyDTO,
     * or with status {@code 400 (Bad Request)} if the blackKeyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the blackKeyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/black-keys")
    public ResponseEntity<BlackKeyDTO> updateBlackKey(@RequestBody BlackKeyDTO blackKeyDTO) throws URISyntaxException {
        log.debug("REST request to update BlackKey : {}", blackKeyDTO);
        if (blackKeyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BlackKeyDTO result = blackKeyService.save(blackKeyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, blackKeyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /black-keys} : get all the blackKeys.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of blackKeys in body.
     */
    @GetMapping("/black-keys")
    public List<BlackKeyDTO> getAllBlackKeys() {
        log.debug("REST request to get all BlackKeys");
        return blackKeyService.findAll();
    }

    /**
     * {@code GET  /black-keys/:id} : get the "id" blackKey.
     *
     * @param id the id of the blackKeyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the blackKeyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/black-keys/{id}")
    public ResponseEntity<BlackKeyDTO> getBlackKey(@PathVariable Long id) {
        log.debug("REST request to get BlackKey : {}", id);
        Optional<BlackKeyDTO> blackKeyDTO = blackKeyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(blackKeyDTO);
    }

    /**
     * {@code DELETE  /black-keys/:id} : delete the "id" blackKey.
     *
     * @param id the id of the blackKeyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/black-keys/{id}")
    public ResponseEntity<Void> deleteBlackKey(@PathVariable Long id) {
        log.debug("REST request to delete BlackKey : {}", id);
        blackKeyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
