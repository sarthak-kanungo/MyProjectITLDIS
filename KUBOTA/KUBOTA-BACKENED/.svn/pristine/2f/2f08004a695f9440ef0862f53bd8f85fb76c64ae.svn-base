package com.i4o.dms.kubota.spares.reports.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"dealerName","stockOnDate","itemNumber","itemDescription","availableStock","store","binLocation",
	"ndpPrice","mrpPrice","total"})
public interface ClosingStockReportDto {

	String getDealerName();
	String getStockOnDate();
	String getItemNumber();
	String getItemDescription();
	Integer getAvailableStock();
	String getStore();
	String getBinLocation();
	BigDecimal getNdpPrice();
	BigDecimal getMrpPrice();
	BigDecimal getTotal();
	@JsonIgnore
	Long getTotalCount();
}
