package com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","kaiInvoiceUpload","invoice","wcrNo","wcrDate","wcrType","status","dealerCode","dealerName",
	"workshopAddress","dealerMobileNo","jobCardNo","jobCardDate","goodwillNo","goodwillDate","pcrNo","pcrDate","installationDate",
	"dateOfFailure","dateOfRepair","customerName","customerAddress","customerMobileNo","model","chassisNo","engineNo","soldToDealer",
	"hour","failureType","rcnNumber","rcnDate","receivedOn","claimAmount","approvedAmount","lastApprovedBy", "finalStatus"})
public interface WarrantyWcrResponse {

	//@JsonIgnore
	Long getId();
	String getInvoice();
	String getAction();
    String getWcrNo();
    String getWcrDate();
    String getWcrType();
    String getStatus();
    String getDealerCode();
    String getDealerName();
    String getWorkshopAddress();
    String getDealerMobileNo();
    String getJobCardNo();
    String getPcrNo();
    String getGoodwillNo();
    String getGoodwillDate();
    String getPcrDate();
    String getInstallationDate();
    String getDateOfFailure();
    String getDateOfRepair();
    String getCustomerName();
    String getCustomerAddress();
    String getCustomerMobileNo();
    String getModel();
    String getChassisNo();
    String getEngineNo();
    String getSoldToDealer();
    String getHour();
    String getFailureType();
    @JsonIgnore
    Long getTotalCount();
    String getLastApprovedBy();
    String getRcnNumber();
    String getRcnDate();

    String getKaiInvoiceUpload();
    
    String getFinalStatus();	//Suraj--27-09-2022
    
    String getReceivedOn();	//Suraj--25-10-2023
    
    Float getClaimAmount();	//Suraj--20-11-2023
    Float getApprovedAmount();	//Suraj--20-11-2023
}
