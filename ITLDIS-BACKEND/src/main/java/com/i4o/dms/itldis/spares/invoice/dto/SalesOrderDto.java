package com.i4o.dms.itldis.spares.invoice.dto;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesOrderDto {
	
	private Map<String,Object> invoiceDetails;
	
	private List<Map<String,Object>> itemDetails;
}
