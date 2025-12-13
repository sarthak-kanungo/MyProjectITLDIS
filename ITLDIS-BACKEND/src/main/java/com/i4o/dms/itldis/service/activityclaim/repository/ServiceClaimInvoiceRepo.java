package com.i4o.dms.itldis.service.activityclaim.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.service.activityclaim.domain.ServiceActivityClaim;
import com.i4o.dms.itldis.service.activityclaim.dto.DealerInvoiceSearchResponseDto;

@Transactional
public interface ServiceClaimInvoiceRepo extends JpaRepository<ServiceActivityClaim,Long>{

    @Query(value="{call sp_dealer_generate_invoice(:claimId, :invoiceType, :usercode)}",nativeQuery = true)
    Map<String, Object> generateDealerInvoice(@Param("claimId") Long claimId, @Param("invoiceType") String invoiceType, @Param("usercode") String usercode);
    
    @Query(value = "{call sp_dealer_claim_invoice_search(:invoiceNumber,:invoiceType,:fromDate,:toDate,:usercode,:dealerCode,:page,:size,:orgHierarchyId,'N',:claimNumber,:activityNumber)}",nativeQuery = true)
    List<DealerInvoiceSearchResponseDto> getDealerInvoiceList(
            @Param("invoiceNumber") String invoiceNumber,
            @Param("claimNumber") String claimNumber,
            @Param("invoiceType") String invoiceType,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("usercode") String usercode,
            @Param("dealerCode") String dealerCode,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("orgHierarchyId")Long orgHierarchyId,
            @Param("activityNumber") String activityNumber);
    
    @Query(value="{call sp_dealer_invoice_header_details(:invoiceId)}",nativeQuery = true)
    Map<String, Object> getInvoiceHeaderDetails(@Param("invoiceId") Long invoiceId);
   
    @Query(value="{call sp_dealer_invoice_item_details(:invoiceId)}",nativeQuery = true)
    List<Map<String, Object>> getInvoiceItemDetails(@Param("invoiceId") Long invoiceId);
    
    @Query(value="{call sp_dealer_invoice_number_auto_search(:searchVal, :invoiceType, :userCode)}",nativeQuery = true)
    List<Map<String, Object>> getInvoiceNumberSearch(@Param("searchVal")String searchVal, @Param("invoiceType")String invoiceType, @Param("userCode")String userCode);

    @Query(value="{call sp_dealer_claim_number_auto_search(:searchVal, :invoiceType, :userCode)}",nativeQuery = true)
    List<Map<String, Object>> getClaimNumberSearch(@Param("searchVal")String searchVal, @Param("invoiceType")String invoiceType, @Param("userCode")String userCode);
    
    @Query(value="{call sp_dealer_activity_number_auto_search(:searchVal, :invoiceType, :userCode)}",nativeQuery = true)
    List<Map<String, Object>> getActivityNumberSearch(@Param("searchVal")String searchVal, @Param("invoiceType")String invoiceType, @Param("userCode")String userCode);
    
    @Modifying
    @Query(value="Update dlr_claim_invoice_hdr set file_name=:fileName, file_type=:fileType, modified_on=getdate(), modified_by=:userId where dlr_invoice_hdr_id=:id",nativeQuery = true)
    void uploadInvoice(@Param("fileName") String fileName, @Param("fileType") String fileType, @Param("id") Integer invoiceId, @Param("userId")Long userId);
    
    @Query(value="select file_name from dlr_claim_invoice_hdr where dlr_invoice_hdr_id=:id", nativeQuery=true)
    String getInvoiceFileName(@Param("id")Long id);
    
    @Modifying
    @Query(value="{call SP_CLAIM_INVOICE_UPLOAD (:id, :userId, :ClaimStatus)}",nativeQuery = true)
    void invoiceStatusUpdate(@Param("id") Integer invoiceId, @Param("userId")Long userId, @Param("ClaimStatus") String ClaimStatus);
    
    @Modifying
    @Query(value="Update DLR_CLAIM_INVOICE_HDR set file_name=:fileName, file_type=:fileType, modified_on=getdate(), modified_by=:userId, manual_invoice_no=:invoiceNo, manual_invoice_date=:invoiceDate where dlr_invoice_hdr_id=:id",nativeQuery = true)
    void dealerUploadInvoice(@Param("fileName") String fileName, @Param("fileType") String fileType, @Param("id") Integer invoiceId, @Param("userId")Long userId, @Param("invoiceNo") String invoiceNo, @Param("invoiceDate") Date invoiceDate);
    
    @Modifying
    @Query(value="Update DLR_CLAIM_INVOICE_HDR set modified_on=getdate(), modified_by=:userId, manual_invoice_no=:invoiceNo, manual_invoice_date=:invoiceDate where dlr_invoice_hdr_id=:id",nativeQuery = true)
    void kaiUpdateInvoiceNoDate(@Param("id") Integer invoiceId, @Param("userId")Long userId, @Param("invoiceNo") String invoiceNo, @Param("invoiceDate") Date invoiceDate);
    
    @Query(value="select manual_invoice_no invoiceNo, manual_invoice_date invoiceDate from DLR_CLAIM_INVOICE_HDR where dlr_invoice_hdr_id=:invoiceId", nativeQuery=true)
    Map<String,Object> getInvoiceDetail(@Param("invoiceId")Long invoiceId);
    
}
