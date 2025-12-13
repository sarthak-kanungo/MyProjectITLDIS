package com.i4o.dms.kubota.service.activityreport.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonPropertyOrder({"id","dealerCode","dealerName","dealerState","activityNumber","activityCreatedDate","activityType","status","fromDate","toDate","targetedNumber",
        "activityEffectivness","remarks","location"})
public interface ActivitySearchResponse {

    Long getId();

    String getActivityNumber();

    String getActivityCreatedDate();

    String getActivityType();

    String getStatus();

    String getFromDate();

    String getToDate();

    String getTargetedNumber();

    String getActivityEffectivness();

    String getRemarks();

    String getLocation();


    String getDealerCode();
    String getDealerName();
    String getDealerState();
    
    @JsonIgnore
    Long getRecordCount();
}
