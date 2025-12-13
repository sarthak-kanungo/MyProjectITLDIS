package com.i4o.dms.itldis.service.jobcard.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"approve","id","jobCardNo","status","chassisNo","engineNo","model","jobCardDate","dealerName","serviceCategory","placeOfService","taskDate","taskTime"})
public interface JobcardReopenApprovalSearchResponseDto {

	    Long getId();
	    String getStatus();
	    String getJobCardNo();
	    String getChassisNo();
	    String getEngineNo();
	    String getModel();
	    String getApprove();
	    String getJobCardDate();
	    String getDealerName();
	    String getServiceCategory();
	    String getPlaceOfService();
	    String getTaskDate();
	    String getTaskTime();
	    @JsonIgnore
		public Long getRecordCount();
}
