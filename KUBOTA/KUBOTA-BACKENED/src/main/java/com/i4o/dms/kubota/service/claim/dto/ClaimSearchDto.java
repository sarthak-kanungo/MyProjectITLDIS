package com.i4o.dms.kubota.service.claim.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
	"taction","dealerCode","dealerName","dealerState", "claimNo", "claimDate", "claimStatus", "fromDate", "toDate", "claimAmount", "bonusAmount", "totalClaimAmount", "totalApprovedAmount", 
	"rcnNumber",
	"rcnDate",
	"lastApprovedBy"
})
public interface ClaimSearchDto {

	String getId();
	
	String getClaimNo();
	
	String getClaimDate();
	
	String getClaimStatus();
	
	String getFromDate();
	
	String getToDate();
	
	BigDecimal getClaimAmount();
	
	BigDecimal getBonusAmount();
	
	BigDecimal getTotalClaimAmount();
	
	BigDecimal getTotalApprovedAmount();
	
	String getLastApprovedBy();
	
	@JsonProperty("action")
	String getTaction();
	

    String getDealerCode();
    String getDealerName();
    String getDealerState();
	@JsonIgnore
	Long getTotalCount();

    String getRcnNumber();
    String getRcnDate();
}
