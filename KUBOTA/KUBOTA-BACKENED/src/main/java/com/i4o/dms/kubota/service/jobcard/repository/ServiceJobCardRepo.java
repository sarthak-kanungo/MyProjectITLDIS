package com.i4o.dms.kubota.service.jobcard.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobCard;
import com.i4o.dms.kubota.service.jobcard.dto.JobCardViewDto;
import com.i4o.dms.kubota.service.jobcard.dto.JobcardDetailedExcelResponseDto;
import com.i4o.dms.kubota.service.jobcard.dto.JobcardReopenApprovalSearchResponseDto;
import com.i4o.dms.kubota.service.jobcard.dto.JobcardSearchResponseDto;
import com.i4o.dms.kubota.service.jobcard.dto.MachineServiceHistoryDto;

@Transactional
public interface ServiceJobCardRepo extends JpaRepository<ServiceJobCard, Long> {
	@Query(value = "{call sp_service_jobcard_autocomplete_chassis_number(:chassisNo,:preSalesFlag,:branchID,:dealerID,:isFor)}", nativeQuery = true)
	List<Map<String, Object>> autoCompleteChassisNumberInJobCard(@Param("chassisNo") String chassisNo,
			@Param("preSalesFlag") Boolean preSalesFlag, @Param("branchID") Long branchID,
			@Param("dealerID") Long dealerID,
			@Param("isFor")String isFor);

	@Query(value = "{call sp_service_jobcard_getChassisDetailsByChassisNo(:chassisNo,:preSalesFlag,:branchID,:dealerID)}", nativeQuery = true)
	Map<String, Object> getChassisDetailsByChassisNoInJobCard(@Param("chassisNo") String chassisNo,
			@Param("preSalesFlag") Boolean preSalesFlag, @Param("branchID") Long branchID,
			@Param("dealerID") Long dealerID);

	@Modifying
	@Query(value = "{call SP_JOBCARD_CANCEL(:JobId,:Remark,:loginId)}", nativeQuery = true)
	Integer cancelJobCard(@Param("JobId")Long jobId, @Param("Remark")String remark, @Param("loginId")Long loginId);
	
	@Query(value = "{call sp_service_jobcard_dropdown_employee_name_by_dealer_id(:dealerId)}", nativeQuery = true)
	List<Map<String, Object>> employeeNameByDealerId(@Param("dealerId") Long dealerId);

	@Query(value = "{call sp_service_jobcard_dropdown_service_category(:preSalesFlag,:retrofitmentFlag)}", nativeQuery = true)
	List<Map<String, Object>> dropDownServiceCategory(@Param("preSalesFlag") Boolean preSalesFlag,
			@Param("retrofitmentFlag") Boolean retrofitmentFlag);

	@Query(value = "{call sp_service_jobcard_dropdown_place_of_service(:preSalesFlag)}", nativeQuery = true)
	List<Map<String, Object>> dropDownPlaceOfService(@Param("preSalesFlag") Boolean preSalesFlag);
	
	@Query(value = "{call sp_service_jobcard_search(:management,:kaiUserId,:branchId,:dealerEmployeeId,:JcNo,:chassisNo,:engineNo,:csbNo,:bookingNo,"
			+ ":jsFromDate,:jsToDate,:customerName,:model,:registrationNo,:mobileNo,:serviceCategory,:placeOfService,:activityType,:activityNo,:page,"
			+ ":size,:userCode,:dealerId,:fromMachineInDate,:toMachineInDate,:product,:series,:subModel,:varient,:zone,:region,:area,:terriory,:includeInactive,:OrgHierId,:status,:isFor,:partNo)}", nativeQuery = true)
	List<JobcardSearchResponseDto> jobCardSearch(@Param("management") Boolean management,
			@Param("kaiUserId") Long kaiUserId, @Param("branchId") Long branchId,
			@Param("dealerEmployeeId") Long dealerEmployeeId, @Param("JcNo") String jcNo,
			@Param("chassisNo") String chassisNo, @Param("engineNo") String engineNo, @Param("csbNo") String csbNo,
			@Param("bookingNo") String bookingNo, @Param("jsFromDate") String jsFromDate,
			@Param("jsToDate") String jsToDate, @Param("customerName") String customerName,
			@Param("model") String model, @Param("registrationNo") String registrationNo,
			@Param("mobileNo") String mobileNo, @Param("serviceCategory") String serviceCategory,
			@Param("placeOfService") String placeOfService, @Param("activityType") String activityType,
			@Param("activityNo") String activityNo, @Param("page") Integer page, @Param("size") Integer size,@Param("userCode") String userCode, @Param("dealerId") Long dealerId,
			@Param("fromMachineInDate") String fromMachineInDate,@Param("toMachineInDate") String toMachineInDate,@Param("product") String product,
			@Param("series") String series,@Param("subModel") String subModel,@Param("varient") String varient,@Param("zone") Long zone,@Param("region") Long region,
			@Param("area") Long area,@Param("terriory") Long terriory,@Param("includeInactive") Character includeInactive,@Param("OrgHierId") Long OrgHierId, @Param("status")String status, @Param("isFor") String isFor, @Param("partNo")String partNo);
	
	
	@Query(value = "{call sp_service_jobcard_search(:management,:kaiUserId,:branchId,:dealerEmployeeId,:JcNo,:chassisNo,:engineNo,:csbNo,:bookingNo,:jsFromDate,:jsToDate,:customerName,:model,:registrationNo,:mobileNo,:serviceCategory,:placeOfService,:activityType,:activityNo,:page,:size,:userCode,:dealerId,:fromMachineInDate,:toMachineInDate,:product,:series,:subModel,:varient,:zone,:region,:area,:terriory,:includeInactive,:OrgHierId,:status,:isFor,:partNo)}", nativeQuery = true)
	List<JobcardDetailedExcelResponseDto> jobcardDetailedExcel(@Param("management") Boolean management,
			@Param("kaiUserId") Long kaiUserId, @Param("branchId") Long branchId,
			@Param("dealerEmployeeId") Long dealerEmployeeId, @Param("JcNo") String jcNo,
			@Param("chassisNo") String chassisNo, @Param("engineNo") String engineNo, @Param("csbNo") String csbNo,
			@Param("bookingNo") String bookingNo, @Param("jsFromDate") String jsFromDate,
			@Param("jsToDate") String jsToDate, @Param("customerName") String customerName,
			@Param("model") String model, @Param("registrationNo") String registrationNo,
			@Param("mobileNo") String mobileNo, @Param("serviceCategory") String serviceCategory,
			@Param("placeOfService") String placeOfService, @Param("activityType") String activityType,
			@Param("activityNo") String activityNo, @Param("page") Integer page, @Param("size") Integer size,@Param("userCode") String userCode, @Param("dealerId") Long dealerId,
			@Param("fromMachineInDate") String fromMachineInDate,@Param("toMachineInDate") String toMachineInDate,@Param("product") String product,
			@Param("series") String series,@Param("subModel") String subModel,@Param("varient") String varient,@Param("zone") Long zone,@Param("region") Long region,
			@Param("area") Long area,@Param("terriory") Long terriory,@Param("includeInactive") Character includeInactive,@Param("OrgHierId") Long OrgHierId, @Param("status")String status, @Param("isFor") String isFor, @Param("partNo")String partNo);
	
	@Query(value = "{call sp_service_jobcard_reopen_approval_search(:page,:size,:userCode,:orgHierId)}", nativeQuery = true)
	List<JobcardReopenApprovalSearchResponseDto> jobCardReopenApprovalSearch(@Param("page") Integer page, @Param("size") Integer size,@Param("userCode") String userCode,@Param("orgHierId") Long orgHierId);
	
	JobCardViewDto findByJobCardNo(String jobCardNo);

	@Query(value = "{call sp_service_jobcard_autocomplete_part_number(:partNumber,:dealerId)}", nativeQuery = true)
	List<Map<String, Object>> autoCompletePartNumber(@Param("partNumber") String partNumber, @Param("dealerId")Long dealerId);

	@Query(value = "{call sp_service_jobcard_partDetailsByPartNumber(:partNumber)}", nativeQuery = true)
	Map<String, Object> getPartDetailsByPartNumber(@Param("partNumber") String partNumber);

	@Query(value = "{call sp_service_jobcard_dropdown_mechanic(:dealerEmployeeId)}", nativeQuery = true)
	List<Map<String, Object>> getDropDownMechanicName(@Param("dealerEmployeeId") Long dealerEmployeeId);

	@Query(value = "{call sp_service_jobcard_dropdown_service_representative(:dealerId,:dealerEmployeeId)}", nativeQuery = true)
	List<Map<String, Object>> getDropDownServiceRepresentative(@Param("dealerId") Long dealerId, @Param("dealerEmployeeId") Long dealerEmployeeId);

	@Query(value = "{call sp_service_dropdown_category(:preSalesFlag)}", nativeQuery = true)
	List<Map<String, Object>> getDropDownCategory(@Param("preSalesFlag") Boolean preSalesFlag);

	@Query(value = "{call sp_service_jobcard_search_chassisNo()}", nativeQuery = true)
	List<Map<String, Object>> searchChassisNo();

	@Query(value = "{call sp_service_jobcard_get_total_hour(:currentHour,:machineInventoryId,:meterChangeHour)}", nativeQuery = true)
	Map<String, Object> getTotalHour(@Param("currentHour") Integer currentHour,
			@Param("machineInventoryId") Long machineInventoryId, @Param("meterChangeHour") Double meterChangeHour);

	@Query(value = "{call sp_service_jobcard_get_service_type_by_hour(:machineInventoryId,:totalHour,:serviceCategory,:jobid)}", nativeQuery = true)
	List<Map<String, Object>> getServiceTypeByHour(@Param("machineInventoryId") Long machineInventoryId,
			@Param("totalHour") Float totalHour, @Param("serviceCategory") String serviceCategory, @Param("jobid")Long jobid);

	@Query(value = "{call sp_service_jobcard_get_booking_details_by_booking_no(:bookingId,:branchId)}", nativeQuery = true)
	Map<String, Object> getBookingDetailsByBookingNo(@Param("bookingId") Long bookingId,
			@Param("branchId") Long branchId);

	@Query(value = "{call sp_service_service_booking_check_chassis_no(:machineInventoryId,:jobCardFlag,:branchID)}", nativeQuery = true)
	Map<String, Object> checkByChassisNo(@Param("machineInventoryId") String chassisNo,
			@Param("jobCardFlag") Boolean jobCardFlag, @Param("branchID") Long branchID);

	@Query(value = "{call sp_service_service_jobcode_is_closed(:id)}", nativeQuery = true)
	Long isClosed(@Param("id") Long id);

	@Query(value = "{call sp_service_jobcard_reopen(:id, :loginId)}", nativeQuery = true)
	Long reopen(@Param("id") Long id, @Param("loginId") Long loginId);
	
	@Query(value = "{call sp_service_jobcard_search_autocomplete_chassis_no(:chassisNo,:branchId,:userCode)}", nativeQuery = true)
	List<Map<String, Object>> searchAutoCompleteChassisNo(@Param("chassisNo") String chassisNo,
			@Param("branchId") Long branchId,
			@Param("userCode") String userCode);

	@Query(value = "{call sp_service_jobcard_search_autocomplete_engine_no(:engineNo,:branchId,:userCode)}", nativeQuery = true)
	List<Map<String, Object>> searchAutoCompleteEngineNo(@Param("engineNo") String engineNo,
			@Param("branchId") Long branchId,
			@Param("userCode") String userCode);

	@Query(value = "{call sp_service_jobcard_autocomplete_jobcard_no(:jobCodeNo,:branchId,:userCode,:type, :chassis)}", nativeQuery = true)
	List<Map<String, Object>> autoCompleteJobCodeNo(@Param("jobCodeNo") String jobCodeNo,
													@Param("branchId") Long branchId,
													@Param("userCode") String userCode,
													@Param("type")String type,
													@Param("chassis")String chassis);

	@Query(value = "{call sp_service_jobcard_autocomplete_frc_code(:frsCode,:modelId)}", nativeQuery = true)
	List<Map<String, Object>> autoCompleteFrsCode(@Param("frsCode") String frsCode, @Param("modelId") Long modelId);

	@Query(value = "{call sp_service_jobcard_frs_detail_by_frs_no(:frsCode,:modelId,:dealerId)}", nativeQuery = true)
	Map<String, Object> frsCodeDetailByFrsCode(@Param("frsCode") String frsCode, @Param("modelId") Long modelId,@Param("dealerId")Long dealerId);

	@Query(value = "{call sp_service_jobcode_auto_complete_jobcode_no(:jobcode)}", nativeQuery = true)
	List<Map<String, Object>> autoCompleteOutsizeJobCode(@Param("jobcode") String jobcode);

	@Query(value = "{call sp_service_jobcard_create_part_requisition(:jobCardId)}", nativeQuery = true)
	String createSparePartRequistion(@Param("jobCardId") Long jobCardId);

	@Query(value = "{call sp_warranty_service_jobcard_close(:jobCardId)}", nativeQuery = true)
	String closeJobCard(@Param("jobCardId") Long jobCardId);

	@Query(value = "{call sp_service_jobcard_set_pcr_no(:jobCardId)}", nativeQuery = true)
	String setPcrNo(@Param("jobCardId") Long jobCardId);

//	@Query(value = "{call sp_service_jobcard_set_jobcard_close_flag(:jobCardId)}", nativeQuery = true)
//	Boolean setJobcardCloseFlag(@Param("jobCardId") Long jobCardId);

	@Query(value = "{call sp_service_jobcard_set_pcr_approved_flag(:jobCardId)}", nativeQuery = true)
	Map<String, Object> setPcrApprovedFlag(@Param("jobCardId") Long jobCardId);

	@Query(value = "Select allow_video_upload from wa_pcr where service_jobcode_id=:jobCardId", nativeQuery = true)
	Boolean setAllowVideoUpload(@Param("jobCardId") Long jobCardId);
	
	@Query(value = "{call sp_service_jobcard_drop_down_labour_charge_category()}", nativeQuery = true)
	List<Map<String, Object>> dropDownLabourChargeCategory();

	@Query(value = "{call sp_service_jobcard_drop_down_os_labour_charge_category()}", nativeQuery = true)
	List<Map<String, Object>> dropDownOsLabourChargeCategory();

	@Query(value="select distinct status from SV_JOBCARD(nolock) order by status", nativeQuery=true)
	List<Object> getStatusList();
	
	@Query(value = "{call sp_service_jobcard_is_jobcard_created(:machineInventoryId)}", nativeQuery = true)
	Boolean isJobCardCreated(@Param("machineInventoryId") Long machineInventoryId);
	
	@Modifying
	@Query(value = "update sv_jobcard set Part_Issue_Flag=1 where id=:id", nativeQuery = true)
	Integer updatePastIssue(@Param("id")Long id );

	@Modifying
	@Query(value="update SV_JOBCARD_PHOTOS set delete_flag=1 where service_jobcard_id=:id and file_type=:fileType", nativeQuery = true)
	Integer deletePhoto(@Param("id")Long id,@Param("fileType")String fileType);
	
	@Query(value = "{call sp_machine_service_history(:chassisNo, :page, :size, :userCode)}", nativeQuery = true)
	List<MachineServiceHistoryDto> getMachineServiceHistory(@Param("chassisNo") String chassisNo, @Param("page") Integer page, @Param("size") Integer size,@Param("userCode") String userCode);
	
	/**
	 * @author suraj.gaur
	 * @param chassisNo
	 * @return List<Map<String, Object>> 
	 */
	@Query(value = "{call SP_GET_SV_RETROFITMENT_BY_CHASSISNO(:chassisNo)}", nativeQuery = true)
	List<Map<String, Object>> getServiceRetroByChassisNo(@Param("chassisNo") String chassisNo);
	
	/**
	 * @author suraj.gaur
	 * @param chassisNo
	 * @return List<Map<String, Object>> 
	 */
	@Query(value = "{call SP_GET_RETRO_ITEM_DETAILS_BY_RETRO_ID(:retroId)}", nativeQuery = true)
	List<Map<String, Object>> getRetroItemDetailById(@Param("retroId") Long retroId);
	
	/**
	 * @author suraj.gaur
	 * @param chassisNo
	 * @return List<Map<String, Object>> 
	 */
	@Query(value = "{call SP_GET_RETRO_LABOUR_INFO_BY_RETRO_ID(:retroId)}", nativeQuery = true)
	List<Map<String, Object>> getRetroLabourInfoById(@Param("retroId") Long retroId);/**
	
	/**
	 *@author suraj.gaur 
	 */
	@Query(value = "{call SP_SA_GET_IMPL_DTL(:inventoryId)}", nativeQuery = true)
	List<Map<String, Object>> getImplementsDetailsForChassisNo(@Param("inventoryId") Long inventoryId);
	
}
