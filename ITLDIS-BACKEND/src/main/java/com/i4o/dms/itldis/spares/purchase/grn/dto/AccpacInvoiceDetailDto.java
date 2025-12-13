package com.i4o.dms.itldis.spares.purchase.grn.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class AccpacInvoiceDetailDto {
    Map<String,Object> accpacInvoice;
    List<Map<String,Object>> accpacInvoiceItems;

}
