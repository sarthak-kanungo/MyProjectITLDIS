package com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.repo.CustomerCareExeCallRepo;
import com.i4o.dms.kubota.utils.ApiResponse;

/**
 * @author mahesh.kumar
 */
@Service
public class CustomerCareExeCallServiceImp implements CustomerCareExeCallService{
	@Autowired
	private CustomerCareExeCallRepo cceCallRepo;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Override
	public ApiResponse<?> getCallStatus() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(cceCallRepo.getCallStatus());
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> getCallType() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(cceCallRepo.getCallType());
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> getQuesionnaire(Long typeOfCallId) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(cceCallRepo.getQuesionnaire(typeOfCallId));
		
		return apiResponse;
	}

}
