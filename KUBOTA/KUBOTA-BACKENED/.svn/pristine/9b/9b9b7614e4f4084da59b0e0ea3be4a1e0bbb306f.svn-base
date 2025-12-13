package com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.domain.DealerTerritoryMapHdr;
import com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.dto.DTMResponceViewDto;
import com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.dto.DTMSearchResponseDto;

@Transactional
public interface DealerTerritoryMapHdrRepo extends JpaRepository<DealerTerritoryMapHdr, Long> {
	
	@Query(nativeQuery=true, value="{call sp_territory_master_get_dealer (:dealer)}")
	List<Map<String, Object>> getAllDealers(String dealer);
	
	@Query(value = "{call sp_territory_master_get_branch(:dealerId)}", nativeQuery = true)
	List<Map<String, Object>> getBranchByDealerId(@Param("dealerId") Long dealerId);
	
	@Query(nativeQuery=true, value="{call sp_territory_master_get_country}")
	List<Map<String, Object>> getCountry();
	
	@Query(value = "{call sp_territory_master_get_state_by_dealer(:dealerId)}", nativeQuery = true)
	List<Map<String, Object>> getState(@Param("dealerId") Long dealerId);
	
	@Query(value = "{call sp_territory_master_get_district(:stateId)}", nativeQuery = true)
	List<Map<String, Object>> getDistrict(@Param("stateId") Long stateId);
	
	@Query(value = "{call sp_territory_master_get_tehsil(:districtId)}", nativeQuery = true)
	List<Map<String, Object>> getTehsil(@Param("districtId") Long districtId);
	
	@Query(value = "{call SP_CM_GEO_TERRITORY_SEARCH(:territoryNo, :dealerName, :branchName, :countryName, :stateName, :districtName, :tehsilName, :page, :size)}", nativeQuery = true)
	List<DTMSearchResponseDto> searchDTM(
			@Param("territoryNo") String territoryNo,
			@Param("dealerName") String dealerName,
			@Param("branchName") String branchName,
			@Param("countryName") String countryName,
			@Param("stateName") String stateName,
			@Param("districtName") String districtName,
			@Param("tehsilName") String tehsilName,
			@Param("page") Integer page,
			@Param("size") Integer size);
	
	DTMResponceViewDto findByTerritoryNo(String territoryNo);
	
	
	@Query(value = "{call sp_territory_master_autosearch_territory_number(:territoryNo)}", nativeQuery = true)
	List<Map<String, Object>> autoSearchTerritoryNo(@Param("territoryNo") String territoryNo);
	
	
	@Query(value = "{call sp_territory_master_autosearch_branch_name(:branchName)}", nativeQuery = true)
	List<Map<String, Object>> autoSearchBranchName(@Param("branchName") String branchName);
	
	@Modifying
    @Query(value = "delete from CM_GEO_TERRITORY_MAP_DTL where territory_hdr_id = :hdrId", nativeQuery = true)
    Integer deleteTerritoryMappingByHdrId(@Param("hdrId") Long hdrId);
	
}
