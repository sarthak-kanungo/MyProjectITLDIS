package com.i4o.dms.itldis.dashboard.dto;

public interface DashboardWarrantyEnquiryReportDto {
	String getSourceOfEnquiry();
	Integer getNoOfEnquiries();
	Integer getRetailed();
	Integer getPendingForValidation();
	Double getHot();
	Double getWarm();
	Double getCold();
	Double getLost();
	Double getDrop();
	Double getAgeingLessSevenDays();
	Double getAgeingLessFifteenDays();
	Double getAgeingLessThirtyDays();
	Double getAgeingLessSixtyDays();
	Double getAgeingLessNintyDays();
	Double getAgeingGreaterNintyDays();
	Double getNotContacted();
}
