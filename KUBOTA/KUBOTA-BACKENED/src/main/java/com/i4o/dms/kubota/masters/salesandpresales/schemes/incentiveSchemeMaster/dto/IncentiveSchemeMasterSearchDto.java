package com.i4o.dms.kubota.masters.salesandpresales.schemes.incentiveSchemeMaster.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","schemeNo", "schemeDate", "schemeType", "status", "referenceNo", "validFrom", "validTo"})
public interface IncentiveSchemeMasterSearchDto {

	Long getId();
	
	String getSchemeNo();
	
	String getSchemeDate();
	
	String getSchemeType();
	
	String getStatus();
	
	String getValidFrom();
	
	String getValidTo();
	
	String getReferenceNo();
	
	@JsonIgnore
	Long getRecordCount();
	
}
