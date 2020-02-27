package com.bank.web.rest;

import com.bank.service.CommunityService;
import com.bank.web.rest.errors.BadRequestAlertException;
import com.bank.service.dto.CommunityDTO;

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
 * REST controller for managing {@link com.bank.domain.Community}.
 */
@RestController
@RequestMapping("/api")
public class CommunityResource {

    private final Logger log = LoggerFactory.getLogger(CommunityResource.class);

    private static final String ENTITY_NAME = "community";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommunityService communityService;

    public CommunityResource(CommunityService communityService) {
        this.communityService = communityService;
    }

    /**
     * {@code POST  /communities} : Create a new community.
     *
     * @param communityDTO the communityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new communityDTO, or with status {@code 400 (Bad Request)} if the community has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/communities")
    public ResponseEntity<CommunityDTO> createCommunity(@RequestBody CommunityDTO communityDTO) throws URISyntaxException {
        log.debug("REST request to save Community : {}", communityDTO);
        if (communityDTO.getId() != null) {
            throw new BadRequestAlertException("A new community cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommunityDTO result = communityService.save(communityDTO);
        return ResponseEntity.created(new URI("/api/communities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /communities} : Updates an existing community.
     *
     * @param communityDTO the communityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated communityDTO,
     * or with status {@code 400 (Bad Request)} if the communityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the communityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/communities")
    public ResponseEntity<CommunityDTO> updateCommunity(@RequestBody CommunityDTO communityDTO) throws URISyntaxException {
        log.debug("REST request to update Community : {}", communityDTO);
        if (communityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommunityDTO result = communityService.save(communityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, communityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /communities} : get all the communities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of communities in body.
     */
    @GetMapping("/communities")
    public List<CommunityDTO> getAllCommunities() {
        log.debug("REST request to get all Communities");
        return communityService.findAll();
    }

    /**
     * {@code GET  /communities/:id} : get the "id" community.
     *
     * @param id the id of the communityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the communityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/communities/{id}")
    public ResponseEntity<CommunityDTO> getCommunity(@PathVariable Long id) {
        log.debug("REST request to get Community : {}", id);
        Optional<CommunityDTO> communityDTO = communityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(communityDTO);
    }

    /**
     * {@code DELETE  /communities/:id} : delete the "id" community.
     *
     * @param id the id of the communityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/communities/{id}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable Long id) {
        log.debug("REST request to delete Community : {}", id);
        communityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}