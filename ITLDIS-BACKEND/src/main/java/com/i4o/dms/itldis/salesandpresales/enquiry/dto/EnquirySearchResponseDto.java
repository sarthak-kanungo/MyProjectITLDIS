package com.i4o.dms.itldis.salesandpresales.enquiry.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","enquiryStatus","enquiryType","enquiryNumber","enquiryDate","salesPerson",
	"appEnquiryFlag","validationDate",
	"source","currentFollowUpDate","nextFollowUpDate","tentativePurchaseDate","prospectCode","prospectType","companyName","firstName","middleName","lastName","fatherName","mobileNumber","telephoneNumber","whatsAppNumber","emailId","address1","address2","address3","pinCode","postOffice","tehsil","district","city","state","country",
	"itemNumber","itemDescription","product","model","subModel","variant",
	"cashLoan","financier","subsidy","exchangeRequired","generationActivityNumber","conversionActivityNumber","remarks","assetValue", "autoClose"})
@JsonIgnoreProperties({"totalRecords"})
public interface EnquirySearchResponseDto {
    Long getId();
    String getEnquiryNumber();
    String getEnquiryDate();
    String getSalesPerson();
    //String getReferralPersonName();
    String getCurrentFollowUpDate();
    String getEnquiryStatus();
    String getEnquiryType();
    //String getFollowUpType();
    String getSource();
    //String getPurposeOfPurchase();
    //String getGenerationActivityType();
    String getGenerationActivityNumber();
    //String getRetailConversionActivity();
    //String getConversionActivityType();
    String getConversionActivityNumber();
    String getTentativePurchaseDate();
    String getNextFollowUpDate();
    String getRemarks();
    String getItemNumber();
    String getItemDescription();
    String getVariant();
    String getSubModel();
    String getModel();
    //String getSeries();
    String getProduct();
    String getExchangeRequired();
    //String getExchangeBrand();
    //String getExchangeModel();
    //String getEstimatedExchangePrice();
    String getProspectCode();
    String getProspectType();
    String getCompanyName();
    String getFirstName();
    String getMiddleName();
    String getLastName();
    String getFatherName();
    String getMobileNumber();
    String getTelephoneNumber();
    String getWhatsAppNumber();
    String getEmailId();
    String getAddress1();
    String getAddress2();
    String getAddress3();
    String getPinCode();
    String getPostOffice();
    String getCity();
    String getTehsil();
    String getDistrict();
    String getState();
    String getCountry();
    //String getLanguage();
    //String getDob();
    //String getAnniversaryDate();
    //String getGstNumber();
    //String getPanNumber();
    //String getOccupation();
    //Long getLandSize();
    //String getSoilType();
    //String getCropGrown();
    String getAssetValue();
    String getCashLoan();
    String getFinancier();
    //String getFinanceLoggedDate();
    //String getFinanceStatus();
    //String getDisbursedDate();
    //String getDisbursedFinanceAmount();
    //String getFinanceExchangePrice();
    String getSubsidy();
    //String getSubsidyAmount();
    //String getMarginAmount();
    Boolean getAppEnquiryFlag();
    String getValidationDate();
    String getAction();
    Long getTotalRecords();
    String getAutoClose();
//    Integer getCountUpdate();
//    Integer getMobileCountUpdate();
    String getDealerCode(); 	//Suraj--31/10/2022
    String getDealerName(); 	//Suraj--31/10/2022

}