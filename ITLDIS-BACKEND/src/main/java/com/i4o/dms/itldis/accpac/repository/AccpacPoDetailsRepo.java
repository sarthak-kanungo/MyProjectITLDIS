package com.i4o.dms.itldis.accpac.repository;

import com.i4o.dms.itldis.accpac.domain.AccpacPoDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface AccpacPoDetailsRepo extends JpaRepository<AccpacPoDetails,Long> {

    @Query(value = "{call sp_accpac_get_dealer_os(:dealerCode,:poid)}",nativeQuery = true)
    Map<String,Object> getOsStatus(@Param("dealerCode") String poNumber,@Param("poid") Long poid);


}