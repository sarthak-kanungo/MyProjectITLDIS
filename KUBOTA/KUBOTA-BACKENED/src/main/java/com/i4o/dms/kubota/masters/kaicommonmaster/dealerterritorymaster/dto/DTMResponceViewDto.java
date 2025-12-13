package com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.dto;

import java.util.List;

public interface DTMResponceViewDto {

	Long getId();
	
	String getTerritoryNo();
	
	DealerMaster getDealerMaster();
	interface DealerMaster {
		Long getId();
	    String getDealerCode();
	    String getDealerName();
	}
	
	BranchDepotMaster getBranchMaster();
	interface BranchDepotMaster {
		Long getId();
	    String getBranchCode();
	    String getBranchName();
	}
	
	Country getCountry();
	interface Country {
		Long getId();
	    String getCountry();
	}
	
	State getState();
	interface State {
		Long getId();
	    String getState();
	}
	
	District getDistrict();
	interface District {
		Long getId();
	    String getDistrict();
	}
	
	List<DealerTerritoryMapDtl> getDealerTerritoryMapDtls();
	interface DealerTerritoryMapDtl {
		Long getId();
		
		Tehsil getTehsil();
		interface Tehsil {
			Long getId();
		    String getTehsil();
		}
		
	}
	
}
