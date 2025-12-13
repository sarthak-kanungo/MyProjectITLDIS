package com.i4o.dms.kubota.salesandpresales.sales.paymentReceipt.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","receiptNumber","receiptType","receiptMode","receiptDate",
"receiptAmount","payeeType","customerName","customerAddress1","customerAddress2","country","state","district","tehsil","village",
"postOffice","pinCode","remark","checkDDNumber","checkDDDate","checkDDBank","cardType","cardNumber","cardName","transactionNumber","transactionDate","totalCount"})
public interface SparePaymentReceiptResposne {

    public Long getId();

    public String getReceiptNumber();

    public String getReceiptType();

    public String getReceiptDate();

    public Double getReceiptAmount();

    public String getPayeeType();
    
    public String getreceiptMode();

    public String getCustomerName();

    public String getCustomerAddress1();

    public String getCustomerAddress2();

    public String getCountry();

    public String getState();

    public String getDistrict();

    public String getTehsil();

    public String getVillage();

    public String getPostOffice();

    public String getPinCode();

    public String getRemark();

    public String getCheckDDNumber();

    public String getCheckDDDate();

    public String getCheckDDBank();

    public String getCardType();

    public String getCardNumber();

    public String getCardName();

    public String getTransactionNumber();

    public String getTransactionDate();
    
    public Long getTotalCount();

}
