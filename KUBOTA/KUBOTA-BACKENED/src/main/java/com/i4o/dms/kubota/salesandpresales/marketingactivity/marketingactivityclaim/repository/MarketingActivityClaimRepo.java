package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.repository;

import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.domain.MarketingActivityClaim;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.dto.MarketingActivityClaimHeadDto;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.dto.MarketingActivityDetailsForClaimDto;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.dto.MarketingActivityReportImagesDto;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.dto.SearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface MarketingActivityClaimRepo extends JpaRepository<MarketingActivityClaim,Long> {

//    @Query(value = "{call sp_searchActivityNumberForClaim(:activityNumber,:dealerId)}", nativeQuery = true)
//    List<Map<String,Object>> searchActivityNumberForClaim(@Param("activityNumber") String activityNumber,@Param("dealerId")Long dealerId);

    @Query(value = "{call sp_getMarketingActivityClaimDetailsByActivityNumber(:activityNumber)}", nativeQuery = true)
    MarketingActivityDetailsForClaimDto  getMarketingActivityClaimDetailsByActivityNumber(@Param("activityNumber") String activityNumber);

    @Query(value = "{call sp_marketing_activity_proposal_get_head_by_activity_number(:activityNumber)}", nativeQuery = true)
    List<MarketingActivityClaimHeadDto> getMarketingActivityClaimHeadDto(@Param("activityNumber") String activityNumber);

    @Query(value = "{call sp_marketing_activity_report_get_images_by_activity_number(:activityNumber)}", nativeQuery = true)
    List<MarketingActivityReportImagesDto> getMarketingReportImagesForClaim(@Param("activityNumber") String activityNumber);

    @Query(value = "{call sp_sales_presales_marketing_activity_claim_search_claim_number(:claimNumber,:dealerId, :usercode)}", nativeQuery = true)
    List<Map<String,Object>> searchClaimNumber(@Param("claimNumber") String claimNumber,@Param("dealerId")Long dealerId, @Param("usercode") String usercode);

    @Query(value = "{call sp_sales_and_presales_marketing_activity_claim_search_activity_proposal_number(:activityNumber,:dealerId, :usercode)}", nativeQuery = true)
    List<Map<String,Object>> searchActivityNumber(@Param("activityNumber") String activityNumber,
                                                  @Param("dealerId") Long dealerId,
                                                  @Param("usercode") String usercode);

    @Query(value = "{call sp_sales_and_presales_marketing_activity_claim_get_claim_status()}", nativeQuery = true)
    List<Map<String,Object>> searchClaimStatus();

    @Query(value = "{call sp_sales_and_presales_marketing_activity_claim_get_effectiveness()}", nativeQuery = true)
    List<Map<String,Object>> getActivityEffectiveness();


    @Query(value = "{call sp_sales_and_presales_marketing_activity_claim_search_claim(:claimNumber,:activityType,:activityNumber,:claimStatus,:fromDate,:toDate,:dealerId,:hoUserId,:dealerEmpId,:managementAccess,"
    		+ ":page,:size,:userCode,:includeInactive,:org_hierarchy_id,:state)}", nativeQuery = true)
    List<SearchDto> searchBy(
            @Param("claimNumber") String claimNumber,
            @Param("activityType") String activityType,
            @Param("activityNumber") String activityNumber,
            @Param("claimStatus") String claimStatus,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("dealerId")Long dealerId,
            @Param("hoUserId")Long hoUserId,
            @Param("dealerEmpId")Long dealerEmpId,
            @Param("managementAccess")Boolean managementAccess,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("userCode") String userCode,
            @Param("includeInactive")Character includeInactive,
            @Param("org_hierarchy_id")Long orgId,
            @Param("state")Long state);

    //sp_sales_and_presales_marketing_activity_claim_search_claim
    //sp_sales_and_presales_marketing_activity_claim_get_claim_status



    //Code By Tejas:-
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    @Query(value = "{call sp_sales_marketing_activity_claim_get_activity_number(:activityNumber,:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> searchActivityNumberForClaim(@Param("activityNumber") String activityNumber,
                                                          @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_sales_marketing_activity_claim_get_header_data(:activityNumberId)}", nativeQuery = true)
    Map<String,Object> getActivityClaimHeaderData(@Param("activityNumberId") Long activityNumberId);

    @Query(value = "{call sp_sales_marketing_activity_claim_get_heads(:activityNumberId)}", nativeQuery = true)
    List<Map<String,Object>> getActivityClaimHeads(@Param("activityNumberId") Long activityNumberId);

    @Query(value = "{call sp_sales_marketing_activity_claim_get_view_header_data(:claimId)}", nativeQuery = true)
    Map<String,Object> getViewHeaderData(@Param("claimId") Long claimId);

    @Query(value = "{call sp_sales_marketing_activity_claim_get_view_heads_data(:claimId)}", nativeQuery = true)
    List<Map<String,Object>> getViewHeads(@Param("claimId") Long claimId);

    @Query(value = "{call sp_sales_marketing_activity_claim_get_dealer_info(:claimId)}", nativeQuery = true)
    Map<String,Object> getDealerInfo(@Param("claimId") Long claimId);

    @Query(value = "{call sp_sales_marketing_activity_claim_get_view_report_photos(:claimId)}", nativeQuery = true)
    List<Map<String,Object>> getViewReportPhotos(@Param("claimId") Long claimId);

    @Query(value = "{call sp_marketing_activity_claim_check_activity_number(:dealerId, :activityNumber)}", nativeQuery = true)
    List<Map<String,Object>> checkActivityNumber(@Param("dealerId")Long dealerId,@Param("activityNumber") String activityNumber);

    @Query(value = "{call sp_gstValues}", nativeQuery = true)
    List<Map<String,Object>> getGstDropDown();

    @Query(value = "{call sp_sales_marketing_activity_claim_get_report_photos(:activityNumberId)}", nativeQuery = true)
    List<Map<String, Object>> getActivityReportPhotos(@Param("activityNumberId") Long activityNumberId);

    @Query(value = "{call sp_sales_marketing_activity_claim_pending_for_approval(:usercode)}", nativeQuery = true)
    List<Map<String, Object>> getClaimPendingForApproval(@Param("usercode")String userCode);

}
