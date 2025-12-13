package com.i4o.dms.kubota.crm.crmmodule.surveysummaryreport.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "action", "surveyNumber", "productGroup", "product", "model", "subModel", "chassisNo", "engineNo",
	"customerName", "customerNumber", "customerAlternetNumber", "customerState", "dealerCode", "dealerName",
	"dealerState", "surveyType", "dateOfInstallation", "dcDate", "surveyDate", "surveyDueDate", "surveyComments",
	"surveyStatus", "satisficationLevel", "surveyDoneBy", "surveyDoneDate",
	"actionTakenBy", "issueClosedBy", "issueClosedOn", "department", "tmName", "rsmName", "zone", "id", "secondId",
	"callAttemptID", "survey_Hdr_Id" })
public interface SurveySummaryReportDissatisfiedResponse {

	Long getId();

	String getAction();

	String getSurveyNumber();

	String getProductGroup();

	String getProduct();

	String getModel();

	String getSubModel();

	String getChassisNo();

	String getEngineNo();

	String getCustomerName();

	String getCustomerNumber();

	String getCustomerAlternetNumber();

	@JsonProperty("customerState")
	String getState();

	String getDealerCode();

	String getDealerName();

	String getDealerState();

	String getSurveyType();

	String getDateOfInstallation();

	String getDcDate();

	String getSurveyDate();

	String getSurveyDueDate();

	String getSurveyComments();

	@JsonProperty("satisficationLevel")
	String getCustomerSatisfied();

	String getSurveyDoneBy();

	String getSurveyDoneDate();

	String getActionTakenBy();

	String getIssueClosedBy();

	String getIssueClosedOn();

	String getDepartment();

	String getTmName();

	String getRsmName();

	String getzone();

	String getSurveyStatus();

	Long getSecondId();

	Long getCallAttemptID();

	Long getSurveyRecordingID();

	Long getSurvey_Hdr_Id();

	@JsonIgnore
	Long getRecordCount();
}
