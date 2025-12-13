package com.i4o.dms.kubota.service.claim.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.claim.domain.ClaimSearchRequestModel;
import com.i4o.dms.kubota.service.claim.domain.ServiceClaimApprovalEntity;
import com.i4o.dms.kubota.service.claim.domain.ServiceClaimApprovalRequestModel;
import com.i4o.dms.kubota.service.claim.domain.ServiceClaimHdrEntity;
import com.i4o.dms.kubota.service.claim.dto.ClaimSearchDto;
import com.i4o.dms.kubota.service.claim.dto.ServiceClaimDetailExcelReport;
import com.i4o.dms.kubota.service.claim.dto.ServiceClaimSummeryExcelReport;
import com.i4o.dms.kubota.service.claim.repo.ServiceClaimApprovalRepo;
import com.i4o.dms.kubota.service.claim.repo.ServiceClaimRepo;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.ExcelCellGenerator;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/service/claim")
public class ServiceClaimController {

	@Autowired
	private UserAuthentication userAuthentication;
	@Autowired
	private ServiceClaimRepo claimRepor;
	@Autowired
	private ServiceClaimApprovalRepo approvalRepo;
	
	@GetMapping("/getClaimPeriod")
    public ResponseEntity<?> getClaimPeriod() {
        ApiResponse<Map<String,Object>> apiResponse = new ApiResponse<Map<String,Object>>();
        
        Map<String, Object> o1 = claimRepor.getClaimPeriod(userAuthentication.getDealerId());
        
        List<Map<String, Object>> o2 = claimRepor.getProductAndService(userAuthentication.getDealerId(), o1.get("fromDate").toString(), o1.get("toDate").toString());
        
        List<Map<String, Object>> o4 = claimRepor.getDocumentDetails(userAuthentication.getDealerId(), o1.get("fromDate").toString(), o1.get("toDate").toString());
        
        Map<String, Object> o3 = new HashMap<String, Object>();
        o3.put("Header", o1);
        o3.put("Product", o2);
        o3.put("Documents", o4);
        
        apiResponse.setMessage("Claim Period get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(o3);
        return ResponseEntity.ok(apiResponse);
    }
	
	@GetMapping("/claimNumberSearch")
    public ResponseEntity<?> claimNumberSearch(@RequestParam String searchText) {
        ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<List<Map<String,Object>>>();
        
        List<Map<String, Object>> o2 = claimRepor.claimNumberSearch(searchText, userAuthentication.getUsername());
        
        apiResponse.setMessage("Claim Period get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(o2);
        return ResponseEntity.ok(apiResponse);
    }
	
	@GetMapping("/view")
    public ResponseEntity<?> viewClaim(@RequestParam Long id) {
        ApiResponse<Map<String,Object>> apiResponse = new ApiResponse<Map<String,Object>>();
        
        List<Map<String, Object>> o1 = claimRepor.getClaimDetailsForView(id);
        List<Map<String, Object>> o2 = claimRepor.getProductAndServiceForView(id);
        List<Map<String, Object>> o3 =  claimRepor.getDocumentDetailsForView(id);
        
        Map<String, Object> o4 = new HashMap<String, Object>();
        o4.put("Header", o1);
        o4.put("Product", o2);
        o4.put("Documents", o3);
        
        apiResponse.setMessage("Claim Details get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(o4);
        return ResponseEntity.ok(apiResponse);
    }
	
	@PostMapping("/create")
    public ResponseEntity<?> saveClaim(@RequestBody ServiceClaimHdrEntity claim) {
        ApiResponse<Map<String,Object>> apiResponse = new ApiResponse<Map<String,Object>>();
        claim.setClaimStatus("Claim Approval Pending");
        claim.setDealerId(userAuthentication.getDealerId());
        claim.setCreatedBy(userAuthentication.getLoginId());
        claimRepor.save(claim);
        List<ServiceClaimApprovalEntity> approvalList = new ArrayList<ServiceClaimApprovalEntity>();
        approvalRepo.getClaimApprovalHierarchyLevel(userAuthentication.getDealerId())
        .forEach(map -> {
        	ServiceClaimApprovalEntity approval = new ServiceClaimApprovalEntity();
            approval.setServiceClaimId(claim.getId());
            approval.setApproverLevelSeq((Integer)map.get("approver_level_seq"));
            approval.setDesignationLevelId((BigInteger)map.get("designation_level_id"));
            approval.setGrpSeqNo((Integer)map.get("grp_seq_no"));
            approval.setIsfinalapprovalstatus((Character)map.get("isFinalApprovalStatus"));
            approval.setApprovalStatus((String)map.get("approvalStatus"));
            approval.setRejectedFlag('N');
            approvalList.add(approval);
        });
        approvalRepo.saveAll(approvalList);

        apiResponse.setMessage("Claim Generated Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/approval")
    public ResponseEntity<?> claimApproval(@RequestBody ServiceClaimApprovalRequestModel claim) {
        ApiResponse<Map<String,Object>> apiResponse = new ApiResponse<Map<String,Object>>();
        String msg = approvalRepo.claimApproval(claim.getClaimId(), userAuthentication.getKubotaEmployeeId(), claim.getRemarks(), userAuthentication.getUsername(), claim.getApprovalStatus());
        if(msg!=null && msg.startsWith("Approved")){
        	/*if(claim.getDocumentsId()!=null){
        		int length = claim.getDocumentsId().size();
        		int mod = length%2000;
        		int loop = length/2000;

    			int fromIndex = 0;
        		int toIndex = 2000;
        		
        		if(loop>0){
        			for(int i=1; i<=loop; i++){
        				
        				approvalRepo.updateApprovedAmount(claim.getDocumentsId().subList(fromIndex, toIndex), new Date());
        				fromIndex = toIndex;
        				toIndex = toIndex * (1+1);
        			}
        		}
        		if(mod>0){
        			approvalRepo.updateApprovedAmount(claim.getDocumentsId().subList(fromIndex, length), new Date());
        		}
	        }*/

            claim.getRejectionData().forEach(map -> {
            	approvalRepo.updateDtl(!(Boolean)map.get("selection"),(String) map.get("rejectionReason"),(String) map.get("remark"), (Integer) map.get("id"), new Date());
            });
            
        }
        apiResponse.setMessage(msg);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/fetchClaimList")
    public ResponseEntity<?> fetchClaimList(@RequestBody ClaimSearchRequestModel claim) {
        ApiResponse<List<ClaimSearchDto>> apiResponse = new ApiResponse<List<ClaimSearchDto>>();
        List<ClaimSearchDto> list = claimRepor.getClaimSearchResult(userAuthentication.getUsername(), 
        		claim.getFromDate(), 
        		claim.getToDate(), 
        		claim.getClaimNumber(), 
        		claim.getClaimStatus(),
        		claim.getPage(),
        		claim.getSize(),
        		claim.getHierId());		//Suraj_02-11-2023
        if(list!=null && list.size()>0){
        	apiResponse.setCount(list.get(0).getTotalCount());	
        }else{
        	apiResponse.setCount(0L);
        }
        apiResponse.setResult(list);
        apiResponse.setMessage("Claim List Fetched Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
	}
	
	//Suraj-07/11/2022
	@PostMapping("/downloadServiceClaimExcelReport")
	public ResponseEntity<InputStreamResource> serviceClaimExcelReport(@RequestBody ClaimSearchRequestModel claim, HttpServletResponse response) throws IOException {
		List<ServiceClaimSummeryExcelReport> SummeryList = claimRepor.getServiceClaimSummeryExcelReport(
				userAuthentication.getUsername(),
        		claim.getClaimNumber(), 
        		claim.getClaimStatus(),
				claim.getFromDate(), 
        		claim.getToDate(), 
        		"SUMMARY");
		
		List<ServiceClaimDetailExcelReport> DetailList = claimRepor.getServiceClaimDetailExcelReport(
				userAuthentication.getUsername(),
        		claim.getClaimNumber(), 
        		claim.getClaimStatus(),
				claim.getFromDate(), 
        		claim.getToDate(), 
        		"DETAIL"
				);
		
		ByteArrayInputStream in = ExcelCellGenerator.serviceClaimExcelReport(SummeryList, DetailList);
		
		response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "Service_Claim_Report_" + (Calendar.getInstance()).getTimeInMillis() + ".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = " + filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
	/**
     * @author suraj.gaur
     * @return
     */
    @GetMapping(value = "/getClaimPendingsForApproval")
    public ResponseEntity<?> getClaimPendingsForApproval(){
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        
        List<Map<String, Object>> result = claimRepor.getClaimPendingsForApproval(userAuthentication.getUsername());
        apiResponse.setResult(result);
        
        return ResponseEntity.ok(apiResponse);
    }
}
