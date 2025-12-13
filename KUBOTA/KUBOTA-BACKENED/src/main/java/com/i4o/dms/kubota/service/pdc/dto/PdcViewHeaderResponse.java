package com.i4o.dms.kubota.service.pdc.dto;

public interface PdcViewHeaderResponse {

    Long getPdcId();
    String getChassisNo();
    Long getChassisId();
    String getRemarks();
    String getPdcNo();
    String getPdcDate();
    String getEngineNo();
    String getModel();
    String getName();
    Boolean getOkFlag();


}
