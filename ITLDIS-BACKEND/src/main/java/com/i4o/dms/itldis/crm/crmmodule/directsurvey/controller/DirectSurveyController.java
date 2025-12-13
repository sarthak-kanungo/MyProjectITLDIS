package com.i4o.dms.itldis.crm.crmmodule.directsurvey.controller;



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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain.CallAttempt;
import com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain.SurveyHeader;
import com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain.SurveyRecordings;
import com.i4o.dms.itldis.crm.crmmodule.directsurvey.repository.CallAttemptRepo;
import com.i4o.dms.itldis.crm.crmmodule.directsurvey.repository.DirectSurveyRepo;
import com.i4o.dms.itldis.crm.crmmodule.directsurvey.repository.SurveyRecordingRepo;
import com.i4o.dms.itldis.crm.crmmodule.directsurvey.response.UpdateCallAndSurveyRecordingDto;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping("/api/crm/crmmodule/directsurvey")

public class DirectSurveyController {

	@Autowired
	private DirectSurveyRepo directSurveyRepo;

	@Autowired
	private UserAuthentication userAuthentication;
	
    @Autowired
    private StorageService storageService;
    
    @Autowired
    private SurveyRecordingRepo surveyRecordingRepo;
    
    @Autowired
    private CallAttemptRepo callAttemptRepo;
	
	@GetMapping(value = "/getSurveyType")
	ResponseEntity<ApiResponse<Object>> getSurveyType(){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> surveyType = directSurveyRepo.getSurveyType();
		apiResponse.setResult(surveyType);
		apiResponse.setMessage("Survet Type Get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}

	
	@GetMapping(value = "/getSurveyStatus")
	ResponseEntity<ApiResponse<Object>> getSurveyStatus(){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> surveyStatus = directSurveyRepo.getSurveyStatus();
		apiResponse.setResult(surveyStatus);
		apiResponse.setMessage("Survet Status Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	@GetMapping(value = "/getSatisfactionLevel")
	ResponseEntity<ApiResponse<Object>> getSatisfactionLevel(){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> surveyStatus = directSurveyRepo.getSatisfactionLevel();
		apiResponse.setResult(surveyStatus);
		apiResponse.setMessage("Satisfaction  Level Get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	
	@GetMapping(value = "/getDirectSurveyDetails")
	ResponseEntity<ApiResponse<Object>> getDirectSurveyDetails(@RequestParam(required=false) String surveyType,@RequestParam Long vinId,@RequestParam Long customerId){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> surveyData = directSurveyRepo.getDirectSurveyDetails(surveyType,vinId,customerId);
		apiResponse.setResult(surveyData);
		apiResponse.setMessage("Survey details Get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	@GetMapping(value = "/getSurveyQuestion")
	ResponseEntity<ApiResponse<Object>> getSurveyQuestion(@RequestParam Long surveyMstId,@RequestParam Long surveyTypeId,@RequestParam Long vinId){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		Map<String, List<Map<String, Object>>> map = new HashMap<>();
		List <Map<String, Object>> questionList = directSurveyRepo.getSurveyQuestion(surveyMstId,surveyTypeId,vinId);
		map.put("questionList", questionList);
		apiResponse.setResult(map);
		apiResponse.setMessage("Survey Question get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value = "/getSurveyAnswer")
	ResponseEntity<ApiResponse<Object>> getSurveyAnswer(@RequestParam Long questionId){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		Map<String, List<Map<String, Object>>> map = new HashMap<>();
		List <Map<String, Object>> answerList = directSurveyRepo.getSurveyAnswer(questionId);
		map.put("answerList", answerList);
		apiResponse.setResult(map);
		apiResponse.setMessage("Survey answers get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value = "/getSubAnswer")
	ResponseEntity<ApiResponse<Object>> getSurveySubAnswer(@RequestParam Long ansId){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> subAns = directSurveyRepo.getSubAnswer(ansId);
		apiResponse.setResult(subAns);
		apiResponse.setMessage("Sub answer Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value = "/getVillageAuto")
	ResponseEntity<ApiResponse<Object>> getVillageAuto(@RequestParam String village,@RequestParam String state,@RequestParam String district){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> villageList = directSurveyRepo.getVillageAuto(village,userAuthentication.getUsername(),state,district);
		apiResponse.setResult(villageList);
		apiResponse.setMessage("Villager Get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value = "/getAgeOfMachine")
	ResponseEntity<ApiResponse<Object>> getAgeOfMachine(@RequestParam Long vinId){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> machineAge = directSurveyRepo.getAgeOfMachine(vinId);
		apiResponse.setResult(machineAge);
		apiResponse.setMessage("Machine age get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value = "/getMeterHours")
	ResponseEntity<ApiResponse<Object>> getMeterHours(@RequestParam Long vinId){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> meterHours = directSurveyRepo.getMeterHours(vinId);
		apiResponse.setResult(meterHours);
		apiResponse.setMessage("Meter Hours get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value="getSurveyMachineDetails")
	public ResponseEntity<?> getCustomerMachineDetails(@RequestParam(value="customerId")Long customerId, @RequestParam(value="chassisNo") String chassisNo){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		List<Map<String, Object>> machineDetails = directSurveyRepo.getSurveyMachineDetails(customerId, userAuthentication.getDealerId(),chassisNo);
		apiResponse.setResult(machineDetails);
		apiResponse.setMessage("Survey Machine Details Get Succesfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	
	
	
	
	@PostMapping(value = "/submitSurveyForm")
	public ResponseEntity<?> submitSurveyForm(@RequestPart SurveyHeader surveyDto, @RequestPart List<MultipartFile> complaintRecording, @RequestPart List<MultipartFile> callRecording){
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		try {
			surveyDto.setCreatedBy(userAuthentication.getLoginId());
			if(surveyDto.getImplementDetails()!=null) {
				surveyDto.setImplementDetails(surveyDto.getImplementDetails().stream().filter(imp -> (imp.getHours()!=null) && imp.getImplementName()!=null).collect(Collectors.toList()));
				surveyDto.getImplementDetails().forEach(imp->{
					imp.setDeleteFlag(true);
				});
			}
			if(surveyDto.getCrops()!=null) {
				surveyDto.getCrops().forEach(crop->{
					crop.setDeleteFlag(false);
				});
			}
			
			if(surveyDto.getOtherPurchaseDetails()!=null) {
				surveyDto.setOtherPurchaseDetails(surveyDto.getOtherPurchaseDetails().stream().filter(other -> (other.getBrand()!=null && other.getModel()!=null)).collect(Collectors.toList()));
				surveyDto.getOtherPurchaseDetails().forEach(other->{
					other.setDeleteFlag(false);
				});
			}
			if(surveyDto.getComplaint()!=null) {
				surveyDto.setComplaint(surveyDto.getComplaint().stream().filter(comp -> comp.getDepartment()!=null).collect(Collectors.toList()));
			}
			
			SurveyHeader headerResponse = directSurveyRepo.save(surveyDto);
			 if(headerResponse.getSurveyHdrId() != null) {
				 directSurveyRepo.updateSurveyStatus(surveyDto.getSurveyRemdId());
				 directSurveyRepo.updateSurveySatisfactionLevel(surveyDto.getSurveyHdrId());
			 }
			 
			 if (complaintRecording.size() <= 5 && !complaintRecording.isEmpty()) {
				 complaintRecording.forEach(m -> {
					 SurveyRecordings recording = new SurveyRecordings();
					 String fileName = m.getOriginalFilename();
					 String recordingName = "CT" + System.currentTimeMillis() + "_" + fileName;
					 storageService.store(m, recordingName);
					 recording.setSurveyHdrId(headerResponse.getSurveyHdrId());
					 recording.setRecording(recordingName);
					 surveyRecordingRepo.save(recording);
				 });
			 }

		
			 apiResponse.setMessage("Survey Details saved successfully");
			 apiResponse.setStatus(HttpStatus.OK.value());
		}
		catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Survey Details can't saved");
		}
		return ResponseEntity.ok(apiResponse);
		
	}
	
	@PostMapping(value = "/submitCallAttempt")
	public ResponseEntity<?> submitCallAttempt(@RequestPart CallAttempt callDetails,@RequestPart List<MultipartFile> callRecording){
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		try {
			if (callRecording.size() <= 5 && !callRecording.isEmpty()) {
				callRecording.forEach(m -> {
					String fileName = m.getOriginalFilename();
					String recordingName = "CA" + System.currentTimeMillis() + "_" + fileName;
					storageService.store(m, recordingName);
					callDetails.setRecordingFileName(recordingName);
				});
			}
			 callDetails.setCreatedBy(userAuthentication.getLoginId());
			 callAttemptRepo.save(callDetails);
			 apiResponse.setMessage("Call Attempt Details saved successfully");
			 apiResponse.setStatus(HttpStatus.OK.value());
			
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Call Attempt Details can't saved");
		}
		return ResponseEntity.ok(apiResponse);
	}

	
	
	
	 @GetMapping("/getViewEditData")
	    public ResponseEntity<?> surveyView(@RequestParam String surveyNo) {
		 ApiResponse<Object> apiResponse = new ApiResponse<>();
		 Map<String, Object> map = new HashMap<>();
		 SurveyHeader view= directSurveyRepo.findBySurveyNo(surveyNo);
		 SurveyRecordings complaint = surveyRecordingRepo.findBySurveyHdrId(view.getSurveyHdrId());
		 String surveyDoneBy = directSurveyRepo.getSurveyDoneUser(view.getCreatedBy());
		 map.put("survey", view);
		 map.put("complaintRecording", complaint);
		 map.put("surveyDoneBy", surveyDoneBy);
		 apiResponse.setResult(map);
		 apiResponse.setMessage("Get survey view Data");
	     apiResponse.setStatus(HttpStatus.OK.value());
	     return ResponseEntity.ok(apiResponse);

	 }
	 
	 @GetMapping("/getCallHistory")
	 public ResponseEntity<?> callHistory(@RequestParam String reminderId) {
		 ApiResponse<Object> apiResponse = new ApiResponse<Object>();
		 List<Map<String, Object>> callHistory = directSurveyRepo.getCallHistory(reminderId);
		 apiResponse.setResult(callHistory);
		 apiResponse.setMessage("Call History get Successfully");
		 apiResponse.setStatus(HttpStatus.OK.value());
		 return ResponseEntity.ok(apiResponse);
	 }
	 
	 @PostMapping(value = "/updateCallrecording")
	 public ResponseEntity<?> updateCallrecordin(@RequestPart UpdateCallAndSurveyRecordingDto updateFile , @RequestPart List<MultipartFile> updateRecording){
		 ApiResponse<Object> response = new ApiResponse<Object>();
		 try {
				if(updateFile.getCallAttemptId()!=null) {
					if (updateRecording.size() <= 5 && !updateRecording.isEmpty()) {
						updateRecording.forEach(m -> {
							String fileName = m.getOriginalFilename();
							String recordingName = "CA" + System.currentTimeMillis() + "_" + fileName;
							storageService.store(m, recordingName);
							directSurveyRepo.updateCallrecording(updateFile.getCallAttemptId(), recordingName);
						});
					}
				}
				else if(updateFile.getSurveyHdrId()!=null) {
					 if (updateRecording.size() <= 5 && !updateRecording.isEmpty()) {
						 updateRecording.forEach(m -> {
							 SurveyRecordings recording = new SurveyRecordings();
							 String fileName = m.getOriginalFilename();
							 String recordingName = "CT" + System.currentTimeMillis() + "_" + fileName;
							 storageService.store(m, recordingName);
							 recording.setSurveyHdrId(updateFile.getSurveyHdrId());
							 recording.setRecording(recordingName);
							 surveyRecordingRepo.save(recording);
						 });
					 }
				}
				response.setMessage("Update Call Recording Successfully");
				response.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				response.setMessage("Call Recording Updation Failed");
		}
		 return ResponseEntity.ok(response);
	 }
}
