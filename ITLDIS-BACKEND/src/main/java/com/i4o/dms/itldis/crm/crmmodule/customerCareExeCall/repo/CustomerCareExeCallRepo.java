package com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.domain.CustomerCareExeCallHdr;
import com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.dto.CRMCallSearchResponseDto;
import com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.dto.CallSearchResponseDto;

import com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.dto.CceHdrViewDto;
import com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.dto.SearchDCResponseDto;
import com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.dto.JobCardSearchResponceDto;
import com.i4o.dms.itldis.service.servicebooking.dto.ServiceBookingViewDto;


public interface CustomerCareExeCallRepo extends JpaRepository<CustomerCareExeCallHdr, Long>{

	@Query(nativeQuery=true, value="{call SP_CCE_CALL_DEALER_DETAILS (:dealerId)}")
	Map<String, Object> getDealerDetails(@Param("dealerId")Long dealerId);
	
	@Query(nativeQuery=true, value="{call sp_GetCustomerMachine (:customerId)}")
	List<Map<String, Object>> getCustomerMachineDetails(@Param("customerId")Long customerId);
	
	@Query(nativeQuery=true, value="{call sp_GetServiceHistory (:customerId,:vinId)}")
	List<Map<String, Object>> getCustomerServiceHistory(@Param("customerId")Long customerId, @Param("vinId")Long vinId);
	
	@Query(nativeQuery=true, value="{call sp_GetCallHistory (:customerId, :vinId)}")
	List<Map<String, Object>> getCustomerCallHistory(@Param("customerId")Long customerId, @Param("vinId")Long vinId);
	
	@Query(nativeQuery=true, value="{call sp_cce_call_search (:dealerId, :fromDate, :toDate, :mobileNo, :page, :size, :UserCode)}")
	List<CallSearchResponseDto> getCallSearch(
			@Param("dealerId")Long dealerId,
			@Param("fromDate")String fromDate,
			@Param("toDate")String toDate,
			@Param("mobileNo")String mobileNo,
			@Param("page")Integer page,
			@Param("size")Integer size,
			@Param("UserCode")String UserCode);
	
	@Query(nativeQuery=true, value="{call sp_cce_call_searchs (:dealerId, :fromDate, :toDate, :mobileNo, :callType, :callStatus, :customerName, :page, :size)}")
	List<CRMCallSearchResponseDto> getCRMCallSearch(
			@Param("dealerId")Long dealerId,
			@Param("fromDate")String fromDate,
			@Param("toDate")String toDate,
			@Param("mobileNo")String mobileNo,
			@Param("callType")String callType,
			@Param("callStatus")String callStatus,
			@Param("customerName")String customerName,
			@Param("page")Integer page,
			@Param("size")Integer size);
	
	
	@Query(nativeQuery=true, value="{call SP_COMPLAINT_REPORTING_PERSON (:dealerId, :department)}")
	List<Map<String, Object>> getComplaintReportingList( @Param("dealerId")Long dealerId, @Param("department")String department);
	
	@Query(value = "select id, call_status as callStatus from QA_CRM_CALL_STATUS", nativeQuery = true)
	List<Map<String, Object>> getCallStatus();
	
	@Query(value = "select id, type_of_call as typeOfCall from QA_CRM_CUST_CALL_TYPE", nativeQuery = true)
	List<Map<String, Object>> getCallType();
	
	@Query(nativeQuery=true, value="{call sp_qa_crm_questionnaire (:typeOfCallId)}")
	List<Map<String, Object>> getQuesionnaire(@Param("typeOfCallId")Long typeOfCallId);
	
	@Query(nativeQuery=true, value="{call sp_crm_cce_service_jobcard_feedback(:chassisNo,:mobileNo,:customerName,:userCode,:includeInactive,:page,:size)}")
	List<JobCardSearchResponceDto> getJobCardList(
			@Param("chassisNo")String chassisNo,
			@Param("mobileNo")String mobileNo,
			@Param("customerName")String customerName,
			@Param("userCode")String userCode,
			@Param("includeInactive")Character includeInactive,
			@Param("page")Integer page,
			@Param("size")Integer size);
	
	@Query(nativeQuery=true, value="{call sp_qa_crm_call_history (:typeOfCall, :chassisNo)}")
	List<Map<String, Object>> getCCECallHistory(@Param("typeOfCall")Integer typeOfCall, @Param("chassisNo")String chassisNo);
	
	@Query(value="{call SP_QA_CRM_CCE_SERVICE_BOOKING_SEARCH (:chassisNo, :mobileNumber, :customerName, :currentServiceDueType,"
			+ ":currentServiceDueDate, :page, :size)}", nativeQuery=true)
	public List<CallSearchResponseDto> getCCESBMonitoringBoardRecords(
			@Param("chassisNo")String chassisNo,
			@Param("mobileNumber")String mobileNumber,
			@Param("customerName")String customerName,
			@Param("currentServiceDueType")String currentServiceDueType,
			@Param("currentServiceDueDate")String currentServiceDueDate,
			@Param("page")Integer page,
			@Param("size")Integer size);
	
	
	@Query(value = "{call SP_QA_CRM_CCE_AUTOSEARCH_CHASSIS_NUMBER(:chassisNumber)}", nativeQuery = true)
	List<Map<String, Object>> autoSearchChassisNo(@Param("chassisNumber") String chassisNumber);
	
	
	@Query(value = "{call SP_QA_CRM_CCE_AUTOSEARCH_CUSTOMER_NAME(:customerName)}", nativeQuery = true)
	List<Map<String, Object>> autoSearchCustomerName(@Param("customerName") String customerName);
	
	@Query(value = "{call SP_QA_CRM_CCE_AUTOSEARCH_CUSTOMER_MOBILE_NUMBER(:mobileNumber)}", nativeQuery = true)
	List<Map<String, Object>> autoSearchCustomerMobileNo(@Param("mobileNumber") String mobileNumber);
	
	@Query(value = "{call SP_QA_CRM_CCE_SB_AUTOSEARCH_CUSTOMER_NAME(:customerName)}", nativeQuery = true)
	List<Map<String, Object>> autoSBSearchCustomerName(@Param("customerName") String customerName);
	
	@Query(value = "{call SP_QA_CRM_CCE_SB_AUTOSEARCH_CUSTOMER_MOBILE_NUMBER(:mobileNumber)}", nativeQuery = true)
	List<Map<String, Object>> autoSBSearchCustomerMobileNo(@Param("mobileNumber") String mobileNumber);
	
	@Query(value = "{call SP_QA_CRM_CCE_AUTOSEARCH_CUSTOMER_CURRENT_SERVICE_DUE_TYPE(:currentServiceDueType)}", nativeQuery = true)
	List<Map<String, Object>> autoSearchCustomerServiceDueType(@Param("currentServiceDueType") String currentServiceDueType);
	
	@Query(value = "{call sp_qa_crm_cce_service_booking_view(:crmNo)}", nativeQuery = true)
    ServiceBookingViewDto viewServiceBooking(@Param("crmNo")String crmNo);
	
	@Query(value = "{call sp_qa_crm_cce_post_sales_feedback_view(:crmNo)}", nativeQuery = true)
    ServiceBookingViewDto viewPostSalesFeedback(@Param("crmNo")String crmNo);
	
	@Query(value = "{call sp_crm_cce_service_dc_list(:dealerId, :chassisNumber, :customerName, :customerMobileNumber, :userCode, :includeInactive, :OrgHierId, :dealerEmployeeId, :page, :size)}", nativeQuery = true)
    List<SearchDCResponseDto> getDcList(
    		@Param("dealerId") Long dealerId,
    		@Param("chassisNumber") String chassisNumber,
    		@Param("customerName") String customerName,
    		@Param("customerMobileNumber") String customerMobileNumber,
    		@Param("userCode") String userCode,
            @Param("includeInactive") Character includeInactive,
            @Param("OrgHierId") Long OrgHierId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("page") Integer page,
            @Param("size") Integer size
    );
	CceHdrViewDto findByCallNo(String callNo);
	
	@Query(value = "{call sp_get_dealer_by_id(:dealerId)}", nativeQuery = true)
	Map<String, Object> getDealerDetailsById(@Param("dealerId") Long dealerId);
	
	@Query(value = "{call sp_get_enquiry_by_id(:enquiryId)}", nativeQuery = true)
	Map<String, Object> getEnquiryDetailsById(@Param("enquiryId") Long enquiryId);
	
	@Query(value = "select customer_master_id from sv_jobcard where id = :id", nativeQuery = true)
	Long getJcCustomerId(@Param("id") Long id);
	
	@Query(value = "select customer_master_id from SA_DELIVERY_CHALLAN where delivery_challan_id = :id", nativeQuery = true)
	Long getDcCustomerId(@Param("id") Long id);
	
	@Modifying
    @Transactional
	@Query(value = "update QA_CRM_CCE_CALL_FEEDBACK set call_hdr_id = :callId where id = :id", nativeQuery = true)
	Integer setCallIdToQuestionair(@Param("id") Long id, @Param("callId") Long callId);
	
	@Query(value = "{call cce_get_cust_id_code(:custHdrId, :jcDcId)}", nativeQuery = true)
	Map<String, Object> getCustMobNo(Long custHdrId, Long jcDcId);

}