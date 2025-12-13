package com.i4o.dms.itldis.eamg.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.eamg.group.domain.EamgGroup;
import java.util.List;

/**
 * EAMG Group Repository
 * TODO: Add custom query methods based on legacy DAO methods
 * Reference: EAMG.Group.DAO.*
 */
@Repository
public interface EamgGroupRepository extends JpaRepository<EamgGroup, Long> {
    
    /**
     * Find group by group number
     */
    EamgGroup findByGroupNo(String groupNo);
    
    /**
     * Find all groups
     */
    List<EamgGroup> findAll();
    
    // TODO: Add more query methods based on EAMG.Group.DAO methods
}
