package com.i4o.dms.itldis.spares.reports.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"dealerName","poNumber","poDate","saleOrderNumber","salesOrderDate","itemNumber","itemDescription",
	"poOrderQty","kaiSuppliedQty","pendingBackorderQty","ndpPrice","total"})
public interface BackOrderPartsReportsDto {

	String getDealerName();
	String getPoNumber();
	String getPoDate();
	String getSaleOrderNumber();
	String getSalesOrderDate();
	String getItemNumber();
	String getItemDescription();
	Integer getPoOrderQty();
	String getKaiSuppliedQty();
	String getPendingBackorderQty();
	BigDecimal getNdpPrice();
	BigDecimal getTotal();
	@JsonIgnore
	Long getTotalCount();
}
