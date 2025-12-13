package com.i4o.dms.kubota.service.activityproposal.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.kubota.service.activityproposal.domain.ServiceActivityProposalApproval;

public interface ServiceActivityProposalApprovalRepository extends JpaRepository<ServiceActivityProposalApproval,Long> {
    @Query(value = "{call sp_service_activity_proposal_get_approval_hierarchy_level(:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getServiceActivityProposalApprovalHierarchyLevel(@Param("dealerId") Long dealerId);

    @Transactional
    @Query(value = "{call sp_service_activity_proposal_approve_activity_proposal(:proposalId,:userId,:remark,:usercode,:approvalStatus,:approvedAmount)}", nativeQuery = true)
    String approveServiceActivityProposal(@Param("proposalId") Long proposalId, @Param("userId") Long userId,
                                          @Param("remark") String remark, @Param("usercode") String usercode, @Param("approvalStatus") String approvalStatus, @Param("approvedAmount")Double approvedAmount);
   
}
