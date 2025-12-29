package com.modernapp.services.repository;

import com.modernapp.services.model.VehicleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDetailsRepository extends JpaRepository<VehicleDetails, String> {
    
    List<VehicleDetails> findByPdiStatus(Character pdiStatus);
    
    List<VehicleDetails> findByVinNoContainingIgnoreCase(String vinNo);
    
    List<VehicleDetails> findByDealerCode(String dealerCode);
    
    @Query("SELECT v FROM VehicleDetails v WHERE v.pdiStatus = 'N' " +
           "AND (:chassisNo IS NULL OR v.vinNo LIKE %:chassisNo%) " +
           "AND (:dealerCode IS NULL OR v.dealerCode = :dealerCode)")
    List<VehicleDetails> findPendingPdiChassis(@Param("chassisNo") String chassisNo, 
                                               @Param("dealerCode") String dealerCode);
}

