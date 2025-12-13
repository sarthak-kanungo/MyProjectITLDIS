package com.i4o.dms.itldis.warranty.pcr.dto;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"approve","id","dealerName","branch","pcrNo","status","pcrDate","jobCardNo","chassisNo","engineNo","model","jobCardDate","serviceDealerCode","serviceDealerName","serviceDealerState","customerName","mobileNo","village","serviceType","serviceHours","approvalRemark","lastApprovedBy"})
public interface WarrantyPcrResponseDto {

    Long getId();
    String getPcrNo();

    String getStatus();

    String getPcrDate();

    String getJobCardNo();

    String getChassisNo();

    String getEngineNo();

    String getModel();

    String getJobCardDate();

    String getApprove();
    
    String getApprovalRemark();

    @JsonIgnore
    Long getTotalCount();
    
    String getLastApprovedBy();
    
    String getServiceDealerCode();
    
    String getServiceDealerName();
    
    String getServiceDealerState();
    
    String getCustomerName();
    
    String getServiceType();
    
    String getServiceHours();
    
    String getMobileNo();
    
    String getVillage();
    
    String getBranch();
    
    String getDealerName();
    
    BigInteger getClaimAmount();	//Suraj 07-10-2022
    
    BigInteger getApprovedAmount();	//Suraj 07-10-2022
}
