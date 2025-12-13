package com.i4o.dms.kubota.crm.crmmodule.directsurvey.domain;

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
@Table(name="QA_Survey_Complaint_DTL")
public class SurveyComplaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//private Long surveyHdrId;
	private String complaintNo;
	private String department;
	private String typeOfComplaint;
	private String description;
	private String actionTaken;
	private Long hoUserId;
	
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "Survey_Hdr_Id")
	private SurveyHeader surveryHdr;

}
