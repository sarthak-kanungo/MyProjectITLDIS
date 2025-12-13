package com.i4o.dms.itldis.spares.purchase.backordercancellation.dto;

import java.util.List;

public interface SPBackOrderCancellationViewDto {
	
	Long getId();
	
	String getBocNo();
	
	String getStatus(); //added by Mahesh.Kumar on 22-02-2024
	
	DealerMaster getDealerMaster();
	interface DealerMaster {
		
		Long getId();
		
		String getDealerCode();
		
		String getDealerName();
	}
	
	DealerEmployeeMaster getEmployeeMaster();
	interface DealerEmployeeMaster {
		
		Long getId();

	    Long getDealerId();
	    
	    String getEmployeeCode();
	}
	
	List<SPBackOrderCancellationDtl> getCancellationDtls();
	interface SPBackOrderCancellationDtl {
		
		Long getId();
		
		String getStatus();
		
		Integer getPendingOrderQty();
		
		Integer getCancelQty();
		
		Integer getKaiAcceptedQty();
		
		String getAccpacPoNo();
		
		SparePartMaster getSparePartMaster();
		interface SparePartMaster {

			Long getId();
			
			String getItemNo();
			
			String getItemDescription();
		}
		
		SparePurchaseOrder getPurchaseOrder();
		interface SparePurchaseOrder {
			
			Long getId();
			
			String getPurchaseOrderNumber();
		}
	}
	
}
