package com.i4o.dms.itldis.masters.crm.questionMaster.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name="QA_MST_QUES_SUB_DTL")
public class QuestionMainSubAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quesSubDtlId;
//	private Long quesDtlId;
	private String subAnsDesc;
	private char isotherapplicable;
	private char isactive;
	private Long createdBy;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "QUES_DTL_ID")
	private QuestionMainAnswer subAnswer;


}
