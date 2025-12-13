package com.i4o.dms.itldis.service.activityclaim.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","approve", "id","dealerCode","dealerName","dealerState", "claimNumber", "claimDate", "claimStatus", "activityNumber", "createdDate", "activityType", "fromDate",
        "toDate", "actualFromDate", "actualToDate", "noOfDays", "location", "activityEffectiveness", "totalClaimAmount",
        "rcnNumber",
        "rcnDate",        
		"lastApprovedBy"})
public interface ActivityClaimSearchResponse {
    Long getId();

    String getClaimNumber();

    String getClaimDate();

    String getClaimStatus();

    String getActivityNumber();

    String getCreatedDate();

    String getActivityType();

    String getFromDate();

    String getToDate();

    String getActualFromDate();

    String getActualToDate();

    Long getNoOfDays();
    
    String getDealerCode();
    String getDealerName();
    String getDealerState();

    String getLocation();

    String getActivityEffectiveness();

    Double getTotalClaimAmount();

    String getAction();

    String getApprove();
    
    @JsonIgnore
    Long getRecordCount();
    
    String getLastApprovedBy();
    
    String getRcnNumber();
    String getRcnDate();
}
