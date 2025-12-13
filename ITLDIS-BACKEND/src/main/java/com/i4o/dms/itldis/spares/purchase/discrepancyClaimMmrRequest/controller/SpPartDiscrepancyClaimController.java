package com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.domain.SpPartDiscrepancyClaimHdr;
import com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.dto.ClaimApprovalRequestDto;
import com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.dto.DiscrepancyClaimSearchRequestDto;
import com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.dto.PrintPdfRequestDto;
import com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.service.SpPartDiscrepancyClaimService;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;

import static com.i4o.dms.itldis.configurations.Constants.DISCREPANCY_CLAIM;
import static com.i4o.dms.itldis.configurations.Constants.MRR;
import static com.i4o.dms.itldis.configurations.Constants.SUBMITTED;
import static com.i4o.dms.itldis.configurations.Constants.DRAFT;
import static com.i4o.dms.itldis.configurations.Constants.APPROVED;
import static com.i4o.dms.itldis.configurations.Constants.PARTIALLY_APPROVED;

/**
 * @author suraj.gaur
 */
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/spares/purchase/discrepancyClaim")
public class SpPartDiscrepancyClaimController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private SpPartDiscrepancyClaimService discrepancyClaimService;
	
	
	/**
	 * @author suraj.gaur
	 * @param claimNoString
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/saveDiscrepancyClaim")
	public ResponseEntity<?> saveDiscrepancyClaim(
			@RequestPart(value = "discrepancyClaim") SpPartDiscrepancyClaimHdr discrepancyClaim,
			@RequestPart(value = "multipartFileList") List<MultipartFile> multipartFileList)
	{
		try {
			ApiResponse<?> apiResponse = discrepancyClaimService.saveDiscrepancyClaim(discrepancyClaim, multipartFileList);
			apiResponse.setMessage("Saving Discrepancy Claim Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/saveDiscrepancyClaim Exception: " + e.getMessage());
			logger.error("An error occurred in /saveDiscrepancyClaim:", e);
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
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getClaimTypes")
	public ResponseEntity<?> getClaimTypes()
	{
		try {
			ApiResponse<List<String>> apiResponse = new ApiResponse<>();
			
			List<String> claimType = new ArrayList<>();
			claimType.add(DISCREPANCY_CLAIM);
			claimType.add(MRR);
			
			apiResponse.setResult(claimType);
			apiResponse.setMessage("Get Discrepancy Claim Types Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/getClaimTypes Exception: " + e.getMessage());
			logger.error("An error occurred in /getClaimTypes:", e);
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
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getClaimStatus")
	public ResponseEntity<?> getClaimStatus()
	{
		try {
			ApiResponse<List<String>> apiResponse = new ApiResponse<>();
			
			List<String> claimStatus = new ArrayList<>();
			claimStatus.add(SUBMITTED);
			claimStatus.add(DRAFT);
			claimStatus.add(APPROVED);
			claimStatus.add(PARTIALLY_APPROVED);
			
			apiResponse.setResult(claimStatus);
			apiResponse.setMessage("Get Discrepancy Claim Types Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/getClaimStatus Exception: " + e.getMessage());
			logger.error("An error occurred in /getClaimStatus:", e);
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
	 * @param claimNoString
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoGetDiscrepancyClaimNo")
	public ResponseEntity<?> autoGetDiscrepancyClaimNo(@RequestParam String claimNoString)
	{
		try {
			ApiResponse<?> apiResponse = discrepancyClaimService.autoGetDiscrepancyClaimNo(claimNoString);
			apiResponse.setMessage("Auto Get Discrepancy Claim No List Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/autoGetDiscrepancyClaimNo Exception: " + e.getMessage());
			logger.error("An error occurred in /autoGetDiscrepancyClaimNo:", e);
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
	 * @param grnStr
	 * @param descrClaimType
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoGetDiscrepancyGrnNo")
	public ResponseEntity<?> autoGetDiscrepancyGrnNo(@RequestParam String descrClaimType, @RequestParam String grnStr)
	{
		try {
			ApiResponse<?> apiResponse = discrepancyClaimService.autoGetDiscrepancyGrnNo(descrClaimType, grnStr);
			apiResponse.setMessage("Auto Get GRN No List Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/autoGetDiscrepancyGrnNo Exception: " + e.getMessage());
			logger.error("An error occurred in /autoGetDiscrepancyGrnNo:", e);
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
	 * @param grnId
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getDiscrepancyItems")
	public ResponseEntity<?> getDiscrepancyItems(@RequestParam Long grnId)
	{
		try {
			ApiResponse<?> apiResponse = discrepancyClaimService.getDiscrepancyItems(grnId);
			apiResponse.setMessage("Get Discrepancy Items List Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/getDiscrepancyItems Exception: " + e.getMessage());
			
			//printing error on production error logs
			logger.error("An error occurred in /getDiscrepancyItems:", e);
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
	 * @param grnId
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getHeaderGrnDetails")
	public ResponseEntity<?> getHeaderGrnDetails(@RequestParam Long grnId)
	{
		try {
			ApiResponse<?> apiResponse = discrepancyClaimService.getHeaderGrnDetails(grnId);
			apiResponse.setMessage("Get Grn Details for Header Data Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/getHeaderGrnDetails Exception: " + e.getMessage());
			logger.error("An error occurred in /getHeaderGrnDetails:", e);
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
	 * @param grnId
	 * @param itemStr
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoGetGrnItemsNo")
	public ResponseEntity<?> autoGetGrnItemsNo(@RequestParam Long grnId, @RequestParam String itemStr)
	{
		try {
			ApiResponse<?> apiResponse = discrepancyClaimService.autoGetGrnItemsNo(grnId, itemStr);
			apiResponse.setMessage("Auto Get GRN Items Nos Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/autoGetGrnItemsNo Exception: " + e.getMessage());
			logger.error("An error occurred in /autoGetGrnItemsNo:", e);
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
	 * @param grnId
	 * @param itemNo
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getGrnItemDiscrDetails")
	public ResponseEntity<?> getGrnItemDiscrDetails(@RequestParam Long grnId, @RequestParam String itemNo)
	{
		try {
			ApiResponse<?> apiResponse = discrepancyClaimService.getGrnItemDiscrDetails(grnId, itemNo);
			apiResponse.setMessage("Auto Get Grn Item Discrepancy Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/getGrnItemDiscrDetails Exception: " + e.getMessage());
			logger.error("An error occurred in /getGrnItemDiscrDetails:", e);
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
	 * @param requestDto
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/searchDiscrepancyClaim")
	public ResponseEntity<?> searchDiscrepancyClaim(@RequestBody DiscrepancyClaimSearchRequestDto requestDto)
	{
		try {
			ApiResponse<?> apiResponse = discrepancyClaimService.searchDiscrepancyClaim(requestDto);
			apiResponse.setMessage("Discrepancy Search Result Get Successful");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/searchDiscrepancyClaim Exception: " + e.getMessage());
			logger.error("An error occurred in /searchDiscrepancyClaim:", e);
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
	 * @param discrClaimNo
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getDiscrepancyClaimView")
	public ResponseEntity<?> getDiscrepancyClaimView(@RequestParam String discrClaimNo)
	{
		try {
			ApiResponse<?> apiResponse = discrepancyClaimService.getDiscrepancyClaimView(discrClaimNo);
			apiResponse.setMessage("Discrepancy Claim View Get Successful");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/getDiscrepancyClaimView Exception: " + e.getMessage());
			logger.error("An error occurred in /getDiscrepancyClaimView:", e);
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
	 * @param request
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/claimApproveReject")
    public ResponseEntity<?> claimApproveReject(@RequestBody ClaimApprovalRequestDto approvalRequestDto) {
		
		try {
			ApiResponse<?> apiResponse = discrepancyClaimService.claimApproveReject(approvalRequestDto);
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			logger.info("/claimApproveReject Exception: " + e.getMessage());
			logger.error("An error occurred in /claimApproveReject:", e);
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
	 * @param printPdfDto
	 * @param request
	 * @param response
	 * @apiNote API for generating report PDF.
	 */
	@PostMapping("/printDMCReport")
    public void printDMCReport(@RequestBody PrintPdfRequestDto printPdfDto, HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	String filePath = request.getServletContext().getRealPath("/WEB-INF/reports/");
    	
    	OutputStream outputStream = null;
    	response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=" + printPdfDto.getClaimNo().replace('/', '-') 
				+ "-" + ThreadLocalRandom.current().nextInt(1000) + "-" + System.currentTimeMillis() + ".pdf");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		
    	try {
    		outputStream = response.getOutputStream();
    		discrepancyClaimService.printDMCReport(printPdfDto, filePath, outputStream);
		} 
    	catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			logger.info("/printDMCReport Exception: " + e.getMessage());
			logger.error("An error occurred in /printDMCReport:", e);
		} finally{
			if(outputStream != null){
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					System.err.println("There is an Error flushing and closing the outStream object");
					e.printStackTrace();
					System.out.println(e.getMessage());
					logger.info("/printDMCReport Exception: " + e.getMessage());
				}
            }
		}
    }
	
	
	
	@Autowired
    private StorageService storageService;
	/**
	 * @author suraj.gaur
	 * @apiNote this api is created for testing multipart configuration for checking size of files configured in
	 * {@link com.i4o.dms.itldis.configurations.webConfig} file. For this I had to add a dependency named: commons-fileupload in pox.xml
	 * @param media
	 * @return
	 */
	@PostMapping(value = "/testing", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> testing(@RequestPart(value = "mediaList") List<MultipartFile> mediaList)
	{
		try {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			
			if (mediaList.size() <= 5 && !mediaList.isEmpty()) {
				mediaList.forEach(file -> {
					System.out.println("Media size after compression: " + file.getSize());
					storageService.store(file, file.getOriginalFilename());
					
					//For deleting file we can use this API
//					storageService.deleteExistingFile(file.getOriginalFilename());
				});
			}
			apiResponse.setMessage("File upload Success!");
			apiResponse.setStatus(HttpStatus.ACCEPTED.value());
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			logger.info("/testing Exception: " + e.getMessage());
			logger.error("An error occurred in /testing:", e);
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> debugUpload(HttpServletRequest request) {
	    request.getParameterMap().forEach((key, value) -> System.out.println(key + ": " + value[0]));
	    System.out.println("abcd");
	    return ResponseEntity.ok("Logged request parts.");
	}
}
