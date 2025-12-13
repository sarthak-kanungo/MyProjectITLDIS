package com.i4o.dms.itldis.warranty.hotlinereport.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarrantyHotlineReportSearchDto {
	
	private String hotlineNo;
	private String status;
	private String fromDate;
	private String toDate;
	private Long page;
	private Long size;
	
}
