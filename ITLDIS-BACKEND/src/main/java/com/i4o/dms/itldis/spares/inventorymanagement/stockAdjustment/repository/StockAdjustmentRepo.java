package com.i4o.dms.itldis.spares.inventorymanagement.stockAdjustment.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.spares.inventorymanagement.stockAdjustment.domain.StockAdjustmentHdr;
import com.i4o.dms.itldis.spares.inventorymanagement.stockAdjustment.dto.StockAdjustmentDeatils;
import com.i4o.dms.itldis.spares.inventorymanagement.stockAdjustment.dto.StockAdjustmentSearchResult;

@Transactional
@Repository
public interface StockAdjustmentRepo extends JpaRepository<StockAdjustmentHdr, Long> {

   @Query(value="{call SP_STOCK_ADJ_UPLOAD (:adjustmentTypes, :parts, :stores, :locations, :mrps, :qtys, :branchId)}", nativeQuery=true)	
   List<Map<String,Object>>	uploadExcel(
 		   String adjustmentTypes,
 		   String parts, 
 		   String stores,
 		   String locations,
 		   String mrps,
 		   String qtys,
 		   Long branchId );
   

//   @Modifying
   @Query(value = "{call sp_update_stock_table_after_stkadj(:adjID,:branchId,:userId)}", nativeQuery = true)
   List<Map<String,Object>> updateStockTable(@Param("adjID") Long adjID, @Param("branchId") Long branchId, @Param("userId") Long userId);
   
   @Query(value = "{call sp_stkadj_approval_hierarchy_level(:dealerId)}", nativeQuery = true)
   List<Map<String,Object>> getStockAdjApprovalHierarchyLevel(@Param("dealerId") Long dealerId);
   
   @Transactional
   @Query(value = "{call sp_stk_adj_approval(:stkadjId,:houserId,:remark,:usercode,:approvalStatus)}", nativeQuery = true)
   String adjustmentApproval(@Param("stkadjId") Long claimId, @Param("houserId") Long houserId,
                                           @Param("remark") String remark, @Param("usercode") String usercode, @Param("approvalStatus")String approvalStatus);
   
   @Query(value="{call sp_stkadj_getApprovalHierarchyDetails(:Id,:userCode)}",nativeQuery = true)
   List<Map<String,Object>> getApprovalHierDetails(@Param("Id") Long Id, @Param("userCode") String userCode);
   
   @Query(value = "{call sp_stkadj_search(:adjustmentNo,:fromDate,:toDate,:page,:size,"
   		+ ":userCode,:reportType, :adjustmentStatus,:hierId,:dealerId)}", nativeQuery = true)
   List<StockAdjustmentSearchResult> searchAdjustmentList(@Param("adjustmentNo") String adjustmentNo, 
		   @Param("fromDate") String adjustmentFromDate, 
		   @Param("toDate") String adjustmentToDate, 
		   @Param("page") Integer page, 
		   @Param("size") Integer size, 
		   @Param("userCode") String userCode, 
		   @Param("reportType")String reportType,
		   @Param("adjustmentStatus")String adjustmentStatus,
		   @Param("hierId")Long hierId,
		   @Param("dealerId")Long dealerId
		   );
   
   @Query(value = "{call sp_create_bin(:itemNo,:store,:location,:branchId)}", nativeQuery = true)
   BigInteger createBinLocation(@Param("itemNo")String itemNo, @Param("store")Long store, @Param("location")String location, @Param("branchId") Long branchId);
   
   @Query(value = "{call sp_fetch_stkadj_details(:adjId,:branchId)}", nativeQuery = true)
   List<StockAdjustmentDeatils> getAdjustmentDetails(@Param("adjId")Long adjId, @Param("branchId")Long branchId);
   
   @Query(value = "{call sp_get_stkadj(:searchVal,:branchId,:usercode)}", nativeQuery = true)
   List<Map<String,Object>> getAdjustmentNumber(@Param("searchVal")String searchVal, 
		   @Param("branchId")Long branchId,
		   @Param("usercode")String usercode);
   
   @Query(value = "{call sp_get_current_Stock(:itemNo, :storeId, :bin_location_id, :branchId)}", nativeQuery = true)
   Integer getCurrentStockValue(@Param("itemNo")String itemNo, @Param("storeId")Long storeId, @Param("bin_location_id")Long binLocationId, @Param("branchId")Long branchId);
}
