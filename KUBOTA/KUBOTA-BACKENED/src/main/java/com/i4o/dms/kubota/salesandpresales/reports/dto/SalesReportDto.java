package com.i4o.dms.kubota.salesandpresales.reports.dto;



import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SalesReportDto {
	

	private Long dealer;
	private Long hierId;
	private String model;
	private String subModel;
	private Integer page;
	private Integer size;
	private Long dealerEmpId;
	private String product;
	private String productGroup;
	private String series;
	private String variant;
	private String itemCode;
	private Long hoUser;
	private String flag;

	private String activityDate;
	private String fromDate;
	private String toDate;
	private String machineModel;
	private String chassisNo;
	private Long orgHierId;
	private Long dealerId;
	private Long branchId;
	private String activityNumber;
	private String activityStatus;
	private String activityClaimNumber;
	private String activityClaimStatus;
	
	private String financeType;
	private String financier;
	private String subsidy;
}
