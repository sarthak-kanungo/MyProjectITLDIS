package com.i4o.dms.itldis.warranty.goodwill.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"approve","dealerCode","status","goodwillNo","pcrNo","jobCardNo","goodwillType","goodwillDate","chassisNo","model","lastApprovedBy"})
public interface GoodwillSearchResponse {
    String getGoodwillNo();
    String getPcrNo();
    String getJobCardNo();
    String getGoodwillDate();
    String getGoodwillType();
    String getStatus();
    String getDealerCode();
    @JsonIgnore
    Long getRecordCount();
    String getApprove();
    String getChassisNo();
    String getModel();
    String getLastApprovedBy();
}
