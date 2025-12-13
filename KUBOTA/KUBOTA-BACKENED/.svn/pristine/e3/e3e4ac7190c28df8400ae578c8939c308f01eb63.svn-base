package com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"edit","employeeName","branch_Name","isActive"})
public interface AssignUserToBranchSearchResponse {
	Long getId();
	Long getDealerEmployeeId();
	String getEmployeeName();
	String getBranch_Name();
	String getIsActive();
	String getEdit();
    
    @JsonIgnore
   	public Long getCount();

}
