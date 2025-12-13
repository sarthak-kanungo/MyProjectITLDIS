package com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.domain.SpPartDiscrepancyClaimHdr;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.dto.DiscrepancyClaimSearchResponseDto;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.dto.DiscrepancyClaimViewDto;

@Transactional
public interface SpPartDiscrepancyClaimHdrRepo extends JpaRepository<SpPartDiscrepancyClaimHdr, Long>{
	
	@Query(value = "{call SP_AUTO_GET_DISC_CLAIM_NO(:claimNoString)}", nativeQuery = true)
	List<Map<String, Object>> autoGetDiscrepancyClaimNo(@Param("claimNoString") String claimNoString);
	
	@Query(value = "{call SP_AUTO_GET_DISCREPANCY_GRN_NO(:descrClaimType, :grnStr, :branchId)}", nativeQuery = true)
	List<Map<String, Object>> autoGetDiscrepancyGrnNo(@Param("descrClaimType") String descrClaimType, 
			@Param("grnStr") String grnStr,
			@Param("branchId") Long branchId);

	@Query(value = "{call SP_PART_DISCREPANCY_CLAIM_HIERARCHY_LEVEL()}", nativeQuery = true)
	List<Map<String, Object>> discrepancyClaimHierarchyLevel();
	
	@Query(value = "{call SP_GET_DISCREPANCY_ITEMS_BY_GRN_ID(:grnId)}", nativeQuery = true)
	List<Map<String, Object>> getDiscrepancyItems(@Param("grnId") Long grnId);
	
	@Query(value = "{call SP_GET_GRN_HDR_DETAILS_BY_GRN_ID(:grnId)}", nativeQuery = true)
	Map<String, Object> getHeaderGrnDetails(@Param("grnId") Long grnId);

	@Query(value = "{call SP_GET_GRN_ITEMS_NO(:grnId, :itemStr)}", nativeQuery = true)
	List<Map<String, Object>> autoGetGrnItemsNo(Long grnId, String itemStr);

	@Query(value = "{call SP_GET_DESC_ITEM_DETAIL_BY_ITEMS_NO(:grnId, :itemNo)}", nativeQuery = true)
	Map<String, Object> getGrnItemDiscrDetails(Long grnId, String itemNo);

	@Query(value = "{call SP_SEARCH_DISCREPANCY_CLAIM_MRR(:claimNo,:claimType,:claimStatus,:grnNo,:invoiceNo,"
			+ ":dealerEmployeeId,:fromDate,:toDate,:page,:size,:userCode,:includeInactive)}", nativeQuery = true)
	List<DiscrepancyClaimSearchResponseDto> searchDiscrepancyClaim(
			@Param("claimNo") String claimNo,
			@Param("claimType") String claimType,
			@Param("claimStatus") String claimStatus,
			@Param("grnNo") String grnNo,
			@Param("invoiceNo") String invoiceNo,
			@Param("dealerEmployeeId") Long dealerEmployeeId,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("page") Integer page,
			@Param("size") Integer size,
			@Param("userCode") String userCode,
			@Param("includeInactive") Character includeInactive);

	DiscrepancyClaimViewDto findByClaimReqNo(String discrClaimNo);
	
	@Query(value = "{call SP_DISCREPANCY_CLAIM_VIEW(:discrClaimId, :flag)}", nativeQuery = true)
	List<Map<String, Object>> getDiscrepancyAttachNKaiRemark(Long discrClaimId, String flag);
	
	@Query(value = "{call SP_SPARE_DISCREPANCY_MMR_CLAIM_APPROVAL(:claimId,:hoUserId,:remark,:userCode,:approvalStatus)}", nativeQuery = true)
	String claimApproveOrReject(@Param("claimId") Long claimId,
			@Param("hoUserId") Long hoUserId,
			@Param("remark") String remark,
			@Param("userCode") String userCode, 
			@Param("approvalStatus")String approvalStatus);
	
	@Query(value = "{call SP_UPDATE_STOCK_AFTER_DISCR_CLAIM(:claimId, :userId)}", nativeQuery = true)
	Map<String, Object> updateStockAfterDiscrepacnyClaim(
			@Param("claimId") Long claimId,
			@Param("userId") Long userId);
	
}
