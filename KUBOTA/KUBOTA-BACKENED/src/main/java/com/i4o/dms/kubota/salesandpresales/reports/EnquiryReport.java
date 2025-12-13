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

import com.i4o.dms.kubota.salesandpresales.reports.dto.EnquiryReportSearchResponse;
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
public class EnquiryReport {
	
	@Autowired
	private SalersReportRepo salersReportRepo;
	
	@Autowired
	private UserAuthentication userAuthentication;

	@RequestMapping(value="/searchEnquiryReport")
	public ResponseEntity<?> searchEnquiryReport(@RequestBody SalesReportDto claimReport) {
		ApiResponse<List<EnquiryReportSearchResponse>> response = new ApiResponse<>();
		Long count = 0l;
		List<EnquiryReportSearchResponse> qaReportResponse = salersReportRepo.enquiryReport(
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
				claimReport.getFinanceType(),
				claimReport.getFinancier(),
				claimReport.getSubsidy(),
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

	@PostMapping("/downloadEnquiryReport")
    public ResponseEntity<InputStreamResource> downloadEnquiryReport(@RequestBody SalesReportDto claimReport, HttpServletResponse response) throws IOException{
    	
		List<EnquiryReportSearchResponse> reportResponse = salersReportRepo.enquiryReport(
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
				claimReport.getFinanceType(),
				claimReport.getFinancier(),
				claimReport.getSubsidy(),
				claimReport.getPage(), 
				claimReport.getSize(),
				claimReport.getOrgHierId(),
				userAuthentication.getUsername(),
				"excel");
    	
		
    	ByteArrayInputStream in = ExcelCellGenerator.enquiryReport(reportResponse);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "Enquiry_Report_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}
