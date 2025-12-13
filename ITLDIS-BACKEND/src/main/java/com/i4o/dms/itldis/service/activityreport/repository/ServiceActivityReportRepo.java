package com.i4o.dms.itldis.service.activityreport.repository;

import com.i4o.dms.itldis.service.activityreport.domain.ServiceActivityReport;
import com.i4o.dms.itldis.service.activityreport.dto.ActivitySearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ServiceActivityReportRepo extends JpaRepository<ServiceActivityReport,Long> {

    @Query(value="{call sp_service_activity_report_autocomplete_activity_number(:activityNumber,:dealerId)}",nativeQuery = true)
    List<Map<String,Object>> findByActivityNumberContaining(@Param("activityNumber") String activityNumber,
                                                            @Param("dealerId") Long dealerId);

    @Query(value="{call sp_service_activity_report_get_header_details(:dealerId,:activityProposalId)}",nativeQuery = true)
    Map<String,Object>getHeaderDetails( @Param("dealerId") Long dealerId,
                                              @Param("activityProposalId") Long activityProposalId);

    @Query(value = "{call sp_service_activity_report_get_job_card_details(:dealerId, :activityProposalId, :fromDate, :toDate)}",nativeQuery = true)
    List<Map<String,Object>>getJobCardDetails( @Param("dealerId") Long dealerId,
                                               @Param("activityProposalId") Long activityProposalId, 
                                               @Param("fromDate")String fromDate, 
                                               @Param("toDate")String toDate);

    @Query(value="{call sp_service_activity_report_get_machine_details(:dealerId,:activityProposalId, :fromDate, :toDate)}",nativeQuery = true)
    List<Map<String,Object>>getMachineDetails( @Param("dealerId") Long dealerId,
                                               @Param("activityProposalId") Long activityProposalId, 
                                               @Param("fromDate")String fromDate, 
                                               @Param("toDate")String toDate);

    @Query(value="{call sp_service_activity_report_get_service_details(:dealerId,:activityProposalId, :fromDate, :toDate)}",nativeQuery = true)
    List<Map<String,Object>>getServiceDetails( @Param("dealerId") Long dealerId,
                                               @Param("activityProposalId") Long activityProposalId, 
                                               @Param("fromDate")String fromDate, 
                                               @Param("toDate")String toDate);

    @Query(value = "{call sp_service_activity_report_search(:fromDate,:toDate,:activityNumber,:activityStatus,:activityType,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size,:userCode,:hierId)}",nativeQuery = true)
    List<ActivitySearchResponse> searchActivityReport(@Param("fromDate") String fromDate,
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
                                                      @Param("hierId") Long hierId);

  

    @Query(value="{call sp_service_activity_report_view_header_data(:activityReportId)}",nativeQuery = true)
    Map<String,Object>getHeaderDataForView( @Param("activityReportId") Long activityReportId);

    @Query(value="{call sp_service_activity_report_view_machine_details(:activityReportId)}",nativeQuery = true)
    List<Map<String,Object>>getMachineDataForView( @Param("activityReportId") Long activityReportId);

    @Query(value="{call sp_service_activity_report_view_service_details(:activityReportId)}",nativeQuery = true)
    List<Map<String,Object>>getServiceDataForView( @Param("activityReportId") Long activityReportId);

    @Query(value="{call sp_service_activity_report_view_job_card_details(:activityReportId)}",nativeQuery = true)
    List<Map<String,Object>>getJobCardDataForView( @Param("activityReportId") Long activityReportId);

    @Query(value="{call sp_service_activity_report_view_photos(:activityReportId)}",nativeQuery = true)
    List<Map<String,Object>>getPhotoForView( @Param("activityReportId") Long activityReportId);

    @Query(value="{call sp_service_activity_report_get_activity_number_for_search(:dealerId,:activityNumber)}",nativeQuery = true)
    List<Map<String,Object>>getActivityNumberForSearch( @Param("dealerId") Long dealerId,
                                                        @Param("activityNumber") String activityNumber);
    
    @Query(value = "select arp.id, arp.file_name fileName from SV_ACTIVITY_REPORT_PHOTOS arp inner join SV_ACTIVITY_REPORT sar on sar.id = arp.service_activity_report_id where service_activity_proposal_id = :proposalId", nativeQuery = true)
    List<Map<String, Object>> getReportImagesByReportId(@Param("proposalId") Long proposalId);

}

