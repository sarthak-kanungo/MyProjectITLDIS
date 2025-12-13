package com.i4o.dms.itldis.crm.crmmodule.surveysummaryreport.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"productGroup","product","model","question","answer","subAnswer","noOfTimeAnswered","percentageOfAnswer"})

public interface QAReportResponse {

	String getProductGroup();
	String getProduct();
	String getModel();
	String getQuestion();
	String getAnswer();
	String getSubAnswer();
	String getNoOfTimeAnswered();
	String getPercentageOfAnswer();
	
	@JsonIgnore
	Long getRecordCount();
}
