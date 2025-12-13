package com.i4o.dms.kubota.service.accpac.claim.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccpacClaimApprovalSearchModel {

	private String fromDate;
	private String toDate;
	private String claimType;
	private String status;
	private Integer page;
	private Integer size;
}
