package com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.service;

import javax.transaction.Transactional;

import com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.domain.DealerTerritoryMapHdr;
import com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.dto.DTMtSearchRequestDto;
import com.i4o.dms.kubota.utils.ApiResponse;

@Transactional
public interface TerritoryMasterService {
	
	ApiResponse<?> saveDealerTerritoryMap(DealerTerritoryMapHdr territoryMapHdr) throws Exception;
	
	ApiResponse<?> getAllDealers(String dealer);
	
	ApiResponse<?> getBranchByDealerId(Long dealerId);
	
	ApiResponse<?> getCountry();
	
	ApiResponse<?> getState(Long dealerId);
	
	ApiResponse<?> getDistrict(Long stateId);
	
	ApiResponse<?> getTehsil(Long districtId);
	
	ApiResponse<?> searchDTM(DTMtSearchRequestDto dtmSearchDto);
	
	ApiResponse<?> viewDealerTerritoryMapping(String territoryNo);
	
	ApiResponse<?> autoSearchTerritoryNo(String territoryNo);
	
	ApiResponse<?> autoSearchBranchName(String branchName);
	
}
