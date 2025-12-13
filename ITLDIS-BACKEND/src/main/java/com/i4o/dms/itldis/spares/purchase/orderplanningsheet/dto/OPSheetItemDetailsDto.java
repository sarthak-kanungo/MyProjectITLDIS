package com.i4o.dms.itldis.spares.purchase.orderplanningsheet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author suraj.gaur
 */
public interface OPSheetItemDetailsDto {

	Long getId();
	
	String getItemNo();
	
	String getItemDescription();
	
	Integer getReorderQty();
	
	Integer getDealerStock();
	
	Integer getKaiBackOrder();
	
	Integer getTransitFromKai();
	
	Integer getUnitPrice();
	
	Integer getGstPercent();
	
	Integer getL12mSales();
	
	Integer getSuggestedOrderQty();
	
	@JsonIgnore 
	Long getTotalCount();
}
