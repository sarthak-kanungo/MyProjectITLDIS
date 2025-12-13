package com.i4o.dms.itldis.inventory.repository;

import com.i4o.dms.itldis.inventory.domain.SpOrderDetailsExp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for SP Order Details EXP
 */
@Repository
public interface SpOrderDetailsExpRepository extends JpaRepository<SpOrderDetailsExp, Integer> {
    
    /**
     * Find order details by order number
     */
    List<SpOrderDetailsExp> findByCustPoNo(String custPoNo);
}

