package com.i4o.dms.kubota.masters.kaicommonmaster.assignorghierarchytodealer.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = { "createdBy", "createddate", "createdby", "modifieddate" }, allowSetters = true)
@Table(name = "ADM_HO_MST_ORG_LEVEL_HIER")
public class AssignOrgLevelHierarchyMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orgHierarchyId;

	@NotBlank(message = "hierarchy code can't be blank")
	@Column(length = 20)
	String hierarchyCode;

	@NotBlank(message = "hierarchy desc can't be blank")
	@Column(length = 150)
	String hierarchyDesc;

	Long parentOrgHierarchyId;

	@Column(updatable=false)
	private Long levelId;
	
	@NotBlank(message = "isActive can't be blank")
	@Column(length = 1)
	String isactive;

	@NotNull
	@Column(updatable = false)
	private Date createddate = new Date();

	private Date modifieddate;

	@NotNull
	@Column(updatable = false)
	private Long createdby;

	private Long modifiedby;

}