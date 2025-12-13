package com.i4o.dms.kubota.warranty.pcr.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.kubota.connection.ConnectionConfiguration;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcr;
import com.i4o.dms.kubota.warranty.pcr.dto.JobCardHistoryDto;
import com.i4o.dms.kubota.warranty.pcr.dto.JobCardPartDto;
import com.i4o.dms.kubota.warranty.pcr.dto.JobCardViewDto;
import com.i4o.dms.kubota.warranty.pcr.dto.WarrantyPcrDetailsExcelResponse;
import com.i4o.dms.kubota.warranty.pcr.dto.WarrantyPcrResponseDto;
import com.i4o.dms.kubota.warranty.pcr.dto.WarrantyPcrSummaryExcelResponse;
import com.i4o.dms.kubota.warranty.pcr.dto.WarrantyPcrViewDto;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.PcrWarrantyClaimDto;

@Transactional
@Repository
public interface WarrantyPcrRepo extends JpaRepository<WarrantyPcr,Long>, ConnectionConfiguration {

	@Query(value = "select d.email_id, d.mobile_no, d.dealer_code from ADM_BRANCH_MASTER b inner join ADM_DEALER_MASTER d on b.dealer_id = d.id where b.id=:branchId", nativeQuery = true)
	Map<String,Object> getDealerDetails(@Param("branchId")Long branchId);
	
    @Query(value = "{call sp_mt_dropdown_field_condition()}", nativeQuery = true)
    List<Map<String, Object>> dropDownFieldCondition();

    @Query(value = "{call sp_mt_dropdown_failure_type(:serviceJobcardId)}", nativeQuery = true)
    List<Map<String, Object>> dropDownFailureType(@Param("serviceJobcardId")Long serviceJobcardId);

    @Query(value = "{call sp_mt_dropdown_crop_condition()}", nativeQuery = true)
    List<Map<String, Object>> dropDownCropCondition();

    @Query(value = "{call sp_warranty_pcr_job_card_data_for_pcr(:id)}", nativeQuery = true)
    JobCardViewDto jobCardForPcr(@Param("id") Long id);

    @Query(value = "{call sp_warranty_period_detail(:chassisNo)}", nativeQuery = true)
    Map<String,Object> getWarrantyPeriodDetail(@Param("chassisNo")String chassisNo);
    
    @Query(value = "{call sp_warranty_pcr_get_part_info(:id)}", nativeQuery = true)
    List<JobCardPartDto> jobCardPartDetailsForPcr(@Param("id") Long id);

    @Query(value = "{call sp_warranty_job_card_history_for_pcr(:id)}", nativeQuery = true)
    List<JobCardHistoryDto> jobCardHistoryForPcr(@Param("id") Long id);

    @Modifying
    @Query(value="update wa_pcr set status='Open', allow_video_upload=0, dealer_remarks=:dealerRemarks, last_modified_date=getdate(), last_modified_by=:userid where id=:id",nativeQuery=true)
    Integer updatePcr(@Param("userid") Long userid, @Param("id") Long id, @Param("dealerRemarks") String dealerRemarks);
    
    @Modifying
    @Query(value="update wa_pcr set status='Open', last_modified_date=getdate(), last_modified_by=:userid, special_apv_remarks=:remark where id=:id",nativeQuery=true)
    Integer updatePcrStatus(@Param("userid") Long userid, @Param("id") Long id, @Param("remark")String remark);
    
    
    @Modifying
    @Query(value="update wa_pcr set goodwill_flag=1, sales_goodwill_flag=0,goodwill_enable_by_ho=:hoId,goodwill_enable_on=getdate(), last_modified_date=getdate(), last_modified_by=:userid where id=:id",nativeQuery=true)
    Integer enableServiceGoodWill(@Param("hoId") Long hoId, @Param("userid") Long userid, @Param("id") Long id);
    
    @Modifying
    @Query(value="update wa_pcr set goodwill_flag=0, sales_goodwill_flag=1,goodwill_enable_by_ho=:hoId,goodwill_enable_on=getdate(), last_modified_date=getdate(), last_modified_by=:userid where id=:id",nativeQuery=true)
    Integer enableSalesGoodWill(@Param("hoId") Long hoId, @Param("userid") Long userid, @Param("id") Long id);

    @Query(value = "{call sp_warranty_search_pcr(:management,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:pcrNo,:status,:pcrFromDate,:pcrToDate,:jobCardNo,"
    		+ ":chassisNo,:engineNo,:jobCardFromDate,:jobCardToDate,:partno,:product,:series,:machineModel,:subModel,:variant,:page,:size,:includeInactive,:orgHierarchyId,:usercode,:branch,:state)}", nativeQuery = true)
    List<WarrantyPcrResponseDto> warrantyPcrSearch(@Param("management") Boolean management,
                                                   @Param("dealerId") Long dealerId,
                                                   @Param("kaiEmployeeId") Long kaiEmployeeId,
                                                   @Param("dealerEmployeeId") Long dealerEmployeeId,
                                                   @Param("pcrNo") String pcrNo,
                                                   @Param("status") String status,
                                                   @Param("pcrFromDate") String pcrFromDate,
                                                   @Param("pcrToDate") String pcrToDate,
                                                   @Param("jobCardNo") String jobCardNo,
                                                   @Param("chassisNo") String chassisNo,
                                                   @Param("engineNo") String engineNo,
                                                   @Param("jobCardFromDate") String jobCardFromDate,
                                                   @Param("jobCardToDate") String jobCardToDate,
                                                   @Param("partno") String partno,
                                                   @Param("product") String product,
                                                   @Param("series") String series,
                                                   @Param("machineModel") String machineModel,
                                                   @Param("subModel") String subModel,
                                                   @Param("variant") String variant,
                                                   @Param("page") Integer page,
                                                   @Param("size") Integer size,
	                                               @Param("includeInactive") Character includeInactive,
	                                               @Param("orgHierarchyId") Long orgHierarchyId,
	                                               @Param("usercode") String usercode,
	                                               @Param("branch") Long branch,
	                                               @Param("state") Long state);
    
    @Query(value = "{call sp_warranty_PCR_Excel_Report(:management,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:pcrNo,:status,:pcrFromDate,:pcrToDate,:jobCardNo,"
    		+ ":chassisNo,:engineNo,:jobCardFromDate,:jobCardToDate,:partno,:product,:series,:machineModel,:subModel,:variant,:branchId,:includeInactive,:orgHierarchyId,:usercode,:flag,:state)}", nativeQuery = true)
    List<WarrantyPcrSummaryExcelResponse> warrantyreport(@Param("management") Boolean management,
                                                   @Param("dealerId") Long dealerId,
                                                   @Param("kaiEmployeeId") Long kaiEmployeeId,
                                                   @Param("dealerEmployeeId") Long dealerEmployeeId,
                                                   @Param("pcrNo") String pcrNo,
                                                   @Param("status") String status,
                                                   @Param("pcrFromDate") String pcrFromDate,
                                                   @Param("pcrToDate") String pcrToDate,
                                                   @Param("jobCardNo") String jobCardNo,
                                                   @Param("chassisNo") String chassisNo,
                                                   @Param("engineNo") String engineNo,
                                                   @Param("jobCardFromDate") String jobCardFromDate,
                                                   @Param("jobCardToDate") String jobCardToDate,
                                                   @Param("partno") String partno,
                                                   @Param("product") String product,
                                                   @Param("series") String series,
                                                   @Param("machineModel") String machineModel,
                                                   @Param("subModel") String subModel,
                                                   @Param("variant") String variant,
                                                   @Param("branchId") Long branchId,
	                                               @Param("includeInactive") Character includeInactive,
	                                               @Param("orgHierarchyId") Long orgHierarchyId,
	                                               @Param("usercode") String usercode,
	                                               @Param("flag") String flag,
	                                               @Param("state") Long state);
    
    @Query(value = "{call sp_warranty_PCR_Excel_Report(:management,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:pcrNo,:status,:pcrFromDate,:pcrToDate,:jobCardNo,"
    		+ ":chassisNo,:engineNo,:jobCardFromDate,:jobCardToDate,:partno,:product,:series,:machineModel,:subModel,:variant,:branchId,:includeInactive,:orgHierarchyId,:usercode,:flag,:state)}", nativeQuery = true)
    List<WarrantyPcrDetailsExcelResponse> warrantyReportDetails(@Param("management") Boolean management,
                                                   @Param("dealerId") Long dealerId,
                                                   @Param("kaiEmployeeId") Long kaiEmployeeId,
                                                   @Param("dealerEmployeeId") Long dealerEmployeeId,
                                                   @Param("pcrNo") String pcrNo,
                                                   @Param("status") String status,
                                                   @Param("pcrFromDate") String pcrFromDate,
                                                   @Param("pcrToDate") String pcrToDate,
                                                   @Param("jobCardNo") String jobCardNo,
                                                   @Param("chassisNo") String chassisNo,
                                                   @Param("engineNo") String engineNo,
                                                   @Param("jobCardFromDate") String jobCardFromDate,
                                                   @Param("jobCardToDate") String jobCardToDate,
                                                   @Param("partno") String partno,
                                                   @Param("product") String product,
                                                   @Param("series") String series,
                                                   @Param("machineModel") String machineModel,
                                                   @Param("subModel") String subModel,
                                                   @Param("variant") String variant,
                                                   @Param("branchId") Long branchId,
	                                               @Param("includeInactive") Character includeInactive,
	                                               @Param("orgHierarchyId") Long orgHierarchyId,
	                                               @Param("usercode") String usercode,
	                                               @Param("flag") String flag,
	                                               @Param("state") Long state);

    @Query(value = "{call sp_warranty_pcr_dropdown_pcr_no(:pcrNo,:usercode)}", nativeQuery = true)
    List<Map<String,Object>> autoCompletePcrNo(@Param("pcrNo") String pcrNo, @Param("usercode") String usercode);

    WarrantyPcrViewDto findByPcrNo(String PcrNo);
    
    @Query(value = "{call sp_is_create_wcr(:docNo, :type)}", nativeQuery = true)
    Boolean isCreateWcr(String docNo, String type);

    PcrWarrantyClaimDto findByPcrNoAndId(String pcrNo, Long id);

    @Query(value = "select top 1 wcr_no from WA_WCR where warranty_pcr_id=:pcrId order by id desc", nativeQuery = true)
    String getWcrNo(@Param("pcrId")Long pcrId);
    
    @Query(value = "{call sp_warranty_pcr_get_jobcode_info(:jobcardId)}", nativeQuery = true)
    List<Map<String,Object>> getJobCodeInfo(@Param("jobcardId") Long jobcardId);
    
    @Query(value = "{call sp_warranty_pcr_get_files_info(:jobcardId)}", nativeQuery = true)
    List<Map<String,Object>> getFilesInfo(@Param("jobcardId") Long jobcardId);

    @Query(value = "{call sp_warranty_pcr_get_labour_info(:jobcardId)}", nativeQuery = true)
    List<Map<String,Object>> getLobourChargeInfo(@Param("jobcardId") Long jobcardId);

    @Query(value = "{call sp_warranty_pcr_get_part_info(:jobcardId)}", nativeQuery = true)
    List<Map<String,Object>> getJobCardPartWarrantyInfo(@Param("jobcardId") Long jobcardId);

    
    @Query(value = "{call sp_warranty_goodwill_get_part_info(:jobcardId)}", nativeQuery = true)
    List<Map<String,Object>> getJobCardPartWarrantyInfoGoodwill(@Param("jobcardId") Long jobcardId);

    @Query(value = "{call sp_warranty_goodwill_get_jobcode_info(:jobcardId)}", nativeQuery = true)
    List<Map<String,Object>> getJobCodeInfoGoodwill(@Param("jobcardId") Long jobcardId);

    @Query(value = "{call sp_warranty_goodwill_get_labour_info(:jobcardId)}", nativeQuery = true)
    List<Map<String,Object>> getLobourChargeInfoGoodwill(@Param("jobcardId") Long jobcardId);
    
    /*@Query(value = "{call sp_warranty_pcr_get_part_info_to_update_pcr(:jobcardId)}", nativeQuery = true)
    List<Map<String,Object>> getJobCardPartForUpdatePcr(@Param("jobcardId") Long jobcardId);
*/

    @Query(value = "{call sp_warranty_pcr_update_qty_update_flag(:jobcardId)}", nativeQuery = true)
    String updateQtyUpdateFlag(@Param("jobcardId") Long jobcardId);

    @Query(value = "{call sp_warranty_drop_down_pcr_status()}",nativeQuery = true)
    List<Map<String,Object>> dropDownPcrStatus();


    @Query(value = "{call sp_warranty_goodwill_enable(:pcrid,:usercode)}",nativeQuery = true)
    Map<String,Object> warrantyGoodwillEnable(@Param("pcrid") Long pcrid, @Param("usercode")String usercode);
    
    
    @Query(value = "{call sp_get_pcr_num(:jobCardId)}",nativeQuery = true)	//Suraj 11-10-2022
    Map<String,Object> getPcrNumberByJobCardId(@Param("jobCardId") Long jobCardId);
    
    
    @Query(value = "{call sp_pcr_pending_for_approval(:usercode)}", nativeQuery = true)
    List<Map<String, Object>> getPcrPendingForApproval(@Param("usercode")String userCode);
}
