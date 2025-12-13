package com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.service;

import javax.transaction.Transactional;

import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * @author mahesh.kumar
 */
@Transactional
public interface CustomerCareExeCallService {
	ApiResponse<?> getCallStatus();
	
	ApiResponse<?> getCallType();
	
	ApiResponse<?> getQuesionnaire(Long typeOfCallId);

}
