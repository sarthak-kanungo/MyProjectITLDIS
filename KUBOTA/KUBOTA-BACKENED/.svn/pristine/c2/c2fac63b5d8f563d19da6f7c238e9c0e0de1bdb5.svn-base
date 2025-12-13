package com.i4o.dms.kubota.service.report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","dealerName","docType","docNumber","docDate","requesitionLineItemsCount","issuedLintItemsCount",
	"fullyCompleted", "partiallyCompleted", "fillRateFull", "fillRatePartial", "remarks"})
public interface FillRatioReportResponseDto {
	Long getId(); 
	String getDocType();
	String getDocNumber();
	String getDocDate();
	@JsonProperty("Line Items")
	Integer getRequesitionLineItemsCount();
	@JsonProperty("Line Items Issued/Sold")
	Integer getIssuedLintItemsCount();
	Integer getFullyCompleted();
	Integer getPartiallyCompleted();
	Double getFillRateFull();
	Double getFillRatePartial();
	String getRemarks();
	String getDealerName();
	@JsonIgnore
	Long getRecordsCount();
}
