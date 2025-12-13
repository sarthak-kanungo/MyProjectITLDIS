package com.i4o.dms.itldis.spares.inventorymanagement.stockAdjustment.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"id","action","dealerCode","dealerName","adjustmentNo","adjustmentDate","adjustmentStatus","adjustmentValue","totalLineItem"})
public interface StockAdjustmentSearchResult {

	Long getId();
	
	String getAdjustmentNo();
	
	String getAdjustmentStatus();
	
	String getAdjustmentDate();
	
	@JsonIgnore
	String getAdjustmentType();
	@JsonIgnore
	String getItemNo();
	@JsonIgnore
	String getItemDesc();
	@JsonIgnore
	String getStore();
	@JsonIgnore
	String getBin();
	@JsonIgnore
	String getReason();
	@JsonIgnore
	Integer getQuantity();
	@JsonIgnore
	BigDecimal getMrp();
	
	BigDecimal getAdjustmentValue();
	
	String getAction();
	
	String getDealerCode();
	
	String getDealerName();
	
	@JsonIgnore
	Long getTotalCount();
	
	Integer getTotalLineItem();
	
	//--------------------Suraj-23/09/22
//    String getHierId();
//    
//    String getDealerId();
    //--------------------
}
