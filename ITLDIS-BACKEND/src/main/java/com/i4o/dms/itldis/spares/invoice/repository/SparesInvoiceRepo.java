package com.i4o.dms.itldis.spares.invoice.repository;

import com.i4o.dms.itldis.spares.invoice.domain.SparesInvoice;
import com.i4o.dms.itldis.spares.invoice.dto.ResponseSearchSparesInvoice;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface SparesInvoiceRepo extends JpaRepository<SparesInvoice, Long> {

    @Query(value = "{call sp_spares_getSaleOrderForInvoice(:id,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getSaleOrderInfo(@Param("id") Long id,
                                               @Param("branchId") Long dealerId);

    @Query(value = "{call sp_spares_invoice_saleorder_autocomplete(:documentType,:saleOrderNo,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getSaleOrderAutoComplete(@Param("documentType") String documentType,
                                                       @Param("saleOrderNo") String saleOrderNo,
                                                       @Param("branchId") Long branchId);

    @Query(value = "{call sp_spares_invoice_search(:salesInvoiceId,:customerCode,:customerName,:referenceDocument,:customerType,"
    		+ ":salesType,:modeOfTransport,:transporter,:fromDate,:toDate,:dealerEmployeeId,:dealerId,:page,:size,:userCode,:IncludeInactive,:OrgHierId,:reportType, :wcrNo,:jobCardNumber)}", nativeQuery = true)
    List<ResponseSearchSparesInvoice> searchSaleInvoice(@Param("salesInvoiceId") Long salesInvoiceId,
                                                        @Param("customerCode") String customerCode,
                                                        @Param("customerName") String customerName,
                                                        @Param("referenceDocument") String referenceDocument,
                                                        @Param("customerType") String customerType,
                                                        @Param("salesType") String salesType,
                                                        @Param("modeOfTransport") String modeOfTransport,
                                                        @Param("transporter") String transporter,
                                                        @Param("fromDate") String fromDate,
                                                        @Param("toDate") String toDate,
                                                        @Param("dealerEmployeeId") Long dealerEmployeeId,
                                                        @Param("dealerId") Long dealerId,
                                                        @Param("page") Integer page,
                                                        @Param("size") Integer size,
                                                        @Param("userCode") String userCode,
                                                        @Param("IncludeInactive") Character includeInactive,
                                                        @Param("OrgHierId") Long OrgHierId,
                                                        @Param("reportType") String reportType,
                                                        @Param("wcrNo") String wcrNo,
                                                        @Param("jobCardNumber") String jobCardNumber
    );

//    @Query(value = "{call sp_spares_invoice_search_count(:salesInvoiceId,:customerCode,:customerName,:referenceDocument,:customerType,:salesType,:modeOfTransport,:transporter,:dealerId,:page,:size)}", nativeQuery = true)
//    Long searchSaleInvoiceCount(@Param("salesInvoiceId") Long salesInvoiceId,
//                                @Param("customerCode") String customerCode,
//                                @Param("customerName") String customerName,
//                                @Param("referenceDocument") String referenceDocument,
//                                @Param("customerType") String customerType,
//                                @Param("salesType") String salesType,
//                                @Param("modeOfTransport") String modeOfTransport,
//                                @Param("transporter") String transporter,
//                                @Param("dealerId") Long dealerId,
//                                @Param("page") Integer page,
//                                @Param("size") Integer size
//    );

    @Query(value = "{call sp_spares_invoice_get_view_header_data(:siId,:branchId)}", nativeQuery = true)
    Map<String, Object> getSparesInvoiceViewHeaderData(@Param("siId") Long siId,
                                                       @Param("branchId") Long branchId);

    @Query(value = "{call sp_spare_invoice_get_view_part_details(:siId,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getSparesInvoiceViewPartDetails(@Param("siId") Long siId,
                                                              @Param("branchId") Long branchId);
    
    @Query(value = "{call sp_spare_invoice_get_view_labour_details(:siId,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getSparesInvoiceViewLabourDetails(@Param("siId") Long siId,
                                                              @Param("branchId") Long branchId);
    
    @Query(value = "{call sp_spare_invoice_get_view_outside_charge_details(:siId,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getSparesInvoiceViewOutsideChargeDetails(@Param("siId") Long siId,
                                                              @Param("branchId") Long branchId);

    @Query(value = "{call sp_spares_invoice_view_get_sales_order_object(:siId,:branchId)}", nativeQuery = true)
    Map<String, Object> getSparesInvoiceViewSalesOrderDetails(@Param("siId") Long siId,
                                                              @Param("branchId") Long branchId);

    @Query(value = "{call sp_spares_invoice_number_autocomplete(:invoiceNumber,:userName)}", nativeQuery = true)
    List<Map<String, Object>> getSpareInvoiceNumberAutocomplete(@Param("invoiceNumber") String invoiceNumber,
                                                                @Param("userName") String userName);

    @Query(value = "{call sp_spares_invoice_job_card_details(:jobCardId,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getSpareInvoiceJobCardDetails(@Param("jobCardId") Long jobCardId,
                                                            @Param("branchId") Long branchId);
    
    @Query(value = "{call sp_spares_invoice_wcr_details(:wcrId, :claimType, :branchId)}", nativeQuery = true)
    List<Map<String, Object>> getSpareInvoiceWcrDetails(@Param("wcrId") Long jobCardId,
    													  @Param("claimType") String claimType,					
                                                            @Param("branchId") Long branchId);
    
    @Modifying
    @Query(value = "{call sp_spare_picklist_updateStatusOnInvoice(:spareInvoiceId,:branchId)}", nativeQuery = true)
    void spareInvoiceSave(@Param("spareInvoiceId") Long spareInvoiceId,
                            @Param("branchId") Long branchId);
    @Modifying
    @Query(value = "update j set invoiced_flag=:invoiceFlag,status=:status,"
    		+ "last_modified_date=getdate(),last_modified_by=:userId "
    		+ "from sp_sales_invoice s inner join sv_jobcard j on s.service_jobcard_id = j.id "
    		+ "where s.id=:spareInvoiceId and s.branch_id=:branchId", nativeQuery = true)
    Integer updateInvoiceFlagJobCard(@Param("invoiceFlag") Integer invoiceFlag,
    						@Param("status") String status,
    						@Param("spareInvoiceId") Long spareInvoiceId,
                            @Param("branchId") Long branchId,
    						@Param("userId") Long userId);
    
    
    @Modifying
    @Query(value = "update j set wcr_status=:status,modified_on=getdate(),modified_by=:userId from sp_sales_invoice s inner join wa_wcr j on s.warranty_wcr_id=j.id where s.id=:InvId", nativeQuery = true)
    Integer updateInvoiceFlagWCR(@Param("status") String status, 
    						@Param("userId") Long userId,
    						@Param("InvId") Long id);
    
    @Modifying
    @Query(value="{call SP_INVOICE_CANCELLATION(:invoiceId,:userId,:remark)}", nativeQuery = true)
    void cancelInvoice(@Param("invoiceId") Long invoiceId, @Param("userId") Long userId, @Param("remark")String remark);
}
