package com.i4o.dms.kubota.service.report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","zone", "area", "areaManger", "territoryManager", "soldDealerCode", "soldDealershipName", "productGroup", "product", "model",
	"subModel", "chassisNo", "engineNo", "customerName", "mobileNumber", "alternateNo", "fullCustAddress", "previuosServiceType", 
	"previousJobCardDate", "previousHours", "cusrrentServiceDueType", "currentServiceDueDate", "serviceBookingDate",
	"bookingNumber", "serviceCompleteDate"})
public interface ServiceMonitoringBoardResponseDto {
    Long getId();
	String getAction();
    @JsonProperty("Zone")
	String getZone();
	@JsonProperty("Area / State")
	String getArea();	
	@JsonProperty("RSM/ASM Name")
	String getAreaManger();
	@JsonProperty("TM Name")
	String getTerritoryManager();
	@JsonProperty("Sold Dealer Code")
	String getSoldDealerCode();
	@JsonProperty("Sold Dealership Name")
	String getSoldDealershipName();
	@JsonProperty("Product Group")	
	String getProductGroup();
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
	String getCusrrentServiceDueType();
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
