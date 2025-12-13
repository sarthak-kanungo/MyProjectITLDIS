package com.i4o.dms.itldis.service.jobcard.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"dealerName","jobCardNo","jobCardDate","machineInDate","status","chassisNo","engineNo","model","category","serviceType","currentHours","pcrNo"})
public interface MachineServiceHistoryDto {
	 String getMachineInDate();
	 String getServiceType();
	 String getCurrentHours();
	 String getJobCardNo();
	 String getJobCardDate();
	 String getStatus();
	 String getChassisNo();
	 String getEngineNo();
	 String getModel();
	 String getDealerName();
	 String getPcrNo();
	 String getCategory();
	 @JsonIgnore
	 Long getRecordCount();
}
