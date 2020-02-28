package com.bank.repository;

import com.bank.domain.AppointmentPool;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the AppointmentPool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppointmentPoolRepository extends JpaRepository<AppointmentPool, Long> {

    //根据日期查询
    public Optional<List<AppointmentPool>> findByDate(String date);

    //根据日期查询
    public long countByDate(String date);
}
