package com.bank.repository;

import com.bank.domain.Appointment;

import com.bank.domain.enumeration.AppointStateEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Appointment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> ,JpaSpecificationExecutor{
    public long countByDateAndIdCardOrMobile(String date,String idCard,String mobile);
    public Page<Appointment> findByMobile(String mobile, Pageable pageable);
    public Page<Appointment> findByOrg_Id(long orgId, Pageable pageable);
    public List<Appointment> findByDateLessThanAndState(String date, AppointStateEnum appointStateEnum);
}
