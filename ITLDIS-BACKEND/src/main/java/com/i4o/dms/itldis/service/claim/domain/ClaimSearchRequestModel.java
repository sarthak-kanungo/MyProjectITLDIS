package com.i4o.dms.itldis.service.claim.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimSearchRequestModel {
	
	private String claimNumber;
	
	private String fromDate;
	
	private String toDate;
	
	private String claimStatus;
	
	private Integer page;
	
	private Integer size;
	
	private Long hierId;	//Suraj_02-11-2023

}
