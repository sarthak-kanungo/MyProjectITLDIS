package com.i4o.dms.itldis.spares.purchase.backordercancellation.dto;

import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.SparePartMasterDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SPBackOrderCancellationItemsDto {

	private SparePartMasterDto sparePartMaster;
	
	private SpPurchaseOrderDto purchaseOrder;
	
	private String accpacPoNo;
	
	private Integer pendingOrderQty;
	
	private Integer cancelQty;
	
	private Integer kaiAcceptedQty;
	
}
