package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferreciept.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferreciept.domain.SPBranchTransferReceipt;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferreciept.dto.BTReceiptSearchResponseDto;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferreciept.dto.BTReceiptViewDto;

@Repository
@Transactional
public interface SPBranchTransferReceiptRepo extends JpaRepository<SPBranchTransferReceipt, Long> {
	
	@Query(value = "{call SP_AUTO_GET_ISSUE_NO_IN_RECEIPT(:issuedToBranchId, :issueNo)}", nativeQuery = true)
	List<Map<String, Object>> autoGetIssueNoInReceipt(@Param("issuedToBranchId") Long issuedToBranchId, @Param("issueNo") String issueNo);
	
	@Query(value = "select bti.issued_by_branch_id id, abm.branch_name branchName "
			+ "from SP_BRANCH_TRANSFER_ISSUE As bti "
			+ "INNER JOIN ADM_BRANCH_MASTER AS abm "
			+ "ON issued_by_branch_id = abm.id where bti.id = :issueId", nativeQuery = true)
	Map<String, Object> getIssueingBranchName(@Param("issueId") String issueId);
	
	@Query(value = "select id, branch_name branchName from ADM_BRANCH_MASTER where id= :branchId", nativeQuery = true)
	Map<String, Object> getReceivingBranch(@Param("branchId") Long branchId);	
	
	@Query(value = "{call sp_branch_transfer_receipt_search(:receiptNo, :status, :fromDate, :toDate, :page, :size)}", nativeQuery = true)
	List<BTReceiptSearchResponseDto> searchBTReceipt(
			@Param("receiptNo") String receiptNo,
			@Param("status") String status,
			@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,
			@Param("page") Integer page,
			@Param("size") Integer size);
	
	
	@Query(value = "{call SP_AUTO_GET_BTR_NO(:branchId, :receiptNo)}", nativeQuery = true)
	List<Map<String, Object>> autoPopulateReceiptNo(
			@Param("branchId") Long branchId,
			@Param("receiptNo") String receiptNo);
	
	@Query(value = "{call SP_BT_RECEIPT_ITEM_DETAILS(:issueId)}", nativeQuery = true)
	List<Map<String, Object>> getReceiptItemDetails(@Param("issueId") Long issueId);
	
	BTReceiptViewDto findByReceiptNo(String receiptNo);

	@Modifying
    @Query(value = "{call SP_UPDATE_STOCKS_AFTER_BT_RECEIPT(:receiptId, :userId)}", nativeQuery = true)
    void updateStockTable(@Param("receiptId") Long receiptId, @Param("userId") Long userId);
	
	
	@Query(value = "{call SP_AUTO_COMPLETE_RECEIPT_ISSUE_NO(:branchId, :issueNo)}", nativeQuery = true)
	List<Map<String, Object>> autoPopulateIssueNo(
			@Param("branchId") Long branchId,
			@Param("issueNo") String issueNo);
	
}
