package com.i4o.dms.itldis.masters.usermanagement.kubotausers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","departmentCode","departmentName", "activeStatus"})
@JsonIgnoreProperties({"totalRecords"})
public interface DepartmentSearchResponse {
    Long getId();

    String getDepartmentCode();

    String getDepartmentName();

//    Character getLinkedToDealer();
//
//    Character getDealerCode();

   // String getRemarks();

    Character getActiveStatus();
    
    Long getTotalRecords();
}
