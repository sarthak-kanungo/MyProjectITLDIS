package com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CallSearchRequest {

	private String fromDate;
	private String toDate;
	private String mobileNo;
	private String chassisNo;
	private String customerMobNo;
	private String customerName;
//	private Long callTypeId;		// added by mahesh.kumar on 27-06-23
	private String callType;       // added by mahesh.kumar on 26-06-23
	private String callStatus;		// added by mahesh.kumar on 26-06-23
	private String currentServiceDueDate;
	private String currentServiceDueType;
	private Integer page;
	private Integer size;
	
	// added on 26-06-23
//	
//	private String deliveryChallanNumber;
    private String chassisNumber;
//    private String customerName;
    private String customerMobileNumber;
//    private String dcFromDate;
//    private String dcToDate;
//    private String enquiryNumber;
//    private String enquiryType;
//    private String dcStatus;
//    private String product;
//    private String series;
//    private String model;
//    private String subModel;
//    private String varient;
//    private String itemNumber;
//    private String engineNumber;
////    private Integer page;
////    private Integer size;
    private Long hierId;
//    private Long dealerId;
	
	
}
