package com.i4o.dms.kubota.salesandpresales.sales.paymentReceipt.dto;

import com.i4o.dms.kubota.salesandpresales.enquiry.domain.Enquiry;

import javax.persistence.*;
import java.util.Date;

public interface PaymentReceiptDto {

    Long getId();
    String getReceiptNo();
    String getReceiptType();
    Enquiry getEnquiry();
    interface Enquiry{
        Long getId();
       String getEnquiryNumber();
       Date getEnquiryDate();
       String getFirstName();
       String getMobileNumber();
       String getVariant();
       String getAddress1();
       String getPinCode();
       String getPostOffice();
       String getCity();
       String getTehsil();
       String getDistrict();
       String getState();
       String getRemarks();

    }
    Date getReceiptDate();
    String getReceiptMode();
    String getChequeDdNo();
    Date getChequeDdDate();
    Double getReceiptAmount();
    Double getCustomerBalance();
    String getChequeDdBank();
    String getTransactionNo();
    Date getTransactionDate();
    String getCardNo();
    String getCardType();
    String getCardName();
    String getServiceProvides();
    String getRemarks();
    String getStatus();

}
