package com.i4o.dms.kubota.salesandpresales.sales.blockmachinesforsale.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","activeStatus","product","series","model","subModel","variant"})

public interface BlockMachineForSaleSearchResponse {
	
	Long getId();
	
	String getProduct();
	
	String getSeries();
	
	String getModel();
	
	String getSubModel();
	
	String getVariant();
	
	String getActiveStatus();
	
	@JsonIgnore
	Long getCount();
	
}
