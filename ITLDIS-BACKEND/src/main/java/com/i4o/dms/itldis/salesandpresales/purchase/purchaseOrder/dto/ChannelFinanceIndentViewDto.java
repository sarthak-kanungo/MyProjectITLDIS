package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public interface ChannelFinanceIndentViewDto {

    Long getChannelFinanceId();
    String getBankName();
    String getIndentNumber();
    int getNumberOfDays();
    double getLimit();
    double getUtilized();
    double getAvailable();
    double getIndentAmount();
    String getDealerCode();
    String getFlexiLoanAccountNumber();
    String getDealerCategory();

    List<ChannelFinanceIntentDetail> channelFinanceIntentDetail();
    interface ChannelFinanceIntentDetail{

        @JsonFormat(pattern = "yyyy-MM-dd")
        Date getInvoiceDate();

        double getInvoiceAmount();
        String getAgeing();
        String getStatus();
        double getChannelFinanceAmount();
        String getInvoiceNumber();
    }
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getIndentDate();

    DealerEmployeeMaster dealerEmployeeMaster();
    interface DealerEmployeeMaster {
        Long getId();
    }

}
