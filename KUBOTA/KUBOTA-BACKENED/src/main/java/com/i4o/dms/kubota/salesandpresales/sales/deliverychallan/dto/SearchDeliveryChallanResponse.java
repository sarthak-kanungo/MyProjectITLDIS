package com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "cancelFlag","deliveryChallanNumber", "deliveryChallanDate","deliveryChallantype","dcStatus", "enquiryNumber",
        "enquiryDate", "enquiryType", "dealerName", "dealerCode",
        "dealerState", "dSE_EmployeeCode", "dSEName", "cashLoan",
        "financier", "itemNumber", "itemDescription", "quantity",
        "chassisNumber", "engineNumber", "color", "remark",
        "customerType", "companyName", "customerCode", "customerName",
        "invoiceCustomerName", "mobileNumber", "address1", "address2", "address3",
        "pinCode", "postOffice", "village", "tehsil",
        "district", "city", "state",
        "GSTIN Number", "PAN Number", "Insurance Company Code", "Insurance Company Name", "Insurance Address1",
		"Insurance Address2", "Insurance Address3", "Insurance PinCode", "Insurance Locality",
		"Insurance Tehsil", "Insurance City", "Insurance State", "Insurance Country",
		"Insurance TelephoneNumber", "Insurance Email", "Policy Start Date", "Policy Exipry Date",
        "requestNumber","transferToDealerName",
        "transferToDealerCode","createdDate","kaiInvoiceDate","kaiInvoiceNumber",
        "getPassNumber","gatePassDate"
        
  })
public interface SearchDeliveryChallanResponse {

    Long getId();

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

    String getGstinNumber();

    String getPanNumber();

    String getInsuranceCompanyCode();

    String getInsuranceCompanyName();

    @JsonProperty("insuranceAddress1")
    String getInsAddress1();
    @JsonProperty("insuranceAddress2")
    String getInsAddress2();
    @JsonProperty("insuranceAddress3")
    String getInsAddress3();
    @JsonProperty("insurancePinCode")
    String getInsPinCode();
    @JsonProperty("insuranceLocality")
    String getInsLocality();
    @JsonProperty("insuranceTehsil")
    String getInsTehsil();
    @JsonProperty("insuranceCity")
    String getInsCity();
    @JsonProperty("insuranceState")
    String getInsState();
    @JsonProperty("insuranceCountry")
    String getInsCountry();
    @JsonProperty("insuranceTelephoneNumber")
    String getInsTelephoneNumber();
    @JsonProperty("insuranceEmail")
    String getInsEmail();

    String getPolicyStartDate();

    String getPolicyExipryDate();

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

    @JsonProperty("cancel")
    String getCancelFlag();
    
    String getTransferToDealerName();
    
    String getTransferToDealerCode();

    @JsonIgnore
    String getBankName();	//Suraj-26/12/2022
    
    @JsonIgnore
    String getBankAccountNo();	//Suraj-26/12/2022
    
    @JsonIgnore
    String getIfsCode();	//Suraj-26/12/2022
    
    @JsonIgnore
    String getPanNo();	//Suraj-26/12/2022

    @JsonIgnore
    Long getRecordCount();
}
