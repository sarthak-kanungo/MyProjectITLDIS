package com.i4o.dms.itldis.spares.purchase.backordercancellation.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mahesh.kumar
 */
@Getter
@Setter

public class SPBackOrderCancellationRequestDto {
	
	private String bocno;
	private String dealercode;
	private Date fromDate;
	private Date toDate;
	private Integer page;
	private Integer size; 

}
