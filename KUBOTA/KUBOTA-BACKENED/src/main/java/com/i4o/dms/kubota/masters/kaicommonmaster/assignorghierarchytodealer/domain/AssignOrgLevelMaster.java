package com.i4o.dms.kubota.masters.kaicommonmaster.assignorghierarchytodealer.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.KubotaDepartmentMaster;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = { "createdBy", "createddate", "createdby", "modifieddate" }, allowSetters = true)
@Table(name = "ADM_HO_MST_ORG_LEVEL")
public class AssignOrgLevelMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long level_id;

	@JoinColumn(name = "ho_department_id")
	@ManyToOne
	@NotNull
	private KubotaDepartmentMaster department;

	@Column(length = 50)
	String level_code;

	@NotBlank(message = "hierarchy desc can't be blank")
	@Column(length = 100)
	String level_desc;

	@NotNull
	Integer seq_no;

	@NotBlank(message = "isActive can't be blank")
	@Column(length = 10)
	String isActive;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(updatable = false)
	private Date createddate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date modifieddate;

	@NotBlank(message = "created by can't be blank")
	@Column(updatable = false, length = 100)
	private String createdby;

	private Long modifiedby;

}