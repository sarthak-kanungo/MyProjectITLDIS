package com.i4o.dms.kubota.warranty.pcr.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcrApproval;

public interface WarrantyPcrApprovalRepository extends JpaRepository<WarrantyPcrApproval,Long> {
    @Query(value = "{call sp_warranty_pcr_get_approval_hierarchy_level(:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getWarrantyPcrApprovalHierarchyLevel(@Param("dealerId") Long dealerId);

    @Query(value = "{call sp_warranty_pcr_get_special_approval_hierarchy_level(:usercode)}", nativeQuery = true)
    List<Map<String,Object>> getWarrantyPcrSpecialApprovalHierarchyLevel(@Param("usercode")String usercode);
    
    @Transactional
    @Query(value = "{call sp_warranty_pcr_approve_pcr(:warrantyPcrId,:houserId,:remark, :usercode, :approvalStatus, :allowVideoUpload, :managementCheck, :reason, :rating, :delayReason)}", nativeQuery = true)
    String approveMarketingActivityProposal(@Param("warrantyPcrId") Long warrantyPcrId, 
    										@Param("houserId") Long userId,
                                            @Param("remark") String remark,
                                            @Param("usercode") String usercode,
                                            @Param("approvalStatus") String approvalStatus,
                                            @Param("allowVideoUpload")Boolean allowVideoUpload,
                                            @Param("managementCheck") Boolean managementCheck,
                                            @Param("reason") String reason,
                                            @Param("rating") Integer rating,
                                            @Param("delayReason") String delayReason);
    
    @Query(value="{call sp_warranty_pcr_getApprovalHierarchyDetails(:Id,:userCode)}",nativeQuery = true)
    List<Map<String,Object>> getApprovalHierDetails(@Param("Id") Long Id, @Param("userCode") String userCode);
    
    List<WarrantyPcrApproval> findBywarrantyPcrId(Long id);
}
