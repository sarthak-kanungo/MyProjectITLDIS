package com.i4o.dms.kubota.crm.crmmodule.report.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"dealerCode","dealerName","dealerState","totalDue","totalSurveyDone","totalSatisifed",
	"csScore", "rnrOrSwitchedOff", "incorrectData"})
public interface CustomerSatisfactionScoreDto {

	String getDealerCode();	
	String getDealerName();	
	String getDealerState();
//	String getState();
//	String getMonth();
	Integer getTotalDue();	
	Integer getTotalSurveyDone();	
//	Integer getTotalComplaints();
//	@JsonProperty("Complaint Dept.")
//	String getDepartment();
	Integer getTotalSatisfied();	
	Integer getCsScore();	
	@JsonProperty("RNR / Switched Off")
	Integer getRnrOrSwitchedOff();	
	Integer getIncorrectData();
	@JsonIgnore
	Long getRecordsCount();
}
