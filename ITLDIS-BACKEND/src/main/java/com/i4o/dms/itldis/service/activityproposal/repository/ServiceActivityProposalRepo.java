package com.i4o.dms.itldis.service.activityproposal.repository;

import com.i4o.dms.itldis.service.activityproposal.domain.ServiceActivityProposal;
import com.i4o.dms.itldis.service.activityproposal.dto.SearchResponsiveServiceActivityProposal;
import com.i4o.dms.itldis.service.activityproposal.dto.ServiceActivityProposalViewResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ServiceActivityProposalRepo extends JpaRepository<ServiceActivityProposal,Long>
{

    @Query(value = "{call sp_service_activity_proposal_get_max_budget(:proposedBudget,:targetedNumbers,:activityId,:totalSubActivity)}", nativeQuery = true)
    Double getMAxAllowedBudget(@Param("proposedBudget") Double proposedBudget,
                               @Param("targetedNumbers") Integer targetedNumbers,
                               @Param("activityId") Long activityId,
                               @Param("totalSubActivity") Double totalSubActivity);


    @Query(value = "{call sp_service_activity_proposal_search(:fromDate,:toDate,:activityNumber,:activityStatus,:activityType,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size,:userCode,:includeInactive,:hierId)}", nativeQuery = true)
    List<SearchResponsiveServiceActivityProposal> searchServiceActivityProposal(
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("activityNumber") String activityNumber,
            @Param("activityStatus") String activityStatus,
            @Param("activityType") String activityType,
            @Param("dealerId") Long dealerId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("kubotaEmployeeId") Long kubotaEmployeeId,
            @Param("managementAccess") Boolean managementAccess,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("userCode") String userCode,
            @Param("includeInactive") Character includeInactive,
            @Param("hierId") Long hierId
    );


    @Query(value = "{call sp_service_activity_proposal_search_autocomplete(:searchString,:usercode)}", nativeQuery = true)
    List<Map<String, Object>> getActivityProposalSearchForActivityProposalListing(@Param("searchString") String searchString, @Param("usercode") String usercode);

    ServiceActivityProposalViewResponse findByActivityNumber(String activityNumber);


    @Query(value = "{call sp_service_activity_proposal_search_count(:fromDate,:toDate,:activityNumber,:activityStatus,:activityType,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size)}", nativeQuery = true)
    Long searchServiceActivityProposalCount(
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("activityNumber") String activityNumber,
            @Param("activityStatus") String activityStatus,
            @Param("activityType") String activityType,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("dealerId") Long dealerId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("kubotaEmployeeId") Long kubotaEmployeeId,
            @Param("managementAccess") Boolean managementAccess
    );


    @Query(value = "{call sp_service_activity_proposal_get_activity_number_by_activity_type(:activityTypeId,:activityNumber,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getActivityNumberByActivityType(@Param("activityTypeId") Long activityTypeId,
                                                              @Param("activityNumber") String activityNumber,
                                                              @Param("dealerId") Long dealerId);


    @Query(value = "{call sp_service_activity_proposal_get_all_status}", nativeQuery = true)
    List<Map<String, Object>> getServiceActivityProposalStatus();


    @Query("select m from ServiceActivityProposal m where m.id =:activityProposalId")
    ServiceActivityProposal findByActivityProposal(@Param("activityProposalId") Long activityProposalId);

    @Query(value="select top 1 remark from SV_ACTIVITY_PROPOSAL_APPROVAL where service_activity_proposal_id=:activityProposalId order by approved_date,ho_user_id desc", nativeQuery=true)
    String getLastKaiRemark(@Param("activityProposalId") Long activityProposalId);
    
    @Query(value="{call SP_SERVICE_ACTIVITY_PROPOSAL_PENDING_FOR_APPROVAL(:userCode)}", nativeQuery=true)
    List<Map<String, Object>> getProposalPendingForApproval(@Param("userCode") String userCode);
    
}

