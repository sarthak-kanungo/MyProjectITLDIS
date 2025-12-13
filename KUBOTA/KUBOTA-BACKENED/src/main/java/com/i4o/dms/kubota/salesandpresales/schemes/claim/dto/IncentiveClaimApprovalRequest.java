package com.i4o.dms.kubota.salesandpresales.schemes.claim.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncentiveClaimApprovalRequest {
	
    private String kaiRemarks;
    private Long claimId;
    private String approvalStatus;
}
