package com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.domain.DealerTerritoryMapHdr;
import com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.dto.DTMResponceViewDto;
import com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.dto.DTMtSearchRequestDto;
import com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.dto.DTMSearchResponseDto;
import com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.repository.DealerTerritoryMapHdrRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;

@Service
public class TerritoryMasterServiceImpl implements TerritoryMasterService{
	
	@Autowired
	private UserAuthentication userAuthentication;
	
	@Autowired
	private DealerTerritoryMapHdrRepo territoryMapRepo;
	
	@Override
	public ApiResponse<?> getAllDealers(String dealer) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(territoryMapRepo.getAllDealers(dealer));
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> getBranchByDealerId(Long dealerId) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(territoryMapRepo.getBranchByDealerId(dealerId));
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> getCountry() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(territoryMapRepo.getCountry());
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> getState(Long dealerId) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(territoryMapRepo.getState(dealerId));
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> getDistrict(Long stateId) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(territoryMapRepo.getDistrict(stateId));
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> getTehsil(Long districtId) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(territoryMapRepo.getTehsil(districtId));
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> searchDTM(DTMtSearchRequestDto dtmSearchDto) {
		ApiResponse<List<DTMSearchResponseDto>> apiResponse = new ApiResponse<>();
		
		List<DTMSearchResponseDto> responceDtoList = territoryMapRepo.searchDTM(
				dtmSearchDto.getTerritoryNo(),
				dtmSearchDto.getDealerName(),
				dtmSearchDto.getBranchName(),
				dtmSearchDto.getCountry(),
				dtmSearchDto.getState(),
				dtmSearchDto.getDistrict(),
				dtmSearchDto.getTehsil(),
				dtmSearchDto.getPage(),
				dtmSearchDto.getSize());
		
		Long count = 0l;
		if (responceDtoList != null && !responceDtoList.isEmpty()) {
			count = responceDtoList.get(0).getTotalCount();
		}
		
		apiResponse.setCount(count);
		apiResponse.setResult(responceDtoList);
		
		return apiResponse;
	}

	@Override
	@Transactional
	public ApiResponse<?> saveDealerTerritoryMap(DealerTerritoryMapHdr territoryMapHdr) throws Exception {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		
		if(territoryMapHdr.getId() == null){
			territoryMapHdr.setTerritoryNo("DRA/"+ ThreadLocalRandom.current().nextInt(1000) + "/" + System.currentTimeMillis());
        }
		
		if(territoryMapHdr.getId() == null) {
			territoryMapHdr.setCreatedBy(userAuthentication.getLoginId());
			territoryMapHdr.setCreatedDate(new Date());
		} 
		else if(territoryMapHdr.getId() != null) {
			territoryMapHdr.setLastModifiedBy(userAuthentication.getLoginId());
			territoryMapHdr.setLastModifiedDate(new Date());
			
			//Deleting old mapping data for correctly updating.
			territoryMapRepo.deleteTerritoryMappingByHdrId(territoryMapHdr.getId());
        }
		
//		if(territoryMapHdr.getDealerTerritoryMapDtls() == null || territoryMapHdr.getDealerTerritoryMapDtls().isEmpty()) {
//			throw new Exception("Territory details cann't be empty");
//		}

		territoryMapRepo.save(territoryMapHdr);
		
		apiResponse.setResult("Saved");
		apiResponse.setMessage("Territory Dealer Master Mapping Saved Successful!");
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> viewDealerTerritoryMapping(String territoryNo) {
		ApiResponse<DTMResponceViewDto> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(territoryMapRepo.findByTerritoryNo(territoryNo));
		
		return apiResponse;
	}
	
	
	
	@Override
	public ApiResponse<?> autoSearchTerritoryNo(String territoryNo) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(territoryMapRepo.autoSearchTerritoryNo(territoryNo));
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> autoSearchBranchName(String branchName) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(territoryMapRepo.autoSearchBranchName(branchName));
		
		return apiResponse;
	}
	

}
