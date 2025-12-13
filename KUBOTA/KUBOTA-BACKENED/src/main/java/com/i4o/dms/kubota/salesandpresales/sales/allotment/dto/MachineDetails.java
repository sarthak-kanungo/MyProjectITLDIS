package com.i4o.dms.kubota.salesandpresales.sales.allotment.dto;

public interface MachineDetails {
    Long getId();
    String getChassisNo();
    String getEngineNo();
    String getColor();
    String getGrnNo();
    String getItemNo();
    String getInvoiceNo();
    String getStockType();
    String getInvoiceDate();
    Integer getAgeInDays();
    String getProductGroup();
}
