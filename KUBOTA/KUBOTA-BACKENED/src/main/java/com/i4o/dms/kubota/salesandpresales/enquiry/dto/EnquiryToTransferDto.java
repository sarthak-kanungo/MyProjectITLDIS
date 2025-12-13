package com.i4o.dms.kubota.salesandpresales.enquiry.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class EnquiryToTransferDto {
    private Long userId;
    private String  salesPerson;
    private String  enquiryNumber;
    private String taluka;
    private String enquiryType;
    private String autoClose;

}
