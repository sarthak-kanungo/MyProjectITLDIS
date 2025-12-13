package com.i4o.dms.kubota.service.activityclaim.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DealerInvoiceSearchRequestModel {

	private String invoiceNumber;
	
	private String claimNumber;
	
	private String activityNumber;
	
	private String invoiceType;
	
	private Integer page;
	
	private Integer size;
	
	private String fromDate;
	
	private String toDate;
	
	private String dealerCode;
	
	private Long orgHierarchyId;
}
