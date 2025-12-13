package com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.repository;

import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.domain.SalesPoApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SalesPoApprovalRepository extends JpaRepository<SalesPoApproval, Long> {

    @Query(value = "{call sp_sales_presales_purchase_get_all_active_sales_admin()}", nativeQuery = true)
    List<Long> getSalesAdmin();

    //List<SalesPoApproval> findByPurchaseOrderIdAndDesignationHierarchyIdAndFinalStatusIsNull(Long poId,Long hierarchyId);

    @Query(value = "{call sp_sales_presales_purchase_approve_po_by_designation(:poId,:houserId,:remark,:usercode, :approvalStatus,:current_os,:os0to30days,:os31to60days,:os61to90days,:os90days,:payment_pending,:net_os,"
    		+ ":pending_order,:order_under_process,:channel_finance_available,:exposure_amount,:total_credit_limit,:available_limit,:basicAmount,:totalGstAmount,:totalAmount,:isMngmtApprovalRequired)}",nativeQuery = true)
    String approvePoAccordingToDesignationLevel(@Param("poId") Long poId, @Param("houserId") Long houserId, @Param("remark") String remark,@Param("usercode")String usercode ,@Param("approvalStatus") String approvalStatus,
    		@Param("current_os") Double current_os,        
    		@Param("os0to30days") Double os0to30days,       
    		@Param("os31to60days") Double os31to60days,      
    		@Param("os61to90days") Double os61to90days,      
    		@Param("os90days") Double os90days,          
    		@Param("payment_pending") Double payment_pending,   
    		@Param("net_os") Double net_os,            
    		@Param("pending_order") Double pending_order,     
    		@Param("order_under_process") Double order_under_process,
    		@Param("channel_finance_available") Double channel_finance_available,  
    		@Param("exposure_amount") Double exposure_amount,            
    		@Param("total_credit_limit") Double total_credit_limit,         
    		@Param("available_limit") Double available_limit,
    		@Param("basicAmount") Double basicAmount,
    		@Param("totalGstAmount") Double totalGstAmount,
    		@Param("totalAmount") Double totalAmount,
    		@Param("isMngmtApprovalRequired") String isMngmtApprovalRequired);
    
    //SalesPoApproval findByPurchaseOrderIdAndKubotaEmployeeMasterIdAndFinalStatusIsNull(Long poId,Long kubotaUserId);

    @Query(value = "{call sp_sales_presale_purchase_get_approval_hierarchy_level(:dealerId,:poid)}",nativeQuery = true)
    List<Map<String,Object>>  getApprovalHierarchyLevel(@Param("dealerId")Long dealerId, @Param("poid")Long poid);
    
    @Query(value= "{call sp_sales_po_checkForAllApprovalButtons(:houserid,:poid)}",nativeQuery = true)
    Character checkForAllApprovalButtons(@Param("houserid")Long houserid, @Param("poid")Long poid);
}

