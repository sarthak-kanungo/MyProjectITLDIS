package com.i4o.dms.itldis.dashboard.dto;

public interface DashboardInstallationReportDto {

	String getInstallationType();
	String getDueTarget();
	Integer getNoOfInstallationDone();
	Integer getPercAchieved();
	String getOverDue();
	Integer getLapseNotDoneGreater15Days();
}
