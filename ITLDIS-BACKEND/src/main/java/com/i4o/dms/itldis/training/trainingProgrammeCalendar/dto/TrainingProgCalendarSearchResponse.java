package com.i4o.dms.itldis.training.trainingProgrammeCalendar.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


//@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"id","edit","approval","updateNominee","programNumber","programDate","trainingLocDesc","trainingModuleDesc","location","startDate","endDate",
				"lastNominationDate","maxNoOfNominees","noOfAllowedNominees","createdDate",})

public interface TrainingProgCalendarSearchResponse {

	
	Long getId();
	String getProgramNumber();
	String getProgramDate();
	String getTrainingLocDesc();
	String getTrainingModuleDesc();
	String getLocation();
	String getStartDate();
	String getEndDate();
	String getLastNominationDate();
	Integer getMaxNoOfNominees();
	Integer getNoOfAllowedNominees();
	String getCreatedDate();
	String getUpdateNominee();
	String getApproval();
	String getEdit();
	
	@JsonIgnore
	Long getRecordCount();
}
