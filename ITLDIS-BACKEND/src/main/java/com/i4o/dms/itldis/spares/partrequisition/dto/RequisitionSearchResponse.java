package com.i4o.dms.itldis.spares.partrequisition.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","edit","requisitionNo","requisitionPurpose","requestedBy","requisitionDate","jobCardNo","jobCardDate"})

public interface RequisitionSearchResponse {
    Long getId();
    String getRequisitionNo();
    String getRequisitionPurpose();
    String getRequestedBy();
    String getRequisitionDate();
    String getJobCardNo();
    String getJobCardDate();
    String getEdit();
    @JsonIgnore
    Long getTotalCount();
}
