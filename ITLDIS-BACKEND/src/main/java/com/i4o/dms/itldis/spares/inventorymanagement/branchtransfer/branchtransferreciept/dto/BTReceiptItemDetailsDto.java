package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferreciept.dto;

import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.SparePartMasterDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BTReceiptItemDetailsDto {
	
	private SparePartMasterDto sparePartMaster;
	
	private Integer issuedQty;
	
	private Integer receiptQty;
	
	private Double itemMrp;
	
	private Double receiptMrpValue;
	
	private Integer pendingQty;
	
}
