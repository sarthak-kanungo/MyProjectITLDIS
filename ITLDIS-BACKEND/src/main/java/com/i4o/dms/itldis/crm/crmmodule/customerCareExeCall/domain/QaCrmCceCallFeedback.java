package com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="QA_CRM_CCE_CALL_FEEDBACK")
public class QaCrmCceCallFeedback {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long callHdrId;
	
	@ManyToOne
	@JoinColumn(name = "questionnaire_id", referencedColumnName = "id")
	private QaCrmCallQuesionnaire callQuesionnaire;
	
	private int rating;
	
	private String remarks;
	
	private Long jcId;
	
	private Long dcId;
}
