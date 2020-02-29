package com.bank.service.impl;

import com.bank.service.OrgService;
import com.bank.domain.Org;
import com.bank.repository.OrgRepository;
import com.bank.service.dto.OrgDTO;
import com.bank.service.mapper.OrgMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Org}.
 */
@Service
@Transactional
public class OrgServiceImpl implements OrgService {

    private final Logger log = LoggerFactory.getLogger(OrgServiceImpl.class);

    private final OrgRepository orgRepository;

    private final OrgMapper orgMapper;

    public OrgServiceImpl(OrgRepository orgRepository, OrgMapper orgMapper) {
        this.orgRepository = orgRepository;
        this.orgMapper = orgMapper;
    }

    /**
     * Save a org.
     *
     * @param orgDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OrgDTO save(OrgDTO orgDTO) {
        log.debug("Request to save Org : {}", orgDTO);
        Org org = orgMapper.toEntity(orgDTO);
        org = orgRepository.save(org);
        return orgMapper.toDto(org);
    }

    /**
     * Get all the orgs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrgDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Orgs");
        return orgRepository.findAll(pageable)
            .map(orgMapper::toDto);
    }

    /**
     * Get one org by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrgDTO> findOne(Long id) {
        log.debug("Request to get Org : {}", id);
        return orgRepository.findById(id)
            .map(orgMapper::toDto);
    }

    /**
     * Delete the org by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Org : {}", id);
        orgRepository.deleteById(id);
    }
}
