package com.i4o.dms.kubota.salesandpresales.reports.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
	"zone",	
	"area",	
	"dealerCode",	
	"dealerName",	
	"dealerState",	
	"activityNumber",	
	"acitivityStatus",
	"approvedAmount",	
	"approvedMonth",	
	"totalEnquires",	
	"hotEnquires",	
	"warmEnquires",	
	"coldEnquires",	
	"costperEnquiry",	
	"costPerHotEnquiry",	
	"totalBookings",	
	"costPerBooking",	
	"totalRetails",	
	"costPerRetail"
})
public interface ActivityEnquiryReportResponse {

	String getZone();	
	String getArea();	
	String getDealerCode();	
	String getDealerName();	
	String getDealerState();	
	String getActivityNumber();	
	String getAcitivityStatus();	
	BigDecimal getApprovedAmount();	
	Integer getApprovedMonth();	
	Integer getTotalEnquires();	
	Integer getHotEnquires();	
	Integer getWarmEnquires();	
	Integer getColdEnquires();	
	BigDecimal getCostperEnquiry();	
	BigDecimal getCostPerHotEnquiry();	
	Integer getTotalBookings();	
	BigDecimal getCostPerBooking();	
	Integer getTotalRetails();	
	BigDecimal getCostPerRetail();
	
	@JsonIgnore
	Long getRecordCount();
}
