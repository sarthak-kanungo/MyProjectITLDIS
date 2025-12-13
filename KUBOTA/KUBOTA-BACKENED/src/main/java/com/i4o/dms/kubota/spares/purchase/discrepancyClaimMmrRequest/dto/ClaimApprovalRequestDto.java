package com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.dto;

import java.util.List;

import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.domain.SpDiscrepancyKaiAdditionalRemarks;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.domain.SpPartDiscrepancyClaimDtl;

import lombok.Data;

@Data
public class ClaimApprovalRequestDto {
	
	private Long claimId;
	private String remark;
	private String approvalStatus;
	private List<SpPartDiscrepancyClaimDtl> discrepancyClaimDtl;
	private SpDiscrepancyKaiAdditionalRemarks kaiAdditionalRemarks;

}
