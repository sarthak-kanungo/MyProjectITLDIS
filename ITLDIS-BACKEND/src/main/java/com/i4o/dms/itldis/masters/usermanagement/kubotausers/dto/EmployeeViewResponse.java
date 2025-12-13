package com.i4o.dms.itldis.masters.usermanagement.kubotausers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","action","activeStatus", "employeeCode", "employeeName", "emailId", "contactNo","reportingEmployeeCode", "reportingEmployeeName", 
	"departmentName", "designation", "designationLevel", "managementAccess", "directRepotee", "departmentId", "designationId", "designationLevelId", "reportingId"})

@JsonIgnoreProperties({"totalRecords"})
public interface EmployeeViewResponse {
	
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
    
    Long getTotalRecords();
    
    String getDirectRepotee();
    
    Long getDepartmentId();
    
    Long getDesignationId();
    
    Long getDesignationLevelId();
    
    Long getReportingId();


}
