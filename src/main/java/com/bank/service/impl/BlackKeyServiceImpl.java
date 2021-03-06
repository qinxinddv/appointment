package com.bank.service.impl;

import com.bank.service.BlackKeyService;
import com.bank.domain.BlackKey;
import com.bank.repository.BlackKeyRepository;
import com.bank.service.dto.BlackKeyDTO;
import com.bank.service.mapper.BlackKeyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BlackKey}.
 */
@Service
@Transactional
public class BlackKeyServiceImpl implements BlackKeyService {

    private final Logger log = LoggerFactory.getLogger(BlackKeyServiceImpl.class);

    private final BlackKeyRepository blackKeyRepository;

    private final BlackKeyMapper blackKeyMapper;

    public BlackKeyServiceImpl(BlackKeyRepository blackKeyRepository, BlackKeyMapper blackKeyMapper) {
        this.blackKeyRepository = blackKeyRepository;
        this.blackKeyMapper = blackKeyMapper;
    }

    /**
     * Save a blackKey.
     *
     * @param blackKeyDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BlackKeyDTO save(BlackKeyDTO blackKeyDTO) {
        log.debug("Request to save BlackKey : {}", blackKeyDTO);
        BlackKey blackKey = blackKeyMapper.toEntity(blackKeyDTO);
        blackKey = blackKeyRepository.save(blackKey);
        return blackKeyMapper.toDto(blackKey);
    }

    /**
     * Get all the blackKeys.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BlackKeyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BlackKeys");
        return blackKeyRepository.findAll(pageable)
            .map(blackKeyMapper::toDto);
    }

    /**
     * Get one blackKey by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BlackKeyDTO> findOne(Long id) {
        log.debug("Request to get BlackKey : {}", id);
        return blackKeyRepository.findById(id)
            .map(blackKeyMapper::toDto);
    }

    /**
     * Delete the blackKey by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BlackKey : {}", id);
        blackKeyRepository.deleteById(id);
    }
}
