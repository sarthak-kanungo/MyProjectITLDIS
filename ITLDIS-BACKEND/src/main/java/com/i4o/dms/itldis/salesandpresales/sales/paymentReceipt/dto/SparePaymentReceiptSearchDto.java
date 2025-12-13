package com.i4o.dms.itldis.salesandpresales.sales.paymentReceipt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SparePaymentReceiptSearchDto {

    private String receiptNumber;

    private String receiptMode;

    private String customerName;

    private String customerContactNumber;

    private String fromDate;

    private String toDate;

    private Integer page;

    private Integer size;
}
