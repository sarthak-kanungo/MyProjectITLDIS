package com.i4o.dms.kubota.spares.purchase.orderplanningsheet.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.domain.SPOrderPlanningSheet;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetItemDetailsDto;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetSearchResponseDto;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetViewDto;

@Transactional
public interface SPOrderPlanningSheetRepo extends JpaRepository<SPOrderPlanningSheet, Long> {
	
	@Query(value = "{call SP_AUTO_GET_OPS_NO(:opsNo)}", nativeQuery = true)
	List<Map<String, Object>> autoGetOpsNo(@Param("opsNo") String opsNo);
	
	
	@Query(value = "{call sp_order_planning_sheet_search(:opSheetNo,:orderTypeId,:logicId,:fromDate,:toDate,:page,:size)}", nativeQuery = true)
	List<OPSheetSearchResponseDto> searchOPSheet(
			@Param("opSheetNo") String opSheetNo,
			@Param("orderTypeId") Long orderTypeId,
			@Param("logicId") Long logicId,
			@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,
			@Param("page") Integer page,
			@Param("size") Integer size);
	
	
	@Query(value = "select id,value from SP_ORDER_PLANNING_SUGGESTED_ORDER_QTY where type = 'option1'", nativeQuery = true)
	List<Map<String, Object>> getReorderQtyMonth();
	
	
	@Query(value = "select id,value from SP_ORDER_PLANNING_SUGGESTED_ORDER_QTY", nativeQuery = true)
	List<Map<String, Object>> getSuggestedQtyMonth();
	
	
	@Query(value = "select id, logic from SP_ORDER_PLANNING_LOGIC", nativeQuery = true)
	List<Map<String, Object>> getAllLogic();
	
	
	OPSheetViewDto findByOpsNo(String opsNo);
	
	
	@Query(value = "{call SP_GET_ORDER_PLANNING_ITEMS(:userName,:orderTypeId,:logicId,:reOrderMonth,:suggestedOrderMonth)}", nativeQuery = true)
	List<OPSheetItemDetailsDto> getOPSheetItemDetails(
			@Param("userName") String userName,
			@Param("orderTypeId") Long orderTypeId,
			@Param("logicId") Long logicId,
			@Param("reOrderMonth") float reOrderMonth,
			@Param("suggestedOrderMonth") float suggestedOrderMonth);

	@Query(value = "SELECT id, order_type orderType FROM SP_ORDER_PLANNING_ORDER_TYPE WHERE id <> 3", nativeQuery = true)
	List<Map<String, Object>> getOrderTypes();
	
    @Modifying
    @Query(value = "UPDATE SP_ORDER_PLANNING_SHEET SET purchase_order_id = :poId WHERE id = :id", nativeQuery = true)
	void updatePOIdInOps(@Param("id") Long id, @Param("poId") Long poId);
    
    @Modifying
    @Query(value = "UPDATE SP_ORDER_PLANNING_SHEET SET status='PO Created' WHERE id = :id", nativeQuery = true)
	void updateStatusOfOps(@Param("id") Long id);

}
