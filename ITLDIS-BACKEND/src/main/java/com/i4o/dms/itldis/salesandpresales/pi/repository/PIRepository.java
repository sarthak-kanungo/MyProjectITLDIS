package com.i4o.dms.itldis.salesandpresales.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.salesandpresales.pi.domain.ProformaInvoice;
import java.util.List;

/**
 * PI Repository
 * TODO: Add custom query methods based on legacy DAO methods
 */
@Repository
public interface PIRepository extends JpaRepository<ProformaInvoice, Long> {
    
    /**
     * Find PI by PI number
     */
    ProformaInvoice findByPiNo(String piNo);
    
    /**
     * Find PIs by dealer code
     */
    List<ProformaInvoice> findByDealerCode(String dealerCode);
    
    /**
     * Find PIs by dealer code and status
     */
    @Query("SELECT pi FROM ProformaInvoice pi WHERE pi.dealerCode = :dealerCode AND pi.status = :status")
    List<ProformaInvoice> findByDealerCodeAndStatus(@Param("dealerCode") String dealerCode, 
                                                     @Param("status") String status);
}
