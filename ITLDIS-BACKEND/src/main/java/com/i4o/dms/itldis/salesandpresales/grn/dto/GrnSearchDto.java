package com.i4o.dms.itldis.salesandpresales.grn.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrnSearchDto {
    private String grnType;

    private String dmsGrnNumber;

    private String grnStatus;

    private String invoiceNumber;

    private String fromDate;

    private String toDate;

    private Long userId;

    private Integer page = 0;

    private Integer size = 10;
    
    private String itemNo;
    
    private String supplierType;
}
