package com.i4o.dms.itldis.spares.inventorymanagement.stockAdjustment.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"adjustmentNumber",
	    "adjustmentDate",
	    "adjustmentType",
	    "adjustmentStatus",
	    "partNo",
	    "description",
	    "store",
	    "binLocation", 
	    "mrp",
	    "qtyAdjusted"})
public interface StockAdjustmentDeatils {

	    String getAdjustmentNumber();
	    String getAdjustmentDate();
	    String getAdjustmentType();
	    String getAdjustmentStatus();
	    String getPartNo();
	    String getDescription();
	    String getStore();
	    String getBinLocation(); 
	    Double getMrp();
	    Integer getQtyAdjusted();
}
