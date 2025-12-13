package com.i4o.dms.itldis.spares.invoice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceCancellationDto {

	private String remark;
	
	private Long id;
	
	private String referenceType; 
}
