package com.bank.repository;

import com.bank.domain.Org;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Org entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrgRepository extends JpaRepository<Org, Long> {

}
