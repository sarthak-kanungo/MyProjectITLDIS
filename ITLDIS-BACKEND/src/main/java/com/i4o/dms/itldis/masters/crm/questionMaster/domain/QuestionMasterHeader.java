package com.i4o.dms.itldis.masters.crm.questionMaster.domain;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name="QA_MST_QUESTION")
public class QuestionMasterHeader {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quesId;
	private String questionCode;
	private Long surveyTypeId;
	private String questionDesc;
	private char isactive;
	private Long createdBy;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	
	@OneToMany(mappedBy = "quesHeader", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<QuestionMainAnswer> mainAnswer;

}
