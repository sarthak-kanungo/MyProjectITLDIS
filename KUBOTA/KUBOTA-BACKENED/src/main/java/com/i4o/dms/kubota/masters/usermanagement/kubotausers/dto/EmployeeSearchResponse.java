package com.i4o.dms.kubota.masters.usermanagement.kubotausers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","action","activeStatus","updateOrghierarchy", "employeeCode", "employeeName", "emailId", "contactNo","reportingEmployeeCode", "reportingEmployeeName", 
	"departmentName", "designation", "designationLevel", "managementAccess"})
@JsonIgnoreProperties({"totalRecords"})
public interface EmployeeSearchResponse {

    Long getId();

    Character getActiveStatus();

    String getEmployeeCode();

    String getEmployeeName();

    String getEmailId();

    String getContactNo();

    String getReportingEmployeeCode();

    String getReportingEmployeeName();

    Boolean getManagementAccess();
    
    String getDepartmentName();
    
    String getDesignation();
    
    String getDesignationLevel();
    
    String getAction();
    
    String getUpdateOrghierarchy();
    
    Long getTotalRecords();

}




