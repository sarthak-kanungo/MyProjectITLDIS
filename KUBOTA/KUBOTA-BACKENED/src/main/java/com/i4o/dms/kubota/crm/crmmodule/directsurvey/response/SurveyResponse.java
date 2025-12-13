package com.i4o.dms.kubota.crm.crmmodule.directsurvey.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "action", "id" })

public interface SurveyResponse {

	Long getId();

	@JsonIgnore
	Long getRecordCount();

}
