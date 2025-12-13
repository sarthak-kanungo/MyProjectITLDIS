package com.i4o.dms.kubota.warranty.logsheet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","action","logsheetNo","logsheetDate","logsheetType","status","jobCardNo","jobCardDate","installationDate","customerName","address","dateOfFailure","hours","model","registrationNumber","chassisNo","serviceDealer","serviceDealerCity","soldToDealer","crop","cropCondition","failureType","soilType","fieldCondition"})
public interface LogsheetResponseDto {
	String getAction();
	Long getId();
    String getLogsheetNo();
    String getLogsheetDate();
    String getLogsheetType();
    String getStatus();
    String getJobCardNo();
    String getJobCardDate();
    String getInstallationDate();
    String getCustomerName();
    String getAddress();
    String getDateOfFailure();
    String getHours();
    String getModel();
    String getRegistrationNumber();
    String getChassisNo();
    String getServiceDealer();
    String getServiceDealerCity();
    String getSoldToDealer();
    String getCrop();
    String getCropCondition();
    String getFailureType();
    String getSoilType();
    String getFieldCondition();
    @JsonIgnore
    Long getTotalCount();
}
