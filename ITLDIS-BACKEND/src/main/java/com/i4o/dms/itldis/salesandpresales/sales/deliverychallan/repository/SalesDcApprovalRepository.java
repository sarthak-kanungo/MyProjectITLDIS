package com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.domain.SalesDcApproval;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto.SearchDeliveryChallanCancelApproval;

public interface SalesDcApprovalRepository extends JpaRepository<SalesDcApproval,Long> {

    @Query(value = "{call sp_sales_dc_cancellation_approval_hierarchy(:dealerId)}",nativeQuery = true)
    List<Map<String,Object>> dcApprovalHierarchy(@Param("dealerId")Long dealerId);

    @Query(value = "{call sp_sales_dc_approve_cancelled_dc(:dcId,:hoUserId,:remark,:usercode,:approvalStatus)}",nativeQuery = true)
    String approveCancelledDC(@Param("dcId") Long dcId, 
    						  @Param("hoUserId") Long hoUserId,
                              @Param("remark") String remark, 
                              @Param("usercode") String usercode,
                              @Param("approvalStatus") String approvalStatus);
    
    @Query(value="{call sp_sales_dc_getApprovalHierarchyDetails(:dcId,:userCode)}",nativeQuery = true)
    List<Map<String,Object>> getApprovalHierDetails(@Param("dcId") Long dcId, @Param("userCode") String userCode);
    
    @Query(value = "{call sp_sales_dc_search_pendingForApproval(:usercode, :includeInactive, :orgHierId, :page, :size)}", nativeQuery = true)
    List<SearchDeliveryChallanCancelApproval> getDCListForApproval(@Param("usercode") String usercode, @Param("includeInactive") Character includeInactive,
            @Param("orgHierId") Long orgHierId, @Param("page")Integer page, @Param("size")Integer size);
}
