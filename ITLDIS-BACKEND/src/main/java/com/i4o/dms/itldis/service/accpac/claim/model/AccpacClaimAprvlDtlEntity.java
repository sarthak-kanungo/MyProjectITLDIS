package com.i4o.dms.itldis.service.accpac.claim.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ACC_PAC_CLAIM_APRVL_DTL")
public class AccpacClaimAprvlDtlEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="accpac_claim_aprvl_hdr_id")
    @JsonBackReference
	private AccpacClaimAprvlHdrEntity claimHdr;
	private Long claimId;
	private BigDecimal claimAmount;
	private BigDecimal approvedAmount;
	private Date lastModifiedOn;
	private Long lastModifiedBy;
}
