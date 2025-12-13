package com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "action","deliveryChallanNumber", "deliveryChallanDate","deliveryChallantype","dcStatus", "enquiryNumber",
    "enquiryDate", "enquiryType", "dealerName", "dealerCode",
    "dealerState", "dSE_EmployeeCode", "dSEName", "cashLoan",
    "financier", "itemNumber", "itemDescription", "quantity",
    "chassisNumber", "engineNumber", "color", "remark",
    "customerType", "companyName", "customerCode", "customerName",
    "invoiceCustomerName", "mobileNumber", "address1", "address2", "address3",
    "pinCode", "postOffice", "village", "tehsil",
    "district", "city", "state",
    "requestNumber","transferToDealerName",
    "transferToDealerCode","createdDate","kaiInvoiceDate","kaiInvoiceNumber",
    "getPassNumber","gatePassDate"
    
})

public interface SearchDCResponseDto {
	
	Long getId();

    @JsonProperty("Action")
    String getAction();

    String getDeliveryChallanNumber();

    String getDeliveryChallanDate();

    String getDeliveryChallantype();

    String getEnquiryNumber();

    String getEnquiryDate();

    String getEnquiryType();

    String getdcStatus();
    @JsonProperty("gatePassNumber")
    String getGetPassNumber();

    String getGatePassDate();

    String getRemark();

    String getItemNumber();

    String getItemDescription();

    String getQuantity();

    String getChassisNumber();

    String getEngineNumber();

    String getColor();

    String getCustomerType();

    String getCompanyName();

    String getCustomerCode();

    String getCustomerName();

    String getInvoiceCustomerName();

    String getMobileNumber();

    String getAddress1();

    String getAddress2();

    String getAddress3();

    String getPinCode();

    String getPostOffice();

    String getVillage();

    String getTehsil();

    String getDistrict();

    String getCity();

    String getState();

    String getRequestNumber();

    String getDealerName();
    
    String getDealerCode();
    
    String getDealerState();
    @JsonProperty("DSEName")
    String getdSEName();
    @JsonProperty("DSEEmployeeCode")
    String getdSE_EmployeeCode();
    @JsonProperty("cash/Loan")
    String getCashLoan();
    
    String getFinancier();
    @JsonProperty("KAIInvoiceDate")
    String getkaiInvoiceDate();
    @JsonProperty("KAIInvoiceNumber")
    String getkaiInvoiceNumber();

    String getCreatedDate();

    
    String getTransferToDealerName();
    
    String getTransferToDealerCode();

    @JsonIgnore
    String getBankName();
    
    @JsonIgnore
    String getBankAccountNo();
    
    @JsonIgnore
    String getIfsCode();
    
    @JsonIgnore
    String getPanNo();

    @JsonIgnore
    Long getRecordCount();

}
