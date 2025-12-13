package com.i4o.dms.itldis.crm.crmmodule.directsurvey.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@Entity
@Table(name="QA_SURVER_CALL_RESPONSE")
public class CallAttempt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long surveyResponseId;
//	private Long surveyHdrId;
	private Long surveyRemdId;
	private String responseTypeId;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date callDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private Date callTime;
	
	@NotNull(message = "Call Remarks can't be null")
	private String callRemarks;
	private String recordingFileName;
	@Column(updatable=false)
    private Date createdDate = new Date();
	@Column(updatable=false)
	private Long createdBy;
	
//
//	@JoinColumn(name = "Survey_Hdr_Id")
//	@ManyToOne
//	@JsonBackReference
//	private SurveyHeader surveryHdr;

}
