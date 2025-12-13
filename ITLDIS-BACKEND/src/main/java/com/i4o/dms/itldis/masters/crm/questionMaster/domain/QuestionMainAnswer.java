package com.i4o.dms.itldis.masters.crm.questionMaster.domain;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name="QA_MST_QUESTION_DTL")
public class QuestionMainAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quesDtlId;
//	private Long quesId;
	@Column(name = "Main_Ans_desc")
	private String mainAnsDesc;
	
	@Column(name = "Sub_Answer_Applicable")
	private char subAnswerApplicable;
	
	@Column(name = "Multiple_Answer_Applicable")
	private char multipleAnswer;
	
	@Column(name = "Considerdissatisfied")
	private char considerDissatisfied;	
	private Long createdBy;
	
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "Ques_id")
	private QuestionMasterHeader quesHeader;
	
	@OneToMany(mappedBy = "subAnswer", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<QuestionMainSubAnswer> subAnswer;


}
