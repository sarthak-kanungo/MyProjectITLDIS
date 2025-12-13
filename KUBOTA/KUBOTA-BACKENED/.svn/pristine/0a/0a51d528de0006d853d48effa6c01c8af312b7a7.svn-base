package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.repository;


import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.domain.MarketingActivityProposal;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.dto.ResponseSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

@Transactional
public interface MarketingActivityProposalRepo extends JpaRepository<MarketingActivityProposal,Long> {

    @Query(value = "{call sp_SM_activity_proposal_get_activity_number(:userCode,:activityNumber,:dealerId,:hoUserId,:dealerEmpId,:managementAccess,:functionality)}",nativeQuery = true)
    List<Map<String,Object>> searchActivityNumber(@Param("userCode") String userCode,@Param("activityNumber") String activityNumber,
    		@Param("dealerId") Long dealerId,
            @Param("hoUserId") Long hoUserId,
            @Param("dealerEmpId") Long dealerEmpId,
            @Param("managementAccess") Boolean managementAccess,
            @Param("functionality") String functionality);

    @Query(value = "{call sp_SM_activity_proposal_get_activity_type()}",nativeQuery = true)
    List<Map<String,Object>> searchActivityType();

    @Query(value = "{call sp_getClaimableAmountForActivity(:activityTypeId, :budget, :maxLimit)}",nativeQuery = true)
    Double getClaimableAmount(Integer activityTypeId, Double budget, Double maxLimit);
    
    @Query(value = "{call sp_getOrgLevelByHODept(:deptCode)}",nativeQuery = true)
    List<Map<String,Object>> getOrgLevelByHODept(@Param("deptCode") String deptCode);
    
    @Query(value = "{call sp_getOrgLevelHierForParent(:userCode, :levelId, :hierId, 'N')}",nativeQuery = true)
    List<Map<String,Object>> getOrgLevelHierForParent(@Param("userCode") String userCode,
    		@Param("levelId") Integer levelId,
    		@Param("hierId") Integer hierId);
    
    @Query(value = "{call sp_search_activity_proposal(:usercode,:activityNumber,:activityStatus,:activityType,:fromDate,:toDate,"
    		+ ":dealerId,:hoUserId,:dealerEmpId,:managementAccess,:page,:size,'N',:hierId,:state)}", nativeQuery = true)
    List<ResponseSearchDto> searchBy(
    		@Param("usercode") String usercode,
            @Param("activityNumber") String poNumber,
            @Param("activityStatus") String depot,
            @Param("activityType")Long activityType,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("dealerId") Long dealerId,
            @Param("hoUserId") Long hoUserId,
            @Param("dealerEmpId") Long dealerEmpId,
            @Param("managementAccess") Boolean managementAccess,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("hierId") Integer hierId ,
            @Param("state") Long state
    );
    
    @Modifying
    @Query(value="{call SP_SM_PROPOSAL_UPDATE_GRACE(:activityId)}", nativeQuery=true)
    void updateGracePeriod(@Param("activityId") Integer activityId);

   /* @Query(value = "{call sp_search_activity_proposal_count(:activityNumber,:activityStatus,:fromDate,:toDate,:dealerId,:hoUserId,:dealerEmpId,:managementAccess,:page,:size)}", nativeQuery = true)
    Long searchByCount(
            @Param("activityNumber") String poNumber,
            @Param("activityStatus") String depot,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("dealerId") Long dealerId,
            @Param("hoUserId") Long hoUserId,
            @Param("dealerEmpId") Long dealerEmpId,
            @Param("managementAccess") Boolean managementAccess,
            @Param("page") Integer page,
            @Param("size") Integer size);*/

    @Query(value = "{call sp_sales_marketing_activity_proposal_proposal_details_by_activity_number(:activityNumber)}", nativeQuery = true)
    Map<String,Object> proposalDetailsByActivityNumber(@Param("activityNumber") String activityNumber);


    //not used
    @Query(value = "{call sp_get_activityProposalById(:id)}",nativeQuery = true)
    List<Map<String,Object>> activityProposalById(@Param("id") Long id);


    //Stored Procedure Not Available
    @Query(value = "{call sp_getEnquiryForProposal(:enquiryNumber,:dealerId)}",nativeQuery = true)
    List<Map<String,Object>> searchEnquiryForProposal(@Param("enquiryNumber")String enquiryNumber,
                                                   @Param("dealerId")Long dealerId);

    //Not Used
    @Query(value = "{call sp_marketing_activity_proposal_get_enquiry_for_activity_proposal_by_activity_numbers(:activityNumber)}",nativeQuery = true)
    List<Map<String,Object>> getEnquiryForActivityPurpose(@Param("activityNumber")String activityNumber);

    //Not Used
    @Query(value = "{call sp_marketing_activity_proposal_get_enquiry_for_activity_purpose(:id)}",nativeQuery = true)
    List<Map<String,Object>> getEnquiryForActivityPurposeById(@Param("id")Long id);

    //ActivityProposalViewDto findByActivityProposalId(Long activityProposalId);

    //Not Used
    @Query("select m from MarketingActivityProposal m where m.id =:activityProposalId")
    MarketingActivityProposal findByActivityProposal(@Param("activityProposalId") Long activityProposalId);

    @Query(value="{call sp_getZonalAndRegionalmanagerForDealer(:dealerId)}",nativeQuery = true)
    Map<String,Object> getZonalAndRegionalmanagerForDealer(@Param("dealerId") Long id);




    @Query(value = "{call sp_get_head_by_activity_type(:activityTypeId)}",nativeQuery = true)
    List<Map<String,Object>> getHeadsByActivityType(@Param("activityTypeId")Long activityTypeId);

    @Query(value = "{call sp_get_enquiry_source_by_source_purpose(:sourcePurposeId, :dealerId)}",nativeQuery = true)
    List<Map<String,Object>> getEnquirySourceByPurpose(@Param("sourcePurposeId")Long sourcePurposeId,
    		@Param("dealerId") Long dealerId);

    @Query(value = "{call sp_getMaximumLimitByActivityType(:activityTypeId,:dealerId,:FromDate,:ToDate)}",nativeQuery = true)
    Map<String,Object> getMaximumLimitByActivityType(@Param("activityTypeId") String activityType, @Param("dealerId") Long dealerId,
    		@Param("FromDate") String fromDate, @Param("ToDate") String toDate);

    @Query(value="{call sp_marketing_activity_proposal_get_header_data_by_proposal_id(:activityProposalId)}",nativeQuery = true)
    Map<String,Object> getHeaderDataById(@Param("activityProposalId") Long activityProposalId);

    @Query(value="{call sp_marketing_activity_proposal_get_enquiry_details(:enquiryNumberId)}",nativeQuery = true)
    Map<String,Object> getEnquiryDetails(@Param("enquiryNumberId") Long enquiryNumberId);

    @Query(value="{call sp_mkt_act_proposal_getOpenEnquiryList(:dealerId,:enquiryNumber)}",nativeQuery = true)
    List<Map<String,Object>> getEnquiryNumberAutocomplete(@Param("dealerId") Long dealerId,
                                                          @Param("enquiryNumber") String enquiryNumber);

    @Query(value="{call sp_marketing_activity_proposal_get_header_data_by_proposal_id(:activityProposalId)}",nativeQuery = true)
    Map<String,Object>getViewHeaderData( @Param("activityProposalId") Long activityProposalId);

    @Query(value="{call sp_marketing_activity_proposal_get_view_heads_data(:activityProposalId)}",nativeQuery = true)
    List<Map<String,Object>>getViewHeadsData( @Param("activityProposalId") Long activityProposalId);

    @Query(value="{call sp_marketing_activity_proposal_get_view_enquiry_data(:activityProposalId)}",nativeQuery = true)
    List<Map<String,Object>>getViewEnquiryData( @Param("activityProposalId") Long activityProposalId);

    @Query(value="{call sp_marketing_activity_proposal_get_dealer_info(:activityProposalId)}",nativeQuery = true)
    Map<String,Object>getViewDealerInfo( @Param("activityProposalId") Long activityProposalId);

    Optional<MarketingActivityProposal> findByActivityProposalId(Long activityProposalId);

    @Query(value="{call sp_marketing_activity_proposal_get_proposal_number_for_search(:activityNumber,:dealerId)}",nativeQuery = true)
    List<Map<String,Object>>getActivityProposalNumberForSearch( @Param("activityNumber") String activityNumber,
                                                                @Param("dealerId") Long dealerId);
    
    @Query(value="{call sp_mkt_getApprovalHierarchyDetails(:activityProposalId,:userCode)}",nativeQuery = true)
    List<Map<String,Object>> getApprovalHierDetails(@Param("activityProposalId") Long activityProposalId, @Param("userCode") String userCode);
    
    @Query(value = "{call sp_sales_marketing_activity_proposal_pending_for_approval(:usercode)}", nativeQuery = true)
    List<Map<String, Object>> getProposalsPendingForApproval(@Param("usercode")String userCode);
    
    @Query(value = "{call SP_GET_DEALER_PROPOSAL_COUNT(:proposalId)}", nativeQuery = true)
    Map<String, Object> getCountOfProposal(@Param("proposalId") Long proposalId);
    
}
