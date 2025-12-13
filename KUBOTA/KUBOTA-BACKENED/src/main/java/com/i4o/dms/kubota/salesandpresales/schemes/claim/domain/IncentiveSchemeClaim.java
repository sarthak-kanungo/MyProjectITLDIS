package com.i4o.dms.kubota.salesandpresales.schemes.claim.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;

import lombok.Getter;
import lombok.Setter;

@Table(name="incentive_scheme_claim_hdr")
@Entity
@Getter
@Setter
public class IncentiveSchemeClaim {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String claimNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date claimDate;
	
	private Integer month;
	
	private String status= "Submitted";
	@ManyToOne
	@JoinColumn(name="dealer_id")
	private DealerMaster dealerMaster;
	private Integer wholesaleQty;	
	private BigDecimal incentiveValue;
	private Integer retails;
	private BigDecimal claimAmount;
	private BigDecimal totalAmount;
	
	private Long invoiceUploadedBy;
	
	@Column(updatable=false)
	private Date createdDate;
	@Column(updatable=false)
	private Long createdBy;
	
	private Date modifiedDate;
	private Long modifiedBy;
	
	@JsonManagedReference
	@OneToMany(mappedBy="incentiveSchemeClaim", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<IncentiveSchemeClaimDetails> incentiveSchemeClaimDetails;
	
	@Transient
	private List<Map<String, Object>> approvalDetails;
}
