package com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobCardSearchDto {
	
	private String chassisNo;
	
	private String mobileNo;
	
	private String customerName;
	
	private Integer page;
	
	private Integer size;
	
}
