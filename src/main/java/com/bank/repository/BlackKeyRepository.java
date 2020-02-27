package com.bank.repository;

import com.bank.domain.BlackKey;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BlackKey entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlackKeyRepository extends JpaRepository<BlackKey, Long> {

}
