package com.i4o.dms.itldis.salesandpresales.sales.invoice.service;

import com.i4o.dms.itldis.salesandpresales.sales.invoice.dto.CancelInvoiceDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SalesInvoiceService {
    String cancelInvoice(CancelInvoiceDto invoiceDto);
}
