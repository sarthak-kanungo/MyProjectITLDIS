package com.i4o.dms.itldis.warranty.deliverychallan.repository;

import com.i4o.dms.itldis.warranty.deliverychallan.domain.WarrantyDeliveryChallan;
import com.i4o.dms.itldis.warranty.deliverychallan.dto.WarrantyDcResponseDto;
import com.i4o.dms.itldis.warranty.deliverychallan.dto.WarrantyDeliveryChallanViewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface WarrantyDeliveryChallanRepo extends JpaRepository<WarrantyDeliveryChallan,Long> {
    @Query(value = "{call sp_warranty_dc_get_wcr_for_dc(:ids)}", nativeQuery = true)
    List<Map<String,Object>> getClaimPartInDc(@Param("ids") String ids);

    @Query(value = "{call sp_warranty_delivery_challan_search(:management,:kaiUserId,:dealerId,:dealerEmployeeId,:dcNo,:wcrNo,:dcFromDate,:dcToDate,:machineModel,:wcrFromDate,:wcrToDate,:page,:size, :orgHierId, :userCode, :includeInactive)}", nativeQuery = true)
    List<WarrantyDcResponseDto> deliveryChallanSearch( @Param("management")Boolean management ,
                                                       @Param("kaiUserId")Long kaiUserId,
                                                       @Param("dealerId")Long dealerId,
                                                       @Param("dealerEmployeeId")Long dealerEmployeeId,
                                                       @Param("dcNo")String dcNo ,
                                                       @Param("wcrNo")String wcrNo,
                                                       @Param("dcFromDate")String dcFromDate,
                                                       @Param("dcToDate")String dcToDate,
                                                       @Param("machineModel")String machineModel,
                                                       @Param("wcrFromDate")String wcrFromDate,
                                                       @Param("wcrToDate")String wcrToDate,
                                                       @Param("page")Long page,
                                                       @Param("size")Long size,
                                                       @Param("orgHierId")Long orgHierId,
                                                       @Param("userCode")String userCode,
                                                       @Param("includeInactive")Character includeInactive);

    WarrantyDeliveryChallanViewDto findByDcNo(String dcNo);
    
    @Query(value = "{call sp_warranty_dc_get_dealer_info(:branchId)}", nativeQuery = true)
    Map<String,Object> getDealerDetails(Long branchId);

    @Query(value = "{call sp_warranty_dc_get_part_info(:dcNo,:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getJobCardPartWarrantyInfo(@Param("dcNo") String dcNo, @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_warranty_get_delivery_challan_count(:wcrId)}", nativeQuery = true)
    String getDeliveryChallanCount(@Param("wcrId") Long wcrId);


}

