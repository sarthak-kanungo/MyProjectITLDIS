package com.i4o.dms.kubota.service.mrc.repository;


import com.i4o.dms.kubota.service.mrc.domain.ServiceMrc;
import com.i4o.dms.kubota.service.mrc.dto.MachineSearchResponseDto;
import com.i4o.dms.kubota.service.mrc.dto.SearchResponseServiceMrc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface MrcRepository extends JpaRepository<ServiceMrc, Long> {

    @Query(value = "{call sp_service_mrc_autocomplete_accpac_invoice_number(:invoiceNumber,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getAutoCompleteAccpacInvoiceNumber(@Param("invoiceNumber") String invoiceNumber,
                                                                 @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_service_mrc_get_chassis_Info(:accpacInvoiceId,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getChassisNoByAccpacInvoice(@Param("accpacInvoiceId") Long accpacInvoiceId,
                                                          @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_formf_get_chassis_no(:searchValue,:userCode)}", nativeQuery = true)
    List<Map<String, Object>> getChassisNo(@Param("searchValue")String searchValue, @Param("userCode")String userCode);
    
    @Query(value = "{call sp_service_mrc_autocomplete_accpac_invoice_number_search(:invoiceNumber,:usercode)}", nativeQuery = true)
    List<Map<String, Object>> getAutoCompleteAccpacInvoiceNumberSearch(@Param("invoiceNumber") String invoiceNumber,
                                                          @Param("usercode") String usercode);
    

    @Query(value = "{call sp_service_mrc_search(:mrcno,:invoiceNo,:fromDate,:toDate,:ifromDate,:itoDate,:page,:size,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:usercode,:orgId)}", nativeQuery = true)
    List<SearchResponseServiceMrc> searchServiceMrc(
            @Param("mrcno") String mrcNo,
            @Param("invoiceNo") String invoiceNo,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("ifromDate") String ifromDate,
            @Param("itoDate") String itoDate,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("dealerId") Long dealerId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("kubotaEmployeeId") Long kubotaEmployeeId,
            @Param("managementAccess") Boolean managementAccess,
            @Param("usercode") String usercode,
            @Param("orgId") Long orgId
    );

    @Query(value = "{call sp_machine_formf_search(:machineNo,:page,:size,:usercode,:orgId)}", nativeQuery = true)
    List<MachineSearchResponseDto> machineFormFSearch(
            @Param("machineNo") String machineNo,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("usercode") String usercode,
            @Param("orgId") Long orgId);
    
    @Query(value = "{call sp_service_mrc_checkpoint_list_by_mrc_id(:mrcId)}", nativeQuery = true)
    List<Map<String, Object>> getMrcCheckpointListByMrcId(@Param("mrcId") Long mrcId);

    @Query(value = "{call sp_service_mrc_get_view_header_data(:mrcId,:dealerId)}", nativeQuery = true)
    Map<String, Object> getMrcHeaderData(@Param("mrcId") Long mrcId, @Param("dealerId")Long dealerId);

    @Query(value = "{call sp_service_mrc_photo_list_by_mrc_id(:mrcId)}", nativeQuery = true)
    List<Map<String, Object>> getMrcPhotoList(@Param("mrcId") Long mrcId);

    @Query(value = "{call sp_service_mrc_get_discrepancy_list_by_mrc_id(:mrcId)}", nativeQuery = true)
    List<Map<String, Object>> getMrcDiscrepancyList(@Param("mrcId") Long mrcId);

    @Query(value = "{call sp_service_mrc_findByDealerIdAndMachineId(:dealerId,:machineId)}", nativeQuery = true)
    Long findByMachineMasterAndDealerId(@Param("dealerId") Long dealerId,
                                        @Param("machineId") Long machineId);

    @Query(value = "{call sp_service_mrc_search_autocomplete(:searchString,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getMrcSearchForMrcListing(@Param("searchString") String searchString,
                                                        @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_service_mrc_get_chassis_number(:accpacInvoiceId)}", nativeQuery = true)
    List<Map<String, Object>> getChassisNumber(@Param("accpacInvoiceId") Long accpacInvoiceId);

    @Query(value = "{call sp_mrc_check_chassis_number(:chassisNo)}", nativeQuery = true)
    Map<String, Object> checkByChassisNo(@Param("chassisNo") Long chassisNo);

    @Modifying
    @Query(value = "{call sp_mrc_form_flag_update(:mrcNo,'Form-F')}", nativeQuery = true)
    void updateFormFDownloadStatus(@Param("mrcNo")String mrcNo);

    @Modifying
    @Query(value = "{call sp_mrc_form_flag_update(:mrcNo,'Form-22')}", nativeQuery = true)
    void updateForm22DownloadStatus(@Param("mrcNo")String mrcNo);
}
