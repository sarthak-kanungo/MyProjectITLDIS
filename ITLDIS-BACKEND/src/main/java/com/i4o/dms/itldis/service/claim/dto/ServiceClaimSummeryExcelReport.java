package com.i4o.dms.itldis.service.claim.dto;

public interface ServiceClaimSummeryExcelReport {
	
	String getId();
	String getDealerCode();
	String getDealerName();
	String getDealerState();
	String getClaimNo();
	String getClaimDate();
	String getClaimStatus();
	String getFromDate();
	String getToDate();
	String getClaimAmount();
	String getBonusAmount();
	String getTotalClaimAmount();
	String getTotalApprovedAmount();
	String getRsnNumber();
	String getRsnDate();
	String getLastApprovedBy();
	
}
