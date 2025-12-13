package com.i4o.dms.kubota.service.activityclaim.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.kubota.service.activityclaim.domain.ServiceActivityClaimApproval;

@Transactional
public interface ServiceActivityClaimApprovalRepository extends JpaRepository<ServiceActivityClaimApproval, Long> {
    @Query(value = "{call sp_service_activity_claim_get_approval_hierarchy_level(:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getServiceActivityClaimApprovalHierarchyLevel(@Param("dealerId") Long dealerId);

    @Query(value = "{call sp_service_activity_claim_approve_activity_claim(:claimId,:userId,:remark,:usercode,:approvalStatus,:approvedAmount)}", nativeQuery = true)
    String approveServiceActivityClaim(@Param("claimId") Long claimId, @Param("userId") Long userId,
                                       @Param("remark") String remark, @Param("usercode") String usercode, @Param("approvalStatus") String approvalStatus, @Param("approvedAmount")Double approvedAmount);

    @Modifying
    @Query(value = "update SV_ACTIVITY_PROPOSAL_SUB_ACTIVITY set claim_approved_amount=:amount, remark=:remark where id=:id", nativeQuery = true)
    void updateSubActiivty(@Param("amount") Float amount, @Param("remark") String remark, @Param("id")Long id);

    @Modifying
    @Query(value = "update SV_ACTIVITY_PROPOSAL_heads set claim_approved_amount=:amount, remark=:remark where id=:id", nativeQuery = true)
    void updateHeads(@Param("amount") Float amount, @Param("remark") String remark, @Param("id")Long id);

    
    @Query(value = "{call sp_service_activity_claim_getApprovalHierarchyDetails(:claimId, :usercode)}", nativeQuery = true)
    List<Map<String,Object>> getApprovalHierDetails(@Param("claimId") Long claimId, @Param("usercode") String usercode);
}
