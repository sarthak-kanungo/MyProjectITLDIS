package com.i4o.dms.itldis.masters.dealermaster.bankapproval.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeBankProposalApprovalRequest {

	private List<EmployeeBankProposalApproval> employeeBankProposalApprovalList;
	private String remark;
	private String approvalType;
	
}
