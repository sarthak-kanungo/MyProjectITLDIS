package com.i4o.dms.kubota.spares.purchase.orderplanningsheet.controller;

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

import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.domain.SPOrderPlanningSheet;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetItemDetailsReqDto;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetSearchRequestDto;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.service.SPOrderPlanningSheetService;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/spares/purchase/orderplanningsheet")
public class SPOrderPlanningSheetController {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	SPOrderPlanningSheetService opsService;
	
	/**
	 * @author mahesh.kumar
	 * @param opsNo
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoGetOPSheetNo")
	public ResponseEntity<?> autoGetOPSheetNo(@RequestParam("opsNo") String opsNo)
	{
		try {
			ApiResponse<?> apiResponse = opsService.autoGetOPSheetNo(opsNo);
			apiResponse.setMessage("Auto Get Order Planning Sheet No Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/autoGetOPSheetNo Exception: " + e.getMessage());
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
	@PostMapping("/searchOPSheet")
	public ResponseEntity<?> searchOPSheet(@RequestBody OPSheetSearchRequestDto requestDto)
	{
		try {
			ApiResponse<?> apiResponse = opsService.searchOPSheet(requestDto);
			apiResponse.setMessage("Get OP Sheet Search Details Successful");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/searchOPSheet Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	
	/**
	 * @author mahesh.kumar
	 * @apiNote get reorder Quantity Month
	 * @return ResponseEntity<?>
	 */
	
	@GetMapping("/getReorderQtyMonth")
	public ResponseEntity<?> getReorderQtyMonth()
	{
		try {
			ApiResponse<?> apiResponse = opsService.getReorderQtyMonth();
			apiResponse.setMessage("Get Request to Reorder Quantity Month Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getReorderQtyMonth Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	

	/**
	 * @author mahesh.kumar
	 * @apiNote get reorder Quantity Month
	 * @return ResponseEntity<?>
	 */
	
	@GetMapping("/getSuggestedQtyMonth")
	public ResponseEntity<?> getSuggestedQtyMonth()
	{
		try {
			ApiResponse<?> apiResponse = opsService.getSuggestedQtyMonth();
			apiResponse.setMessage("Get Request to Suggested Order Quantity Month Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getSuggestedQtyMonth Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	
	/**
	 * @author mahesh.kumar
	 * @apiNote get all the logics
	 * @return ResponseEntity<?>
	 */
	
	@GetMapping("/getAllLogic")
	public ResponseEntity<?> getAllLogic()
	{
		try {
			ApiResponse<?> apiResponse = opsService.getAllLogic();
			apiResponse.setMessage("Get Request to All the Logic Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getAllLogic Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	
	/**
	 * @author mahesh.kumar
	 * @param opSheetId
	 * @param opSheetNo
	 * @param printStatus
	 * @param request
	 * @param response
	 * @apiNote Api for generating report PDF for a particular Order Planning Sheet no.
	 */
	@GetMapping("printOPSheetReport")
    public void printBTIndentReport(@RequestParam String opSheetId, @RequestParam String opSheetNo,
    		@RequestParam String printStatus, HttpServletRequest request, HttpServletResponse response)
    {
    	String filePath = request.getServletContext().getRealPath("/WEB-INF/reports/");
    	
    	OutputStream outputStream = null;
    	response.setContentType("application/pdf");
    	response.setHeader("Content-Disposition", "inline; filename=" 
				+ opSheetNo.replace('/', '-') + "-" + ThreadLocalRandom.current().nextInt(1000) + "-" 
				+ System.currentTimeMillis() + ".pdf");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		
    	try {
    		outputStream = response.getOutputStream();
    		opsService.printOPSheetReport(opSheetId, printStatus, filePath, outputStream);
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
	
	
	/**
	 * @author suraj.gaur
	 * @param String opSheetNo
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/viewOPSheet")
	public ResponseEntity<?> viewOPSheet(@RequestParam String opSheetNo)
	{
		try {
			ApiResponse<?> apiResponse = opsService.viewOPSheet(opSheetNo);
			apiResponse.setMessage("View Order Planning Sheet Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/viewOPSheet Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	
	/**
	 * @author suraj.gaur
	 * @param OPSheetItemDetailsReqDto
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/getOPSheetItemDetails")
	public ResponseEntity<?> getOPSheetItemDetails(@RequestBody OPSheetItemDetailsReqDto detailsReqDto)
	{
		try {
			ApiResponse<?> apiResponse = opsService.getOPSheetItemDetails(detailsReqDto);
			apiResponse.setMessage("Get Order Planning Sheet Items Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getOPSheetItemDetails Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	
	/**
	 * @author suraj.gaur
	 * @param SPOrderPlanningSheet
	 * @return ResponseEntity<?>
	 * @apiNote Saving Order Planning Sheet 
	 */
	@PostMapping("/saveOPSheet")
	public ResponseEntity<?> saveOPSheet(@RequestBody SPOrderPlanningSheet orderPlanningSheet)
	{
		try {
			ApiResponse<?> apiResponse = opsService.saveOPSheet(orderPlanningSheet);
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/saveOPSheet Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getOrderTypes")
	public ResponseEntity<?> getOrderTypes()
	{
		try {
			ApiResponse<?> apiResponse = opsService.getOrderTypes();
			apiResponse.setMessage("Get Order Types Successful!");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getOrderTypes Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}

}
