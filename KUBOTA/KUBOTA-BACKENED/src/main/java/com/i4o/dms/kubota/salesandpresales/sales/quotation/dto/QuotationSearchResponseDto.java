package com.i4o.dms.kubota.salesandpresales.sales.quotation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"quotationCode","quotationDate","enquiryNumber", "salesPerson","enquiryStatus","source","purposeOfPurchase","tentativePurchaseDate","itemNumber","itemDescription","model","subModel","variant","series","product","prospectCode","prospectType","companyName","firstName", "middleName", "lastName","fatherName","whatsAppNumber","alternateMobileNumber","telephoneNumber","emailId","address1","address2","address3","pinCode","postOffice","city","state","country","language","dob","anniversaryDate","gstNumber","panNumber"})
@JsonIgnoreProperties({"totalCount"})
public interface QuotationSearchResponseDto {
    public String getQuotationCode();

    public String getEnquiryNumber();

    @JsonFormat(pattern = "dd-MM-yyyy")
    public String getQuotationDate();

    public String getSalesPerson();

    public String getEnquiryStatus();

    public String getSource();

    public String getPurposeOfPurchase();

    @JsonFormat(pattern = "dd-MM-yyyy")
    public String getTentativePurchaseDate();
    public String getItemNumber();
    public String getItemDescription();

    public String getVariant();

    public String getSubModel();

    public String getModel();

    public String getSeries();

    public String getProduct();

    public String getProspectCode();

    public String getProspectType();

    public String getCompanyName();

    public String getFirstName();

    public String getMiddleName();

    public String getLastName();

    public String getFatherName();

    public String getWhatsAppNumber();

    public String getAlternateMobileNumber();

    public String getTelephoneNumber();

    public String getEmailId();

    public String getAddress1();

    public String getAddress2();

    public String getAddress3();

    public String getPinCode();

    public String getPostOffice();

    public String getCity();

    public String getState();

    public String getCountry();

    public String getLanguage();

    @JsonFormat(pattern = "dd-MM-yyyy")
    public String getDob();

    @JsonFormat(pattern = "dd-MM-yyyy")
    public String getAnniversaryDate();
    public String getGstNumber();

    public String getPanNo();

    public Long getTotalCount();
}
