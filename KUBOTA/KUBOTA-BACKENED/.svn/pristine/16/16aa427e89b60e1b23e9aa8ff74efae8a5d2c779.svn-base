package com.i4o.dms.kubota.service.jobcard.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobCardReopenApproval;

public interface ServiceJobCardReopenApprovalRepo extends JpaRepository<ServiceJobCardReopenApproval,Long>{

    @Query(value = "{call sp_service_jobcard_reopen_approved(:dcId,:hoUserId,:remark,:usercode,:approvalStatus)}",nativeQuery = true)
    String approveJobcardReopen(@Param("dcId") Long dcId, 
    						  @Param("hoUserId") Long hoUserId,
                              @Param("remark") String remark, 
                              @Param("usercode") String usercode,
                              @Param("approvalStatus") String approvalStatus);
    
    @Query(value="{call sp_service_jobcard_reopen_getApprovalHierarchyDetails(:dcId,:userCode)}",nativeQuery = true)
    List<Map<String,Object>> getApprovalHierDetails(@Param("dcId") Long dcId, @Param("userCode") String userCode);
    
}
