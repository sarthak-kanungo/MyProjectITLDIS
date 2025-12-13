package com.i4o.dms.itldis.masters.kaicommonmaster.transactionHierManagement.controller;

import static com.i4o.dms.itldis.configurations.Constants.YES;
import static com.i4o.dms.itldis.configurations.Constants.NO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

import com.i4o.dms.itldis.masters.kaicommonmaster.transactionHierManagement.dto.TranHierSearchRequestDto;
import com.i4o.dms.itldis.masters.kaicommonmaster.transactionHierManagement.dto.TranHierUpdateRequestDto;
import com.i4o.dms.itldis.masters.kaicommonmaster.transactionHierManagement.service.TranHierManagementService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * @author suraj.gaur
 */
@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/master/kaicommonmaster/mngmtHierarchy")
public class TranHierManagementController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private TranHierManagementService managementService;
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getIfFinalApprover")
	public ResponseEntity<?> getIfFinalApprover()
	{
		try {
			ApiResponse<List<String>> apiResponse = new ApiResponse<>();
			
			List<String> claimType = new ArrayList<>();
			claimType.add(YES);
			claimType.add(NO);
			
			apiResponse.setResult(claimType);
			apiResponse.setMessage("Get Final Approver Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/getIfFinalApprover Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param transName
	 * @return ResponseEntity<?> 
	 */
	@PostMapping("/updateTranHierSeq")
	public ResponseEntity<?> updateHierarchySeq(@RequestBody TranHierUpdateRequestDto requestDto)
	{
		try {
			ApiResponse<?> apiResponse = managementService.updateHierarchySeq(requestDto.getApprovalFlowMasters());
			apiResponse.setMessage("Update Hierarchy Sequence Successful !!");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/updateTranHierSeq Exception: " + e.getMessage());
//			logger.error(e.getMessage(), e);	//Logging stack trace 
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param transName
	 * @return ResponseEntity<?> 
	 */
	@GetMapping("/autoGetTransName")
	public ResponseEntity<?> autoGetTransName(@RequestParam String transName)
	{
		try {
			ApiResponse<?> apiResponse = managementService.autoGetTransName(transName);
			apiResponse.setMessage("Auto Get Transaction Name Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/autoGetTransName Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param hierSearchDto
	 * @return ResponseEntity<?> 
	 */
	@PostMapping("/tranHierManagementSearch")
	public ResponseEntity<?> tranHierManagementSearch(@RequestBody TranHierSearchRequestDto hierSearchDto)
	{
		try {
			ApiResponse<?> apiResponse = managementService.tranHierManagementSearch(hierSearchDto);
			apiResponse.setMessage("Transaction Hierarchy Search Successful");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/tranHierManagementSearch Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param desigStr
	 * @return ResponseEntity<?> 
	 */
	@GetMapping("/autoGetHoDesigLevel")
	public ResponseEntity<?> autoGetHoDesigLevel(@RequestParam String desigStr)
	{
		try {
			ApiResponse<?> apiResponse = managementService.autoGetHoDesigLevel(desigStr);
			apiResponse.setMessage("Auto Get HO Designation Level Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/autoGetHoDesigLevel Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param desigStr
	 * @return ResponseEntity<?> 
	 */
	@GetMapping("/tranHierManagementView")
	public ResponseEntity<?> tranHierManagementView(@RequestParam String transName)
	{
		try {
			ApiResponse<?> apiResponse = managementService.tranHierManagementView(transName);
			apiResponse.setMessage("Transaction Hierarchy View Successful");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/tranHierManagementView Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}

}
