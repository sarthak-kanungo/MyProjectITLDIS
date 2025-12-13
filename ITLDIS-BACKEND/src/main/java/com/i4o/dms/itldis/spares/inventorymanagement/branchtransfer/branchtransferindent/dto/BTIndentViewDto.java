package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author suraj.gaur
 */
public interface BTIndentViewDto {
	
	Long getId();
	
	String getReqNo();
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date getReqDate();
	
	Boolean getDraftFlag();
	
	String getStatus();
	
	String getRemarks();
	
	BranchDepotMaster getReqFromBranch();
	BranchDepotMaster getReqToBranch();
	interface BranchDepotMaster {
		
		Long getId();
		String getBranchCode();
		String getBranchName();
	}
	
	DealerEmployeeMaster getReqBy();
	interface DealerEmployeeMaster {
		
		Long getId();
		String getEmployeeCode();
		String getFirstName();
	}
	
	List<BranchTransferIndentItem> getIndentItems();
	interface BranchTransferIndentItem {

		Long getId();
		Long getReqQty();
		Integer getReqBranchStockQty(); 
		Integer getSupBranchStockQty();
		SparePartMaster getSparePartMaster();
		interface SparePartMaster {
			
			Long getId();
			String getItemNo();
			String getItemDescription();
		}
	}
	
}
