package com.i4o.dms.itldis.training.attendanceSheet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"id", "edit", "programNumber", "programDate", "startDate", "endDate", "trainingLocDesc",
	"trainingModuleDesc", "trainingType"})

public interface TrainingAttendanceSheetSearchResponse {
	
	Long getId();
	String getEdit();
	String getProgramNumber();
	String getProgramDate();
	String getStartDate();
	String getEndDate();
	String getTrainingLocDesc();
	String getTrainingModuleDesc();
	String getTrainingType();
	
//	String getLocation();
//	String getNominationNumber();
//	String getNomineeDate();
//	String getTrainingDate();
//	String getAttendance();
//	String getEmployeeCode();
//	String getDealerName();
//	String getEmployeeName();
	
	@JsonIgnore
	Long getRecordCount();

}
