package com.i4o.dms.kubota.training.trainingNomination.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"id","nominationNumber","programNumber","employeeCode","employeeName","departmentName","dealerCode","dealerName","createdDate"})

public interface TrainingNominationSearchResponse {
	Long getId();
//	String getEdit();
	String getProgramNumber();
	String getNominationNumber();
	String getEmployeeCode();
	String getEmployeeName();
	String getDepartmentName();
	String getDealerCode();
	String getDealerName();
	String getCreatedDate();
	
	
	@JsonIgnore
	Long getRecordCount();
}
