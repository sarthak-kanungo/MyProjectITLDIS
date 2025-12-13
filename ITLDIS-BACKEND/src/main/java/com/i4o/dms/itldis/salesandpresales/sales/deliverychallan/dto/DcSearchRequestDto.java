package com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DcSearchRequestDto {

    private String deliveryChallanNumber;
    private String chassisNumber;
    private String customerName;
    private String customerMobileNumber;
    private String dcFromDate;
    private String dcToDate;
    private String enquiryNumber;
    private String enquiryType;
    private String dcStatus;
    private String product;
    private String series;
    private String model;
    private String subModel;
    private String varient;
    private String itemNumber;
    private String engineNumber;
    private Integer page;
    private Integer size;
    private Long hierId;
    private Long dealerId;

}
