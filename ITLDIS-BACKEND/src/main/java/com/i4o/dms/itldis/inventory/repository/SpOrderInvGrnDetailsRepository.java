package com.i4o.dms.itldis.inventory.repository;

import com.i4o.dms.itldis.inventory.domain.SpOrderInvGrnDetails;
import com.i4o.dms.itldis.inventory.domain.SpOrderInvGrnDetailsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for SP Order Inventory GRN Details
 */
@Repository
public interface SpOrderInvGrnDetailsRepository extends JpaRepository<SpOrderInvGrnDetails, SpOrderInvGrnDetailsPK> {
    
    /**
     * Find GRN details by GRN number
     */
    List<SpOrderInvGrnDetails> findBySpOrderInvGrnDetailsPK_GrNo(String grNo);
}

