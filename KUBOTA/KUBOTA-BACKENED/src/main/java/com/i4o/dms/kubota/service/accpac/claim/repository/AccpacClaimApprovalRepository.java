package com.i4o.dms.kubota.service.accpac.claim.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.kubota.service.accpac.claim.model.AccpacClaimAprvlHdrEntity;
import com.i4o.dms.kubota.service.accpac.claim.model.AccpacClaimAprvlSearchDto;

@Transactional
public interface AccpacClaimApprovalRepository extends JpaRepository<AccpacClaimAprvlHdrEntity, Long>{

	@Query(value="{call SP_CLAIM_PENDING_FOR_APPROVAL (:requestFrom,:userCode,:claimNo,:pcrNumber,:wcrType,:jobcardNumber,:fromDate,:toDate,:dealerId)}",nativeQuery=true)
	public List<Map<String, Object>> claimSearchForApproval(@Param("requestFrom")String requestFrom, @Param("userCode")String userCode,
			@Param("claimNo")String claimNo,
			@Param("pcrNumber")String pcrNumber,
			@Param("wcrType")String wcrType,
			@Param("jobcardNumber")String jobcardNumber,
			@Param("fromDate")String fromDate,
			@Param("toDate")String toDate,
			@Param("dealerId")Long dealerId);
	
	@Query(value="{call SP_ACC_PAC_CLAIM_APPROVAL_SEARCH (:fromDate,:toDate,:claimType,:claimStatus,:page,:size,:userCode)}",nativeQuery=true)
	public List<AccpacClaimAprvlSearchDto> searchClaimSubmittedApproval(@Param("fromDate")String fromDate, 
			@Param("toDate")String toDate, 
			@Param("claimType")String claimType, 
			@Param("claimStatus")String claimStatus, 
			@Param("page")Integer page,
			@Param("size")Integer size,
			@Param("userCode")String userCode);
	
	@Modifying
	@Query(value = "{call sp_accpac_claim_get_approval_hierarchy(:claimType,:id)}", nativeQuery = true)
	public void insertClaimApprovalHierarchyLevel(@Param("claimType")String claimType, @Param("id") Long id);
	
    
    @Query(value = "{call sp_accpac_claim_approval(:claimType,:claimId,:houserId,:remark,:usercode,:approvalStatus)}", nativeQuery = true)
    public String claimApproval(@Param("claimType")String claimType, @Param("claimId") Long claimId, @Param("houserId") Long houserId,
                                            @Param("remark") String remark, @Param("usercode") String usercode, @Param("approvalStatus")String approvalStatus);

	@Query(value="{call SP_accpac_view_claim (:requestFrom,:id)}",nativeQuery=true)
    public List<Map<String, Object>> viewManagementApprovedClaim(@Param("requestFrom")String requestFrom, @Param("id")Long id);
	
	/**
	 * @author suraj.gaur
	 */
	@Query(value="{call SP_WARRANTY_CLAIM_MGNT_getApprovalHierarchyDetails(:id)}",nativeQuery=true)
	public List<Map<String, Object>> getApprovalHierarchyDetails(@Param("id") Long id);
}
