package com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.dto;

import lombok.Data;

@Data
public class AuctionPartsListReqDto {
	
	private Boolean isAuction;
	
	private Integer page;
	
	private Integer size;
}
