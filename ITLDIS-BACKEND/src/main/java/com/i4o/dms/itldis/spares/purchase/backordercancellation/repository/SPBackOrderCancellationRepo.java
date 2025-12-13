package com.i4o.dms.itldis.spares.purchase.backordercancellation.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.spares.purchase.backordercancellation.domain.SPBackOrderCancellation;
import com.i4o.dms.itldis.spares.purchase.backordercancellation.dto.SPBackOrderCancellationViewDto;
import com.i4o.dms.itldis.spares.purchase.backordercancellation.dto.SPBackOrderCancellationResponseDto;

/**
 * @author suraj.gaur
 */
@Repository
@Transactional
public interface SPBackOrderCancellationRepo extends JpaRepository<SPBackOrderCancellation, Long> {
	
	@Query(value = "select boc_no bocNo from SP_BACK_ORDER_CANCELLATION where boc_no like '%' + :bocNo + '%'", nativeQuery = true)
	List<Map<String, Object>> autoGetBOCNo(@Param("bocNo") String bocNo);

	@Query(value = "{call GET_BOC_DEALER_CODE(:dealerCode, :dealerId)}", nativeQuery = true)
	List<Map<String, Object>> autoGetDealerCode(@Param("dealerCode") String dealerCode, @Param("dealerId") Long dealerId);
	
	SPBackOrderCancellationViewDto findByBocNo(String bocNo);
	
	@Query(value = "{call sp_back_order_cancellation_search(:bocno, :dealercode, :fromDate, :toDate, :page, :size)}", nativeQuery = true)
	List<SPBackOrderCancellationResponseDto> searchBOCancellation(
			@Param("bocno") String bocno,
			@Param("dealercode") String dealercode,
			@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,
			@Param("page") Integer page,
			@Param("size") Integer size);
	
	@Query(value = "{call SP_GET_BACK_ORDER_CANCELLATION_ITEMS(:dealerCode)}", nativeQuery = true)
	List<Map<String, Object>> getBOCItemDetails(@Param("dealerCode") String dealerCode);
	
	@Query(value = "{call SP_GET_AUTOCOMPLETE_DEALER_CODE(:dealerCode)}", nativeQuery = true)
	List<Map<String, Object>> autoCompleteDealerCode(@Param("dealerCode") String dealerCode);
	
	@Transactional
	@Query(value = "{call SP_BO_CANCELLATION_APPROVAL(:bocId,:houserId,:remark,:usercode,:approvalStatus)}", nativeQuery = true)
	String boCancellationApproval(@Param("bocId") Long bocId,
			@Param("houserId") Long houserId,
			@Param("remark") String remark,
			@Param("usercode") String usercode, 
			@Param("approvalStatus")String approvalStatus);
	
	@Query(value = "{call sp_back_order_cancellation_hierarchy_level}", nativeQuery = true)
	List<Map<String,Object>> getBackOrderCancellationApprovalHierarchyLevel();
	
	@Query(value = "{call SP_BOC_GetApprovalHierarchyDetails(:backorderId)}", nativeQuery = true)
	List<Map<String,Object>> getBOCApproverHierarchyDetails(@Param("backorderId") Long backorderId);
	
	@Query(value = "{call sp_back_order_cancellation_approval_search(:bocno, :dealercode, :fromDate, :toDate, :page, :size)}", nativeQuery = true)
	List<SPBackOrderCancellationResponseDto> searchBOCApproval(
			@Param("bocno") String bocno,
			@Param("dealercode") String dealercode,
			@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,
			@Param("page") Integer page,
			@Param("size") Integer size);
	
	@Query(value = "{call SP_SPARE_BACK_ORDER_CANCELLATION_SEARCH(:bocno, :dealercode, :fromDate, :toDate, :page, :size, :userCode, :includeInactive)}", nativeQuery = true)
	List<SPBackOrderCancellationResponseDto> spBackOrderCancellationSearch(
			@Param("bocno") String bocno,
			@Param("dealercode") String dealercode,
			@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,
			@Param("page") Integer page,
			@Param("size") Integer size,
			@Param("userCode") String userCode,
			@Param("includeInactive") Character includeInactive);
	
}
