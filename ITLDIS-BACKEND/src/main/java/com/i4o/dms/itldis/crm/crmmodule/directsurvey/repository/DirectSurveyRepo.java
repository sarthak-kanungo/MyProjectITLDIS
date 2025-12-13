package com.i4o.dms.itldis.crm.crmmodule.directsurvey.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain.SurveyHeader;

@Transactional
public interface DirectSurveyRepo extends JpaRepository<SurveyHeader, Long> {
	
	@Query(value = "{call sp_QA_Survey_type_List}",  nativeQuery = true)
	List<Map<String,Object>> getSurveyType();
	
	@Query(value = "{call sp_QA_Get_Survey_Status}" ,nativeQuery = true)
	List<Map<String,Object>> getSurveyStatus();
	
	@Query(value = "{call sp_QA_Get_Survey_Satisfaction}" , nativeQuery = true)
	List<Map<String, Object>> getSatisfactionLevel();
	
	
	@Query(value = "{call sp_QA_Survey_History_New(:surveyType,:vinId,:customerId)}",  nativeQuery = true)
	List<Map<String,Object>> getDirectSurveyDetails(@Param ("surveyType") String surveyType, @Param ("vinId") Long vinId, @Param ("customerId") Long customerId);
	
	@Query( value = "{call sp_QA_Survey_Question(:surveyMstId,:surveyTypeId,:vinId)}" , nativeQuery = true)
	List <Map<String, Object>>getSurveyQuestion(@Param("surveyMstId") Long surveyMstId,@Param("surveyTypeId") Long surveyTypeId, @Param("vinId") Long vinId);
	
	@Query( value = "{call sp_QA_Survey_Answer(:questionId)}" , nativeQuery = true)
	List <Map<String, Object>>getSurveyAnswer(@Param ("questionId") Long questionId);
	
	@Query( value = "{call sp_QA_Servey_Sub_Answer(:ansId)}" , nativeQuery = true)
	List <Map<String, Object>>getSubAnswer(@Param("ansId")  Long ansId);
	
	@Query( value = "{call sp_villageTehsilDistrictSearchForCustomer(:village,:userName,:state,:district)}" , nativeQuery = true)
	List <Map<String, Object>>getVillageAuto(@Param("village")  String village, @Param("userName")  String userName,  @Param("state")  String state,  @Param("district")  String district);
	
	@Query( value = "{call sp_GetMachineAge(:vinId)}" , nativeQuery = true)
	List <Map<String, Object>>getAgeOfMachine(@Param("vinId")  Long vinId);
	
	@Query( value = "{call sp_GetMachineReadingHours(:vinId)}" , nativeQuery = true)
	List <Map<String, Object>>getMeterHours(@Param("vinId")  Long vinId);
	
	@Query( value = "{call sp_getCustomerMachine_Survey(:customerId, :dealerId, :chassisNo)}" , nativeQuery = true)
	List <Map<String, Object>>getSurveyMachineDetails(@Param("customerId")  Long customerId, @Param("dealerId") Long dealerId, @Param("chassisNo") String chassisNo);
	
	SurveyHeader findBySurveyNo(String SurveyNo);
	
	@Query( value = "{call sp_QA_Survey_DoneBy(:createdBy)}" , nativeQuery = true)
	String getSurveyDoneUser(@Param("createdBy") Long createdBy);
	
	@Modifying
	@Query(value = "update QA_SURVEY_REMINDER set Survey_Status='Completed' where SURVEY_REMD_ID=:surveyReminderId", nativeQuery = true)
	Integer updateSurveyStatus(@Param("surveyReminderId") Long  surveyReminderId);
	
	@Query(value = "{call sp_QA_Survey_CallAttemptHistory(:reminderId)}",nativeQuery = true)
	List<Map<String, Object>> getCallHistory(@Param("reminderId") String reminderId);
	
	@Modifying
	@Query(value = "{call sp_QA_Update_Survey_Satisfaction_Level(:surveyHdrId)}",nativeQuery = true)
	void updateSurveySatisfactionLevel(@Param("surveyHdrId") Long surveyHdrId);
	
	@Modifying
	@Query(value = "update QA_SURVER_CALL_RESPONSE set RECORDING_FILE_NAME=:fileName where SURVEY_RESPONSE_ID=:callAttemptId", nativeQuery = true)
	Integer updateCallrecording(@Param("callAttemptId") Long  callAttemptId, @Param("fileName") String fileName);



}