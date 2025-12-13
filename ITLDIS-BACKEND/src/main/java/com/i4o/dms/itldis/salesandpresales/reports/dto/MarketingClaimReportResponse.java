package com.i4o.dms.itldis.salesandpresales.reports.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
	"dealerCode",
	"dealerName",	
	"dealerState",
	"claimNumber",	
	"claimStatus",	
	"claimDate",	
	"activityNumber",	
	"activityStatus",	
	"activityCreationDate",
	"activityType",	
	"noOfDays",
	"fromActivityDate",	
	"toActivityDate",	
	"actualFromActivityDate",	
	"actualToActivityDate",	
	"location",	
	"hotEnquiries",	
	"warmEnquiries",
	"coldEnquiries",	
	"costPerEnquiry",	
	"costPerHotEnquiry",	
	"totalBookings",	
	"costPerBooking",	
	"totalRetails",	
	"costPerRetail",	
	"activityEffectiveness",
	"approvedAmount",	
	"actualClaimAmount",	
	"lastApprovedBy"})
public interface MarketingClaimReportResponse {

	
	String getClaimNumber();	
	String getClaimStatus();	
	String getClaimDate();
	String getActivityNumber();	
	String getActivityStatus();	
	String getActivityCreationDate();
	String getActivityType();	
	Integer getNoOfDays();
	String getFromActivityDate();	
	String getToActivityDate();	
	String getActualFromActivityDate();	
	String getActualToActivityDate();	
	String getLocation();		
	Integer getHotEnquiries();	
	Integer getWarmEnquiries();
	Integer getColdEnquiries();	
	Integer getCostPerEnquiry();	
	BigDecimal getCostPerHotEnquiry();	
	Integer getTotalBookings();	
	BigDecimal getCostPerBooking();	
	Integer getTotalRetails();	
	BigDecimal getCostPerRetail();	
	String getActivityEffectiveness();
	BigDecimal getApprovedAmount();	
	BigDecimal getActualClaimAmount();	
	String getLastApprovedBy();
	String getDealerCode();
	String getDealerName();	
	String getDealerState();
	
	@JsonIgnore
	Long getRecordCount();
}
