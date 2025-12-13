package com.i4o.dms.itldis.service.activityclaim.repository;

import com.i4o.dms.itldis.service.activityclaim.domain.ServiceActivityClaim;
import com.i4o.dms.itldis.service.activityclaim.dto.ActivityClaimSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ServiceActivityClaimRepo extends JpaRepository<ServiceActivityClaim,Long>
{
    @Query(value = "{call sp_service_activity_claim_get_activity_number_autocomplete(:activityNumber,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getActivityNumberForClaim(@Param("activityNumber") String activityNumber,
                                                        @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_service_activity_claim_get_header_data(:activityNumberId)}", nativeQuery = true)
    Map<String, Object> getHeaderData(@Param("activityNumberId") Long activityNumberId);

    @Query(value = "{call sp_service_activity_claim_get_heads(:activityNumberId)}", nativeQuery = true)
    List<Map<String, Object>> getHeadsData(@Param("activityNumberId") Long activityNumberId);


    @Query(value = "{call sp_service_activity_claim_search(:fromDate,:toDate,:activityClaimNumber,:activityClaimStatus,:activityNumber,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size,:usercode,:includeInactive,:orgHierarchyId)}",nativeQuery = true)
    List<ActivityClaimSearchResponse> searchActivityClaim(@Param("fromDate") String fromDate,
                                                          @Param("toDate") String toDate,
                                                          @Param("activityClaimNumber") String activityClaimNumber,
                                                          @Param("activityClaimStatus") String activityClaimStatus,
                                                          @Param("activityNumber") String activityNumber,
                                                          @Param("dealerId") Long dealerId,
                                                          @Param("dealerEmployeeId") Long dealerEmployeeId,
                                                          @Param("kubotaEmployeeId") Long kubotaEmployeeId,
                                                          @Param("managementAccess") Boolean managementAccess,
                                                          @Param("page") Integer page,
                                                          @Param("size") Integer size,
                                                          @Param("usercode") String usercode,
                                                          @Param("includeInactive") Character includeInactive,
                                                          @Param("orgHierarchyId") Long orgHierarchyId);

    @Query(value="{call sp_service_activity_claim_get_view_header_data(:serviceClaimId)}",nativeQuery = true)
    Map<String,Object>getHeaderDataForView( @Param("serviceClaimId") Long serviceClaimId);

    @Query(value="{call sp_service_activity_claim_get_view_heads_data(:serviceClaimId)}",nativeQuery = true)
    List<Map<String,Object>>getHeadsDataForView( @Param("serviceClaimId") Long serviceClaimId);

    @Query(value="{call sp_service_activity_claim_get_view_sub_activities(:serviceClaimId)}",nativeQuery = true)
    List<Map<String,Object>>getSubActivitiesForView( @Param("serviceClaimId") Long serviceClaimId);

    @Query(value="{call sp_service_activity_claim_get_claim_number_for_search_autocomplete(:userCode,:claimNumber)}",nativeQuery = true)
    List<Map<String,Object>>getClaimNumberForSearch( @Param("userCode") String userCode,
                                                     @Param("claimNumber") String claimNumber);

    @Query(value="{call sp_service_activity_claim_get_activity_number_for_search(:usercode,:activityNumber)}",nativeQuery = true)
    List<Map<String,Object>>getActivityNumberForSearch( @Param("usercode") String usercode,
                                                        @Param("activityNumber") String activityNumber);


    @Query(value = "{call sp_service_activity_claim_get_report_photos(:activityNumberId)}", nativeQuery = true)
    List<Map<String, Object>> getActivityReportPhotos(@Param("activityNumberId") Long activityNumberId);

    @Query(value="{call sp_service_activity_claim_get_sub_activities(:activityProposalId)}",nativeQuery = true)
    List<Map<String,Object>>getSubActivityForClaim( @Param("activityProposalId") Long activityProposalId);

    @Query(value="{call sp_service_activity_claim_get_view_report_photos(:serviceClaimId)}",nativeQuery = true)
    List<Map<String,Object>>getViewReportPhoto( @Param("serviceClaimId") Long serviceClaimId);
    
    @Query(value="{call SP_SERVICE_ACTIVITY_CLAIM_PENDINGS_FOR_APPROVAL(:userCode)}", nativeQuery=true)
    List<Map<String, Object>> getClaimPendingForApproval(@Param("userCode") String userCode);

}
