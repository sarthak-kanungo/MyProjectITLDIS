package com.i4o.dms.itldis.crm.crmmodule.complaintResolution.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintResolutionSearchRequest {

	private String status;
	private String complaintType;
	private String department;
	private String fromDate;
	private String toDate;
	private Integer page;
	private Integer size;
	
}
