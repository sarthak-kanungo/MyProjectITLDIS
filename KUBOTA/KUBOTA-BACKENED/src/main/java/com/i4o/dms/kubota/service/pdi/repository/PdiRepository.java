package com.i4o.dms.kubota.service.pdi.repository;

import com.i4o.dms.kubota.service.pdi.domain.ServicePdi;
import com.i4o.dms.kubota.service.pdi.dto.PdiSearchResponse;
import com.i4o.dms.kubota.service.pdi.dto.PdiViewHeaderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PdiRepository extends JpaRepository<ServicePdi, Long> {

    @Query(value = "{call sp_service_pdi_findByDealerIdAndMachineId(:dealerId,:machineId)}", nativeQuery = true)
    Long findByMachineMasterAndDealerId(@Param("dealerId") Long dealerId, @Param("machineId") Long machineId);

    @Query(value = "{call sp_service_pdi_search_autocomplete_chassis_number(:searchString,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> searchAutocompleteChassisNumber(@Param("searchString") String searchString, @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_service_pdi_autocomplete_kai_invoice_number(:kaiInvoiceNumber,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> searchKaiInvoiceNumber(@Param("kaiInvoiceNumber") String kaiInvoiceNumber, @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_service_pdi_autocomplete_grn_number(:dmsGrnNumber,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> searchDmsGrnNumber(@Param("dmsGrnNumber") String dmsGrnNumber, @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_service_pdi_get_view_header_data(:pdiId)}", nativeQuery = true)
    PdiViewHeaderResponse pdiViewGetHeaderData(@Param("pdiId") Long pdiId);

    @Query(value = "{call sp_service_pdi_get_aggergate_checkpoint_list_by_pdiId(:pdiId)}", nativeQuery = true)
    List<Map<String, Object>> pdiViewGetCheckpointListByPdiId(@Param("pdiId") Long pdiId);

    @Query(value = "{call sp_service_pdi_get_chassis_checkpoint_info_list(:pdiId)}", nativeQuery = true)
    List<Map<String, Object>> pdiViewGetChassisCheckPointList(@Param("pdiId") Long pdiId);

    @Query(value = "{call sp_service_pdi_search(:pdiId,:pdiFromDate,:pdiToDate,:kaiInvoiceNumber,:dmsGrnNumber,:dmsGrnFromDate,:dmsGrnToDate,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size,:userCode,:includeInactive,:hierId)}", nativeQuery = true)
    List<PdiSearchResponse> pdiSearch(@Param("pdiId") Long pdiId,
                                      @Param("pdiFromDate") String pdiFromDate,
                                      @Param("pdiToDate") String pdiToDate,
                                      @Param("kaiInvoiceNumber") String kaiInvoiceNumber,
                                      @Param("dmsGrnNumber") String dmsGrnNumber,
                                      @Param("dmsGrnFromDate") String dmsGrnFromDate,
                                      @Param("dmsGrnToDate") String dmsGrnToDate,
                                      @Param("dealerId") Long dealerId,
                                      @Param("dealerEmployeeId") Long dealerEmployeeId,
                                      @Param("kubotaEmployeeId") Long kubotaEmployeeId,
                                      @Param("managementAccess") Boolean managementAccess,
                                      @Param("page") Integer page,
                                      @Param("size") Integer size,
                                      @Param("userCode") String userCode,
                                      @Param("includeInactive") Character includeInactive,
                                      @Param("hierId") Long hierId);

}
