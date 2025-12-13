package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketingActivityClaimHeadDtoClass {

    Long id;

    String headName;

    Double approvedAmount;

    Double amount;

    Double actualClaimAmount;

    String headImage;

}
