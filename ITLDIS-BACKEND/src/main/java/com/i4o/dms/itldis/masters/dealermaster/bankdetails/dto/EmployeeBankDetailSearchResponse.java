package com.i4o.dms.itldis.masters.dealermaster.bankdetails.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","approvalStatus","employeeCode","employeeName","mobileNumber","departmentName","designation",
	"bankAccountNumber","bankName","bankBranch","ifsCode","approvedDate","lastApprovedBy",
	"remark"})

public interface EmployeeBankDetailSearchResponse {

	Long getId();

    String getEmployeeCode();
    
    String getEmployeeName();
    
    String getMobileNumber();
    
    String getDepartmentName();
    
    String getDesignationName();
    
    String getBankAccountNo();
    
    String getBankBranch();
    
    String getBankName();
    
    String getIfsCode();
    
    String getApprovalStatus();
    
    String getApprovedDate();
    
    String getLastApprovedBy();
    
    String getRemark();
	
    @JsonIgnore
   	public Long getCount();
}
