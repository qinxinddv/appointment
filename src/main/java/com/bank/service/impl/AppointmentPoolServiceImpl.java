package com.bank.service.impl;

import com.bank.service.AppointmentPoolService;
import com.bank.domain.AppointmentPool;
import com.bank.repository.AppointmentPoolRepository;
import com.bank.service.dto.AppointmentPoolDTO;
import com.bank.service.mapper.AppointmentPoolMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AppointmentPool}.
 */
@Service
@Transactional
public class AppointmentPoolServiceImpl implements AppointmentPoolService {

    private final Logger log = LoggerFactory.getLogger(AppointmentPoolServiceImpl.class);

    private final AppointmentPoolRepository appointmentPoolRepository;

    private final AppointmentPoolMapper appointmentPoolMapper;

    public AppointmentPoolServiceImpl(AppointmentPoolRepository appointmentPoolRepository, AppointmentPoolMapper appointmentPoolMapper) {
        this.appointmentPoolRepository = appointmentPoolRepository;
        this.appointmentPoolMapper = appointmentPoolMapper;
    }

    /**
     * Save a appointmentPool.
     *
     * @param appointmentPoolDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AppointmentPoolDTO save(AppointmentPoolDTO appointmentPoolDTO) {
        log.debug("Request to save AppointmentPool : {}", appointmentPoolDTO);
        AppointmentPool appointmentPool = appointmentPoolMapper.toEntity(appointmentPoolDTO);
        appointmentPool = appointmentPoolRepository.save(appointmentPool);
        return appointmentPoolMapper.toDto(appointmentPool);
    }

    /**
     * Get all the appointmentPools.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AppointmentPoolDTO> findAll() {
        log.debug("Request to get all AppointmentPools");
        return appointmentPoolRepository.findAll().stream()
            .map(appointmentPoolMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one appointmentPool by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AppointmentPoolDTO> findOne(Long id) {
        log.debug("Request to get AppointmentPool : {}", id);
        return appointmentPoolRepository.findById(id)
            .map(appointmentPoolMapper::toDto);
    }

    /**
     * Delete the appointmentPool by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AppointmentPool : {}", id);
        appointmentPoolRepository.deleteById(id);
    }
}
