package com.i4o.dms.itldis.dashboard.dto;

public interface DashboardSalesComplaintReportDto {
	
	String getTypeOfComplaint();
	Integer getReceived();
	Integer getOpen();
	Integer getClosedWithIn3Days();
	Integer getClosedGreater3Days();
}
