package com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MachineDiscrepancyClaimDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name="claim_id")
	private MachineDiscrepancyClaim machineDiscrepancyClaim;
	
	private Long complaintDetailId;
	
	private Integer quantity;
}
