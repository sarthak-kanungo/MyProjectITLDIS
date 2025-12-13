package com.i4o.dms.itldis.masters.kaicommonmaster.dealerterritorymaster.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mahesh.kumar
 */
@Getter
@Setter
public class DTMtSearchRequestDto {
	
	private String territoryNo;
	private String dealerName;
	private String branchName;
	private String country;
	private String state;
	private String district;
	private String tehsil;
	private Integer page;
	private Integer size;

}
