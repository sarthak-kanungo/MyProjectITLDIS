package com.i4o.dms.kubota.spares.inventorymanagement.machinestock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"zone","region","area","dealerCode","dealerName",
	"dealerLocation","invoiceNo","invoiceDate","product","series","model","subModel",
	"variant","itemNo","itemDescription","chassisNo","engineNo",//"status",
	"lastTransactinType","lastTransactinNo","lastTransactinDate"})
@JsonIgnoreProperties({"totalCount"})
public interface MachineStockSearchResponse {

	 String getZone();	
	 String getRegion();	
	 String getArea();	
	 String getDealerCode();	
	 String getDealerName();	
	 String getDealerLocation();	
	 String getProduct();	
	 String getSeries();	
	 String getModel();	
	 String getSubModel();	
	 String getVariant();	
	 String getItemNo();	
	 String getItemDescription();	
	 String getChassisNo();	
	 String getEngineNo();	
	 //String getStatus();	
	 String getLastTransactinType();	
	 String getLastTransactinNo();	
	 String getLastTransactinDate();
	 Long getTotalCount();
	 
	 String getDealerState();	//Suraj--04/11//2022
	 
	 String getInvoiceNo();	//Suraj--02-01-2023
	 String getInvoiceDate();	//Suraj--02-01-2023

}
