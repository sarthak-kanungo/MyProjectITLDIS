package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.dto;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SparePartMasterDto {

	private BigInteger id;
	
	private String itemNo;
	
	private String itemDescription;
}
