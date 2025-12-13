package com.i4o.dms.kubota.warranty.retrofitmentcampaign.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"retrofitmentNumber","retrofitmentDate","campaignName","startDate","endDate"})
public interface WarrantyRetrofitmentCampaignResponseDto {

    String getRetrofitmentNumber();
    String getRetrofitmentDate();
    String getCampaignName();
    String getStartDate();
    String getEndDate();
    @JsonIgnore
    Long getTotalCount();

}
