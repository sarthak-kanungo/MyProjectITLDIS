package com.i4o.dms.itldis.salesandpresales.sales.allotment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllotmentSearchDto {
    private String allotmentNumber;
    private String enquiryNumber;
    private String fromDate;
    private String toDate;
    private String product;
    private String series;
    private String model;
    private String subModel;
    private String variant;
    private String itemNo;
    private String chassisNo;
    private String engineNo;
    private Long userId;
    private Integer page;
    private Integer size;
    private Long orgHierId;

}
