package com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonPropertyOrder({"callNo", "callDate", "mobileNo", "customerName"})
@JsonPropertyOrder({"action", "product", "model",
	"subModel", "chassisNo", "engineNo", "customerName", "mobileNumber", "alternateNo", "fullCustAddress", "previuosServiceType", 
	"previousJobCardDate", "previousHours", "currentServiceDueType", "currentServiceDueDate", "serviceBookingDate",
	"bookingNumber", "serviceCompleteDate"})
public interface CallSearchResponseDto {
	
	Long getId();
	String getAction();
	@JsonProperty("Product")
	String getProduct();
	@JsonProperty("Model")
	String getModel();
	@JsonProperty("Sub Model")
	String getSubModel();
	@JsonProperty("Chassis No")
	String getChassisNo();
	@JsonProperty("Engine No")
	String getEngineNo();
	@JsonProperty("Customer Name")
	String getCustomerName();
	@JsonProperty("Mobile Number")
	String getMobileNumber();
	@JsonProperty("Alternate Mobile No")
	String getAlternateNo();
	@JsonProperty("Customer Full Address")
	String getFullCustAddress();
	@JsonProperty("Previous Service Type")
	String getPreviuosServiceType();
	@JsonProperty("Previous Job Card Date ( Machine Out Date )")
	String getPreviousJobCardDate();
	@JsonProperty("Previous Hours")
	String getPreviousHours();
	@JsonProperty("Current Service Due Type")
	String getCurrentServiceDueType();
	@JsonProperty("Current Service Due Date")
	String getCurrentServiceDueDate();
	@JsonProperty("Service Booking Date")
	String getServiceBookingDate();
	@JsonProperty("Booking Number")
	String getBookingNumber();
	@JsonProperty("Service Completed Date")
	String getServiceCompleteDate();
	@JsonIgnore
	Long getRecordsCount();
	
}
