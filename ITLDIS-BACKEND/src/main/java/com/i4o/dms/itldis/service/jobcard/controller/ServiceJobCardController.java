package com.i4o.dms.itldis.service.jobcard.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.jobcard.domain.ServiceJobCard;
import com.i4o.dms.itldis.service.jobcard.domain.ServiceJobcardPhotos;
import com.i4o.dms.itldis.service.jobcard.dto.JobCardViewDto;
import com.i4o.dms.itldis.service.jobcard.dto.JobcardDetailedExcelResponseDto;
import com.i4o.dms.itldis.service.jobcard.dto.JobcardReopenApprovalDto;
import com.i4o.dms.itldis.service.jobcard.dto.JobcardReopenApprovalSearchResponseDto;
import com.i4o.dms.itldis.service.jobcard.dto.JobcardSearchDto;
import com.i4o.dms.itldis.service.jobcard.dto.JobcardSearchResponseDto;
import com.i4o.dms.itldis.service.jobcard.dto.MachineServiceHistoryDto;
import com.i4o.dms.itldis.service.jobcard.repository.LabourChargeRepo;
import com.i4o.dms.itldis.service.jobcard.repository.OutsideChargeRepo;
import com.i4o.dms.itldis.service.jobcard.repository.ServiceJobCardReopenApprovalRepo;
import com.i4o.dms.itldis.service.jobcard.repository.ServiceJobCardRepo;
//import com.i4o.dms.itldis.service.jobcard.servicejobcardimpl.ServiceJobcardImpl;
//import com.i4o.dms.itldis.service.jobcard.servicejobcardimpl.ServiceJobcardService;
import com.i4o.dms.itldis.service.jobcard.servicejobcardimpl.ServiceJobcardService;
import com.i4o.dms.itldis.service.servicebooking.repository.ServiceBookingRepo;
import com.i4o.dms.itldis.spares.partrequisition.domain.SparePartRequisition;
import com.i4o.dms.itldis.spares.partrequisition.domain.SparePartRequisitionItem;
import com.i4o.dms.itldis.spares.partrequisition.repository.SparePartRequisitionItemRepo;
import com.i4o.dms.itldis.spares.partrequisition.repository.SparePartRequisitionRepo;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.training.trainingProgramReport.dto.TrainingProgramReportExcelDto;
import com.i4o.dms.itldis.training.trainingProgramReport.dto.TrainingProgramReportSearchResponse;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;
import com.i4o.dms.itldis.warranty.pcr.repository.WarrantyPcrRepo;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })

@RequestMapping(value = "/api/service/jobcard")
public class ServiceJobCardController {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

	@Autowired
	private ServiceJobCardReopenApprovalRepo jobCardReopenApprovalRepo; 
	
	@Autowired
	private ServiceJobCardRepo serviceJobCardRepo;

	@Autowired
	private UserAuthentication userAuthentication;

	@Autowired
	private DealerMasterRepo dealerMasterRepo;

	@Autowired
	private StorageService storageService;

	@Autowired
	private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

	@Autowired
	private SparePartRequisitionRepo sparePartRequisitionRepo;

	@Autowired
	private ServiceJobcardService serviceJobcardService;

	@Autowired
	private LabourChargeRepo labourChargeRepo;

	@Autowired
	private OutsideChargeRepo outsideChargeRepo;

	@Autowired
	private SparePartRequisitionItemRepo sparePartRequisitionItemRepo;
	
	@Autowired
	private WarrantyPcrRepo warrantyPcrRepo;
//
//        @Autowired
//    private ServiceJobcardImpl serviceJobcardImpl;
	@Autowired
	private ServiceBookingRepo serviceBookingRepo;

	@PostMapping(value = "/saveJobcard", consumes = { "multipart/form-data" })
	public ResponseEntity<?> saveJobcard(@RequestPart(value = "serviceJobCard") ServiceJobCard serviceJobCard,
			@RequestPart(value = "fsCouponPage1", required = false) MultipartFile fsCouponPage1,
			@RequestPart(value = "fsCouponPage2", required = false) MultipartFile fsCouponPage2,
			@RequestPart(value = "hourMeterPhoto", required = false) MultipartFile hourMeterPhoto,
			@RequestPart(value = "chassisPhoto", required = false) MultipartFile chassisPhoto,
			@RequestPart(value = "signedJobcard", required = false) MultipartFile signedJobcard,
			@RequestPart(value = "retroAcknowledgementForm", required = false) MultipartFile retroAcknowledgementForm) {
		ApiResponse apiResponse = new ApiResponse();
		
		apiResponse = serviceJobcardService.saveJobCard(serviceJobCard, fsCouponPage1, fsCouponPage2, hourMeterPhoto,
				chassisPhoto, signedJobcard, retroAcknowledgementForm);

		return ResponseEntity.ok(apiResponse);
	}
	@PostMapping(value = "/cancelJobcard")
	public ResponseEntity<?> cancelJobcard(@RequestBody ServiceJobCard serviceJobCard){
		
		ApiResponse apiResponse = new ApiResponse();
		serviceJobCardRepo.cancelJobCard(serviceJobCard.getId(), serviceJobCard.getCancelRemarks(), userAuthentication.getLoginId());
		apiResponse.setMessage("Job Card cancelled successfuly");
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping(value = "/saveJobcardTest")
	public ResponseEntity<?> saveJobcardTest(@RequestBody ServiceJobCard serviceJobCard) {
		ApiResponse apiResponse = new ApiResponse();
//        logger.info("injob card"+"==================================");
////        serviceJobCard.setDealerMaster(dealerMasterRepo.getOne(2L));
////        serviceJobCard.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(4L));
//
//        serviceJobCard.setJobCardDate(new Date());
//        if (serviceJobCard.getDraftFlag()) {
//            serviceJobCardRepo.save(serviceJobCard);
//            apiResponse.setStatus(HttpStatus.OK.value());
//            apiResponse.setMessage("job card save successfully  ");
//            return ResponseEntity.ok(apiResponse);
//        }
//        ServiceJobCard serviceJobCard1= serviceJobCardRepo.save(serviceJobCard);
//        SparePartRequisition sparePartRequisition = null;
//
//
//        SparePartRequisition sparePartRequisition1=sparePartRequisitionRepo.findByServiceJobCard(serviceJobCard1);
//        if(sparePartRequisition1==null) {
//            sparePartRequisition = saveRequisition(serviceJobCard1);
//        }
//
//        SparePartRequisition finalSparePartRequisition = sparePartRequisition;
//        serviceJobCard1.getSparePartRequisitionItem().forEach(sparePartRequisitionItem -> {
////                if (sparePartRequisitionItem.getCategory().equalsIgnoreCase("FOC")
////                        || sparePartRequisitionItem.getCategory().equalsIgnoreCase("Warranty")) {
//            sparePartRequisitionItem.setSparePartRequisition(finalSparePartRequisition);
////                }
//        });
////        }
//        serviceJobCardRepo.save(serviceJobCard1);
//        apiResponse.setMessage("job card submitted successfully  ");
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("/autoCompleteChassisNoInJobCard")
	public ResponseEntity<?> getChassisNumber(@RequestParam String chassisNo, @RequestParam Boolean preSalesFlag, @RequestParam(required=false)String isFor) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("auto complete chassis number ");
		apiResponse.setStatus(HttpStatus.OK.value());
		if(isFor==null)isFor="";
		apiResponse.setResult(serviceJobCardRepo.autoCompleteChassisNumberInJobCard(chassisNo, preSalesFlag,
				userAuthentication.getBranchId(), userAuthentication.getDealerId(),isFor));

		return ResponseEntity.ok(apiResponse);
	}


	@GetMapping("/getChassisDetailsByChassisNoInJobCard")
	public ResponseEntity<?> getChassisDetailsByChassisNoInJobCard(@RequestParam String chassisNo,
			@RequestParam Boolean preSalesFlag, @RequestParam(required = false) Boolean jobCardFlag) {

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get chassis details by chassis no");
		apiResponse.setStatus(HttpStatus.OK.value());
		// this block is use warranty logsheet
		if (jobCardFlag == false && preSalesFlag == true) {
			preSalesFlag = false;
			Map<String, Object> map = serviceJobCardRepo.getChassisDetailsByChassisNoInJobCard(chassisNo, preSalesFlag,
					userAuthentication.getBranchId(), userAuthentication.getDealerId());
			
			Map<String, Object> result = new HashMap<>();	//Suraj 10-10-2022
			result.putAll(map);		//Suraj 10-10-2022
			
			Map<String,Object> warrantyPeriod = warrantyPcrRepo.getWarrantyPeriodDetail(chassisNo);
	        if(warrantyPeriod!=null){
//	        	map.put("warrantyEndDate", warrantyPeriod.get("warrantyEndDate"));
//	        	map.put("totalWarrantyHour", warrantyPeriod.get("totalWarrantyHour"));
	        	result.put("warrantyEndDate", warrantyPeriod.get("warrantyEndDate"));	//Suraj 10-10-2022
	        	result.put("totalWarrantyHour", warrantyPeriod.get("totalWarrantyHour"));	//Suraj 10-10-2022
	        }
//	        apiResponse.setResult(map);
			apiResponse.setResult(result);	//Suraj 10-10-2022
			apiResponse.setMessage("job card");
		}
		// this block is use in service booking
		else if (jobCardFlag == false) {
			Map<String, Object> map = serviceJobCardRepo.checkByChassisNo(chassisNo, jobCardFlag,
					userAuthentication.getBranchId());
			if (map.get("result").equals(false)) {
				apiResponse.setResult(serviceJobCardRepo.getChassisDetailsByChassisNoInJobCard(chassisNo, preSalesFlag,
						userAuthentication.getBranchId(), userAuthentication.getDealerId()));
				apiResponse.setMessage("create service booking");
			} else {
				apiResponse.setResult(map);
				apiResponse.setMessage(map.get("draftFlag").equals(true) ? "service booking already save on draft mode "
						: "service booking already create for the given chassis no ");

			}
			// this block is use in job card
		} else if (jobCardFlag == true) {
			Map<String, Object> map = serviceJobCardRepo.checkByChassisNo(chassisNo, jobCardFlag,
					userAuthentication.getBranchId());
			if (map.get("result").equals(false)) {
				apiResponse.setResult(serviceJobCardRepo.getChassisDetailsByChassisNoInJobCard(chassisNo, preSalesFlag,
						userAuthentication.getBranchId(), userAuthentication.getDealerId()));
				apiResponse.setMessage("create job card");
			} else {
				apiResponse.setResult(map);
				apiResponse.setMessage(map.get("draftFlag").equals(true) ? "Job card already save on draft mode "
						: "Job card already create for the given chassis no "+chassisNo);
			}
		}

		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("/employeeNameByDealerId")
	public ResponseEntity<?> employeeNameByDealerId() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("drop down employee name by dealer id");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.employeeNameByDealerId(userAuthentication.getDealerEmployeeId()));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("/dropDownServiceCategory")
	public ResponseEntity<?> dropDownServiceCategory(@RequestParam(required=false) Boolean preSalesFlag,
			@RequestParam(required=false) Boolean retrofitmentFlag) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("drop service category");
		apiResponse.setStatus(HttpStatus.OK.value());
		if(preSalesFlag==null)preSalesFlag=false;
		if(retrofitmentFlag==null)retrofitmentFlag=false;
		apiResponse.setResult(serviceJobCardRepo.dropDownServiceCategory(preSalesFlag, retrofitmentFlag));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("/dropDownPlaceOfService")
	public ResponseEntity<?> dropDownPlaceOfService(@RequestParam("preSalesFlag") Boolean preSalesFlag) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("drop down place of service");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.dropDownPlaceOfService(preSalesFlag));
		return ResponseEntity.ok(apiResponse);

	}

	@PostMapping("searchJobCard")
	public ResponseEntity<?> searchJobCard(@RequestBody JobcardSearchDto jobcardSearchDto) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("search job card ");
		apiResponse.setStatus(HttpStatus.OK.value());
		List<JobcardSearchResponseDto> result = (serviceJobCardRepo.jobCardSearch(
				userAuthentication.getManagementAccess(), userAuthentication.getKubotaEmployeeId(),
				(userAuthentication.getBranchId()!=null?userAuthentication.getBranchId():jobcardSearchDto.getBranch()), userAuthentication.getDealerEmployeeId(),
				jobcardSearchDto.getJobCardNo(), jobcardSearchDto.getChassisNo(), jobcardSearchDto.getEngineNo(),
				jobcardSearchDto.getCsbNo(), jobcardSearchDto.getBookingNo(), jobcardSearchDto.getJobCardDateFrom(),
				jobcardSearchDto.getJobCardDateTo(), jobcardSearchDto.getCustomerName(), jobcardSearchDto.getModel(),
				jobcardSearchDto.getRegistrationNo(), jobcardSearchDto.getMobileNo(),
				jobcardSearchDto.getServiceCategory(), jobcardSearchDto.getPlaceOfService(),
				jobcardSearchDto.getActivityType(), jobcardSearchDto.getActivityNo(), jobcardSearchDto.getPage(),
				jobcardSearchDto.getSize(), userAuthentication.getUsername(),jobcardSearchDto.getDealerId(),jobcardSearchDto.getFromMachineInDate(),jobcardSearchDto.getToMachineInDate(),
				jobcardSearchDto.getProduct(),jobcardSearchDto.getSeries(),jobcardSearchDto.getSubModel(),jobcardSearchDto.getVariant(),
				null,null,null,null,'N',jobcardSearchDto.getHierId(),jobcardSearchDto.getStatus(),jobcardSearchDto.getIsFor(), jobcardSearchDto.getPartNo()));
				
		apiResponse.setResult(result);
		Long count = 0L;
		if (result != null && result.size() > 0) {
			count = result.get(0).getRecordCount();
		}
		apiResponse.setCount(count);
		return ResponseEntity.ok(apiResponse);

	}
	@PostMapping("approveJobcardReopen")
	public ResponseEntity<?> approveJobcardReopen(@RequestBody JobcardReopenApprovalDto dto) {
		ApiResponse apiResponse = new ApiResponse();
		String status = jobCardReopenApprovalRepo.approveJobcardReopen(dto.getId(), userAuthentication.getKubotaEmployeeId(), 
				dto.getRemark(), 
				userAuthentication.getUsername(),
				dto.getApprovalStatus());
		
		apiResponse.setMessage(status);
		apiResponse.setStatus(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("searchJobCardReopenApproval")
	public ResponseEntity<?> searchJobCardReopenApproval(@RequestBody JobcardSearchDto jobcardSearchDto) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("search job card reopen approval");
		apiResponse.setStatus(HttpStatus.OK.value());
		List<JobcardReopenApprovalSearchResponseDto> result = serviceJobCardRepo.jobCardReopenApprovalSearch(
				jobcardSearchDto.getPage(),
				jobcardSearchDto.getSize(), 
				userAuthentication.getUsername(),
				0L);
				
		apiResponse.setResult(result);
		Long count = 0L;
		if (result != null && result.size() > 0) {
			count = result.get(0).getRecordCount();
		}
		apiResponse.setCount(count);
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("getJobCardByJobCardNo")
	public ResponseEntity<?> getJobCardByJobCardNo(@RequestParam String jobCardNo) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get job card by job card number ");
		apiResponse.setStatus(HttpStatus.OK.value());
		JobCardViewDto jobCardViewDto = serviceJobCardRepo.findByJobCardNo(jobCardNo);
		if(jobCardViewDto.getMachineOutDateTime()!=null){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			jobCardViewDto.setOutDateTime(df.format(jobCardViewDto.getMachineOutDateTime()));
		}
		List<ServiceJobcardPhotos> photos = jobCardViewDto.getServiceJobcardPhotos().stream().filter(jobcardphoto -> jobcardphoto.getIsSelected()==null || !jobcardphoto.getIsSelected()).collect(Collectors.toList());
		jobCardViewDto.setServiceJobcardPhotos(photos);
		Map<String, Object> res = serviceJobCardRepo.setPcrApprovedFlag(jobCardViewDto.getId());
		if(res!=null){		
			jobCardViewDto.setPcrApprovedFlag((Boolean)res.get("pcrApproved"));
			jobCardViewDto.setGoodwillRequired((Boolean)res.get("goodwillRequired"));
			jobCardViewDto.setGoodwillRaised((Boolean)res.get("goodwillRaised"));
			jobCardViewDto.setGoodwillApproved((Boolean)res.get("goodwillApproved"));
			jobCardViewDto.setGwNo((String)res.get("gwNo"));
			jobCardViewDto.setIsLatest((Boolean)res.get("isLatest"));
		}
		jobCardViewDto.setPcrNo(serviceJobCardRepo.setPcrNo(jobCardViewDto.getId()));
		jobCardViewDto.setAllowVideoUpload(serviceJobCardRepo.setAllowVideoUpload(jobCardViewDto.getId()));
		
		Map<String,Object> warrantyPeriod = warrantyPcrRepo.getWarrantyPeriodDetail(jobCardViewDto.getMachineInventory().getChassisNo());
        if(warrantyPeriod!=null){
        	jobCardViewDto.setWarrantyEndDate((String)warrantyPeriod.get("warrantyEndDate"));
        	jobCardViewDto.setTotalWarrantyHour((Double)warrantyPeriod.get("totalWarrantyHour"));
        }
        
		apiResponse.setResult(jobCardViewDto);
		return ResponseEntity.ok(apiResponse);

	}
	
	@GetMapping("getJobCardApprovalDetails")
	public ResponseEntity<?> getJobCardApprovalDetails(@RequestParam Long id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get job card by job card number ");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(jobCardReopenApprovalRepo.getApprovalHierDetails(id, userAuthentication.getUsername()));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("autoCompletePartNumber")
	public ResponseEntity<?> autoCompletePartNumber(@RequestParam String partNumber) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("auto complete part number");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.autoCompletePartNumber(partNumber, userAuthentication.getDealerId()));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("getPartDetailsByPartNumber")
	public ResponseEntity<?> getPartDetailsByPartNumber(@RequestParam String partNumber) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get part details by part number");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.getPartDetailsByPartNumber(partNumber));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("getDropDownMechanicName")
	public ResponseEntity<?> getDropDownMechanicName() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("drop down mechanic name");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.getDropDownMechanicName(userAuthentication.getDealerId()));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("getDropDownServiceRepresentative")
	public ResponseEntity<?> getDropDownServiceRepresentative() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("drop down service representative");
		apiResponse.setStatus(HttpStatus.OK.value());
		System.out.println("getDealerEmployeeId-->"+userAuthentication.getDealerEmployeeId());
		apiResponse.setResult(
				serviceJobCardRepo.getDropDownServiceRepresentative(userAuthentication.getDealerId(),userAuthentication.getDealerEmployeeId()));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("getDropDownCategory")
	public ResponseEntity<?> getDropDownCategory(@RequestParam Boolean preSalesFlag) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("drop down category");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.getDropDownCategory(preSalesFlag));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("searchChassisNumber")
	public ResponseEntity<?> searchChassisNumber() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("search chassis Number");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.searchChassisNo());
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("getServiceTypeByHour")
	public ResponseEntity<?> getServiceTypeByHour(@RequestParam Long machineInventoryId, @RequestParam Float totalHour,
			@RequestParam String serviceCategory, @RequestParam("jobid")Long jobid) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get service type by hour");
		apiResponse.setStatus(HttpStatus.OK.value());
		if(jobid==0)jobid = null;
		apiResponse.setResult(serviceJobCardRepo.getServiceTypeByHour(machineInventoryId, totalHour, serviceCategory, jobid));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("getTotalHour")
	public ResponseEntity<?> getTotalHour(@RequestParam Integer currentHour, @RequestParam String machineInventoryId,
			@RequestParam Double meterChangeHour) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get total hour ");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(
				serviceJobCardRepo.getTotalHour(currentHour, Long.parseLong(machineInventoryId), meterChangeHour));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("getBookingDetailsByBookingNo")
	public ResponseEntity<?> getBookingDetailsByBookingNo(@RequestParam Long bookingId) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get service type by hour");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(
				serviceJobCardRepo.getBookingDetailsByBookingNo(bookingId, userAuthentication.getBranchId()));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("autoCompleteSearchChassisNo")
	public ResponseEntity<?> autoCompleteSearchJobCard(@RequestParam String chassisNo) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("auto complete chassis no");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.searchAutoCompleteChassisNo(chassisNo,
				userAuthentication.getBranchId(), userAuthentication.getUsername()));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("searchAutoCompleteEngineNo")
	public ResponseEntity<?> searchAutoCompleteEngineNo(@RequestParam String engineNo) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("auto complete engine no");
		apiResponse.setStatus(HttpStatus.OK.value());
		// List<Map<String, Object>> result
		// =serviceJobCardRepo.searchAutoCompleteEngineNo(engineNo,userAuthentication.getDealerId(),userAuthentication.getUsername());
		apiResponse.setResult(serviceJobCardRepo.searchAutoCompleteEngineNo(engineNo, userAuthentication.getBranchId(),
				userAuthentication.getUsername()));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("searchAutoCompleteJobCode")
	public ResponseEntity<?> autoCompleteJobCode(@RequestParam String jobCode, @RequestParam(required=false)String type, @RequestParam(required=false)String chassis) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("auto complete job code no");
		apiResponse.setStatus(HttpStatus.OK.value());
		List<Map<String, Object>> autoCompleteJobCodeNo=serviceJobCardRepo.autoCompleteJobCodeNo(jobCode, userAuthentication.getBranchId(), userAuthentication.getUsername(), type, chassis);
		apiResponse.setResult(serviceJobCardRepo.autoCompleteJobCodeNo(jobCode, userAuthentication.getBranchId(),
				userAuthentication.getUsername(), type, chassis));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("autoCompleteFrsCode")
	public ResponseEntity<?> autoCompleteFrsCode(@RequestParam String frsCode, @RequestParam Long modelId) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("auto complete frs code");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.autoCompleteFrsCode(frsCode, modelId));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("frsCodeDetailByFrsCode")
	public ResponseEntity<?> frsCodeDetailByFrsCode(@RequestParam String frsCode, @RequestParam Long modelId) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("frs details by frs no");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.frsCodeDetailByFrsCode(frsCode, modelId, userAuthentication.getDealerId()));
		return ResponseEntity.ok(apiResponse);

	}

	@PostMapping(value = "/updateJobcard", consumes = { "multipart/form-data" })
	public ResponseEntity<?> UpdateJobcard(@RequestPart(value = "serviceJobCard") ServiceJobCard serviceJobCard,
			@RequestPart(value = "fsCouponPage1", required = false) MultipartFile fsCouponPage1,
			@RequestPart(value = "fsCouponPage1", required = false) MultipartFile fsCouponPage2,
			@RequestPart(value = "hourMeterPhoto", required = false) MultipartFile hourMeterPhoto,
			@RequestPart(value = "chassisPhoto", required = false) MultipartFile chassisPhoto,
			@RequestPart(value = "signedJobcard", required = false) MultipartFile signedJobcard,
			@RequestPart(value = "retroAcknowledgementForm", required = false) MultipartFile retroAcknowledgementForm) 
	{
		ApiResponse apiResponse = new ApiResponse();
		List<SparePartRequisitionItem> sparePartRequisitionItems = new ArrayList<>();
		ServiceJobCard serviceJobCard1 = serviceJobCardRepo.getOne(serviceJobCard.getId());
		serviceJobCard1.setLastModifiedDate(new Date());
		serviceJobCard1.setAlternateNumber(serviceJobCard.getAlternateNumber());
		serviceJobCard1.setLastModifiedBy(userAuthentication.getLoginId());
		serviceJobCard1.setCloseDelayReason(serviceJobCard.getCloseDelayReason());
		serviceJobCard1.setCloseRemark(serviceJobCard.getCloseRemark());
		if(serviceJobCard.getOutDateTime()!=null){
			String outDateTime = serviceJobCard.getOutDateTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				serviceJobCard1.setMachineOutDateTime(df.parse(outDateTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<ServiceJobcardPhotos> photoList = new ArrayList<>();
		if (fsCouponPage1 != null) {
			ServiceJobcardPhotos serviceJobcardPhotos = new ServiceJobcardPhotos();
			String jobcardPhoto = fsCouponPage1.getOriginalFilename();
			String photoName = "service_jobcard" + System.currentTimeMillis() + "_" + jobcardPhoto;
			storageService.store(fsCouponPage1, photoName);
			serviceJobcardPhotos.setFileName(photoName);
			serviceJobcardPhotos.setFileType("free service coupon 1");
			serviceJobcardPhotos.setServiceJobCard(serviceJobCard1);
			photoList.add(serviceJobcardPhotos);
			serviceJobCardRepo.deletePhoto(serviceJobCard1.getId(), "free service coupon 1");
		}
		if (fsCouponPage2 != null) {

			ServiceJobcardPhotos serviceJobcardPhotos = new ServiceJobcardPhotos();
			String jobcardPhoto = fsCouponPage2.getOriginalFilename();
			String photoName = "service_jobcard" + System.currentTimeMillis() + "_" + jobcardPhoto;
			storageService.store(fsCouponPage2, photoName);
			serviceJobcardPhotos.setFileName(photoName);
			serviceJobcardPhotos.setFileType("free service coupon 2");
			serviceJobcardPhotos.setServiceJobCard(serviceJobCard1);
			photoList.add(serviceJobcardPhotos);
			serviceJobCardRepo.deletePhoto(serviceJobCard1.getId(), "free service coupon 2");
		}
		if (hourMeterPhoto != null) {

			ServiceJobcardPhotos serviceJobcardPhotos = new ServiceJobcardPhotos();
			String jobcardPhoto = hourMeterPhoto.getOriginalFilename();
			String photoName = "service_jobcard" + System.currentTimeMillis() + "_" + jobcardPhoto;
			storageService.store(hourMeterPhoto, photoName);
			serviceJobcardPhotos.setFileName(photoName);
			serviceJobcardPhotos.setFileType("hour meter ");
			serviceJobcardPhotos.setServiceJobCard(serviceJobCard1);
			photoList.add(serviceJobcardPhotos);
			serviceJobCardRepo.deletePhoto(serviceJobCard1.getId(), "hour meter ");
		}
		if (chassisPhoto != null) {

			ServiceJobcardPhotos serviceJobcardPhotos = new ServiceJobcardPhotos();
			String jobcardPhoto = chassisPhoto.getOriginalFilename();
			String photoName = "service_jobcard" + System.currentTimeMillis() + "_" + jobcardPhoto;
			storageService.store(chassisPhoto, photoName);
			serviceJobcardPhotos.setFileName(photoName);
			serviceJobcardPhotos.setFileType("chassis");
			serviceJobcardPhotos.setServiceJobCard(serviceJobCard1);
			photoList.add(serviceJobcardPhotos);
			serviceJobCardRepo.deletePhoto(serviceJobCard1.getId(), "chassis");
		}
		//Suraj-24-04-2023-START
		if (signedJobcard != null) {
			ServiceJobcardPhotos serviceJobcardPhotos = new ServiceJobcardPhotos();
			String jobcardPhoto = signedJobcard.getOriginalFilename();
			String photoName = "service_jobcard" + System.currentTimeMillis() + "_" + jobcardPhoto;
			storageService.store(signedJobcard, photoName);
			serviceJobcardPhotos.setFileName(photoName);
			serviceJobcardPhotos.setFileType("Signed Job Card");
			serviceJobcardPhotos.setServiceJobCard(serviceJobCard1);
			photoList.add(serviceJobcardPhotos);
			serviceJobCardRepo.deletePhoto(serviceJobCard1.getId(), "Signed Job Card");
		}
		if (retroAcknowledgementForm != null) {
			ServiceJobcardPhotos serviceJobcardPhotos = new ServiceJobcardPhotos();
			String jobcardPhoto = retroAcknowledgementForm.getOriginalFilename();
			String photoName = "service_jobcard" + System.currentTimeMillis() + "_" + jobcardPhoto;
			storageService.store(retroAcknowledgementForm, photoName);
			serviceJobcardPhotos.setFileName(photoName);
			serviceJobcardPhotos.setFileType("Retrofitment Acknowledgement Form");
			serviceJobcardPhotos.setServiceJobCard(serviceJobCard1);
			photoList.add(serviceJobcardPhotos);
			serviceJobCardRepo.deletePhoto(serviceJobCard1.getId(), "Retrofitment Acknowledgement Form");
		}
		//Suraj-24-04-2023_END

		serviceJobCard1.setServiceJobcardPhotos(photoList);

		if (serviceJobCard.getSparePartRequisitionItem() != null) {
			SparePartRequisition sparePartRequisition = sparePartRequisitionRepo.findByServiceJobCard(serviceJobCard1);
			serviceJobCard.getSparePartRequisitionItem().forEach(sparePartRequisitionItem -> {
				if (sparePartRequisitionItem.getId() != null && (sparePartRequisitionItem.getCategory().getId() == 2
						|| sparePartRequisitionItem.getCategory().getId() == 3)) {
					SparePartRequisitionItem sparePartRequisitionItem1 = sparePartRequisitionItemRepo
							.getOne(sparePartRequisitionItem.getId());

					sparePartRequisitionItem1.setPriceUnit(sparePartRequisitionItem.getPriceUnit());
					sparePartRequisitionItem1.setGstPercent(sparePartRequisitionItem.getGstPercent());
					sparePartRequisitionItem1.setCategory(sparePartRequisitionItem.getCategory());
					sparePartRequisitionItem1.setSparePartRequisition(sparePartRequisition);
					if (sparePartRequisitionItem.getReqQuantity() != sparePartRequisitionItem1.getReqQuantity()) {
						sparePartRequisitionItem1.setReqQuantity(sparePartRequisitionItem.getReqQuantity());
						sparePartRequisitionItem1.setAmount(sparePartRequisitionItem.getAmount());
						sparePartRequisitionItem1.setQtyUpdateFlag(true);
					}
					sparePartRequisitionItems.add(sparePartRequisitionItem1);

				} else {

					sparePartRequisitionItem.setSparePartRequisition(sparePartRequisition);
					if (sparePartRequisitionItem.getId() == null) {
						serviceJobCard1.setPartIssueFlag(false);
						//serviceJobCard1.setPreinvoiceFlag(false);
					}

					if (sparePartRequisitionItem.getCategory().getId() == 2
							|| sparePartRequisitionItem.getCategory().getId() == 3) {
						sparePartRequisitionItem.setQtyUpdateFlag(true);
					}
					sparePartRequisitionItems.add(sparePartRequisitionItem);
				}

			});
			serviceJobCard1.setSparePartRequisitionItem(sparePartRequisitionItems);
		}
		if (serviceJobCard.getLabourCharge() != null) {
			serviceJobCard1.setLabourCharge(serviceJobCard.getLabourCharge());
		}
		if (serviceJobCard.getOutsideJobCharge() != null) {
			serviceJobCard1.setOutsideJobCharge(serviceJobCard.getOutsideJobCharge());
		}
		if (serviceJobCard.getServiceJobcardPhotos() != null) {
			serviceJobCard1.setServiceJobcardPhotos(serviceJobCard.getServiceJobcardPhotos());
		}
		if(serviceJobCard.getJobcardRetroMappings() != null) {	//Suraj-24-04-2023
			serviceJobCard1.setJobcardRetroMappings(serviceJobCard.getJobcardRetroMappings());	//Suraj-24-04-2023
		}	//Suraj-24-04-2023
		serviceJobCard1.setFinalActionTaken(serviceJobCard.getFinalActionTaken());
		serviceJobCard1.setSuggestionToCustomer(serviceJobCard.getSuggestionToCustomer());
		serviceJobCardRepo.save(serviceJobCard1);
		apiResponse.setMessage("Job card updated successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("autoCompleteOutsideJobCode")
	public ResponseEntity<?> frsCodeDetailByFrsCode(@RequestParam String jobcode) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage(" auto complete outside job code ");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.autoCompleteOutsizeJobCode(jobcode));
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("jobCardPreInvoice/{id}")
	public ResponseEntity<?> jobCardPreInvoice(@PathVariable Long id) {
		ApiResponse apiResponse = new ApiResponse();
		ServiceJobCard serviceJobCard = serviceJobCardRepo.getOne(id);
		serviceJobCard.setPartIssueFlag(false);
		//serviceJobCard.setPreinvoiceFlag(true);
		serviceJobCardRepo.save(serviceJobCard);
		//apiResponse.setResult(serviceJobCard.getPreinvoiceFlag());
		apiResponse.setMessage("pre invoice created successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);

	}
	
	@GetMapping("reopenJobcard/{id}")
	public ResponseEntity<?> reopenJobcard(@PathVariable Long id) {
		ApiResponse apiResponse = new ApiResponse();
		Long reopen = serviceJobCardRepo.reopen(id, userAuthentication.getLoginId());
		if (reopen==1 || reopen.equals(1)) {
			apiResponse.setMessage("Job Card reopen successfully");
			return ResponseEntity.ok(apiResponse);
		} else {
			apiResponse.setMessage("Job Card can't be reopen");
			return ResponseEntity.ok(apiResponse);
		}
	}
	
	@GetMapping("closeJobCard/{id}")
	public ResponseEntity<?> closeJobCard(@PathVariable Long id) {
		ApiResponse apiResponse = new ApiResponse();
		Long close = serviceJobCardRepo.isClosed(id);
		if (close==0 || close.equals(0)) {
			apiResponse.setMessage("Job Card closed successfully");
			return ResponseEntity.ok(apiResponse);
		} else {
			apiResponse.setMessage("Job Card already closed");
			return ResponseEntity.ok(apiResponse);
		}

//            ServiceJobCard serviceJobCard=serviceJobCardRepo.findById(id).get();
//            if(serviceJobCard.getSparePartRequisitionItem()==null && serviceJobCard.getOutsideJobCharge()==null && serviceJobCard.getOutsideJobCharge()==null){
//                serviceJobCard.setStatus("Close");
//            }
//            if(serviceJobCard.getLabourCharge()==null && serviceJobCard.getOutsideJobCharge()==null && ser)
//            serviceJobCard.setStatus("Pre Invoice");
//            serviceJobCard.setClosedDate(new Date());
//            serviceJobCardRepo.save(serviceJobCard);

	}

	private SparePartRequisition saveRequisition(ServiceJobCard serviceJobCard) {
		SparePartRequisition sparePartRequisition = new SparePartRequisition();
		sparePartRequisition.setRequisitionPurpose(
				serviceJobCard.getPlaceOfService().equalsIgnoreCase("Dealer Workshop") ? "Workshop" : "Field");
		sparePartRequisition.setServiceJobCard(serviceJobCard);
		sparePartRequisition.setRequisitionDate(new Date());
		sparePartRequisition.setCreatedBy(userAuthentication.getLoginId());
		sparePartRequisition.setBranchId(userAuthentication.getBranchId());
		return sparePartRequisitionRepo.save(sparePartRequisition);
	}

	@GetMapping("dropDownLabourChargeCategory")
	public ResponseEntity<?> dropDownLabourChargeCategory() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get labour charge category");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.dropDownLabourChargeCategory());
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("jobcardStatusList")
	public ResponseEntity<?>getJobcardStatusList(){
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get job acrd status list");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.getStatusList());
		return ResponseEntity.ok(apiResponse);
	}
	@GetMapping("dropDownOsLabourChargeCategory")
	public ResponseEntity<?> dropDownOsLabourChargeCategory() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get os labour charge category");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceJobCardRepo.dropDownOsLabourChargeCategory());
		return ResponseEntity.ok(apiResponse);

	}

	@PostMapping(value="machineServiceHistory")
	public ResponseEntity<?> machineServiceHistory(@RequestBody Map<String,Object> map){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<MachineServiceHistoryDto> reportingList = serviceJobCardRepo.getMachineServiceHistory((String)map.get("chassisNo"),(Integer)map.get("page"),(Integer)map.get("size"), userAuthentication.getUsername());
		Long count=0l;
		if(reportingList!=null && reportingList.size()>0){
			count = reportingList.get(0).getRecordCount();
		}
		apiResponse.setCount(count);
		apiResponse.setResult(reportingList);
		apiResponse.setMessage("Machine Service History List Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
    @PostMapping("/downloadJcExcelReport")
    public ResponseEntity<InputStreamResource> jcExcelReport(@RequestBody JobcardSearchDto jcExcel, HttpServletResponse response) throws IOException{
    	Integer size = Integer.MAX_VALUE-1;
    	List<JobcardSearchResponseDto> result = null;
    	List<JobcardDetailedExcelResponseDto> resultExcel = null;
    	 if (jcExcel.isFor.equals("VIEW") || jcExcel.isFor.equals("EXCEL")) {
    			result =  (serviceJobCardRepo.jobCardSearch(
    					userAuthentication.getManagementAccess(), userAuthentication.getKubotaEmployeeId(),
    					(userAuthentication.getBranchId()!=null?userAuthentication.getBranchId():jcExcel.getBranch()), userAuthentication.getDealerEmployeeId(),
    					jcExcel.getJobCardNo(), jcExcel.getChassisNo(), jcExcel.getEngineNo(),
    					jcExcel.getCsbNo(), jcExcel.getBookingNo(), jcExcel.getJobCardDateFrom(),
    					jcExcel.getJobCardDateTo(), jcExcel.getCustomerName(), jcExcel.getModel(),
    					jcExcel.getRegistrationNo(), jcExcel.getMobileNo(),
    					jcExcel.getServiceCategory(), jcExcel.getPlaceOfService(),
    					jcExcel.getActivityType(), jcExcel.getActivityNo(), 0,
    					size, userAuthentication.getUsername(),jcExcel.getDealerId(),jcExcel.getFromMachineInDate(),jcExcel.getToMachineInDate(),
    					jcExcel.getProduct(),jcExcel.getSeries(),jcExcel.getSubModel(),jcExcel.getVariant(),
    					null,null,null,null,'N', jcExcel.getHierId(),jcExcel.getStatus(),jcExcel.getIsFor(), jcExcel.getPartNo()));
    	 }
    	 if (jcExcel.isFor.equals("ALLEXCEL")) {
    		 resultExcel = (serviceJobCardRepo.jobcardDetailedExcel(
 					userAuthentication.getManagementAccess(), userAuthentication.getKubotaEmployeeId(),
 					(userAuthentication.getBranchId()!=null?userAuthentication.getBranchId():jcExcel.getBranch()), userAuthentication.getDealerEmployeeId(),
 					jcExcel.getJobCardNo(), jcExcel.getChassisNo(), jcExcel.getEngineNo(),
 					jcExcel.getCsbNo(), jcExcel.getBookingNo(), jcExcel.getJobCardDateFrom(),
 					jcExcel.getJobCardDateTo(), jcExcel.getCustomerName(), jcExcel.getModel(),
 					jcExcel.getRegistrationNo(), jcExcel.getMobileNo(),
 					jcExcel.getServiceCategory(), jcExcel.getPlaceOfService(),
 					jcExcel.getActivityType(), jcExcel.getActivityNo(), 0,
 					size, userAuthentication.getUsername(),jcExcel.getDealerId(),jcExcel.getFromMachineInDate(),jcExcel.getToMachineInDate(),
 					jcExcel.getProduct(),jcExcel.getSeries(),jcExcel.getSubModel(),jcExcel.getVariant(),
 					null,null,null,null,'N', jcExcel.getHierId(),jcExcel.getStatus(),jcExcel.getIsFor(), jcExcel.getPartNo()));
    	 }

		
    	ByteArrayInputStream in = ExcelCellGenerator.jobCardExcelReport(result,resultExcel, jcExcel.isFor);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "JCReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
    
    /**
     * @author suraj.gaur
     * @apiNote For getting ongoing retrofitment campaign details by chassis no.
     * @param chassisNo
     * @return ResponseEntity<?>
     */
    @GetMapping("getRetrofitmentDetails")
	public ResponseEntity<?> getRetrofitmentDetails(@RequestParam String chassisNo) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setResult(serviceJobCardRepo.getServiceRetroByChassisNo(chassisNo));
		apiResponse.setMessage("Get Ongoing Retrofitment Details successfull");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
    
    /**
     * @author suraj.gaur
     * @param retroId
     * @return ResponseEntity<?>
     */
    @GetMapping("getRetroItemAndLabourDetailsById")
	public ResponseEntity<?> getRetroItemAndLabourDetailsById(@RequestParam Long retroId) {
		ApiResponse apiResponse = new ApiResponse();
		Map<String, Object> retroData = new HashMap<>();
		
		retroData.put("retroItemDetails", serviceJobCardRepo.getRetroItemDetailById(retroId));
		retroData.put("retroLabourInfoDetails", serviceJobCardRepo.getRetroLabourInfoById(retroId));
		
		apiResponse.setResult(retroData);
		apiResponse.setMessage("Get Retrofitment Item & Labour Details successfull");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
    
    /**
     * @author suraj.gaur
     * @param retroId
     * @return ResponseEntity<?>
     */
    @GetMapping("getImplementsDetailsForChassisNo")
	public ResponseEntity<?> getImplementsDetailsForChassisNo(@RequestParam Long inventoryId) {
		ApiResponse apiResponse = new ApiResponse();
		Map<String, Object> retroData = new HashMap<>();
		
		retroData.put("implementDetails", serviceJobCardRepo.getImplementsDetailsForChassisNo(inventoryId));
		
		apiResponse.setResult(retroData);
		apiResponse.setMessage("Get Implement details successfull");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}

}