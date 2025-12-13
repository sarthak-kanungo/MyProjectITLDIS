package com.i4o.dms.kubota.warranty.hotlinereport.domain;

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
@Table(name = "WA_HTLR_FAILURE_MST")
public class WarrantyHotlineReportFailureMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String failureType;
	
	private Character activeStatus;
	
}
