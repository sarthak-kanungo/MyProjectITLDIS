package com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface DiscrepancyClaimViewDto {
	
	Long getId();
	String getClaimReqNo();
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date getClaimDate();
	String getClaimType();
	String getStatus();
	
	DealerMaster getDealerMaster();
	interface DealerMaster {
		Long getId();
	    String getDealerCode();
	    String getDealerName();
	}
	
	AccpacSparePartInvoice getAccpacSparePartInvoice();
	interface AccpacSparePartInvoice {
		Long getId();
	    String getAccpacInvoiceNo();
	    
	    @JsonFormat(pattern = "yyyy-MM-dd")
	    Date getAccpacInvoiceDate();
	}
	
	SparePartGrn getSparePartGrn();
	interface SparePartGrn {
		Long getId();
	    String getSpareGrnNumber();
	    
	    @JsonFormat(pattern = "yyyy-MM-dd")
	    Date getGrnDate();
	    String getSupplierInvoiceNumber();
	    Integer getNoOfBoxesSent();
	    Integer getNoOfBoxesReceived();
	    Date getInvoiceDate();
	    
	    AccpacSparePartInvoice getInvoiceNumber();
	    interface AccpacSparePartInvoice {
	    	Long getId();
	        String getAccpacInvoiceNo();
	        
	        @JsonFormat(pattern = "yyyy-MM-dd")
	        Date getAccpacInvoiceDate();
	        String getTransportMode();
	        String getTransporter();
	        String getLrNo();
	        String getDealerCode();
	    }
	}
	
	DealerEmployeeMaster getDealerEmployeeMaster();
	interface DealerEmployeeMaster {
		Long getId();
	    String getEmployeeCode();
	    String getMobileNo();
	}
	
	List<SpPartDiscrepancyClaimDtl> getDiscrepancyClaimDtls();
	interface SpPartDiscrepancyClaimDtl {
		Long getId();
		Integer getInvoiceQty();
		Integer getReceiptQty();
		Integer getShortQty();
		Integer getDamageQty();
		Integer getExcessQty();
		Integer getReturnQty();
		Float getValue();
		String getDealerRemarkReasons();
		Integer getKaiAcceptedQty();
		String getKaiRemarkReason();
		
		SparePartMaster getPartMaster();
		interface SparePartMaster {
			Long getId();
			String getItemNo();
			String getItemDescription();
		}
	}

}
