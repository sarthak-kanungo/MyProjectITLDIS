package com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class OPSItemsDetailResponseDto {
	
	private int orderTypeId;
	private String orderType;
	private List<Map<String, Object>> itemDetailsList;

}
