package com.i4o.dms.itldis.service.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"docType","docNumber","itemNumber","itemDescription","totalQty","issueSoldQty","backQty"})
public interface FillRatioItemReportResponseDto {
	
	String getDocType();
	
	String getDocNumber();
	
	String getItemNumber();

	String getItemDescription();
	
	Integer getTotalQty();
	
	@JsonProperty("Issue/Sold Qty")
	Integer getIssueSoldQty();
	
	Integer getBackQty();
}
