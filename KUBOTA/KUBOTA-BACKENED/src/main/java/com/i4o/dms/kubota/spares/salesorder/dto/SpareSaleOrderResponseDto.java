package com.i4o.dms.kubota.spares.salesorder.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"edit","picklist","invoice","id", "salesOrderNumber", "salesOrderDate", "quotationNumber", "mobileNumber", "customerName", "customerType"
        , "customerAddress1", "customerAddress2", "totalBasicValue", "totalDiscountValue", "totalSalesOrderAmount", "totalTaxAmount"})
public interface SpareSaleOrderResponseDto {

    Long getId();

    String getSalesOrderNumber();

    String getSalesOrderDate();

    String getQuotationNumber();

    String getMobileNumber();

    String getCustomerName();

    String getCustomerType();

    String getCustomerAddress1();

    String getCustomerAddress2();

    Double getTotalBasicValue();

    Double getTotalDiscountValue();

    Double getTotalSalesOrderAmount();

    Double getTotalTaxAmount();

    String getEdit();
    
    String getPicklist();
    
    String getInvoice();
    
    @JsonIgnore
    Long getRecordCount();

}
