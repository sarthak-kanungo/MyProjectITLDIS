package com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","claimReqNo","claimDate","status","spGrnNo","spGrnDate","spGrnType","supplierType",
	"noOfBoxesSent","noOfBoxesReceived","dealerCode","dealerName","dealerLocation","accPacInvoiceNo","accPacInvoiceDate",
	"transporter","lrNo"})
public interface DiscrepancyClaimSearchResponseDto {
	
	Long getClaimId();
	String getClaimReqNo();
	String getClaimDate();
	String getStatus();
	String getSpGrnNo();
	String getGrnDate();
	String getGrnType();
	String getSupplierType();
	Integer getNoOfBoxesSent();
	Integer getNoOfBoxesReceived();
	String getDealerCode();
	String getDealerName();
	String getDealerLocation();
	String getAccPacInvoiceNo();
	String getAccPacInvoiceDate();
	String getTransporter();
	String getLrNo();
	String getAction();
	@JsonIgnore
    Long getTotalCount();
	
}
