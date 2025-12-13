package com.i4o.dms.itldis.service.report.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.jobcard.dto.JobcardDetailedExcelResponseDto;
import com.i4o.dms.itldis.service.jobcard.dto.JobcardSearchDto;
import com.i4o.dms.itldis.service.jobcard.dto.JobcardSearchResponseDto;
import com.i4o.dms.itldis.service.report.model.CustomerMachineMasterReportResponse;
import com.i4o.dms.itldis.service.report.model.FillRatioItemReportResponseDto;
import com.i4o.dms.itldis.service.report.model.FillRatioReportResponseDto;
import com.i4o.dms.itldis.service.report.model.InstallationMonitoringBoardResponseDto;
import com.i4o.dms.itldis.service.report.model.ServiceMonitoringBoardResponseDto;
import com.i4o.dms.itldis.service.report.model.ServiceReportRequestModel;
import com.i4o.dms.itldis.service.report.repository.ServiceReportRepo;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;
@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/service/report")
public class ServiceReportController {

	@Autowired
	private ServiceReportRepo repo;
	@Autowired
	private UserAuthentication userAuthentication;
	
	@GetMapping("/getBranchesUnderUser")
	public ResponseEntity<?> getBranchesUnderUser(@RequestParam(required=false)Long dealerId){
		if(dealerId==null)dealerId=0l;
		ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<List<Map<String,Object>>>();
		List<Map<String, Object>> result = repo.getBranchesUnderUser(userAuthentication.getUsername(), 'N', dealerId);
        apiResponse.setMessage("Branches get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/searchSMB")
	public ResponseEntity<?> getSMBRecords(@RequestBody ServiceReportRequestModel requestModel){
		ApiResponse<List<ServiceMonitoringBoardResponseDto>> apiResponse = new ApiResponse<List<ServiceMonitoringBoardResponseDto>>();
		List<ServiceMonitoringBoardResponseDto> result = repo.getMonitoringBoardRecords(userAuthentication.getUsername(), 
				requestModel.getDealerId(),
				requestModel.getBranchId(),
				requestModel.getOrgHierId(),
				requestModel.getProduct(),
				requestModel.getSeries(),
				requestModel.getModel(),
				requestModel.getSubModel(),
				requestModel.getVariant(),
				requestModel.getChassisNo(),
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
        apiResponse.setMessage("SMB Records get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/searchIMB")
	public ResponseEntity<?> getIMBRecords(@RequestBody ServiceReportRequestModel requestModel){
		ApiResponse<List<InstallationMonitoringBoardResponseDto>> apiResponse = new ApiResponse<List<InstallationMonitoringBoardResponseDto>>();
		List<InstallationMonitoringBoardResponseDto> result = repo.getInstallationMonitoringBoardRecords(userAuthentication.getUsername(), 
				requestModel.getDealerId(),
				requestModel.getBranchId(),
				requestModel.getOrgHierId(),
				requestModel.getProduct(),
				requestModel.getSeries(),
				requestModel.getModel(),
				requestModel.getSubModel(),
				requestModel.getVariant(),
				requestModel.getChassisNo(),
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
        apiResponse.setMessage("IMB Records get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/exportIMB")
    public ResponseEntity<InputStreamResource> imbExcelReport(@RequestBody ServiceReportRequestModel requestModel, HttpServletResponse response) throws IOException{
    	Integer size = Integer.MAX_VALUE-1;
    	List<InstallationMonitoringBoardResponseDto> result = repo.getInstallationMonitoringBoardRecords(userAuthentication.getUsername(), 
				requestModel.getDealerId(),
				requestModel.getBranchId(),
				requestModel.getOrgHierId(),
				requestModel.getProduct(),
				requestModel.getSeries(),
				requestModel.getModel(),
				requestModel.getSubModel(),
				requestModel.getVariant(),
				requestModel.getChassisNo(),
				requestModel.getFromDate(),
				requestModel.getToDate(),
				requestModel.getPage(),
				size
				);
    	ByteArrayInputStream in = ExcelCellGenerator.imbExcelReport(result);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "IMB_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
	
	@PostMapping("/exportSMB")
    public ResponseEntity<InputStreamResource> smbExcelReport(@RequestBody ServiceReportRequestModel requestModel, HttpServletResponse response) throws IOException{
    	Integer size = Integer.MAX_VALUE-1;
    	List<ServiceMonitoringBoardResponseDto> result = repo.getMonitoringBoardRecords(userAuthentication.getUsername(), 
				requestModel.getDealerId(),
				requestModel.getBranchId(),
				requestModel.getOrgHierId(),
				requestModel.getProduct(),
				requestModel.getSeries(),
				requestModel.getModel(),
				requestModel.getSubModel(),
				requestModel.getVariant(),
				requestModel.getChassisNo(),
				requestModel.getFromDate(),
				requestModel.getToDate(),
				requestModel.getPage(),
				size
				);
    	ByteArrayInputStream in = ExcelCellGenerator.smbExcelReport(result);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "SMB_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

	@PostMapping("/fillRatioReport")
	public ResponseEntity<?> getFillRatioReport(@RequestBody ServiceReportRequestModel requestModel){
		ApiResponse<List<FillRatioReportResponseDto>> apiResponse = new ApiResponse<List<FillRatioReportResponseDto>>();
		List<FillRatioReportResponseDto> result = repo.getFillRatioRecords(requestModel.getJfromDate(),
				requestModel.getJtoDate(),
				requestModel.getCfromDate(),
				requestModel.getCtoDate(),
				requestModel.getDealerId(),
				requestModel.getBranchId(),
				requestModel.getOrgHierId(),
				userAuthentication.getUsername(),
				requestModel.getPage(),
				requestModel.getSize()
				);
		Long count = 0l;
		if(result!=null && !result.isEmpty()){
			count = result.get(0).getRecordsCount();
		}
		apiResponse.setCount(count);
        apiResponse.setMessage("Fill Ratio Records get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/exportFillRatioReport")
    public ResponseEntity<InputStreamResource> exportFillRatioReport(@RequestBody ServiceReportRequestModel requestModel, HttpServletResponse response) throws IOException{
    	Integer size = Integer.MAX_VALUE-1;
    	List<FillRatioReportResponseDto> result = repo.getFillRatioRecords(requestModel.getJfromDate(),
				requestModel.getJtoDate(),
				requestModel.getCfromDate(),
				requestModel.getCtoDate(),
				requestModel.getDealerId(),
				requestModel.getBranchId(),
				requestModel.getOrgHierId(),
				userAuthentication.getUsername(),
				requestModel.getPage(),
				size
				);
    	List<FillRatioItemReportResponseDto> itemrecords = repo.getFillRatioRecordsItemDetails(requestModel.getJfromDate(),
				requestModel.getJtoDate(),
				requestModel.getCfromDate(),
				requestModel.getCtoDate(),
				requestModel.getDealerId(),
				requestModel.getBranchId(),
				requestModel.getOrgHierId(),
				userAuthentication.getUsername(),
				requestModel.getPage(),
				size,
				"Details"
				);
    	ByteArrayInputStream in = ExcelCellGenerator.fillRatioExcelReport(result, itemrecords);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "FillRatio_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
	@PostMapping("/customerMachineMasterReport")
	public ResponseEntity<?> getCustomerMachineMasterReport(@RequestBody ServiceReportRequestModel requestModel){
		ApiResponse<List<CustomerMachineMasterReportResponse>> apiResponse = new ApiResponse<List<CustomerMachineMasterReportResponse>>();
		List<CustomerMachineMasterReportResponse> result = repo.getCustomerMachineMasterRecords(
				requestModel.getCustomerId(),
				requestModel.getDealerId(),
				requestModel.getBranchId(),
				requestModel.getOrgHierId(),
				userAuthentication.getUsername(),
				requestModel.getFromDate(),		//Suraj--09/12/2022
				requestModel.getToDate(),		//Suraj--09/12/2022
				requestModel.getPage(),
				requestModel.getSize(),
				"view"
				);
		Long count = 0l;
		if(result!=null && !result.isEmpty()){
			count = result.get(0).getRecordsCount();
		}
		apiResponse.setCount(count);
        apiResponse.setMessage("Customer Machine Master Records get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/exportCustomerMachineMasterReport")
    public ResponseEntity<InputStreamResource> exportCustomerMachineMasterReport(@RequestBody ServiceReportRequestModel requestModel, HttpServletResponse response) throws IOException{
		Integer size = Integer.MAX_VALUE-1;
    	List<CustomerMachineMasterReportResponse> result = repo.getCustomerMachineMasterRecords(
				requestModel.getCustomerId(),
				requestModel.getDealerId(),
				requestModel.getBranchId(),
				requestModel.getOrgHierId(),
				userAuthentication.getUsername(),
				requestModel.getFromDate(),		//Suraj--09/12/2022
				requestModel.getToDate(),		//Suraj--09/12/2022
				requestModel.getPage(),
//				requestModel.getSize(),
				size,
				"excel"
				);
    	ByteArrayInputStream in = ExcelCellGenerator.customerMachineMasterExcelReport(result);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "CustomerMachineMaster_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}
