package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoApprovalMachineDetails {
    private Long id;
    private Double discountPercentage;
    private Double discountAmount;
    private String discountType;
    private String accpacLocationCode;
    private Double amount;
}
