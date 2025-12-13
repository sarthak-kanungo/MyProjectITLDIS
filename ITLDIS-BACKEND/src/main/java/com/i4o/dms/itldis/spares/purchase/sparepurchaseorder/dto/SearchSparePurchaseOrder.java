package com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchSparePurchaseOrder {

   // private String dealerShip;

//    private String branch;

    private String poType;

    private String poStatus;

    private String fromDate;

    private String toDate;

    private String poNumber;

    private String partNumber;
    
    private Long hierId;
    
    private Long dealerId;

    private Integer page = 1;

    private Integer size = 10;
}
