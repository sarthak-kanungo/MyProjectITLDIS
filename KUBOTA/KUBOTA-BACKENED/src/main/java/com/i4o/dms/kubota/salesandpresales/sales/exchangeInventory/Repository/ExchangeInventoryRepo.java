package com.i4o.dms.kubota.salesandpresales.sales.exchangeInventory.Repository;


import com.i4o.dms.kubota.connection.ConnectionConfiguration;
import com.i4o.dms.kubota.salesandpresales.enquiry.dto.EnquirySearchResponseDto;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.dto.PurchaseOrderResponseDto;
import com.i4o.dms.kubota.salesandpresales.sales.exchangeInventory.domain.ExchangeInventory;
import com.i4o.dms.kubota.salesandpresales.sales.exchangeInventory.dto.ExchangeInventoryDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ExchangeInventoryRepo extends JpaRepository<ExchangeInventory, Long>,ConnectionConfiguration {


	@Query(value = "{call sp_getExchangeInventroySearch(:management,:branchId,:kaiEmployeeId,:dealerEmployeeId,:enquiryNo,:fromdate,:todate,:Status,:userCode,:includeInactive,:orgHierarchyId,:page,:size)}", nativeQuery = true)
    List<ExchangeInventoryDto> getExchangeInventorySearch(
            @Param("management") Boolean managementAccess,
            @Param("branchId") Long branchId,
            @Param("kaiEmployeeId") Long kaiEmployeeId,
            @Param("dealerEmployeeId") Long userId,
            @Param("enquiryNo") String enquiryNumber,
            @Param("todate") String toDate,
            @Param("fromdate") String fromDate,
            @Param("Status") String status,
            @Param("userCode") String userCode,
        	@Param("includeInactive") Character includeInactive,
        	@Param("orgHierarchyId") Long orgHierarchyId,
            @Param("page") Integer page,
            @Param("size") Integer size
    );
//    ExchangeInventoryDto findByEnquiryNumber(String enquiryNumber);

}
