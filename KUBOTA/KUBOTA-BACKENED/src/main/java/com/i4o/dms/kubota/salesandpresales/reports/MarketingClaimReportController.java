package com.i4o.dms.kubota.salesandpresales.reports;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

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

import com.i4o.dms.kubota.salesandpresales.reports.dto.ActivityClaimStatusReportResponse;
import com.i4o.dms.kubota.salesandpresales.reports.dto.ActivityPropoalStatusReportResponse;
import com.i4o.dms.kubota.salesandpresales.reports.dto.ActivityReportStatusReportReponse;
import com.i4o.dms.kubota.salesandpresales.reports.dto.MachineMasterReportResponse;
import com.i4o.dms.kubota.salesandpresales.reports.dto.MarketingClaimReportResponse;
import com.i4o.dms.kubota.salesandpresales.reports.dto.SalesReportDto;
import com.i4o.dms.kubota.salesandpresales.reports.repo.SalersReportRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.ExcelCellGenerator;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/reports")
public class MarketingClaimReportController {
	
	@Autowired
	private SalersReportRepo salersReportRepo;
	
	@Autowired
	private UserAuthentication userAuthentication;
	
	
	@PostMapping("/searchMarketingClaimReport")
	public ResponseEntity<?> searchMarketingClaimReport(@RequestBody SalesReportDto claimReport) {
		ApiResponse<List<MarketingClaimReportResponse>> response = new ApiResponse<>();
		Long count = 0l;
		List<MarketingClaimReportResponse> qaReportResponse = salersReportRepo.marketingClaimReport(
				userAuthentication.getManagementAccess(), 
				claimReport.getDealerId(), 
				claimReport.getBranchId(),
				claimReport.getProduct(), 
				claimReport.getSeries(),
				claimReport.getModel(), 
				claimReport.getSubModel(), 
				claimReport.getVariant(), 
				claimReport.getFromDate(),
				claimReport.getToDate(),
				claimReport.getActivityNumber(),
				claimReport.getActivityStatus(),
				claimReport.getActivityClaimNumber(),
				claimReport.getActivityClaimStatus(),
				claimReport.getPage(), 
				claimReport.getSize(),
				claimReport.getOrgHierId(),
				userAuthentication.getUsername(),
				"view");
		
		if (qaReportResponse != null && qaReportResponse.size() > 0) {
			count = qaReportResponse.get(0).getRecordCount();
		}

		response.setResult(qaReportResponse);
		response.setMessage("Data get successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setCount(count);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/searchActivityReportStatusReport")
	public ResponseEntity<?> searchActivityReportStatusReport(@RequestBody SalesReportDto claimReport) {
		ApiResponse<List<ActivityReportStatusReportReponse>> response = new ApiResponse<>();
		Long count = 0l;
		List<ActivityReportStatusReportReponse> qaReportResponse = salersReportRepo.activityReportStatusReport(
				userAuthentication.getManagementAccess(), 
				claimReport.getDealerId(), 
				claimReport.getBranchId(),
				claimReport.getProduct(), 
				claimReport.getSeries(),
				claimReport.getModel(), 
				claimReport.getSubModel(), 
				claimReport.getVariant(), 
				claimReport.getActivityDate(),
				claimReport.getActivityNumber(),
				claimReport.getActivityStatus(),
				claimReport.getPage(), 
				claimReport.getSize(),
				claimReport.getOrgHierId(),
				userAuthentication.getUsername(),
				"view");
		
		if (qaReportResponse != null && qaReportResponse.size() > 0) {
			count = qaReportResponse.get(0).getRecordCount();
		}

		response.setResult(qaReportResponse);
		response.setMessage("Data get successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setCount(count);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/searchActivityProposalStatusReport")
	public ResponseEntity<?> searchActivityProposalStatusReport(@RequestBody SalesReportDto claimReport) {
		ApiResponse<List<ActivityPropoalStatusReportResponse>> response = new ApiResponse<>();
		Long count = 0l;
		List<ActivityPropoalStatusReportResponse> qaReportResponse = salersReportRepo.activityProposalStatusReport(
				userAuthentication.getManagementAccess(), 
				claimReport.getDealerId(), 
				claimReport.getBranchId(),
				claimReport.getProduct(), 
				claimReport.getSeries(),
				claimReport.getModel(), 
				claimReport.getSubModel(), 
				claimReport.getVariant(), 
				claimReport.getActivityDate(),
				claimReport.getActivityNumber(),
				claimReport.getActivityStatus(),
				claimReport.getPage(), 
				claimReport.getSize(),
				claimReport.getOrgHierId(),
				userAuthentication.getUsername(),
				"view");
		
		if (qaReportResponse != null && qaReportResponse.size() > 0) {
			count = qaReportResponse.get(0).getRecordCount();
		}

		response.setResult(qaReportResponse);
		response.setMessage("Data get successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setCount(count);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/searchActivityClaimStatusReport")
	public ResponseEntity<?> searchActivityClaimStatusReport(@RequestBody SalesReportDto claimReport) {
		ApiResponse<List<ActivityClaimStatusReportResponse>> response = new ApiResponse<>();
		Long count = 0l;
		List<ActivityClaimStatusReportResponse> qaReportResponse = salersReportRepo.activityClaimStatusReport(
				userAuthentication.getManagementAccess(), 
				claimReport.getDealerId(), 
				claimReport.getBranchId(),
				claimReport.getProduct(), 
				claimReport.getSeries(),
				claimReport.getModel(), 
				claimReport.getSubModel(), 
				claimReport.getVariant(), 
				claimReport.getActivityDate(),
				claimReport.getActivityNumber(),
				claimReport.getActivityStatus(),
				claimReport.getPage(), 
				claimReport.getSize(),
				claimReport.getOrgHierId(),
				userAuthentication.getUsername(),
				"view");
		
		if (qaReportResponse != null && qaReportResponse.size() > 0) {
			count = qaReportResponse.get(0).getRecordCount();
		}

		response.setResult(qaReportResponse);
		response.setMessage("Data get successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setCount(count);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/exportMarketingClaimReport")
    public ResponseEntity<InputStreamResource> exportMarketingClaimReport(@RequestBody SalesReportDto claimReport, HttpServletResponse response) throws IOException{
    	
		List<MarketingClaimReportResponse> qaReportResponse = salersReportRepo.marketingClaimReport(
				userAuthentication.getManagementAccess(), 
				claimReport.getDealerId(), 
				claimReport.getBranchId(),
				claimReport.getProduct(), 
				claimReport.getSeries(),
				claimReport.getModel(), 
				claimReport.getSubModel(), 
				claimReport.getVariant(), 
				claimReport.getFromDate(),
				claimReport.getToDate(),
				claimReport.getActivityNumber(),
				claimReport.getActivityStatus(),
				claimReport.getActivityClaimNumber(),
				claimReport.getActivityClaimStatus(),
				claimReport.getPage(), 
				claimReport.getSize(),
				claimReport.getOrgHierId(),
				userAuthentication.getUsername(),
				"excel");
    	
    	ByteArrayInputStream in = ExcelCellGenerator.marketingClaimReport(qaReportResponse);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "Marketing_Claim_Report"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
	
	@PostMapping("/exportActivityProposalStatusReport")
    public ResponseEntity<InputStreamResource> exportActivityProposalStatusReport(@RequestBody SalesReportDto claimReport, HttpServletResponse response) throws IOException{
    	
		List<ActivityPropoalStatusReportResponse> reportResponse = salersReportRepo.activityProposalStatusReport(
				userAuthentication.getManagementAccess(), 
				claimReport.getDealerId(), 
				claimReport.getBranchId(),
				claimReport.getProduct(), 
				claimReport.getSeries(),
				claimReport.getModel(), 
				claimReport.getSubModel(), 
				claimReport.getVariant(), 
				claimReport.getActivityDate(),
				claimReport.getActivityNumber(),
				claimReport.getActivityStatus(),
				claimReport.getPage(), 
				claimReport.getSize(),
				claimReport.getOrgHierId(),
				userAuthentication.getUsername(),
				"excel");
		
    	ByteArrayInputStream in = ExcelCellGenerator.activityProposalStatusReport(reportResponse);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "Activity_Proposal_Status_Report"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
	
	@PostMapping("/exportActivityClaimStatusReport")
    public ResponseEntity<InputStreamResource> exportActivityClaimStatusReport(@RequestBody SalesReportDto claimReport, HttpServletResponse response) throws IOException{
    	
		List<ActivityClaimStatusReportResponse> reportResponse = salersReportRepo.activityClaimStatusReport(
				userAuthentication.getManagementAccess(), 
				claimReport.getDealerId(), 
				claimReport.getBranchId(),
				claimReport.getProduct(), 
				claimReport.getSeries(),
				claimReport.getModel(), 
				claimReport.getSubModel(), 
				claimReport.getVariant(), 
				claimReport.getActivityDate(),
				claimReport.getActivityNumber(),
				claimReport.getActivityStatus(),
				claimReport.getPage(), 
				claimReport.getSize(),
				claimReport.getOrgHierId(),
				userAuthentication.getUsername(),
				"excel");
    	
    	ByteArrayInputStream in = ExcelCellGenerator.activityClaimStatusReport(reportResponse);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "Activity_Claim_Status_Report"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
	
	@PostMapping("/exportActivityReportStatusReport")
    public ResponseEntity<InputStreamResource> exportActivityReportStatusReport(@RequestBody SalesReportDto claimReport, HttpServletResponse response) throws IOException{
    	
		List<ActivityReportStatusReportReponse> reportResponse = salersReportRepo.activityReportStatusReport(
				userAuthentication.getManagementAccess(), 
				claimReport.getDealerId(), 
				claimReport.getBranchId(),
				claimReport.getProduct(), 
				claimReport.getSeries(),
				claimReport.getModel(), 
				claimReport.getSubModel(), 
				claimReport.getVariant(), 
				claimReport.getActivityDate(),
				claimReport.getActivityNumber(),
				claimReport.getActivityStatus(),
				claimReport.getPage(), 
				claimReport.getSize(),
				claimReport.getOrgHierId(),
				userAuthentication.getUsername(),
				"excel");
		
    	ByteArrayInputStream in = ExcelCellGenerator.activityReportStatusReport(reportResponse);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "Activity_Report_Status_Report"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
	
}
