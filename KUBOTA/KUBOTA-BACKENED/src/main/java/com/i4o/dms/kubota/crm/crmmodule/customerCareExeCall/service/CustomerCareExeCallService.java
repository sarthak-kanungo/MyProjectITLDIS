package com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.service;

import javax.transaction.Transactional;

import com.i4o.dms.kubota.utils.ApiResponse;

/**
 * @author mahesh.kumar
 */
@Transactional
public interface CustomerCareExeCallService {
	ApiResponse<?> getCallStatus();
	
	ApiResponse<?> getCallType();
	
	ApiResponse<?> getQuesionnaire(Long typeOfCallId);

}
