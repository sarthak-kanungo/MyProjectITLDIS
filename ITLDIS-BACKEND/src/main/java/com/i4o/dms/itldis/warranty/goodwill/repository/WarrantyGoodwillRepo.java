package com.i4o.dms.itldis.warranty.goodwill.repository;

import com.i4o.dms.itldis.warranty.goodwill.domain.WarrantyGoodwill;
import com.i4o.dms.itldis.warranty.goodwill.dto.GoodwillSearchResponse;
import com.i4o.dms.itldis.warranty.goodwill.dto.GoodwillViewDto;
import com.i4o.dms.itldis.warranty.pcr.dto.WarrantyPcrResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface WarrantyGoodwillRepo extends JpaRepository<WarrantyGoodwill,Long> {

    @Query(value = "{call sp_warranty_mt_dropdown_price_type()}", nativeQuery = true)
    List<Map<String,Object>> dropDownPriceType();

    @Query(value = "{call sp_warranty_goodwill_search(:management,:kaiUserId,:dealerId,:dealerEmployeeId,:goodwillNo,"
    		+ ":pcrNo,:jobcardNo,:status,:goodwillFromDate,:goodwillToDate,:machineModel,:chassisNo,"
    		+ ":failureType,:mobileNo,:registrationNo,:jobCardFromDate,:jobCardToDate,"
    		+ ":page,:size,:orgHier,:userCode,:includeInactive,:stateId, :branchId)}", nativeQuery = true)
    List<GoodwillSearchResponse> goodwillSearch(@Param("management") Boolean management,
                                                @Param("kaiUserId") Long kaiUserId,
                                                @Param("dealerId") Long dealerId,
                                                @Param("dealerEmployeeId") Long dealerEmployeeId,
                                                @Param("goodwillNo") String goodwillNo,
                                                @Param("pcrNo") String pcrNo,
                                                @Param("jobcardNo") String jobcardNo,
                                                @Param("status") String status ,
                                                @Param("goodwillFromDate") String goodwillFromDate,
                                                @Param("goodwillToDate") String goodwillToDate,
                                                @Param("machineModel") String machineModel,
                                                @Param("chassisNo") String chassisNo,
                                                @Param("failureType") String failureType,
                                                @Param("mobileNo") String mobileNo,
                                                @Param("registrationNo") String registrationNo,
                                                @Param("jobCardFromDate") String jobCardFromDate,
                                                @Param("jobCardToDate") String jobCardToDate,
                                                @Param("page") Long page,
                                                @Param("size") Long size,
                                                @Param("orgHier")Long orgHier,
                                                @Param("userCode")String userCode,
                                                @Param("includeInactive") String includeInactive,
                                                @Param("stateId") Long stateId,
                                                @Param("branchId") Long branchId
                                                );

   
    GoodwillViewDto findByGoodwillNo(String goodwillNo);

    @Query(value = "{call sp_warranty_goodwill_get_part_info_view(:goodwill)}", nativeQuery = true)
    List<Map<String,Object>> getJobCardPartWarrantyInfo(@Param("goodwill")String goodwill);


    @Query(value = "{call sp_warranty_mt_drop_down_goodwill_type()}", nativeQuery = true)
    List<Map<String,Object>> dropDownGoodwillType();

    @Query(value = "{call sp_warranty_mt_drop_down_goodwill_status()}", nativeQuery = true)
    List<Map<String,Object>> dropDownGoodwillStatus();
    

    @Query(value = "select wcr_no from WA_WCR where warranty_gwl_id=:goodwillId", nativeQuery = true)
    String getWcrNo(@Param("goodwillId")Long goodwillId);
    
    
    @Query(value = "{call sp_warranty_goodwill_auto_complete_goodwill_no(:goodwillNo,:usercode)}", nativeQuery = true)
    List<Map<Object,String>> searchGoodwillNo(@Param("goodwillNo") String goodwillNo, @Param("usercode")String usercode);

}
