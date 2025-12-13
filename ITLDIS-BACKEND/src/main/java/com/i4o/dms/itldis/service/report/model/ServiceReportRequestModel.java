package com.i4o.dms.itldis.service.report.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceReportRequestModel {

	private String fromDate;
	
	private String toDate;
	
	private Long customerId;
	
	private Long orgHierId;
	
	private Long dealerId;
	
	private Long branchId;
	
	private String product;
	
	private String series;
	
	private String model;
	
	private String subModel;
	
	private String variant;
	
	private String chassisNo;
	
	private Integer page;
	
	private Integer size;
	
	private String cfromDate;
	
	private String ctoDate;
	
	private String jfromDate;
	
	private String jtoDate;
}
