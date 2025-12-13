package com.i4o.dms.itldis.masters.dealermaster.customermaster.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"edit","customerCode","customerType","customerName","customerAddress","mobileNo","city","district","state"})
public interface CustomerMasterSearchResponseDto {

	String getCustomerCode();
	String getCustomerType();
	String getCustomerName();
	String getCustomerAddress();
	String getMobileNo();
	String getCity();
	String getDistrict();
	String getState();
	@JsonIgnore
	Long getTotalCount();
	String getEdit(); 
}
