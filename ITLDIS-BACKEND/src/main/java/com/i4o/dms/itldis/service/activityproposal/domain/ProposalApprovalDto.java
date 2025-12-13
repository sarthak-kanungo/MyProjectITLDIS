package com.i4o.dms.itldis.service.activityproposal.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalApprovalDto {
	
	private Boolean isSelect;

	private Long activityProposalId;

	private String remark;

    private Double approvedAmount;
    
    private String approvalStatus;
    
    public String toString() {
    	return activityProposalId + " : " + remark + " : " + approvedAmount + " : " + approvalStatus;
    }
    
}
