package com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.domain.MarketingActivityProposalApproval;

public interface MarketingActivityProposalApprovalRepository extends JpaRepository<MarketingActivityProposalApproval, Long> {

    @Query(value = "{call sp_mkt_act_proposal_get_approval_hierarchy(:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getMarketingActivityProposalApprovalHierarchyLevel(@Param("dealerId") Long dealerId);

    @Transactional
    @Query(value = "{call sp_sales_presales_activity_proposal_approve_activity(:proposalId,:houserId,:remark,:usercode,:approvalStatus,:approvedAmount)}", nativeQuery = true)
    String approveMarketingActivityProposal(@Param("proposalId") Long proposalId, @Param("houserId") Long houserId,
                                            @Param("remark") String remark, @Param("usercode") String usercode, @Param("approvalStatus")String approvalStatus,@Param("approvedAmount") Double approvedAmount);

    //MarketingActivityProposalApproval findByMarketingActivityProposalAndKubotaEmployeeMasterId(Long proposalId, Long userId);
}
