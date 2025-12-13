package com.i4o.dms.itldis.salesandpresales.reports.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
	"state",
	"dealerCode",
	"dealerName",	
	"pendingProposalLastMonth",	
	"proposalReceived",	
	"proposalRejected",	
	"proposalHold",	
	"proposalApproved",
	"pendingForApproval"})
public interface ActivityPropoalStatusReportResponse {

	@JsonIgnore
	Long getRecordCount();
	Long getId();
	String getState();
	String getDealerCode();
	String getDealerName();	
	String getPendingProposalLastMonth();	
	String getProposalReceived();
	String getProposalRejected();
	String getProposalHold();
	String getProposalApproved();
	String getPendingForApproval();
}
