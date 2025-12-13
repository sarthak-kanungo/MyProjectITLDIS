package com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"callNo", "callDate", "mobileNo", "customerName", "callTypeId", "callType", "callStatus"})
public interface CRMCallSearchResponseDto {
	
	Long getId();
	
	String getCallNo();
	
	String getCallDate();
	
	String getMobileNo();
	
	String getCustomerName();
	
	Long getCallTypeId();
	
//	@JsonProperty("Call Type")
	String getCallType();
	
//	@JsonProperty("Call Status")
	String getCallStatus();
	
	@JsonIgnore
	Long getTotalCount();

}
