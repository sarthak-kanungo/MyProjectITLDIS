package com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.domain.CallSearchRequest;
import com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.domain.TollFreeFileUplaod;
import com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.domain.TollFreeHdr;
import com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.dto.TollFreeCallSearchResponseDto;
import com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.repo.TollFreeRepo;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.repository.CustomerMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.storage.StorageService;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping("/api/crm/crmmodule/tollFreeCall")
public class TollFreeController {
	
	@Autowired
	private TollFreeRepo tollFreeRepo;
	@Autowired
	private UserAuthentication userAuthentication;
	@Autowired
	private CustomerMasterRepo customerMasterRepo;
	@Autowired
	private DealerMasterRepo dealerMasterRepo;
	@Autowired
	private StorageService storageService;
	@Autowired
	private DealerEmployeeMasterRepo dealerRepo;
	
	@PostMapping(value="/create")
	public ResponseEntity<?> create(@RequestPart TollFreeHdr callData, @RequestPart List<MultipartFile> complaintRecording){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		//callData.setDealerId(userAuthentication.getDealerId());
		callData.setCreatedBy(userAuthentication.getLoginId());
		callData.setCreatedDate(new Date());
		if(callData.getComplaintDtlList()!=null){
			callData.setComplaintDtlList(callData.getComplaintDtlList().stream().filter(comp -> comp.getComplaintType()!=null).collect(Collectors.toList()));
			callData.getComplaintDtlList().forEach(comp -> {
				comp.setComplaintNumber("COMP/"+System.currentTimeMillis());
			});
		}
		List<TollFreeFileUplaod> uploads = new ArrayList<>();
		if(complaintRecording!=null){
			complaintRecording.forEach(recording -> {
				TollFreeFileUplaod fileUpload = new TollFreeFileUplaod();
				
				String fileName = recording.getOriginalFilename();
				String recordingName = "toll_free_call_" + System.currentTimeMillis() + "_" + fileName;
				storageService.store(recording, recordingName);
				
				fileUpload.setContentType(recording.getContentType());
				fileUpload.setTollFreeCallHdr(callData);
				fileUpload.setFileName(recordingName);
				fileUpload.setOriginalName(fileName);
				fileUpload.setCreatedDate(new Date());
				uploads.add(fileUpload);
			});
		}
		callData.setFileUploadList(uploads);
		tollFreeRepo.save(callData);
		apiResponse.setMessage("Toll Free Record Saved Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value="getCallHistory")
	public ResponseEntity<?> getCallHistory(@RequestParam(value="customerId")Long customerId,
			@RequestParam(value="vinId")Long vinId){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> callHistory = tollFreeRepo.getCallHistory(customerId, vinId);
		apiResponse.setResult(callHistory);
		apiResponse.setMessage("CCE CALL Histroy Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value="getDealerDetails")
	public ResponseEntity<?> getDealerDetails(@RequestParam(value="id")Integer id){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		Map<String, Object> tsmDetails = tollFreeRepo.getTsmDetails(null, id);
		apiResponse.setResult(tsmDetails);
		apiResponse.setMessage("TSM Details Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value="getTsmDetails")
	public ResponseEntity<?> getTsmDetails(@RequestParam(value="pincode")String pincode){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		Map<String, Object> tsmDetails = tollFreeRepo.getTsmDetails(pincode,null);
		apiResponse.setResult(tsmDetails);
		apiResponse.setMessage("TSM Details Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping(value="search")
	public ResponseEntity<?> getCallSearch(@RequestBody CallSearchRequest request){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<TollFreeCallSearchResponseDto> callList = tollFreeRepo.getCallSearch(userAuthentication.getDealerId(), request.getFromDate(),
				request.getToDate(), request.getMobileNo(), request.getPage(), request.getSize(), userAuthentication.getUsername());
		Long count=0l;
		if(callList!=null && !callList.isEmpty()){
			count = callList.get(0).getTotalCount();
		}
		apiResponse.setResult(callList);
		apiResponse.setCount(count);
		apiResponse.setMessage("Toll Free CALL Search Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping(value="fetchCallDetails")
	public ResponseEntity<?> fetchCallDetails(@RequestParam(value="callId")Long callId){
		TollFreeHdr tollFreeCallHdr = tollFreeRepo.getOne(callId);
		if(tollFreeCallHdr.getCustomerMasterId()!=null){
			String customerCode = customerMasterRepo.getCustomerCodeById(tollFreeCallHdr.getCustomerMasterId());
			Map<String, Object> customerDetails = customerMasterRepo.getcustomerDetails(customerCode);
			tollFreeCallHdr.setCustomerDetails(customerDetails);
		}
		if(tollFreeCallHdr.getPinId()!=null){
			Map<String, Object> pinDetails = dealerMasterRepo.getPincodeDetailByPincodeId(0L, tollFreeCallHdr.getPinId());
			Map<String, Object> tsmDetails = null;
			Map<String, Object> enquiryDetails = new HashMap<>();
			if(pinDetails!=null){
				if(tollFreeCallHdr.getDealerId()!=null)
					tsmDetails = tollFreeRepo.getTsmDetails(null, tollFreeCallHdr.getDealerId().intValue());
				else
					tsmDetails = tollFreeRepo.getTsmDetails(pinDetails.get("pinCode").toString(), null);
				enquiryDetails.put("pinCode",pinDetails.get("pinCode"));
				enquiryDetails.put("city",pinDetails.get("city"));
				enquiryDetails.put("tehsil",pinDetails.get("tehsil"));
				enquiryDetails.put("district",pinDetails.get("district"));
				enquiryDetails.put("state",pinDetails.get("state"));
				enquiryDetails.put("country",pinDetails.get("country"));
				enquiryDetails.put("postOffice",pinDetails.get("postOffice"));
				enquiryDetails.put("pinID",pinDetails.get("pinID"));
			}
			if(tsmDetails!=null){	
				enquiryDetails.put("tsm",tsmDetails.get("tsm"));
				enquiryDetails.put("tsmContactNo",tsmDetails.get("tsmContactNo"));
				enquiryDetails.put("dealerName",tsmDetails.get("dealerName"));
				enquiryDetails.put("dealerLocation",tsmDetails.get("dealerLocation"));
				enquiryDetails.put("dealerContactNo",tsmDetails.get("dealerContactNo"));
			}
			tollFreeCallHdr.setEnquiryDetails(enquiryDetails);
		}
		if(tollFreeCallHdr.getComplaintDtlList()!=null){
			tollFreeCallHdr.getComplaintDtlList().forEach(comp -> {
				if(comp.getHoUserId()!=null){
					comp.setAssignTo(tollFreeRepo.getHoUserNameById(comp.getHoUserId()));
				}
			});
		}
		if(tollFreeCallHdr.getDealerId()!=null){
			String dealerCode = dealerRepo.getDealerDetailsById(tollFreeCallHdr.getDealerId()).get("dealerCode").toString();
			tollFreeCallHdr.setDealerCode(dealerCode);
		}
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		apiResponse.setResult(tollFreeCallHdr);
		apiResponse.setMessage("Toll Free CALL Details Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
}
