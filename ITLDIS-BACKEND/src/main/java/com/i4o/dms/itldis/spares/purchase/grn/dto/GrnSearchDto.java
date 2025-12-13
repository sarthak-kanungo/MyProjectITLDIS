package com.i4o.dms.itldis.spares.purchase.grn.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GrnSearchDto {
    private String grnType;

//    private String spareGrnNumber;
    private String dmsGrnNumber;

    private String grnStatus;

    private String invoiceNumber;

    private String supplierType;

//    private Long supplierId;
    private Long supplierName;

    private String fromDate;

    private String toDate;

    private String result="All";



    private Integer page = 0;

    private Integer size = 10;
}
