package com.i4o.dms.itldis.salesandpresales.enquiry.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","enquiryStatus","enquiryType","enquiryNumber","enquiryDate","salesPerson","validationDate","source","nextFollowUpDate","tentativePurchaseDate","companyName","firstName","lastName","mobileNumber","city","tehsil","model","cashLoan","subsidy","exchangeRequired"})
@JsonIgnoreProperties({"totalRecords"})
public interface EnquiryPartialSearchResponseDto {

	Long getId();
    String getEnquiryStatus();
    String getEnquiryType();
    String getEnquiryNumber();
    String getEnquiryDate();
    String getSalesPerson();
    String getValidationDate();
    String getSource();
    String getNextFollowUpDate();
    String getTentativePurchaseDate();
    String getCompanyName();
    String getFirstName();
    String getLastName();
    String getMobileNumber();
    String getCity();
    String getTehsil();
    String getModel();
    String getCashLoan();
    String getSubsidy();
    String getExchangeRequired();
    String getAction();
    Long getTotalRecords();
    String getAutoClose();
//    Integer getCountUpdate();
//    Integer getMobileCountUpdate();
}
