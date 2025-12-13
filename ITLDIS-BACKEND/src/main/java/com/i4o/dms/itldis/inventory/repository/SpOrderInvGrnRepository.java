package com.i4o.dms.itldis.inventory.repository;

import com.i4o.dms.itldis.inventory.domain.SpOrderInvGrn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for SP Order Inventory GRN
 */
@Repository
public interface SpOrderInvGrnRepository extends JpaRepository<SpOrderInvGrn, String> {
    
    /**
     * Find GRN by dealer code
     */
    List<SpOrderInvGrn> findByDealerCode(String dealerCode);
    
    /**
     * Find GRN by order number
     */
    List<SpOrderInvGrn> findByCustPoNo(String custPoNo);
}

