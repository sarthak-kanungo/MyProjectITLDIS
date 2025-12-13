package com.i4o.dms.itldis.crm.crmmodule.report.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain.SurveyHeader;
import com.i4o.dms.itldis.crm.crmmodule.report.dto.CustomerSatisfactionScoreDto;
import com.i4o.dms.itldis.crm.crmmodule.report.dto.MonthlyFailureCodeSummaryReportDto;
import com.i4o.dms.itldis.crm.crmmodule.report.dto.TollFreeReportDto;

@Repository
public interface CrmReportRepo  extends JpaRepository<SurveyHeader,Long> {

	@Query(value="{call SP_Customer_Satisfaction_Score_Report (:dealerId, :product, :series, :model, "
			+ ":subModel, :variant, :reportType, :dueFromDate, :dueToDate, :page, :size)}", nativeQuery=true)
	List<CustomerSatisfactionScoreDto> getSatisfactionScoreReport( 
			@Param("dealerId") Long dealerId,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("reportType") String reportType,
			@Param("dueFromDate") String dueFromDate,
			@Param("dueToDate") String dueToDate,
			@Param("page") Integer Page,
			@Param("size") Integer Size);
	
	@Query(value="{call SP_Monthly_Failure_Code_Summary_Report (:username, :product, :series, :model, :subModel, :variant, :complaintCode, :FromYear, :ToYear, :page, :size)}" ,nativeQuery=true)
	List<MonthlyFailureCodeSummaryReportDto> getMonthlyFailureCodeSummaryReport(@Param("username") String username, 
			@Param("product") String Product,
			@Param("series") String Series,
			@Param("model") String Model,
			@Param("subModel") String SubModel,
			@Param("variant") String Variant,
			@Param("complaintCode") String complaintCode,
			@Param("FromYear") String FromYear,
			@Param("ToYear") String ToYear,
			@Param("page") Integer Page,
			@Param("size") Integer Size);
	
	@Query(value="{call SP_Toll_Free_Report (:fromDate, :toDate, :mobileNo, :dealerId, :orgHierId, :usercode, :page, :size)}" ,nativeQuery=true)
	List<TollFreeReportDto> getTollFreeReport( 
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("mobileNo") String mobileNo,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("usercode") String usercode,
			@Param("page") Integer Page,
			@Param("size") Integer Size);
	
	@Query(value="{call SP_Monthly_Failure_Code_Summary_Report (:username, :product, :series, :model, :subModel, :variant, :complaintCode, :FromYear, :ToYear, :page, :size)}" ,nativeQuery=true)
	List<MonthlyFailureCodeSummaryReportDto> getMfcsExcelReport(@Param("username") String username, 
			@Param("product") String Product,
			@Param("series") String Series,
			@Param("model") String Model,
			@Param("subModel") String SubModel,
			@Param("variant") String Variant,
			@Param("complaintCode") String complaintCode,
			@Param("FromYear") String FromYear,
			@Param("ToYear") String ToYear,
			@Param("page") Integer Page,
			@Param("size") Integer Size);
	
	@Query(value="{call SP_Customer_Satisfaction_Score_Report (:dealerId, :product, :series, :model, "
			+ ":subModel, :variant, :reportType, :dueFromDate, :dueToDate, :page, :size)}", nativeQuery=true)
	List<Map<String, Object>> getSatisfactionScoreExcelReport( 
			@Param("dealerId") Long dealerId,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("reportType") String reportType,
			@Param("dueFromDate") String dueFromDate,
			@Param("dueToDate") String dueToDate,
			@Param("page") Integer Page,
			@Param("size") Integer Size);
}
