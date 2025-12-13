package com.i4o.dms.itldis.salesandpresales.sales.paymentReceipt.Repository;

import com.i4o.dms.itldis.connection.ConnectionConfiguration;
import com.i4o.dms.itldis.salesandpresales.sales.paymentReceipt.domain.PaymentReceipt;
import com.i4o.dms.itldis.salesandpresales.sales.paymentReceipt.dto.PaymentReceiptDto;
import com.i4o.dms.itldis.salesandpresales.sales.paymentReceipt.dto.PaymentReceiptList;
import com.i4o.dms.itldis.salesandpresales.sales.paymentReceipt.dto.SparePaymentReceiptResposne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.StyledEditorKit;
import java.net.Inet4Address;
import java.util.List;
import java.util.Map;

public interface PaymentReceiptRepo extends JpaRepository<PaymentReceipt, Long>,ConnectionConfiguration {


    @Query(value = "{call s_sales_payment_getReceiptType(:enquiryNumber)}", nativeQuery = true)
    List<Map<String, Object>> getReceiptType(@Param("enquiryNumber") String enquiryNumber);

    @Query(value = "{call sp_getReceiptMode()}", nativeQuery = true)
    List<Map<String, Object>> getReceiptMode();
    
    @Query(value = "{call sp_getReceiptType()}", nativeQuery = true)
    List<String> getReceiptAllType();

    @Query(value = "{call sp_getCardType()}", nativeQuery = true)
    List<Map<String, Object>> getCardType();

    @Query(value = "{call sp_getBank()}", nativeQuery = true)
    List<Map<String, Object>> getBank();

    @Query(value = "{call sp_getCheckDdBank()}", nativeQuery = true)
    List<Map<String, Object>> getCheckDdBank();


    @Query(value = "{call sp_getMarginAmount(:enquiryNo)}", nativeQuery = true)
    Map<String, Object> getMarginAmount(@Param("enquiryNo") String enquiryNo);

    @Query(value = "{call sp_getSubsidyAmount(:enquiryNo)}", nativeQuery = true)
    Map<String, Object> getSubsidyAmount(@Param("enquiryNo") String enquiryNo);

    @Query(value = "{call sp_getLoanAmount(:enquiryNo)}", nativeQuery = true)
    Map<String, Object> getLoanAmount(@Param("enquiryNo") String enquiryNo);

    @Query(value = "{call sp_getExchangeAmount(:enquiryNo)}", nativeQuery = true)
    Map<String, Object> getExchangeAmount(@Param("enquiryNo") String enquiryNo);

    @Query(value = "{call sp_getEnquiryOnPayment(:enquiryNo)}", nativeQuery = true)
    Map<String, Object> getEnquiryOnPayment(@Param("enquiryNo") String enquiryNo);


    @Query(value = "{call sp_getPaymentReceiptList(:managementAccess,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:branchId,:receiptNo,:receiptMode,:customerName,:customerMobileNo,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:itemNo,:receiptType,:usercode,:includeInactive,:page,:size)}", nativeQuery = true)
    List<PaymentReceiptList> getPaymentReceiptList(
            @Param("managementAccess") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("kaiEmployeeId") Long kaiEmployeeId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("branchId") Long branchId,
            @Param("receiptNo") String receiptNo,
            @Param("receiptMode") String receiptMode,
            @Param("customerName") String customerName,//can be prospect first name or customer first name
            @Param("customerMobileNo") String customerMobilNo,//can be prospect mobile no or customer mobile no
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("product") String product,
            @Param("series") String series,
            @Param("model") String model,
            @Param("subModel") String subModel,
            @Param("variant") String variant,
            @Param("itemNo") String itemNo,
            @Param("receiptType") String receiptType,
            @Param("usercode") String usercode,
            @Param("includeInactive")Character includeInactive,
            @Param("page") Integer page,
            @Param("size") Integer size);

    @Query(value = "{call sp_getPaymentReceiptListCount(:managementAccess,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:receiptNo,:receiptMode,:customerName,:customerMobileNo,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:itemNo,:page,:size)}", nativeQuery = true)
    Long getPaymentReceiptListCount(
            @Param("managementAccess") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("kaiEmployeeId") Long kaiEmployeeId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("receiptNo") String receiptNo,
            @Param("receiptMode") String receiptMode,
            @Param("customerName") String customerName,//can be prospect first name or customer first name
            @Param("customerMobileNo") String customerMobilNo,//can be prospect mobile no or customer mobile no
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("product") String product,
            @Param("series") String series,
            @Param("model") String model,
            @Param("subModel") String subModel,
            @Param("variant") String variant,
            @Param("itemNo") String itemNo,
            @Param("page") Integer page,
            @Param("size") Integer size);

    @Query(value = "{call sp_getReceiptNumber(:receiptNumber,:documentType,:usercode,:branchId,:includeInactive)}", nativeQuery = true)
    List<Map<String, Object>> getReceiptNumber(@Param("receiptNumber") String receiptNumber,
                                               @Param("documentType") String documentType,
                                               @Param("usercode") String usercode,
                                               @Param("branchId") Long branchId,
                                               @Param("includeInactive") Character includeInactive);

    @Query(value = "{call sp_master_searchMobileNumber(:mobileNumber,:documentType)}", nativeQuery = true)
    List<Map<String, Object>> searchMobileNumber(
            @Param("mobileNumber") String mobileNumber,
            @Param("documentType") String documentType);
            //@Param("dealerId")Long dealerId);


    @Query(value = "{call sp_master_searchCustomerName(:customerName,:documentType)}", nativeQuery = true)
    List<Map<String, Object>> searchCustomerName(@Param("customerName") String customerName,
                                                 @Param("documentType") String documentType);

    @Query(value = "{call sp_sales_checkEnquiryInPaymentReceipt(:id,:receiptType)}", nativeQuery = true)
    Integer checkEnquiryInPaymentReceipt(@Param("id") Long id, @Param("receiptType") String receiptType);


    PaymentReceiptDto findByReceiptNo(String receiptNo);

    @Query(value = "{call sp_spares_payment_receipt_type()}", nativeQuery = true)
    List<Map<String, Object>> sparesPaymentReceiptType();


    @Query(value = "{call sp_spare_payment_receipt_search(:receiptNumber,:receiptMode,:customerName,:customerContactNumber,:fromDate,:toDate,:managementAccess,:dealerId,:kubotaEmployeeId,:dealerEmployeeId,:page,:size,:UserCode,:includeInactive)}", nativeQuery = true)
    List<SparePaymentReceiptResposne> sparesPaymentReceiptSearch(
            @Param("receiptNumber") String receiptNumber,
            @Param("receiptMode") String receiptMode,
            @Param("customerName") String customerName,
            @Param("customerContactNumber") String customerContactNumber,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("managementAccess") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("kubotaEmployeeId") Long kubotaEmployeeId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("UserCode") String UserCode,
            @Param("includeInactive") char includeInactive
    );


    @Query(value = "{call sp_spare_payment_receipt_search_count(:receiptNumber,:receiptMode,:customerName,:customerContactNumber,:fromDate,:toDate,:managementAccess,:dealerId,:kubotaEmployeeId,:dealerEmployeeId,:page,:size)}", nativeQuery = true)
    Long sparesPaymentReceiptSearchCount(
            @Param("receiptNumber") String receiptNumber,
            @Param("receiptMode") String receiptMode,
            @Param("customerName") String customerName,
            @Param("customerContactNumber") String customerContactNumber,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("managementAccess") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("kubotaEmployeeId") Long kubotaEmployeeId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("page") Integer page,
            @Param("size") Integer size
    );

    @Query(value = "{call sp_spare_payment_receipt_view_details(:id,:branchId)}", nativeQuery = true)
    Map<String, Object> sparePaymentReceiptView(@Param("id") Long id, @Param("branchId") Long branchId);


}
