package com.i4o.dms.itldis.service.claim.repo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.itldis.service.claim.domain.ServiceClaimApprovalEntity;

@Transactional
public interface ServiceClaimApprovalRepo extends JpaRepository<ServiceClaimApprovalEntity, Long> {

	@Query(value = "{call sp_service_claim_get_approval_hierarchy(:dealerId)}", nativeQuery = true)
	List<Map<String,Object>> getClaimApprovalHierarchyLevel(@Param("dealerId") Long dealerId);
	
    @Transactional
    @Query(value = "{call sp_service_claim_approval(:claimId,:houserId,:remark,:usercode,:approvalStatus)}", nativeQuery = true)
    String claimApproval(@Param("claimId") Long claimId, @Param("houserId") Long houserId,
                                            @Param("remark") String remark, @Param("usercode") String usercode, @Param("approvalStatus")String approvalStatus);
    
   @Modifying 
   @Query(value = "update sv_claim_dtl set total_approved_amount = total_claim_amount, last_modified_date=:modifiedDate where id in (:ids) ", nativeQuery = true)
   void updateApprovedAmount(@Param("ids")List<Long> ids,@Param("modifiedDate")Date modifiedDate); 
   
   @Modifying 
   @Query(value = "update sv_claim_dtl set total_approved_amount = (case :selection when 0 then total_claim_amount else 0 end), is_rejected=:selection, rejection_reason = :reason, remark= :remark, last_modified_date=:modifiedDate where id =:id ", nativeQuery = true)
   void updateDtl(@Param("selection")Boolean selection, @Param("reason")String reason, @Param("remark")String remark, @Param("id")Integer id, @Param("modifiedDate")Date modifiedDate); 

}
