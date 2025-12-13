package com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.repository;

import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.domain.ChannelFinanceIndent;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.dto.ChannelFinanceInvoiceDto;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.dto.ResponseChannelFinanceIndentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ChannelFinanceIndentRepo extends JpaRepository<ChannelFinanceIndent, Long> {
   /* @Query(value = "{call sp_getInvoices(:dealerCode,:flexiAccount,:indentAmount)}",nativeQuery = true)
    List<ResponseInvoiceDto> getInvoices(@Param("dealerCode") String dealerCode,
                                         @Param("flexiAccount") String flexiAccount,
                                         @Param("indentAmount") String indentAmount);
*/

    @Query(value = "{call sp_search_channel_finance_indent(:indentNumber,:dealerCategory,:bank,:fromDate,:toDate," +
            ":dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess,:page,:size,:userCode,:inculdeInactive,:orgHierId,:status)}", nativeQuery = true)
    List<ResponseChannelFinanceIndentDto> searchBy(
            @Param("indentNumber") String indentNumber,
           //@Param("dealerCode") String dealerCode,
            @Param("dealerCategory") String dealerCategory,
            @Param("bank") String bank,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("dealerId") Long dealerId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("kubotaEmployeeId") Long kubotaEmployeeId,
            @Param("managementAccess") Boolean managementAccess,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("userCode")String userCode,
            @Param("inculdeInactive")Character inculdeInactive,
            @Param("orgHierId") Long orgHierId,
            @Param("status")String status);

    @Query(value = "{call sp_search_channel_finance_indent_count(:indentNumber,:dealerCode,:dealerCategory,:bank,:fromDate," +
            ":toDate,:dealerId,:dealerEmployeeId,:kubotaEmployeeId,:managementAccess)}", nativeQuery = true)
    Long searchCountBy(
            @Param("indentNumber") String indentNumber,
            @Param("dealerCode") String dealerCode,
            @Param("dealerCategory") String dealerCategory,
            @Param("bank") String bank,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("dealerId") Long dealerId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,
            @Param("kubotaEmployeeId") Long kubotaEmployeeId,
            @Param("managementAccess") Boolean managementAccess);

    // ChannelFinanceIndentViewDto findByChannelFinanceId(Long channelFinanceId);
    ChannelFinanceIndent findByChannelFinanceId(Long channelFinanceId);

    @Query(value = "{call sp_sales_and_presales_channel_finance_indent_auto_indent_number(:indentNumber,:dealerId,:managementAccess,:usercode)}", nativeQuery = true)
    List<Map<String, Object>> searchIndentNumber(@Param("indentNumber") String indentNumber,
                                                 @Param("dealerId") Long dealerId,
                                                 @Param("managementAccess") Boolean managementAccess,
                                                 @Param("usercode")String usercode);

    @Query(value = "{call sp_accpac_get_channel_finanace_invoices(:dealerCode)}", nativeQuery = true)
    List<ChannelFinanceInvoiceDto> getInvoices(@Param("dealerCode") String dealerCode);

    @Query(value = "{call sp_channel_finance_indent_get_dealer_category()}", nativeQuery = true)
    List<Map<String, Object>> getCategory();

    @Query(value = "{call sp_getChannelFinApprovalButtons(:usercode,:indentnumber)}", nativeQuery = true)
    List<String> getApprovalBtnList(String usercode,String indentnumber);
    
    @Query(value = "{call sp_updateChannelIndentStatus(:indentNumber, :status, :remark, :userCode)}", nativeQuery = true)
    String updateIndentStatus(String indentNumber, String status, String remark, String userCode);
}
