package com.i4o.dms.kubota.dashboard.dto;

public interface SalesStockReportDto {
	String getModel();
	Integer getFreshStock();
	Integer getReturnedStock();
	Integer getTransitStock();
	Integer getTotal();
}
