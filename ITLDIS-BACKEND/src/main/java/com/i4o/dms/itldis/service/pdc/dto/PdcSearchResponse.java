package com.i4o.dms.itldis.service.pdc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"edit","pdcNo","pdcDate","chassisNo","engineNo", "model", "name","id"})
@JsonIgnoreProperties({"recordCount"})
public interface PdcSearchResponse {

    Long getId();
    String getChassisNo();
    String getPdcNo();
    String getPdcDate();
    String getEngineNo();
    String getModel();
    String getName();
    String getEdit();
    Long getRecordCount();
}
