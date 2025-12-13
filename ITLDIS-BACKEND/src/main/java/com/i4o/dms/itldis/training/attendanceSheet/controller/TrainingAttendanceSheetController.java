package com.i4o.dms.itldis.training.attendanceSheet.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

import com.i4o.dms.itldis.common.service.JasperPrintService;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.training.attendanceSheet.dto.TrainingAttendanceSheetDto;
import com.i4o.dms.itldis.training.attendanceSheet.dto.TrainingAttendanceSheetSearchDto;
import com.i4o.dms.itldis.training.attendanceSheet.dto.TrainingAttendanceSheetSearchResponse;
import com.i4o.dms.itldis.training.attendanceSheet.dto.TrainingCertificateDto;
import com.i4o.dms.itldis.training.attendanceSheet.repo.TrainingAttendanceSheetRepo;
import com.i4o.dms.itldis.training.attendanceSheet.service.AttendenceServiceImpl;
import com.i4o.dms.itldis.training.trainingProgrammeCalendar.repo.TrainingProgramCalendarRepo;
import com.i4o.dms.itldis.utils.ApiResponse;

import net.sf.jasperreports.engine.JasperPrint;


@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
@RequestMapping("/api/training/attendance-sheet")
public class TrainingAttendanceSheetController {
	
	@Autowired
	private UserAuthentication userAuthentication;
	
	@Autowired
	private TrainingAttendanceSheetRepo tasRepo;
	
	@Autowired
	private TrainingProgramCalendarRepo tpcHeaderRepo;

	@Autowired
    private JasperPrintService jasperPrintService;
	
	@Autowired
	private AttendenceServiceImpl attendenceService;
	
	Long id = null; 
	
	@GetMapping("/getProgramNoForNominee")
	public ResponseEntity<?> getAutoProgramNo(@RequestParam("programeNo") String programeNo) {
		ApiResponse<Object> response = new ApiResponse<>();
		List<Map<String, Object>> progNo = tasRepo.getProgramNoForNominee(programeNo);
		response.setMessage("Nominee Program Number Successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(progNo);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getProgramAndNomineeDtl")
	public ResponseEntity<?> getProgramAndNomineeDetails(@RequestParam("programId") Long programId) {
		ApiResponse<Object> response = new ApiResponse<>();
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> attendanceDtl = tasRepo.getProgramAndNomineeDtl(programId);
		Map<String, Object> tcpHeaderDtl  = tpcHeaderRepo.getHeaderDetailsByProgramId(programId,"Header");
		map.put("AttendanceDtl", attendanceDtl);
		map.put("TcpHeaderDtl", tcpHeaderDtl);
		response.setMessage("Program and Nominee details get Successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(map);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getAttendanceTrainers")
	public ResponseEntity<?> attendanceTrainers(@RequestParam("departmentName") String departmentName) {
		ApiResponse<Object> response = new ApiResponse<>();
		List<Map<String, Object>> trainersList = tasRepo.getAttendanceTrainers(departmentName);
		response.setMessage("Trainer List get Successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(trainersList);
		return ResponseEntity.ok(response);
	}
		
	@PostMapping(value = "saveNomineAttendanceSheet")
	ResponseEntity<?> nomineAttendanceSheetSave(@RequestPart() TrainingAttendanceSheetDto tnAttendanceSheet , 
			@RequestPart List<MultipartFile> tnAttendanceSheetDocs) 
	{
		ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>();
		try {;
			
			attendenceService.nomineAttendanceSheetSave(tnAttendanceSheet, tnAttendanceSheetDocs);
		
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Attendance Sheet Save Successfully");
		}catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Attendance Sheet can't Save");
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/attendaceSheetSearch")
	public ResponseEntity<?> getTpcSearch(@RequestBody TrainingAttendanceSheetSearchDto tasSearch) {
		ApiResponse<Object> response = new ApiResponse<>();
		Long count = 0l;
		List<TrainingAttendanceSheetSearchResponse> search;
		search = tasRepo.attendaceSheetSearch(userAuthentication.getUsername(), tasSearch.getDepartmentName(),tasSearch.getProgramNumber(),tasSearch.getTrainingLocationId(),
											tasSearch.getTrainingModuleId(),tasSearch.getFromDate(),tasSearch.getToDate(), tasSearch.getPage(), tasSearch.getSize());
		
		if (search != null && search.size() > 0) {
			count = search.get(0).getRecordCount();
		}
		
		response.setMessage("Nomination Search get successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(search);
		response.setCount(count);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getViewEditData")
	public ResponseEntity<?> tasViewEdit(@RequestParam Long programId) 
	{
		ApiResponse<Object> response = new ApiResponse<>();
		Map<String, Object> map = new HashMap<>();
		
//		Map<String, Object> map1 = new HashMap<>();
//		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();

		Map<String, Object> tcpHeader  = tpcHeaderRepo.getHeaderDetailsByProgramId(programId, "Header");
		Map<String, Object> trainers = tasRepo.viewTrainers(programId);
		List<Map<String, Object>> attendanceDtl = tasRepo.getProgramAndNomineeDtlView(programId);
		List<Map<String, Object>> nominEmpIndex = tasRepo.viewNominationEmpIndex(programId);
		List<Map<String, Object>> docsDtl = tasRepo.viewAttendanceDoc(programId);
		
//		List<Object> dateList = new ArrayList<>();
//		List<Map<String, Object>> attendantList = new ArrayList<>();
		
//		if (!tasDtl.isEmpty()) {
//			tasDtl.forEach(data->{
//				attendantList.add(data);
//				dateList.add( tasRepo.getTrainingDates( (BigInteger) data.get("programNominationDTLId")));
//			
//				//list.add(map1);
//			});
//		}
		
//		map1.put("AttendanceDtl", attendantList);
//		map1.put("TrainingDates", dateList);
		
		map.put("TcpHeaderDtl", tcpHeader);
		map.put("Trainers", trainers);
		map.put("AttendanceDtl", attendanceDtl);
		map.put("nominEmpIndex", nominEmpIndex);
		map.put("docsDtl", docsDtl);
		
		response.setResult(map);
		response.setMessage("Get TN view Data");
		response.setStatus(HttpStatus.OK.value());
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "updateNomineAttendanceSheet")
	@Transactional
	ResponseEntity<?> nomineAttendanceSheetUpdate(@RequestPart TrainingAttendanceSheetDto tnAttendanceSheetUpdate)
	{
		ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>();

		try {
			
			attendenceService.updateAttendence(tnAttendanceSheetUpdate);
			
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Attendance Sheet Update Successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Attendance Sheet can't Update");
		}
		
		return ResponseEntity.ok(response);
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
	@PostMapping("generateTrainingCertificate")
    public void generateTrainingCertificate(@RequestBody TrainingCertificateDto certificateDto, 
    		HttpServletRequest request, HttpServletResponse response)
    {
    	String filePath = request.getServletContext().getRealPath("/WEB-INF/reports/");
    	
    	OutputStream outputStream = null;
    	response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=" 
				+ "Training_Certificate-" + ThreadLocalRandom.current().nextInt(1000) + "-" 
				+ System.currentTimeMillis() + ".pdf");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		
    	try {
    		outputStream = response.getOutputStream();
    		
    		String jasperfile = filePath + "printingtrainingprogramCertificate.jasper";
        	
        	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
    		jasperParameter.put("programId", certificateDto.getProgramId());
    		jasperParameter.put("employeeId", certificateDto.getEmployeeId());
    		jasperParameter.put("dealerId", certificateDto.getDealerId());
        	
    		JasperPrint jasperPrint = jasperPrintService.getJasperPrint(jasperfile, jasperParameter);
        	
    		jasperPrintService.printPdfReport(jasperPrint, certificateDto.getPrintStatus(), outputStream);
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
