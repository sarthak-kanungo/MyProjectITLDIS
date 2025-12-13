package com.i4o.dms.itldis.masters.dealermaster.customermaster.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerMasterSearchDto {
	private String customerCode;
	private String mobileNo;
	private Integer page;
	private Integer size;
	
}
