package com.i4o.dms.kubota.accpac.repository;

import com.i4o.dms.kubota.accpac.domain.AccPacDealerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface AccPacDealerMasterRepository extends JpaRepository<AccPacDealerMaster,Long>
{

         @Query(value = "{call sp_accpac_dealer_master_dealer_code_auto(:dealerCode)}",nativeQuery = true)
         List<Map<String,Object>> findByDealerCodeContaining(@Param("dealerCode") String dealerCode);

         @Query(value="{call sp_accpac_dealer_get_details_by_dealer_code(:dealerCode)}",nativeQuery = true)
         List<Map<String,Object>> getDetailsByDealerCode(@Param("dealerCode") String dealerCode);

          @Query(value="{call sp_acc_pac_dealer_dealer_type_list()}",nativeQuery = true)
          List<Map<String,Object>> getDealerType();

}
