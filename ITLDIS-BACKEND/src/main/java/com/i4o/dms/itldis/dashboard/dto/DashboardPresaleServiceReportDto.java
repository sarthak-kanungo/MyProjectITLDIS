package com.i4o.dms.itldis.dashboard.dto;

public interface DashboardPresaleServiceReportDto {

	String getServiceType();
	Integer getDue();
	Integer getDone();
	Double getPercAchieved();
	Integer getWithinDueDate();
	Integer getAfterDueDateDue();
	
}
