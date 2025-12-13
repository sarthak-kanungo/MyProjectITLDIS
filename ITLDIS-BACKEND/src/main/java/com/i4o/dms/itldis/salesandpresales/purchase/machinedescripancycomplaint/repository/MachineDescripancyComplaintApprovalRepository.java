package com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.domain.MachineDescripancyComplaintApproval;

public interface MachineDescripancyComplaintApprovalRepository extends JpaRepository<MachineDescripancyComplaintApproval,Long> {

	@Query(value = "{call sp_machine_discripency_complaint_get_approval_hierarchy_level(:dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getApprovalHierarchyLevel(@Param("dealerId") Long dealerId);
	
	@Transactional
    @Query(value = "{call sp_machine_discripency_complaint_approve_claim(:claimId,:houserId,:remark, :usercode, :approvalStatus)}", nativeQuery = true)
    String approveClaim(@Param("claimId") Long claimId, 
						@Param("houserId") Long userId,
                        @Param("remark") String remark,
                        @Param("usercode") String usercode,
                        @Param("approvalStatus") String approvalStatus);
    
    @Query(value="{call sp_machine_discripency_complaint_getApprovalHierarchyDetails(:Id,:userCode)}",nativeQuery = true)
    List<Map<String,Object>> getApprovalHierDetails(@Param("Id") Long Id, @Param("userCode") String userCode);

}
