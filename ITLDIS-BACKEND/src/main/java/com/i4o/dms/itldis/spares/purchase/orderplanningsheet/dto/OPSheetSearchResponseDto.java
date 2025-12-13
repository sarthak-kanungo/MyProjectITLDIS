package com.i4o.dms.itldis.spares.purchase.orderplanningsheet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author suraj.gaur
 */
@JsonPropertyOrder({"action","id","opSheetNo","orderDate","status","dealerCode","dealerName","reOrderQtyMonth",
	"suggestedOrderQtyMonth","orderType","logic","totalOrderValue"})
public interface OPSheetSearchResponseDto {
	
	Long getId();
	String getOpSheetNo();
	String getOrderDate();
	String getStatus();
	String getDealerCode();
	String getDealerName();
	String getReOrderQtyMonth();
	String getSuggestedOrderQtyMonth();
	String getOrderType();
	String getLogic();
	String getTotalOrderValue();
	String getAction();
    @JsonIgnore
    Long getTotalCount();

}
