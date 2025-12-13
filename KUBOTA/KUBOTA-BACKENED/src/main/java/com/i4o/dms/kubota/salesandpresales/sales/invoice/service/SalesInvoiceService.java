package com.i4o.dms.kubota.salesandpresales.sales.invoice.service;

import com.i4o.dms.kubota.salesandpresales.sales.invoice.dto.CancelInvoiceDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SalesInvoiceService {
    String cancelInvoice(CancelInvoiceDto invoiceDto);
}
