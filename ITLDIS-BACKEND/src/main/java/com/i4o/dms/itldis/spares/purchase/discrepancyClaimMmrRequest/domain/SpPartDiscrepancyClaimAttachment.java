package com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SP_PART_DISCREPANCY_ATTACHMENT")
public class SpPartDiscrepancyClaimAttachment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long spDiscrHdrId;
	
	private String fileName;
	
	private String fileType;

}
