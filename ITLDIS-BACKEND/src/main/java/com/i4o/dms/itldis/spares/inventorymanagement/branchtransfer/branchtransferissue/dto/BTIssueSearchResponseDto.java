package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * @author mahehs.kumar
 */
@JsonPropertyOrder({"action", "issueNo", "reqFromBranch", "reqBy", "reqToBranch", "issueDate"})

public interface BTIssueSearchResponseDto {
	String getIssueNo();
	String getReqFromBranch();
	String getReqBy();
	String getReqToBranch();
	String getIssueDate();
	String getAction();
    @JsonIgnore
    Long getTotalCount();

}
