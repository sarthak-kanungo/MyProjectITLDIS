package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * @author suraj.gaur
 */
@JsonPropertyOrder({"action", "indentNo", "reqFromBranch", "reqBy", "reqToBranch", "indentDate", "remarks"})
public interface BTIndentSearchResponseDto {
	
	String getIndentNo();
	String getReqFromBranch();
	String getReqBy();
	String getReqToBranch();
	String getIndentDate();
	String getRemarks();
	String getAction();
    @JsonIgnore
    Long getTotalCount();
}
