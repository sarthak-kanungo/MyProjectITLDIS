package com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SparePartMaster;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SP_PART_DISCREPANCY_CLAIM_DTL")
public class SpPartDiscrepancyClaimDtl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "sp_discr_hdr_id", referencedColumnName = "id")
	private SpPartDiscrepancyClaimHdr discrepancyClaimHdr;
	
	@ManyToOne
	@JoinColumn(name = "part_id")
	private SparePartMaster partMaster;
	
	private Integer invoiceQty = 0;
	
	private Integer receiptQty = 0;
	
	private Integer shortQty = 0;
	
	private Integer damageQty = 0;
	
	private Integer excessQty = 0;
	
	private Integer returnQty = 0;
	
	private Float value = 0F;
	
	private String dealerRemarkReasons;
	
	private Integer kaiAcceptedQty = 0;
	
	private String kaiRemarkReason;	

}
