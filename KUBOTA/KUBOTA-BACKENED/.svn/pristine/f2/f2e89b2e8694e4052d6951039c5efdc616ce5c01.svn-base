package com.i4o.dms.kubota.masters.dealermaster.bankapproval.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.masters.dealermaster.bankapproval.repository.EmployeeBankApprovalRepo;
import com.i4o.dms.kubota.masters.dealermaster.bankapproval.dto.EmployeeBankApprovalSearchDto;
import com.i4o.dms.kubota.masters.dealermaster.bankapproval.dto.EmployeeBankProposalApproval;
import com.i4o.dms.kubota.masters.dealermaster.bankapproval.dto.EmployeeBankProposalApprovalRequest;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping(value = "/api/employeeBankApproval")
public class EmployeeBankApprovalController {
	
	@Autowired
	private EmployeeBankApprovalRepo employeeBankApprovalRepo;
	
	@Autowired
	private UserAuthentication userAuthentication;
	
	@PostMapping("/searchEmployeeBankApproval")
	public ResponseEntity<?> searchEmployeeBankApproval(@RequestBody EmployeeBankApprovalSearchDto employeeBankApprovalSearchDto) {
		ApiResponse apiResponse = new ApiResponse();
		
		//List<EmployeeBankApprovalSearchResponse>
		List<Map<String, Object>> result = employeeBankApprovalRepo.searchEmployeeBankApproval(
				userAuthentication.getUsername(), employeeBankApprovalSearchDto.getDealerId(),
				employeeBankApprovalSearchDto.getEmployeeCode(), employeeBankApprovalSearchDto.getEmployeeName(), 
				employeeBankApprovalSearchDto.getFromDate(), employeeBankApprovalSearchDto.getToDate(),
				employeeBankApprovalSearchDto.getPage(), employeeBankApprovalSearchDto.getSize());
		
		Long count = 0L;
		if (result!= null && result.size()>0) {
			count = ((Integer) result.get(0).get("count")).longValue();
			//count = result.get(0).getCount();
		}
		
		apiResponse.setResult(result);
		apiResponse.setMessage("Bank Approval Data Get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setCount(count);
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/employeeBankGroupApproval")
    public ResponseEntity<?> employeeBankGroupApproval(@Valid @RequestBody EmployeeBankProposalApprovalRequest request) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        
        ResponseEntity<?> entity = null;
        
        List<EmployeeBankProposalApproval> employeeBankProposalApprovalList = request.getEmployeeBankProposalApprovalList();
        
        employeeBankProposalApprovalList.stream().
        filter(employeeBankProposalApproval -> employeeBankProposalApproval.getIsSelect() != null 
        && employeeBankProposalApproval.getIsSelect()).forEach(employeeBankProposalApproval -> {
        	employeeBankApprovalRepo.approveEmployeeBankDetails(
                   userAuthentication.getKubotaEmployeeId(), employeeBankProposalApproval.getDealerEmployeeId(),
                   userAuthentication.getUsername(),request.getApprovalType(),
                   request.getRemark());
        	});
        
        apiResponse.setMessage(request.getApprovalType()+ " successfully" );
        apiResponse.setStatus(HttpStatus.OK.value());
        entity = ResponseEntity.ok(apiResponse);
        return entity;
    }
	
}
