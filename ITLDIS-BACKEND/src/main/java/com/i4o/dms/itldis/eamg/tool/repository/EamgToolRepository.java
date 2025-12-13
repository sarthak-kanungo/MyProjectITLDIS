package com.i4o.dms.itldis.eamg.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.eamg.tool.domain.EamgTool;

/**
 * EAMG Tool Repository
 */
@Repository
public interface EamgToolRepository extends JpaRepository<EamgTool, Long> {
    EamgTool findByToolNo(String toolNo);
}
