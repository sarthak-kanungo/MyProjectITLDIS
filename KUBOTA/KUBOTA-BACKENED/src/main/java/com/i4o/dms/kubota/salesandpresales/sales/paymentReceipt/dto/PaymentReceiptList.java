package com.i4o.dms.kubota.salesandpresales.sales.paymentReceipt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"receiptNumber","receiptType","receiptDate","receiptMode","receiptAmount","enquiryNumber","enquiryDate","enquiryStatus","variant","subModel","customerBalance","firstName","mobileNumber","address1","pinCode","postOffice","city","tehsil","district","state","remarks","transactionNo","transactionDate","bankName","serviceProvides","cardNo","cardType","cardName"})
@JsonIgnoreProperties({"totalCount"})
public interface PaymentReceiptList {
    Long getId();

    String getReceiptNumber();

    String getReceiptType();


    @JsonFormat(pattern = "dd-MM-yyyy")
    String getReceiptDate();

    String getReceiptMode();

    String getReceiptAmount();

    String getEnquiryNumber();

    @JsonFormat(pattern = "dd-MM-yyyy")
    String getEnquiryDate();

    String getEnquiryStatus();

    String getVariant();

    String getSubModel();

    String getCustomerBalance();

    String getCustomerName();

    String getMobileNumber();

    String getAddress1();

    Integer getPinCode();

    String getPostOffice();

    String getCity();

    String getTehsil();

    String getDistrict();

    String getState();

    String getRemarks();

    String getTransactionNo();
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    String getTransactionDate();

    String getBankName();
    
    String getServiceProvides();

    String getCardNo();

    String getCardType();

    String getCardName();

    Long getTotalCount();
}
