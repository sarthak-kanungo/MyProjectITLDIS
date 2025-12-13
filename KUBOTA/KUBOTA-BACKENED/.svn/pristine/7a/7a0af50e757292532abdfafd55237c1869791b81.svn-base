package com.i4o.dms.kubota.service.mrc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "edit", "mrcNumber", "kaiInvoiceNumber", "totalItem", "pendingMrc", "chassisNumber",
		"mrcDate", "lrDate", "lrNumber", "engineNumber", "id" ,"invoiceDate"})
@JsonIgnoreProperties({ "recordCount" })
public interface SearchResponseServiceMrc {

	String getEdit();

	@JsonProperty("MRC_Number")
	String getMrcNumber();

	@JsonProperty("KAI_Invoice_Number")
	String getKaiInvoiceNumber();

	@JsonProperty("Total_Item")
	String getTotalItem();

	@JsonProperty("Pending_MRC")
	String getPendingMrc();

	@JsonProperty("Chassis_Number")
	String getChassisNumber();

	@JsonProperty("MRC_Date")
	String getMrcDate();

	@JsonProperty("LR_Date")
	String getLrDate();

	@JsonProperty("LR_Number")
	String getLrNumber();

	@JsonProperty("Engine_Number")
	String getEngineNumber();

	Long getId();

	Long getRecordCount();

	String getInvoiceDate();
}
