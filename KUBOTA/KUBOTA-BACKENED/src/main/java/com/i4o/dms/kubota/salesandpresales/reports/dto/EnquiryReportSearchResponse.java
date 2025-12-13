package com.i4o.dms.kubota.salesandpresales.reports.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"zone",	
	"area",	
	"dealerCode",	
	"dealerName",	
	"enquiryNumber",	
	"enquiryDate",	
	"validationDate",
	"genActivityType",
	"salesPersonName",	
	"enquiryType",
	"enquiryStatus",	
	"nextFupDate",	
	"tentativeDateOfPurchase",	
	"exchange",	
	"exchangeAssetDetails",	
	"customerName",	
	"fatherName",	
	"mobileNo",	
	"address",
	"city",
	"district",	
	"pincode",	
	"state",	
	"chassisNo",	
	"engineNo",	
	"model",	
	"subModel",	
	"variant",	
	"assetValue",
	"financeType",		
	"financierName",	
	"finance", 
	"loginDate",	
	"estimatedLoanAmount",
	"financeStatus",	
	"subsidy",	
	"marginAmount",	
	"totalAmountRecd"
})
public interface EnquiryReportSearchResponse {

	String getZone();	
	String getArea();	
	String getDealerCode();	
	String getDealerName();	
	String getEnquiryNumber();	
	String getEnquiryDate();	
	String getValidationDate();
	String getGenActivityType();	
	String getSalesPersonName();	
	String getEnquiryType();	
	String getEnquiryStatus();	
	String getNextFupDate();	
	String getTentativeDateOfPurchase();	
	String getExchange();	
	String getExchangeAssetDetails();	
	String getCustomerName();	
	String getFatherName();	
	String getMobileNo();	
	String getAddress();
	@JsonProperty("Taluk / Mandal")
	String getCity();
	String getDistrict();	
	String getPincode();	
	String getState();	
	String getChassisNo();	
	String getEngineNo();	
	String getModel();	
	String getSubModel();	
	String getVariant();	
	String getAssetValue();	
	@JsonProperty("Cash / Loan")
	String getFinanceType();		
	String getFinancierName();	
	String getFinance(); 
	String getLoginDate();	
	String getEstimatedLoanAmount();
	String getFinanceStatus();	
	String getSubsidy();	
	String getMarginAmount();	
	String getTotalAmountRecd();
	@JsonIgnore
	Long getRecordCount();
}
