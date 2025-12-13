package com.i4o.dms.itldis.masters.dealermaster.bankapproval.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeBankApprovalSearchDto {
	
	private Long dealerId;
	
	private String employeeCode;
	
	private String employeeName;
	
	private String fromDate;

    private String toDate;

    private Integer page;

    private Integer size;

}
