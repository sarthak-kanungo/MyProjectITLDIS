package com.i4o.dms.kubota.spares.partrequisition.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.connection.ConnectionConfiguration;
import com.i4o.dms.kubota.spares.partrequisition.domain.SparePartReturn;
import com.i4o.dms.kubota.spares.partrequisition.dto.ReturnSearchResponse;

public interface SparePartReturnRepository extends JpaRepository<SparePartReturn, Long>,ConnectionConfiguration {

    @Query(value = "{call sp_spares_part_return_get_reasons_for_return}", nativeQuery = true)
    List<Map<String, Object>> getReasonsForReturn();

    @Query(value = "{call sp_spares_part_return_search_by_requisition_mobile_emp_name(:searchString,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> searchByRequisitionNoMobileNoEmpName(@Param("searchString") String searchString, @Param("branchId") Long branchId);

    @Query(value = "{call sp_spares_part_return_search_by_job_card_no_for_part_return(:jobCardNo,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> searchByJobCardNoForPartReturn(@Param("jobCardNo") String jobCardNo, @Param("branchId") Long branchId);

    @Query(value = "{call sp_spares_part_return_get_part_issue_details_for_part_return_by_req_id(:requisitionId,:branchId,:usercode)}", nativeQuery = true)
    Map<String, Object> getPartIssueDetailsForPartReturnByRequisitionId(@Param("requisitionId") Long requisitionId, @Param("branchId") Long branchId, @Param("usercode")String usercode);

    @Query(value = "{call sp_spares_part_return_get_part_issue_item_details_for_part_return_by_req_id(:requisitionId,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getPartIssueItemDetailsForPartReturnByRequisitionId(@Param("requisitionId") Long requisitionId, @Param("branchId") Long branchId);

    @Query(value = "{call sp_spares_part_return_search_return_no(:returnNo,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> searchByReturnNo(@Param("returnNo") String returnNo, @Param("branchId") Long branchId);

    @Query(value = "{call sp_spares_part_return_search_requisition_no_in_part_return(:requisitionNo,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> searchByRequisitionNo(@Param("requisitionNo") String requisitionNo,
                                                    @Param("branchId") Long branchId);

    @Query(value = "{call sp_spares_part_return_search_job_card_no_in_part_return(:jobCardNo,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> searchByJobCardNo(@Param("jobCardNo") String jobCardNo, @Param("branchId") Long branchId);

    @Query(value = "{call sp_spares_part_return_search_part_return(:returnNo,:requisitionNo,:jobCardNo,:requisitionPurpose,:reasonForReturn," +
            ":requisitionFromDate,:requisitionToDate,:jobCardFromDate,:jobCardToDate,:dealerId,:page,:size,:usercode)}", nativeQuery = true)
    List<ReturnSearchResponse> searchPartReturn(@Param("returnNo") String returnNo, @Param("requisitionNo") String requisitionNo,
                                               @Param("jobCardNo") String jobCardNo, @Param("requisitionPurpose") String requisitionPurpose,
                                               @Param("reasonForReturn") String reasonForReturn,
                                               @Param("requisitionFromDate") String requisitionFromDate,
                                               @Param("requisitionToDate") String requisitionToDate,
                                               @Param("jobCardFromDate") String jobCardFromDate,
                                               @Param("jobCardToDate") String jobCardToDate,
                                               @Param("dealerId") Long dealerId, @Param("page") Integer page, @Param("size") Integer size, @Param("usercode")String usercode);


    @Query(value = "{call sp_spares_part_return_get_part_return_details_by_id(:returnId)}",nativeQuery = true)
    Map<String, Object> getPartReturnDetailsById(@Param("returnId") Long returnId);

    @Query(value = "{call sp_spares_part_return_get_part_return_item_details_by_id(:returnId)}",nativeQuery = true)
    List<Map<String, Object>> getPartIssueItemDetailsById(@Param("returnId") Long returnId);
}
