package com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "approve","deliveryChallanNumber", "deliveryChallanDate", "deliveryChallantype","dealerCode","dealerName", "enquiryNumber",
    "enquiryDate", "enquiryType", "itemNumber", "itemDescription",
    "quantity", "chassisNumber", "engineNumber",
    "customerType", "companyName", "customerCode", "customerName",
    "invoiceCustomerName", "mobileNumber", "address1", "pinCode", "postOffice", "village", "tehsil",
    "district", "city", "state",  "cancelRequestDate"
})
public interface SearchDeliveryChallanCancelApproval {

	 Long getId();

     String getDeliveryChallanNumber();

     String getDeliveryChallanDate();

     String getDeliveryChallantype();

     String getEnquiryNumber();

     String getEnquiryDate();

     String getEnquiryType();

     String getDcStatus();

     String getItemNumber();

     String getItemDescription();

     Integer getQuantity();

     String getChassisNumber();

     String getEngineNumber();

     String getCustomerType();

     String getCompanyName();

     String getCustomerCode();

     String getCustomerName();

     String getInvoiceCustomerName();

     String getMobileNumber();

     String getAddress1();

     String getPinCode();

     String getPostOffice();

     String getVillage();

     String getTehsil();

     String getDistrict();

     String getCity();
     
     String getState();
     
     String getCancelRequestDate();
     
     String getApprove();

     @JsonIgnore
     Long getRecordCount();

     String getDealerCode();
     String getDealerName();
}
