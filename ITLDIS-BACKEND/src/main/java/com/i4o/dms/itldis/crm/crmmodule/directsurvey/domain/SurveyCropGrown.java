package com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name="QA_SURVEY_CROPS_GROWN")
public class SurveyCropGrown {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long surveyCropsId;
	//private Long surveyHdrId;
	private String cropGrown;
	private boolean deleteFlag;
	private Date lastupdatedon;
	
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "Survey_Hdr_Id")
	private SurveyHeader surveryHdr;

}
