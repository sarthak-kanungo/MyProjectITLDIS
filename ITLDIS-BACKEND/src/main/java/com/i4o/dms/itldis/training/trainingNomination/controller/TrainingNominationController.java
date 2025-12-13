package com.i4o.dms.itldis.training.trainingNomination.controller;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.training.trainingNomination.domain.TrainingNominationDetails;
import com.i4o.dms.itldis.training.trainingNomination.domain.TrainingNominationHeader;
import com.i4o.dms.itldis.training.trainingNomination.dto.TrainingNominationSearchDto;
import com.i4o.dms.itldis.training.trainingNomination.dto.TrainingNominationSearchResponse;
import com.i4o.dms.itldis.training.trainingNomination.repo.TrainingNominationRepo;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })

@RequestMapping("/api/training/nomination")
public class TrainingNominationController {

	
	@Autowired
	private UserAuthentication userAuthentication;
	
	@Autowired
	private TrainingNominationRepo trainingNominationRepo;
	
	@GetMapping("/getProgramNomineeProgDtl")
	public ResponseEntity<?> getProgramNomineeProgDtl(@RequestParam("programeNo") String programeNo) {
		ApiResponse<Object> response = new ApiResponse<>();
		List<Map<String, Object>> progdtl = trainingNominationRepo.getProgramNomineeProgDtl(userAuthentication.getLoginId(),programeNo);
		response.setMessage("Program dtls for Nominee get Successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(progdtl);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getAutoEmpName")
	public ResponseEntity<?> getNomineesName(@RequestParam("designationId") String designationId, 
			@RequestParam("searchName") String searchName,
			@RequestParam(value="trainingModule",required=false)String trainingModule,
			@RequestParam(value="typeofTraining",required=false)String typeofTraining) {
		ApiResponse<Object> response = new ApiResponse<>();
		List<Map<String, Object>> nomineeName = trainingNominationRepo.getAutoEmpName(designationId,searchName,trainingModule,typeofTraining, userAuthentication.getDealerId());
		response.setMessage("Nominee Name get Successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(nomineeName);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getNomineeEmpDetails")
	public ResponseEntity<?> nomineeEmpDetails(@RequestParam("programId") Long programId,@RequestParam("employeeId") Long employeeId) {
		ApiResponse<Object> response = new ApiResponse<>();
		Map<String, Object> nominiDetails = trainingNominationRepo.getNomineeEmpDetails(programId,employeeId);
		response.setMessage("Nominee details get Successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(nominiDetails);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getAutoNominationNo")
	public ResponseEntity<?> getAutoNominees(@RequestParam("nominationNo") String nominationNo) {
		ApiResponse<Object> response = new ApiResponse<>();
		List<Map<String, Object>> nomineeName = trainingNominationRepo.getAutoNominationNo(userAuthentication.getLoginId(),nominationNo);
		response.setMessage("Nominee Number get Successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(nomineeName);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "saveNomination")
	ResponseEntity<?> saveNomination(@RequestBody() TrainingNominationHeader nomination) {
		ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>();
		List<TrainingNominationDetails> dls = null;
		try {
			if(nomination.getNominationId()!=null){
				nomination.setModifiedBy(userAuthentication.getLoginId());
				nomination.setModifiedDate(new Date());
				dls = trainingNominationRepo.findByNominationId(nomination.getNominationId()).getNominationDetails();
			}else{
				nomination.setCreatedBy(userAuthentication.getLoginId());
				nomination.setCreatedDate(new Date());
				nomination.setDealerId(userAuthentication.getDealerId());
			}
			if (nomination.getNominationDetails() != null) {
				List<Long> dtlids = new ArrayList<>();
				if(dls!=null){
					dtlids = dls.stream().map(d -> d.getNominationDtlId()).collect(Collectors.toList());
				}
				for(int i=0;i<nomination.getNominationDetails().size();i++){
					TrainingNominationDetails nominee = nomination.getNominationDetails().get(i);
					nominee.setCreatedBy(userAuthentication.getLoginId());
					nominee.setCreatedDate(new Date());
					nominee.setActive('Y');
					if(nominee.getNominationDtlId()!=null){
						dtlids.remove(nominee.getNominationDtlId());
					}
				}
				if(dtlids.size()>0){
					dtlids.stream().forEach(System.out::println);
					trainingNominationRepo.deleteDtls(dtlids);
				}
			}
			trainingNominationRepo.save(nomination);
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Training Nomination Save Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Training Nomination can't Save");
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/nomineeSearch")
	public ResponseEntity<?> getTpcSearch(@RequestBody TrainingNominationSearchDto searchNominee) {
		ApiResponse<Object> response = new ApiResponse<>();
		Long count = 0l;
		List<TrainingNominationSearchResponse> searchReasult = trainingNominationRepo.nomineeSearch(userAuthentication.getLoginId(), searchNominee.getProgramId(),
				searchNominee.getNomineeId(),searchNominee.getFromDate(),searchNominee.getToDate(), searchNominee.getPage(), searchNominee.getSize());
		if (searchReasult != null && searchReasult.size() > 0) {
			count = searchReasult.get(0).getRecordCount();
		}
		response.setMessage("Nomination Search get successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(searchReasult);
		response.setCount(count);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getViewEditData")
	public ResponseEntity<?> tnViewEdit(@RequestParam Long programId) {
		String empName = null;
		BigInteger designationid = null;
		String employeeCode = null;
		Map<String, Object> map = new HashMap<>();
		ApiResponse<Object> response = new ApiResponse<>();
		TrainingNominationHeader view = trainingNominationRepo.findByNominationId(programId);
		if (view.getNominationDetails() !=null) {
			for (int i = 0; i < view.getNominationDetails().size(); i++) {
				//map.put("data", trainingNominationRepo.nomineeEmpDetails(view.getNominationDetails().get(i).getEmployeeId()));
				empName = (String) trainingNominationRepo.nomineeEmpDetails(view.getNominationDetails().get(i).getEmployeeId()).get("employeeName");
				designationid = (BigInteger) trainingNominationRepo.nomineeEmpDetails(view.getNominationDetails().get(i).getEmployeeId()).get("designationId");
				employeeCode = (String) trainingNominationRepo.nomineeEmpDetails(view.getNominationDetails().get(i).getEmployeeId()).get("employeeCode");
				//System.out.println("cinay---------"+map.get("data").toString());
				view.getNominationDetails().get(i).setEmployeeName(empName);
				view.getNominationDetails().get(i).setDesignationId(designationid);
				view.getNominationDetails().get(i).setEmployeeCode(employeeCode);
			}
		}
		response.setResult(view);
		response.setMessage("Get TN view Data");
		response.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(response);

	}
	
	
	@PostMapping(value = "updateNomination")
	ResponseEntity<?> updateNomination(@RequestBody() TrainingNominationHeader updateNomination) {
		ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>();
		TrainingNominationHeader tnUpdate = trainingNominationRepo.getOne(updateNomination.getNominationId());
		try {
			tnUpdate.setModifiedBy(userAuthentication.getLoginId());
			tnUpdate.setModifiedDate(new Date());
			tnUpdate.setNominationDetails(updateNomination.getNominationDetails());
			if (tnUpdate.getNominationDetails() != null) {
				//tnUpdate.setNominationDetails(updateNomination.getNominationDetails().stream().filter(other -> (other.getNominationDtlId()!=null)).collect(Collectors.toList()));
				tnUpdate.getNominationDetails().forEach(nominee ->{
					if (nominee.getNominationDtlId() == null) {
						nominee.setCreatedBy(userAuthentication.getLoginId());
						nominee.setCreatedDate(new Date());
						nominee.setActive('Y');
					}else {
						nominee.setModifiedBy(userAuthentication.getLoginId());
						nominee.setModifiedDate(new Date());
					}
				});
			}
			trainingNominationRepo.save(tnUpdate);
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Training Nomination Update Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Training Nomination can't Update");
		}
		return ResponseEntity.ok(response);
	}
	
	//Suraj--05/01/2023 START
	@GetMapping("/getProgramNomineeProgHdr")
	public ResponseEntity<?> getProgramNomineeProgHdr(@RequestParam("programeNo") String programeNo) {
		ApiResponse<Object> response = new ApiResponse<>();
		Map<String, Object> progdtl = trainingNominationRepo.getProgramNomineeProgHdr(programeNo);
		response.setMessage("Program dtls header for Nominee get Successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setResult(progdtl);
		return ResponseEntity.ok(response);
	}
	//Suraj--05/01/2023 END

}
