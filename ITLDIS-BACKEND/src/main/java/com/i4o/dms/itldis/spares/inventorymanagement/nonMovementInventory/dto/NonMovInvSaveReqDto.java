package com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.dto;

import java.util.List;

import com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.domain.SpareStockCurrent;

import lombok.Data;

@Data
public class NonMovInvSaveReqDto {
	
	private List<SpareStockCurrent> stockCurrent;

}
