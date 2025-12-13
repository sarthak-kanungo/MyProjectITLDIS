package com.i4o.dms.kubota.service.activityclaim.dto;

import java.util.List;

import com.i4o.dms.kubota.service.activityproposal.domain.ServiceActivityProposalHeads;
import com.i4o.dms.kubota.service.activityproposal.domain.ServiceActivityProposalSubActivity;

import lombok.Data;

@Data
public class ServiceActivityClaimEditReqDto {
	
	private Long claimId;
	private String claimStatus;
	private Long activityTypeId;
	private List<ServiceActivityProposalHeads> activityProposalHeads; 
	private List<ServiceActivityProposalSubActivity> activityProposalSubActivities;

}
