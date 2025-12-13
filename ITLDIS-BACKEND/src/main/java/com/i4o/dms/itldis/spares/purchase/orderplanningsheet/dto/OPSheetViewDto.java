package com.i4o.dms.itldis.spares.purchase.orderplanningsheet.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author suraj.gaur
 */
public interface OPSheetViewDto {
	
	Long getId();
	
	String getOpsNo();
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date getOrderDate();
	
	SpareOrderPlanningOrderType getPlanningOrderType();
	interface SpareOrderPlanningOrderType {
		Long getId();
		
		String getOrderType();
		
		boolean getActiveFlag();
	}
	
	Boolean getDraftFlag();
	
	float getReorderQtyMonths();
	
	float getSuggestedOrderQty();
	
	SPOrderPlanningSheetLogic getOpSheetLogic();
	interface SPOrderPlanningSheetLogic {
		Long getId();
	    
	    String getLogic();
	    
	    String getStatus();
	}
	
	Double getTotalOrderValue();
	
	String getStatus();
	
	List<SPOrderPlanningSheetDetail> getOrderPlanningSheetDetails();
	interface SPOrderPlanningSheetDetail {
		Long getId();
		
	    SparePartMaster getSparePartMaster();
	    interface SparePartMaster {
	    	Long getId();
	    	
	    	String getItemNo();

	        String getItemDescription();
	    }
		
		Integer getReorderQty();
		
		Integer getDealerStock();
		
		Integer getKaiBackOrder();
		
		Integer getTransitFromKai();
		
		Integer getL12mSales();
		
		Integer getSuggestedOrderQty();
		
		Integer getActualOrderQty();
		
		Double getOrderValue();
		
		float getUnitPrice();
		
		float getGstPercent();
	}

}
