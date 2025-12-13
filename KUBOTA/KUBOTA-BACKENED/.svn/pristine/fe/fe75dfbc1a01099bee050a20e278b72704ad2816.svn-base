package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.domain.MarketingActivityClaimApproval;

@Transactional
public interface MarketingActivityClaimApprovalRepository extends JpaRepository<MarketingActivityClaimApproval, Long> {

   /* @Query("select c from MarketingActivityClaimApproval c where c.marketingActivityClaim.id=:claimId and c.kubotaEmployeeMaster.id=:userId")
    MarketingActivityClaimApproval findByMarketingActivityClaimAndKubotaEmployeeId(@Param("claimId")Long claimId, @Param("userId")Long userId);
*/
    @Query(value = "{call sp_sales_presale_activity_claim_get_approval_hierarchy_level(:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getMarketingActivityClaimApprovalHierarchyLevel(@Param("dealerId") Long dealerId);

    @Query(value = "{call sp_sales_presales_activity_claim_approve_activity(:claimId,:userId,:remark,:userCode,:approvalStatus, :approvedAmount)}", nativeQuery = true)
    String approveMarketingActivityClaim(@Param("claimId") Long claimId, @Param("userId") Long userId, @Param("remark") String remark, 
    		@Param("userCode") String userCode, @Param("approvalStatus") String approvalStatus, @Param("approvedAmount")Double approvedAmount);
    
    
    @Query(value="{call sp_sales_presales_activity_claim_getApprovalHierarchyDetails(:Id,:userCode)}",nativeQuery = true)
    List<Map<String,Object>> getApprovalHierDetails(@Param("Id") Long Id, @Param("userCode") String userCode);
    
    @Modifying
    @Query(value="update SM_activity_claim set total_approved_amount=:amount where claim_id=:id and claim_status='Approved'", nativeQuery=true)	//Suraj--Changed a condition in where clause "and claim_status = 'Approved'"--03-03-2023
    void updateApprovedAmount(@Param("amount")double amount, @Param("id") Long Id);
}
