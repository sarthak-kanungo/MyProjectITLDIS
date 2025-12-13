package com.i4o.dms.kubota.masters.dealermaster.customermaster.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public interface ProspectMasterDto {

     Long   getId();
     String getProspectCode();
     String getProspectType();
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
     Integer getPinCode();
     String getPostOffice();
     String getGstNumber();
     String getPanNumber();
     String getLanguage();
     String getCity();
     String getTehsil();
     String getDistrict();
     String getState();
     String getCountry();
     String getLandSize();
     @JsonFormat(pattern = "dd-MM-yyyy")
     Date getDob();
     @JsonFormat(pattern = "dd-MM-yyyy")
     Date getAnniversaryDate();
     String gstNumber();

     String panNumber();
     String getOccupation();
     String getOther() ;
     List<ProspectSoilType> getProspectSoilTypes();
     interface  ProspectSoilType{
          Long getId();
          String getSoilName();
     }
     List<ProspectCropGrown> getProspectCropNames();
     interface ProspectCropGrown{
          Long getId();
          String getCropName();
     }
     List<ProspectMachineryDetail> getProspectCurrentMachineryDetail();
     interface  ProspectMachineryDetail{
          Long getId();
          String getBrand();
          String getModel();
          String getYearOfPurchase();
     }

}
