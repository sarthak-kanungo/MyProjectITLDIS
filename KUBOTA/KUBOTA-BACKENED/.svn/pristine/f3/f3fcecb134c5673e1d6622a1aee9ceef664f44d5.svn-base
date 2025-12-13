package com.i4o.dms.kubota.spares.purchase.backordercancellation.controller;

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

import com.i4o.dms.kubota.spares.purchase.backordercancellation.domain.SPBackOrderCancellation;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.dto.BOCancellationApprovalRequestDto;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.dto.SPBackOrderCancellationRequestDto;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.service.SPBackOrderCancellationService;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/spares/purchase/BOCancellation")
public class SPBackOrderCancellationController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	SPBackOrderCancellationService cancellationService;
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 * @apiNote For saving and submitting BO Cancellation
	 */
	@PostMapping("/saveBOCancellation")
	public ResponseEntity<?> saveBOCancellation(@RequestBody SPBackOrderCancellation orderCancellation)
	{
		try {
			ApiResponse<?> apiResponse = cancellationService.saveBOCancellation(orderCancellation);
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/saveBOCancellation Exception: " + e.getMessage());
			e.printStackTrace();
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getBOCItemDetails")
	public ResponseEntity<?> getBOCItemDetails(@RequestParam String dealerCode)
	{
		try {
			ApiResponse<?> apiResponse = cancellationService.getBOCItemDetails(dealerCode);
			apiResponse.setMessage("Get Back Order Cancellation Items Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getBOCItemDetails Exception: " + e.getMessage());
			e.printStackTrace();
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/viewBOCancellation")
	public ResponseEntity<?> viewBOCancellation(@RequestParam String bocNo)
	{
		try {
			ApiResponse<?> apiResponse = cancellationService.viewBOCancellation(bocNo);
			apiResponse.setMessage("View Back Order Cancellation Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/viewBOCancellation Exception: " + e.getMessage());
			e.printStackTrace();
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/searchBOCancellation")
	public ResponseEntity<?> searchBOCancellation(@RequestBody SPBackOrderCancellationRequestDto requestDto)
	{
		try {
			ApiResponse<?> apiResponse = cancellationService.searchBOCancellation(requestDto);
			apiResponse.setMessage("Search Back Order Cancellation Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/searchBOCancellation Exception: " + e.getMessage());
			e.printStackTrace();
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/searchBOCApproval")
	public ResponseEntity<?> searchBOCApproval(@RequestBody SPBackOrderCancellationRequestDto requestDto)
	{
		try {
			ApiResponse<?> apiResponse = cancellationService.searchBOCApproval(requestDto);
			apiResponse.setMessage("Search Back Order Cancellation Approval Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/searchBOCApproval Exception: " + e.getMessage());
			e.printStackTrace();
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @apiNote For Auto Get Back Order Cancellation Nos
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoGetBOCNo")
	public ResponseEntity<?> autoGetBOCNo(@RequestParam String bocNo)
	{
		try {
			ApiResponse<?> apiResponse = cancellationService.autoGetBOCNo(bocNo);
			apiResponse.setMessage("Auto Get Back Order Cancellation Nos Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/autoGetBOCNo Exception: " + e.getMessage());
			e.printStackTrace();
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @apiNote For Auto Get BOC Dealer Codes in search page
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoGetDealerCode")
	public ResponseEntity<?> autoGetDealerCode(@RequestParam String dealerCode)
	{
		try {
			ApiResponse<?> apiResponse = cancellationService.autoGetDealerCode(dealerCode);
			apiResponse.setMessage("Auto Get Dealer Codes Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/autoGetDealerCode Exception: " + e.getMessage());
			e.printStackTrace();
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @apiNote For Auto Complete BOC Dealer Codes in new cancellation creation page
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoCompleteDealerCode")
	public ResponseEntity<?> autoCompleteDealerCode(@RequestParam String dealerCode)
	{
		try {
			ApiResponse<?> apiResponse = cancellationService.autoCompleteDealerCode(dealerCode);
			apiResponse.setMessage("Auto AutoComplete Dealer Codes Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/autoCompleteDealerCode Exception: " + e.getMessage());
			e.printStackTrace();
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param request
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/boCancellationApproval")
    public ResponseEntity<?> boCancellationApproval(@RequestBody BOCancellationApprovalRequestDto approvalRequestModel) {
        try {
			ApiResponse<?> apiResponse = cancellationService.boCancellationApproval(approvalRequestModel);
			apiResponse.setMessage("Back Order Cancellation Approved");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/boCancellationApproval Exception: " + e.getMessage());
			e.printStackTrace();
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}

	/**
	 * @author suraj.gaur
	 * @param request
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getBOCApproverHierarchyDetails")
    public ResponseEntity<?> getBOCApproverHierarchyDetails(@RequestParam Long backorderId) {
        try {
			ApiResponse<?> apiResponse = cancellationService.getBOCApproverHierarchyDetails(backorderId);
			apiResponse.setMessage("Get BOC Approver Hierarchy Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getBOCApproverHierarchyDetails Exception: " + e.getMessage());
			e.printStackTrace();
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param bocId
	 * @param bocNo
	 * @param printStatus
	 * @param request
	 * @param response
	 * @apiNote Api for generating report PDF for a particular BT Receipt no.
	 */
	@GetMapping("/printBOCReport")
    public void printBOCReport(@RequestParam String bocId, @RequestParam String bocNo,
    		@RequestParam String printStatus, HttpServletRequest request, HttpServletResponse response)
    {
    	String filePath = request.getServletContext().getRealPath("/WEB-INF/reports/");
    	
    	OutputStream outputStream = null;
    	response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=" 
				+ bocNo.replace('/', '-') + "-" + ThreadLocalRandom.current().nextInt(1000) + "-" 
				+ System.currentTimeMillis() + ".pdf");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		
    	try {
    		outputStream = response.getOutputStream();
    		cancellationService.printBOCReport(bocId, printStatus, filePath, outputStream);
		} 
    	catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			logger.info("/printBOCReport Exception: " + e.getMessage());
		} finally{
			if(outputStream!=null){
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					System.err.println("There is an Error flushing and closing the outStream object");
					e.printStackTrace();
					System.out.println(e.getMessage());
					logger.info("/printBOCReport Exception: " + e.getMessage());
				}
            }
		}
    }
	
}
