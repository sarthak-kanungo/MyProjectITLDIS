package com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author mahesh.kumar
 */
@JsonPropertyOrder({"action","territoryNo", "dealerName", "branchName", "countryName", "stateName", "districtName", "tehsilName"})
public interface DTMSearchResponseDto {
	String getTerritoryNo();
	String getDealerName();
	String getBranchName();
	String getCountryName();
	String getStateName();
	String getDistrictName();
	String getTehsilName();
	String getAction();
    @JsonIgnore
    Long getTotalCount();

}
