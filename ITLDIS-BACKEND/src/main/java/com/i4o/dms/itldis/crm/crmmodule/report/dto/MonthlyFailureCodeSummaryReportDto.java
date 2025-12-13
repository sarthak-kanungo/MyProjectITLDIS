package com.i4o.dms.itldis.crm.crmmodule.report.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"failureCode","complaintDescription","model","year",	
	"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec",	
	"rejected",	"grandTotal","averageYear","total",	
	"averageTotalPerc",	"occurrencePercentage",	"score", "severityScore",	
	"detectablity", "rpn", "priority"})
public interface MonthlyFailureCodeSummaryReportDto {

	String getFailureCode();	
	String getComplaintDescription();	
	String getModel();	
	Integer getYear();	
	Integer getJan();
	Integer getFeb();	
	Integer getMar();	
	Integer getApr();	
	Integer getMay();	
	Integer getJun();	
	Integer getJul();	
	Integer getAug();	
	Integer getSep();	
	Integer getOct();	
	Integer getNov();	
	Integer getDec();	
	Integer getRejected();	
	Double getGrandTotal();	
	Double getAverageYear(); 
//	Double getTotal();	
	Double getAverageTotalPerc();	
	Double getOccurrencePercentage();	
	Double getScore();	
	Double getSeverityScore();
	Double getDetectablity();	
	Double getRpn();	
	Double getPriority();
	@JsonIgnore
	Long getRecordsCount();
}
