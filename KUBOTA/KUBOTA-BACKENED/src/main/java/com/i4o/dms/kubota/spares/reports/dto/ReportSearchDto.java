package com.i4o.dms.kubota.spares.reports.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportSearchDto {

	private String fromDate;

	private String toDate;
	
	private String stockOnDate;
	
	private Long orgHierId;
	
	private Long dealerId;
	
	private Long branchId;
	
	private String product;
	
	private String series;
	
	private String model;
	
	private String subModel;
	
	private String variant;
	
	private Integer page;
	
	private Integer size;
	
	private Long state;
	
	private String partNumber;
}
