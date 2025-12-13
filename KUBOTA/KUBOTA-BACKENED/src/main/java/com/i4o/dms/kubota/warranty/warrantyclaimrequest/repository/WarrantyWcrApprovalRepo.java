package com.i4o.dms.kubota.warranty.warrantyclaimrequest.repository;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.domain.WarrantyWcrApproval;

public interface WarrantyWcrApprovalRepo extends JpaRepository<WarrantyWcrApproval,Long> {

	@Query(value = "{call sp_warranty_wcr_get_approval_hierarchy_level(:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getWarrantyWcrApprovalHierarchyLevel(@Param("dealerId") Long dealerId);

    @Transactional
    @Query(value = "{call sp_warranty_wcr_approve_wcr(:warrantyWcrId,:houserId,:remark, :usercode, :approvalStatus)}", nativeQuery = true)
    String approveWcr(@Param("warrantyWcrId") Long warrantyWcrId, 
    										@Param("houserId") Long userId,
                                            @Param("remark") String remark,
                                            @Param("usercode") String usercode,
                                            @Param("approvalStatus") String approvalStatus);
    
    @Query(value="{call sp_warranty_wcr_getApprovalHierarchyDetails(:Id,:userCode)}",nativeQuery = true)
    List<Map<String,Object>> getApprovalHierDetails(@Param("Id") Long Id, @Param("userCode") String userCode);
    
}
