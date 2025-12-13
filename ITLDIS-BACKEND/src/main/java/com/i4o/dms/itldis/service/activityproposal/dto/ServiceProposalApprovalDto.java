package com.i4o.dms.itldis.service.activityproposal.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceProposalApprovalDto {
    private Long serviceActivityProposalId;
    private Long serviceActivityClaimId;
    private Double approvedAmount;
    private String remark;
    private String approvalType;
//    private Boolean approvedFlag;
    private String approvedFlag;	//Suraj-18/10/2022
    
    private List<IdModel> subActivityDetails;
    private List<IdModel> headDetails;
}
