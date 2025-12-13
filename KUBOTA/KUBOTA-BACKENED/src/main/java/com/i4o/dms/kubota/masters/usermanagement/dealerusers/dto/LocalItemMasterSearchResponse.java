package com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","dmsItemNumber","venderItemNumber","itemDesc","vendorDealerCode","hsCode","igst","cgst","sgst","purchasePrice"})

public interface LocalItemMasterSearchResponse {

    Long getId();

    String getDmsItemNumber();

    String getVenderItemNumber();

    String getItemDesc();

    String getVendorDealerCode();

    String getHsCode();

    Double getIgst();

    Double getCgst();

    Double getSgst();

    Float getPurchasePrice();

}
