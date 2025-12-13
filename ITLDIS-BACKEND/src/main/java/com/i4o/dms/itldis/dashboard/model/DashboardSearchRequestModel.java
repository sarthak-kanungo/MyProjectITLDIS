package com.i4o.dms.itldis.dashboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardSearchRequestModel {

	private String fromDate;
	
	private String toDate;
	
	private Long orgHierId;
	
	private Long dealerId;
	
	private Long branchId;
	
	private String product;
	
	private String series;
	
	private String model;
	
	private String subModel;
	
	private String variant;
	
	private String chassis;
	
	private Integer page;
	
	private Integer size;
	
	private String salesReportOption;
	
	@JsonProperty("mReportType")
	private String mReportType;
	
	private String budgetReportType;
}
