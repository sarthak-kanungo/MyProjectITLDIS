package com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.repository;


import com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.domain.IncentiveSchemeMaster;
import com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.dto.IncentiveSchemeMasterSearchDto;
import com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.dto.IsmViewReponseDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IncentiveSchemeMasterRepo extends JpaRepository<IncentiveSchemeMaster,Long> {

    @Query(value="{call sp_schemeTypeDropdown}",nativeQuery = true)
    List<Map<String,Object>> schemeType();
    
    @Query(value="{call sp_get_insentive_scheme_master_region_dropdown(:userId, :zoneIds)}",nativeQuery = true)
    List<Map<String,Object>> getRegions(@Param("userId")Long userId, @Param("zoneIds")String zone);

    @Query(value="{call sp_search_incentive_scheme_master(:userCode, :schemefromdate, :schemetodate, :schemeNo, :schemeType, :status, :page, :size)}", nativeQuery = true)
    List<IncentiveSchemeMasterSearchDto> searchIncentiveSchemes(@Param("userCode")String userCode, @Param("schemefromdate")String schemefromdate, @Param("schemetodate")String schemetodate,
    		@Param("schemeNo")String schemeNo, @Param("schemeType")String schemeType, @Param("status")String status, @Param("page")Integer page, @Param("size")Integer size);
    
    @Query(value="{call sp_get_scheme_no(:searchvalue,:userCode)}", nativeQuery = true)
    List<Map<String,Object>> getSchemeNo(@Param("searchvalue")String searchvalue,@Param("userCode")String userCode);

    @Query(value="{call GET_ALL_ZONES()}", nativeQuery = true)
    List<Map<String, Object>> getAllZones();
    
    IsmViewReponseDto findBySchemeNumber(String schemeNo);
    
}









