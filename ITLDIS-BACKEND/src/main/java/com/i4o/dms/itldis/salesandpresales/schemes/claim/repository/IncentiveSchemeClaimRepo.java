package com.i4o.dms.itldis.salesandpresales.schemes.claim.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.salesandpresales.schemes.claim.domain.IncentiveSchemeClaim;
import com.i4o.dms.itldis.salesandpresales.schemes.claim.dto.IncentiveSchemeClaimSearchResDto;

public interface IncentiveSchemeClaimRepo extends JpaRepository<IncentiveSchemeClaim, Long> {

	@Query(value="{call sp_incentive_scheme_wholesale_details(:month, :usercode)}", nativeQuery = true)
	Map<String, Object> getWholesaleIncentiveSchemeDetails(@Param("month")String month, @Param("usercode")String userCode);
    
	@Query(value="{call sp_incentive_scheme_retails_details(:month, :usercode)}", nativeQuery = true)
	List<Map<String, Object>> getRetailsIncentiveSchemeDetails(@Param("month")String month, @Param("usercode")String userCode);
    
	@Query(value="{call sp_search_incentive_scheme_claim(:dealerId, :claimNo, :fromDate, :toDate, :userId, :page, :size)}", nativeQuery = true)
	List<IncentiveSchemeClaimSearchResDto> searchIncentiveSchemes(@Param("dealerId")Long dealerId, @Param("claimNo")String claimNo, @Param("fromDate")String fromDate, @Param("toDate")String toDate, @Param("userId")Long userId, @Param("page")int page, @Param("size")int size);
	
    @Query(value="{call sp_get_scheme_claim_no(:searchvalue,:userCode)}", nativeQuery = true)
    List<Map<String,Object>> getSchemeClaimNo(@Param("searchvalue")String searchvalue,@Param("userCode")String userCode);
    
    @Query(value="{call sp_incentive_scheme_invoice_generation(:claimId, :userId)}", nativeQuery = true)
    Map<String,Object> generateInvoice(@Param("claimId")Long claimId, @Param("userId")Long userId);
    
    @Modifying
    @Query(value="Update incentive_scheme_claim_hdr set file_name=:fileName, status='Invoice Uploaded', modified_date=getdate(), modified_by=:userId, invoice_uploaded_by=:userId, invoice_uploaded_on=getdate() where id=:claimId",nativeQuery = true)
    String uploadInvoice(@Param("fileName")String fileName, @Param("claimId")Integer claimId, @Param("userId")Long userId);

    @Modifying
    @Query(value = "update incentive_scheme_claim_hdr set status='Invoice Verified', invoice_verified_by=:userId, invoice_verified_on=getdate(), modified_by=:userId, modified_date=getdate() where id=:claimId", nativeQuery=true)
    String verifyInvoice(@Param("claimId")Long claimId, @Param("userId")Long userId);

    @Query(value="select file_name from incentive_scheme_claim_hdr where id=:id", nativeQuery=true)
    String getInvoiceFileName(@Param("id")Long id);
}
