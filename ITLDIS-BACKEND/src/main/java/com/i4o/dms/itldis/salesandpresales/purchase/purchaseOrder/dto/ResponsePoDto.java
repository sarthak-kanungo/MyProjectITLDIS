package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.JoinColumn;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"approve", "edit", "poNumber","lastApprovedBy", "poDate", "poType","dealerCode","dealerName", "dealerState", "depot", "totalQuantity", "poStatus", "totalCreditLimit", "availableLimit"})
@JsonIgnoreProperties({"totalCount"})
public interface ResponsePoDto {
    Long getId();

    @JsonProperty("P_O_Type")
    String getPoType();

    String getDepot();

    @JsonProperty("P_O_Number")
    String getPoNumber();

    @JsonProperty("P_O_Date")
    String getPoDate();


    Integer getTotalQuantity();

    @JsonProperty("P_O_Status")
    String getPoStatus();

    Double getTotalCreditLimit();

    Double getAvailableLimit();

    //@JsonProperty("Approve")
    String getApprove();

    // @JsonProperty("Edit")
    String getEdit();
    String getLastApprovedBy();
    
    Long getTotalCount();
    String getDealerCode();
    String getDealerName();
    
    String getDealerState(); 		//Suraj-09/11/2022

}
