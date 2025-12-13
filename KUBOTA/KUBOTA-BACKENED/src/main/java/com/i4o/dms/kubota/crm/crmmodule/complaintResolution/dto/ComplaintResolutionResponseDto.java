package com.i4o.dms.kubota.crm.crmmodule.complaintResolution.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id",
	 "action",
	 "complaintType", 
	 "documentNo",
	 "documentDate",
	 "department",
	 "customerName",
	 "mobileNo",
	 "village",
	 "tehsil",
	 "district",
	 "state",
	 "model",
	 "chassisNumber",
	 "dealerName",
	 "dealerLocation",
	 "complaintQuery",
	 "frtStatus",
	 "reasonForDelayFrt",
	 "isInvalid",
	 "actionTaken",
	 "reasonForDelayArt",
	 "complaintStatus"
	 })
public interface ComplaintResolutionResponseDto {
	 Long getId();
	 String getAction(); 
	 String getComplaintType(); 
	 String getDocumentNo();
	 String getDocumentDate();
	 String getDepartment();
	 String getCustomerName();
	 String getMobileNo();
	 String getVillage();
	 String getTehsil();
	 String getDistrict();
	 String getState();
	 String getModel();
	 String getChassisNumber();
	 String getDealerName();
	 String getDealerLocation();
	 String getComplaintQuery();
	 @JsonProperty("FRT Status")
	 String getFrtStatus();
	 @JsonProperty("Reason For Delay FRT")
	 String getReasonForDelayFrt();
	 String getIsInvalid();
	 String getActionTaken();
	 @JsonProperty("Reason For Delay ART")
	 String getReasonForDelayArt();
	 String getComplaintStatus();

	 @JsonIgnore
	 Long getTotalCount();
}
