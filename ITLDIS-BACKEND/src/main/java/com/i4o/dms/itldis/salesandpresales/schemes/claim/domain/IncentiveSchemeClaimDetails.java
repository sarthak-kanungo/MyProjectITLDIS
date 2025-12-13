package com.i4o.dms.itldis.salesandpresales.schemes.claim.domain;

import java.math.BigDecimal;

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

@Table(name="incentive_scheme_claim_dtl")
@Entity
@Getter
@Setter
public class IncentiveSchemeClaimDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="claim_id")
	private IncentiveSchemeClaim incentiveSchemeClaim;
	private Long schemeId;
	private String customerName;
	private String mobileNo;
	private String address1;
	private String pincode;
	private String postOffice;
	private String tehsil;
	private String district;
	private String state;
	private String city;
	private String village;
	private String dcNo;
	private String dcDate;
	private String modelVariant;
	private String scheme;
	private String dse;
	private String tm;
	private String gm;
	private BigDecimal amount;
}
