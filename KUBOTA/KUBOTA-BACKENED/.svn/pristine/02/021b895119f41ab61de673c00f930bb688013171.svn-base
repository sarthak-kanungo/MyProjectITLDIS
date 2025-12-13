package com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.controller;

import static com.i4o.dms.kubota.configurations.Constants.COUNT;
import static com.i4o.dms.kubota.utils.Constants.MESSAGE;
import static com.i4o.dms.kubota.utils.Constants.RESULT;
import static com.i4o.dms.kubota.utils.Constants.STATUS;
import static com.i4o.dms.kubota.utils.Constants.SUCCESS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.domain.CallSearchRequest;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.domain.CustomerCareExeCallHdr;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.domain.QaCrmCceCallFeedback;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto.CRMCallSearchResponseDto;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto.CallSearchResponseDto;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto.CceEnquiryViewDto;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto.CceHdrViewDto;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto.DealerDetailsViewDto;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto.JobCardSearchDto;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto.JobCardSearchResponceDto;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto.SaleEnquiryDetailDto;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto.SearchDCResponseDto;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.repo.CustomerCareExeCallRepo;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.repo.QaCceCallFeedbackRepo;
import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.service.CustomerCareExeCallService;
import com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.repo.TollFreeRepo;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.repository.CustomerMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.salesandpresales.enquiry.domain.Enquiry;
import com.i4o.dms.kubota.salesandpresales.enquiry.repository.EnquiryRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.servicebooking.repository.ServiceBookingRepo;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping("/api/crm/crmmodule/customerCareExeCall")
public class CustomerCareExeCallController {

	@Autowired
	private CustomerCareExeCallRepo cceCallRepo;
	@Autowired
	private UserAuthentication userAuthentication;
	@Autowired
	private CustomerMasterRepo customerMasterRepo;
	@Autowired
	private DealerMasterRepo dealerMasterRepo;
	@Autowired
	private TollFreeRepo tollFreeRepo;
	@Autowired
	private CustomerCareExeCallService crmService;
	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
    private EnquiryRepo enquiryRepo;
	
	@Autowired
	private ServiceBookingRepo serviceBookingRepo;
	
	@Autowired
	private QaCceCallFeedbackRepo callFeedbackRepo; 
	
	@Autowired 
	private QaCceCallFeedbackRepo cceCallFeedbackRepo;
	
	/**
	 * @author suraj.gaur
	 * @param cceCallHdr
	 * @return
	 */
	@PostMapping(value="/create")
	public ResponseEntity<?> create(@RequestBody CustomerCareExeCallHdr cceCallHdr){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		
		cceCallHdr.setDealerId(userAuthentication.getDealerId());
		cceCallHdr.setCreatedBy(userAuthentication.getLoginId());
		cceCallHdr.setCreatedDate(new Date());
		
//		if(cceCallHdr.getComplaintDtlList()!=null){
//			cceCallHdr.setComplaintDtlList(cceCallHdr.getComplaintDtlList().stream().filter(comp -> comp.getComplaintType()!=null).collect(Collectors.toList()));
//			cceCallHdr.getComplaintDtlList().forEach(comp -> {
//				comp.setComplaintNumber("COMP/"+System.currentTimeMillis());
//			});
//		}
		

		DateFormat formatter = new SimpleDateFormat("HH:mm");
		cceCallHdr.setEndTime(formatter.format(new Date()));
		
		List<QaCrmCceCallFeedback> callQuestionair = new ArrayList<>();
		
		if(cceCallHdr.getCallType().getId() == 1L && cceCallHdr.getEnquiryDetailDto() != null) {
			Enquiry enquiry = new Enquiry();
			
			enquiry.setEnquiryDate(new Date());
			enquiry.setSource(cceCallHdr.getEnquiryDetailDto().getSource());
			enquiry.setModel(cceCallHdr.getEnquiryDetailDto().getModel());
			enquiry.setSubModel(cceCallHdr.getEnquiryDetailDto().getSubModel());
			enquiry.setVariant(cceCallHdr.getEnquiryDetailDto().getVariant());
			enquiry.setMobileNumber(cceCallHdr.getEnquiryDetailDto().getMobileNumber());
			enquiry.setFirstName(cceCallHdr.getEnquiryDetailDto().getFirstName());
			enquiry.setLastName(cceCallHdr.getEnquiryDetailDto().getLastName());
			enquiry.setTentativePurchaseDate(cceCallHdr.getEnquiryDetailDto().getTentativePurchaseDate());
			enquiry.setNextFollowUpDate(cceCallHdr.getEnquiryDetailDto().getNextFollowUpDate());
			enquiry.setRemarks(cceCallHdr.getEnquiryDetailDto().getRemarks());
			enquiry.setBranchId(userAuthentication.getBranchId());
			
			DealerEmployeeMaster dealerEmployeeMaster = new DealerEmployeeMaster();
			dealerEmployeeMaster.setId(userAuthentication.getDealerEmployeeId());
			enquiry.setSalesPerson(dealerEmployeeMaster);	//if there will need to change, will change it.
			
			//Mandatory fields
			enquiry.setAddress1("test");
			enquiry.setPurposeOfPurchase("test");
			enquiry.setEnquiryType("test");
			enquiry.setFollowUpType("test");
			enquiry.setPostOffice("test");
			enquiry.setExchangeRequired("test");
			enquiry.setLandSize(0.0);
			enquiry.setFollowUpType("test");
			
			enquiry = enquiryRepo.save(enquiry);
			
			cceCallHdr.setSalesEnquiryId(enquiry.getId());
		}
		
		if(cceCallHdr.getCallType().getId() == 2L && cceCallHdr.getServiceBooking() != null) {			

			cceCallHdr.getServiceBooking().setBranchId(userAuthentication.getBranchId());
			cceCallHdr.getServiceBooking().setBookingDate(new Date());
			cceCallHdr.getServiceBooking().setStatus("Open");
			cceCallHdr.getServiceBooking().setCreatedBy(userAuthentication.getLoginId());					

			serviceBookingRepo.save(cceCallHdr.getServiceBooking());

			cceCallHdr.setServiceBookingId(cceCallHdr.getServiceBooking().getId());
			cceCallHdr.setCustomerMasterId(cceCallHdr.getServiceBooking().getCustomerMaster().getId());
		}
		
		if(cceCallHdr.getCallType().getId() == 3L && cceCallHdr.getCallFeedback() != null && cceCallHdr.getCallFeedback().size() != 0) {
			
			callQuestionair = callFeedbackRepo.saveAll(cceCallHdr.getCallFeedback());
			
			cceCallHdr.setJcId(cceCallHdr.getCallFeedback().get(0).getJcId());
			
			cceCallHdr.setCustomerMasterId(cceCallRepo.getJcCustomerId(cceCallHdr.getJcId()));
		}
		
		if(cceCallHdr.getCallType().getId() == 4L && cceCallHdr.getCallFeedback() != null && cceCallHdr.getCallFeedback().size() != 0) {
			
			callQuestionair = callFeedbackRepo.saveAll(cceCallHdr.getCallFeedback());
			
			cceCallHdr.setDcId(cceCallHdr.getCallFeedback().get(0).getDcId());
			
			cceCallHdr.setCustomerMasterId(cceCallRepo.getDcCustomerId(cceCallHdr.getDcId()));
		}
		
		cceCallRepo.save(cceCallHdr);
		
		if((cceCallHdr.getCallType().getId() == 3L && cceCallHdr.getCallFeedback() != null) || 
				(cceCallHdr.getCallType().getId() == 4L && cceCallHdr.getCallFeedback() != null)) {
			callQuestionair.forEach(item -> {
				cceCallRepo.setCallIdToQuestionair(item.getId(), cceCallHdr.getId());
			});
		}
		
		apiResponse.setMessage("CCE CALL Record Saved Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
		
	}
	
	
	/*
	 Modified by mahesh.kumar on 26-06-23
	 added callType and callStatus fields
	 */
	@PostMapping(value="search")
	public ResponseEntity<?> getCRMCallSearch(@RequestBody CallSearchRequest request){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<CRMCallSearchResponseDto> callList = cceCallRepo.getCRMCallSearch(
				userAuthentication.getDealerId(), 
				request.getFromDate(),
				request.getToDate(), 
				request.getMobileNo(),
				request.getCallType(),
				request.getCallStatus(),
				request.getCustomerName(),
				request.getPage(), 
				request.getSize());
		Long count=0l;
		if(callList!=null && !callList.isEmpty()){
			count = callList.get(0).getTotalCount();
		}
		apiResponse.setResult(callList);
		apiResponse.setCount(count);
		apiResponse.setMessage("CRM CCE CALL Search Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value="dealerDetails")
	public ResponseEntity<?> getDealerDetails(){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		Map<String, Object> dealerDetails = cceCallRepo.getDealerDetails(userAuthentication.getDealerId());
		apiResponse.setResult(dealerDetails);
		apiResponse.setMessage("CCE CALL Dealer Details Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value="getCustomerMachineDetails")
	public ResponseEntity<?> getCustomerMachineDetails(@RequestParam(value="customerId")Long customerId){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> machineDetails = cceCallRepo.getCustomerMachineDetails(customerId);
		apiResponse.setResult(machineDetails);
		apiResponse.setMessage("CCE CALL Machine Details Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value="getCustomerServiceHistory")
	public ResponseEntity<?> getCustomerServiceHistory(@RequestParam(value="customerId")Long customerId,
			@RequestParam(value="vinId")Long vinId){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> serviceHistory = cceCallRepo.getCustomerServiceHistory(customerId, vinId);
		apiResponse.setResult(serviceHistory);
		apiResponse.setMessage("CCE CALL Service History Details Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value="getCustomerCallHistory")
	public ResponseEntity<?> getCustomerCallHistory(@RequestParam(value="customerId")Long customerId,
			@RequestParam(value="vinId")Long vinId){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> callHistory = cceCallRepo.getCustomerCallHistory(customerId, vinId);
		apiResponse.setResult(callHistory);
		apiResponse.setMessage("CCE CALL Histroy Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value="fetchCallDetails")
	public ResponseEntity<?> fetchCallDetails(@RequestParam(value="callId")Long callId){
		CustomerCareExeCallHdr cceCallHdr = cceCallRepo.getOne(callId);
//		if(cceCallHdr.getCustomerMasterId()!=null){
//			String customerCode = customerMasterRepo.getCustomerCodeById(cceCallHdr.getCustomerMasterId());
//			Map<String, Object> customerDetails = customerMasterRepo.getcustomerDetails(customerCode);
//			cceCallHdr.setCustomerDetails(customerDetails);
//		}
//		if(cceCallHdr.getPinId()!=null){
//			Map<String, Object> enquiryDetails = dealerMasterRepo.getPincodeDetailByPincodeId(0L, cceCallHdr.getPinId());
//			cceCallHdr.setEnquiryDetails(enquiryDetails);
//		}
//		if(cceCallHdr.getComplaintDtlList()!=null){
//			cceCallHdr.getComplaintDtlList().forEach(comp -> {
//				if(comp.getHoUserId()!=null){
//					comp.setAssignTo(tollFreeRepo.getHoUserNameById(comp.getHoUserId()));
//				}
//			});
//		}
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		apiResponse.setResult(cceCallHdr);
		apiResponse.setMessage("CCE CALL Details Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value="complaintReportingList")
	public ResponseEntity<?> complaintReportingList(@RequestParam(value="dealerId")Long dealerId,
			@RequestParam(value="department")String department){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> reportingList = cceCallRepo.getComplaintReportingList(dealerId, department);
		apiResponse.setResult(reportingList);
		apiResponse.setMessage("Reporting List Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getCallStatus")
	public ResponseEntity<?> getCallStatus()
	{
		try {
			ApiResponse<?> apiResponse = crmService.getCallStatus();
			apiResponse.setMessage("Get all Call Status Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getCallStatus Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getCallType")
	public ResponseEntity<?> getCallType()
	{
		try {
			ApiResponse<?> apiResponse = crmService.getCallType();
			apiResponse.setMessage("Get all Call Type Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getCallType Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getQuesionnaire")
	public ResponseEntity<?> getQuesionnaire(@RequestParam Long typeOfCallId)
	{
		try {
			ApiResponse<?> apiResponse = crmService.getQuesionnaire(typeOfCallId);
			apiResponse.setMessage("Get all Quesionnaire Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getQuesionnaire Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?> 
	 */
	@PostMapping(value="/getJobCardList")
	public ResponseEntity<?> getJobCardList(@RequestBody JobCardSearchDto searchDto){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<JobCardSearchResponceDto> dealerDetails = cceCallRepo.getJobCardList(searchDto.getChassisNo(),
				searchDto.getMobileNo(), searchDto.getCustomerName(), userAuthentication.getUsername(), 
				'N', searchDto.getPage(), searchDto.getSize());
		apiResponse.setResult(dealerDetails);
		Long count = 0L;
		if (dealerDetails != null && dealerDetails.size() > 0) {
			count = dealerDetails.get(0).getRecordCount();
		}
		apiResponse.setCount(count);
		apiResponse.setMessage("Job card details get succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}

	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping(value="getCCECallHistory")
	public ResponseEntity<?> getCCECallHistory(@RequestParam(value="typeOfCall")Integer typeOfCall,
			@RequestParam(value="chassisNo")String chassisNo){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> cceCallHistory = cceCallRepo.getCCECallHistory(typeOfCall, chassisNo);
		apiResponse.setResult(cceCallHistory);
		apiResponse.setMessage("CCE CALL Histroy Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	
	@PostMapping("/searchCCESB") //CCESM = customer care executive service booking
	public ResponseEntity<?> getCCESBRecords(@RequestBody CallSearchRequest requestModel){
		ApiResponse<List<CallSearchResponseDto>> apiResponse = new ApiResponse<List<CallSearchResponseDto>>();
		List<CallSearchResponseDto> result = cceCallRepo.getCCESBMonitoringBoardRecords( 
				requestModel.getChassisNo(),
				requestModel.getMobileNo(),
				requestModel.getCustomerName(),
				requestModel.getCurrentServiceDueType(),
				requestModel.getCurrentServiceDueDate(),
				requestModel.getPage(),
				requestModel.getSize()
				);
		Long count = 0l;
		if(result!=null && !result.isEmpty()){
			count = result.get(0).getRecordsCount();
		}
		apiResponse.setCount(count);
        apiResponse.setMessage("CCESB Records get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("autoSearchChassisNo")
	public ResponseEntity<?> autoSearchChassisNo(@RequestParam String chassisNumber) {
		ApiResponse<List<?>> apiResponse = new ApiResponse<List<?>>();
		apiResponse.setMessage("auto complete Chassis Number");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(cceCallRepo.autoSearchChassisNo(chassisNumber));
		return ResponseEntity.ok(apiResponse);

	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("autoSearchCustomerName")
	public ResponseEntity<?> autoSearchCustomerName(@RequestParam String customerName) {
		ApiResponse<List<?>> apiResponse = new ApiResponse<List<?>>();
		apiResponse.setMessage("auto complete Customer Name");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(cceCallRepo.autoSearchCustomerName(customerName));
		return ResponseEntity.ok(apiResponse);

	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("autoSearchCustomerMobileNo")
	public ResponseEntity<?> autoSearchCustomerMobileNo(@RequestParam String mobileNumber) {
		ApiResponse<List<?>> apiResponse = new ApiResponse<List<?>>();
		apiResponse.setMessage("auto complete Customer Mobile No");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(cceCallRepo.autoSearchCustomerMobileNo(mobileNumber));
		return ResponseEntity.ok(apiResponse);

	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 * @apiNote Auto Search Customer Name in Service booking create page
	 */
	@GetMapping("autoSBSearchCustomerName")
	public ResponseEntity<?> autoSBSearchCustomerName(@RequestParam String customerName) {
		ApiResponse<List<?>> apiResponse = new ApiResponse<List<?>>();
		apiResponse.setMessage("auto complete Customer Name");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(cceCallRepo.autoSBSearchCustomerName(customerName));
		return ResponseEntity.ok(apiResponse);

	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 * @apiNote Auto Search Customer Mobile Number in Service booking create page
	 */
	@GetMapping("autoSBSearchCustomerMobileNo")
	public ResponseEntity<?> autoSBSearchCustomerMobileNo(@RequestParam String mobileNumber) {
		ApiResponse<List<?>> apiResponse = new ApiResponse<List<?>>();
		apiResponse.setMessage("auto complete Customer Mobile No");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(cceCallRepo.autoSBSearchCustomerMobileNo(mobileNumber));
		return ResponseEntity.ok(apiResponse);

	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("autoSearchCustomerServiceDueType")
	public ResponseEntity<?> autoSearchCustomerServiceDueType(@RequestParam String currentServiceDueType) {
		ApiResponse<List<?>> apiResponse = new ApiResponse<List<?>>();
		apiResponse.setMessage("auto complete current service due type");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(cceCallRepo.autoSearchCustomerServiceDueType(currentServiceDueType));
		return ResponseEntity.ok(apiResponse);

	}
	
	/**
	 * @author suraj.gaur
	 * @param requestModel
	 * @return
	 */
	@PostMapping(value = "/getDcList")
    public ResponseEntity<?> getDcSearch(@RequestBody CallSearchRequest requestModel) {
        Map<String, Object> map = new HashMap<>();

        List<SearchDCResponseDto> search = cceCallRepo.getDcList(
        		userAuthentication.getDealerId(),
        		requestModel.getChassisNumber(),
        		requestModel.getCustomerName(),
        		requestModel.getCustomerMobileNumber(),
        		userAuthentication.getUsername(), 
        		'N',
        		requestModel.getHierId(),
                userAuthentication.getDealerEmployeeId(),
                requestModel.getPage(),
                requestModel.getSize());

        Long searchCount = 0l;
        if(search!=null && search.size()>0){
        	searchCount = search.get(0).getRecordCount();
        }
        map.put(MESSAGE, "Get Dc list");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, search);
        map.put(COUNT, searchCount);
        return ResponseEntity.ok(map);
    }
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity
	 */
	@GetMapping("/viewCrm")
	public ResponseEntity<?> viewCrm(@RequestParam String crmNo, @RequestParam Long callTypeId) {
		ApiResponse<CceEnquiryViewDto> apiResponse = new ApiResponse<>();
		
		CceEnquiryViewDto enquiryViewDto = new CceEnquiryViewDto();
		
		CceHdrViewDto callDto = cceCallRepo.findByCallNo(crmNo);
		
		DealerDetailsViewDto dealerdetailsDto = new DealerDetailsViewDto();
		Map<String, Object> dealerMap = cceCallRepo.getDealerDetailsById(callDto.getDealerId());
		
		dealerdetailsDto.setId(Long.parseLong(dealerMap.get("id").toString()));
		dealerdetailsDto.setDealerCode((String)dealerMap.get("dealer_code"));
		dealerdetailsDto.setDealerName((String)dealerMap.get("dealer_name"));
		dealerdetailsDto.setDealerLocation((String)dealerMap.get("dealer_location"));
		dealerdetailsDto.setContactNo((String)dealerMap.get("mobile_no").toString());

		enquiryViewDto.setCceHdrViewDto(callDto);
		enquiryViewDto.setDealerDetailsDto(dealerdetailsDto);
		
		if(callTypeId == 1L) {	//Sales Inquiry
			SaleEnquiryDetailDto enquiryDetailDto = new SaleEnquiryDetailDto();
			
			Map<String, Object> enquiryMap = cceCallRepo.getEnquiryDetailsById(callDto.getSalesEnquiryId());
			
			enquiryDetailDto.setSource((String)enquiryMap.get("source"));
			enquiryDetailDto.setModel((String)enquiryMap.get("model"));
			enquiryDetailDto.setSubModel((String)enquiryMap.get("sub_model"));
			enquiryDetailDto.setVariant((String)enquiryMap.get("variant"));
			enquiryDetailDto.setMobileNumber((String)enquiryMap.get("mobile_number"));
			enquiryDetailDto.setFirstName((String)enquiryMap.get("first_name"));
			enquiryDetailDto.setLastName((String)enquiryMap.get("last_name"));
			enquiryDetailDto.setTentativePurchaseDate((Date)enquiryMap.get("tentative_purchase_date"));
			enquiryDetailDto.setNextFollowUpDate((Date)enquiryMap.get("next_follow_up_date"));
			enquiryDetailDto.setRemarks((String)enquiryMap.get("remarks"));
			enquiryDetailDto.setEnquiryDate((Date)enquiryMap.get("enquiry_date"));
			
			enquiryViewDto.setEnquiryDetailDto(enquiryDetailDto);
			
		}else if(callTypeId == 2L) {	//Service Booking
			enquiryViewDto.setServiceBookingViewDto(serviceBookingRepo.viewServiceBookingById(callDto.getServiceBookingId()));
			
		}else if(callTypeId == 3L) {	//Post Service Feedback
			if(callDto.getJcId() != null) {
				enquiryViewDto.setCallFeedback(cceCallFeedbackRepo.findByCallHdrIdAndJcId(callDto.getId(), callDto.getJcId()));
				
				Map<String, Object> customerData = cceCallRepo.getCustMobNo(callDto.getId(), callDto.getJcId());
				if(customerData != null) {
					enquiryViewDto.setCustomerId(customerData.get("customerId").toString());
					enquiryViewDto.setCustomerCode(customerData.get("customerCode").toString());
				}				
			}
			
		}else if(callTypeId == 4L) {	//Post Sales Feedback
			if(callDto.getDcId() != null) {
				enquiryViewDto.setCallFeedback(cceCallFeedbackRepo.findByCallHdrIdAndDcId(callDto.getId(), callDto.getDcId()));
				
				Map<String, Object> customerData = cceCallRepo.getCustMobNo(callDto.getId(), callDto.getDcId());				
				if(customerData != null) {
					enquiryViewDto.setCustomerId(customerData.get("customerId").toString());
					enquiryViewDto.setCustomerCode(customerData.get("customerCode").toString());
				}
			}
		}
		
		apiResponse.setResult(enquiryViewDto);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setMessage("auto complete current service due type");
		return ResponseEntity.ok(apiResponse);
	} 
	
}
