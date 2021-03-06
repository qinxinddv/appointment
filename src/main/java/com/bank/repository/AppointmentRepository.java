package com.bank.repository;

import com.bank.domain.Appointment;

import com.bank.domain.enumeration.AppointStateEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data  repository for the Appointment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> ,JpaSpecificationExecutor{
    public long countByDateAndIdCard(String date,String idCard);
    public long countByDateAndMobile(String date,String mobile);
    public Page<Appointment> findByMobile(String mobile, Pageable pageable);
    public Page<Appointment> findByOrg_Id(long orgId, Pageable pageable);
    public List<Appointment> findByDateLessThanAndState(String date, AppointStateEnum appointStateEnum);
    @Transactional
    public void deleteByStateIn(AppointStateEnum[] stateEnums);
}
