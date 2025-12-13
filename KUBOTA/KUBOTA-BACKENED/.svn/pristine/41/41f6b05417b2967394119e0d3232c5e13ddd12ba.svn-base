package com.i4o.dms.kubota.service.report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"zone", "area", "areaManger", "territoryManager", "soldDealerCode", "soldDealershipName", "productGroup", "product", "model",
	"subModel", "chassisNo", "engineNo",
	"deliveryChallanDate","deliveryInstallationDueDate","deliveryInstallationDate","deliveryInstallationNumber","deliveryInstallationStatus",
	"fieldInstallationDueDate","fieldInstallationDate","fieldInstallationNumber","fieldInstallationStatus",
	"customerName", "mobileNumber", "alternateNo", "fullCustAddress"})
public interface InstallationMonitoringBoardResponseDto {
	    Long getId();
		@JsonProperty("Zone")
		String getZone();
		@JsonProperty("Area / State")
		String getArea();	
		@JsonProperty("RSM/ASM Name")
		String getAreaManger();
		@JsonProperty("TM Name")
		String getTerritoryManager();
		@JsonProperty("Sold Dealer Code")
		String getSoldDealerCode();
		@JsonProperty("Sold Dealership Name")
		String getSoldDealershipName();
		@JsonProperty("Product Group")	
		String getProductGroup();
		@JsonProperty("Product")
		String getProduct();
		@JsonProperty("Model")
		String getModel();
		@JsonProperty("Sub Model")
		String getSubModel();
		@JsonProperty("Chassis No")
		String getChassisNo();
		@JsonProperty("Engine No")
		String getEngineNo();
		@JsonProperty("Customer Name")
		String getCustomerName();
		@JsonProperty("Mobile Number")
		String getMobileNumber();
		@JsonProperty("Alternate Mobile No")
		String getAlternateNo();
		@JsonProperty("Customer Full Address")
		String getFullCustAddress();
		

		@JsonProperty("Delivery Challan Date")
		String getDeliveryChallanDate();
		@JsonProperty("Delivery Installation Due Date")
		String getDeliveryInstallationDueDate();
		@JsonProperty("Delivery Installation Date")
		String getDeliveryInstallationDate();
		@JsonProperty("Delivery Installation Number")
		String getDeliveryInstallationNumber();
		@JsonProperty("Delivery Installation Status")
		String getDeliveryInstallationStatus();
		@JsonProperty("Field Installation Due Date")
		String getFieldInstallationDueDate();
		@JsonProperty("Field Installation Done Date")
		String getFieldInstallationDate();
		@JsonProperty("Field Installation Number")
		String getFieldInstallationNumber();
		@JsonProperty("Field Installation Status")
		String getFieldInstallationStatus();
		
		@JsonIgnore
		Long getRecordsCount();
}
