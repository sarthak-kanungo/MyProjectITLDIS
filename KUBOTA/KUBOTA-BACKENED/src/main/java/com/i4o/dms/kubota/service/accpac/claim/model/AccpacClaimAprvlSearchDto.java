package com.i4o.dms.kubota.service.accpac.claim.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","action","documentNumber","documentDate","status","totalClaimCount","totalClaimAmount"})
public interface AccpacClaimAprvlSearchDto {

	Long getId();
	
	String getDocumentNumber();
	
	String getDocumentDate();
	
	String getStatus();
	
	Integer getTotalClaimCount();
	
	BigDecimal getTotalClaimAmount();
	
	@JsonProperty("action")
	String getTaction();
	
	@JsonIgnore
	Long getRecordCount();
}
