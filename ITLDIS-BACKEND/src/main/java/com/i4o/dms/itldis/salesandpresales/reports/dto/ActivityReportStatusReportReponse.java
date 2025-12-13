package com.i4o.dms.itldis.salesandpresales.reports.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
	"state",
	"dealerCode",
	"dealerName",	
	"approvedProposalTillDate",	
	"reportUplodedTillDate",	
	"reportPending",	
	"contactReceived",	
	"hpReceived",
	"bookingReceived",	
	"retailDone",
	"deferred",	
	"lost",	
	"openContact",	
	"openHp"})
public interface ActivityReportStatusReportReponse {

	@JsonIgnore
	Long getRecordCount();
	String getState();
	String getDealerCode();
	String getDealerName();	
	String getApprovedProposalTillDate();	
	String getReportUplodedTillDate();
	String getReportPending();	
	String getContactReceived();	
	String getHpReceived();
	String getBookingReceived();	
	String getRetailDone();
	String getDeferred();	
	String getLost();	
	String getOpenContact();	
	String getOpenHp();
}
