package com.i4o.dms.itldis.service.claim.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.service.claim.domain.ServiceClaimHdrEntity;
import com.i4o.dms.itldis.service.claim.dto.ClaimSearchDto;
import com.i4o.dms.itldis.service.claim.dto.ServiceClaimDetailExcelReport;
import com.i4o.dms.itldis.service.claim.dto.ServiceClaimSummeryExcelReport;

public interface ServiceClaimRepo extends JpaRepository<ServiceClaimHdrEntity, Long> {

	@Query(value="{call SP_CLAIM_PERIOD (:dealerId)}",nativeQuery=true)
	public Map<String, Object> getClaimPeriod(@Param("dealerId")Long dealerId);
	
	@Query(value="{call SP_CLAIM_PRODUCT_AND_SERVICE (:dealerId, :fromDate, :toDate)}",nativeQuery=true)
	public List<Map<String, Object>> getProductAndService(@Param("dealerId")Long dealerId,
			@Param("fromDate")String fromDate,
			@Param("toDate")String toDate);
	
	@Query(value="{call SP_CLAIM_DOCUMENT_DETAILS (:dealerId, :fromDate, :toDate)}",nativeQuery=true)
	public List<Map<String, Object>> getDocumentDetails(@Param("dealerId")Long dealerId,
			@Param("fromDate")String fromDate,
			@Param("toDate")String toDate);
	

	@Query(value="{call SP_CLAIM_DETAILS_VIEW (:claimId)}",nativeQuery=true)
	public List<Map<String, Object>> getClaimDetailsForView(@Param("claimId")Long claimId);
	
	@Query(value="{call SP_CLAIM_PRODUCT_AND_SERVICE_VIEW (:claimId)}",nativeQuery=true)
	public List<Map<String, Object>> getProductAndServiceForView(@Param("claimId")Long claimId);
	
	@Query(value="{call SP_CLAIM_DOCUMENT_DETAILS_VIEW (:claimId)}",nativeQuery=true)
	public List<Map<String, Object>> getDocumentDetailsForView(@Param("claimId")Long claimId);
	
	@Query(value="{call SP_SEARCH_SERVICE_CLAIM (:usercode, :fromDate, :toDate, :claimNo, :claimStatus, :page, :size, :hierId)}",nativeQuery=true)
	public List<ClaimSearchDto> getClaimSearchResult(@Param("usercode")String usercode,
			@Param("fromDate")String fromDate,
			@Param("toDate")String toDate,
			@Param("claimNo")String claimNo,
			@Param("claimStatus")String claimStatus,
			@Param("page")Integer page,
			@Param("size")Integer size,
			@Param("hierId") Long hierId);
	
	@Query(value="{call SP_CLAIM_NUMBER_SEARCH (:searchText,:userCode)}",nativeQuery=true)
	public List<Map<String, Object>> claimNumberSearch(@Param("searchText")String searchText, @Param("userCode")String userCode);

	//Suraj--07/11/2022-START
	@Query(value = "{call SP_SERVICE_CLAIM_EXCEL_REPORT (:Usercode, :ClaimNo, :ClaimStatus, :FromDate, :ToDate, :flag)}", nativeQuery=true)
	public List<ServiceClaimSummeryExcelReport> getServiceClaimSummeryExcelReport(
			@Param("Usercode") String userCode,
			@Param("ClaimNo") String ClaimNo,
			@Param("ClaimStatus") String ClaimStatus,
			@Param("FromDate") String FromDate,
			@Param("ToDate") String ToDate,
			@Param("flag") String flag);
	
	@Query(value = "{call SP_SERVICE_CLAIM_EXCEL_REPORT (:Usercode, :ClaimNo, :ClaimStatus, :FromDate, :ToDate, :flag)}", nativeQuery=true)
	public List<ServiceClaimDetailExcelReport> getServiceClaimDetailExcelReport(
			@Param("Usercode") String userCode,
			@Param("ClaimNo") String ClaimNo,
			@Param("ClaimStatus") String ClaimStatus,
			@Param("FromDate") String FromDate,
			@Param("ToDate") String ToDate,
			@Param("flag") String flag);
	//Suraj--07/11/2022-END
	
	@Query(value="{call SP_SERVICE_ACTIVITY_PROPOSAL_PENDING_FOR_APPROVAL(:userCode)}", nativeQuery=true)
    List<Map<String, Object>> getClaimPendingsForApproval(@Param("userCode") String userCode);
	
}
