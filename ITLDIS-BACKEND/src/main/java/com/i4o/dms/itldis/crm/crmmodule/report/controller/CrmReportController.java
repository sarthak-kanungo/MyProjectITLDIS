package com.i4o.dms.itldis.crm.crmmodule.report.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.crm.crmmodule.report.dto.CustomerSatisfactionScoreDto;
import com.i4o.dms.itldis.crm.crmmodule.report.dto.MonthlyFailureCodeSummaryReportDto;
import com.i4o.dms.itldis.crm.crmmodule.report.dto.TollFreeReportDto;
import com.i4o.dms.itldis.crm.crmmodule.report.model.CrmReportRequestModel;
import com.i4o.dms.itldis.crm.crmmodule.report.model.TollFreeReportRequestModel;
import com.i4o.dms.itldis.crm.crmmodule.report.repo.CrmReportRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/crm/report")
public class CrmReportController {
	
	@Autowired
	private UserAuthentication userAuthentication;
	@Autowired
	private CrmReportRepo repo;
	
	/**
	 * @author suraj.gaur
	 * @param requestModel
	 * @return
	 */
	@PostMapping("/searchCustomerSatisfactionReport")
	public ResponseEntity<?> searchCustomerSatisfactionReport(@RequestBody CrmReportRequestModel requestModel){
		ApiResponse<List<CustomerSatisfactionScoreDto>> apiResponse = new ApiResponse<List<CustomerSatisfactionScoreDto>>();
		List<CustomerSatisfactionScoreDto> result = repo.getSatisfactionScoreReport(
				userAuthentication.getDealerId(),
				requestModel.getProduct(),
				requestModel.getSeries(),
				requestModel.getModel(),
				requestModel.getSubModel(),
				requestModel.getVariant(),
				requestModel.getReportType(),
				requestModel.getFromDate(),
				requestModel.getToDate(),
				requestModel.getPage(),
				requestModel.getSize()
			);
		Long count = 0l;
		if(result!=null && !result.isEmpty()){
			count = result.get(0).getRecordsCount();
		}
		apiResponse.setCount(count);
        apiResponse.setMessage("Customer Satisfaction Records get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/monthlyFailureCodeSummaryReport")
	public ResponseEntity<?> monthlyFailureCodeSummaryReport(@RequestBody CrmReportRequestModel requestModel){
		ApiResponse<List<MonthlyFailureCodeSummaryReportDto>> apiResponse = new ApiResponse<List<MonthlyFailureCodeSummaryReportDto>>();
		List<MonthlyFailureCodeSummaryReportDto> result = repo.getMonthlyFailureCodeSummaryReport(userAuthentication.getUsername(), 
				requestModel.getProduct(),
				requestModel.getSeries(),
				requestModel.getModel(),
				requestModel.getSubModel(),
				requestModel.getVariant(),
				requestModel.getComplaintCode(),
				requestModel.getFromYear(),
				requestModel.getToYear(),
				requestModel.getPage(),
				requestModel.getSize()
				);
		Long count = 0l;
		if(result!=null && !result.isEmpty()){
			count = result.get(0).getRecordsCount();
		}
		apiResponse.setCount(count);
        apiResponse.setMessage("Monthly Failure Code Summary Records get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/downloadTollFreeReport")
    public ResponseEntity<InputStreamResource> downloadTollFreeReport(@RequestBody TollFreeReportRequestModel requestModel,
            HttpServletResponse response) throws IOException{
    	
    	Integer size = Integer.MAX_VALUE-1;

    	List<TollFreeReportDto> result = repo.getTollFreeReport( 
				requestModel.getFromDate(),
				requestModel.getToDate(),
				requestModel.getMobileNo(),
				requestModel.getDealerId(),
				requestModel.getOrgHierId(),
				userAuthentication.getUsername(),
				requestModel.getPage(),
				size
				);
		
    	ByteArrayInputStream in = ExcelCellGenerator.tollFreeReports(result);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "TollFreeReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }
	
	
	@PostMapping("/exportMFCS")
    public ResponseEntity<InputStreamResource> mfcsExcelReport(@RequestBody CrmReportRequestModel requestModel, HttpServletResponse response) throws IOException{
    	Integer size = Integer.MAX_VALUE-1;
    	List<MonthlyFailureCodeSummaryReportDto> result = repo.getMfcsExcelReport(userAuthentication.getUsername(),
				requestModel.getProduct(),
				requestModel.getSeries(),
				requestModel.getModel(),
				requestModel.getSubModel(),
				requestModel.getVariant(),
				requestModel.getComplaintCode(),
				requestModel.getFromYear(),
				requestModel.getToYear(),
				requestModel.getPage(),
				size
				);
    	ByteArrayInputStream in = ExcelCellGenerator.mfcsExcelReport(result);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "MFSC_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
	
	/**
	 * @author suraj.gaur
	 * @param requestModel
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/cssExcelReport")
    public ResponseEntity<InputStreamResource> cssExcelReport(@RequestBody CrmReportRequestModel requestModel, HttpServletResponse response) throws IOException{
    	Integer size = Integer.MAX_VALUE-1;
    	
    	ByteArrayInputStream in = null;
    	
    	List<Map<String, Object>> result = repo.getSatisfactionScoreExcelReport(
				userAuthentication.getDealerId(),
				requestModel.getProduct(),
				requestModel.getSeries(),
				requestModel.getModel(),
				requestModel.getSubModel(),
				requestModel.getVariant(),
				requestModel.getReportType(),
				requestModel.getFromDate(),
				requestModel.getToDate(),
				requestModel.getPage(),
				size);
    	
    	if(requestModel.getReportType().equalsIgnoreCase("State")) {
    		in = ExcelCellGenerator.cssExcelStateReport(result);
    	} else {
    		in = ExcelCellGenerator.cssExcelReport(result);
    	}

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "CSS_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = " + filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
	
}
