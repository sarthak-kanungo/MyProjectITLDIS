package com.i4o.dms.kubota.spares.inventorymanagement.nonMovementInventory.dto;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"partId","currStockId","itemNumber","itemDescription","aging","isOEMPart",
	"isOilSupplierPart","isLocalPart","spegst","spmgst","spmrp","localPurchasePrice","alternatePartNo",
	"igstPercent","uom","hsCode8Digit"})
public interface NonMovInvSearchResDto {
	
	Long getPartId();
	Long getCurrStockId();
	String getItemNumber();
	String getItemDescription();
//	String getGrnNo();
//	String getGrnDate();
	String getAging();
//	String getBinLocation();
//	Integer getAvlQty();
//	Float getNdpPrice();
	Character getIsOEMPart();
	Character getIsOilSupplierPart();
	Character getIsLocalPart();
	Float getSpegst();
	Float getSpmgst();
	Float getSpmrp();
	Float getLocalPurchasePrice();
	String getAlternatePartNo();
	Float getIgstPercent();
	String getUom();
	String getHsCode8Digit();
//	Float getMrpPrice();
	
	@Value("#{target.isNonMoving == 1 ? true : false}")
	Boolean getIsAuction();
	
	@JsonIgnore
	Integer getIsNonMoving();
	@JsonIgnore
	Long getTotalCount();
	
}
