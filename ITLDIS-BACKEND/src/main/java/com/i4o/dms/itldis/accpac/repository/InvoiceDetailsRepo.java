package com.i4o.dms.itldis.accpac.repository;

import com.i4o.dms.itldis.accpac.domain.AccpacChannelFinanceInvoice;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface InvoiceDetailsRepo extends JpaRepository<AccpacChannelFinanceInvoice,Long> {

       // List<AccpacChannelFinanceInvoice> findByDealerCodeAndFlexiLoanAccountNumber(String dealerCode, String flexiLoanAccountNumber);
        //List<AccpacChannelFinanceInvoice> findByDealerMasterAndFlexiLoanAccountNumberAndStatus(DealerMaster dealerMaster, String flexiLoanAccountNumber,String status);
        AccpacChannelFinanceInvoice findByInvoiceNumber(String invoiceNumber);
       // List<AccpacChannelFinanceInvoice> findByDealerCodeAndStatusOrStatus(String dealerCode,String statusPending,String statusPartial);
         
        @Query(value = "{call sp_getInvoicesForChannelFinanceIndent(:dealerID, :noOfDays)}",nativeQuery = true)
        List<AccpacChannelFinanceInvoice> findByDealerCodeAndStatusOrStatus(@Param("dealerID") Long dealerID, @Param("noOfDays") int noOfDays);
}
