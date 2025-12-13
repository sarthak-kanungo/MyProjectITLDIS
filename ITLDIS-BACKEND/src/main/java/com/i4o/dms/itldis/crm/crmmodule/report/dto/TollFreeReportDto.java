package com.i4o.dms.itldis.crm.crmmodule.report.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "tollFreeCallNumber",	
	"model",
	"machineSerialNumber",
	"dateOfDelivery",
	"dealerName",
	"department",
	"description",
	"customerName",
	"customerNumber",	
	"enquirySavedOn",	
	"state",
	"actionTakenByDealer",
	"frt",
	"frtDelayReasons",
	"actionTakenSharedByDealer",	
	"artDelayReasons",
	"pendingDays",
	"art",
	"customerType",
	"enquiry"})
public interface TollFreeReportDto {
	Long getId();
	String getTollFreeCallNumber();	
	String getModel();	
	String getMachineSerialNumber();	
	String getDateOfDelivery();	
	String getDealerName();	
	String getDepartment();	
	String getDescription();	
	String getCustomerName();	
	String getCustomerNumber();	
	String getEnquirySavedOn();	
	String getState();	
	String getActionTakenByDealer();	
	String getFrt();
	String getFrtDelayReasons();	
	String getActionTakenSharedByDealer();	
	String getArtDelayReasons();	
	String getPendingDays();
	String getArt();
	String getCustomerType();
	String getEnquiry();
	@JsonIgnore
	Integer getTotalCount();
}
