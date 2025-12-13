package com.i4o.dms.itldis.masters.dealermaster.createdealeruser.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"edit","id","employeeCode","employeeName","loginIdStatus","employeeStatus"})
public interface DealerSearchResponse {
	
	Long getId();
	
	String getEdit();

	String getEmployeeCode();

	String getEmployeeName();

	String getLoginIdStatus();

	String getEmployeeStatus();

	@JsonIgnore
	public Long getCount();

}
