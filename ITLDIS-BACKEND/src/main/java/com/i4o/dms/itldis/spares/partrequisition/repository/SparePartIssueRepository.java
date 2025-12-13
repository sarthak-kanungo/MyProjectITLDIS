package com.i4o.dms.itldis.spares.partrequisition.repository;

import com.i4o.dms.itldis.spares.partrequisition.domain.SparePartIssue;
import com.i4o.dms.itldis.spares.partrequisition.dto.IssueSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SparePartIssueRepository extends JpaRepository<SparePartIssue, Long> {

    @Query(value = "{call sp_spares_part_issue_get_requisition_against_issue}", nativeQuery = true)
    List<Map<String, Object>> getRequisitionAgainstIssue();

    @Query(value = "{call sp_spares_part_issue_search_requisition_no_for_part_issue(:searchNo,:issueType,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> searchPartRequisitionNoForPartIssue(@Param("searchNo") String searchNo,
                                                                  @Param("issueType") String issueType,
                                                                  @Param("branchId") Long branchId);

    @Query(value = "{call sp_spares_part_issue_get_part_requisition_details_by_id(:requisitionId)}", nativeQuery = true)
    Map<String, Object> getPartRequisitionDetailsById(@Param("requisitionId") Long requisitionId);

    @Query(value = "{call sp_spares_part_issue_get_part_requisition_item_details_by_id(:requisitionId)}", nativeQuery = true)
    List<Map<String, Object>> getPartRequisitionItemDetailsById(@Param("requisitionId") Long requisitionId);

    @Query(value = "{call sp_spares_part_issue_get_part_requisition_item_details_by_id_from_api(:requisitionId,:apiId)}", nativeQuery = true)
    List<Map<String, Object>> getPartRequisitionItemDetailsByIdFromApi(@Param("requisitionId") Long requisitionId, @Param("apiId")Long apiId);

    @Query(value = "{call sp_spares_part_issue_get_part_issue_to_list(:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getIssueToList(@Param("dealerId") Long dealerId);

    @Query(value = "{call sp_spare_dropdown_category}", nativeQuery = true)
    List<Map<String, Object>> getPartCategoryList();
    
    @Query(value = "{call sp_spares_part_issue_search_part_requisition_no_in_part_issue(:requisitionNo,:userCode)}", nativeQuery = true)
    List<Map<String, Object>> searchPartRequisitionNo(@Param("requisitionNo") String requisitionNo, @Param("userCode")String userCode);

    @Query(value = "{call sp_spares_part_issue_search_job_card_no_in_part_issue(:jobCardNo,:userCode)}", nativeQuery = true)
    List<Map<String, Object>> searchJobCardNo(@Param("jobCardNo") String jobCardNo, @Param("userCode")String userCode);

    @Query(value = "{call sp_spares_getPriceWiseAvailableStock(:itemNo,:branchId,:category)}", nativeQuery = true)
    List<Map<String, Object>> getAvailableStockForPartIssue(@Param("itemNo") String itemNo, @Param("branchId") Long branchId,@Param("category") String category);

    @Query(value = "{call sp_spares_part_issue_search_part_issue(:partIssueNo,:requisitionNo,:jobCardNo,:requisitionPurpose," +
            ":requisitionFromDate,:requisitionToDate,:issueFromDate,:issueToDate,:branchId,:dealerEmpId,:page,:size, :username)}", nativeQuery = true)
    List<IssueSearchResponse> searchPartIssue(@Param("partIssueNo") String partIssueNo,
                                              @Param("requisitionNo") String requisitionNo,
                                              @Param("jobCardNo") String jobCardNo,
                                              @Param("requisitionPurpose") String requisitionPurpose,
                                              @Param("requisitionFromDate") String requisitionFromDate,
                                              @Param("requisitionToDate") String requisitionToDate,
                                              @Param("issueFromDate") String issueFromDate,
                                              @Param("issueToDate") String issueToDate,
                                              @Param("branchId") Long dealerId,
                                              @Param("dealerEmpId") Long dealerEmpId,
                                              @Param("page") Integer page, 
                                              @Param("size") Integer size,
                                              @Param("username") String username);

    @Query(value = "{call sp_spares_part_issue_get_part_issue_details_by_id(:issueId)}", nativeQuery = true)
    Map<String, Object> getPartIssueDetailsById(@Param("issueId") Long requisitionId);

    @Query(value = "{call sp_spares_part_issue_get_part_issue_item_details_by_id(:issueId)}", nativeQuery = true)
    List<Map<String, Object>> getPartIssueItemDetailsById(@Param("issueId") Long requisitionId);

    @Query(value = "{call sp_spares_part_issue_get_issue_type}", nativeQuery = true)
    List<Map<String, Object>> getIssueType();

    @Query(value = "{call sp_spares_part_issue_search_api_no(:apiNo,:userCode)}", nativeQuery = true)
    List<Map<String, Object>> searchApiNo(@Param("apiNo") String apiNo,@Param("userCode")String userCode);

    @Query(value = "{call sp_spares_part_issue_get_available_stock_from_advanced_part_issue(:apiId,:itemNo,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getAvailableStockFromAdvancedPartIssue(@Param("apiId") Long apiId,
                                                                     @Param("itemNo") String itemNo,
                                                                     @Param("branchId") Long branchId);
    @Query(value = "{call sp_spares_part_issue_search_issue_no(:apiNo,:userCode)}", nativeQuery = true)
    List<Map<String, Object>> searchpartIssues(@Param("apiNo") String apiNo,@Param("userCode")String userCode);
    
    @Query(value = "{call sp_spares_part_issue_last_issue_type_by_jobcard(:id,:branchId)}", nativeQuery = true)
    String checkLastIssueAgainstJobcard(@Param("id")Long id, @Param("branchId")Long branchId);
}
