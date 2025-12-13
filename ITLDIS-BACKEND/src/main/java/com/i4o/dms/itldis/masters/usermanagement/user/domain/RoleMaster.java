package com.i4o.dms.itldis.masters.usermanagement.user.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "ADM_MENU_ROLE_HDR")

public class RoleMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleCode = "Role-"+ System.currentTimeMillis();;

    @NotNull
    private String roleName;

    @NotNull
    private String applicableTo;

    private Character activeStatus;
    
    @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(updatable = false)
	private Date createdDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date lastModifiedDate;

	@Column(updatable = false)
	private Long createdBy;

	private Long lastModifiedBy;

}
