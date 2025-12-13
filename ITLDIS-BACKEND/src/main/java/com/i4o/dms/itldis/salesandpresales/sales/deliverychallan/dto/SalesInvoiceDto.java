package com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

import com.i4o.dms.itldis.salesandpresales.sales.invoice.domain.SalesInvoiceCancelRequest;

@Getter
@Setter
public class SalesInvoiceDto {
    private Map<String, Object> invoice;
    private List<Map<String, Object>> deliveryChallanDetails;
    private List<Map<String, Object>> materialDetails;
    private Map<String, Object> customerDetails;
    private Map<String, Object> insuranceCompanyDetails;
    private List<Map<String,Object>> approvalHier;
    private SalesInvoiceCancelRequest invoiceCancelRequest; 
}
