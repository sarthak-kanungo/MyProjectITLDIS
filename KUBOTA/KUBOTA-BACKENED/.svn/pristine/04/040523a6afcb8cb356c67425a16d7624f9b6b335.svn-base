package com.i4o.dms.kubota.warranty.hotlinereport.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.kubota.warranty.hotlinereport.domain.WarrantyHotlineReportAttachment;
import com.i4o.dms.kubota.warranty.hotlinereport.domain.WarrantyHotlineReportMachineDetails;
import com.i4o.dms.kubota.warranty.hotlinereport.domain.WarrantyHotlineReportPartsDetail;

public interface WarrantyHotlineReportViewDto {
	
	Long getId();
	
	String getHtlrNo();
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date getHtlrDate();
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date getFailureDate();
	
	String getStatus();

	String getVendorResponse();

	String getComplaint();

	String getRemarks();
	
	WarrantyHotlineReportPlantMaster getHotlinePlantMaster();
	interface WarrantyHotlineReportPlantMaster {
		Long getId();
		String getPlantName();
		String getPlantDesc(); 
	}
	
	SalesMasterFormDepotAddress getDealerDepotMapping();
	interface SalesMasterFormDepotAddress {
		Long getId();
		String getDepot();
	}
	
	KubotaDepartmentMaster getDepartmentMaster();
	interface KubotaDepartmentMaster {
		Long getId();
	    String getDepartmentCode();
	    String getDepartmentName();
	}
	
	KubotaEmployeeMaster getEmployeeMaster();
	interface KubotaEmployeeMaster {
		Long getId();
	    String getEmployeeCode();
	    String getEmployeeName();
	}
	
	WarrantyHotlineReportFailureMaster getFailureType();
	interface WarrantyHotlineReportFailureMaster {
		Long getId();
	    String getFailureType();
	}
	
	WarrantyHotlineConditionFailureMaster getConditionFailureMaster();
	interface WarrantyHotlineConditionFailureMaster {
		Long getId();
		String getFailureCode();
		String getFailureDesc();
	}
	
	List<WarrantyHotlineReportPartsDetail> getHotlineReportPartsDetails();
	
	
	List<WarrantyHotlineReportMachineDetails> getHotlineReportMachineDetails();
	
	
	List<WarrantyHotlineReportAttachment> getHotlineReportAttachments();
	
}
