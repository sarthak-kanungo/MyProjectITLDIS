package com.i4o.dms.kubota.spares.invoice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SpareInvoiceViewDto {

    public Map<String,Object> invoiceDetails;

    public List<Map<String,Object>> itemDetails;
    
    public List<Map<String,Object>> labourDetails;
    
    public List<Map<String,Object>> outsideChargeDetails;

    public Map<String,Object> salesOrder;

}
