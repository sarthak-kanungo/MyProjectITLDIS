package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.domain.SPBranchTransferIssue;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.BTIssueSearchResponseDto;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.BTIssueViewDto;
/**
 * @author suraj.gaur
 */

@Repository
@Transactional
public interface SPBranchTransferIssueRepo extends JpaRepository<SPBranchTransferIssue, Long> {
	
	@Query(value = "{call sp_branch_indent_issue_branch(:branchId)}", nativeQuery = true)
	List<Map<String, Object>> getIssueToBranchDetails(@Param("branchId") Long branchId);
	
	@Query(value = "select top 1 id, branch_code branchCode, branch_name branchName from ADM_BRANCH_MASTER where id= :branchId", nativeQuery = true)
	Map<String, Object> getIssueingBranch(@Param("branchId") Long branchId);
	
	@Query(value = "{call SP_GET_INDENT_NOS_BY_BRANCH_ID(:reqFromBranchId, :reqToBranchId)}", nativeQuery = true)
	List<Map<String, Object>> getIndentNos(@Param("reqFromBranchId") Long reqFromBranchId, @Param("reqToBranchId") Long reqToBranchId);

	@Query(value = "{call SP_AUTO_COMPLETE_ISSUE_NO(:issueNo)}", nativeQuery = true)
	List<Map<String, Object>> autoGetIssueNo(@Param("issueNo") String issueNo);
	
	@Query(value = "{call SP_GET_INEDNT_ITEMS_DETAILS_BY_IDS(:indentIds, :branchId)}", nativeQuery = true)
	List<Map<String, Object>> getIndentItemsDetailsByIds(@Param("indentIds") String indentIds, @Param("branchId") Long branchId);
	
	BTIssueViewDto findByIssueNo(String issueNo);
	
	@Query(value = "{call SP_GET_iNDENT_NOS(:indentNo)}", nativeQuery = true)
	List<Map<String, Object>> autoGetIndentNo(String indentNo);
	
	@Modifying
    @Query(value = "{call SP_UPDATE_STOCKS_AFTER_BT_ISSUE(:issueId, :userId)}", nativeQuery = true)
	List<Map<String,Object>> updateStockTable(@Param("issueId") Long issueId, @Param("userId") Long userId);
	
	@Query(value = "{call sp_branch_transfer_issue_search(:issueNo, :status, :fromDate, :toDate, :page, :size)}", nativeQuery = true)
	List<BTIssueSearchResponseDto> searchBTIssue(
			@Param("issueNo") String issueNo,
			@Param("status") String status,
			@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,
			@Param("page") Integer page,
			@Param("size") Integer size);
	
	@Query(value = "{call SP_GET_SELECTED_INDENT_NOS(:issueId)}", nativeQuery = true)
	List<Map<String, Object>> getSelectedIndentNos(@Param("issueId") Long issueId);
	
}