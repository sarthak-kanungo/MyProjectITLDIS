package com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferissue.dto;

import java.util.Date;
import java.util.List;

public interface BTIssueViewDto {
	
	Long getId();
	
	String getIssueNo();
	
	Date getIssueDate();
	
	String getStatus();
	
	BranchDepotMaster getByBranchMaster();
	
	BranchDepotMaster getToBranchMaster();
	
	interface BranchDepotMaster {
		
		Long getId();
		
		String getBranchCode();
		
		String getBranchName();
	}
	
	DealerEmployeeMaster getEmployeeMaster();
	interface DealerEmployeeMaster {
		
		Long getId();
		
		String getEmployeeCode();
		
		String getFirstName();
	}
	
	List<SPBranchTransferIssueItem> getIssueItems();
	interface SPBranchTransferIssueItem {
		
		Long getId();
		
		Integer geCurrentStockQty();
		
		Integer getReqQty();
		
		Integer getIssuedQty();
		
		Integer getPendingQty();
		
		Double getItemMrp();
		
		Double getItemValue();
		
		SPBranchTransferIndent getTransferIndent();
		interface SPBranchTransferIndent {
			
			Long getId();
			
			String getReqNo();
		}
		
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
