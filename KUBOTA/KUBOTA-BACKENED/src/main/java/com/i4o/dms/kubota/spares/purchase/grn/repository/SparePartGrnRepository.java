package com.i4o.dms.kubota.spares.purchase.grn.repository;

import com.i4o.dms.kubota.spares.purchase.grn.domain.SparePartGrn;
import com.i4o.dms.kubota.spares.purchase.grn.dto.GrnSearchResponseDto;
import com.i4o.dms.kubota.spares.purchase.transitreport.dto.SearchResponsedto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

@Transactional
public interface SparePartGrnRepository extends JpaRepository<SparePartGrn, Long> {
    @Query(value = "{call sp_spares_grn_get_supplier_name(:supplierName,:supplierType, :branchId)}", nativeQuery = true)
    List<Map<String, Object>> getSupplierName(@Param("supplierName") String supplierName, @Param("supplierType") String supplierType,
    		@Param("branchId")Long branchId);

    @Query(value = "{call sp_spares_grn_get_supplier_details_by_id(:supplierId,:supplierType)}", nativeQuery = true)
    Map<String, Object> getSupplierDetailsBySupplierId(@Param("supplierId") Long supplierId, @Param("supplierType")String supplierType);

    @Query(value = "{call sp_spares_grn_get_grn_type}", nativeQuery = true)
    List<Map<String, Object>> getGrnType();

    @Query(value = "{call sp_spares_grn_get_grn_status}", nativeQuery = true)
    List<Map<String, Object>> getGrnStatus();

    @Query(value = "{call sp_spares_grn_get_supplier_type(:grnType)}", nativeQuery = true)
    List<Map<String, Object>> getSupplierType(@Param("grnType") String grnType);

    @Query(value = "{call sp_spares_grn_search_invoice_no(:invoiceNo,:branchId,:dealerId,:supplierType,:storeID)}", nativeQuery = true)
    List<Map<String, Object>> searchSparesInvoiceNo(@Param("invoiceNo") String invoiceNo, 
    												@Param("branchId") Long branchId, 
    												@Param("dealerId") Long dealerId, 
    												@Param("supplierType") String supplierType,
    												@Param("storeID") Long storeID);

    @Query(value = "{call sp_spares_grn_get_invoice_details(:invoiceId,:branchId,:dealerId,:supplierType)}", nativeQuery = true)
    Map<String, Object> getInvoiceDetails(@Param("invoiceId") Long invoiceId, @Param("branchId") Long branchId, @Param("dealerId") Long dealerId,@Param("supplierType") String supplierType);

    @Query(value = "{call sp_spares_grn_get_invoice_item_details(:invoiceId,:branchId,:dealerId,:supplierType,:storeID)}", nativeQuery = true)
    List<Map<String, Object>> getInvoiceItemDetails(@Param("invoiceId") Long invoiceId, 
    												@Param("branchId") Long branchId, 
    												@Param("dealerId") Long dealerId,
    												@Param("supplierType") String supplierType,
    												@Param("storeID") Long storeID);

    @Query(value = "{call sp_spares_mt_get_stores_list_by_dealer(:branchId,:tranType)}", nativeQuery = true)
    List<Map<String, Object>> getStoresList(@Param("branchId") Long branchId, @Param("tranType")String tranType);

    @Query(value = "{call sp_spares_mt_get_bin_location_by_store_id(:storeId,:binLocation,:branchId,:item_no)}", nativeQuery = true)
    List<Map<String, Object>> getBinLocationById(@Param("storeId") Long storeId,
                                                 @Param("binLocation") String binLocation,
                                                 @Param("branchId") Long branchId,
                                                 @Param("item_no") String item_no);

    @Query(value = "{call sp_spares_grn_auto_grn_no(:grnNumber,:dealerId, :usercode)}", nativeQuery = true)
    List<Map<String, Object>> searchBySpareGrnNumber(@Param("grnNumber") String grnNumber, @Param("dealerId") Long dealerId, @Param("usercode")String usercode);

    @Query(value = "{call sp_spares_grn_auto_accpac_invoice_no(:invoiceNumber,:dealerId, :usercode)}", nativeQuery = true)
    List<Map<String, Object>> searchInvoiceNumberFromGrn(@Param("invoiceNumber") String invoiceNumber, @Param("dealerId") Long dealerId, @Param("usercode")String usercode);

    @Query(value = "{call sp_spares_grn_search_grn(:spareGrnNumber,:grnType,:grnStatus,:invoiceNumber,:supplierType,:supplierId,:fromDate," +
            ":toDate,:result,:dealerId,:dealerEmployeeId,:page,:size,:usercode,:includeInactive,:orgHierId,:reportType)}", nativeQuery = true)
    List<GrnSearchResponseDto> searchSpareGrn(@Param("spareGrnNumber") String spareGrnNumber, @Param("grnType") String grnType,
                                              @Param("grnStatus") String grnStatus, @Param("invoiceNumber") String invoiceNumber,
                                              @Param("supplierType") String supplierType, @Param("supplierId") Long supplierId,
                                              @Param("fromDate") String fromDate, @Param("toDate") String toDate,
                                              @Param("result") String result, @Param("dealerId") Long dealerId,
                                              @Param("dealerEmployeeId") Long dealerEmployeeId,
                                              @Param("page") Integer page, @Param("size") Integer size,
                                              @Param("usercode") String usercode,
                                              @Param("includeInactive") Character includeInactive,
                                              @Param("orgHierId") Long orgHierId, @Param("reportType")String reportType);

    
    @Query(value = "{call sp_spares_grn_get_grn_by_grn_id(:grnId)}", nativeQuery = true)
    Map<String, Object> findGrnByGrnId(@Param("grnId") Long grnId);

    @Query(value = "{call sp_spares_grn_get_grn_item_details_by_grn_id(:grnId)}", nativeQuery = true)
    List<Map<String, Object>> findGrnItemDetailByGrnId(@Param("grnId") Long grnId);

    @Query(value = "{call sp_spares_grn_search_po_number_for_grn(:poNumber,:supplierId,:dealerId,:supplierType)}", nativeQuery = true)
    List<Map<String, Object>> searchPoNumberForGrn(@Param("poNumber") String poNumber,
                                                   @Param("supplierId") Long supplierId,
                                                   @Param("dealerId") Long dealerId,
                                                   @Param("supplierType") String supplierType);

    @Query(value = "{call sp_spares_grn_get_po_details_by_po_number_for_grn(:poId,:dealerId)}", nativeQuery = true)
    Map<String, Object> getPoDetailsByPoNumberForGrn(@Param("poId") Long poId, @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_spares_grn_get_po_item_details_by_po_number_for_grn(:poId,:branchId,:storeID)}", nativeQuery = true)
    List<Map<String, Object>> getPoItemDetailsByPoNumberForGrn(@Param("poId") Long poId, @Param("branchId") Long branchId, @Param("storeID") Long storeID);

    //binning slip
    @Query(value = "{call sp_spares_grn_search_grn_no_for_binning_slip(:grnNo,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> searchSparesGrnNo(@Param("grnNo") String grnNo, @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_spares_grn_get_binning_slip_from_grn(:grnId,:dealerId)}",nativeQuery = true)
    Map<String,Object> getBinningSlipFromGrn(@Param("grnId") Long grnId,@Param("dealerId") Long dealerId);

    @Query(value = "{call sp_spares_grn_get_binning_slip_item_detail_from_grn(:grnId,:dealerId)}",nativeQuery = true)
    List<Map<String,Object>> getBinningSlipItemDetailsFromGrn(@Param("grnId") Long grnId,@Param("dealerId") Long dealerId);

    @Query(value = "{call sp_spares_grn_get_transit_report_detail(:grnStatus,:fromInvoiceDate,:toInvoiceDate,:dealerId,:page,:size,:userName,:IncludeInactive,:OrgHierId)}",nativeQuery = true)
    List<SearchResponsedto> getTransitReportDetails(@Param("grnStatus") String grnStatus, @Param("fromInvoiceDate") String fromInvoiceDate,
                                                     @Param("toInvoiceDate") String toInvoiceDate, @Param("dealerId") Long dealerId,
                                                     @Param("page") Integer page, @Param("size") Integer size,
                                                     @Param("userName") String userName,
                                                     @Param("IncludeInactive") Character includeInactive,
                                                     @Param("OrgHierId") Long OrgHierId);

    @Query(value = "{call sp_spares_grn_check_po_exists_with_draft(:poId)}",nativeQuery = true)
    Integer checkPoExistsWithDraft(@Param("poId") Long poId);
    
    @Modifying
    @Query(value = "{call sp_update_stock_table_after_spare_grn(:grnID)}", nativeQuery = true)
    void updateStockTable(@Param("grnID") Long grnID);
    
    @Query(value = "{call sp_spares_grn_get_transit_report_items(:invoiceNo)}", nativeQuery = true)
    List<Map<String, Object>> getTransitReportItems(@Param("invoiceNo") String invoiceNo);
}