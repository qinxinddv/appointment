package com.bank.repository;

import com.bank.domain.AppointmentConfig;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AppointmentConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppointmentConfigRepository extends JpaRepository<AppointmentConfig, Long> {

}
