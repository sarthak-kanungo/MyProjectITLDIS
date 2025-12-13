package com.i4o.dms.itldis.masters.dealermaster.bankapproval.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","dealerName","dealerCode","employeeCode","employeeName","bankName",
	"bankAccountNumber","ifsCode","panCardNo"})

public interface EmployeeBankApprovalSearchResponse {
	
	Long getId();

    String getDealerName();
    
    String getDealerCode();
    
    String getEmployeeCode();
    
    String getEmployeeName();
    
    String getBankName();
    
    String getBankAccountNo();
    
    String getIfsCode();
    
    String getPanCardNo();
	
    @JsonIgnore
   	public Long getCount();
}
