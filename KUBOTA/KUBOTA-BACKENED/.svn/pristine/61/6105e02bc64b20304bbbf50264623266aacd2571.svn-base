package com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"edit","id","jobCardNo","jobCardStatus","chassisNo","customerCode","customerName","mobileNo","engineNo","model","jobCardDate","dealerName","serviceCategory",
	"placeOfService","taskDate","taskTime","closeDate","currentHour","meterChangeHours","meterTotalHours",
	"pcrNumber","pcrStatus","mechanicName","reasonForDelay"})
public interface JobCardSearchResponceDto {
	
    Long getId();
    String getJobCardStatus();
//    String getRemark();
    String getJobCardNo();
    String getChassisNo();
    String getCustomerCode();
    String getCustomerName();
    String getMobileNo();
    String getEngineNo();
    String getModel();
    String getEdit();
    String getJobCardDate();
    String getDealerName();
    String getServiceCategory();
    String getPlaceOfService();
    @JsonProperty("Machine In Date")
    String getTaskDate();
    String getCloseDate();
    @JsonProperty("Machine In Time")
    String getTaskTime();
    @JsonProperty("Current Hours")
    Double getCurrentHour();
    Double getMeterChangeHours();
    @JsonProperty("Total Hours")
    Double getMeterTotalHours();
    
    String getPcrNumber();
	String getPcrStatus();
	String getMechanicName();
	String getReasonForDelay();
	@JsonIgnore
	String getCreatedBy();
	@JsonIgnore		//Suraj--30/11/2022
	String getCreatorName();	//Suraj--30/11/2022
    @JsonIgnore
	public Long getRecordCount();

}
