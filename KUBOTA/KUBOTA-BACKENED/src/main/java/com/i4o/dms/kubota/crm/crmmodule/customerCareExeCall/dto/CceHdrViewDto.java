package com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto;

import java.util.Date;

public interface CceHdrViewDto {
	
	Long getId();
	
	Long getDealerId();
	
	Date getCallDate();
	
	String getCallNo();
	
	String getCallDetails();
	
	Long getSalesEnquiryId();
	
	Long getServiceBookingId();
	
	Long getJcId();
    
    Long getDcId();
	
	String getStartTime();
	
	String getEndTime();
	
	CrmCustomerCallType getCallType();
	interface CrmCustomerCallType {
		Long getId();
		
		String getTypeOfCall();
	}
	
    QaCrmCallStatus getCrmCallStatus();
    interface QaCrmCallStatus {
    	Long getId();
    	
    	String getCallStatus();
    }
}
