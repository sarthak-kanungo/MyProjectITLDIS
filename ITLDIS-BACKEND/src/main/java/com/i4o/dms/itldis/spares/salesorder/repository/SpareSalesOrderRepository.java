package com.i4o.dms.itldis.spares.salesorder.repository;

import com.i4o.dms.itldis.spares.salesorder.domain.SpareSalesOrder;
import com.i4o.dms.itldis.spares.salesorder.dto.SpareSaleOrderResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Transactional
@Repository
public interface SpareSalesOrderRepository extends JpaRepository<SpareSalesOrder, Long> {

    @Query(value = "{call sp_spare_sales_order_so_number_autocomplete(:soNumber,:dealerId,:usercode)}", nativeQuery = true)
    List<Map<String, Object>> getSpareSalesOrderAutocomplete(@Param("soNumber") String soNumber,
                                                             @Param("dealerId") Long dealerId,
                                                             @Param("usercode") String usercode);

    @Query(value = "{call sp_spare_sales_order_get_open_quotation_number_autocomplete(:quotationNumber,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getQuotationNumberAutocomplete(@Param("quotationNumber") String quotationNumber,
                                                             @Param("branchId") Long branchId);

    @Query(value = "{call sp_spare_sale_order_customer_name_autocomplete(:customerName,:dealerId,:usercode)}", nativeQuery = true)
    List<Map<String, Object>> getSpareSalesOrderCustomerNameAutocomplete(@Param("customerName") String customerName,
                                                                         @Param("dealerId") Long dealerId,@Param("usercode") String usercode);

    @Query(value = "{call sp_spare_sales_order_get_item_details_by_item_no(:itemNumber,:state,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getItemDetailsByItemNumber(@Param("itemNumber") String itemNumber,
                                                         @Param("state") String state,
                                                         @Param("branchId") Long branchId);

    @Query(value = "{call sp_spare_sales_order_view_header_data(:id,:branchId)}", nativeQuery = true)
    Map<String, Object> getSpareSalesOrderHeaderDetails(@Param("id") Long id,
                                                        @Param("branchId") Long branchId);

    @Query(value = "{call sp_spare_sales_order_view_part_details(:id,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getSpareSalesOrderPartDetails(@Param("id") Long id,
                                                            @Param("branchId") Long branchId);

    @Query(value = "{call sp_spare_sales_order_search(:soId,:customerName,:customerType,:orderStatus,:soFromDate,:soToDate,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size, :UserCode,:includeInactive,:orgHierId)}", nativeQuery = true)
    List<SpareSaleOrderResponseDto> spareSalesOrderSearch(@Param("soId") Long soId,
                                                          @Param("customerName") String customerName,
                                                          @Param("customerType") String customerType,
                                                          @Param("orderStatus") String orderStatus,
                                                          @Param("soFromDate") String soFromDate,
                                                          @Param("soToDate") String soToDate,
                                                          @Param("dealerId") Long dealerId,
                                                          @Param("dealerEmployeeId") Long dealerEmployeeId,
                                                          @Param("kubotaEmployeeId") Long kubotaEmployeeId,
                                                          @Param("managementAccess") Boolean managementAccess,
                                                          @Param("page") Integer page,
                                                          @Param("size") Integer size,
                                                          @Param("UserCode") String usercode,
                                                          @Param ("includeInactive") char includeInactive,
                                                          @Param ("orgHierId")  Long orgHierId
    );


   

    @Query(value = "{call sp_spare_sales_order_retailer_autocomplete(:searchKey,:customerType,:branch_id)}", nativeQuery = true)
    List<Map<String, Object>> getRetailerAndMechanicAutocomplete(@Param("searchKey") String retailerName,
                                                                 @Param("customerType") String customerType,
                                                                 @Param("branch_id") Long branch_id);

    @Query(value = "{call sp_spare_sales_order_retailer_or_mechanic_details(:id)}", nativeQuery = true)
    Map<String, Object> getRetailerAndMechanicDetails(@Param("id") Long id);

    @Query(value = "{call sp_spare_sales_order_dealer_details(:id)}", nativeQuery = true)
    Map<String, Object> getDealerDetails(@Param("id") Long id);

    @Query(value = "{call sp_spare_sales_order_dealer_code_autocomplete(:dealerCode)}", nativeQuery = true)
    List<Map<String, Object>> getDealerCodeAutocomplete(@Param("dealerCode") String dealerCode);


   /* @Query(value = "{call sp_spares_sales_order_delete_part(:partId,:dealerId)}", nativeQuery = true)
    String deletePart(@Param("partId") String partId,
                      @Param("dealerId") Long dealerId);*/
    @Modifying
    @Query(value = "delete from SP_SALES_ORDER_PART_DETAIL where id in (:partIds)", nativeQuery = true)
    String deletePart(@Param("partIds") List<Object> partIds);
    
  
    @Query(value = "{call sp_spare_sales_order_get_item_details_by_excel(:parts,:qtys,:state,:branchId,:existingItems,:discountRate)}", nativeQuery = true)
    List<Map<String, Object>> getItemDetailsByExcel(@Param("parts") String parts,
									    	@Param("qtys") String qtys,
									    	@Param("state") String customerType,
									    	@Param("branchId") Long branchId,
									    	@Param("existingItems") String existingItems,
									    	@Param("discountRate") Double discountRate
    		);
    

    
}
