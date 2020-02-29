package com.bank.repository;

import com.bank.domain.AppointmentPool;

import com.bank.domain.enumeration.BusiTypeEnum;
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
    public long countByDateAndOrg_Id(String date,long orgId);

    public List<AppointmentPool> findByDateLessThan(String date);

    public Optional<AppointmentPool> findByOrg_IdAndBusiTypeAndDateAndPeriod(long orgId, BusiTypeEnum busiTypeEnum,String date,String period);

    public List<AppointmentPool> findByOrg_idAndBusiType(long orgId,BusiTypeEnum busiTypeEnum);
}
