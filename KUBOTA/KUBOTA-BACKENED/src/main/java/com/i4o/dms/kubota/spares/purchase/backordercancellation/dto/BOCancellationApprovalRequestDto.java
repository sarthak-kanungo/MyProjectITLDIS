package com.i4o.dms.kubota.spares.purchase.backordercancellation.dto;

import com.i4o.dms.kubota.spares.purchase.backordercancellation.domain.SPBackOrderCancellation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BOCancellationApprovalRequestDto {
		
	private String remark;
	
	private String approvalStatus;
	
	private SPBackOrderCancellation backOrderCancellation;

}
