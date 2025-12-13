package com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"edit","id", "purchaseOrderNumber", "orderType", "purchaseOrderDate", "purchaseOrderStatus",
		"dealerCode","dealerName",
        "supplierType","supplierName", "transferMode", "transporter", "freightBorneBy",
        "totalBaseAmount", "totalPoAmount"})
public interface ResponseSearchPurchaseOrder {

    String getId();

    String getPurchaseOrderNumber();

    String getOrderType();

    String getPurchaseOrderDate();

    String getSupplierType();
    
    String getSupplierName();

    String getTransferMode();

    String getTransporter();

    String getFreightBorneBy();

    Double getTotalBaseAmount();

    Double getTotalPoAmount();

    String getPurchaseOrderStatus();

    String getEdit();

    @JsonIgnore
    Long getRecordCount();
    
    String getDealerCode();
    String getDealerName();
    
    @JsonIgnore
    String getItemNo();
    @JsonIgnore
    String getItemDesc();
    @JsonIgnore
    Integer getQuantity();
    @JsonIgnore
    BigDecimal getNdp();
    @JsonIgnore
    BigDecimal getGst();
    @JsonIgnore
    BigDecimal getTotalAmount();
}
