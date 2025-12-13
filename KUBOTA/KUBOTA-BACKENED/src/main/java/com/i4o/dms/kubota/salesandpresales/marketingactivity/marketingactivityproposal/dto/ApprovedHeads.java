package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class ApprovedHeads
{
    //@Min(value = 1, message = "The value must be positive")
    private Double approvedAmount = 0.0;

    //@Min(value = 1, message = "The value must be positive")
    private Double actualClaimAmount = 0.0;

    private Long id;
}
