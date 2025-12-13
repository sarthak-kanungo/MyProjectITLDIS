package com.i4o.dms.itldis.crm.crmmodule.report.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrmReportRequestModel {

	private String fromDate;
	
	private String toDate;
	
	private Long orgHierId;
	
	private Long dealerId;
	
	private Long stateId;
	
	private String product;
	
	private String series;
	
	private String model;
	
	private String subModel;
	
	private String variant;
	
	private String department;
	
	private String reportType;
	
	private Integer page;
	
	private Integer size;
	
	private String fromYear;
	
	private String toYear;
	
	private String complaintCode;
}
