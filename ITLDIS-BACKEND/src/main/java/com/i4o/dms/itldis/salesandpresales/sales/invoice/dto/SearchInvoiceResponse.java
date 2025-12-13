package com.i4o.dms.itldis.salesandpresales.sales.invoice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","edit","cancel","invoiceNumber","invoiceType","deliveryChallanType","invoiceDate","customerCode","customerName","invoiceStatus",
"hypothecation","customerType","companyName"})
@JsonIgnoreProperties({"recordCount"})
public interface SearchInvoiceResponse {
    Long getId();
    String getInvoiceNumber();
    String getInvoiceType();
    String getInvoiceDate();
    String getInvoiceStatus();
    String getChassisNo();
    String getDeliveryChallanType();
    String getCustomerCode();
    String getCustomerName();
    String getHypothecation();
    String getCustomerType();
    String getCompanyName();
    String getEdit();
    String getCancel();
    Long getRecordCount();
//    String mobileNo;
//    String fromDate;
//    String toDate;
//    String enquiryNumbe
//    String enquiryType;
//    String invoiceStatu
//    String invoiceType;
//    String product;
//    String model;
//    String series;
//    String subModel;
//    String variant;
//    String itemNo;
//    String engineNo;
}
