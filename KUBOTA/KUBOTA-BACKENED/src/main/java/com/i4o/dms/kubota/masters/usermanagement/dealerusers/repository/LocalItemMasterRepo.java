package com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.LocalItemMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.LocalItemMasterSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LocalItemMasterRepo extends JpaRepository<LocalItemMaster,Long> {

    @Query(value="{call sp_mt_local_item_master_igst_dropdown}",nativeQuery = true)
    List<Map<String,Object>> getIgstList();

//    @Query(value="{call sp_local_item_master_cgst_sgst_percent(:igst)}",nativeQuery = true)
//    List<Map<String,Object>> getSgstAndCgst(@Param("igst") Double igst);

    @Query(value="{call sp_mt_local_item_master_search(:dmsItemNo,:vendorItemNo,:dealerVendorCode,:page,:size)}",nativeQuery = true)
    List<LocalItemMasterSearchResponse> searchItemMaster(@Param("dmsItemNo") String dmsItemNo,
                                                         @Param("vendorItemNo") String vendorItemNo,
                                                         @Param("dealerVendorCode") String dealerVendorCode,
                                                         @Param("page") Integer page,
                                                         @Param("size") Integer size
                                                         );

    @Query(value="{call sp_mt_local_item_master_search_count(:dmsItemNo,:vendorItemNo,:dealerVendorCode,:page,:size)}",nativeQuery = true)
    Long searchItemMasterCount(@Param("dmsItemNo") String dmsItemNo,
                                  @Param("vendorItemNo") String vendorItemno,
                                  @Param("dealerVendorCode") String dealerVendorCode,
                                  @Param("page") Integer page,
                                  @Param("size") Integer size);

    @Query(value = "{call sp_mt_local_item_master_dms_no_autocomplete(:dmsItemNo)}",nativeQuery = true)
    List<Map<String,Object>> findByDmsItemNoContaining(@Param("dmsItemNo") String dmsItemNo);

    @Query(value = "{call sp_mt_local_item_master_vendor_item_no_autocomplete(:vendorItemNo)}",nativeQuery = true)
    List<Map<String,Object>> findByVendorItemNoContaining(@Param("vendorItemNo") String vendorItemNo);

    @Query(value = "{call sp_mt_local_item_master_dealer_vendor_code_autocomplete(:vendorDealerCode)}",nativeQuery = true)
    List<Map<String,Object>> findByDealerVendorCodeContaining(@Param("vendorDealerCode") String vendorDealerCode);

}
