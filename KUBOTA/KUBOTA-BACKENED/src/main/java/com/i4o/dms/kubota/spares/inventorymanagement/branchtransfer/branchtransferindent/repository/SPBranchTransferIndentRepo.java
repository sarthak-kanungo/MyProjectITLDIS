package com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferindent.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferindent.domain.SPBranchTransferIndent;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferindent.dto.BTIndentSearchResponseDto;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferindent.dto.BTIndentViewDto;

/**
 * @author suraj.gaur
 */
@Repository
@Transactional
public interface SPBranchTransferIndentRepo extends JpaRepository<SPBranchTransferIndent, Long>{
	
	@Query(value = "select top 1 id, branch_code branchCode, branch_name branchName from ADM_BRANCH_MASTER where id = :branchId", nativeQuery = true)
	Map<String, Object> getReqToBranchDeatilsById(@Param("branchId") Long branchId);
	
	@Query(value = "select id, dealer_id dealerId, employee_code employeeCode from ADM_DEALER_EMP where id = :empId", nativeQuery = true)
	Map<String, Object> getEmployeeDetailsById(@Param("empId") Long empId);
	
	@Query(value = "select id, status from SP_BRANCH_TRANSFER_STATUS", nativeQuery = true)
	List<Map<String, Object>> getAllStatus();
	
	@Query(value = "{call SP_AUTO_GET_BTI_NO(:indentNo)}", nativeQuery = true)
	List<Map<String, Object>> autoGetIndentNo(@Param("indentNo") String indentNo);
	
	@Query(value = "{call SP_SUB_BRANCH_DTLS_BY_ID(:branchId)}", nativeQuery = true)
	List<Map<String, Object>> findSubBranchByBranchId(@Param("branchId") Long branchId);
	
	@Query(value = "{call SP_GET_SPARE_ITEM_DTLS(:itemNo, :reqByBranchId, :suppliedByBranchId)}", nativeQuery = true)
	Map<String, Object> getSpareItemDetails(
			@Param("itemNo") String itemNo, 
			@Param("reqByBranchId") Long reqByBranchId,
			@Param("suppliedByBranchId") Long suppliedByBranchId);
	
	@Query(value = "{call SP_BTINDENT_GET_ITEM_DETAILS_BY_EXCEL(:items, :qtys, :existingItems, :reqByBranchId, :suppliedByBranchId)}", nativeQuery = true)
    List<Map<String, Object>> getItemDetailsByExcel(
    		@Param("items") String items, 
    		@Param("qtys") String qtys,
    		@Param("existingItems") String existingItems,
    		@Param("reqByBranchId") Long reqByBranchId, 
    		@Param("suppliedByBranchId") Long suppliedByBranchId);
	
	@Query(value = "{call sp_branch_transfer_indent_search(:indentNo, :fromDate, :toDate, :status, :page, :size)}", nativeQuery = true)
	List<BTIndentSearchResponseDto> searchBTIndent(
			@Param("indentNo") String indentNo,
			@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,
			@Param("status") String status,
			@Param("page") Integer page,
			@Param("size") Integer size);
	
	BTIndentViewDto findByReqNo(String reqNo);
	
	@Query(value = "{call SP_BT_INDENT_AUTOCOMPLETE_PART_NUMBER(:itemNumber, :dealerId)}", nativeQuery = true)
	List<Map<String, Object>> autoCompleteItemNumber(@Param("itemNumber") String itemNumber, @Param("dealerId")Long dealerId);
	
}
