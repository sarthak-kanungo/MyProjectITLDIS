package com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityreport.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityreport.domain.MarketingActivityReport;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityreport.dto.ReportViewResponse;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityreport.dto.SearchResponseMarketingActivityReportDto;

public interface MarketingActivityReportRepo extends JpaRepository<MarketingActivityReport,Long> {

//    @Query(value = "{call sp_report_getActivityNumber(:activityNumber)}",nativeQuery = true)
//    List<Map<String,Object>> getActivityNumber(@Param("activityNumber") String activityNumber);

    @Query(value = "{call sp_search_activity_report(:managementAccess,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:activityNumber,:activityType,:fromDate,:toDate,:page,:size,:usercode,:includeInactive,:hierId)}", nativeQuery = true)
    List<SearchResponseMarketingActivityReportDto> searchBy(
            @Param("managementAccess") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("kaiEmployeeId") Long kaiEmployeeId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("activityNumber") String activityNumber,
            @Param("activityType") String activityType,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("usercode") String usercode,
            @Param("includeInactive") Character includeInactive,
            @Param("hierId") Long hierId
    );

    @Query(value = "{call sp_search_activity_report_count(:managementAccess,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:activityNumber,:activityType,:activityStatus,:fromDate,:toDate,:page,:size)}", nativeQuery = true)
    Long searchByCount(
            @Param("managementAccess") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("kaiEmployeeId") Long kaiEmployeeId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("activityNumber") String activityNumber,
            @Param("activityType") String activityType,
            @Param("activityStatus") String activityStatus,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("page") Integer page,
            @Param("size") Integer size
    );


    ReportViewResponse findByReportId(Long reportId);

    MarketingActivityReport  findByMarketingActivityProposalActivityProposalId(Long id);
    
    @Query(value="select source_name from SA_MST_ENQUIRY_SOURCE(nolock) where id=:id", nativeQuery=true)
    String getActivityTypeName(@Param("id") Long id);

    //Not Used
    @Query(value = "{call sp_sales_presales_marketing_get_enquiry_by_activity_number(:activityNumber)}", nativeQuery = true)
    List<Map<String,Object>> getEnquiryByActivityNumber(@Param("activityNumber") String activityNumber);

    @Query(value = "{call sp_searchActivityNumberForReport(:activityNumber,:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> searchActivityNumberForReport(@Param("activityNumber") String activityNumber,@Param("dealerId") Long dealerId);

    @Query(value = "{call sp_marketing_activity_report_get_activity_number(:activityNumber,:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getActivityNumberForReport(@Param("activityNumber") String activityNumber,
                                                        @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_marketing_activity_report_get_header_data(:activityProposalId)}", nativeQuery = true)
    Map<String,Object> getHeaderData(@Param("activityProposalId") Long activityProposalId);

    @Query(value = "{call sp_sm_activity_report_getEnquiries(:activityProposalId,:fromDate,:toDate)}", nativeQuery = true)
    List<Map<String,Object>> getEnquiryDetails(@Param("activityProposalId") Long activityProposalId, @Param("fromDate")String fromDate,@Param("toDate")String toDate);

    @Query(value = "{call sp_marketing_activity_report_check_activity_number(:activityNumber)}", nativeQuery = true)
    Map<String,Object> checkActivityNumber(@Param("activityNumber") String activityNumber);

    @Query(value = "select ari.id, ari.file_name fileName from SM_ACTIVITY_REPORT_IMAGE ari inner join SM_ACTIVITY_REPORT sar on sar.report_id = ari.marketing_activity_report_id where sar.marketing_activity_proposal_id = :proposalId", nativeQuery = true)
    List<Map<String, Object>> getReportImagesByReportId(@Param("proposalId") Long proposalId);

}
