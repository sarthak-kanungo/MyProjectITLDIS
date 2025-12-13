package com.i4o.dms.kubota.salesandpresales.purchase.machinediscrepancyclaim.repository;

import com.i4o.dms.kubota.salesandpresales.purchase.machinedescripancycomplaint.domain.MachineDescripancyComplaintDetail;
import com.i4o.dms.kubota.salesandpresales.purchase.machinediscrepancyclaim.domain.MachineDiscrepancyClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface MachineDiscrepancyClaimRepository extends JpaRepository<MachineDiscrepancyClaim,Long> {
    //
    @Query(value = "{call sp_salesandpresales_purchase_machine_discrepancy_claim_search_claim_number(:claimNumber)}",nativeQuery = true)
    List<Map<String,Object>> searchClaimNumber(@Param("claimNumber") String claimNumber);

    @Query(value = "{call sp_salesandpresales_purchase_machine_discrepancy_claim_search_claim_status()}",nativeQuery = true)
    List<Map<String,Object>> getClaimStatus();


    @Query(value = "{call sp_sales_and_presales_machine_descripancy_claim_search" +
            "(:claimNumber,:claimStatus,:fromDate,:toDate,:page,:size,:username)}", nativeQuery = true)
    List<ResponseSearchDto> searchBy(
            @Param("claimNumber") String claimNumber,
            @Param("claimStatus") String claimStatus,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("username")String username);

    @Query(value = "{call sp_sales_and_presales_machine_descripancy_claim_search_count(" +
            ":claimNumber,:claimStatus,:fromDate,:toDate,:page,:size)}", nativeQuery = true)
    Long searchByCount(
            @Param("claimNumber") String claimNumber,
            @Param("claimStatus") String claimStatus,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("page") Integer page,
            @Param("size") Integer size);

    @Query(value = "{call sp__salesandpresales_machine_discrepancy_claim_compalint_details_get_by_claimid(:claimId)}",nativeQuery = true)
    List<MachineDescripancyComplaintDetail> getComplaintDetailsByClaimId(@Param("claimId")Long claimId);
}
