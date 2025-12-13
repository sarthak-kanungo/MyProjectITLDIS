package com.i4o.dms.kubota.spares.purchase.blockpartsforsale.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","activeStatus","partNo","partDescription"})
public interface BlockPartsForSaleSearchRespose {
	
	Long getId();
	
	String getPartNo();
	
	String getPartDescription();
	
	String getActiveStatus();
	
	@JsonIgnore
	public Long getCount();
	
}
