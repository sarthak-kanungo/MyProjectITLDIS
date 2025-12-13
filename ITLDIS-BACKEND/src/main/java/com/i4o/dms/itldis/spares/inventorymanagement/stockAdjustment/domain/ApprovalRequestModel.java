package com.i4o.dms.itldis.spares.inventorymanagement.stockAdjustment.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalRequestModel {

	private Long adjId;
	
	private String remarks;
	
	private String approvalStatus; 
}
