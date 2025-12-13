package com.i4o.dms.itldis.crm.crmmodule.report.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TollFreeReportRequestModel {

	private String fromDate;
	
	private String toDate;

	private String mobileNo;
	
	private Long orgHierId;
	
	private Long dealerId;
	
	private Long stateId;
	
	private Integer page;
	
	private Integer size;
	
}
