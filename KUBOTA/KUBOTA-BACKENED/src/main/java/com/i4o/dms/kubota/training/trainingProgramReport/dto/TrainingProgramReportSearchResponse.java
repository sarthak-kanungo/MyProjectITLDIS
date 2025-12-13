package com.i4o.dms.kubota.training.trainingProgramReport.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"id","edit",})
public interface TrainingProgramReportSearchResponse {
	
	String getProgramNumber();
	String getProgramDate();
	String getTrainingLocDesc();
	String getTrainingModuleDesc();
	String getTrainingTypeDesc();
	String getLocation();
	String getStartDate();
	String getEndDate();
	String getRemarks();
	String getNomination_Number();
	String getNominationDate();
	String getDealerName();
	String getEmployeeName();
	String getAttendedStatus();
	String getNominationStatus();
	String getTrainingDate();
	String getAttendance();
	
	@JsonIgnore
	Long getRecordCount();

}
