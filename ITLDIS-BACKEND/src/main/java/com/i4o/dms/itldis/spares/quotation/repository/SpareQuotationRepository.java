package com.i4o.dms.itldis.spares.quotation.repository;

import com.i4o.dms.itldis.spares.quotation.domain.SpareQuotation;
import com.i4o.dms.itldis.spares.quotation.dto.SpareQuotationSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SpareQuotationRepository extends JpaRepository<SpareQuotation, Long> {

    @Query(value = "{call sp_spare_sales_order_quotation_number_autocomplete(:quotationNumber,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getQuotationNumberAutocomplete(@Param("quotationNumber") String quotationNumber,
                                                             @Param("branchId") Long branchId);

    @Query(value = "{call sp_spare_quotation_search(:quotationId,:customerName,:customerType,:quotationFromDate,:quotationToDate,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size,:UserCode,:includeInactive,:orgHierId)}", nativeQuery = true)
    List<SpareQuotationSearchResponse> getQuotationSearch(@Param("quotationId") Long quotationId,
                                                          @Param("customerName") String customerName,
                                                          @Param("customerType") String customerType,
                                                          @Param("quotationFromDate") String quotationFromDate,
                                                          @Param("quotationToDate") String quotationToDate,
                                                          @Param("dealerId") Long dealerId,
                                                          @Param("dealerEmployeeId") Long dealerEmployeeId,
                                                          @Param("kubotaEmployeeId") Long kubotaEmployeeId,
                                                          @Param("managementAccess") Boolean managementAccess,
                                                          @Param("page") Integer page,
                                                          @Param("size") Integer size,
                                                          @Param("UserCode") String UserCode,
                                                          @Param("includeInactive") char includeInactive,
                                                          @Param("orgHierId") Long orgHierId);

//    @Query(value = "{call sp_spare_quotation_search_count(:quotationId,:customerName,:customerType,:quotationFromDate,:quotationToDate,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size)}", nativeQuery = true)
//    Long getQuotationSearchCount(@Param("quotationId") Long quotationId,
//                                 @Param("customerName") String customerName,
//                                 @Param("customerType") String customerType,
//                                 @Param("quotationFromDate") String quotationFromDate,
//                                 @Param("quotationToDate") String quotationToDate,
//                                 @Param("dealerId") Long dealerId,
//                                 @Param("dealerEmployeeId") Long dealerEmployeeId,
//                                 @Param("kubotaEmployeeId") Long kubotaEmployeeId,
//                                 @Param("managementAccess") Boolean managementAccess,
//                                 @Param("page") Integer page,
//                                 @Param("size") Integer size);


    @Query(value = "{call sp_spare_quotation_view_header_details(:quotationId,:dealerId)}", nativeQuery = true)
    Map<String, Object> getQuotationViewHeaderDetails(@Param("quotationId") Long quotationId,
                                                      @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_spare_quotation_view_part_details(:quotationId,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getQuotationViewPartDetails(@Param("quotationId") Long quotationId,
                                                          @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_spare_quotation_view_part_details_for_sales_order(:quotationId,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getQuotationViewPartDetailsForSalesOrder(@Param("quotationId") Long quotationId,
                                                                       @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_spare_quotation_customer_name_autocomplete(:customerName,:dealerId,:userCode)}", nativeQuery = true)
    List<Map<String, Object>> getQuotationCustomerNameAutocomplete(@Param("customerName") String customerName,
                                                                   @Param("dealerId") Long dealerId,
                                                                   @Param("userCode") String userCode);

    @Query(value = "{call sp_spares_get_part_details_for_quotation_by_excel(:items, :qtys, :branchId, :state, :existingItems, :discountRate)}", nativeQuery = true)
    List<Map<String, Object>> getItemDetailsByExcel(@Param("items") String items, @Param("qtys") String qtys, @Param("branchId") Long branchId, @Param("state")String state, @Param("existingItems")String existingItems, @Param("discountRate")String discountRate);

}
