package com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.domain;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.itldis.accpac.domain.AccpacSparePartInvoice;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.spares.purchase.grn.domain.SparePartGrn;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Getter
@Setter
@Entity
@Table(name = "SP_PART_DISCREPANCY_CLAIM_HDR")
@JsonIgnoreProperties(value = {"createdBy","createdDate","lastModifiedBy","lastModifiedDate"}, allowSetters = true)
public class SpPartDiscrepancyClaimHdr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "claim_req_no", unique = true, length = 21)
	private String claimReqNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();
	
	@Column(name = "branch_id", updatable=false)
    private Long branchId;
	
	@Column(name = "claim_date", updatable=false)
	private Date claimDate;
	
	@Column(name = "claim_type")
	private String claimType;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "grn_dealer_id")
	private DealerMaster dealerMaster;
	
	@ManyToOne
    @JoinColumn(name = "accpac_invoice_id")
	private AccpacSparePartInvoice accpacSparePartInvoice;
	
	@ManyToOne
    @JoinColumn(name = "sp_grn_id")
	private SparePartGrn sparePartGrn;
	
	@ManyToOne
	@JoinColumn(name = "claim_raised_by")
	private DealerEmployeeMaster dealerEmployeeMaster;
	
	@Column(name = "draft_flag")
	private boolean draftFlag;
	
	@OneToMany(mappedBy = "discrepancyClaimHdr", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SpPartDiscrepancyClaimDtl> discrepancyClaimDtls;	
	
	@Column(name = "created_by", updatable = false)
	private Long createdBy;
	
	@Column(name = "create_date", updatable = false)
	private Date createdDate;
	
	@Column(name = "last_modified_by")
	private Long lastModifiedBy;
	
	@Column(name = "last_modified_date")
	private Date lastModifiedDate;
	
}
