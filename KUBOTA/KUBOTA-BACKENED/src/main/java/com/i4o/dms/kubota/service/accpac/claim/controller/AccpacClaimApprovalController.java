package com.i4o.dms.kubota.service.accpac.claim.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.accpac.claim.model.AccpacClaimApprovalSearchModel;
import com.i4o.dms.kubota.service.accpac.claim.model.AccpacClaimAprvlHdrEntity;
import com.i4o.dms.kubota.service.accpac.claim.model.AccpacClaimAprvlSearchDto;
import com.i4o.dms.kubota.service.accpac.claim.model.ManagementApprovalModel;
import com.i4o.dms.kubota.service.accpac.claim.model.WcrClaimApprovalRequestBody;
import com.i4o.dms.kubota.service.accpac.claim.repository.AccpacClaimApprovalRepository;
import com.i4o.dms.kubota.service.claim.repo.ServiceClaimRepo;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/service/accpac")
public class AccpacClaimApprovalController {

	@Autowired
	private UserAuthentication userAuthentication;
	@Autowired
	private AccpacClaimApprovalRepository approvalRepo;
	
	@GetMapping("/claimSearchForApproval")
    public ResponseEntity<?> claimSearchForApproval(@RequestParam String requestFrom) {
        ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<List<Map<String,Object>>>();
        
        List<Map<String, Object>> o2 = approvalRepo.claimSearchForApproval(requestFrom, userAuthentication.getUsername(),
        		null,null,null,null,null,null,null);
        
        apiResponse.setMessage("Claim Details get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(o2);
        return ResponseEntity.ok(apiResponse);
    }
	
	@PostMapping("/claimSearchForApproval")
    public ResponseEntity<?> claimSearchForApprovalPost(@RequestBody WcrClaimApprovalRequestBody requestBody) {
        ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<List<Map<String,Object>>>();
        
        List<Map<String, Object>> o2 = approvalRepo.claimSearchForApproval(requestBody.getRequestFrom(), userAuthentication.getUsername(),
        		requestBody.getClaimNo(),requestBody.getPcrNumber(),requestBody.getWcrType(),requestBody.getJobcardNumber(),
        		requestBody.getFromDate(),requestBody.getToDate(), requestBody.getDealerId());
        
        apiResponse.setMessage("Claim Details get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(o2);
        return ResponseEntity.ok(apiResponse);
    }
	
	@GetMapping("/viewManagementApprovedClaim")
    public ResponseEntity<?> viewManagementApprovedClaim(@RequestParam String requestFrom,
    		@RequestParam Long id
    		) {
        ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<List<Map<String,Object>>>();
        
        List<Map<String, Object>> o2 = approvalRepo.viewManagementApprovedClaim(requestFrom, id);
        
        apiResponse.setMessage("Claim Details get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(o2);
        return ResponseEntity.ok(apiResponse);
    }
	
	@PostMapping("/submitClaimForApproval")
    public ResponseEntity<?> submitClaimForApproval(@RequestBody AccpacClaimAprvlHdrEntity request) {
        ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<List<Map<String,Object>>>();
        request.setCreatedBy(userAuthentication.getLoginId());
        request.setDocDate(new Date());
        request.setCreatedOn(new Date());
        approvalRepo.save(request);
        
        approvalRepo.insertClaimApprovalHierarchyLevel(request.getDocType(), request.getId());
        
        apiResponse.setMessage("Claim Approval Details saved successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
	@PostMapping("/claimManagementApproval")
	public ResponseEntity<?> searchClaimSubmittedApproval(@RequestBody ManagementApprovalModel request) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<List<Map<String, Object>>>();
        approvalRepo.claimApproval(request.getClaimType(), request.getClaimId(), userAuthentication.getKubotaEmployeeId(),
        		request.getRemark(), userAuthentication.getUsername(), request.getApprovalType());
        
        apiResponse.setMessage("Claim Management Approval done successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
	
	@PostMapping("/searchClaimSubmittedApproval")
    public ResponseEntity<?> searchClaimSubmittedApproval(@RequestBody AccpacClaimApprovalSearchModel request) {
        ApiResponse<List<AccpacClaimAprvlSearchDto>> apiResponse = new ApiResponse<List<AccpacClaimAprvlSearchDto>>();
        List<AccpacClaimAprvlSearchDto> list = approvalRepo.searchClaimSubmittedApproval(request.getFromDate(), request.getToDate(), request.getClaimType(),
        		request.getStatus(), request.getPage(), request.getSize(), userAuthentication.getUsername());
        Long count = 0l;
        if(list!=null && list.size()>0){
        	count = list.get(0).getRecordCount();
        }
        apiResponse.setCount(count);
        apiResponse.setMessage("Claim Period get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(list);
        return ResponseEntity.ok(apiResponse);
    }
	
	/**
	 * @author suraj.gaur
	 */
	@GetMapping(value = "/getWarrantyClaimHierarchyById/{id}")
	public ResponseEntity<?> getWarrantyClaimHierarchyById(@PathVariable Long id) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<List<Map<String, Object>>>();
        List<Map<String, Object>> approvalDetails=approvalRepo.getApprovalHierarchyDetails(id);
        
        apiResponse.setMessage("Claim Period get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(approvalDetails);
        return ResponseEntity.ok(apiResponse);
    }
}
