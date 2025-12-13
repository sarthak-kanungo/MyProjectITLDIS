package com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","activityStatus","activityNumber","activityCreationDate","dealerCode","dealerName","activityPurpose","activityType",
        "fromDate","toDate","location","numberOfDays","proposedBudget","maxAllowedBudget","approvedAmount","lastApprovedBy"})
@JsonIgnoreProperties({"recordCount"})
public interface ResponseSearchDto
{
    Long getId();

    String getAction();

    @JsonAlias("Marketing Activity Proposal Number")
    String getActivityNumber();

    @JsonAlias("Marketing Activity Proposal Date")
    String getActivityCreationDate();

    @JsonAlias("Marketing Activity Purpose")
    String getActivityPurpose();

    @JsonAlias("Marketing Activity Type")
    String getActivityType();

    @JsonAlias("Marketing Activity Proposal Status")
    String getActivityStatus();

    String getFromDate();

    String getToDate();

    String getLocation();

    @JsonAlias("No. of Days")
    String getNumberOfDays();

    String getProposedBudget();

    String getMaxAllowedBudget();

    Double getApprovedAmount();

    String getDealerCode();
    
    String getDealerName();
    
    Long getRecordCount();
    
    String getLastApprovedBy();
}
