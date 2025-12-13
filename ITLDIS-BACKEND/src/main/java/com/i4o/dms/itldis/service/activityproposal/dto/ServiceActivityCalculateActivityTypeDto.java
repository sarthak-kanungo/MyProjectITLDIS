package com.i4o.dms.itldis.service.activityproposal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceActivityCalculateActivityTypeDto
{

    private Double proposedBudget=0.0;

    private Integer targetedNumbers=0;

    private Long activityTypeId;

    private Double totalSubActivity=0.0;


}
