package com.i4o.dms.itldis.service.claim.domain;

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
@Table(name="SV_CLAIM_DTL")
public class ServiceClaimDltEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="claim_hdr_id")
	@JsonBackReference("claimDtls")
	private ServiceClaimHdrEntity claimHdr;
	
	private String documentType;
	
	private Long serviceJobCardId;
	
	private Long installationId;
	
	private Date lastModifiedDate;
	
	private BigDecimal claimAmount;
	
	private BigDecimal bonusAmount;
	
	private BigDecimal totalClaimAmount;
	
}
