package com.bank.repository;

import com.bank.domain.Appointment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Appointment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    public long countByDateAndIdCardOrMobile(String date,String idCard,String mobile);
    public Page<Appointment> findByMobile(String mobile, Pageable pageable);
}
