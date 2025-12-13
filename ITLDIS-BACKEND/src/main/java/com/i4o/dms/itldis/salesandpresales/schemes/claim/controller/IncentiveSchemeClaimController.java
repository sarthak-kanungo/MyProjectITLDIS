package com.i4o.dms.itldis.salesandpresales.schemes.claim.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

import com.i4o.dms.itldis.salesandpresales.schemes.claim.domain.IncentiveSchemeClaim;
import com.i4o.dms.itldis.salesandpresales.schemes.claim.domain.SchemeClaimApproval;
import com.i4o.dms.itldis.salesandpresales.schemes.claim.dto.IncentiveClaimApprovalRequest;
import com.i4o.dms.itldis.salesandpresales.schemes.claim.dto.IncentiveSchemeClaimSearchRequestDto;
import com.i4o.dms.itldis.salesandpresales.schemes.claim.dto.IncentiveSchemeClaimSearchResDto;
import com.i4o.dms.itldis.salesandpresales.schemes.claim.repository.IncentiveSchemeClaimApprovalRepo;
import com.i4o.dms.itldis.salesandpresales.schemes.claim.repository.IncentiveSchemeClaimRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/incentiveScheme/claim")
public class IncentiveSchemeClaimController {

	@Autowired
	private IncentiveSchemeClaimRepo incentiveSchemeClaimRepo;
	@Autowired
	private IncentiveSchemeClaimApprovalRepo incentiveSchemeClaimApprovalRepo;
	@Autowired
	private UserAuthentication userAuthentication;
    @Autowired
    private StorageService storageService;
	
	@GetMapping("/wholesaleIncentiveSchemeDetails")
	public ResponseEntity<?> getWholesaleIncentiveSchemeDetails(@RequestParam("month")String month){
		ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
		Map<String, Object> result = incentiveSchemeClaimRepo.getWholesaleIncentiveSchemeDetails(month, userAuthentication.getUsername());
		apiResponse.setMessage("get wholesales schemes details");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/retailsIncentiveSchemeDetails")
	public ResponseEntity<?> getRetailsIncentiveSchemeDetails(@RequestParam("month")String month){
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> result = incentiveSchemeClaimRepo.getRetailsIncentiveSchemeDetails(month, userAuthentication.getUsername());
		apiResponse.setMessage("get retails schemes details");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/saveIncentiveSchemeDetails")
	public ResponseEntity<?> saveIncentiveSchemeDetails(@RequestBody IncentiveSchemeClaim incentiveSchemeClaim){
		ApiResponse<IncentiveSchemeClaim> apiResponse = new ApiResponse<>();
		incentiveSchemeClaim.setCreatedDate(new Date());
		incentiveSchemeClaim.setCreatedBy(userAuthentication.getLoginId());
		incentiveSchemeClaimRepo.save(incentiveSchemeClaim);
		List<SchemeClaimApproval> approvals = new ArrayList<>();
		incentiveSchemeClaimApprovalRepo.getApprovalHierarchyLevel(userAuthentication.getDealerId())
                 .forEach(designationHierarchy -> {
                	 SchemeClaimApproval approval = new SchemeClaimApproval();
                     approval.setClaimId(incentiveSchemeClaim.getId());
                     approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
                     approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
                     approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
                     approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
                     approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
                     approval.setRejectedFlag('N');
                     approvals.add(approval);
                 });
		incentiveSchemeClaimApprovalRepo.saveAll(approvals);
		apiResponse.setMessage("save scheme claim details");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(incentiveSchemeClaim);
        return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/viewIncentiveSchemeDetails")
	public ResponseEntity<?> viewIncentiveSchemeDetails(@RequestParam Long id){
		ApiResponse<IncentiveSchemeClaim> apiResponse = new ApiResponse<>();
		IncentiveSchemeClaim claim = incentiveSchemeClaimRepo.getOne(id);
		if(claim!=null){
			if(userAuthentication.getDealerId()==null){
	        	claim.setApprovalDetails(incentiveSchemeClaimApprovalRepo.getApprovalHierDetails(id, userAuthentication.getUsername()));
	        }
			apiResponse.setMessage("scheme claim details fetched");
	        apiResponse.setStatus(HttpStatus.OK.value());
	        apiResponse.setResult(claim);
	    }
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/searchIncentiveSchemeClaim")
    public ResponseEntity<?> searchIncentiveSchemeClaim(@RequestBody IncentiveSchemeClaimSearchRequestDto request){
        ApiResponse apiResponse = new ApiResponse();
        List<IncentiveSchemeClaimSearchResDto> result = incentiveSchemeClaimRepo.searchIncentiveSchemes(request.getDealerId(), 
        		request.getClaimNo(),
        		request.getFromDate(),
        		request.getToDate(),
        		userAuthentication.getLoginId(),
        		request.getPage(),
        		request.getSize());
        Long count = 0l;
        if(result!=null){
        	count = result.get(0).getRecordsCount();
        }
        apiResponse.setMessage("get incentive schemes list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setCount(count);
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);    	
    }
	 
    @GetMapping("/getIncentiveSchemesClaimNo")
    public ResponseEntity<?> getSchemeClaimNo(@RequestParam("searchValue")String searchValue){
    	ApiResponse apiResponse = new ApiResponse();
    	List<Map<String, Object>> result = incentiveSchemeClaimRepo.getSchemeClaimNo(searchValue, userAuthentication.getUsername());
    	apiResponse.setMessage("get incentive schemes claim no");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/approveIncentiveClaim")
    public ResponseEntity<?> approveIncentiveClaim(@RequestBody IncentiveClaimApprovalRequest request) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(incentiveSchemeClaimApprovalRepo.approveClaim(request.getClaimId(), userAuthentication.getKubotaEmployeeId(), request.getKaiRemarks(), userAuthentication.getUsername(), request.getApprovalStatus()));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);

    }
    
    @GetMapping("/generateInvoice")
	public ResponseEntity<?> generateInvoice(@RequestParam Long id){
		ApiResponse<Map<String,Object>> apiResponse = new ApiResponse<>();
		Map<String,Object> result = incentiveSchemeClaimRepo.generateInvoice(id, userAuthentication.getLoginId());
		apiResponse.setMessage("incentive scheme claim invoice generated");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
		return ResponseEntity.ok(apiResponse);
	}
    
    @PostMapping(value = "/invoiceUpload", consumes = { "multipart/form-data" })
   	public ResponseEntity<?> invoiceUpload(@RequestPart(value = "invoiceData") Map<String,Object> invoiceData,
   			@RequestPart(value = "attachedFile", required = false) MultipartFile attachedFile) {
		ApiResponse apiResponse = new ApiResponse();
		
		if (attachedFile != null) {
			Integer claimId = (Integer) invoiceData.get("claimId");
			String invoiceFileName = attachedFile.getOriginalFilename().replaceAll(" ", "_");
			String photoName = "IncentiveSchemeInvoice_" + System.currentTimeMillis() + "_" + invoiceFileName;
			try{
				incentiveSchemeClaimRepo.uploadInvoice(photoName, claimId, userAuthentication.getLoginId());
				storageService.store(attachedFile, photoName);
				apiResponse.setStatus(HttpStatus.OK.value());
				apiResponse.setMessage("Invoice Copy uploaded Succesfully");
			}catch(Exception ex){
				ex.printStackTrace();
			apiResponse.setMessage("Error while uploading Invoice Copy");
			}
		}else{
			apiResponse.setMessage("Invoice Copy not found");
		}
	    return ResponseEntity.ok(apiResponse);
	}
    
    @GetMapping(value = "invoiceVerify")
    public ResponseEntity<?> invoiceVerify(@RequestParam Long id)  {
        ApiResponse apiResponse=new ApiResponse();
        incentiveSchemeClaimRepo.verifyInvoice(id, userAuthentication.getLoginId());
        
        List<SchemeClaimApproval> approvals = new ArrayList<>();
		incentiveSchemeClaimApprovalRepo.getApprovalHierarchyLevel(userAuthentication.getDealerId())
                 .forEach(designationHierarchy -> {
                	 SchemeClaimApproval approval = new SchemeClaimApproval();
                     approval.setClaimId(id);
                     approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
                     approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
                     approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
                     approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
                     approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
                     approval.setRejectedFlag('N');
                     approvals.add(approval);
                 });
		incentiveSchemeClaimApprovalRepo.saveAll(approvals);
		
        apiResponse.setMessage("Invoice Verified Successfuly");
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value="/invoiceDownload")
    public void invoiceDownload(@RequestParam Long id, HttpServletResponse response) throws IOException{
    	String filename = incentiveSchemeClaimRepo.getInvoiceFileName(id);
    	ServletOutputStream outputStream;
    	if(filename!=null){
	    	Resource file = storageService.loadAsResource(filename);
	    	InputStream is = file.getInputStream();
	    	response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + filename);
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
			outputStream = response.getOutputStream();
			IOUtils.copy(is, outputStream);
			outputStream.flush();
			is.close();
	    }
    }
    
}
