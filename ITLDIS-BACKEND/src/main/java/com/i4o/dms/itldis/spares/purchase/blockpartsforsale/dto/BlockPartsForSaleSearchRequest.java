package com.i4o.dms.itldis.spares.purchase.blockpartsforsale.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockPartsForSaleSearchRequest {
	
	private String partNo;
	
	private String status;
	
	private Integer page;
	
	private Integer size;
	
}
