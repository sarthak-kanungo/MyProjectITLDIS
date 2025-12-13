package com.i4o.dms.itldis.salesandpresales.sales.allotment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","action","allotmentNumber","allotmentDate","status","enquiryNumber","dealerTransferNo","customerName","customerMobileNumber","itemNumber"})
@JsonIgnoreProperties({"recordCount"})
public interface AllotmentSearchResponseDto {
    String getAction();
    String getItemNumber();
    String getCustomerMobileNumber();
    String getAllotmentNumber();
    String getCustomerName();
    String getAllotmentDate();
    Long getId();
    String getStatus(); 
    String getEnquiryNumber();
    Long getRecordCount();
    String getDealerTransferNo();
}
