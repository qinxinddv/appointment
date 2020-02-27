package com.bank.service.impl;

import com.bank.service.AppointmentConfigService;
import com.bank.domain.AppointmentConfig;
import com.bank.repository.AppointmentConfigRepository;
import com.bank.service.dto.AppointmentConfigDTO;
import com.bank.service.mapper.AppointmentConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AppointmentConfig}.
 */
@Service
@Transactional
public class AppointmentConfigServiceImpl implements AppointmentConfigService {

    private final Logger log = LoggerFactory.getLogger(AppointmentConfigServiceImpl.class);

    private final AppointmentConfigRepository appointmentConfigRepository;

    private final AppointmentConfigMapper appointmentConfigMapper;

    public AppointmentConfigServiceImpl(AppointmentConfigRepository appointmentConfigRepository, AppointmentConfigMapper appointmentConfigMapper) {
        this.appointmentConfigRepository = appointmentConfigRepository;
        this.appointmentConfigMapper = appointmentConfigMapper;
    }

    /**
     * Save a appointmentConfig.
     *
     * @param appointmentConfigDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AppointmentConfigDTO save(AppointmentConfigDTO appointmentConfigDTO) {
        log.debug("Request to save AppointmentConfig : {}", appointmentConfigDTO);
        AppointmentConfig appointmentConfig = appointmentConfigMapper.toEntity(appointmentConfigDTO);
        appointmentConfig = appointmentConfigRepository.save(appointmentConfig);
        return appointmentConfigMapper.toDto(appointmentConfig);
    }

    /**
     * Get all the appointmentConfigs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AppointmentConfigDTO> findAll() {
        log.debug("Request to get all AppointmentConfigs");
        return appointmentConfigRepository.findAll().stream()
            .map(appointmentConfigMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one appointmentConfig by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AppointmentConfigDTO> findOne(Long id) {
        log.debug("Request to get AppointmentConfig : {}", id);
        return appointmentConfigRepository.findById(id)
            .map(appointmentConfigMapper::toDto);
    }

    /**
     * Delete the appointmentConfig by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AppointmentConfig : {}", id);
        appointmentConfigRepository.deleteById(id);
    }
}
