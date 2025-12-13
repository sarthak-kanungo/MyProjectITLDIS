package com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name="ADM_HO_USER_ORG_HIER")
public class KubotaEmployeeMasterOrgHier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UsridVsFieldroleid;
	@Column(updatable=false)
	private Long hoUsrId;
	@Column(updatable=false)
	private Long orgHierarchyId;
	
	private String Isactive;
	@Column(updatable=false)
	private Date createdDate = new Date();
	@Column(updatable=false)
	private Long createdBy;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date lastModifiedDate;
	
	private Long lastModifiedBy;

}
