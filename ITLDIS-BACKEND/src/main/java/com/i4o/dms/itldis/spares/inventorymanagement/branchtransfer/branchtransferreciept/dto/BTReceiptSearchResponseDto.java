package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferreciept.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author mahehs.kumar
 */
@JsonPropertyOrder({"action", "receiptNo", "issuingBranch", "reqBy", "receivingBranch", "receiptDate"})

public interface BTReceiptSearchResponseDto {
	String getReceiptNo();
	String getissuingBranch();
	String getReqBy();
	String getreceivingBranch();
	String getReceiptDate();
	String getAction();
    @JsonIgnore
    Long getTotalCount();

}
