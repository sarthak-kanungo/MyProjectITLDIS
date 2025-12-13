package com.i4o.dms.itldis.crm.crmmodule.surveysummaryreport.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "action", "surveyNumber", "productGroup", "product", "model", "subModel", "chassisNo", "engineNo",
	"customerName", "customerNumber", "customerAlternetNumber", "customerState", "dealerCode", "dealerName",
	"dealerState", "surveyType", "dateOfInstallation", "dcDate", "surveyDate", "surveyDueDate", "surveyComments",
	"question", "mainAnswer", "subAnswer", "surveyStatus", "satisficationLevel", "surveyDoneBy", "surveyDoneDate",
	"issueClosedBy", "issueClosedOn", "id", "secondId",
	"callAttemptID", "survey_Hdr_Id" })
public interface SurveySummaryReportWithQuationariesResponse {

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

	String getQuestion();

	String getMainAnswer();

	String getSubAnswer();

	String getIssueClosedBy();

	String getIssueClosedOn();

	String getSurveyStatus();

	Long getSecondId();

	Long getCallAttemptID();

	Long getSurveyRecordingID();

	Long getSurvey_Hdr_Id();

	@JsonIgnore
	Long getRecordCount();
}
