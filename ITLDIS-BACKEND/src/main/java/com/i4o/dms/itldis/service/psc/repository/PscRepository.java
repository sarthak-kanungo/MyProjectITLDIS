package com.i4o.dms.itldis.service.psc.repository;


import com.i4o.dms.itldis.service.psc.domain.ServicePsc;
import com.i4o.dms.itldis.service.psc.dto.PscSearchResponse;
import com.i4o.dms.itldis.service.psc.dto.PscViewHeaderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PscRepository extends JpaRepository<ServicePsc, Long> {


    @Query(value = "{call sp_service_psc_findByDealerIdAndMachineId(:branchId,:machineId)}",nativeQuery = true)
    Long findByMachineMasterAndDealerId(Long branchId, Long machineId);

    @Query(value="{call sp_service_psc_autocomplete_chassis_no(:branchId,:chassisNo)}",nativeQuery = true)
    List<Map<String, Object>> getAutoCompleteChassisNumber(@Param("branchId") Long branchId,
                                                           @Param("chassisNo") String chassisNo
    );
    @Query(value = "{call sp_service_psc_get_chassis_details(:chassisNo)}",nativeQuery = true)
    Map<String, Object> getDetailsByChassisNo(@Param("chassisNo") String chassisNo);

    @Query(value = "{call sp_service_psc_autocomplete_psc_no(:pscNo,:usercode)}",nativeQuery = true)
    List<Map<String, Object>> getPscNoContaining(@Param("pscNo") String pscNo,@Param("usercode") String usercode);

    @Query(value = "{call sp_service_psc_search(:chassisNo,:pscNo,:fromDate,:toDate,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size,:userCode,:includeInactive,:hierId)}",nativeQuery = true)
    List<PscSearchResponse> searchPsc(@Param("chassisNo") String chassisNo,
                                      @Param("pscNo") String pscNo,
                                      @Param("fromDate") String fromDate,
                                      @Param("toDate") String toDate,
                                      @Param("dealerId") Long dealerId,
                                      @Param("dealerEmployeeId") Long dealerEmployeeId,
                                      @Param("kubotaEmployeeId") Long kubotaEmployeeId,
                                      @Param("managementAccess") Boolean managementAccess,
                                      @Param("page") Integer page,
                                      @Param("size") Integer size,
                                      @Param("userCode") String userCode,
                                      @Param("includeInactive") Character includeInactive,
                                      @Param("hierId") Long hierId);

    @Query(value = "{call sp_service_psc_view_header_data(:pscId)}", nativeQuery = true)
    PscViewHeaderResponse pscViewGetHeaderData(@Param("pscId") Long pscId);

    @Query(value = "{call sp_service_psc_view_aggregate_checkpoints(:pscId)}", nativeQuery = true)
    List<Map<String, Object>> pscViewGetCheckpointListById(@Param("pscId") Long pscId);

    @Query(value="{call sp_service_psc_autocomplete_chassis_no_for_search(:usercode,:chassisNo)}",nativeQuery = true)
    List<Map<String, Object>> getAutoCompleteChassisNumberForSearch(@Param("usercode") String usercode,
                                                                    @Param("chassisNo") String chassisNo
    );
}


