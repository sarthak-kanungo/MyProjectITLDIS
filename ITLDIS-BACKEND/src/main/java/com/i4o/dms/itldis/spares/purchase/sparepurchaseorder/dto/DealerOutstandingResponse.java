package com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.dto;


public interface DealerOutstandingResponse {

    String getDealerCode();

    Double getCreditLimit();

    Double getCurrentOutStanding();

    Double getOrderUnderProcess();

    Double getOverDuesOutStanding();

    Double getPaymentUnderProcess();

    Double getAvailableLimit();

    Double getNetPayableAmount();
    
}
