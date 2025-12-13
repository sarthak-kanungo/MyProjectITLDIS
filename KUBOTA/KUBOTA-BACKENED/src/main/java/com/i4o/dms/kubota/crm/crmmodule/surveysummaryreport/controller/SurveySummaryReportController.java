package com.i4o.dms.kubota.crm.crmmodule.surveysummaryreport.controller;

import static com.i4o.dms.kubota.utils.Constants.MESSAGE;
import static com.i4o.dms.kubota.utils.Constants.RESULT;
import static com.i4o.dms.kubota.utils.Constants.STATUS;
import static com.i4o.dms.kubota.utils.Constants.SUCCESS;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
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

import com.i4o.dms.kubota.crm.crmmodule.surveysummaryreport.repository.SurveySummaryReportRepo;
import com.i4o.dms.kubota.crm.crmmodule.surveysummaryreport.response.QAReportResponse;
import com.i4o.dms.kubota.crm.crmmodule.surveysummaryreport.response.SurveySummaryReportDissatisfiedResponse;
import com.i4o.dms.kubota.crm.crmmodule.surveysummaryreport.response.SurveySummaryReportDto;
import com.i4o.dms.kubota.crm.crmmodule.surveysummaryreport.response.SurveySummaryReportResponse;
import com.i4o.dms.kubota.crm.crmmodule.surveysummaryreport.response.SurveySummaryReportWithQuationariesResponse;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.ExcelCellGenerator;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping("/api/crm/crmmodule/surveysummaryreport")

public class SurveySummaryReportController {

	@Autowired
	private SurveySummaryReportRepo surveySummaryReportRepo;

	@Autowired
	private UserAuthentication userAuthentication;

	@GetMapping(value = "/getSurveyName")
	public ResponseEntity<?> getSurveyName() {
		Map<String, Object> map = new HashMap<>();

		map.put(MESSAGE, "Survey Name ");
		map.put(STATUS, SUCCESS);
		map.put(RESULT, surveySummaryReportRepo.getSurveyName());
		return ResponseEntity.ok(map);
	}

	@GetMapping(value = "/getQASurveyName")
	public ResponseEntity<?> getQASurveyName() {
		Map<String, Object> map = new HashMap<>();

		map.put(MESSAGE, "Survey Name ");
		map.put(STATUS, SUCCESS);
		map.put(RESULT, surveySummaryReportRepo.getSurveyNameQAReport());
		return ResponseEntity.ok(map);
	}
	
	@GetMapping(value = "/getQuestion")
	public ResponseEntity<?> getQuestionList() {
		Map<String, Object> map = new HashMap<>();

		map.put(MESSAGE, "Question list get Successfully");
		map.put(STATUS, SUCCESS);
		map.put(RESULT, surveySummaryReportRepo.getQuestionList());
		return ResponseEntity.ok(map);
	}
	
	
	
	@GetMapping(value = "/getSurveyDate")
	public ResponseEntity<?> getSurveyDate() {
		Map<String, Object> map = new HashMap<>();

		map.put(MESSAGE, "Survey Date ");
		map.put(STATUS, SUCCESS);
		map.put(RESULT, surveySummaryReportRepo.getSurveyDate());
		return ResponseEntity.ok(map);
	}

	@GetMapping(value = "/getSurveySatisfaction")
	public ResponseEntity<?> getSurveySatisfaction() {
		Map<String, Object> map = new HashMap<>();

		map.put(MESSAGE, "Survey Satisfaction ");
		map.put(STATUS, SUCCESS);
		map.put(RESULT, surveySummaryReportRepo.getSurveySatisfaction());
		return ResponseEntity.ok(map);
	}

	@GetMapping(value = "/getSurveyStatus")
	public ResponseEntity<?> getSurveyStatus() {
		Map<String, Object> map = new HashMap<>();

		map.put(MESSAGE, "Survey Status ");
		map.put(STATUS, SUCCESS);
		map.put(RESULT, surveySummaryReportRepo.getSurveyStatus());
		return ResponseEntity.ok(map);
	}

	@PostMapping("/searchSurveySummaryReport")
	public ResponseEntity<?> searchSurveySummaryReport(@RequestBody SurveySummaryReportDto surveySummaryReportDto) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("data get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		
		/*
		 * System.out.println("vinayy--"+surveySummaryReportDto);
		 * System.out.println("vinayy--"+userAuthentication.getManagementAccess()+", "
		 * +userAuthentication.getDealerId()+", "+userAuthentication.getLoginId()+", "
		 * +userAuthentication.getDealerEmployeeId()+", "+userAuthentication.getUsername
		 * ());
		 */
		Long count = 0l;
		//if (surveySummaryReportResponse != null && surveySummaryReportResponse.size() > 0) {
			List<SurveySummaryReportWithQuationariesResponse> res1=null;
			List<SurveySummaryReportDissatisfiedResponse> res2=null; 
			List<SurveySummaryReportResponse> res3=null;
			if(surveySummaryReportDto.getReportType()!=null && surveySummaryReportDto.getReportType().equals("With Questionaries")){
				res1 = surveySummaryReportRepo
						.searchSurveySummaryReportWith(userAuthentication.getManagementAccess(), surveySummaryReportDto.getDealer(),
								userAuthentication.getLoginId(), userAuthentication.getDealerEmployeeId(),
								surveySummaryReportDto.getSurveyName(), surveySummaryReportDto.getSurveyStatus(),
								surveySummaryReportDto.getAsOnDate(), surveySummaryReportDto.getFromDate(),
								surveySummaryReportDto.getToDate(), surveySummaryReportDto.getHierId(),
								surveySummaryReportDto.getCustSatisfaction(),
								surveySummaryReportDto.getModel(), surveySummaryReportDto.getSubModel(),
								surveySummaryReportDto.getSurveyNo(),surveySummaryReportDto.getQuestion(), surveySummaryReportDto.getProduct(),
								surveySummaryReportDto.getVariant(),surveySummaryReportDto.getSeries(),surveySummaryReportDto.getReportType(),
								surveySummaryReportDto.getPage(), surveySummaryReportDto.getSize(),
								userAuthentication.getUsername(),surveySummaryReportDto.getChassisNo(),"view", surveySummaryReportDto.getSurveyType(),
								surveySummaryReportDto.getFromDcDate(), surveySummaryReportDto.getToDcDate());

				count = (res1!=null && res1.size()>0) ? res1.get(0).getRecordCount() : 0;
				apiResponse.setResult(res1);
			}else if(surveySummaryReportDto.getReportType()!=null && surveySummaryReportDto.getReportType().equals("Dissatisfied Report")){
				res2 = surveySummaryReportRepo
						.searchSurveySummaryReportDisatisfied(userAuthentication.getManagementAccess(), surveySummaryReportDto.getDealer(),
								userAuthentication.getLoginId(), userAuthentication.getDealerEmployeeId(),
								surveySummaryReportDto.getSurveyName(), surveySummaryReportDto.getSurveyStatus(),
								surveySummaryReportDto.getAsOnDate(), surveySummaryReportDto.getFromDate(),
								surveySummaryReportDto.getToDate(), surveySummaryReportDto.getHierId(),
								surveySummaryReportDto.getCustSatisfaction(),
								surveySummaryReportDto.getModel(), surveySummaryReportDto.getSubModel(),
								surveySummaryReportDto.getSurveyNo(),surveySummaryReportDto.getQuestion(), surveySummaryReportDto.getProduct(),
								surveySummaryReportDto.getVariant(),surveySummaryReportDto.getSeries(),surveySummaryReportDto.getReportType(),
								surveySummaryReportDto.getPage(), surveySummaryReportDto.getSize(),
								userAuthentication.getUsername(),surveySummaryReportDto.getChassisNo(),"view", surveySummaryReportDto.getSurveyType(),
								surveySummaryReportDto.getFromDcDate(), surveySummaryReportDto.getToDcDate());

				count = (res2!=null && res2.size()>0) ? res2.get(0).getRecordCount() : 0;
				apiResponse.setResult(res2);
			} else {
				res3 = surveySummaryReportRepo
						.searchSurveySummaryReport(userAuthentication.getManagementAccess(), surveySummaryReportDto.getDealer(),
								userAuthentication.getLoginId(), userAuthentication.getDealerEmployeeId(),
								surveySummaryReportDto.getSurveyName(), surveySummaryReportDto.getSurveyStatus(),
								surveySummaryReportDto.getAsOnDate(), surveySummaryReportDto.getFromDate(),
								surveySummaryReportDto.getToDate(), surveySummaryReportDto.getHierId(),
								surveySummaryReportDto.getCustSatisfaction(),
								surveySummaryReportDto.getModel(), surveySummaryReportDto.getSubModel(),
								surveySummaryReportDto.getSurveyNo(),surveySummaryReportDto.getQuestion(), surveySummaryReportDto.getProduct(),
								surveySummaryReportDto.getVariant(),surveySummaryReportDto.getSeries(),surveySummaryReportDto.getReportType(),
								surveySummaryReportDto.getPage(), surveySummaryReportDto.getSize(),
								userAuthentication.getUsername(),surveySummaryReportDto.getChassisNo(),"view", surveySummaryReportDto.getSurveyType(),
								surveySummaryReportDto.getFromDcDate(), surveySummaryReportDto.getToDcDate());

				count = (res3!=null && res3.size()>0) ? res3.get(0).getRecordCount() : 0;
				apiResponse.setResult(res3);
			}
		//}
		apiResponse.setCount(count);
		return ResponseEntity.ok(apiResponse);
	}

    @GetMapping("/getZone")
    public ResponseEntity<?> getZone() {
        ApiResponse apiResponse = new ApiResponse();
        List model1 = surveySummaryReportRepo.getZone(userAuthentication.getLoginId());
        apiResponse.setMessage("get zones");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(model1);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getRegions")
    public ResponseEntity<?> getRegions(@RequestParam("zoneId") Long zoneId) {
        ApiResponse apiResponse = new ApiResponse();
        List subModel = surveySummaryReportRepo.getRegions(userAuthentication.getLoginId(),zoneId);
        apiResponse.setMessage("get regions");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(subModel);
        return ResponseEntity.ok(apiResponse);

    }
    @GetMapping("/getAreas")
    public ResponseEntity<?> getAreas(@RequestParam("zoneId") Long zoneId,@RequestParam("regionId") Long regionId) {
        ApiResponse apiResponse = new ApiResponse();
        List model1 = surveySummaryReportRepo.getAreas(userAuthentication.getLoginId(),zoneId,regionId);
        apiResponse.setMessage("get areas");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(model1);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getTerritory")
    public ResponseEntity<?> getTerritory(@RequestParam("zoneId") Long zoneId,@RequestParam("regionId") Long regionId,
    		@RequestParam("areaId") Long areaId) {
        ApiResponse apiResponse = new ApiResponse();
        List subModel = surveySummaryReportRepo.getTerritory(userAuthentication.getLoginId(),zoneId,regionId,areaId);
        apiResponse.setMessage("get territories");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(subModel);
        return ResponseEntity.ok(apiResponse);

    }
    
    @GetMapping("/getChassisNo")
    public ResponseEntity<?> getChassisNoList(@RequestParam("chassisNo") String chassisNo) {
        ApiResponse apiResponse = new ApiResponse();
        List chassisList = surveySummaryReportRepo.getChassisNo(userAuthentication.getUsername(),null,chassisNo);
        apiResponse.setMessage("Get Chassis No Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(chassisList);
        return ResponseEntity.ok(apiResponse);

    }
    
    
    @GetMapping("/getSurveyNo")
    public ResponseEntity<?> getSurveyNoList(@RequestParam("surveyNo") String surveyNo) {
        ApiResponse apiResponse = new ApiResponse();
        List surveyNoList = surveySummaryReportRepo.getSurveyNo(userAuthentication.getUsername(),null,surveyNo);
        apiResponse.setMessage("Survey No Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(surveyNoList);
        return ResponseEntity.ok(apiResponse);

    }
    
    
    @PostMapping("/downloadSurveyDetailsExcelReport")
    public ResponseEntity<InputStreamResource> surveyDetailsExcelReport(@RequestBody SurveySummaryReportDto surveyDetailsExcel, HttpServletResponse response) throws IOException{
		
    	ByteArrayInputStream in =  null;
    	surveyDetailsExcel.setSize(Integer.MAX_VALUE);
    	List<SurveySummaryReportWithQuationariesResponse> res1=null;
		List<SurveySummaryReportDissatisfiedResponse> res2=null; 
		List<SurveySummaryReportResponse> res3=null;
    	if(surveyDetailsExcel.getReportType()!=null && surveyDetailsExcel.getReportType().equals("With Questionaries")){
			res1 = surveySummaryReportRepo.searchSurveySummaryReportWith(userAuthentication.getManagementAccess(), surveyDetailsExcel.getDealer(),
					userAuthentication.getLoginId(), userAuthentication.getDealerEmployeeId(),
					surveyDetailsExcel.getSurveyName(), surveyDetailsExcel.getSurveyStatus(),
					surveyDetailsExcel.getAsOnDate(), surveyDetailsExcel.getFromDate(),
					surveyDetailsExcel.getToDate(), surveyDetailsExcel.getHierId(),
					surveyDetailsExcel.getCustSatisfaction(),
					surveyDetailsExcel.getModel(), surveyDetailsExcel.getSubModel(),
					surveyDetailsExcel.getSurveyNo(),surveyDetailsExcel.getQuestion(), surveyDetailsExcel.getProduct(),
					surveyDetailsExcel.getVariant(),surveyDetailsExcel.getSeries(),surveyDetailsExcel.getReportType(),
					surveyDetailsExcel.getPage(), surveyDetailsExcel.getSize(),
					userAuthentication.getUsername(),surveyDetailsExcel.getChassisNo(),"excel", surveyDetailsExcel.getSurveyType(),
					surveyDetailsExcel.getFromDcDate(), surveyDetailsExcel.getToDcDate());
	    	in = ExcelCellGenerator.surveyDetailsExcelReportWith(res1);
		}else if(surveyDetailsExcel.getReportType()!=null && surveyDetailsExcel.getReportType().equals("Dissatisfied Report")){
			res2 = surveySummaryReportRepo.searchSurveySummaryReportDisatisfied(userAuthentication.getManagementAccess(), surveyDetailsExcel.getDealer(),
					userAuthentication.getLoginId(), userAuthentication.getDealerEmployeeId(),
					surveyDetailsExcel.getSurveyName(), surveyDetailsExcel.getSurveyStatus(),
					surveyDetailsExcel.getAsOnDate(), surveyDetailsExcel.getFromDate(),
					surveyDetailsExcel.getToDate(), surveyDetailsExcel.getHierId(),
					surveyDetailsExcel.getCustSatisfaction(),
					surveyDetailsExcel.getModel(), surveyDetailsExcel.getSubModel(),
					surveyDetailsExcel.getSurveyNo(),surveyDetailsExcel.getQuestion(), surveyDetailsExcel.getProduct(),
					surveyDetailsExcel.getVariant(),surveyDetailsExcel.getSeries(),surveyDetailsExcel.getReportType(),
					surveyDetailsExcel.getPage(), surveyDetailsExcel.getSize(),
					userAuthentication.getUsername(),surveyDetailsExcel.getChassisNo(),"excel", surveyDetailsExcel.getSurveyType(),
					surveyDetailsExcel.getFromDcDate(), surveyDetailsExcel.getToDcDate());
	    	in = ExcelCellGenerator.surveyDetailsExcelReportDisatisfied(res2);
		} else {
			res3 = surveySummaryReportRepo.searchSurveySummaryReport(userAuthentication.getManagementAccess(), surveyDetailsExcel.getDealer(),
					userAuthentication.getLoginId(), userAuthentication.getDealerEmployeeId(),
					surveyDetailsExcel.getSurveyName(), surveyDetailsExcel.getSurveyStatus(),
					surveyDetailsExcel.getAsOnDate(), surveyDetailsExcel.getFromDate(),
					surveyDetailsExcel.getToDate(), surveyDetailsExcel.getHierId(),
					surveyDetailsExcel.getCustSatisfaction(),
					surveyDetailsExcel.getModel(), surveyDetailsExcel.getSubModel(),
					surveyDetailsExcel.getSurveyNo(),surveyDetailsExcel.getQuestion(), surveyDetailsExcel.getProduct(),
					surveyDetailsExcel.getVariant(),surveyDetailsExcel.getSeries(),surveyDetailsExcel.getReportType(),
					surveyDetailsExcel.getPage(), surveyDetailsExcel.getSize(),
					userAuthentication.getUsername(),surveyDetailsExcel.getChassisNo(),"excel", surveyDetailsExcel.getSurveyType(),
					surveyDetailsExcel.getFromDcDate(), surveyDetailsExcel.getToDcDate());
	    	in = ExcelCellGenerator.surveyDetailsExcelReport(res3);
		}
    	
        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "Warranty_Pcr_Report_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
    

	@PostMapping("/surveySummaryQandAReport")
	public ResponseEntity<?> surveySummaryQandAReport(@RequestBody SurveySummaryReportDto ssqaReport) {
		ApiResponse<List<QAReportResponse>> response = new ApiResponse<>();
		Long count = 0l;
		List<QAReportResponse> qaReportResponse = surveySummaryReportRepo
				.surveySummaryQAReport(userAuthentication.getManagementAccess(), ssqaReport.getDealer(),
						ssqaReport.getHoUser(), userAuthentication.getDealerEmployeeId(),
						ssqaReport.getSurveyType(), ssqaReport.getHierId(), ssqaReport.getFromDate(),
						ssqaReport.getToDate(),ssqaReport.getProduct(), ssqaReport.getSeries(),
						ssqaReport.getModel(),ssqaReport.getSubModel(), ssqaReport.getVariant(),
						ssqaReport.getQuestion(),ssqaReport.getPage(), ssqaReport.getSize(),
						userAuthentication.getUsername(),"view");
		
		if (qaReportResponse != null && qaReportResponse.size() > 0) {
			count = qaReportResponse.get(0).getRecordCount();
		}

		response.setResult(qaReportResponse);
		response.setMessage("Data get successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setCount(count);
		return ResponseEntity.ok(response);
	}
	
	
    @PostMapping("/downloadSurveyQandAExcelReport")
    public ResponseEntity<InputStreamResource> surveyQandAExcelReport(@RequestBody SurveySummaryReportDto excel, HttpServletResponse response) throws IOException{
    	List<QAReportResponse> qaExcel = surveySummaryReportRepo
				.surveySummaryQAReport(userAuthentication.getManagementAccess(), excel.getDealer(),
						excel.getHoUser(), userAuthentication.getDealerEmployeeId(),
						excel.getSurveyType(), excel.getHierId(), excel.getFromDate(),
						excel.getToDate(),excel.getProduct(), excel.getSeries(),
						excel.getModel(),excel.getSubModel(), excel.getVariant(),
						excel.getQuestion(),excel.getPage(), excel.getSize(),
						userAuthentication.getUsername(),"excel");
    	
		
    	ByteArrayInputStream in = ExcelCellGenerator.surveyQaExcelReport(qaExcel);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "Summary_QA_Report_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
    
}