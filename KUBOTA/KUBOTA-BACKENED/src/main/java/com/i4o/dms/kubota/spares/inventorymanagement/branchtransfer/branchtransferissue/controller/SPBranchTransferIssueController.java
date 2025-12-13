package com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferissue.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferissue.domain.SPBranchTransferIssue;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.BTIssueSearchRequestDto;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferissue.service.SPBranchTransferIssueService;
import com.i4o.dms.kubota.utils.ApiResponse;

/**
 * @author suraj.gaur
 */
@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/spares/branchTransfer/issue")
public class SPBranchTransferIssueController {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private SPBranchTransferIssueService issueService; 
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/saveBTIssue")
	public ResponseEntity<?> saveBTIssue(@RequestBody SPBranchTransferIssue branchTransferIssue)
	{
		try {
			ApiResponse<?> apiResponse = issueService.saveBTIssue(branchTransferIssue);
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/saveBTIssue Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getIssueToBranchDetails")
	public ResponseEntity<?> getIssueToBranchDetails()
	{
		try {
			ApiResponse<?> apiResponse = issueService.getIssueToBranchDetails();
			apiResponse.setMessage("Get Issue_To Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getIssueToBranchDetails Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getIssueingBranch")
	public ResponseEntity<?> getIssueingBranch()
	{
		try {
			ApiResponse<?> apiResponse = issueService.getIssueingBranch();
			apiResponse.setMessage("Get Issue_To Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getIssueingBranch Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getIndentNos")
	public ResponseEntity<?> getIndentNos(@RequestParam Long reqFromBranchId)
	{
		try {
			ApiResponse<?> apiResponse = issueService.getIndentNos(reqFromBranchId);
			apiResponse.setMessage("Get Available Indent no Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getIndentNos Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}

	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoGetIssueNo")
	public ResponseEntity<?> autoGetIssueNo(@RequestParam String issueNo)
	{
		try {
			ApiResponse<?> apiResponse = issueService.autoGetIssueNo(issueNo);
			apiResponse.setMessage("Auto Get Issue No Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/autoGetIssueNo Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getIndentItemsDetailsByIds")
	public ResponseEntity<?> getIndentItemsDetailsByIds(@RequestParam String indentIds)
	{
		try {
			ApiResponse<?> apiResponse = issueService.getIndentItemsDetailsByIds(indentIds);
			apiResponse.setMessage("Auto Get Indent Item Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getIndentItemsDetailsByIds Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/viewBTIssue")
	public ResponseEntity<?> viewBTIssue(@RequestParam String issueNo)
	{
		try {
			ApiResponse<?> apiResponse = issueService.viewBTIssue(issueNo);
			apiResponse.setMessage("View Branch Transfer Issue Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/viewBTIssue Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoGetIndentNo")
	public ResponseEntity<?> autoGetIndentNo(@RequestParam String indentNo)
	{
		try {
			ApiResponse<?> apiResponse = issueService.autoGetIndentNo(indentNo);
			apiResponse.setMessage("Auto Get Indent No Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/viewBTIssue Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author mahesh.kumar
	 * @param requestDto
	 * @return ResponseEntity<?>
	 * @apiNote Searching Branch Transfer issue 
	 */
	@PostMapping("/searchBTIssue")
	public ResponseEntity<?> searchBTIssue(@RequestBody BTIssueSearchRequestDto requestDto)
	{
		try {
			ApiResponse<?> apiResponse = issueService.searchBTIssue(requestDto);
			apiResponse.setMessage("Get BT Issue Search Details Successful");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/searchBTIssue Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param issueId
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getSelectedIndentNos")
	public ResponseEntity<?> getSelectedIndentNos(@RequestParam Long issueId)
	{
		try {
			ApiResponse<?> apiResponse = issueService.getSelectedIndentNos(issueId);
			apiResponse.setMessage("Get Selected BT Indent Nos Successful");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getSelectedIndentNos Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param issueId
	 * @param issueNo
	 * @param printStatus
	 * @param request
	 * @param response
	 * @apiNote Api for generating report PDF for a particular BT Issue no.
	 */
	@GetMapping("/printBTIssueReport")
    public void printBTIssueReport(@RequestParam String issueId, @RequestParam String issueNo,
    		@RequestParam String printStatus, HttpServletRequest request, HttpServletResponse response)
    {
    	String filePath = request.getServletContext().getRealPath("/WEB-INF/reports/");
    	
    	OutputStream outputStream = null;
    	response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=" 
				+ issueNo.replace('/', '-') + "-" + ThreadLocalRandom.current().nextInt(1000) + "-" 
				+ System.currentTimeMillis() + ".pdf");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		
    	try {
    		outputStream = response.getOutputStream();
    		issueService.printBTIssueReport(issueId, printStatus, filePath, outputStream);
		} 
    	catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(outputStream!=null){
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					System.err.println("There is an Error flushing and closing the outStream object");
					e.printStackTrace();
				}
            }
		}
    }
	
}
