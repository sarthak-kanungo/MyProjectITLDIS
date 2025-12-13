package com.i4o.dms.itldis.service.claim.domain;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="SV_CLAIM_HDR")
public class ServiceClaimHdrEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String claimNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();
	
	private Date claimDate = new Date();
	
	private String claimStatus = "Claim Approval Pending";
	
	@Column(updatable=false)
	private Long dealerId;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(updatable=false)
	private Date fromDate;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(updatable=false)
	private Date toDate;
	
	@Column(updatable=false)
	private Date createdOn = new Date();
	
	@Column(updatable=false)
	private Long createdBy;
	
	private Date modifiedOn;
	
	private Long modifiedBy;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="claimHdr")
	@JsonManagedReference("claimDtls")
	private List<ServiceClaimDltEntity> claimDtls;
}
