package com.i4o.dms.itldis.training.trainingProgramReport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrainingProgramReportExcelDto {
	
	private Long region;
	private Long  state;
	private Long tsmName;
	private Long dealerName;
	private char employeeStatus;
	private Long typeofTraining;
	private Long trainingModule;
	private Long delearEmpDesignation;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String trainingStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String  trainingEndDate;
    public Integer page;
    public Integer size;

}
