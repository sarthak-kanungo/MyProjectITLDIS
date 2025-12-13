package com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","activeStatus","creditLimit","dealerCode","dealerFirmType","dealerName","dealerType","emailId","gstNo","mobileNo",
        "panNo","state","subsidyDealer"})

public interface DealerMasterSearchResponse {
    Long getId();

    String getActiveStatus();

    Double getCreditLimit();

    String getDealerCode();

    String getDealerFirmType();

    String getDealerName();

    String getDealerType();

    String getEmailId();

    String getGstNo();

    String getMobileNo();

    String getPanNo();

    String getState();

    String getSubsidyDealer();


}
