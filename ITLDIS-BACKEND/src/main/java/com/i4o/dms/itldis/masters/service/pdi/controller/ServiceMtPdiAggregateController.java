package com.i4o.dms.itldis.masters.service.pdi.controller;

import com.i4o.dms.itldis.masters.service.pdi.dto.CheckDraftModeDto;
import com.i4o.dms.itldis.masters.service.pdi.repository.ServiceMtPdiAggregateRepo;
import com.i4o.dms.itldis.masters.service.pdi.repository.ServiceMtPdiModelAggregateCheckpointMappingRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/api/master/service/pdiAggregate")
public class ServiceMtPdiAggregateController {

	@Autowired
	private UserAuthentication userAuthentication;

	@Autowired
	private ServiceMtPdiAggregateRepo serviceMtPdiAggregateRepo;

	@Autowired
	private ServiceMtPdiModelAggregateCheckpointMappingRepo serviceMtPdiModelAggregateCheckpointMappingRepo;

	private Logger logger = LoggerFactory.getLogger(ServiceMtPdiAggregateController.class);

	@GetMapping(value = "/autoCompleteChassisNo")
	public ResponseEntity<?> getAutoCompleteChassisNo(@RequestParam("chassisNo") String chassisNo) {
		System.out.println("chessis");
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("ChassisNo get successfully.");
		apiResponse.setStatus(HttpStatus.OK.value());

		apiResponse.setResult(
				serviceMtPdiAggregateRepo.autoCompleteChassisNo(userAuthentication.getDealerId(), chassisNo));
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping(value = "/grnDetailsByChassisNo")
	public ResponseEntity<?> getGrnDetailsByChassisNo(@RequestParam("chassisNo") String chassisNo) {
		ApiResponse apiResponse = new ApiResponse();
		CheckDraftModeDto draftModeDto = serviceMtPdiAggregateRepo.servicePdiDraftModeCheck(chassisNo,
				userAuthentication.getDealerId());
		if (draftModeDto.getIsTrue() == Boolean.TRUE) {
			apiResponse.setMessage("Chassis Number is Already in draft mode");
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setResult(draftModeDto);
		} else {
			apiResponse.setMessage("Details get successfully.");
			apiResponse.setStatus(HttpStatus.OK.value());
			System.out.println("chassisNo--"+chassisNo+"----");
			apiResponse.setResult(serviceMtPdiAggregateRepo.grnDetailsByChassisNo(chassisNo));
		}
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping(value = "/getAggregateAndCheckpoints")
	public ResponseEntity<?> getAggregateAndCheckpoints(@RequestParam("model") String model) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Aggregates and checkpoints get successfully.");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(serviceMtPdiModelAggregateCheckpointMappingRepo.getAggregateAndCheckpoints(model));
		return ResponseEntity.ok(apiResponse);
	}
}
