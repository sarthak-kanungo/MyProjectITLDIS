package com.i4o.dms.itldis.spares.inventorymanagement.machinestock.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MachineStockSearchModel {

	private Integer page;
	private Integer size;
	private String product;
	private String series;
	private String model;
	private String subModel;
	private String variant;
	private String itemNo;
	private String chassisNo;
	private String engineNo;
	private String status;
	private String dealerCode;
	private Long orgHierId;
	
	private String invoiceFromDate;	//Suraj--13-12-2022
	private String invoiceToDate;	//Suraj--13-12-2022
	
	private String grnDoneFlag;	//Suraj--02-01-2023
}
