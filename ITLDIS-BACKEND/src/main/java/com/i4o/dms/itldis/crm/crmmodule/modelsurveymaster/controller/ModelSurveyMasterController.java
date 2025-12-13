package com.i4o.dms.itldis.crm.crmmodule.modelsurveymaster.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.crm.crmmodule.modelsurveymaster.dto.ModelSurveyMasterDtoMapping;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })




@RequestMapping(value = "/api/modelSurveyMaster")
public class ModelSurveyMasterController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
	
	@Autowired
	private UserAuthentication userAuthentication;
	
	
	@PostMapping(value="/saveModelSurveyMaster")
	public ResponseEntity<?> saveModelSurveyMaster(@RequestBody ModelSurveyMasterDtoMapping saveData) {
		ApiResponse<?> apiResponse = new ApiResponse<>();
		try {
			logger.info("vinay---"+saveData);
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setMessage("Model Survey Master saved Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Model Survey Master can't saved");
		}
		return ResponseEntity.ok(apiResponse);}
	

}
