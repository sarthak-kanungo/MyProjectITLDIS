package com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.BinLocationMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.BinLocationSearchResponse;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BinLocationMasterRepository extends JpaRepository<BinLocationMaster,Long> {
	
	@Query(value = "{call sp_spares_mt_check_bin_location(:branchId,:storeId,:binLocationName)}", nativeQuery = true)
    BinLocationSearchResponse getExistingBinLocation(@Param("branchId") Long branchId,
    											 @Param("storeId") Long storeId,
                                                 @Param("binLocationName") String binLocationName);
}
