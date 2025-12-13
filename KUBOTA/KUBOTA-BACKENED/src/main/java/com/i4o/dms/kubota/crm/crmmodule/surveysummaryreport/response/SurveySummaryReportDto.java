package com.i4o.dms.kubota.crm.crmmodule.surveysummaryreport.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
public class SurveySummaryReportDto {

	private String surveyName;
	private Long dealer;
	private String surveyStatus;
	private Character asOnDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private String fromDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private String toDate;
	private Long hierId;
	private String custSatisfaction;
	private String model;
	private String subModel;
	private String chassisNo;
	private Integer page;
	private Integer size;
	
	private Long dealerEmpId;
	private String product;
	private String series;
	private String variant;
	private String surveyNo;
	private String surveyType;
	private Long hoUser;
	private String question;
	private String reportType;
	private String customerSatisfication;
	private String flag;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private String fromDcDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private String toDcDate;
}
