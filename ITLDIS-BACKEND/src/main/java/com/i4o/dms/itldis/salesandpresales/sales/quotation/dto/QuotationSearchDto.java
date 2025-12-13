package com.i4o.dms.itldis.salesandpresales.sales.quotation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuotationSearchDto {
    String quotationNumber;
    String source;
    String enquiryStatus;
    String salesPerson;
    String product;
    String series;
    String model;
    String subModel;
    String variant;
    String itemNo;
    String fromDate;
    String toDate;
    Integer page;
    Integer size;
}
