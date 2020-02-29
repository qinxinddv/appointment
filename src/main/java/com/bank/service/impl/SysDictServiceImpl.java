package com.bank.service.impl;

import com.bank.service.SysDictService;
import com.bank.domain.SysDict;
import com.bank.repository.SysDictRepository;
import com.bank.service.dto.SysDictDTO;
import com.bank.service.mapper.SysDictMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysDict}.
 */
@Service
@Transactional
public class SysDictServiceImpl implements SysDictService {

    private final Logger log = LoggerFactory.getLogger(SysDictServiceImpl.class);

    private final SysDictRepository sysDictRepository;

    private final SysDictMapper sysDictMapper;

    public SysDictServiceImpl(SysDictRepository sysDictRepository, SysDictMapper sysDictMapper) {
        this.sysDictRepository = sysDictRepository;
        this.sysDictMapper = sysDictMapper;
    }

    /**
     * Save a sysDict.
     *
     * @param sysDictDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysDictDTO save(SysDictDTO sysDictDTO) {
        log.debug("Request to save SysDict : {}", sysDictDTO);
        SysDict sysDict = sysDictMapper.toEntity(sysDictDTO);
        sysDict = sysDictRepository.save(sysDict);
        return sysDictMapper.toDto(sysDict);
    }

    /**
     * Get all the sysDicts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysDictDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysDicts");
        return sysDictRepository.findAll(pageable)
            .map(sysDictMapper::toDto);
    }

    /**
     * Get one sysDict by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysDictDTO> findOne(Long id) {
        log.debug("Request to get SysDict : {}", id);
        return sysDictRepository.findById(id)
            .map(sysDictMapper::toDto);
    }

    /**
     * Delete the sysDict by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysDict : {}", id);
        sysDictRepository.deleteById(id);
    }
}
