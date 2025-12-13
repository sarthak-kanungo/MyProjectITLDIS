package com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SP_DISCREPANCY_KAI_ADDNL_REMARKS")
public class SpDiscrepancyKaiAdditionalRemarks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "sp_discr_hdr_id")
	private SpPartDiscrepancyClaimHdr discrepancyClaimHdr;
	
	private String remarkForDealer;
	
	private String internalKaiRemark;
	
	private Date kaiAcceptanceDate;
	
	private String creditNoteNo;
	
	private Date creditNoteDate;
	
	private Double creditNoteAmount;
	
	private Date kaiSettlementDate;

}
