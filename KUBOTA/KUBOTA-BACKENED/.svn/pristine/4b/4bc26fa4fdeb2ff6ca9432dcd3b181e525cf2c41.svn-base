package com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferreciept.controller;

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

import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferreciept.dto.BTReceiptSearchRequestDto;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferreciept.domain.SPBranchTransferReceipt;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferreciept.service.SPBranchTransferReceiptService;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/spares/branchTransfer/receipt")
public class SPBranchTransferReceiptController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private SPBranchTransferReceiptService receiptService;
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 * @apiNote For saving and submitting BT Receipt
	 */
	@PostMapping("/saveBTReceipt")
	public ResponseEntity<?> saveBTReceipt(@RequestBody SPBranchTransferReceipt transferReceipt)
	{
		try {
			ApiResponse<?> apiResponse = receiptService.saveBTReceipt(transferReceipt);
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/saveBTReciept Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoGetIssueNoInReceipt")
	public ResponseEntity<?> autoGetIssueNoInReceipt(@RequestParam String issueNo)
	{
		try {
			ApiResponse<?> apiResponse = receiptService.autoGetIssueNoInReceipt(issueNo);
			apiResponse.setMessage("Auto Get Reciept No Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/autoGetIssueNoInReceipt Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getIssueingBranchName")
	public ResponseEntity<?> getIssueingBranchName(@RequestParam String issueId)
	{
		try {
			ApiResponse<?> apiResponse = receiptService.getIssueingBranchName(issueId);
			apiResponse.setMessage("Get Reciept_To Details Success");
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
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getReceivingBranch")
	public ResponseEntity<?> getReceivingBranch()
	{
		try {
			ApiResponse<?> apiResponse = receiptService.getReceivingBranch();
			apiResponse.setMessage("Get Reciept_To Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getReceivingBranch Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author mahesh.kumar
	 * @param requestDto
	 * @return ResponseEntity<?>
	 * @apiNote Searching Branch Transfer receipt 
	 */
	@PostMapping("/searchBTReceipt")
	public ResponseEntity<?> searchBTReceipt(@RequestBody BTReceiptSearchRequestDto requestDto)
	{
		try {
			ApiResponse<?> apiResponse = receiptService.searchBTReceipt(requestDto);
			apiResponse.setMessage("Get BT Receipt Search Details Successful");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/searchBTReceipt Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author mahesh.kumar
	 * @param receiptNo
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoPopulateReceiptNo")
	public ResponseEntity<?> autoPopulateReceiptNo(@RequestParam("receiptNo") String receiptNo)
	{
		try {
			ApiResponse<?> apiResponse = receiptService.autoPopulateReceiptNo(receiptNo);
			apiResponse.setMessage("Auto Get Receipt No Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/autoPopulateReceiptNo Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}

	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getReceiptItemDetails")
	public ResponseEntity<?> getReceiptItemDetails(@RequestParam Long issueId)
	{
		try {
			ApiResponse<?> apiResponse = receiptService.getReceiptItemDetails(issueId);
			apiResponse.setMessage("Get Reciept Items Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getReciptItemDetails Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}

	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/viewBTReceipt")
	public ResponseEntity<?> viewBTReceipt(@RequestParam String receiptNo)
	{
		try {
			ApiResponse<?> apiResponse = receiptService.viewBTReceipt(receiptNo);
			apiResponse.setMessage("View BT Reciept Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/viewBTReceipt Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param receiptId
	 * @param receiptNo
	 * @param printStatus
	 * @param request
	 * @param response
	 * @apiNote Api for generating report PDF for a particular BT Receipt no.
	 */
	@GetMapping("/printBTReceiptReport")
    public void printBTReceiptReport(@RequestParam String receiptId, @RequestParam String receiptNo,
    		@RequestParam String printStatus, HttpServletRequest request, HttpServletResponse response)
    {
    	String filePath = request.getServletContext().getRealPath("/WEB-INF/reports/");
    	
    	OutputStream outputStream = null;
    	response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=" 
				+ receiptNo.replace('/', '-') + "-" + ThreadLocalRandom.current().nextInt(1000) + "-" 
				+ System.currentTimeMillis() + ".pdf");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		
    	try {
    		outputStream = response.getOutputStream();
    		receiptService.printBTReceiptReport(receiptId, printStatus, filePath, outputStream);
		} 
    	catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			logger.info("/printBTReceiptReport Exception: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			logger.info("/printBTReceiptReport Exception: " + e.getMessage());
		} finally{
			if(outputStream!=null){
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					System.err.println("There is an Error flushing and closing the outStream object");
					e.printStackTrace();
					System.out.println(e.getMessage());
					logger.info("/printBTReceiptReport Exception: " + e.getMessage());
				}
            }
		}
    }
	
	/**
	 * @author mahesh.kumar
	 * @param issueNo
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoPopulateIssueNo")
	public ResponseEntity<?> autoPopulateIssueNo(@RequestParam("issueNo") String issueNo)
	{
		try {
			ApiResponse<?> apiResponse = receiptService.autoPopulateIssueNo(issueNo);
			apiResponse.setMessage("Auto Get Issue No Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/autoPopulateIssueNo Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}

}
