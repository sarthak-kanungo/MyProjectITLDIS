package com.i4o.dms.itldis.salesandpresales.marketingactivity.kaiactivitytypebudgetmaster.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityBudgetMasterSearchDto {

    private  String activityType;

    private  String budgetType;

    private Double maximumLimit;

    private Double kaiShare;

    private Integer maximumDayMonth;

    private Integer page;

    private Integer size;



}
