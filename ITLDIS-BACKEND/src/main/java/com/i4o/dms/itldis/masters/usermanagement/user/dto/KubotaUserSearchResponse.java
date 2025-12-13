package com.i4o.dms.itldis.masters.usermanagement.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","action","employeeCode","employeeName","employeeStatus","loginIdStatus"})
@JsonIgnoreProperties({"count"})
public interface KubotaUserSearchResponse {

    Long getId();

    String getEmployeeCode();

    String getEmployeeName();

    String getLoginIdStatus();

    Character getEmployeeStatus();
    
    Long getCount();
    
    String getAction();

}



