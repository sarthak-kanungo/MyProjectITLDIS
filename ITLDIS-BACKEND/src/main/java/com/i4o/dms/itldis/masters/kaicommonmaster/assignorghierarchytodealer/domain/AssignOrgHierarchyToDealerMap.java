package com.i4o.dms.itldis.masters.kaicommonmaster.assignorghierarchytodealer.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = { "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" }, allowSetters = true)
@Table(name = "ADM_DEALER_ORG_HIER_MAP")

public class AssignOrgHierarchyToDealerMap {

	@EmbeddedId
	DealerHoDepartmentId dealerHoDepartmentId;
	
	@NotNull
	private Long orgHierarchyId;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(updatable = false)
	private Date createdDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date lastModifiedDate = new Date();

	@Column(updatable = false)
	private Long createdBy;

	private Long lastModifiedBy;

}