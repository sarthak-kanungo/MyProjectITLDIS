package com.i4o.dms.itldis.spares.inventorymanagement.btbt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonPropertyOrder({"print","transferNumber","transferDate","itemNo","itemDescription","fromStore","fromLocation","fromBinStock","mrp","transferQty","toStore","toLocation","toBinStock"})
public interface SpareBinTransferSearchResponseDto {

	String getPrint();
	
	Long getId();
	
	String getTransferNumber();
	
	String getTransferDate();
	
	String getItemNo();
	
	String getItemDescription();
	
	String getFromStore();
	
	String getFromLocation();
	
	@JsonIgnore
	Integer getFromBinStock();

	Integer getTransferQty();
	
	String getToStore(); 
	
	String getToLocation();
	
	@JsonProperty("MRP")
	Double getMrp();
	
	@JsonIgnore
	Integer getToBinStock();
	
	@JsonIgnore
	Long getRecordCount();
	
}
