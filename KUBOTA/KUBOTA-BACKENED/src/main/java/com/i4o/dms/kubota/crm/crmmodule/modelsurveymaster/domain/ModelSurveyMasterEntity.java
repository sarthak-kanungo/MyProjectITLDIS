package com.i4o.dms.kubota.crm.crmmodule.modelsurveymaster.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
@Getter
@Entity
@Table(name="")
public class ModelSurveyMasterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String surveyName;
	private Long surveyTypeId;
	private Long subModelId;
	private long noOfDays;
	private String isactive;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;

}
