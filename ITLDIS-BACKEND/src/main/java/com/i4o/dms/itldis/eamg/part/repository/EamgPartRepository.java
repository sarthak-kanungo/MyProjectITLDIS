package com.i4o.dms.itldis.eamg.part.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.eamg.part.domain.EamgPart;

/**
 * EAMG Part Repository
 */
@Repository
public interface EamgPartRepository extends JpaRepository<EamgPart, Long> {
    EamgPart findByPartNo(String partNo);
}
