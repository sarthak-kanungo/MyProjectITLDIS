package com.i4o.dms.itldis.salesandpresales.reports;

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

import com.i4o.dms.itldis.salesandpresales.reports.dto.MachineMasterReportResponse;
import com.i4o.dms.itldis.salesandpresales.reports.dto.SalesReportDto;
import com.i4o.dms.itldis.salesandpresales.reports.repo.SalersReportRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/reports")
@Slf4j
public class MachineMasterReportController {
	

	@Autowired
	private SalersReportRepo salersReportRepo;
	
	@Autowired
	private UserAuthentication userAuthentication;
	
	@PostMapping("/searchMachineMasterReport")
	public ResponseEntity<?> machineMasterReport(@RequestBody SalesReportDto machineMasterReport) {
		ApiResponse<List<MachineMasterReportResponse>> response = new ApiResponse<>();
		Long count = 0l;
		List<MachineMasterReportResponse> qaReportResponse = salersReportRepo.machineMasterReport(userAuthentication.getManagementAccess(), machineMasterReport.getDealer(),
						machineMasterReport.getHoUser(), userAuthentication.getDealerEmployeeId(),
						machineMasterReport.getModel(), machineMasterReport.getSubModel(), machineMasterReport.getProduct(),
						machineMasterReport.getProductGroup(),machineMasterReport.getVariant(), machineMasterReport.getSeries(),
						machineMasterReport.getItemCode(),machineMasterReport.getPage(), machineMasterReport.getSize(),
						machineMasterReport.getHierId(),userAuthentication.getUsername(),"view");
		
		if (qaReportResponse != null && qaReportResponse.size() > 0) {
			count = qaReportResponse.get(0).getRecordCount();
		}

		response.setResult(qaReportResponse);
		response.setMessage("Data get successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setCount(count);
		return ResponseEntity.ok(response);
	}
	
    @PostMapping("/downloadMachineMasterReport")
    public ResponseEntity<InputStreamResource> downloadMachineMasterReport(@RequestBody SalesReportDto mmrExcel, HttpServletResponse response) throws IOException{
    	
    	List<MachineMasterReportResponse> mmrResponse = salersReportRepo.machineMasterReport(userAuthentication.getManagementAccess(), mmrExcel.getDealer(),
    			mmrExcel.getHoUser(), userAuthentication.getDealerEmployeeId(),
    			mmrExcel.getModel(), mmrExcel.getSubModel(), mmrExcel.getProduct(),
    			mmrExcel.getProductGroup(),mmrExcel.getVariant(), mmrExcel.getSeries(),
    			mmrExcel.getItemCode(),mmrExcel.getPage(), mmrExcel.getSize(),
    			mmrExcel.getHierId(),userAuthentication.getUsername(),"excel");
    	
		
    	ByteArrayInputStream in = ExcelCellGenerator.machineMasterReport(mmrResponse);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "MAchine_Master_Report_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

}
