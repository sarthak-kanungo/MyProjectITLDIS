package com.i4o.dms.kubota.warranty.pcr.dto;

public interface WarrantyPcrSummaryExcelResponse {
	String getJobCardNo();
	String getJobCardDate();
	String getSoldDealerCode();
	String getSoldDealerName();
	String getServiceDealerCode();
	String getServiceDealerName();
	String getServiceDealerCity();
	String getServiceDealerState();
	String getPCRNo();
	String getPCRDate();
	String getStatus();
	String getPcrType();
	String getProduct();
	String getModel();
	String getsubModel();
	String getChassisNo();
	String getEngineNo();
	String getDateOfInstallation();
	String getDateOfFailure();
	String getHours();
	String getCrop();
	String getCropCondition();
	String getFieldCondition();
	String getSoilType();
	String getFailureTypeOE();
	String getFailureTypeRepeatFailure();
	String getCustomernameAddress();
	String getCustomerConcern();
	String getMechanicObservation();
	String getDealerObservation();
	String getActionTaken();
	String getDealerRemarks();
	String getKAIRemarks();
	String getFreeServiceDate1();
	String getFreeServiceHours1();
	String getFreeServiceDate2();
	String getFreeServiceHours2();
	String getFreeServiceDate3();
	String getFreeServiceHours3();
	String getFreeServiceDate4();
	String getFreeServiceHours4();
	String getFreeServiceDate5();
	String getFreeServiceHours5();
	String getFreeServiceDate6();
	String getFreeServiceHours6();
	String getFreeServiceDate7();
	String getFreeServiceHours7();
	String getFreeServiceDate8();
	String getFreeServiceHours8();
	String getFreeServiceDate9();
	String getFreeServiceHours9();
	String getFreeServiceDate10();
	String getFreeServiceHours10();
	String getApprovedBy1();
	String getDesignation1();
	String getApprovedDate1();
	String getApprovedBy2();
	String getDesignation2();
	String getApprovedDate2();
	String getApprovedReamarks();
	
	String getClaimAmount();	//Suraj-21-03-2023
	String getApprovedAmount();	//Suraj-21-03-2023

}
