package com.i4o.dms.kubota.salesandpresales.grn.repository;

import com.i4o.dms.kubota.connection.ConnectionConfiguration;
import com.i4o.dms.kubota.salesandpresales.grn.domain.MachineGrn;
import com.i4o.dms.kubota.salesandpresales.grn.dto.GrnSearchResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface MachineGrnRepository extends JpaRepository<MachineGrn, Long>,ConnectionConfiguration  {

    @Query("select g.id as id,g.grnType as value from GrnType g where g.activeStatus = 'Y'")
    List<Map<String, Object>> getGrnType();

    @Query("select g.id as id,g.grnStatus as value from GrnStatus g where g.activeStatus = 'Y'")
    List<Map<String, Object>> getGrnStatus();


    @Query("select t.id as id,t.transporterName as value from Transporter t ")
    List<Map<String, Object>> getTransporterName();

    //like %:serialNo%
    @Query(value = "{call sp_sales_presale_accpac_search_invoice_no_by_dealer(:invoiceNumber, :grnType, :dealerId)}", nativeQuery = true)
    List<Map<String, Object>> searchAccPacInvoiceNumber(@Param("invoiceNumber") String invoiceNumber,
    													@Param("grnType") String grnType,
                                                        @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_presales_grn_auto_invoice_no(:invoiceNumber,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> searchGrnInvoiceNumber(@Param("invoiceNumber") String invoiceNumber,
                                                     @Param("dealerId") Long dealerId);

    @Query(value = "{call sp_presales_grn_auto_dms_grn_no(:grnNumber,:dealerEmployeeId)}", nativeQuery = true)
    List<Map<String, Object>> searchGrnNumber(@Param("grnNumber") String grnNumber, @Param("dealerEmployeeId") Long dealerEmployeeId);

    @Query(value = "{call sp_presales_grn_search_grn(:dmsGrnNumber,:grnType,:grnStatus,:invoiceNumber,:fromDate,:toDate," +
            ":dealerId,:dealerEmployeeId,:page,:size,:itemNo,:supplierType,:userCode)}", nativeQuery = true)
    List<GrnSearchResponseDto> searchGrn(@Param("dmsGrnNumber") String dmsGrnNumber, @Param("grnType") String grnType,
                                         @Param("grnStatus") String grnStatus, @Param("invoiceNumber") String invoiceNumber,
                                         @Param("fromDate") String fromDate, @Param("toDate") String toDate,
                                         @Param("dealerId") Long dealerId, @Param("dealerEmployeeId") Long dealerEmployeeId,
                                         @Param("page") Integer page, @Param("size") Integer size,
                                         @Param("itemNo") String itemNo, @Param("supplierType") String supplierType,
                                         @Param("userCode") String userCode);


    @Query(value = "{call sp_presales_grn_search_grn_count(:dmsGrnNumber,:grnType,:grnStatus,:invoiceNumber,:fromDate,:toDate," +
            ":dealerId,:dealerEmployeeId)}", nativeQuery = true)
    Long searchGrnCount(@Param("dmsGrnNumber") String dmsGrnNumber, @Param("grnType") String grnType,
                           @Param("grnStatus") String grnStatus, @Param("invoiceNumber") String invoiceNumber,
                           @Param("fromDate") String fromDate, @Param("toDate") String toDate,
                           @Param("dealerId") Long dealerId, @Param("dealerEmployeeId") Long dealerEmployeeId);
    
    @Query(value = "{call SP_GRN_CO_DEALER_INVOICE_DLT(:invoiceId, :branchId)}", nativeQuery = true)
    Map<String,Object> getInvoiceDetails( @Param("invoiceId") Long invoiceId, @Param("branchId") Long branchId);
}
