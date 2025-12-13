package com.i4o.dms.itldis.service.activityclaim.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","kaiInvoiceUpload","invoice","dealerName","invoiceNumber","invoiceDate","invoiceType","status","claimNumber","claimDate" })
public interface DealerInvoiceSearchResponseDto {

	String getAction();
	
	Long getId();
	
	String getInvoiceNumber();
	
	String getInvoiceDate();
	
	String getClaimNumber();

	String getClaimDate();

	String getDealerName();
	
	String getInvoiceType();
	
	String getStatus();
	
	String getKaiInvoiceUpload();
	
	String getInvoice();
	
	@JsonIgnore
	Long getRecordCount();
}
