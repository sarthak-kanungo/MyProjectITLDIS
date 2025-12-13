package com.i4o.dms.itldis.salesandpresales.sales.invoice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchInvoiceDto {
    private String invoiceNumber;
    private String chassisNo;
    private String customerName;
    private String mobileNo;
    private String fromDate;
    private String toDate;
    private String enquiryNumber;
    private String enquiryType;
    private String invoiceStatus;
    private String invoiceType;
    private String product;
    private String model;
    private String series;
    private String subModel;
    private String variant;
    private String itemNo;
    private String engineNo;
    private Integer page = 0;
    private Integer size = 10;
}
