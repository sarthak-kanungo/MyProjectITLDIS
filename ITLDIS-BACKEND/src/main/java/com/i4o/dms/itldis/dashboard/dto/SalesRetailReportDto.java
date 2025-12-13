package com.i4o.dms.itldis.dashboard.dto;

public interface SalesRetailReportDto {
	 String getModel();
     Integer getNoOfRetails();
     Integer getTotalEnquiries();
     Double getConversionRatio();
     Integer getRetailSameDay();
     Integer getRetailWithin7Days();
     Integer getRetailWithin15Days();
     Integer getRetailWithin30Days();
     Integer getRetailWithin60Days();
     Integer getRetailMoreThan60Days();
     Integer getNoOfCashCases();
     Integer getNoOfLoanCases();
     Integer getNoOfExchange();
}
