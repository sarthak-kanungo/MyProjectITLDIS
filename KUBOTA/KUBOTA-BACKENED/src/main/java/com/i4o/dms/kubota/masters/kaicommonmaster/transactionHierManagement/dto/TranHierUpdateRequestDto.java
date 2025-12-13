package com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.dto;

import java.util.List;

import com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.domain.SystemApprovalFlowMaster;

import lombok.Data;

@Data
public class TranHierUpdateRequestDto {
	
	private List<SystemApprovalFlowMaster> approvalFlowMasters;
	
}
