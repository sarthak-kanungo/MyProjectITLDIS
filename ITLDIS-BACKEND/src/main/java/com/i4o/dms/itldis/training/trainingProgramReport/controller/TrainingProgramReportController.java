package com.i4o.dms.itldis.training.trainingProgramReport.controller;

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
import com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.dto.SearchSparePurchaseOrder;
import com.i4o.dms.itldis.training.trainingProgramReport.dto.TrainingProgramReportExcelDto;
import com.i4o.dms.itldis.training.trainingProgramReport.dto.TrainingProgramReportSearchResponse;
import com.i4o.dms.itldis.training.trainingProgramReport.repo.TrainingProgramReportRepo;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })

@RequestMapping("/api/training/training-program-report")
public class TrainingProgramReportController {
	
	@Autowired
	private UserAuthentication userAuthentication;
	
	@Autowired
	private TrainingProgramReportRepo tprRepo;
	
	@GetMapping(value = "/getZoneRegionForTrainingReport")
	ResponseEntity<ApiResponse<Object>> trainingProgramRegion(@RequestParam("levelId") Long levelId,@RequestParam("orghierId") Long  orghierId) {
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> trZone = tprRepo.getZoneForTrainingReport(userAuthentication.getUsername(),levelId,orghierId,'N');
		apiResponse.setResult(trZone);
		apiResponse.setMessage("Data Get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	
	
    @PostMapping("/downloadTrainingReportExcel")
    public ResponseEntity<InputStreamResource> trainingReportExcel(@RequestBody TrainingProgramReportExcelDto tprExcel, HttpServletResponse response) throws IOException{
    	Integer size = Integer.MAX_VALUE-1;
    	List<TrainingProgramReportSearchResponse>  list =  tprRepo.downloadTrainingReportExcel(userAuthentication.getUsername(),
    													tprExcel.getState(), tprExcel.getTsmName(), tprExcel.getDealerName(), tprExcel.getDelearEmpDesignation(),
    													tprExcel.getEmployeeStatus(), tprExcel.getTypeofTraining(), tprExcel.getTrainingModule(),
    													tprExcel.getTrainingStartDate(),tprExcel.getTrainingEndDate());
    	
    	ByteArrayInputStream in = ExcelCellGenerator.trainingProgramExcelReport(list);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "TrainingProgramReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
    
    @PostMapping("/trainingReportSearch")
    public ResponseEntity<?> trainingReportSearch(@RequestBody TrainingProgramReportExcelDto trSearch){
		ApiResponse<Object> response = new ApiResponse<>();
		Long count = 0l;
    	List<TrainingProgramReportSearchResponse>  search =  tprRepo.downloadTrainingReportExcel(userAuthentication.getUsername(),
    			trSearch.getState(), trSearch.getTsmName(), trSearch.getDealerName(), trSearch.getDelearEmpDesignation(),
    			trSearch.getEmployeeStatus(), trSearch.getTypeofTraining(), trSearch.getTrainingModule(),
    			trSearch.getTrainingStartDate(),trSearch.getTrainingEndDate());
    	
		if (search != null && search.size() > 0) {
			count = search.get(0).getRecordCount();
		}
		
		response.setMessage("Training Report Search get successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(search);
		response.setCount(count);
		return ResponseEntity.ok(response);
    }

}
