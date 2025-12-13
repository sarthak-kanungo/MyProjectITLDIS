package com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.repository;


import com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.domain.IncentiveSchemeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IncentiveSchemeRepo extends JpaRepository<IncentiveSchemeMaster,Long> {

    @Query(value="{call sp_schemeTypeDropdown}",nativeQuery = true)
    List<Map<String,Object>> schemeType();

}









