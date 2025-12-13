package com.i4o.dms.kubota.dashboard.dto;

public interface DashboardPresaleServiceReportDto {

	String getServiceType();
	Integer getDue();
	Integer getDone();
	Double getPercAchieved();
	Integer getWithinDueDate();
	Integer getAfterDueDateDue();
	
}
