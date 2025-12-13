package com.i4o.dms.kubota.spares.purchase.backordercancellation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author mahesh.kumar
 */
@JsonPropertyOrder({"action", "bocNo", "dealer", "dealerCode", "reqGivenBy", "status", "Approve"})
public interface SPBackOrderCancellationResponseDto {
	
	String getBocNo();
	String getDealer();
	String getDealerCode();
	String getReqGivenBy();
	String getAction();
	String getStatus();
	String getApprove();
    @JsonIgnore
    Long getTotalCount();

}
