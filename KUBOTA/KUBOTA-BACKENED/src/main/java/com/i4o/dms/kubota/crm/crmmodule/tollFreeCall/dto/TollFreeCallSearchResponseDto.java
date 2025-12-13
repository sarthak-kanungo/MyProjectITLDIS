package com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"callNo", "callDate", "mobileNo", "customerName"})
public interface TollFreeCallSearchResponseDto {

	Long getId();
	
	String getCallNo();
	
	String getCallDate();
	
	String getMobileNo();
	
	String getCustomerName();
	
	@JsonIgnore
	Long getTotalCount();
}
