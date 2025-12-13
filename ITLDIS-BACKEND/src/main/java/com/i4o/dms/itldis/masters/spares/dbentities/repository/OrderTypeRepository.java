package com.i4o.dms.itldis.masters.spares.dbentities.repository;

import com.i4o.dms.itldis.masters.spares.dbentities.domain.SparesMtPurchaseOrderOrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface OrderTypeRepository extends JpaRepository<SparesMtPurchaseOrderOrderType, Long> {

    @Query(value = "{call sp_spares_mt_getOrderType_dropdown()}", nativeQuery = true)
    List<Map<String, Object>> getOrderTypeDropdown();

    @Query(value = "{call sp_spares_mt_transfermode_dropdown()}", nativeQuery = true)
    List<Map<String, Object>> getTransferModeDropdown();

    @Query(value = "{call sp_spare_mt_transporter_dropdown()}", nativeQuery = true)
    List<Map<String, Object>> getTransporterDropdown();

    @Query(value = "{call sp_spares_mt_get_purchase_order_status_dropdown()}", nativeQuery = true)
    List<Map<String, Object>> getPurchaseOrderStatus();

    @Query(value = "{call sp_spares_mt_get_supplier_name_autocomplete(:supplierName)}", nativeQuery = true)
    List<Map<String, Object>> getSupplierName(@Param("supplierName") String supplierName);
    
    @Query(value = "{call sp_spares_mt_get_vendor_name_autocomplete(:vendorName, :dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getVendorName(@Param("vendorName") String vendorName, @Param("dealerId")Long dealerId);

    @Query(value = "{call sp_spares_get_sparepart_details_for_mrc(:itemNumber)}", nativeQuery = true)
    List<Map<String, Object>> getSparePartItemDetailsForMrc(@Param("itemNumber") String itemNumber);

    @Query(value = "{call sp_spare_mt_get_price_type_dropdown}", nativeQuery = true)
    List<Map<String, Object>> getSparesPriceTypeDropdown();

    @Query(value = "{call sp_spares_mt_get_customer_type(:documentType)}", nativeQuery = true)
    List<Map<String, Object>> getSparesCustomerTypeDropdown(@Param("documentType")String documentType);

    @Query(value = "{call sp_spare_mt_get_purchase_order_type()}", nativeQuery = true)
    List<Map<String, Object>> getSparesPoTypeDropdown();

}
