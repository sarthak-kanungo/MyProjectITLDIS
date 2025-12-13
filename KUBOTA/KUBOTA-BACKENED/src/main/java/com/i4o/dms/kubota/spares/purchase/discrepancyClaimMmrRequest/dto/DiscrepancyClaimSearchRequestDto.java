package com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.dto;

import lombok.Data;

@Data
public class DiscrepancyClaimSearchRequestDto {
	
	private String claimNo;
	private String claimType;
	private String claimStatus;
	private String grnNo;
	private String invoiceNo;
	private String fromDate;
	private String toDate;
	private Integer page;
	private Integer size;

}
