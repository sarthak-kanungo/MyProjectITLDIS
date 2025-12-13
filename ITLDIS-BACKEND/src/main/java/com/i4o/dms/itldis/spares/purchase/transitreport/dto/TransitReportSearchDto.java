package com.i4o.dms.itldis.spares.purchase.transitreport.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransitReportSearchDto {
    private String grnStatus;
    private String fromInvoiceDate;
    private String toInvoiceDate;
    private Integer page;
    private Integer size;

}
