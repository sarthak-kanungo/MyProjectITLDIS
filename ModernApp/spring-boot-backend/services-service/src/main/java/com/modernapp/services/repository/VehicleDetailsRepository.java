package com.modernapp.services.repository;

import com.modernapp.services.model.VehicleDetails;
import com.modernapp.services.model.DealerVsLocationCode;
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
           "AND (:chassisNo IS NULL OR v.vinNo LIKE CONCAT('%', :chassisNo, '%')) " +
           "AND (:dealerCode IS NULL OR v.dealerCode = :dealerCode)")
    List<VehicleDetails> findPendingPdiChassis(@Param("chassisNo") String chassisNo, 
                                               @Param("dealerCode") String dealerCode);

    @Query("SELECT new com.modernapp.services.dto.PendingInstallationDTO(" +
           "v.vinNo, v.modelFamily, v.modelCode, v.customerName, v.mobilePh, " +
           "d.dealerName, d.dealerCode, d.location, d.locationCode, v.deliveryDate) " +
           "FROM VehicleDetails v, DealerVsLocationCode d " +
           "WHERE v.dealerCode = d.dealerCode " +
           "AND v.pdiStatus = 'Y' AND (v.insStatus = 'N' OR v.insStatus IS NULL) " +
           "AND v.deliveryDate IS NOT NULL " +
           "AND (:chassisNo IS NULL OR v.vinNo LIKE CONCAT('%', :chassisNo, '%')) " +
           "AND (:dealerCode IS NULL OR v.dealerCode = :dealerCode)")
    List<com.modernapp.services.dto.PendingInstallationDTO> findPendingInstallations(
            @Param("chassisNo") String chassisNo,
            @Param("dealerCode") String dealerCode);
}

