package com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.domain;

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
import com.i4o.dms.kubota.masters.areamaster.model.Country;
import com.i4o.dms.kubota.masters.areamaster.model.District;
import com.i4o.dms.kubota.masters.areamaster.model.State;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.BranchDepotMaster;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Getter
@Setter
@Entity
@Table(name = "CM_GEO_TERRITORY_MAP_HDR")
@JsonIgnoreProperties(value = {"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"}, allowSetters = true)
public class DealerTerritoryMapHdr {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String territoryNo = "DRA/" + ThreadLocalRandom.current().nextInt(1000) + "/" + System.currentTimeMillis();
	
	@ManyToOne
	@JoinColumn(name = "dealer_id", referencedColumnName = "id")
	private DealerMaster dealerMaster;
	
	@ManyToOne
	@JoinColumn(name = "branch_id", referencedColumnName = "id")
	private BranchDepotMaster branchMaster;
	
	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	private Country country;
	
	@ManyToOne
	@JoinColumn(name = "state_id", referencedColumnName = "id")
	private State state;
	
	@ManyToOne
	@JoinColumn(name = "district_id", referencedColumnName = "id")
	private District district;
	
	private char isActive = 'Y';
	
	@OneToMany(mappedBy = "dealerTerritoryMapHdr", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<DealerTerritoryMapDtl> dealerTerritoryMapDtls;
	
	@Column(updatable=false)
	private Long createdBy;
	
	@Column(updatable=false)
	private Date createdDate;
	
	private Long lastModifiedBy;
	
	private Date lastModifiedDate;
}
