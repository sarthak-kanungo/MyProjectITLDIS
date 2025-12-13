package com.i4o.dms.itldis.warranty.kaiinspectionsheet.dto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.itldis.warranty.kaiinspectionsheet.domain.KaiInspectionSheetPhoto;

public interface KaiInspectionSheetView {
	 Long getId();
	 String getInspectionNo();
	 Date getInspectionDate();
     WarrantyMtTypeOfUse getTypeOfUse();
     interface  WarrantyMtTypeOfUse{
          Long getId();
          @JsonProperty("value")
          @Value("#{target.typeOfUse} #{target.code}")
          String getTypeOfUse();
          String getCode();
     }
     WarrantyMtFailureMode getFailureMode();
     interface  WarrantyMtFailureMode{
          Long getId();
          @JsonProperty("value")
          @Value("#{target.failureMode} #{target.code}")
          String getFailureMode();
          String getCode();
     }
     WarrantyMtFailureUnit getFailureUnit();
     interface  WarrantyMtFailureUnit{
          Long getId();
          @JsonProperty("value")
          @Value("#{target.failureUnit} #{target.code}")
          String getFailureUnit();
          String getCode();
     }

      String getSymptom();

      String getDefect();

      String getRemedy();
      @JsonProperty("remark")
      String getClaimFinalRemark();

     List<KaiInspectionSheetPhoto> getKaiInspectionSheetPhoto();



}
