package com.i4o.dms.itldis.salesandpresales.sales.invoice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelInvoiceDto {
    private Long invoiceId;
    private String cancellationType;
    private String invoiceCancellationReason;
    /*private String brand;
    private String model;*/
    private String reason;
    private String remarks;
}
