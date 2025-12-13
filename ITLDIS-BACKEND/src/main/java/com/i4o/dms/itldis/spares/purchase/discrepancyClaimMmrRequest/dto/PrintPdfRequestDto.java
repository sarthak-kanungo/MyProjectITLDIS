package com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.dto;

import lombok.Data;

@Data
public class PrintPdfRequestDto {
	
	private String claimId;
	private String claimNo;
	private String printStatus;

}
