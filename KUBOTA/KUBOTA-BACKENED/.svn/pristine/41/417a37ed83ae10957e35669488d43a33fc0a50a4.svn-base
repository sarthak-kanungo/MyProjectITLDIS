package com.i4o.dms.kubota.crm.crmmodule.complaintResolution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.crm.crmmodule.complaintResolution.domain.ComplaintResolutionRequest;
import com.i4o.dms.kubota.crm.crmmodule.complaintResolution.domain.ComplaintResolutionSearchRequest;
import com.i4o.dms.kubota.crm.crmmodule.complaintResolution.dto.ComplaintResolutionResponseDto;
import com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.repo.TollFreeRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping("/api/crm/crmmodule/complaintOrQueryResolution")
public class ComplaintResolutionController {

	@Autowired
	private UserAuthentication userAuthentication;
	@Autowired
	private TollFreeRepo tollFreeRepo;
	
	@PostMapping(value="getComplaintOrQueryResolution")
	public ResponseEntity<?> getCallSearch(@RequestBody ComplaintResolutionSearchRequest request){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<ComplaintResolutionResponseDto> callList = tollFreeRepo.getComplaintResoltionSearchSearch(userAuthentication.getDealerId(), request.getFromDate(),
				request.getToDate(), request.getStatus(), request.getComplaintType(), request.getDepartment(), request.getPage(), request.getSize(), userAuthentication.getUsername());
		Long count=0l;
		if(callList!=null && !callList.isEmpty()){
			count = callList.get(0).getTotalCount();
		}
		apiResponse.setResult(callList);
		apiResponse.setCount(count);
		apiResponse.setMessage("ComplaintResolution Search Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}

	
	@PostMapping(value="updateResolutionDetails")
	public ResponseEntity<?> updateResolutionDetails(@RequestBody ComplaintResolutionRequest request){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		tollFreeRepo.updateComplaintResolutionDetails(request.getComplaintId(),
				request.getComplaintType(),
				request.getReasonForDelayFrt(),
				request.getReasonForDelayFrt(),
				request.getActionTaken(),
				request.getIsInvalid(),
				userAuthentication.getLoginId());
		apiResponse.setMessage("ComplaintResolution update Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}

	
}
