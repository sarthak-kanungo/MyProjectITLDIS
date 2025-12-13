package com.i4o.dms.itldis.salesandpresales.enquiry.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.itldis.salesandpresales.enquiry.domain.EnquiryCropGrown;
import com.i4o.dms.itldis.salesandpresales.enquiry.domain.EnquiryMachineryDetails;
import com.i4o.dms.itldis.salesandpresales.enquiry.domain.EnquirySoilType;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityreport.dto.ReportViewResponse.MarketingActivityReportImages;

import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

public interface EnquiryDto {

    Long getId();

    String getEnquiryNumber();
    String getEnquiryStatus();

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getTentativePurchaseDate();
    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getNextFollowUpDate();
    String getPurposeOfPurchase();
    String getGenerationActivityType();

    String getRetailConversionActivity();
    String getConversionActivityType();

    String getRemarks();

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getEnquiryDate();

    Date getCurrentFollowUpDate();
    void setCurrentFollowUpDate(Date currentFollowUpDate);
    
    MarketingActivityProposal getConversionActivityNumber();
    MarketingActivityProposal getGenerationActivityNumber();
    interface  MarketingActivityProposal{
        Long getActivityProposalId();
        String getActivityNumber();
    }


    DealerEmployeeMaster getSalesPerson();
    interface DealerEmployeeMaster{
        Long getId();
        @JsonProperty("salesPerson")
        String getFirstName();
        String getLastName();
    }

    String getReferralPersonName();

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getValidationDate();

    String getEnquiryType();
    String getFollowUpType();
    String getSource();
    String getItemDescription();

    String getVariant();

    String getSubModel();

    String getModel();

    String getSeries();

    String getProduct();

    String getExchangeRequired();

    Double getExchangeAmount();

    String getExchangeBrand();

    String getExchangeModel();

    Double getEstimatedExchangePrice();
    
    Character getExchangeReceived();
    
    Integer getExchangeModelYear();

     //String getProspectCode();
     
     String getCompanyName();
     String getTitle();
     String getFirstName();
     String getMiddleName();
     String getLastName();
     String getFatherName();
     Integer getStd();
     String getTelephoneNumber();
     String getWhatsAppNumber();
     String getLanguage();
     String getEmailId();
     String getAadharNo();
     Date getDob();
     String getMobileNumber();
     String getAlternateMobileNumber();
     Date getAnniversaryDate();
     String getGstNumber();
     String getPanNumber();
     String getAddress1();
     String getAddress2();
     String getAddress3();
     Integer getPinCode();
     Long getPinId();
     String getPostOffice();
     String getCity();
     String getTehsil();
     String getDistrict();
     String getState();
     String getCountry();
     String getLandSize();
     String getOccupation();


    List<EnquirySoilType> getEnquirySoilTypes();

    List<EnquiryCropGrown> getEnquiryCropGrows();

    List<EnquiryMachineryDetails> getEnquiryMachineryDetails();

    Long getAssetValue();

    String getCashLoan();

    String getFinancier();

    Date getFinanceLoggedInDate();

    Double getEstimatedFinanceAmount();

    String getFinanceStatus();
    String getItemNo();

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getDisbursedDate();

    String getDisbursedFinanceAmount();

    Double getFinalExchangePrice();
    String getSubsidy();
    Double getSubsidyAmount();
    Double getMarginAmount();
    CustomerMaster getCustomerMaster();
    interface CustomerMaster{
      Long getId();
      String getCustomerCode();
      String getCustomerType();
      String getRecordType();
    }
    /*ProspectMaster getProspectMaster();
    interface  ProspectMaster{
        Long getId();
        String getProspectCode();

    }
*/
    Double getTotalReceived();
    Boolean getSubsidyReceived();
    void setSubsidyReceived(Boolean subsidyReceived);
    
    List<EnquiryAttachmentsImages> getEnquiryAttachmentsImages();
    
    interface EnquiryAttachmentsImages {
        String getFileName();

        Long getId();
    }

    Integer getCountUpdate();
    Integer getMobileCountUpdate();
}