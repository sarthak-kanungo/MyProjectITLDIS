package com.i4o.dms.kubota.service.accpac.claim.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ACC_PAC_CLAIM_APRVL_HDR")
public class AccpacClaimAprvlHdrEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(updatable=false)
	private String docNo = "DRA/"+ System.currentTimeMillis();;
	@Column(updatable=false)
	private Date docDate;
	@Column(updatable=false)
	private String docType;
	private String docStatus="Waiting For Approval";
	@Column(updatable=false)
	private Date createdOn;
	@Column(updatable=false)
	private Long createdBy;
	private Date modifiedOn;
	private Long modifiedBy;
	@JsonManagedReference
	@OneToMany(mappedBy="claimHdr", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<AccpacClaimAprvlDtlEntity> claimDtls;
	
}
