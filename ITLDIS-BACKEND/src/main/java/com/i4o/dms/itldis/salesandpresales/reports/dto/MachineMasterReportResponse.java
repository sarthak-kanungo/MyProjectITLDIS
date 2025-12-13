package com.i4o.dms.itldis.salesandpresales.reports.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonPropertyOrder({"productGroup","product","series","model","subModel","variant","itemCode","description"})
public interface MachineMasterReportResponse {
	
	String getProductGroup();
	String getProduct();
	String getSeries();
	String getModel();
	String getSubModel();
	String getVariant();
	String getItemCode();
	String getDescription();
	@JsonIgnore
	Long getRecordCount();
}
