package com.i4o.dms.kubota.warranty.warrantyclaimrequest.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.warranty.pcr.dto.WarrantyPcrDetailsExcelResponse;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.domain.WarrantyWcr;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.ViewWcrReportDto;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.WarrantyWcrExcelSummaryResponse;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.WarrantyWcrResponse;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.WarrantyWcrView;

@Transactional
public interface WarrantyWcrRepo extends JpaRepository<WarrantyWcr,Long> {

    @Query(value = "{call sp_warranty_wcr_search(:management,:kaiUserId,:dealerId,:dealerEmployeeId,:wcrNo,:wcrType,:wcrStatus,:pcrNo,:jobCardNo,:chassisNo,:wcrFromDate,:wcrToDate,:model,:failureType,:mobileNo,:registrationNo,:jobCardFromDate,:jobCardToDate,:pcrFromDate,:pcrToDate,:page,:size,:orgHierarchyId, :userCode, :includeInactive, :branchId, :stateId, :finalStatus)}", nativeQuery = true)
    List<WarrantyWcrResponse> searchWcr(
            @Param("management")Boolean management,
            @Param("kaiUserId")Long kaiUserId,
            @Param("dealerId")Long dealerId,
            @Param("dealerEmployeeId")Long dealerEmployeeId,
            @Param("wcrNo") String wcrNo,
            @Param("wcrType") String wcrType,
            @Param("wcrStatus") String wcrStatus,
            @Param("pcrNo") String pcrNo,
            @Param("jobCardNo") String jobCardNo,
            @Param("chassisNo") String chassisNo,
            @Param("wcrFromDate") String wcrFromDate,
            @Param("wcrToDate") String wcrToDate,
            @Param("model") String model,
            @Param("failureType") String failureType,
            @Param("mobileNo") String mobileNo,
            @Param("registrationNo") String registrationNo,
            @Param("jobCardFromDate") String jobCardFromDate,
            @Param("jobCardToDate") String jobCardToDate,
            @Param("pcrFromDate") String pcrFromDate,
            @Param("pcrToDate") String pcrToDate,
            @Param("page") Long page,
            @Param("size") Long size,
            @Param("orgHierarchyId") Long orgHierarchyId,
            @Param("userCode") String userCode,
            @Param("includeInactive") Character includeInactive ,
            @Param("branchId") Long branchId, 
            @Param("stateId") Long stateId,
            @Param("finalStatus") Long finalStatus
    );
    
    @Query(value = "{call sp_Warranty_WCR_Excel_Report(:management,:kaiUserId,:dealerEmployeeId,:dealerId,"
    		+ ":wcrNo,:jobCardNo,:chassisNo,:wcrFromDate,:wcrToDate,:model,:product,:variant,:series,"
    		+ ":subModel,:orgHierarchyId, :userCode, :includeInactive,:flag, :branchId, :stateId,:status)}", nativeQuery = true)
    List<WarrantyWcrExcelSummaryResponse> wcrExcelSummary(
            @Param("management")Boolean management,
            @Param("kaiUserId")Long kaiUserId,
            @Param("dealerEmployeeId")Long dealerEmployeeId,
            @Param("dealerId")Long dealerId,
            @Param("wcrNo") String wcrNo,
            @Param("jobCardNo") String jobCardNo,
            @Param("chassisNo") String chassisNo,
            @Param("wcrFromDate") String wcrFromDate,
            @Param("wcrToDate") String wcrToDate,
            @Param("model") String model,
            @Param("product") String product,
            @Param("variant") String variant,
            @Param("series") String series,
            @Param("subModel") String subModel,
            @Param("orgHierarchyId") Long orgHierarchyId,
            @Param("userCode") String userCode,
            @Param("includeInactive") Character includeInactive,
            @Param("flag") String flag,
            @Param("branchId") Long branchId, @Param("stateId") Long stateId, @Param("status")String status
    );
    
    @Query(value = "{call sp_Warranty_WCR_Excel_Report(:management,:kaiUserId,:dealerEmployeeId,:dealerId,"
    		+ ":wcrNo,:jobCardNo,:chassisNo,:wcrFromDate,:wcrToDate,:model,:product,:variant,:series,"
    		+ ":subModel,:orgHierarchyId, :userCode, :includeInactive,:flag, :branchId, :stateId,:status)}", nativeQuery = true)
    List<WarrantyPcrDetailsExcelResponse> wcrExcelDetails(
            @Param("management")Boolean management,
            @Param("kaiUserId")Long kaiUserId,
            @Param("dealerEmployeeId")Long dealerEmployeeId,
            @Param("dealerId")Long dealerId,
            @Param("wcrNo") String wcrNo,
            @Param("jobCardNo") String jobCardNo,
            @Param("chassisNo") String chassisNo,
            @Param("wcrFromDate") String wcrFromDate,
            @Param("wcrToDate") String wcrToDate,
            @Param("model") String model,
            @Param("product") String product,
            @Param("variant") String variant,
            @Param("series") String series,
            @Param("subModel") String subModel,
            @Param("orgHierarchyId") Long orgHierarchyId,
            @Param("userCode") String userCode,
            @Param("includeInactive") Character includeInactive,
            @Param("flag") String flag,
            @Param("branchId") Long branchId, @Param("stateId") Long stateId,@Param("status")String status
    );

    @Query(value = "{call sp_warranty_wcr_dropdown_status(:dealerId)}", nativeQuery = true)
    List<Map<Object,String>> searchStatus(@Param("dealerId")Long dealerId);

    @Query(value = "{call sp_warranty_wcr_dropdown_wcr_type()}", nativeQuery = true)
    List<Map<Object,String>> searchWcrType();

    @Modifying
    @Query(value = "update WA_WCR set final_status=:status, modified_by=:loginid where id=:id", nativeQuery = true)
    void updateStatus(@Param("status")Long status,
    		@Param("loginid")Long loginid,
    		@Param("id")Long id);
    
    
    @Query(value = "{call sp_warranty_wcr_report(:wcrType,:status,:fromDate,:toDate,:model,:machineNo,:partNo,:dealerCode,:state,:creditNoteReqNo,:dealerName,:branchId)}", nativeQuery = true)
    List<ViewWcrReportDto> wcrReport(@Param("wcrType") String wcrType,
                                     @Param("status") String status,
                                     @Param("fromDate") String fromDate,
                                     @Param("toDate") String toDate,
                                     @Param("model") String model,
                                     @Param("machineNo") String machineNo,
                                     @Param("partNo") String partNo,
                                     @Param("dealerCode") String dealerCode,
                                     @Param("state") String state,
                                     @Param("creditNoteReqNo") String creditNoteReqNo,
                                     @Param("dealerName") String dealerName,
                                     @Param("branchId") Long branchId);

    WarrantyWcrView findByWcrNo(String wcrNo);

    @Modifying
    @Query(value = "update WA_WCR set wcr_status='Received', received_by=:userid, received_on=getdate(), modified_by=:userid, modified_on=getdate() where id=:wcrId", nativeQuery=true)
    void markWcrReceived(@Param("userid")Long userid, @Param("wcrId")Long id);
    
    @Query(value = "{call sp_warranty_wcr_auto_complete_wcr_no(:wcrNo,:usercode)}", nativeQuery = true)
    List<Map<Object,String>> searchWcrNo(@Param("wcrNo") String wcrNo, @Param("usercode")String usercode);

    @Query(value = "{call sp_warranty_wcr_get_part_info(:pcrNo, :wcrType)}", nativeQuery = true)
    List<Map<String,Object>> getJobCardPartWarrantyInfo(@Param("pcrNo") String  pcrNo, @Param("wcrType") String wcrType);

    @Query(value = "{call sp_warranty_wcr_get_labour_info(:pcrNo, :wcrType)}", nativeQuery = true)
    List<Map<String,Object>> getLabourCharge(@Param("pcrNo") String pcrNo, @Param("wcrType") String wcrType);

    @Query(value = "{call sp_warranty_wcr_get_jobcode_info(:pcrNo, :wcrType)}", nativeQuery = true)
    List<Map<String,Object>> getOutSideLabourCharge(@Param("pcrNo") String pcrNo, @Param("wcrType") String wcrType);
    
    @Query(value = "{call sp_warranty_wcr_is_show_inspection(:wcrNo,:usercode)}", nativeQuery = true)
    Boolean getInspectionBtn(@Param("wcrNo") String wcrNo, @Param("usercode")String usercode);
    
    @Query(value = "{call sp_warranty_wcr_invoice_type(:wcrNo,:usercode)}", nativeQuery = true)
    Map<String,Boolean> getInvoiceType(@Param("wcrNo") String wcrNo, @Param("usercode")String usercode);

//    @Modifying
//    @Query(value="Update WA_WCR set file_name=:fileName, wcr_status='Invoice Uploaded', modified_on=getdate(), modified_by=:userId, invoice_uploaded_by=:userId, invoice_uploaded_on=getdate(), manual_invoice_no=:invoiceNo, manual_invoice_date=:invoiceDate where id=:id",nativeQuery = true)
//    void uploadInvoice(@Param("fileName") String fileName, @Param("id") Integer id, @Param("invoiceNo") String invoiceNo, @Param("invoiceDate") Date invoiceDate, @Param("userId")Long userId);
    @Modifying
    @Query(value = "{call SV_INVOICE_UPLOAD_VARIFY(:wcrId, :invoiceNo, :invoiceDate, :userId, :wcrStatus, :fileName)}", nativeQuery = true)
    void uploadInvoice(@Param("wcrId") Integer wcrId, @Param("invoiceNo") String invoiceNo, @Param("invoiceDate") Date invoiceDate, @Param("userId")Long userId, @Param("wcrStatus") String wcrStatus, @Param("fileName") String fileName);
    
    @Modifying
    @Query(value="Update WA_WCR set file_name=:fileName, wcr_status='Invoice Uploaded', modified_on=getdate(), modified_by=:userId, invoice_uploaded_by=:userId, invoice_uploaded_on=getdate() where id=:id",nativeQuery = true)
    void KaiUploadInvoice(@Param("fileName") String fileName, @Param("id") Integer id, @Param("userId")Long userId);

//    @Modifying
//    @Query(value = "update WA_WCR set wcr_status='Invoice Verified', invoice_verified_by=:userid, invoice_verified_on=getdate(), modified_by=:userid, modified_on=getdate(), manual_invoice_no=:invoiceNo, manual_invoice_date=:invoiceDate where id=:wcrId", nativeQuery=true)
    @Modifying
    @Query(value = "{call SV_INVOICE_UPLOAD_VARIFY(:wcrId, :invoiceNo, :invoiceDate, :userId, :wcrStatus, :fileName)}", nativeQuery = true)
    void markAsInvoiceVerified(@Param("wcrId") Integer wcrId, @Param("invoiceNo") String invoiceNo, @Param("invoiceDate") Date invoiceDate, @Param("userId")Long userId, @Param("wcrStatus") String wcrStatus, @Param("fileName") String fileName);
    
    @Query(value="select file_name from WA_WCR where id=:id", nativeQuery=true)
    String getInvoiceFileName(@Param("id")Long id);
    
    @Query(value="{call sp_get_final_status()}", nativeQuery=true)
    List<Map<String,Object>> getFinalStatus();
    
    @Query(value="select top 1 manual_invoice_no invoiceNo, manual_invoice_date invoiceDate from SP_SALES_INVOICE where warranty_wcr_id=:wcrId", nativeQuery=true)
    Map<String,Object> getInvoiceDetail(@Param("wcrId")Long wcrId);
    
}
