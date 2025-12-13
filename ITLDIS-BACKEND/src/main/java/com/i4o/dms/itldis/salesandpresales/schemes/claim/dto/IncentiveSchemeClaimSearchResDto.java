package com.i4o.dms.itldis.salesandpresales.schemes.claim.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","invoice","dealerCode","dealerName","claimNo","claimDate", "month","status","claimAmount"})
public interface IncentiveSchemeClaimSearchResDto {

	Long getId();
	
	String getClaimNo();
	
	String getClaimDate();
	
	String getDealerCode();
	
	String getDealerName();
	
	String getMonth();
	
	String getStatus();
	
	BigDecimal getClaimAmount();
	
	String getAction();
	
	String getInvoice();
	
	@JsonIgnore
	Long getRecordsCount();
}
