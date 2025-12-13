package com.i4o.dms.itldis.salesandpresales.sales.invoice.repository;


import com.i4o.dms.itldis.salesandpresales.sales.invoice.domain.SalesInvoice;
import com.i4o.dms.itldis.salesandpresales.sales.invoice.dto.SearchInvoiceApprovalResponse;
import com.i4o.dms.itldis.salesandpresales.sales.invoice.dto.SearchInvoiceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

@Transactional
public interface InvoiceRepository extends JpaRepository<SalesInvoice, Long> {

    @Query(value = "{call sp_bank_Name(:bankName)}", nativeQuery = true)
    List<Map<String, Object>> getBankName(@Param("bankName") String bankName);

    @Query(value = "{call sp_sales_invoice_get_invoice_type()}", nativeQuery = true)
    List<Map<String, Object>> getInvoiceType();

    @Query(value = "{call sp_sales_invoice_get_invoice_status()}", nativeQuery = true)
    List<Map<String, Object>> getInvoiceStatus();

    @Query(value = "{call sp_sales_invoice_customer_autocomplete_dc_done(:customerCode,:branchId,:invoiceType)}", nativeQuery = true)
    Set<Map<String, Object>> getCustomerCodeAutoComplete(@Param("customerCode") String customerCode,
                                                         @Param("branchId") Long branchId,
    													 @Param("invoiceType") String invoiceType);

    @Query(value = "{call sp_sales_invoice_dcDetails_By_customerCode(:customerCode,:branchId)}", nativeQuery = true)
    List<Map<String, Object>> getDcDetailsByCustomerCode(@Param("customerCode") String customerCode, @Param("branchId")Long branchId);

    @Query(value = "{call sp_sales_invoice_get_materialDetails_by_dcId(:dcId)}", nativeQuery = true)
    List<Map<String, Object>> getMaterialDetailsByDcId(@Param("dcId") String dcId);

    @Query(value = "{call sp_sales_invoice_getCustomerDetails_by_customerCode(:customerCode,:branchId)}", nativeQuery = true)
    Map<String, Object> getCustomerDetailsByCustomerCode(@Param("customerCode") String customerCode, @Param("branchId")Long branchId);

    @Query(value = "{call sp_gstValues()}", nativeQuery = true)
    List<Map<String, Object>> getGstValue();

    @Query(value = "{call sp_sales_invoice_invoice_cancellation_type()}", nativeQuery = true)
    List<Map<String, Object>> getInvoiceCancellationType();

    @Query(value = "{call sp_sales_invoice_cancellation_reason()}", nativeQuery = true)
    List<Map<String, Object>> getInvoiceCancellationReason();


    @Query(value = "{call sp_sales_invoice_cancellation_other_reason()}", nativeQuery = true)
    List<Map<String, Object>> getInvoiceCancellationOtherReason();

    @Query(value = "{call sp_sales_invoice_search_invoice(:invoiceNumber,:chassisNo,:customerName,:mobileNo,:fromDate," +
            ":toDate,:enquiryNumber,:enquiryType,:invoiceStatus,:invoiceType,:product,:model,:series,:variant,:itemNo,:engineNo," +
            ":dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size,:usercode,:includeInactive,:orgHierId)}", nativeQuery = true)
    List<SearchInvoiceResponse> searchInvoice(@Param("invoiceNumber") String invoiceNumber, @Param("chassisNo") String chassisNo,
                                              @Param("customerName") String customerName, @Param("mobileNo") String mobileNo,
                                              @Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("enquiryNumber") String enquiryNumber,
                                              @Param("enquiryType") String enquiryType,
                                              @Param("invoiceStatus") String invoiceStatus, @Param("invoiceType") String invoiceType, @Param("product") String product,
                                              @Param("model") String model,
                                              @Param("series") String series, @Param("variant") String variant, @Param("itemNo") String itemNo, @Param("engineNo") String engineNo,
                                              @Param("dealerId") Long dealerId, @Param("dealerEmployeeId") Long dealerEmployeeId,
                                              @Param("kubotaEmployeeId") Long kubotaEmployeeId, @Param("managementAccess") Boolean managementAccess, @Param("page") Integer page,
                                              @Param("size") Integer size,
                                              @Param("usercode") String usercode,
                                              @Param("includeInactive") Character includeInactive,
                                              @Param("orgHierId") Long orgHierId);
    
    @Query(value="{call sp_sales_invoice_cancel_search_pendingForApproval(:userCode,:includeInactive,:orgHierId,:page,:size)}", nativeQuery = true)
    List<SearchInvoiceApprovalResponse> searchInvoiceApproval(@Param("userCode") String usercode,
    		@Param("includeInactive") Character includeInactive,
    		@Param("orgHierId") Long orgHierId,
    		@Param("page") Integer page,
            @Param("size") Integer size);

    @Query(value = "{call sp_sales_invoice_get_cancel_approval_hierarchy(:dealerId)}", nativeQuery = true)
    List<Long> getCancelApprovalHierarchy(@Param("dealerId") Long dealerId);

    @Query(value = "{call sp_sales_invoice_get_invoice(:invoiceId)}", nativeQuery = true)
    Map<String, Object> getSalesInvoiceById(@Param("invoiceId") Long invoiceId);

    @Query(value = "{call sp_sales_invoice_get_delivery_challan_by_invoice_id(:invoiceId)}",nativeQuery = true)
    List<Map<String,Object>> getDeliveryChallanDetailsByInvoiceId(@Param("invoiceId")Long invoiceId);

    @Query(value = "{call sp_sales_invoice_get_material_details_by_invoice_id(:invoiceId)}",nativeQuery = true)
    List<Map<String,Object>> getMaterialsDetailsByInvoiceId(@Param("invoiceId")Long invoiceId);

    @Query(value = "{call sp_sales_invoice_get_customer_details_by_invoice_id(:invoiceId)}", nativeQuery = true)
    Map<String, Object> getCustomerDetailsByInvoiceId(@Param("invoiceId") Long invoiceId);

    @Query(value = "{call sp_sales_invoice_get_insurance_company_details_by_invoice_id(:invoiceId)}", nativeQuery = true)
    Map<String, Object> getInsuranceCompanyDetailsByInvoiceId(@Param("invoiceId") Long invoiceId);

    @Query(value = "{call sp_sales_invoice_search_by_invoice_number(:invoiceNumber,:usercode)}",nativeQuery = true)
    List<Map<String,Object>> searchInvoiceByInvoiceNumber(@Param("invoiceNumber") String invoiceNumber,@Param("usercode") String usercode);

    @Query(value = "{call sp_sales_invoice_search_by_chassis_number(:chassisNumber,:usercode)}",nativeQuery = true)
    List<Map<String,Object>> searchInvoiceByChassisNumber(@Param("chassisNumber") String chassisNumber,@Param("usercode") String usercode);

    @Query(value = "{call sp_sales_invoice_search_by_customer_name(:customerName,:usercode)}",nativeQuery = true)
    List<Map<String,Object>> searchInvoiceByCustomerName(@Param("customerName") String customerName,@Param("usercode") String usercode);

    @Query(value = "{call sp_sales_invoice_search_by_mobile_number(:mobileNumber,:usercode)}",nativeQuery = true)
    List<Map<String,Object>> searchInvoiceByMobileNumber(@Param("mobileNumber") String mobileNumber,@Param("usercode") String usercode);

    @Query(value = "{call sp_sales_invoice_search_by_engine_number(:engineNumber,:usercode)}",nativeQuery = true)
    List<Map<String,Object>> searchInvoiceByEngineNumber(@Param("engineNumber") String engineNumber,@Param("usercode") String usercode);
    
    @Query(value = "{call sp_sales_invoice_updateDCStatus(:invoiceId)}", nativeQuery = true)
    String updateDcStatus(@Param("invoiceId")Long invoiceId);
    
    @Query(value="{call sp_sales_dc_getApprovalHierarchyDetails(:dcId,:userCode)}",nativeQuery = true)
    List<Map<String,Object>> getApprovalHierDetails(@Param("dcId") Long dcId, @Param("userCode") String userCode);
    
    @Modifying
    @Query(value="{call SP_CSB_TRANSFER(:invoiceId)}",nativeQuery = true)
    void callCsbTransfer(@Param("invoiceId")Long invoiceId);
}
