package com.i4o.dms.kubota.spares.inventorymanagement.currentstock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentStockDto {

	private String itemNo;
	private Integer page;
	private Integer size;
}
