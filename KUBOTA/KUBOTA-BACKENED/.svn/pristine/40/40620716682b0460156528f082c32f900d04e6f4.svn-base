package com.i4o.dms.kubota.masters.crm.questionMaster.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


//@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"id","edit","questionCode","questionType","question","mainAnswer","subAnswer","questionCreationDate"})


public interface QuestionMasterSearchResponse {

	Long getId();
	String getEdit();
	String getQuestionType();
	String getQuestionCode();
	String getQuestion();
	String getMainAnswer();
	String getSubAnswer();
	String getQuestionCreationDate();
	
	@JsonIgnore
	Long getRecordCount();
}
