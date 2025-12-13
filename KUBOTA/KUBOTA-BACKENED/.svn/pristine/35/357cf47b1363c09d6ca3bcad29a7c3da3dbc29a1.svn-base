package com.i4o.dms.kubota.service.machineinstallation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"edit","id","installationNumber","installationDate","dealerName","dealerCode","chassisNo","installationType","engineNo","model","serviceStaffName",
        "representativeType","customerRepName","customerName","csbNo"})
@JsonIgnoreProperties({"recordCount"})
public interface DiSearchResponse {

    Long getId();

    String getInstallationType();

    String getChassisNo();

    String getInstallationNumber();

    String getInstallationDate();

    String getEngineNo();

    String getModel();

    String getServiceStaffName();

    String getRepresentativeType();

    String getCustomerRepName();

    String getCustomerName();

    String getCsbNo();

    String getEdit();

    Long getRecordCount();
    
    String getDealerName();		//Suraj--02/11/20
    
    String getDealerCode();		//Suraj--02/11/20
}
