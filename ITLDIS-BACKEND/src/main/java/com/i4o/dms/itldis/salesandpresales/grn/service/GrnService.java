package com.i4o.dms.itldis.salesandpresales.grn.service;

import com.i4o.dms.itldis.salesandpresales.grn.dto.AccPacInvoiceDto;

public interface GrnService {
    AccPacInvoiceDto getAccPacInvoice(String invoiceNumber, String grnType);
}
