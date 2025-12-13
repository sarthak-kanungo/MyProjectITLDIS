package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ProposalApproval {
	private Boolean isSelect;
    //private Long userId;
    private Long activityProposalId;
   // @NotNull
    private Long activityClaimId;
   // private List<ApprovedHeads> activityHeads;
    private String remark;

    private Double approvedAmount;
    
    private String approvalStatus;
    
    public String toString(){
    	return activityProposalId + " : " + activityClaimId + " : " + remark + " : " + approvedAmount + " : " + approvalStatus;
    }
}
