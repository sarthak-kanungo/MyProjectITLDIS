package com.i4o.dms.itldis.inventory.repository;

import com.i4o.dms.itldis.inventory.domain.SpOrderHeaderExp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for SP Order Header EXP
 */
@Repository
public interface SpOrderHeaderExpRepository extends JpaRepository<SpOrderHeaderExp, String> {
    
    /**
     * Find orders by dealer code and status
     */
    List<SpOrderHeaderExp> findByDealerCodeAndStatus(String dealerCode, String status);
    
    /**
     * Find orders by dealer code and order type
     */
    List<SpOrderHeaderExp> findByDealerCodeAndOrdType(String dealerCode, String ordType);
    
    /**
     * Find open orders by dealer code and order type
     */
    @Query("SELECT o FROM SpOrderHeaderExp o WHERE o.dealerCode = :dealerCode " +
           "AND o.ordType = :ordType AND o.status = 'OPEN'")
    List<SpOrderHeaderExp> findOpenOrdersByDealerAndType(@Param("dealerCode") String dealerCode, 
                                                          @Param("ordType") String ordType);
}

