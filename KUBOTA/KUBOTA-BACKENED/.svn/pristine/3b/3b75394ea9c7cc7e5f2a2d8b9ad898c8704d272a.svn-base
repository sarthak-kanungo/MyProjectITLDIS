package com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.masters.areamaster.model.Tehsil;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Getter
@Setter
@Entity
@Table(name = "CM_GEO_TERRITORY_MAP_DTL")
@JsonIgnoreProperties(value = {"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"}, allowSetters = true)
public class DealerTerritoryMapDtl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "territory_hdr_id", referencedColumnName = "id")
	private DealerTerritoryMapHdr dealerTerritoryMapHdr;
	
	@ManyToOne
	@JoinColumn(name = "tehsil_id", referencedColumnName = "id")
	private Tehsil tehsil;

}
