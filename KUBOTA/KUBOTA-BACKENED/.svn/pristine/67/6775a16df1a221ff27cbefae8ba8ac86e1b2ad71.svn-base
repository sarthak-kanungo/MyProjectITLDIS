package com.i4o.dms.kubota.spares.partrequisition.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","returnNo","returnDate","reasonForReturn","requisitionNo","requisitionDate","requisitionPurpose","jobCardNo","jobCardDate","partsReceivedBy"})

public interface ReturnSearchResponse {
    Long getId();
    String getReturnNo();
    String getReturnDate();
    String getReasonForReturn();
    String getRequisitionNo();
    String getRequisitionDate();
    String getRequisitionPurpose();
    String getJobCardNo();
    String getJobCardDate();
    String getPartsReceivedBy();
    @JsonIgnore
    Long getTotalCount();
}