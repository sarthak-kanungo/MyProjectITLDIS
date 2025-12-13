package com.i4o.dms.itldis.warranty.retrofitmentcampaign.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarrantyRetrofitmentCampaignSearchDto {

    String rfcNo;
    String status;
    String fromDate;
    String toDate;
    Long page;
    Long size;
}
