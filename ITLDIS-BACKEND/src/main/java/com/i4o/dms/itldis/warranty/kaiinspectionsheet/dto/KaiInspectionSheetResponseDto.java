package com.i4o.dms.itldis.warranty.kaiinspectionsheet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","inspectionNo","inspectedDate","inspectedBy","wcrNo","wcrDate","wcrType","wcrStatus"})
public interface KaiInspectionSheetResponseDto {

    String getInspectionNo();
    String getInspectedDate();
    String getInspectedBy();
    String getWcrNo();
    String getWcrDate();
    String getWcrType();
    String getWcrStatus();
    String getAction();
    @JsonIgnore
    Long getTotalCount();
}
