package com.modernapp.services.repository;

import com.modernapp.services.model.InstallationDetails;
import com.modernapp.services.dto.ViewInstallationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstallationDetailsRepository extends JpaRepository<InstallationDetails, String> {

    @Query("SELECT new com.modernapp.services.dto.ViewInstallationDTO(" +
           "id.insNo, id.insDate, v.vinNo, v.modelFamily, v.customerName, v.mobilePh, " +
           "d.dealerName, d.dealerCode, d.location, v.deliveryDate) " +
           "FROM InstallationDetails id, VehicleDetails v, DealerVsLocationCode d " +
           "WHERE id.vinNo = v.vinNo " +
           "AND id.dealerCode = v.dealerCode " + 
           "AND v.dealerCode = d.dealerCode " +
           "AND v.insStatus = 'Y' " +
           "AND (:chassisNo IS NULL OR v.vinNo LIKE CONCAT('%', :chassisNo, '%')) " +
           "AND (:dealerCode IS NULL OR v.dealerCode = :dealerCode)")
    List<ViewInstallationDTO> findCompletedInstallations(
            @Param("chassisNo") String chassisNo,
            @Param("dealerCode") String dealerCode);
}
