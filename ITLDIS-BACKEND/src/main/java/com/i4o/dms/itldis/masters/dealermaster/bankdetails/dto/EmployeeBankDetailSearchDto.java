package com.i4o.dms.itldis.masters.dealermaster.bankdetails.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeBankDetailSearchDto {
	
	private String employeeCode;
	
	private String employeeName;
	
	private String departmentName;
	
	private String designation;

    private Long mobileNo;

    private Integer page;

    private Integer size;
}
