package com.i4o.dms.itldis.crm.crmmodule.surveysummaryreport.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.crm.crmmodule.surveysummaryreport.domain.SurveySummaryReport;
import com.i4o.dms.itldis.crm.crmmodule.surveysummaryreport.response.QAReportResponse;
import com.i4o.dms.itldis.crm.crmmodule.surveysummaryreport.response.SurveyDetailsExcelReportResponse;
import com.i4o.dms.itldis.crm.crmmodule.surveysummaryreport.response.SurveySummaryReportDissatisfiedResponse;
import com.i4o.dms.itldis.crm.crmmodule.surveysummaryreport.response.SurveySummaryReportResponse;
import com.i4o.dms.itldis.crm.crmmodule.surveysummaryreport.response.SurveySummaryReportWithQuationariesResponse;


public interface SurveySummaryReportRepo extends JpaRepository<SurveySummaryReport, Long> {

	@Query(value = "{call sp_QA_Survey_type_List()}", nativeQuery = true)
	List<Map<String, Object>> getSurveyName();
	
	@Query(value = "{call SP_QA_Survey_Name_List()}", nativeQuery = true)
	List<Map<String, Object>> getSurveyNameQAReport();
	
	@Query(value = "{call sp_QA_MST_Questions()}", nativeQuery = true)
	List<Map<String, Object>> getQuestionList();

	@Query(value = "{call sp_QA_Search_Survey_Summary_Report_New(:managementAccess,:dealerId,:hoUserId,:dealerEmployeeId,:surveyName,"
			+ ":SurveyStatus,:AsOnDate,:FromDate,:ToDate,:hierId,:CustSatisfaction,"
			+ ":model,:subModel,:surveyNo,:questionId,:product,:variant,:series,:reportType,:page,:size,:userCode,:chasisNo,:flag,:surveyType, :FromDcDate, :ToDcDate)}", nativeQuery = true)
	List<SurveySummaryReportResponse> searchSurveySummaryReport(@Param("managementAccess") Boolean managementAccess,
			@Param("dealerId") Long dealerId, @Param("hoUserId") Long hoUserId,
			@Param("dealerEmployeeId") Long dealerEmployeeId, @Param("surveyName") String surveyName,
			@Param("SurveyStatus") String SurveyStatus, @Param("AsOnDate") Character AsOnDate,
			@Param("FromDate") String FromDate, @Param("ToDate") String ToDate, @Param("hierId") Long hierId,
			@Param("CustSatisfaction") String CustSatisfaction, @Param("model") String model,
			@Param("subModel") String subModel,@Param("surveyNo") String surveyNo, @Param("questionId") String questionId,
			@Param("product") String product, @Param("variant") String variant,@Param("series") String series, @Param("reportType") String reportType,
			@Param("page") Integer page, @Param("size") Integer size,
			@Param("userCode") String userCode, @Param("chasisNo") String chasisNo, @Param("flag") String flag, @Param("surveyType")String surveyType,
			@Param("FromDcDate") String FromDcDate, @Param("ToDcDate") String ToDcDate);
	@Query(value = "{call sp_QA_Search_Survey_Summary_Report_New(:managementAccess,:dealerId,:hoUserId,:dealerEmployeeId,:surveyName,"
			+ ":SurveyStatus,:AsOnDate,:FromDate,:ToDate,:hierId,:CustSatisfaction,"
			+ ":model,:subModel,:surveyNo,:questionId,:product,:variant,:series,:reportType,:page,:size,:userCode,:chasisNo,:flag,:surveyType, :FromDcDate, :ToDcDate)}", nativeQuery = true)
	List<SurveySummaryReportWithQuationariesResponse> searchSurveySummaryReportWith(@Param("managementAccess") Boolean managementAccess,
			@Param("dealerId") Long dealerId, @Param("hoUserId") Long hoUserId,
			@Param("dealerEmployeeId") Long dealerEmployeeId, @Param("surveyName") String surveyName,
			@Param("SurveyStatus") String SurveyStatus, @Param("AsOnDate") Character AsOnDate,
			@Param("FromDate") String FromDate, @Param("ToDate") String ToDate, @Param("hierId") Long hierId,
			@Param("CustSatisfaction") String CustSatisfaction, @Param("model") String model,
			@Param("subModel") String subModel,@Param("surveyNo") String surveyNo, @Param("questionId") String questionId,
			@Param("product") String product, @Param("variant") String variant,@Param("series") String series, @Param("reportType") String reportType,
			@Param("page") Integer page, @Param("size") Integer size,
			@Param("userCode") String userCode, @Param("chasisNo") String chasisNo, @Param("flag") String flag, @Param("surveyType")String surveyType,
			@Param("FromDcDate") String FromDcDate, @Param("ToDcDate") String ToDcDate);
	@Query(value = "{call sp_QA_Search_Survey_Summary_Report_New(:managementAccess,:dealerId,:hoUserId,:dealerEmployeeId,:surveyName,"
			+ ":SurveyStatus,:AsOnDate,:FromDate,:ToDate,:hierId,:CustSatisfaction,"
			+ ":model,:subModel,:surveyNo,:questionId,:product,:variant,:series,:reportType,:page,:size,:userCode,:chasisNo,:flag,:surveyType, :FromDcDate, :ToDcDate)}", nativeQuery = true)
	List<SurveySummaryReportDissatisfiedResponse> searchSurveySummaryReportDisatisfied(@Param("managementAccess") Boolean managementAccess,
			@Param("dealerId") Long dealerId, @Param("hoUserId") Long hoUserId,
			@Param("dealerEmployeeId") Long dealerEmployeeId, @Param("surveyName") String surveyName,
			@Param("SurveyStatus") String SurveyStatus, @Param("AsOnDate") Character AsOnDate,
			@Param("FromDate") String FromDate, @Param("ToDate") String ToDate, @Param("hierId") Long hierId,
			@Param("CustSatisfaction") String CustSatisfaction, @Param("model") String model,
			@Param("subModel") String subModel,@Param("surveyNo") String surveyNo, @Param("questionId") String questionId,
			@Param("product") String product, @Param("variant") String variant,@Param("series") String series, @Param("reportType") String reportType,
			@Param("page") Integer page, @Param("size") Integer size,
			@Param("userCode") String userCode, @Param("chasisNo") String chasisNo, @Param("flag") String flag, @Param("surveyType")String surveyType,
			@Param("FromDcDate") String FromDcDate, @Param("ToDcDate") String ToDcDate);

	@Query(value = "{call sp_QA_Get_Survey_Date()}", nativeQuery = true)
	List<Map<String, Object>> getSurveyDate();

	@Query(value = "{call sp_QA_Get_Survey_Satisfaction()}", nativeQuery = true)
	List<Map<String, Object>> getSurveySatisfaction();

	@Query(value = "{call sp_QA_Get_Survey_Status()}", nativeQuery = true)
	List<Map<String, Object>> getSurveyStatus();
	
	   @Query(value = "{call SP_GetZones(:UserId)}", nativeQuery = true)
	    List<Map<String, Object>> getZone(@Param("UserId") Long UserId);
	   
	   @Query(value = "{call SP_GetRegions(:UserId,:ZoneId)}", nativeQuery = true)
	    List<Map<String, Object>> getRegions(@Param("UserId") Long UserId,
	    		@Param("ZoneId") Long ZoneId);
	   
	   @Query(value = "{call SP_GetAreas(:UserId,:ZoneId,:ReginoId)}", nativeQuery = true)
	    List<Map<String, Object>> getAreas(@Param("UserId") Long UserId,
	    		@Param("ZoneId") Long ZoneId,@Param("ReginoId") Long ReginoId);
	   
	   @Query(value = "{call SP_GetTerritory(:UserId,:ZoneId,:ReginoId,:AreaId)}", nativeQuery = true)
	    List<Map<String, Object>> getTerritory(@Param("UserId") Long UserId,
	    		@Param("ZoneId") Long ZoneId,@Param("ReginoId") Long ReginoId,
	    		@Param("AreaId") Long AreaId);
	   
	   @Query(value = "{call sp_QA_Survey_Summary_ChassisNo_Auto_Search(:userCode,:hierId,:chassisNo)}", nativeQuery = true)
	    List<Map<String, Object>> getChassisNo(@Param("userCode") String userCode,@Param("hierId") Long hierId,@Param("chassisNo") String chassisNo);
	   
	   @Query(value = "{call sp_QA_Survey_Number_Auto_Search(:userCode,:hierId,:surveyNo)}", nativeQuery = true)
	    List<Map<String, Object>> getSurveyNo(@Param("userCode") String userCode,@Param("hierId") Long hierId,@Param("surveyNo") String surveyNo);
	   
	   
		@Query(value = "{call sp_QA_Survey_Ques_Ans_Report(:managementAccess,:dealerId,:hoUserId,:dealerEmployeeId,:surveyType,:hierId,:fromDate,"
				+ ":toDate,:product,:series,:model,:subModel,:variant,:questionId,:page,:size,:userCode,:flag)}", nativeQuery = true)
		List<QAReportResponse> surveySummaryQAReport(@Param("managementAccess") Boolean managementAccess,
				@Param("dealerId") Long dealerId, @Param("hoUserId") Long hoUserId, @Param("dealerEmployeeId") Long dealerEmployeeId,
				@Param("surveyType") String surveyType, @Param("hierId") Long hierId, @Param("fromDate") String fromDate, @Param("toDate") String toDate,
				@Param("product") String product, @Param("series") String series, @Param("model") String model, @Param("subModel") String subModel,
				@Param("variant") String variant,  @Param("questionId") String questionId,@Param("page") Integer page, @Param("size") Integer size,
				@Param("userCode") String userCode, @Param("flag") String flag);


}