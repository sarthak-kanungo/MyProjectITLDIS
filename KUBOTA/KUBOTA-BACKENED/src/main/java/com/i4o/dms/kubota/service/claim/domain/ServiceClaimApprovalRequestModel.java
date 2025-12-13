package com.i4o.dms.kubota.service.claim.domain;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceClaimApprovalRequestModel {

	private Long claimId;
	
	private String remarks;
	
	private String approvalStatus; 
	
	private List<Long> documentsId;
	
	private List<Map<String,Object>> rejectionData;
	
}
