package com.i4o.dms.kubota.training.trainingProgrammeCalendar.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.training.trainingProgrammeCalendar.domain.TrainingProgramCalendarHeader;
import com.i4o.dms.kubota.training.trainingProgrammeCalendar.dto.TrainingProgCalendarApprovalDto;
import com.i4o.dms.kubota.training.trainingProgrammeCalendar.dto.TrainingProgCalendarSearchDto;
import com.i4o.dms.kubota.training.trainingProgrammeCalendar.dto.TrainingProgCalendarSearchResponse;
import com.i4o.dms.kubota.training.trainingProgrammeCalendar.repo.TrainingProgramCalendarRepo;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })

@RequestMapping("/api/training/training-program-calender")
public class TrainingProgramCalendarController {

	@Autowired
	private UserAuthentication userAuthentication;

	@Autowired
	private TrainingProgramCalendarRepo headerRepo;

	@GetMapping(value = "/getProgramLocation")
	ResponseEntity<ApiResponse<Object>> programLocation(@RequestParam() String type) {
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> location = headerRepo.getProgramLocation(type);
		apiResponse.setResult(location);
		apiResponse.setMessage("Program Locations Get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping(value = "/getTrainingType")
	ResponseEntity<ApiResponse<Object>> trainingType(@RequestParam() String departmentName, @RequestParam() String type) {
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> result = headerRepo.getTrainingType(departmentName, type);
		apiResponse.setResult(result);
		apiResponse.setMessage("Training Types Get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping(value = "/getTrainingModule")
	ResponseEntity<ApiResponse<Object>> trainingModule(@RequestParam() Long trainingTypeId,
			@RequestParam() String type) {
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> result = headerRepo.getTrainingModule(trainingTypeId, type);
		apiResponse.setResult(result);
		apiResponse.setMessage("Training Modules Get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping(value = "/getDealerName")
	ResponseEntity<ApiResponse<Object>> dealerName(@RequestParam() String allStateName) {
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> result = headerRepo.getDealerName(allStateName);
		apiResponse.setResult(result);
		apiResponse.setMessage("Dealer Names Get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}

	@PostMapping(value = "saveTrainingProgramCalendar")
	ResponseEntity<?> saveTrainingProgramCalendar(@RequestBody() TrainingProgramCalendarHeader header) {
		ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>();
		try {
			header.setCreatedBy(userAuthentication.getLoginId());
			header.setCreatedDate(new Date());
			header.setActive('Y');
			if (header.getTpcDealerDetails() != null) {
				header.getTpcDealerDetails().forEach(dealerDtl -> {
					dealerDtl.setCreatedBy(userAuthentication.getLoginId());
					dealerDtl.setCreatedDate(new Date());
					dealerDtl.setActive('Y');
				});
			}
			if (header.getTpcHolidayDetails() != null) {
				header.getTpcHolidayDetails().forEach(holiday -> {
					holiday.setActive('Y');
				});
			}
			headerRepo.save(header);
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Training Program Calendar Save Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Training Program Claender can't Save");
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("/tpcSearch")
	public ResponseEntity<?> getTpcSearch(@RequestBody TrainingProgCalendarSearchDto tpcSearch) {
		ApiResponse<Object> response = new ApiResponse<>();
		List<TrainingProgCalendarSearchResponse> searchReasult = headerRepo.tpcSearch(userAuthentication.getUsername(),tpcSearch.getDepartmentName(),
				tpcSearch.getProgramNo(), tpcSearch.getTrainingLocationId(), tpcSearch.getTrainingModuleId(),
				tpcSearch.getFromDate(), tpcSearch.getToDate(), tpcSearch.getPage(), tpcSearch.getSize());
		Long count = 0l;
		if (searchReasult != null && searchReasult.size() > 0) {
			count = searchReasult.get(0).getRecordCount();
		}
		response.setMessage("Training Prog Search get successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(searchReasult);
		response.setCount(count);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getViewEditData")
	public ResponseEntity<?> tpcViewEdit(@RequestParam Long programId) {
		ApiResponse<Object> response = new ApiResponse<>();
		Map<String, Object> view = headerRepo.getHeaderDetailsByProgramId(programId,"Header");
//		 TrainingProgramCalendarHeader view = headerRepo.findByProgramId(programId);
		response.setResult(view);
		response.setMessage("Get TPC view Data");
		response.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(response);

	}

	@GetMapping("/getProgramNo")
	public ResponseEntity<?> getAutoProgramNo(@RequestParam("programeNo") String programeNo,
			@RequestParam("departmentName") String departmentName) {
		ApiResponse<Object> response = new ApiResponse<>();
		List<Map<String, Object>> progNo = headerRepo.getProgramNo(programeNo, userAuthentication.getUsername(), departmentName);
		response.setMessage("Get Program Number Successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(progNo);
		return ResponseEntity.ok(response);

	}

	@PostMapping(value = "updateTrainingProgramCalendar")
	ResponseEntity<?> updateTrainingProgramCalendar(@RequestBody() TrainingProgramCalendarHeader update) {
		ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>();
		try {
			headerRepo.deleteDealerDetails(update.getProgramId());
			headerRepo.deleteHolidayDates(update.getProgramId());
			TrainingProgramCalendarHeader tpcUpdate = headerRepo.getOne(update.getProgramId());
			tpcUpdate.setModifiedBy(userAuthentication.getLoginId());
			tpcUpdate.setModifiedDate(new Date());
//				tpcUpdate.tra
			tpcUpdate.setTrainingModuleId(update.getTrainingModuleId());
			tpcUpdate.setProgramLocationId(update.getProgramLocationId());
			tpcUpdate.setLocation(update.getLocation());
			tpcUpdate.setStartDate(update.getStartDate());
			tpcUpdate.setEndDate(update.getEndDate());
			tpcUpdate.setLastNominationDate(update.getLastNominationDate());
			tpcUpdate.setMaxNoOfNominees(update.getMaxNoOfNominees());
			tpcUpdate.setNoNominees(update.getNoNominees());
			tpcUpdate.setRemarks(update.getRemarks());

			tpcUpdate.setTpcDealerDetails(update.getTpcDealerDetails());
			tpcUpdate.setTpcHolidayDetails(update.getTpcHolidayDetails());
			if (tpcUpdate.getTpcDealerDetails() != null) {
				tpcUpdate.getTpcDealerDetails().forEach(dealerDtl -> {
					if (dealerDtl.getProgramDlrDtlId() == null) {
						dealerDtl.setCreatedBy(userAuthentication.getLoginId());
						dealerDtl.setCreatedDate(new Date());
						dealerDtl.setActive('Y');
					} else {
						tpcUpdate.setModifiedBy(userAuthentication.getLoginId());
						tpcUpdate.setModifiedDate(new Date());
						dealerDtl.setActive('Y');
					}
				});
			}
			if (tpcUpdate.getTpcHolidayDetails() != null) {
				tpcUpdate.getTpcHolidayDetails().forEach(holiday -> {
					if (holiday.getProgramHolidDtlId() == null) {
						holiday.setActive('Y');
					}else {
						holiday.setActive('Y');
					}
				});
			}
			headerRepo.save(tpcUpdate);
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Training Program Calendar Update Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Training Program Claender can't Update");
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getNomineeDetails")
	public ResponseEntity<?> getNominationDetails(@RequestParam("programeNo") String programeNo) {
		ApiResponse<Object> response = new ApiResponse<>();
		List<Map<String, Object>> nomineeDetails = headerRepo.getNomineeDetails(programeNo);
		response.setMessage("Get Nominee Details Successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(nomineeDetails);
		return ResponseEntity.ok(response);

	}
	@PostMapping("/getNomineesApproval")
	public ResponseEntity<?> approveNominees(@RequestBody() TrainingProgCalendarApprovalDto nomineesApproval) {
		ApiResponse<Object> response = new ApiResponse<>();
		Long id = null;
		if(nomineesApproval.getNomineesApproval()!=null && nomineesApproval.getNomineesApproval().size()>0){
			id = nomineesApproval.getNomineesApproval().get(0).getProgramNominationDtlId();
			nomineesApproval.getNomineesApproval().forEach(data->{
				headerRepo.aproveNominies(data.getNominationStatus(), data.getAttendedStatus(), data.getProgramNominationDtlId());
			});
		}
//		if(id!=null){
//			headerRepo.updateNominieHdrStatus(id);
//		}
		response.setMessage("Nominees Approval Successfully");
		response.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(response);
	}

}
