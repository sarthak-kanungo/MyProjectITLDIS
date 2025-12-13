package com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","edit","activeStatus","departmentCode","departmentName","remarks"})

public interface DealerDepartmentSearchResponse {
	
	String getEdit();

    Long getId();
    
    String getDepartmentCode();

    String getDepartmentName();

    String getRemarks();

    Character getActiveStatus();
    
    @JsonIgnore
	public Long getTotalRecords();
}
