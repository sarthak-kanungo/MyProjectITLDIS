package com.i4o.dms.itldis.service.machineinstallation.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.service.machineinstallation.domain.ServiceMachineInstallation;
import com.i4o.dms.itldis.service.machineinstallation.dto.DiSearchResponse;
import com.i4o.dms.itldis.service.machineinstallation.dto.DiViewHeaderResponse;

public interface ServiceMachineInstallationRepository extends JpaRepository<ServiceMachineInstallation, Long> {

   @Query(value = "{call sp_service_mi_dropdown_representative_type()}",nativeQuery = true)
   List<Map<String, Object>> getRepresentativeTypeDropdown();

   @Query(value = "{call sp_service_mi_autocomplete_installation_no(:installationNo,:usercode)}",nativeQuery = true)
   List<Map<String, Object>> FindByInstallationNumberContaining(@Param("installationNo") String installationNo,@Param("usercode")String usercode);

   @Query(value = "{call sp_service_mi_autocomplete_service_staff_name(:serviceStaffName,:dealerId)}",nativeQuery = true)
   List<Map<String, Object>> FindByServiceStaffNameContaining(@Param("serviceStaffName") String serviceStaffName,
                                                              @Param("dealerId")Long dealerId);

   @Query(value = "{call sp_service_mi_search(:chassisNo,:installationNumber,:installationType,:fromDate,:toDate,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size,:usercode,:includeInactive,:orgHierId)}",nativeQuery = true)
   List<DiSearchResponse> searchDi(@Param("chassisNo") String chassisNo,
                                   @Param("installationNumber")String installationNumber,
                                   @Param("installationType") String installationType,
                                   @Param("fromDate") String fromDate,
                                   @Param("toDate") String toDate,
                                   @Param("dealerId") Long dealerId,
                                   @Param("dealerEmployeeId") Long dealerEmployeeId,
                                   @Param("kubotaEmployeeId") Long kubotaEmployeeId,
                                   @Param("managementAccess") Boolean managementAccess,
                                   @Param("page") Integer page,
                                   @Param("size") Integer size,
                                   @Param("usercode") String usercode,
                                   @Param("includeInactive") Character includeInactive,
                                   @Param("orgHierId") Long orgHierId);

   @Query(value = "{call sp_service_mi_findByDealerIdAndMachineId(:dealerId,:machineId)}", nativeQuery = true)
   Long findByMachineMasterAndDealerId(@Param("dealerId") Long dealerId, @Param("machineId") Long machineId);

   @Query(value = "{call sp_service_mi_view_header_data(:diId)}", nativeQuery = true)
   DiViewHeaderResponse diViewGetHeaderData(@Param("diId") Long diId);
   //photo
   @Query(value = "{call sp_service_mi_photo_list_by_machine_installation_id(:diId)}", nativeQuery = true)
   List<Map<String, Object>> getMachineInstallationPhotoList(@Param("diId") Long diId);

   @Query(value = "{call sp_service_mi_view_aggregate_checkpoint(:diId)}", nativeQuery = true)
   List<Map<String,Object>> diViewGetCheckpointListByDiId(@Param("diId") Long diId);

   @Query(value = "{call sp_service_mi_autocomplete_chassis_no(:branchId,:chassisNo)}",nativeQuery = true)
   List<Map<String,Object>> findByChassisNoContaining(@Param("branchId") Long branchId,
                                                      @Param("chassisNo") String chassisNo);

   @Query(value = "{call sp_service_mi_get_chassis_no_details(:chassisNo,:branchId)}",nativeQuery = true)
   Map<String,Object> getDetailsByChassisNo(@Param("chassisNo") String chassisNo,@Param("branchId") Long branchId);

   @Query(value = "{call sp_mt_kdm_autocomplete_getAvailableCsb(:dealerId,:csbNo,:model)}",nativeQuery = true)
   List<Map<String,Object>> findByCsbNoContaining(@Param("dealerId") Long dealerId,
                                                  @Param("csbNo") String csbNo,
                                                  @Param("model") String model);

   @Query(value = "{call sp_service_mi_autocomplete_chassis_no_for_search(:usercode,:chassisNo)}",nativeQuery = true)
   List<Map<String,Object>> findByChassisNoContainingForSearch(@Param("usercode") String usercode,
                                                               @Param("chassisNo") String chassisNo);

   @Query(value = "{call sp_service_mi_check_chassisno_in_draft(:chassisNo,:branchId)}",nativeQuery = true)
   Map<String,Object> checkChassisNumberInDraft(@Param("chassisNo") String chassisNo,@Param("branchId") Long branchId);

}

//sp_service_mi_autocomplete_chassis_no_for_search