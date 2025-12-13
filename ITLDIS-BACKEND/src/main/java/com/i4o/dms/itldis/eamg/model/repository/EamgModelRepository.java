package com.i4o.dms.itldis.eamg.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.eamg.model.domain.EamgModel;

/**
 * EAMG Model Repository
 */
@Repository
public interface EamgModelRepository extends JpaRepository<EamgModel, Long> {
    EamgModel findByModelNo(String modelNo);
}
