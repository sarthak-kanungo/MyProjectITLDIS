package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchInvoicesDto {
    private String dealerCode;
    private double indentAmount;
    private String bankName;
}
