package com.i4o.dms.itldis.masters.dbentities.user;

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
@Table(name = "SA_MST_FORM_DEPOT_ADDRESS")
public class SalesMasterFormDepotAddress {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String accpacLocation;
	
	private String depot;
	
	private String depotAddress;
}
