package com.i4o.dms.itldis.masters.usermanagement.kubotausers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.i4o.dms.itldis.masters.dbentities.user.DesignationHierarchy;

@JsonPropertyOrder({"id","designation","department_name","activeStatus"})
@JsonIgnoreProperties({"totalRecords"})
public interface DesignationSearchResponse {

    Long getId();

    String getDesignation();

    String getDepartment_name();

    //String getRemarks();

    Character getActiveStatus();

    Long getTotalRecords();
}
