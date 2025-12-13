package com.i4o.dms.kubota.accpac.repository;

import com.i4o.dms.kubota.accpac.domain.AccPacInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface AccPacInvoiceRepository extends JpaRepository<AccPacInvoice,Long> {

    AccPacInvoice findByInvoiceNumber(String invoiceNo);

    @Query(value = "{call sp_sales_grn_get_acc_pac_invoice_details(:invoiceNumber, :grnType, :branchId)}",nativeQuery = true)
    List<Map<String,Object>> getAccPacInvoiceDetails(@Param("invoiceNumber") String invoiceNumber,
    		@Param("grnType") String grnType, @Param("branchId")Long branchId);
}
