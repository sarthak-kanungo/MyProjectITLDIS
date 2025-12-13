package com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferissue.dto;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndentItemDetailsDto {
	
	private BranchTransferIndentDto transferIndent;
	
	private SparePartMasterDto sparePartMaster;
	
	private Integer currentStockQty;
	
	private BigInteger reqQty;
	
	private Double itemMrp;
	
}
