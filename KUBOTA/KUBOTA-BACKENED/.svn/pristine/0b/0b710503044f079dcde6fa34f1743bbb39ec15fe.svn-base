package com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.kubota.connection.ConnectionConfiguration;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.domain.SparePurchaseOrder;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.DealerOutstandingResponse;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.ResponseSearchPurchaseOrder;
@Transactional
@Repository
public interface SparePurchaseOrderRepository extends JpaRepository<SparePurchaseOrder, Long>,ConnectionConfiguration {

    @Query(value = "{call sp_spares_purchase_order_get_item_number_autocomplete(:itemNumber,:itemId, :orderType, :dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getItemNumberAutoComplete(@Param("itemNumber") String itemNumber,
                                                        @Param("itemId") String itemId,
                                                        @Param("orderType") String orderType,
                                                        @Param("dealerId") Long dealerId);
    
    @Query(value = "{call sp_spares_item_autocomplete (:usercode,:itemNumber,'N')}", nativeQuery = true)
    List<Map<String, Object>> getItemNumberSearchAutoComplete(
                                                        @Param("usercode") String usercode,@Param("itemNumber") String itemNumber);

    @Query(value = "{call sp_spare_local_part_itemno_autocomplete(:itemNumber,:itemId,:orderType)}", nativeQuery = true)
    List<Map<String, Object>> getLocalItemNumberAutoComplete(@Param("itemNumber") String itemNumber,
                                                             @Param("itemId") String itemId,
                                                             @Param("orderType") String orderType);

    @Query(value = "{call sp_spares_purchase_order_get_itemdetails_by_item_id(:itemId,:orderType,:dealerId, :priceType)}", nativeQuery = true)
    Map<String, Object> getItemDetailsByItemId(@Param("itemId") String itemId, @Param("orderType") String orderType, @Param("dealerId") Long dealerId, @Param("priceType")String priceType);
    
    @Query(value = "{call sp_spares_purchase_order_get_itemdetails_by_excel(:items, :qtys, :orderType, :dealerId, :priceType, :existingItems)}", nativeQuery = true)
    List<Map<String, Object>> getItemDetailsByExcel(@Param("items") String items, @Param("qtys") String qtys, @Param("orderType")String orderType, @Param("dealerId") Long dealerId, @Param("priceType")String priceType, @Param("existingItems")String existingItems);

    @Query(value = "{call sp_spares_purchase_order_get_dealer_outstanding(:dealerId, :poId)}", nativeQuery = true)
    DealerOutstandingResponse getDealerOutStanding(@Param("dealerId") Long dealerId,@Param("poId") Long poId);

    @Query(value = "{call sp_spares_purchase_order_po_number_autocomplete(:poNumber,:dealerId, :username)}", nativeQuery = true)
    List<Map<String, Object>> getPoNumberAutoComplete(@Param("poNumber") String poNumber,
                                                      @Param("dealerId") Long dealerId,
                                                      @Param("username") String username);

    @Query(value = "{call sp_spare_purchase_order_search(:poType,:poStatus,:fromDate,:toDate,:poNumber,:partNumber,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size, :userCode, :includeInactive, :orgHierId,:reportType)}", nativeQuery = true)
    List<ResponseSearchPurchaseOrder> searchSparePurchaseOrder(@Param("poType") String poType,
                                                               @Param("poStatus") String poStatus,
                                                               @Param("fromDate") String fromDate,
                                                               @Param("toDate") String toDate,
                                                               @Param("poNumber") String poNumber,
                                                               @Param("partNumber") String partNumber,
                                                               @Param("dealerId") Long dealerId,
                                                               @Param("dealerEmployeeId") Long dealerEmployeeId,
                                                               @Param("kubotaEmployeeId") Long kubotaEmployeeId,
                                                               @Param("managementAccess") Boolean managementAccess,
                                                               @Param("page") Integer page,
                                                               @Param("size") Integer size,
                                                               @Param("userCode") String userCode,
                                                               @Param("includeInactive") Character includeInactive,
                                                               @Param("orgHierId")Long orgHierId,
                                                               @Param("reportType") String reportType
    );
    @Query(value = "{call sp_spare_purchase_order_get_order_type_by_supplier_type(:supplierType)}", nativeQuery = true)
    List<Map<String, Object>> getorderTypeBySupplierType(@Param("supplierType") String supplierType);

    @Query(value = "{call sp_spares_purchase_order_supplier_type_dropdown()}", nativeQuery = true)
    List<Map<String, Object>> getSupplierTypeDropdown();

    @Query(value = "{call sp_spare_purchase_order_get_moq(:partId)}", nativeQuery = true)
    Long getMoq(@Param("partId") Long partId);

    @Query(value = "{call sp_spare_purchase_order_get_view_details(:poId)}", nativeQuery = true)
    Map<String, Object> getPoViewDetails(@Param("poId") Long poId);

    @Query(value = "{call sp_spare_purchase_order_get_view_part_details(:poId,:orderType)}", nativeQuery = true)
    List<Map<String, Object>> getPoViewPartDetails(@Param("poId") Long poId, @Param("orderType") String orderType);

    @Query(value = "{call sp_spare_purchase_order_get_view_order_type(:poId)}", nativeQuery = true)
    Map<String, Object> getPoViewOrderType(@Param("poId") Long poId);

    @Query(value = "{call sp_spare_purchase_order_get_view_supplier_name(:poId)}", nativeQuery = true)
    Map<String, Object> getPoViewSupplierName(@Param("poId") Long poId);

    @Query(value = "{call sp_spare_purchase_order_view_co_dealer_master_details(:poId)}", nativeQuery = true)
    Map<String, Object> getPoViewCoDealerDetails(@Param("poId") Long poId);

    @Query(value = "{call sp_spares_purchase_order_get_dealer_code_autocomplete(:dealerCode,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getDealerCodeAutocomplete(@Param("dealerCode") String dealerCode, @Param("dealerId")Long dealerId);

    @Query(value = "{call sp_spares_po_item_info_by_jobcardno(:jobCardId,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getPoPartItemInfoByJobCardNo(@Param("jobCardId") Long jobCardId,
                                                     @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_spares_po_autocomplete_jobcardno(:jobcardNo,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getJobCardNoAutocomplete(@Param("jobcardNo") String jobcardNo,
                                                       @Param("dealerId") Long dealerId);
    @Query(value = "{call sp_getPurchaseTCSValue()}", nativeQuery = true)
    List<Map<String, Object>>  getTcsList();
    
    @Query(value = "{call sp_spares_purchase_order_insert_to_dms_spares_po(:poId)}",nativeQuery = true)
    String insertPoToDmsSparesPo(@Param("poId") Long poId);
    @Modifying
    @Query(value="delete From SP_PURCHASE_ORDER_ITEM where id not in (:ids) and spare_purchase_order_id=:poid", nativeQuery = true)
    int deletePartFromPO(@Param("ids")List<Long> ids, @Param("poid")Long poid);
    
    @Query(value="{call SP_CODEALER_ORDER_REG(:PoId)}", nativeQuery = true)
    String generateSalesOrderForCodealerOrder(@Param("PoId")Long poId);

    @Query(value = "{call SP_AUTO_GET_PO_OPS_NO(:dealerId, :opsNo)}", nativeQuery = true)
	List<Map<String, Object>> autoGetOPSnumber(@Param("dealerId") Long dealerId,
			@Param("opsNo") String opsNo);

    @Query(value = "{call SP_GET_OPS_ITEMS_DETAILS(:opsId)}", nativeQuery = true)
	List<Map<String, Object>> getOPSitemsDeatail(@Param("opsId") String opsId);
    
}

