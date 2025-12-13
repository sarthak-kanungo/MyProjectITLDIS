package com.i4o.dms.itldis.warranty.hotlinereport.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.spares.sparepartmaster.domain.SparePartMaster;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "WA_HTLR_PARTS_DTL")
public class WarrantyHotlineReportPartsDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "HTLR_ID", referencedColumnName = "id")
	private  WarrantyHotlineReport warrantyHotlineReport;
	
	@ManyToOne
    @JoinColumn(name = "spare_part_master_id", referencedColumnName = "id")
    private SparePartMaster sparePartMaster;
	
	private Integer quantity;
	
	private Boolean isClaimmable;
	
}
