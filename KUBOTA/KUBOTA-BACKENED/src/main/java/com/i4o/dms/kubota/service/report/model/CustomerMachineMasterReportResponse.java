package com.i4o.dms.kubota.service.report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"dealerCode","dealerName",
		"modelCode","status","machineSerialNumber","engineNumber","kaiInvoiceDate",
		"customerName","address","city","taluka","district",
		"customerMobileNumber","csbBookNumber","pdiDate",
		"dcNumber", "dcDate",		//Suraj--09/12/2022
		"deliveryInstallationDate","fieldInstallationDate","warrantyEndDate",
		"freeservice1Date","freeservice1Hour","service1By",
		"freeservice2Date","freeservice2Hour","service2By",
		"freeservice3Date","freeservice3Hour","service3By",
		"freeservice4Date","freeservice4Hour","service4By",
		"freeservice5Date","freeservice5Hour","service5By",
		"freeservice6Date","freeservice6Hour","service6By",
		"freeservice7Date","freeservice7Hour","service7By",
		"freeservice8Date","freeservice8Hour","service8By",
		"freeservice9Date","freeservice9Hour","service9By",
		"freeservice10Date","freeservice10Hour","service10By"
		})
public interface CustomerMachineMasterReportResponse {

	String getDealerCode();
	String getDealerName();
	String getModelCode();
	String getStatus();
	String getMachineSerialNumber();
	String getEngineNumber();
	String getKaiInvoiceDate();
	String getCustomerName();
	String getAddress();
	String getCity();
	String getTaluka();
	String getDistrict();
	String getCustomerMobileNumber();
	String getCsbBookNumber();
	String getPdiDate();
	
	String getDcNumber();		//Suraj--09/12/2022
	String getDcDate();		//Suraj--09/12/2022
	
	String getDeliveryInstallationDate();
	String getFieldInstallationDate();
	String getWarrantyEndDate();
	String getFreeservice1Date();
	String getFreeservice1Hour();	
	String getService1By();
	String getFreeservice2Date();
	String getFreeservice2Hour();	
	String getService2By();
	String getFreeservice3Date();
	String getFreeservice3Hour();	
	String getService3By();
	String getFreeservice4Date();
	String getFreeservice4Hour();	
	String getService4By();
	String getFreeservice5Date();
	String getFreeservice5Hour();	
	String getService5By();
	String getFreeservice6Date();
	String getFreeservice6Hour();	
	String getService6By();
	String getFreeservice7Date();
	String getFreeservice7Hour();	
	String getService7By();
	String getFreeservice8Date();
	String getFreeservice8Hour();	
	String getService8By();
	String getFreeservice9Date();
	String getFreeservice9Hour();	
	String getService9By();
	String getFreeservice10Date();
	String getFreeservice10Hour();	
	String getService10By();
	@JsonIgnore
	Long getRecordsCount();
}
