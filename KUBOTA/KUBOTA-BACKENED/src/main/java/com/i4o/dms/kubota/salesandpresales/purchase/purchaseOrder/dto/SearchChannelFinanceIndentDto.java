package com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchChannelFinanceIndentDto {

    private String indentNumber=null;
    private String dealerCode=null;
    private String dealerCategory=null;
    private String bank=null;
    private String fromDate=null;
    private String toDate=null;
    private Integer page=0;
    private Integer size=0;
    private Long dealerId;
    private Long orgHierId;
    private String status;
}
