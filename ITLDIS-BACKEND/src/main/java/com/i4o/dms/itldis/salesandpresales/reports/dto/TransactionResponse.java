package com.i4o.dms.itldis.salesandpresales.reports.dto;

public interface TransactionResponse {

    Long getDealerId();

    String getDealerCode();

    String getDealerName();

    Integer getPoCount();

    Integer getGrnCount();

    Integer getAllotmentCount();

    Integer getEnquiryCount();

    Integer getQuotationCount();

    Integer getPaymentReceiptCount();

    Integer getDeliveryChallanCount();

    Integer getSalesInvoiceCount();

    Integer getChannelFinanceIntentCount();

    Integer getProposalCount();

    Integer getReportCount();

    Integer getClaimCount();

    Integer getMachineTransferCount();
    
    Integer getMrcCount();
    
    Integer getPdiCount();
    
    Integer getPdcCount();
    
    Integer getPscCount();
    
    Integer getSparesPO();
    
    Integer getSparesGRN();
    
    Integer getSparesQuotation();
    
    Integer getSparesCustomerOrder();
    
    Integer getSparesPickList();
    
    Integer getSparesInvoice();
    
    Integer getJobCard();
    
    Integer getPCR();
    
    Integer getWarrantyClaim();
    
    Integer getSparesIssue();
    
    Integer getInstallation();
    

}
