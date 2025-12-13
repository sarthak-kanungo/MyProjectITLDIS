package com.i4o.dms.itldis.dashboard.dto;

public interface SalesStockReportDto {
	String getModel();
	Integer getFreshStock();
	Integer getReturnedStock();
	Integer getTransitStock();
	Integer getTotal();
}
