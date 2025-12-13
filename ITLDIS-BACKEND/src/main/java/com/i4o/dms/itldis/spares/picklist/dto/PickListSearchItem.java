package com.i4o.dms.itldis.spares.picklist.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"edit","picklistNumber","picklistDate","saleOrderNumber","saleOrderDate","customerName","contactNumber","city","picklistStatus"})
public interface PickListSearchItem {
	public String getId();
	public String getPicklistNumber();
	public String getPicklistDate();
	@JsonIgnore
	public String getSalesOrderId();
	public String getSaleOrderNumber();
	public String getSaleOrderDate();
	public String getCustomerName();
	public String getContactNumber();
	public String getCity();
	public String getPicklistStatus();	
	public String getEdit();
	@JsonIgnore
	public Long getRecordCount();
}
