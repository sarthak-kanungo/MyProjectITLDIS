package com.i4o.dms.itldis.warranty.hotlinereport.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.warranty.hotlinereport.domain.WarrantyHotlineReport;
import com.i4o.dms.itldis.warranty.hotlinereport.dto.WarrantyHotlineReportViewDto;
import com.i4o.dms.itldis.warranty.hotlinereport.dto.WarrantyHotlineSearchResponceDto;

/**
 * @author suraj.gaur
 */
@Transactional
public interface WarrantyHotlineReportRepo extends JpaRepository<WarrantyHotlineReport, Long> {
	
	@Query(value = "{call sp_warranty_hotline_report_no_dropdown(:hotlineNo)}", nativeQuery = true)
    List<Map<String, Object>> searchHotlineNo(@Param("hotlineNo") String hotlineNo);
	
	@Query(value = "select distinct id,	DEPOT value	from SA_MST_FORM_DEPOT_ADDRESS", nativeQuery = true)
	List<Map<String, Object>> dealerDepoList();
	
	@Query(value = "select id, FAILURE_CODE code,FAILURE_DESC value from WA_HTLR_CONDITION_FAILURE_MST where ACTIVE_FLAG = 1", nativeQuery = true)
	List<Map<String, Object>> getFailureCode();
	
	@Query(value = "select id, status value from WA_HTLR_STATUS where active_status = 1", nativeQuery = true)
	List<Map<String, Object>> getHotlineStatus();
	
	@Query(value = "select id, PLANT_NAME code, PLANT_DESC value from WA_HTLR_PLANT_MST WHERE ACTIVE_FLAG = 1", nativeQuery = true)
	List<Map<String, Object>> getHotlinePlant();
	
	@Query(value = "select id, department_code code, department_name value from ADM_HO_MST_DEPARTMENT where active_status = 'Y'", nativeQuery = true)
	List<Map<String, Object>> getHoDepartment();
	
	@Query(value = "{call SP_GET_HOTLINE_CHASSIS_NO(:chassisNo)}", nativeQuery = true)
	List<Map<String, Object>> getChassisNo(@Param("chassisNo") String chassisNo);
	
	@Query(value = "{call SP_GET_DEPT_INCHARGE(:deptId)}", nativeQuery = true)
	List<Map<String, Object>> getDepartmentIncharge(@Param("deptId") Long deptId);
	
	@Query(value = "{call sp_warranty_hotline_report_search(:hotlineNo,:status,:fromDate,:toDate,:page,:size)}", nativeQuery = true)
	List<WarrantyHotlineSearchResponceDto> searchHotlineReport(
			@Param("hotlineNo") String hotlineNo,
			@Param("status") String status,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("page") Long page,
			@Param("size") Long size
			);
	
	WarrantyHotlineReportViewDto findByHtlrNo(String hotlineNo);
	
	@Query(value = "select id, failure_type value from WA_HTLR_FAILURE_MST where active_status = 'Y'", nativeQuery = true)
	List<Map<String, Object>> getFailureType();
}
