package com.i4o.dms.itldis.service.machinereinstallation.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"edit", "id", "reInstallationNumber", "reInstallationDate", "machineSeries", "serviceStaffName"})
public interface RiSearchResponse {

    Long getId();

    String getMachineSeries();

    String getServiceStaffName();

    String getReInstallationNumber();

    String getReInstallationDate();

    String getEdit();
    
    @JsonIgnore
    Long getRecordCount();
}
