package com.i4o.dms.kubota.spares.quotation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "edit","id", "quotationNumber", "quotationDate", "customerType", "customerName", "mobileNumber",
        "customerAddress1", "customerAddress2", "country", "state", "district", "tehsil", "city", "discountType", "discountRate", "totalDiscountValue", "totalBasicValue"})

@JsonIgnoreProperties({"recordCount"})
public interface SpareQuotationSearchResponse {
    Long getId();

    @JsonProperty("Quotation_Number")
    String getQuotationNumber();

    @JsonProperty("Quotation_Date")
    String getQuotationDate();

    @JsonProperty("Customer_Type")
    String getCustomerType();

    @JsonProperty("Customer_Name")
    String getCustomerName();

    @JsonProperty("Mobile_Number")
    String getMobileNumber();

    @JsonProperty("Customer_Address1")
    String getCustomerAddress1();

    @JsonProperty("Customer_Address2")
    String getCustomerAddress2();

    @JsonProperty("Country")
    String getCountry();

    @JsonProperty("State")
    String getState();

    @JsonProperty("District")
    String getDistrict();

    @JsonProperty("Tehsil")
    String getTehsil();

    @JsonProperty("City")
    String getCity();

    @JsonProperty("Discount_Type")
    String getDiscountType();

    @JsonProperty("Discount_Rate")
    String getDiscountRate();

    @JsonProperty("Discount_Value")
    String getTotalDiscountValue();

    @JsonProperty("Total_Basic_Value")
    String getTotalBasicValue();

    @JsonProperty("Edit")
    String getEdit();
    
    Long getRecordCount();
}
