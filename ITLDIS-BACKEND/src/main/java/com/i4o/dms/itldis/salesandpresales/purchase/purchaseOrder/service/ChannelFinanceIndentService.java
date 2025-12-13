package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.service;


import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain.ChannelFinanceIndent;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.ResponseInvoiceDto;

import java.text.ParseException;
import java.util.List;

public interface ChannelFinanceIndentService {
    List<ResponseInvoiceDto> getInvoice(String dealerCode, double indentAmount, String bankname) throws ParseException;

    void saveChannelFinanceIndent(ChannelFinanceIndent channelFinanceIndent) throws ParseException;

}
