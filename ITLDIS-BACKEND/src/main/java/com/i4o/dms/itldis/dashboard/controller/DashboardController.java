package com.i4o.dms.itldis.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.dashboard.dto.DashboardActivityReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardComplaintReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardInstallationReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardJobCardReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardPresaleServiceReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardSalesActivityReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardSalesComplaintReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardSalesEnquiryReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardWarrantyActivityReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardWarrantyComplaintReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardWarrantyEnquiryReportDto;
import com.i4o.dms.itldis.dashboard.dto.SalesRetailReportDto;
import com.i4o.dms.itldis.dashboard.dto.SalesStockReportDto;
import com.i4o.dms.itldis.dashboard.dto.WarrantyRetailReportDto;
import com.i4o.dms.itldis.dashboard.model.DashboardSearchRequestModel;
import com.i4o.dms.itldis.dashboard.repo.DashboardRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping("/api/dashboard")
public class DashboardController {

	@Autowired
	private DashboardRepo repo;
	@Autowired
	private UserAuthentication userAuthentication;

	@PostMapping("/serviceReport")
	public ResponseEntity<?> serviceReport(@RequestBody DashboardSearchRequestModel requestModel) {
		ApiResponse<Map<String, List<?>>> apiResponse = new ApiResponse<Map<String, List<?>>>();
		List<DashboardJobCardReportDto> jobcardReport = repo.getDashboardJobCardReport(userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId()
				);
		List<DashboardInstallationReportDto> installationReport = repo.getDashboardInstallationReport(userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId()
				);
		List<DashboardActivityReportDto> activityReport = repo.getDashboardServiceActivityReport(userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId()
				);
		List<DashboardComplaintReportDto> complaintReport = repo.getDashboardServiceComplaintReport(userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId()
				);
		List<DashboardPresaleServiceReportDto> presaleServiceReport = repo.getDashboardPresalesServiceReport(userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId()
				);
		
		Map<String, List<?>> result = new HashMap<>();
		result.put("jobcardReport", jobcardReport);
		result.put("installationReport", installationReport);
		result.put("activityReport", activityReport);
		result.put("complaintReport", complaintReport);
		result.put("presaleServiceReport", presaleServiceReport);
		apiResponse.setMessage("Dashboard Service Report");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(result);
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/salesReport")
	public ResponseEntity<?> salesReport(@RequestBody DashboardSearchRequestModel requestModel) {
		ApiResponse<Map<String, List<?>>> apiResponse = new ApiResponse<Map<String, List<?>>>();
		List<DashboardSalesEnquiryReportDto> enquiryReport = repo.getDashboardEnquiryReport(userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId()
				);
		List<DashboardSalesActivityReportDto> activityDealerOwnReport = repo.getDashboardSalesActivityDealerOwnReport(userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId()
				);
		List<DashboardSalesActivityReportDto> activityKubotaSupportedReport = repo.getDashboardSalesActivityKubotaSupportReport(userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId()
				);
		List<DashboardSalesComplaintReportDto> complaintReport = repo.getDashboardSalesComplaintReport(userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId()
				);
		List<SalesStockReportDto> stockReport = repo.getSalesStockReport(requestModel.getSalesReportOption(),
				userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId()
				);
		List<SalesRetailReportDto> retailReport = repo.getSalesRetailReport(requestModel.getSalesReportOption(),
				userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId()
				);
		Map<String, List<?>> result = new HashMap<>();
		result.put("enquiryReport", enquiryReport);
		result.put("activityDealerOwnReport", activityDealerOwnReport);
		result.put("activityKubotaSupportedReport", activityKubotaSupportedReport);
		result.put("complaintReport", complaintReport);
		result.put("stockReport", stockReport);
		result.put("retailReport", retailReport);
		apiResponse.setMessage("Dashboard Sales Report");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(result);
		return ResponseEntity.ok(apiResponse);
	}

	@PostMapping("/marketingReport")
	public ResponseEntity<?> marketingReport(@RequestBody DashboardSearchRequestModel requestModel) {
		ApiResponse<Map<String, List<?>>> apiResponse = new ApiResponse<Map<String, List<?>>>();
		System.out.println("requestModel.getMReportType() :"+requestModel.getMReportType());
		List<Map<String,Object>> budgetStatusReport = repo.getDashboardMarketingReport(requestModel.getBudgetReportType(),
				userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId(),
				"BUDGET_STATUS"
				);
		List<Map<String,Object>> proposalStatusReport = repo.getDashboardMarketingReport(requestModel.getMReportType(),
				userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId(),
				"PROPOSAL_STATUS"
				);
		List<Map<String,Object>> enquiryStatusReport = repo.getDashboardMarketingReport(requestModel.getMReportType(),
				userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId(),
				"ENQUIRY_STATUS"
				);
		List<Map<String,Object>> claimStatusReport = repo.getDashboardMarketingReport(requestModel.getMReportType(),
				userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId(),
				"CLAIM_STATUS"
				);
		Map<String, List<?>> result = new HashMap<>();
		result.put("budgetStatusReport", budgetStatusReport);
		result.put("proposalStatusReport", proposalStatusReport);
		result.put("enquiryStatusReport", enquiryStatusReport);
		result.put("claimStatusReport", claimStatusReport);
		apiResponse.setMessage("Dashboard Sales Report");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(result);
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/enquiryStatusReport")
	public ResponseEntity<?> enquiryStatusReport(@RequestBody DashboardSearchRequestModel requestModel) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
	
		List<Map<String,Object>> enquiryStatusReport = repo.getDashboardMarketingReport(requestModel.getMReportType(),
				userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId(),
				"ENQUIRY_STATUS"
				);
		apiResponse.setMessage("Dashboard Sales Report");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(enquiryStatusReport);
		return ResponseEntity.ok(apiResponse);
	}
	
	
	@PostMapping("/warrantyReport")
	public ResponseEntity<?> warrantyReport(@RequestBody DashboardSearchRequestModel requestModel) {
		ApiResponse<Map<String, List<?>>> apiResponse = new ApiResponse<Map<String, List<?>>>();
		List<Map<String,Object>> pcrStatusReport = repo.getDashboardWarrantyReport(userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId(),
				"PCR_STATUS"
				);
		List<Map<String,Object>> wcrStatusReport = repo.getDashboardWarrantyReport(userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId(),
				"WCR_STATUS"
				);
		List<Map<String,Object>> settlementStatusReport = repo.getDashboardWarrantyReport(userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId(),
				"SETTLEMENT_STATUS"
				);
		List<Map<String,Object>> goodwillStatusReport = repo.getDashboardWarrantyReport(userAuthentication.getUsername(),
				requestModel.getFromDate(), 
				requestModel.getToDate(),
				requestModel.getProduct(), 
				requestModel.getSeries(), 
				requestModel.getModel(), 
				requestModel.getSubModel(), 
				requestModel.getVariant(), 
				requestModel.getDealerId(),
				requestModel.getOrgHierId(), 
				requestModel.getBranchId(),
				"GOODWILL_STATUS"
				);
		Map<String, List<?>> result = new HashMap<>();
		result.put("settlementStatusReport", settlementStatusReport);
		result.put("wcrStatusReport", wcrStatusReport);
		result.put("pcrStatusReport", pcrStatusReport);
		result.put("goodwillStatusReport", goodwillStatusReport);
		apiResponse.setMessage("Dashboard Sales Report");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(result);
		return ResponseEntity.ok(apiResponse);
	}
}
