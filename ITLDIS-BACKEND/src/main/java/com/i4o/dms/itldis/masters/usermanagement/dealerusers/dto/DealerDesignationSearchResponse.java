package com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","edit","activeStatus","designation","department_name","remarks"})

public interface DealerDesignationSearchResponse {


    Long getId();
    
    public String getEdit();

    String getDesignation();

    String getDepartment_name();

    String getRemarks();

    Character getActiveStatus();
    
    @JsonIgnore
   	public Long getTotalRecords();

}
