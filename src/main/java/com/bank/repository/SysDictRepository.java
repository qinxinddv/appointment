package com.bank.repository;

import com.bank.domain.SysDict;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the SysDict entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysDictRepository extends JpaRepository<SysDict, Long> {
    List<SysDict> findDistinctByType(String type);
}
