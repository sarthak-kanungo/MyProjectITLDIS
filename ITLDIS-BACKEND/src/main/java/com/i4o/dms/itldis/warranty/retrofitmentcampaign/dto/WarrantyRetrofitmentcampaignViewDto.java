package com.i4o.dms.itldis.warranty.retrofitmentcampaign.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.domain.WarrantyRetrofitmentCampaignItem;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.domain.WarrantyRetrofitmentCampaignPhoto;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.domain.WarrantyRetrofitmentChassisInfo;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.domain.WarrantyRetrofitmentJcLabourChargeInfo;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface WarrantyRetrofitmentcampaignViewDto {

    Long getId();

    String getRetrofitmentNo();

    String getCampaignName();

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getRetrofitmentDate();

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getStartDate();

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getEndDate();
    
    String getStatus();
    
    String getDataFilePath();

    List<WarrantyRetrofitmentCampaignItem> getWarrantyRetrofitmentCampaignItemList();

    List<WarrantyRetrofitmentCampaignPhoto> getWarrantyRetrofitmentCampaignPhoto();
    
    List<WarrantyRetrofitmentJcLabourChargeInfo> getWarrantyRetrofitmentJcLabourChargeInfoList();
    
    Set<WarrantyRetrofitmentChassisInfo> getWarrantyRetroFitmentChassisInfo();

}
