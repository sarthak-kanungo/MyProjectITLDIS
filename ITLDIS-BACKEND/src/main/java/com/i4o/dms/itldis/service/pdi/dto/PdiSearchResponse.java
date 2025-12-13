package com.i4o.dms.itldis.service.pdi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","id", "chassisNumber", "pdiNumber", "pdiDate","pdiStatus", "engineNumber", "kaiInvoiceNumber", "machineModel", "dmsGrnNumber", "dmsGrnDate"})
@JsonIgnoreProperties({"recordCount"})
public interface PdiSearchResponse {

    Long getId();

    String getAction();

    @JsonProperty("Chassis_Number")
    String getChassisNumber();

    @JsonProperty("PDI_Number")
    String getPdiNumber();

    @JsonProperty("PDI_Date")
    String getPdiDate();

    @JsonProperty("Engine_Number")
    String getEngineNumber();

    @JsonProperty("KAI_Invoice_Number")
    String getKaiInvoiceNumber();

    @JsonProperty("Machine_Model")
    String getMachineModel();

    @JsonProperty("DMS_GRN_Number")
    String getDmsGrnNumber();

    @JsonProperty("DMS_GRN_Date")
    String getDmsGrnDate();
    
    @JsonProperty("PDI_Status")
    String getPdiStatus();
    Long getRecordCount();
}
