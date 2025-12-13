package com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.domain.SparePOApproval;


@Repository
public interface SparePurchaseOrderApprovalRepository extends JpaRepository<SparePOApproval, Long>  {

	@Query(value = "{call sp_spares_purchase_get_approval_hierarchy_level(:dealerId,:poid)}",nativeQuery = true)
    List<Map<String,Object>>  getApprovalHierarchyLevel(@Param("dealerId")Long dealerId, @Param("poid")Long poid);
    
	@Query(value = "{call sp_spares_purchase_approve_order(:poid, :kaiEmpId, :remark, :userCode, :approvalStatus)}",nativeQuery = true)
	String poApproval(Long poid, Long kaiEmpId, String remark, String userCode, String approvalStatus);
	
	@Query(value = "{call sp_spares_purchase_get_approval_details(:poId,:userId)}",nativeQuery = true)
	List<Map<String,Object>> getSparesPoApprovalDetails(@Param("poId") Long poId, @Param("userId") Long userId);

}
