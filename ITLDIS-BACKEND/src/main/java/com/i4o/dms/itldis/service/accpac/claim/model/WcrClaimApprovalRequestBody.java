package com.i4o.dms.itldis.service.accpac.claim.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WcrClaimApprovalRequestBody {
	private String requestFrom;
	private Long dealerId;
	private String claimNo; 
	private String pcrNumber;
	private String wcrType; 
	private String jobcardNumber;
	private String fromDate;
	private String toDate;
	
}
