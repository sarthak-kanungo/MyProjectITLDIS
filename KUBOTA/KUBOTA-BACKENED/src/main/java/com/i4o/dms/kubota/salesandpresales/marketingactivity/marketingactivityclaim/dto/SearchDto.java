package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"edit","action","id","claimNumber","claimStatus",
        "claimDate","activityNumber","activityCreationDate","activityType",
        "numberOfDays","fromDate","toDate","actualFromDate","actualToDate","location",
        "totalEnquiries","hotEnquiries","warmEnquiries","coldEnquiries","costPerEnquiry",
        "costPerHotEnquiry","totalBookings","costPerBooking","totalRetails","costPerRetail",
        "activityEffectiveness","approvedAmount","actualClaimAmount",
        "rcnNumber",
        "rcnDate",
		"lastApprovedBy"})
public interface SearchDto {


    @JsonProperty("id")
    Long getId();

    String getClaimNumber();

    String getClaimStatus();

    String getClaimDate();

    String getActivityNumber();

    String getActivityCreationDate();

    String getActivityType();

    Integer getNumberOfDays();

    String getFromDate();

    String getToDate();

    String getActualFromDate();

    String getActualToDate();

    String getLocation();

    Integer getHotEnquiries();

    Integer getWarmEnquiries();

    Integer getColdEnquiries();

    Double getCostPerEnquiry();

    Double getCostPerHotEnquiry();

    Integer getTotalBookings();

    Double getCostPerBooking();

    Integer getTotalRetails();

    Double getCostPerRetail();

    String getActivityEffectiveness();

    Double getApprovedAmount();

    Double getActualClaimAmount();
    
    String getEdit(); //added by Mahesh.Kumar on 18-01-2024

    String getAction();
    
    String getLastApprovedBy();
    
    @JsonIgnore
    Long getRecordCount();

    String getRcnNumber();
    String getRcnDate();
}

