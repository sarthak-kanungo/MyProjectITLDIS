package com.i4o.dms.itldis.service.activityproposal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"edit","approve","id","dealerCode","dealerName","dealerState","activityNumber","activityCreationDate","status","activityType","location",
"fromDate","toDate","noOfDays","proposedBudget","maxAllowedBudget","targetedNumbers","remarks","approvedAmount", "lastApprovedBy"})
public interface SearchResponsiveServiceActivityProposal
{

    @JsonProperty("Edit")
    String getEdit();

    @JsonProperty("Approve")
    String getApprove();

    @JsonProperty("id")
    Long getId();

    String getDealerCode();
    String getDealerName();
    String getDealerState();
    
    @JsonProperty("Activity_Proposal_Number")
    String getActivityNumber();

    @JsonProperty("Activity_Creation_Date")
    String getActivityCreationDate();

    @JsonProperty("Status")
    String getStatus();

    @JsonProperty("Activity_Type")
    String getActivityType();

    @JsonProperty("Location")
    String getLocation();

    @JsonProperty("From_Date")
    String getFromDate();

    @JsonProperty("To_Date")
    String getToDate();

    @JsonProperty("No_Of_Days")
    Integer getNoOfDays();

    @JsonProperty("Proposed_Budget")
    Double getProposedBudget();

    @JsonProperty("Max_Allowed_Budget")
    Double getMaxAllowedBudget();

    @JsonProperty("Targeted_Numbers")
    Integer getTargetedNumbers();

    @JsonProperty("Remarks")
    String getRemarks();

    @JsonProperty("Approved_Amount")
    Double getApprovedAmount();

    @JsonIgnore
    Long getRecordCount();

    String getLastApprovedBy();
}