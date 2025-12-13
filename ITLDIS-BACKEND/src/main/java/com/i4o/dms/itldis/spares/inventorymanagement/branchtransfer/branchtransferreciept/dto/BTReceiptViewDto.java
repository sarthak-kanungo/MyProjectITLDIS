package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferreciept.dto;

import java.util.Date;
import java.util.List;

public interface BTReceiptViewDto {
	
	Long getId();
	
	String getReceiptNo();
	
	Date getReceiptDate();
	
	SPBranchTransferIssue getTransferIssue();
	interface SPBranchTransferIssue {
		
		Long getId();
		
		String getIssueNo();
	}
	
	DealerEmployeeMaster getReceivedBy();
	interface DealerEmployeeMaster {
		
		Long getId();

	    Long getDealerId();
	    
	    String getEmployeeCode();
	}
	
	BranchDepotMaster getIssuingBranchId();
	
	BranchDepotMaster getReceivingBranchId();
	interface BranchDepotMaster {
		
		Long getId();
		
		String getBranchCode();

		String getBranchName();
	}
	
	List<SPBranchTransferReceiptItem> getReceiptItems();
	interface SPBranchTransferReceiptItem {
		
		Long getId();
		
		Integer getIssuedQty();
		
		Integer getReceiptQty();
		
		Double getItemMrp();
		
		Double getReceiptMrpValue();
		
		SparePartMaster getSparePartMaster();
		interface SparePartMaster {

			Long getId();
			
			String getItemNo();
			
			String getItemDescription();
		}
		
		StoreMaster getStoreMaster();
		interface StoreMaster {
			
			Long getId();
			
			String getStoreCode();

		    String getStoreName();
		}
		
		BinLocationMaster getBinLocationMaster();
		interface BinLocationMaster {

			Long getId();
			
			String getBinLocation();
		}
	}
	
}
