package com.i4o.dms.itldis.salesandpresales.sales.quotation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.itldis.masters.dealermaster.customermaster.domain.CustomerMaster;

import lombok.Setter;

import java.util.Date;
import java.util.List;

public interface QuotationDto {

    String getQuotationNumber();

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getCreatedDate();
    String getItemDescription();
    String getColor();
    Integer getQty();
    Double getRate();


    Double getGrossDiscount();
    Double getAmountAfterDiscount();
    Double getIgst();
    Double getCgst();
    Double getSgst();
    Double getGstAmount();
    Double getInsurance();
    Double getRto();
    String getItemNo();
    Double getBasicPrice();
    Double setBasicPrice(Double basicPrice);
     Enquiry getEnquiry();
     interface  Enquiry{
        Long getId();
        String getEnquiryNumber();
         @JsonFormat(pattern = "yyyy-MM-dd")
        Date getEnquiryDate();
         @JsonFormat(pattern = "yyyy-MM-dd")
        Date getTentativePurchaseDate();
        String getEnquiryStatus();
        void setSalesPersonName(String data);
        @JsonProperty("salesPerson")
        String getSalesPersonName();
        DealerEmployeeMaster getSalesPerson();
        interface  DealerEmployeeMaster{
	       String getId();
	       String getFirstName();
	       String getLastName();
	    }
        String getSource();
        interface CustomerMaster {
        	 String getCustomerCode();
             String getCustomerType();
        };
        CustomerMaster getCustomerMaster();
   	    //void setProspectCode(String prospectCode);
        //void setProspectType(String prospectType);
        
        String getPurposeOfPurchase();
       
        String getCompanyName();
        String getFirstName();
        String getMiddleName();
        String getLastName();
        String getFatherName();
        String getMobileNumber();
        String getWhatsAppNumber();
        String getAlternateMobileNumber();
        Integer getStd();
        String getTelephoneNumber();
        String getEmailId();
        String getAddress1();
        String getAddress2();
        String getAddress3();
        String getPinCode();
        String getPostOffice();
        String getCity();
        String getState();
        String getCountry();
        String getLanguage();
        String getDob();
        String getPanNumber();
        String getProduct();
        String getSeries();
        String getModel();
        String getVariant();
        String getDistrict();
        String getTehsil();



     }



    List<QuotationImplements> getQuotationImplements();
    interface  QuotationImplements{
        Long getId();
        String getItemNo();
        String getItemDescription();
        String getColor();
        Integer getQty();
        Double getRate();
        Double getGrossDiscount();
        Double getAmountAfterDiscount();
        Double getIGst();
        Double getCGst();
        Double getSGst();
        Double getGstAmount();
        Double getTotalAmount();
        Double getBasicPrice();
        Double setBasicPrice(Double basicPrice);
    }
    Double getBasicValue();
    Double getDiscount();
    Double getTotalGstAmount();
    Double getCharges();
    Double getTotalAmount();

    Double getTotal();





}
