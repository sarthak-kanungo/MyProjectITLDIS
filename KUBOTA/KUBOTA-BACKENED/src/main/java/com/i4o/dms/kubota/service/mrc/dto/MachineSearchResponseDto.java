package com.i4o.dms.kubota.service.mrc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","formF","form22","dealer","machineNo","engine","product","series","model","subModel","variant"})
public interface MachineSearchResponseDto {

	String getFormF();
	@JsonProperty("Form 22")
	String getForm22();
	
	String getDealer();
	
	String getMachineNo();
	
	String getEngine();
	
	String getProduct();
	
	String getSeries();
	
	String getModel();
	
	String getSubModel();
	
	String getVariant();
	
	Long getId();

	@JsonIgnore
	Long getRecordCount();
}
