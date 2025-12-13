package com.i4o.dms.itldis.salesandpresales.sales.invoice.repository;

import com.i4o.dms.itldis.salesandpresales.sales.invoice.domain.SalesInvoiceCancelApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SalesInvoiceCancelApprovalRepository extends JpaRepository<SalesInvoiceCancelApproval,Long> {
    @Query(value = "{call sp_sales_invoice_get_cancel_approval_hierarchy(:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getCancelApprovalHierarchy(@Param("dealerId") Long dealerId);
    
    @Query(value = "{call sp_sales_invoice_getApprovalHierarchyDetails(:invoiceId,:userCode)}", nativeQuery = true)
    List<Map<String,Object>> getApprovalHierarchy(@Param("invoiceId") Long invoiceId, @Param("userCode")String userCode);
    
    @Query(value = "{call sp_sales_invoice_approve_cancelled_invoice(:invoiceId,:houserId,:remark,:usercode,:flag)}", nativeQuery = true)
    String approveCancelInvoice(Long invoiceId,Long houserId,String remark,String usercode,String flag);
}
