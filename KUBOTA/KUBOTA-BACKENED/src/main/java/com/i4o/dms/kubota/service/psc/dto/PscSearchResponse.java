package com.i4o.dms.kubota.service.psc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"edit","id","chassisNo","pscNo","pscDate","engineNo","machineModel"})
@JsonIgnoreProperties({"recordCount"})
public interface PscSearchResponse {
    Long getId();

    String getPscNo();

    String getChassisNo();

    String getPscDate();

    String getEngineNo();

    String getMachineModel();

    String getEdit();
    
    Long getRecordCount();
}
