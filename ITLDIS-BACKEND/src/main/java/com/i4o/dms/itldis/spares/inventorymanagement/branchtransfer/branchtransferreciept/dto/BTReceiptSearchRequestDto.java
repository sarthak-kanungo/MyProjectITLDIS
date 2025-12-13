package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferreciept.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
/**
 * @author mahesh.kumar
 */
@Getter
@Setter

public class BTReceiptSearchRequestDto {
	private String receiptNo;
	private String status;
	private Date fromDate;
	private Date toDate;
	private Integer page;
	private Integer size; 

}
