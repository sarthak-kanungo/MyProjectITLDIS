package com.i4o.dms.itldis.spares.picklist.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.itldis.spares.picklist.domain.PickList;
import com.i4o.dms.itldis.spares.picklist.dto.PickListSearchDto;
import com.i4o.dms.itldis.spares.picklist.dto.PickListSearchItem;


@Transactional
@Repository
public interface PickListRepository extends JpaRepository<PickList, Long>{
	
	@Query(value = "{call sp_spare_picklist_so_number_autocomplete(:saleOrderNumber,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getPickListCustomerOrderNoAuto(@Param("saleOrderNumber") String saleOrderNumber,
                                                             @Param("branchId") Long branchId);
	
	@Query(value = "{call sp_spare_picklist_get_customer_detail(:saleOrderNumber,:branchId)}", nativeQuery = true)
	Map<String, Object> getSalesOrderCustomerDetails(@Param("saleOrderNumber") Long saleOrderNumber,
	                                                                       @Param("branchId") Long branchId);

	@Query(value = "{call sp_spare_picklist_getItemForPick(:soId, :branchId)}", nativeQuery = true)
	List<Map<String,Object>> getSalesOrderItemDetails(@Param("soId") Long soId,
            @Param("branchId") Long branchId);

	
	@Query(value = "{call sp_spare_picklist_get_header_by_pickId(:pickId,:branchId)}", nativeQuery = true)
	Map<String, Object> getPickHeaderDetails(@Param("pickId") Long pickId, @Param("branchId") Long branchId);

	@Query(value = "{call sp_spare_picklist_itemdetails_by_pickId(:pickId, :branchId)}", nativeQuery = true)
	List<Map<String,Object>> getPickLineItemDetails(@Param("pickId") Long pickId, @Param("branchId") Long branchId);

	@Query(value = "{call sp_spare_picklist_search_pickNo(:saleOrderNumber,:userName)}", nativeQuery = true)
    List<Map<String, Object>> getSalesOrderAutocomplete(@Param("saleOrderNumber") String saleOrderNumber,
                                                             @Param("userName") String userName);
	
    @Query(value = "{call sp_spare_picklist_search(:picklistNumber,:pickStatus,:fromDate,:toDate, :dealerId,:dealerEmployeeId,:page,:size,:userName,:IncludeInactive,:OrgHierId)}",nativeQuery = true)
    List<PickListSearchItem> searchPickList(@Param("picklistNumber") String picklistNumber,
    									   @Param("pickStatus") String pickStatus,
    									   @Param("fromDate") String fromDate,
                                           @Param("toDate") String toDate,
                                           @Param("dealerId") Long dealerId,
                                           @Param("dealerEmployeeId") Long dealerEmployeeId,
                                           @Param("page") Integer page, @Param("size") Integer size,
                                           @Param("userName") String userName,
                                           @Param("IncludeInactive") Character includeInactive,
                                           @Param("OrgHierId") Long OrgHierId);
    
    @Modifying
    @Query(value="{call sp_spare_picklist_update_SaleOrderStatus (:picklistId, :branchId)}", nativeQuery=true)
    void updateSaleOrderStatus(@Param("picklistId") Long picklistId, @Param("branchId") Long branchId);
    
    //@Modifying
    @Query(value="{call sp_spares_UpdateStockBinDetail (:BranchId,:itemNo,:StockStoreid,:StockBInId,:transactiontype,:refTransHdrId,:MRP,:unitPrice,:spegst,:spmgst,:ReceivedQty,:IssuedQty,:userId)}", nativeQuery=true)
    Integer updateStockDetails(@Param("BranchId") Long branchId, 
    		@Param("itemNo") String itemNo,
    		@Param("StockStoreid") Long stockStoreid,
    		@Param("StockBInId") Long stockBInId,
    		@Param("transactiontype") String transactiontype,
    		@Param("refTransHdrId") Long refTransHdrId,
    		@Param("MRP") Double mrp,
    		@Param("unitPrice") Double unitPrice,
    		@Param("spegst") Double spegst,
    		@Param("spmgst") Double spmgst,
    		@Param("ReceivedQty") Integer receivedQty,
    		@Param("IssuedQty") Integer issuedQty,
    		@Param("userId") Long userId);
}
