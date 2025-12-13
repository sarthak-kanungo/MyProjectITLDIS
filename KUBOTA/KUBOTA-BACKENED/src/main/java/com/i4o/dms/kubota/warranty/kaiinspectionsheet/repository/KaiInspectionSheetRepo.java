package com.i4o.dms.kubota.warranty.kaiinspectionsheet.repository;

import com.i4o.dms.kubota.warranty.kaiinspectionsheet.domain.KaiInspectionSheet;
import com.i4o.dms.kubota.warranty.kaiinspectionsheet.dto.KaiInspectionSheetResponseDto;
import com.i4o.dms.kubota.warranty.kaiinspectionsheet.dto.KaiInspectionSheetView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface KaiInspectionSheetRepo extends JpaRepository<KaiInspectionSheet,Long> {


    @Query(value = "{call sp_warranty_kai_inspection_sheet_search(:inspectionNo,:fromDate,:toDate,:page,:size, :username, :wcrNo)}", nativeQuery = true)
    List<KaiInspectionSheetResponseDto> kaiInspectionSheetSearch(@Param("inspectionNo")String inspectionNo,
                                                                     @Param("fromDate")String fromDate,
                                                                     @Param("toDate")String dcFromDate,
                                                                     @Param("page")Long page,
                                                                     @Param("size")Long size,
                                                                     @Param("username")String username,
                                                                     @Param("wcrNo")String wcrNo);

    KaiInspectionSheetView findByInspectionNo(String inspectionNo);


    @Query(value = "{call sp_warranty_inspection_get_part_info(:wcrNo,:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getJobCardPartWarrantyInfo(@Param("wcrNo") String wcrNo, @Param("dealerId") Long dealerId);
    
    @Query(value = "{call sp_warranty_inspection_get_labour_info(:wcrNo,:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getLabourChargeInfo(@Param("wcrNo") String wcrNo, @Param("dealerId") Long dealerId);

    
    @Query(value = "{call sp_warranty_inspection_get_outsideCharge_info(:wcrNo,:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getOutsideChargeInfo(@Param("wcrNo") String wcrNo, @Param("dealerId") Long dealerId);


    @Query(value = "{call sp_warranty_inspection_dc_wcr_details(:wcrNo,:dealerId)}", nativeQuery = true)
    Map<String,Object> getWcrDcDetails(@Param("wcrNo") String wcrNo, @Param("dealerId") Long dealerId);


    @Query(value = "{call sp_warranty_mt_drop_down_failure_unit()}", nativeQuery = true)
    List<Map<String,Object>> dropDownFailureUnit();

    @Query(value = "{call sp_warranty_mt_drop_down_failure_mode()}", nativeQuery = true)
    List<Map<String,Object>> dropDownFailureMode();
    
    @Query(value = "{call sp_warranty_autocomplete_inspection_no(:inspectionNo, :userCode)}", nativeQuery = true)
    List<Map<String,Object>> autoCompleteInspectionNo(@Param("inspectionNo")String inspectionNo, @Param("userCode")String userCode);
    
    @Query(value = "{call sp_warranty_autocomplete_inspection_wcr_no(:wcrNo, :userCode)}", nativeQuery = true)
    List<Map<String,Object>> autoCompleteWcrNo(@Param("wcrNo")String wcrNo, @Param("userCode")String userCode);
    
    @Query(value = "{call sp_warranty_mt_drop_down_type_of_use()}", nativeQuery = true)
    List<Map<String,Object>> dropDownTypeOfUse();


}
