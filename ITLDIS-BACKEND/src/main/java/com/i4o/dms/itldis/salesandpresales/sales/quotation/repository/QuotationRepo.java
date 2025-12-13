package com.i4o.dms.itldis.salesandpresales.sales.quotation.repository;


import com.i4o.dms.itldis.connection.ConnectionConfiguration;
import com.i4o.dms.itldis.salesandpresales.sales.quotation.domain.Quotation;
import com.i4o.dms.itldis.salesandpresales.sales.quotation.dto.QuotationDto;
import com.i4o.dms.itldis.salesandpresales.sales.quotation.dto.QuotationSearchResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

public interface QuotationRepo extends JpaRepository<Quotation, Long>,ConnectionConfiguration {
    @Query(value = "{call sp_getQuotationId(:quotationCode)}", nativeQuery = true)
    Long getQuotationId(@Param("quotationCode") String quotationCode);

    @Query(value = "{call sp_getQuotationSearch(:management,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:quotationCode,:source,:enquiryStatus,:salesPerson,:product,:series,:model,:subModel,:variant,:itemNo,:quotationFromDate,:quotationToDate,:page,:size,:usercode,:includeinactive)}", nativeQuery = true)
    List<QuotationSearchResponseDto> getQuotationSearch(
            @Param("management") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("kaiEmployeeId")Long kaiEmployeeId,
            @Param("dealerEmployeeId")Long dealerEmployeeId,
            @Param("quotationCode") String quotation,
            @Param("source") String source,
            @Param("enquiryStatus") String enquiryStatus,
            @Param("salesPerson") String salesPerson,
            @Param("product") String product,
            @Param("series") String series,
            @Param("model") String model,
            @Param("subModel") String subModel,
            @Param("variant") String variant,
            @Param("itemNo") String itemNo,
            @Param("quotationFromDate") String quotationFromDate,
            @Param("quotationToDate") String quotationToDate,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("usercode")String usercode,
            @Param("includeinactive")Character includeinactive);

    @Query(value = "{call sp_getQuotationSearchCount(:management,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:quotationCode,:source,:enquiryStatus,:salesPerson,:product,:series,:model,:subModel,:variant,:itemNo,:quotationFromDate,:quotationToDate,:page,:size)}", nativeQuery = true)
    Long getQuotationSearchCount(
            @Param("management") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("kaiEmployeeId")Long kaiEmployeeId,
            @Param("dealerEmployeeId")Long dealerEmployeeId,
            @Param("quotationCode") String quotation,
            @Param("source") String source,
            @Param("enquiryStatus") String enquiryStatus,
            @Param("salesPerson") String salesPerson,
            @Param("product") String product,
            @Param("series") String series,
            @Param("model") String model,
            @Param("subModel") String subModel,
            @Param("variant") String variant,
            @Param("itemNo") String itemNo,
            @Param("quotationFromDate") String quotationFromDate,
            @Param("quotationToDate") String quotationToDate,
            @Param("page") Integer page,
            @Param("size") Integer size);


    @Query(value = "{call sp_getEnquiryByEnquiryNo(:enquiryNo,:branchId)}", nativeQuery = true)
    Map<String, Object> getEnquiryByEnquiryNo(@Param("enquiryNo") String enquiryNo, @Param("branchId")Long branchId);

    @Query(value = "{call sp_getMachineDetailByItemNo(:itemNo)}", nativeQuery = true)
    Map<String, Object> getMachineDetailByItemNo(@Param("itemNo") String itemNo);

    @Query(value = "{call sp_getQuotationCodes(:itemNo,:branchId, :usercode)}", nativeQuery = true)
    List<Map<String, Object>> getQuotationCodes(@Param("itemNo") String itemNo,@Param("branchId")Long dealerId,@Param("usercode")String usercode);

    @Query(value = "{call sp_sales_getQuotationNumberAfterQuotationCreation(:branchId)}", nativeQuery = true)
    Map<String, Object> getQuotationNumberAfterQuotationCreation(Long branchId);

    QuotationDto findByQuotationNumber(String quotationNumber);

    @Query(value = "{call sp_sales_get_auto_complete_open_enquiry_for_quotation(:enquiryNumber,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> getAutoCompleteOpenEnquiryForQuotation(@Param("enquiryNumber") String enquiryNumber,@Param("dealerId") Long dealerId);


}
