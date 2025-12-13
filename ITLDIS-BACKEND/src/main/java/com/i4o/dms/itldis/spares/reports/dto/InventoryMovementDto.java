package com.i4o.dms.itldis.spares.reports.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"fromDate","toDate","dealerName","dealerCode","kaiInvoiceNumber","itemNumber","itemDescription",
	"purchaseQuantity","refGrnNo","saleQty","salesInvoiceNo","ndp","openingBalance","closingBalance"})
public interface InventoryMovementDto {

	String getFromDate();
	String getToDate();	
	String getDealerName();	
	String getDealerCode();	
	String getKaiInvoiceNumber();
	String getItemNumber();
	String getItemDescription();
	Integer getPurchaseQuantity();
	String getRefGrnNo();	
	Integer getSaleQty();	
	String getSalesInvoiceNo();	
	BigDecimal getNdp();	
	BigDecimal getOpeningBalance();	
	BigDecimal getClosingBalance();

	@JsonIgnore
	Long getTotalCount();
}