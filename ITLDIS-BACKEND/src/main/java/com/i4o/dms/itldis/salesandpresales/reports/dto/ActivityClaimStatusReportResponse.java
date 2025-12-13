package com.i4o.dms.itldis.salesandpresales.reports.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
	"state",
	"dealerCode",
	"dealerName",	
	"claimUploaded",	
	"claimApproved",	
	"claimRejected",	
	"pendingForApproval",	
	"ageingLessThan30",
	"ageingLessThan31To45",	
	"dhApproval",
	"completed",	
	"pending"})
public interface ActivityClaimStatusReportResponse {

	@JsonIgnore
	Long getRecordCount();
	Long getId();
	String getState();
	String getDealerCode();
	String getDealerName();
	String getClaimUploaded();	
	String getClaimApproved();	
	String getClaimRejected();
	String getPendingForApproval();	
	String getAgeingLessThan30();
	String getAgeingLessThan31To45();	
	String getDhApproval();
	String getCompleted();	
	String getPending();
}
