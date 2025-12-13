package com.i4o.dms.itldis.warranty.warrantyclaimrequest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WcrApprovalDto {

	private Long id;
	private String remarks;
	private String approvalStatus;
	private Boolean isSelect;
	private Long status;
}
