package com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name="QA_SURVEY_QUESTION_DTL")
public class CurrentSurveyQuestions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//private Long surveyHdrId;
	private String dept;
	private String questionDesc;
	private String mainAnswer;
	
//	@NotNull(message = "Sub Answer can't be null")
	private String subAnswer;
	private String remarks;
	private String additionalComments;
	private Long quesId;
	private Long quesDtlId;
	private Long quesSubDtlId;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "Survey_Hdr_Id")
	private SurveyHeader surveryHdr;
	
}
