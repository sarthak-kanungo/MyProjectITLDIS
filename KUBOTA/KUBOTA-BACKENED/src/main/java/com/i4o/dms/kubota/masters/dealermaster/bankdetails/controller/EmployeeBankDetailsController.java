package com.i4o.dms.kubota.masters.dealermaster.bankdetails.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.i4o.dms.kubota.masters.dealermaster.bankdetails.repository.EmployeeBankDetailsRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.dealermaster.bankdetails.dto.EmployeeBankDetailSearchDto;
import com.i4o.dms.kubota.masters.dealermaster.bankdetails.dto.EmployeeBankDetailSearchResponse;
import com.i4o.dms.kubota.masters.dealermaster.bankdetails.dto.EmployeeBankDetailsDto;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping(value = "/api/employeeBankDetails")
public class EmployeeBankDetailsController {
	
	@Autowired
	private EmployeeBankDetailsRepo employeeBankDetailsRepo;
	
	@Autowired
	private UserAuthentication userAuthentication;
	
	@GetMapping("getDealerEmployeeByCodeOrMob")
	public ResponseEntity<?> getDealerEmployeeByCodeOrMob(@RequestParam String dealerEmpCode, @RequestParam String dealerEmpMob) {
		ApiResponse apiResponse = new ApiResponse();
 		apiResponse.setMessage("Employee Details get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		Map<String , Object> viewEmpDetails = employeeBankDetailsRepo.getDealerEmployeeByCodeOrMob(dealerEmpCode, dealerEmpMob);

		apiResponse.setResult(viewEmpDetails);
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping(value="/updateEmployeeBankDetails")
	public ResponseEntity updateEmployeeBankDetails(@RequestBody EmployeeBankDetailsDto emplyeeBankDetails) {
		ApiResponse apiResponse = new ApiResponse();
		try {
//			DealerEmployeeMaster empMasterUpdate = employeeBankDetailsRepo.getEmpIdByCode(emplyeeBankDetails.getEmployeeCode());
//			empMasterUpdate.setId(empMasterUpdate.getId());
//			empMasterUpdate.setBankAccountNo(emplyeeBankDetails.getBankAccountNo());
//			empMasterUpdate.setBankBranch(emplyeeBankDetails.getBankBranch());
//			empMasterUpdate.setBankName(emplyeeBankDetails.getBankName());
//			empMasterUpdate.setIfsCode(emplyeeBankDetails.getIfsCode());
//			empMasterUpdate.setApprovalStatus("Waiting for Approval");
//			empMasterUpdate.setLastModifiedDate(new Date());
//			empMasterUpdate.setLastModifiedBy(userAuthentication.getLoginId());
//			employeeBankDetailsRepo.save(empMasterUpdate);
			
			Integer updated = employeeBankDetailsRepo.updateEmployeeBankDetailsById(
					emplyeeBankDetails.getEmployeeCode(), emplyeeBankDetails.getBankAccountNo(), 
					emplyeeBankDetails.getBankBranch(), emplyeeBankDetails.getBankName(), 
					emplyeeBankDetails.getIfsCode(), "Waiting for Approval", 
					new Date(), userAuthentication.getLoginId());
			
			if(updated == 1) {
				apiResponse.setMessage("Employee Bank Details Submit Successful");
				apiResponse.setStatus(HttpStatus.OK.value());
			}
			else {
				apiResponse.setMessage("Employee Bank Details Submit Failed");
				apiResponse.setStatus(HttpStatus.CONFLICT.value());
			}
		} catch (Exception e) {
				e.printStackTrace();
				apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
				apiResponse.setMessage("Employee Bank Details can't Submitted");
		}
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/searchEmployeeBankDetail")
	public ResponseEntity<?> searchEmployeeBankDetail(@RequestBody EmployeeBankDetailSearchDto employeeBankDetailSearchDto) {
		ApiResponse apiResponse = new ApiResponse();
		
		List<EmployeeBankDetailSearchResponse> result = employeeBankDetailsRepo.searchEmployeeBankDetails(
				userAuthentication.getUsername(),employeeBankDetailSearchDto.getEmployeeCode(),
				employeeBankDetailSearchDto.getMobileNo(), employeeBankDetailSearchDto.getEmployeeName(), 
				employeeBankDetailSearchDto.getDesignation(), employeeBankDetailSearchDto.getDepartmentName(),
				employeeBankDetailSearchDto.getPage(), employeeBankDetailSearchDto.getSize());
		
		Long count=0L;
		if (result!= null && result.size()>0) {
			count = result.get(0).getCount();
		}
		
		apiResponse.setResult(result);
		apiResponse.setMessage("data get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setCount(count);
		return ResponseEntity.ok(apiResponse);
	}
}
