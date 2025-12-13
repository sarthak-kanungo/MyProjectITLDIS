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
@Table(name="QA_SURVEY_OTHER_MACHINE")
public class SurveyOtherMachine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//private Long surveyHdrId;
	private String brand;
	private String model;
	private Integer yearOfPurchase;
	private String serialNo;
	private boolean deleteFlag;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "Survey_Hdr_Id")
	private SurveyHeader surveryHdr;

}
