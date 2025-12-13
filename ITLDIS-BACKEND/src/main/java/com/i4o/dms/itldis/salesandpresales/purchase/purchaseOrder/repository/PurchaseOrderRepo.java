package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.repository;

import com.i4o.dms.itldis.connection.ConnectionConfiguration;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain.PurchaseOrder;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.PurchaseOrderResponseDto;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.ResponsePoDto;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.SalesPoExcelResponse;
import com.i4o.dms.itldis.salesandpresales.reports.dto.TransactionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;


public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder,Long>,ConnectionConfiguration  {

    @Query(value = "{call sp_sales_purchase_order_search(:poNumber,:poType,:depot,:itemNo,:fromDate,:toDate,:poStatus,:product,:series," +
            ":model,:subModel,:variant,:dealerId,:hoUserId,:dealerEmpId,:managementAccess,:page,:size,:usercode,:includeInactive,:hierId)}", nativeQuery = true)
    List<ResponsePoDto> searchBy(
            @Param("poNumber") String poNumber,
            @Param("poType") String poType,
            @Param("depot") String depot,
            @Param("itemNo") String itemNo,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("poStatus") String poStatus,
            @Param("product") String product,
            @Param("series") String series,
            @Param("model") String model,
            @Param("subModel") String subModel,
            @Param("variant") String variant,
            @Param("dealerId") Long dealerId, @Param("hoUserId") Long hoUserId,
            @Param("dealerEmpId") Long dealerEmpId,@Param("managementAccess") Boolean managementAccess, 
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("usercode") String usercode,
            @Param("includeInactive") Character includeInactive,
            @Param("hierId") Long hierId);

    @Query(value = "{call sp_search_po_count(:poNumber,:poType,:depot,:itemNo,:fromDate,:toDate,:poStatus,:product,:series," +
            ":model,:subModel,:variant,:dealerId,:hoUserId,:dealerEmpId,:managementAccess)}", nativeQuery = true)
    Long searchCount(
            @Param("poNumber") String poNumber,
            @Param("poType") String poType,
            @Param("depot") String depot,
            @Param("itemNo") String itemNo,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("poStatus") String poStatus,
            @Param("product") String product,
            @Param("series") String series,
            @Param("model") String model,
            @Param("subModel") String subModel,
            @Param("variant") String variant,
            @Param("dealerId") Long dealerId,
            @Param("hoUserId") Long hoUserId,
            @Param("dealerEmpId") Long dealerEmpId,
            @Param("managementAccess") Boolean managementAccess);

    //Change by abhijeet for autocomplete search with multiple parameters
    @Query(value = "{call sp_autocomplete_poNumber(:poNumber,:dealerId,:usercode,:includeInactive)}",nativeQuery = true)
    List<Map<String,Object>> searchPoNumber(@Param("poNumber") String poNumber,@Param("dealerId") Long id,
    		@Param("usercode")String usercode,
    		@Param("includeInactive")Character includeInactive);

    @Query("select d.lookupId as id,d.lookuptext as value from SystemLookUpEntity d where lookuptypecode='PO_DISCOUNT_TYPE' and isactive='Y'")
    List<Map<String,Object>> getPoDiscountType();

    @Query(value = "{call sp_sales_presales_purchase_get_approval_details(:poId,:userId)}",nativeQuery = true)
    List<Map<String,Object>> getSalesPoApprovalDetails(@Param("poId") Long poId, @Param("userId") Long userId);

    @Query(value = "{call sp_salesandpresales_po_getChannelFinanceAvailable(:dealerCode)}",nativeQuery = true)
    Map<String,Object> getChannelFinanceAvailable(@Param("dealerCode") String dealerCode);

    @Query(value = "{call sp_get_all_transactional_count()}",nativeQuery = true)
    List<TransactionResponse> getAllTransactionalCount();

    PurchaseOrderResponseDto findByPoNumber(String poNumber);
    
    @Query(value = "{call sp_getPurchaseTCSValue()}", nativeQuery = true)
    List<Map<String, Object>>  getTcsList();
    
    @Query(value = "{call sp_Sales_Purchase_Order_Excel_Report(:poNumber,:poType,:depot,:itemNo,:fromDate,:toDate,:poStatus,:product,:series," +
            ":model,:subModel,:variant,:dealerId,:hoUserId,:dealerEmpId,:managementAccess,:usercode,:includeInactive,:hierId)}", nativeQuery = true)
    List<SalesPoExcelResponse> poReport(
			            @Param("poNumber") String poNumber,
			            @Param("poType") String poType,
			            @Param("depot") String depot,
			            @Param("itemNo") String itemNo,
			            @Param("fromDate") String fromDate,
			            @Param("toDate") String toDate,
			            @Param("poStatus") String poStatus,
			            @Param("product") String product,
			            @Param("series") String series,
			            @Param("model") String model,
			            @Param("subModel") String subModel,
			            @Param("variant") String variant,
			            @Param("dealerId") Long dealerId, @Param("hoUserId") Long hoUserId,
			            @Param("dealerEmpId") Long dealerEmpId,@Param("managementAccess") Boolean managementAccess,
			            @Param("usercode") String usercode,
			            @Param("includeInactive") Character includeInactive,
			            @Param("hierId") Long hierId);
    
    /**
     * @author suraj.gaur
     */
    @Query(value = "{call sp_get_applicable_tcs_value(:dealerId)}", nativeQuery = true)
    Map<String, Object> getDealerTcsValue(@Param("dealerId") Long dealerId);
    
    /**
     * @author suraj.gaur
     */
//    @Query(value = "{call SP_GET_TYPE_OF_DELIVERY()}", nativeQuery = true)
//    List<Map<String,Object>> getTypeOfDelivery();
    
    
    @Query(value = "{call sp_purchase_order_pending_for_approval(:usercode)}", nativeQuery = true)
    List<Map<String, Object>> getPoPendingForApproval(@Param("usercode")String userCode);
}
