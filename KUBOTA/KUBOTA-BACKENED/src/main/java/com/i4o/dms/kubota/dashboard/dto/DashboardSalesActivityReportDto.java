package com.i4o.dms.kubota.dashboard.dto;

public interface DashboardSalesActivityReportDto {

	String getTypeOfActivity();
	String getActivityType();
	Integer getNoOfActivities();
	Integer getNoOfEnquiries();
	Integer getRetailed();
	Double getHot();
	Double getWarm();
	Double getCold();
	Double getLost();
	Double getDrop();
	Double getReportsGenerated();
	Double getClaimRaised();
	Double getClaimApproved();
	Double getClaimSettled();

}
