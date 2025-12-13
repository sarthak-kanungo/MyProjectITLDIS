package com.i4o.dms.kubota.spares.inventorymanagement.currentstock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonPropertyOrder({"itemNo","itemDescription","binLocation","storeName","spmrp","availableStock"})
public interface CurrentStockResponse {

//	String getPrint();
	
	Long getId();
	
	String getItemNo();
	
	String getItemDescription();
		
	String getBinLocation();
	
	String getStoreName(); 
		
	Double getSpmrp();
	
	Integer getAvailableStock();
	
	@JsonIgnore
	Long getRecordCount();
	
}
