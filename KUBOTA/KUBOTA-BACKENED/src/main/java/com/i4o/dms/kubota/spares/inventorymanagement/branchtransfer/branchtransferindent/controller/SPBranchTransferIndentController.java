package com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferindent.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferindent.domain.SPBranchTransferIndent;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferindent.dto.BTIndentSearchRequestDto;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferindent.service.SPBranchTransferIndentService;
import com.i4o.dms.kubota.utils.ApiResponse;

/**
 * @author suraj.gaur
 */
@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/spares/branchTransfer/indent")
public class SPBranchTransferIndentController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	SPBranchTransferIndentService indentService;
	
	/**
	 * @author suraj.gaur
	 * @param branchTransferIndent
	 * @return ResponseEntity<?>
	 * @apiNote Saving Branch Transfer indent 
	 */
	@PostMapping("/saveBTIndent")
	public ResponseEntity<?> saveBTIndent(@RequestBody SPBranchTransferIndent branchTransferIndent)
	{
		try {
			ApiResponse<?> apiResponse = indentService.saveBTIndent(branchTransferIndent);
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/saveBTIndent Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 * @apiNote viewing Branch Transfer indent 
	 * @param indentNo
	 */
	@GetMapping("/viewBTIndentByReqNo")
	public ResponseEntity<?> viewBTIndentByReqNo(@RequestParam String indentNo)
	{
		try {
			ApiResponse<?> apiResponse = indentService.viewBTIndentByReqNo(indentNo);
			apiResponse.setMessage("Get BT Indent Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getReqToBranchDeatilsById Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param requestDto
	 * @return ResponseEntity<?>
	 * @apiNote Searching Branch Transfer indent 
	 */
	@PostMapping("/searchBTIndent")
	public ResponseEntity<?> searchBTIndent(@RequestBody BTIndentSearchRequestDto requestDto)
	{
		try {
			ApiResponse<?> apiResponse = indentService.searchBTIndent(requestDto);
			apiResponse.setMessage("Get BT Indent Search Details Successful");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/searchBTIndent Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @apiNote get requested branch by id
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getReqToBranchDeatilsById")
	public ResponseEntity<?> getReqToBranchDeatilsById()
	{
		try {
			ApiResponse<?> apiResponse = indentService.getReqToBranchDeatilsById();
			apiResponse.setMessage("Get Request to Branch Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getReqToBranchDeatilsById Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getAllStatus")
	public ResponseEntity<?> getAllStatus()
	{
		try {
			ApiResponse<?> apiResponse = indentService.getAllStatus();
			apiResponse.setMessage("Get all Status Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getAllStatus Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param indentNo
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoGetIndentNo")
	public ResponseEntity<?> autoGetIndentNo(@RequestParam("indentNo") String indentNo)
	{
		try {
			ApiResponse<?> apiResponse = indentService.autoGetIndentNo(indentNo);
			apiResponse.setMessage("Auto Get Indent No Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/autoGetIndentNo Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getSubBranch")
	public ResponseEntity<?> findSubBranch()
	{        
		try {
			ApiResponse<?> apiResponse = indentService.findSubBranch();
			apiResponse.setMessage("Get Sub Branche Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getSubBranch Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getReqByEmpDetail")
	public ResponseEntity<?> getReqByEmpDetail()
	{        
		try {
			ApiResponse<?> apiResponse = indentService.getReqByEmployeeDetail();
			apiResponse.setMessage("Get Request by Employee Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getReqByEmpDetail Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param itemNo
	 * @param suppByBranchId
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getSpareItemDetails")
	public ResponseEntity<?> getSpareItemDetails(@RequestParam("itemNo") String itemNo, 
			@RequestParam("suppByBranchId") Long suppByBranchId)
	{        
		try {
			ApiResponse<?> apiResponse = indentService.getSpareItemDetails(itemNo, suppByBranchId);
			apiResponse.setMessage("Get Spare Item Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getSpareItemDetails Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	@GetMapping("/autoCompleteItemNumber")
	public ResponseEntity<?> autoCompleteItemNumber(@RequestParam String itemNumber)
	{
		try {
			ApiResponse<?> apiResponse = indentService.autoCompleteItemNumber(itemNumber);
			apiResponse.setMessage("Get Auto Spare Item no Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getSpareItemDetails Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param file
	 * @param suppByBranchId
	 * @return ResponseEntity<?>
	 * @apiNote for getting existing spare 
	 */
	@PostMapping(value = "/excelUpload")
    public ResponseEntity<?> uploadExcel(MultipartFile file, 
    		@RequestPart String suppByBranchId,
    		@RequestPart(required=false) String existingItems)
	{
		try {
			ApiResponse<?> apiResponse = indentService.uploadExcel(file, Long.parseLong(suppByBranchId), existingItems);
			apiResponse.setMessage("Get Spare Item Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse); 
		}catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/excelUpload Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(apiResponse);
        }
	}
	
	/**
	 * @author suraj.gaur
	 * @param reqId
	 * @param reqNo
	 * @param printStatus
	 * @param request
	 * @param response
	 * @apiNote Api for generating report PDF for a particular BT Indent no.
	 */
	@GetMapping("printBTIndentReport")
    public void printBTIndentReport(@RequestParam String reqId, @RequestParam String reqNo,
    		@RequestParam String printStatus, HttpServletRequest request, HttpServletResponse response)
    {
    	String filePath = request.getServletContext().getRealPath("/WEB-INF/reports/");
    	
    	OutputStream outputStream = null;
    	response.setContentType("application/pdf");
    	response.setHeader("Content-Disposition", "inline; filename=" 
				+ reqNo.replace('/', '-') + "-" + ThreadLocalRandom.current().nextInt(1000) + "-" 
				+ System.currentTimeMillis() + ".pdf");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		
    	try {
    		outputStream = response.getOutputStream();
    		indentService.printBTIndentReport(reqId, printStatus, filePath, outputStream);
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
