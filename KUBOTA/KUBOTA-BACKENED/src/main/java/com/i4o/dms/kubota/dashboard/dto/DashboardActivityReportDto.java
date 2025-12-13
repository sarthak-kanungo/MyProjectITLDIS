package com.i4o.dms.kubota.dashboard.dto;

public interface DashboardActivityReportDto {

	String getTypeOfActivity();
	Integer getNoOfActivities();
	Integer getNoOfJobCards();
	Integer getSparePartsAmount();
	Double getLabourAmount();
	Double getOutsideCharges();
	Double getWarrantyAmount();
	Double getTotalAmount();
	Double getReportsGenerated();
	Double getClaimRaised();
	Double getClaimApproved();
	Double getClaimSettled();

}
