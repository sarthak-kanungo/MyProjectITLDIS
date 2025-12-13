package com.i4o.dms.kubota.warranty.hotlinereport.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "WA_HTLR_CONDITION_FAILURE_MST")
public class WarrantyHotlineConditionFailureMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String failureCode;
	
	private String failureDesc;
	
	private Long createdBy;
	
	private Date createdDate;
	
	private Long modifiedBy;
	
	private Date modifiedOn;
	
	private boolean activeFlag;
	
}
