package com.i4o.dms.itldis.service.machinereinstallation.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.service.machinereinstallation.domain.ServiceMachineReinstallation;
import com.i4o.dms.itldis.service.machinereinstallation.dto.RiSearchResponse;
import com.i4o.dms.itldis.service.machinereinstallation.dto.RiViewHeaderResponse;

public interface ServiceMachineReinstallationRepository extends JpaRepository<ServiceMachineReinstallation, Long> {

    @Query(value = "{call sp_service_ri_search(:fromDate,:toDate,:series,:serviceStaffName,:reInstallationNumber,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size,:usercode,:includeInactive,:orgHierId)}",nativeQuery = true)
    List<RiSearchResponse> searchRi(@Param("fromDate") String fromDate,
                                    @Param("toDate") String toDate,
                                    @Param("series") String series,
                                    @Param("serviceStaffName") String serviceStaffName,
                                    @Param("reInstallationNumber") String reInstallationNumber,
                                    @Param("dealerId") Integer dealerId,
                                    @Param("dealerEmployeeId") Integer dealerEmployeeId,
                                    @Param("kubotaEmployeeId") Integer kubotaEmployeeId,
                                    @Param("managementAccess") Boolean managementAccess,
                                    @Param("page") Integer page,
                                    @Param("size") Integer size,
                                    @Param("usercode") String usercode,
                                    @Param("includeInactive") Character includeInactive,
                                    @Param("orgHierId") Long orgHierId);

   
    @Query(value = "{call sp_service_ri_autocomplete_installation_no(:reInstallationNumber,:usercode)}",nativeQuery = true)
    List<Map<String, Object>> FindByReInstallationNumberContaining(@Param("reInstallationNumber") String reInstallationNumber,@Param("usercode")String usercode);

    @Query(value = "{call sp_service_ri_get_chassis_no_details(:chassisNo,:usercode)}",nativeQuery = true)
    Map<String, Object> getDetailsByChassisNo(@Param("chassisNo") String chassisNo,@Param("usercode")String usercode);

    @Query(value = "{call sp_service_ri_autocomplete_chassis_no(:branchId,:chassisNo,:seriesId,:chassisId)}",nativeQuery = true)
    List<Map<String,Object>> findByChassisNoContaining(@Param("branchId") Long branchId,
                                                       @Param("chassisNo") String chassisNo,
                                                       @Param("seriesId") Long seriesId,
                                                        @Param("chassisId") String chassisId);


    @Query(value = "{call sp_service_ri_view_header_data(:riId)}", nativeQuery = true)
    RiViewHeaderResponse riViewGetHeaderData(@Param("riId") Long riId);

    @Query(value = "{call sp_service_ri_view_aggregate_checkpoint(:riId)}", nativeQuery = true)
    List<Map<String,Object>> riViewGetCheckpointListByDiId(@Param("riId") Long riId);

    @Query(value = "{call sp_service_ri_view_machine_details(:riId)}", nativeQuery = true)
    List<Map<String,Object>> riViewGetMachineDetailsListByDiId(@Param("riId") Long riId);

    @Query(value = "{call sp_service_ri_view_representative_details(:riId)}", nativeQuery = true)
    List<Map<String,Object>> riViewGetRepresentativeListByDiId(@Param("riId") Long riId);
}


