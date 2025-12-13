package com.i4o.dms.itldis.warranty.goodwill.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.warranty.goodwill.domain.WarrantyGoodwillApproval;

public interface WarrantyGoodwillApprovalRepo  extends JpaRepository<WarrantyGoodwillApproval,Long> {
	
    @Query(value = "{call sp_warranty_goodwill_get_approval_hierarchy_level(:dealerId,:pcrid)}", nativeQuery = true)
    List<Map<String,Object>> getWarrantyGoodwillApprovalHierarchyLevel(@Param("dealerId") Long dealerId,@Param("pcrid") Long pcrid);

    @Transactional
    @Query(value = "{call sp_warranty_goodwill_approve_pcr(:id,:houserId,:remark, :usercode, :approvalStatus,:budgetConsumed)}", nativeQuery = true)
    String approve(@Param("id") Long id, 
				@Param("houserId") Long userId,
                @Param("remark") String remark,
                @Param("usercode") String usercode,
                @Param("approvalStatus") String approvalStatus,
                @Param("budgetConsumed")Double budgetConsumed);
    
    @Query(value="{call sp_warranty_goodwill_getApprovalHierarchyDetails(:Id,:userCode)}",nativeQuery = true)
    List<Map<String,Object>> getApprovalHierDetails(@Param("Id") Long Id, @Param("userCode") String userCode);
    
}

