package com.i4o.dms.itldis.spares.reports.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"dealerName","itemNumber","itemDescription","qty","location","ageingYear1","ageingYear2","ageingYear3","ageingYearMoreThan3",
	"ndpPrice","mrpPrice","total"})
public interface NonMovingPartsStockReportDto {

	String getDealerName();
	String getItemNumber();
	String getItemDescription();
	Integer getQty();
	String getLocation();
	String getAgeingYear1();
	String getAgeingYear2();
	String getAgeingYear3();
	String getAgeingYearMoreThan3();
	BigDecimal getNdpPrice();
	BigDecimal getMrpPrice();
	BigDecimal getTotal();
	@JsonIgnore
	Long getTotalCount();
}
