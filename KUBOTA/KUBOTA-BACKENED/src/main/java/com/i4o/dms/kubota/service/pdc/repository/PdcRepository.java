package com.i4o.dms.kubota.service.pdc.repository;

import com.i4o.dms.kubota.service.pdc.controller.PdcController;
import com.i4o.dms.kubota.service.pdc.domain.ServicePdc;
import com.i4o.dms.kubota.service.pdc.dto.PdcSearchResponse;
import com.i4o.dms.kubota.service.pdc.dto.PdcViewHeaderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.nio.file.LinkOption;
import java.util.List;
import java.util.Map;

public interface PdcRepository extends JpaRepository<ServicePdc,Long> {
    @Query(value = "{call sp_service_pdc_autocomplete_chassis_number(:chassisNo,:branchId)}",nativeQuery = true)
    List<Map<String,Object>> getChassisNumberAutoComplete(@Param("chassisNo") String chassisNo,@Param("branchId") Long branchId);

    @Query(value = "{call sp_service_pdc_getChassisDetailsByChassisNo(:chassisNo,:branchId)}",nativeQuery = true)
    Map<String,Object> getChassisDetailsByChassisNo(@Param("chassisNo") String chassisNo,@Param("branchId") Long branchId);

   /* @Query(value = "{call sp_service_pdc_get_aggregate_and_checkpoints(:model)}",nativeQuery = true)
    List<Map<String,Object>> getAggregateAndCheckPointByModel(@Param("model") String chassisNo);*/


    @Query(value = "{call sp_service_pdc_search(:managementAccess,:dealerEmployeeId,:dealerId,:kaiEmployeeId,:chassisNo,:pdcNo,:model,:fromPdcDate,:toPdcDate,:page,:size,:userCode,:includeInactive,:orgHierId)}",nativeQuery = true)
    List<PdcSearchResponse> pdcSearch(@Param("managementAccess") Boolean managementAccess,
                                      @Param("dealerEmployeeId")Long dealerEmployeeId,
                                      @Param("dealerId")Long dealerId,
                                      @Param("kaiEmployeeId")Long kaiEmployeeId,
                                      @Param("chassisNo")String chassisNo,
                                      @Param("pdcNo")String pdcNo,
                                      @Param("model")String model,
                                      @Param("fromPdcDate")String fromPdcDate,
                                      @Param("toPdcDate")String toPdcDate,
                                      @Param("page")Integer page,
                                      @Param("size")Integer size,
                                      @Param("userCode")String userCode,
                                      @Param("includeInactive")Character includeInactive,
                                      @Param("orgHierId")Long orgHierId);


    @Query(value = "{call sp_service_pdc_get_view_header_data(:pdcId)}",nativeQuery = true)
    PdcViewHeaderResponse getViewHeader(@Param("pdcId") Long pdcId);

    @Query(value = "{call sp_service_pdc_get_aggergate_checkpoint_list_by_pdcId(:pdcId)}",nativeQuery = true)
    List<Map<String,Object>> getAggregateCheckpointListByPdcId(@Param("pdcId") Long pdcId);

    @Query(value = "{call sp_service_pdc_isPdcCreated(:pdcId,:dealerId)}",nativeQuery = true)
    Boolean isPdcCreated(@Param("pdcId") Long pdcId,@Param("dealerId") Long dealerId);

    @Query(value = "{call sp_service_pdc_search_autocomplete_chassisNo(:chassisNo,:usercode)}",nativeQuery = true)
    List<Map<String,Object>> pdcCreateAutoCompleteChassisNo(@Param("chassisNo")String chassisNo,@Param("usercode")String usercode);

}





