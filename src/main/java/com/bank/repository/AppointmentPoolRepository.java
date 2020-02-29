package com.bank.repository;

import com.bank.domain.AppointmentPool;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AppointmentPool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppointmentPoolRepository extends JpaRepository<AppointmentPool, Long> {

}
