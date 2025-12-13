package com.i4o.dms.kubota.warranty.hotlinereport.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.common.service.JasperPrintService;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.warranty.hotlinereport.domain.WarrantyHotlineReport;
import com.i4o.dms.kubota.warranty.hotlinereport.dto.WarrantyHotlineReportSearchDto;
import com.i4o.dms.kubota.warranty.hotlinereport.dto.WarrantyHotlineSearchResponceDto;
import com.i4o.dms.kubota.warranty.hotlinereport.repository.WarrantyHotlineReportRepo;
import com.i4o.dms.kubota.warranty.hotlinereport.service.WarrantyHotlineReportService;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author suraj.gaur
 */
@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
	methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/warranty/hotlineReport")
public class WarrantyHotlineReportController {
	
	@Autowired
	private WarrantyHotlineReportService hotlineReportService;
	
	/**
	 * @author suraj.gaur
	 * @param hotlineReport
	 * @param multipartFileList
	 * @return ResponseEntity<?>
	 * @apiNote for saving data of warranty hotline report.
	 */
	@PostMapping(value = "submitHotlineReport")
	public ResponseEntity<?> submitHotlineReport(
			@RequestPart(value = "hotlineReport") WarrantyHotlineReport hotlineReport,
			@RequestPart(value = "multipartFileList") List<MultipartFile> multipartFileList){
		ApiResponse apiResponse = null;
		
		try {
			apiResponse =  hotlineReportService.submitHotlineReport(hotlineReport, multipartFileList);
			apiResponse.setMessage("Warranty Hotline Report has been saved Successfully");
			apiResponse.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			apiResponse =  new ApiResponse();
            apiResponse.setResult(e.getStackTrace());
            apiResponse.setMessage("Warranty Hotline Report did not Save");
            apiResponse.setStatus(HttpStatus.CONFLICT.value());
		}
		
		return ResponseEntity.ok(apiResponse);
	}
	
	/**
	 * @author suraj.gaur
	 * @param hotlineNo
	 * @return ResponceEntity<?>
	 * @apiNote for getting view data of hotline report
	 */
	@GetMapping(value = "viewHotlineReport")
	public ResponseEntity<?> viewHotlineReport(@RequestParam("hotlineNo") String hotlineNo){
		ApiResponse apiResponse = null;
        
        try {
        	apiResponse = hotlineReportService.findHotlineByHtlrNo(hotlineNo);
            apiResponse.setMessage("View Hotline Report Success");
            apiResponse.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			System.out.println(e.getMessage());
			apiResponse.setMessage("There is some error occured");
            apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
		}
        
        return ResponseEntity.ok(apiResponse);
	}
	
	/**
	 * @author suraj.gaur
	 * @param retrofitmentNo
	 * @return ResponseEntity<?>
	 * @apiNote for searching hotline report on main page
	 */
	@GetMapping(value = "searchHotlineNo")
    public ResponseEntity<?> searchHotlineNo(@RequestParam String hotlineNo) {
        ApiResponse apiResponse = null;
        
        try {
        	apiResponse = hotlineReportService.searchHotlineNo(hotlineNo);
            apiResponse.setMessage("Search Hotline No Success");
            apiResponse.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			System.out.println(e.getMessage());
			apiResponse.setMessage("There is some error occured");
            apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
		}
        
        return ResponseEntity.ok(apiResponse);
    }
	
	@GetMapping(value = "dealerDepoList")
    public ResponseEntity<?> dealerDepoList() {
        ApiResponse apiResponse = null;
        
		try {
			apiResponse = hotlineReportService.dealerDepoList();
			apiResponse.setMessage("Get Depo List Success");
			apiResponse.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			System.out.println(e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
		}
        
        return ResponseEntity.ok(apiResponse);
    }
	
	@GetMapping(value = "condionFailureCode")
    public ResponseEntity<?> getFailureCode() {
        ApiResponse apiResponse = null;
        
        try {
        	apiResponse = hotlineReportService.getFailureCode();
            apiResponse.setMessage("Search Failure Code Success");
            apiResponse.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			System.out.println(e.getMessage());
			apiResponse.setMessage("There is some error occured");
            apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
		}
        
        return ResponseEntity.ok(apiResponse);
    }
	
	@GetMapping(value = "hotlineStatus")
    public ResponseEntity<?> getHotlineStatus() {
		ApiResponse apiResponse = null;
		
        try {
        	apiResponse = hotlineReportService.getHotlineStatus();
            apiResponse.setMessage("Get Hotline Status Success");
            apiResponse.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			System.out.println(e.getMessage());
			apiResponse.setMessage("There is some error occured");
            apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
		}
        
        return ResponseEntity.ok(apiResponse);
    }
	
	@GetMapping(value = "hotlinePlant")
    public ResponseEntity<?> getHotlinePlant() {
		ApiResponse apiResponse = null;
		
        try {
        	apiResponse = hotlineReportService.getHotlinePlant();
            apiResponse.setMessage("Get Hotline Plant Success");
            apiResponse.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			System.out.println(e.getMessage());
			apiResponse.setMessage("There is some error occured");
            apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
		}
        
        return ResponseEntity.ok(apiResponse);
    }
	
	@GetMapping(value = "hoDeparment")
    public ResponseEntity<?> getHoDeparment() {
		ApiResponse apiResponse = null;
		
        try {
        	apiResponse = hotlineReportService.getHoDepartment();
            apiResponse.setMessage("Get HO Department Success");
            apiResponse.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			System.out.println(e.getMessage());
			apiResponse.setMessage("There is some error occured");
            apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
		}
        
        return ResponseEntity.ok(apiResponse);
    }
	
	@GetMapping(value = "chassisNo")
    public ResponseEntity<?> getChassisNo(@RequestParam("chassisNo") String chassisNo) {
		ApiResponse apiResponse = null;
		
        try {
        	apiResponse = hotlineReportService.getChassisNo(chassisNo);
            apiResponse.setMessage("Get Chassis no Success");
            apiResponse.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			apiResponse =  new ApiResponse();
			System.out.println(e.getMessage());
			apiResponse.setMessage("There is some error occured");
            apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
		}
        
        return ResponseEntity.ok(apiResponse);
    }
	
	@GetMapping(value = "deptIncharge")
    public ResponseEntity<?> getDeptIncharge(@RequestParam("deptId") Long deptId) {
		ApiResponse apiResponse = null;
		
        try {
        	apiResponse = hotlineReportService.getDepartmentIncharge(deptId);
            apiResponse.setMessage("Get Department Incharge Success");
            apiResponse.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			apiResponse =  new ApiResponse();
			System.out.println(e.getMessage());
			apiResponse.setMessage("There is some error occured");
            apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
		}
        
        return ResponseEntity.ok(apiResponse);
    }
	
	@PostMapping(value = "searchHotlineReport")
    public ResponseEntity<?> searchHotlineReport(@RequestBody WarrantyHotlineReportSearchDto reportSearchDto)  {
		ApiResponse apiResponse = null;
		
		try {
			apiResponse = hotlineReportService.searchHotlineReport(reportSearchDto);
			apiResponse.setMessage("Search Data get Success");
            apiResponse.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			apiResponse =  new ApiResponse();
			System.out.println(e.getMessage());
			apiResponse.setMessage("There is some error occured");
            apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
		}
		
		return ResponseEntity.ok(apiResponse);
    }
	
	@GetMapping(value = "getFailureType")
    public ResponseEntity<?> getFailureType() {
		ApiResponse apiResponse = null;
		
        try {
        	apiResponse = hotlineReportService.getFailureType();
            apiResponse.setMessage("Get Failure Type Success");
            apiResponse.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			System.out.println(e.getMessage());
			apiResponse.setMessage("There is some error occured");
            apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
		}
        
        return ResponseEntity.ok(apiResponse);
    }
	
	/**
	 * @author suraj.gaur
	 * @param hotlineId
	 * @param hotlineNo
	 * @param printStatus
	 * @param request
	 * @param response
	 * @apiNote Api for generating report PDF for a particular Hotline no.
	 */
	@GetMapping("printWarrantyHotlineReport")
    public void printWarrantyHotlineReport(@RequestParam String hotlineId, @RequestParam String hotlineNo,
    		@RequestParam String printStatus, HttpServletRequest request, HttpServletResponse response)
    {
    	String filePath = request.getServletContext().getRealPath("/WEB-INF/reports/");
    	
    	OutputStream outputStream = null;
    	response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=" 
				+ hotlineNo.replace('/', '-') + "-" + ThreadLocalRandom.current().nextInt(1000) + "-" 
				+ System.currentTimeMillis() + ".pdf");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		
    	try {
    		outputStream = response.getOutputStream();
    		hotlineReportService.printWarrantyHotlineReport(hotlineId, printStatus, filePath, outputStream);
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
