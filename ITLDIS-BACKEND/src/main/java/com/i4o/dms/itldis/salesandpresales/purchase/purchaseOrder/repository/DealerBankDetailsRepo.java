package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.repository;

import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain.DealerBankDetails;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain.DealerBankDetailsId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DealerBankDetailsRepo extends JpaRepository<DealerBankDetails,Long> {

    @Query(value = "{call sp_getChannelFinanceAvailable(:dealerCode)}",nativeQuery = true)
    Map<String,Object> getchannelfinanceavailable(@Param("dealerCode") String dealerCode);

    @Query(value = "{call sp_getDealerBankDetailsByBankAndDealer(:dealerCode,:bankName)}",nativeQuery = true)
    Map<String,Object> getDealerBankDetailsByBankAndDealer(@Param("dealerCode") String dealerCode,
                                                           @Param("bankName") String bankName);

    @Query(value = "{call sp_bankListByDealer(:dealerCode)}",nativeQuery = true)
    List<Map<String,Object>> getBankListByDealer(@Param("dealerCode") String dealerCode);

    /*DealerBankDetails findByFlexiLoanAccountNumberAndDealerBankDetailsIdDealerCode(String flexiLoanAccountNumber,
                                                                String dealerCode);
*/
    @Query(value = "select * from ADM_DEALER_BANK_FINANCE_MAP where dealer_code = :dealerCode and bank_name = :bankName and flexi_loan_account_number = :flexiLoanAccountNumber", nativeQuery = true)
    DealerBankDetails findByDealerBankDetailsIdDealerCode(String dealerCode, String bankName, String flexiLoanAccountNumber);        
    
    @Query(value = "{call sp_getDealerCategory()}", nativeQuery = true)
    List<Map<String,Object>> getDealerCategory();

    @Query(value = "{call sp_getBankList()}", nativeQuery = true)
    List<Map<String,Object>> getBankList();

    @Query(value = "{call sp_dealer_bank_finance_get_DealerBankDetails_By_dealerId(:dealerId,:bankName)}", nativeQuery = true)
    DealerBankDetails getBankDetailsByDealerId(@Param("dealerId")Long dealerId, @Param("bankName")String bankName);

    @Query(value = "{call sp_sales_and_presales_dealer_bank_details_get_total_and_available_by_dealer_code(:dealerCode)}", nativeQuery = true)
    Map<String,Object> getDealerBankAvailableForPo(@Param("dealerCode") String dealerCode);
        
    @Transactional
    @Modifying
    @Query(value = "update ADM_DEALER_BANK_FINANCE_MAP set cf_credit_limit = :cfCreditLimit, utilised_limit = :utilisedLimit, "
    		+ "available_amount = :availableAmount, last_modified_by = :lastModifiedBy, last_modified_date= :lastModifiedDate where dealer_code = :dealerCode", nativeQuery = true)
    void updateChannelFinanceLimitUpload(@Param("cfCreditLimit") double cfCreditLimit, @Param("utilisedLimit") double utilisedLimit, 
    		@Param("availableAmount") double availableAmount, @Param("dealerCode") String dealerCode, @Param("lastModifiedBy") Long lastModifiedBy, @Param("lastModifiedDate") Date lastModifiedDate);
}
