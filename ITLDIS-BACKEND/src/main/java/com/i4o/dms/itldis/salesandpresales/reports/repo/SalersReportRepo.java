package com.i4o.dms.itldis.salesandpresales.reports.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.salesandpresales.reports.domain.SalersReport;
import com.i4o.dms.itldis.salesandpresales.reports.dto.ActivityClaimStatusReportResponse;
import com.i4o.dms.itldis.salesandpresales.reports.dto.ActivityEnquiryReportResponse;
import com.i4o.dms.itldis.salesandpresales.reports.dto.ActivityPropoalStatusReportResponse;
import com.i4o.dms.itldis.salesandpresales.reports.dto.ActivityReportStatusReportReponse;
import com.i4o.dms.itldis.salesandpresales.reports.dto.EnquiryReportSearchResponse;
import com.i4o.dms.itldis.salesandpresales.reports.dto.MachineMasterReportResponse;
import com.i4o.dms.itldis.salesandpresales.reports.dto.MarketingClaimReportResponse;

public interface SalersReportRepo extends JpaRepository<SalersReport, Long>{
	
	@Query(value = "{call sp_Sales_Machine_Master_Excel_Report(:managementAccess,:dealerId,:hoUserId,:dealerEmployeeId,:model,:subModel,:product,:productGroup,:variant,:series,:itemCode,:page,:size,:hierId,:userCode,:flag)}", nativeQuery = true)
	List<MachineMasterReportResponse> machineMasterReport(@Param("managementAccess") Boolean managementAccess,
			@Param("dealerId") Long dealerId, @Param("hoUserId") Long hoUserId, @Param("dealerEmployeeId") Long dealerEmployeeId,
			@Param("model") String model, @Param("subModel") String subModel,@Param("product") String product,@Param("productGroup") String productGroup,
			@Param("variant") String variant, @Param("series") String series,  @Param("itemCode") String itemCode,
			@Param("page") Integer page, @Param("size") Integer size,  @Param("hierId") Long hierId,@Param("userCode") String userCode, @Param("flag") String flag);


	@Query(value = "{call sp_Sales_Marketing_Claim_Report(:managementAccess, :dealerId, :branchId, :product,:series,:model,:subModel,:variant,:activityFromDate,:activityToDate,:activityNumber,:activityStatus,:activityClaimNumber,:activityClaimStatus,:page,:size,:hierId,:userCode,:flag)}", nativeQuery = true)
	List<MarketingClaimReportResponse> marketingClaimReport(@Param("managementAccess") Boolean managementAccess,
			@Param("dealerId") Long dealerId,
			@Param("branchId") Long branchId, 
			@Param("product") String product, 
			@Param("series") String series, 
			@Param("model") String model, 
			@Param("subModel") String subModel,
			@Param("variant") String variant,  
			@Param("activityFromDate") String activityFromDate,
			@Param("activityToDate") String activityToDate,
			@Param("activityNumber") String activityNumber,
			@Param("activityStatus") String activityStatus,
			@Param("activityClaimNumber") String activityClaimNumber,
			@Param("activityClaimStatus") String activityClaimStatus,
			@Param("page") Integer page, 
			@Param("size") Integer size,  
			@Param("hierId") Long hierId,
			@Param("userCode") String userCode, 
			@Param("flag") String flag);

	@Query(value = "{call sp_Sales_Activity_Enquiry_Report(:managementAccess, :dealerId, :branchId, :product,:series,:model,:subModel,:variant,:activityFromDate,:activityToDate,:activityNumber,:activityStatus,:page,:size,:hierId,:userCode,:flag)}", nativeQuery = true)
	List<ActivityEnquiryReportResponse> activityEnquiryReport(@Param("managementAccess") Boolean managementAccess,
			@Param("dealerId") Long dealerId,
			@Param("branchId") Long branchId,
			@Param("product") String product, 
			@Param("series") String series, 
			@Param("model") String model, 
			@Param("subModel") String subModel,
			@Param("variant") String variant,  
			@Param("activityFromDate") String activityFromDate,
			@Param("activityToDate") String activityToDate,
			@Param("activityNumber") String activityNumber,
			@Param("activityStatus") String activityStatus,
			@Param("page") Integer page, 
			@Param("size") Integer size,  
			@Param("hierId") Long hierId,
			@Param("userCode") String userCode, 
			@Param("flag") String flag);
	
	@Query(value = "{call sp_Sales_Enquiry_Report(:managementAccess, :dealerId, :branchId, :product,:series,:model,:subModel,:variant,"
			+ ":enquiryFromDate,:enquiryToDate,:financeType,:financier,:subsidy,:page,:size,:hierId,:userCode,:flag)}", nativeQuery = true)
	List<EnquiryReportSearchResponse> enquiryReport(@Param("managementAccess") Boolean managementAccess,
			@Param("dealerId") Long dealerId,
			@Param("branchId") Long branchId,
			@Param("product") String product, 
			@Param("series") String series, 
			@Param("model") String model, 
			@Param("subModel") String subModel,
			@Param("variant") String variant,  
			@Param("enquiryFromDate") String enquiryFromDate,
			@Param("enquiryToDate") String enquiryToDate,
			@Param("financeType") String financeType,
			@Param("financier") String financier,
			@Param("subsidy") String subsidy,
			@Param("page") Integer page, 
			@Param("size") Integer size,  
			@Param("hierId") Long hierId,
			@Param("userCode") String userCode, 
			@Param("flag") String flag);
	
	@Query(value = "{call sp_Sales_Marketing_Proposal_Status_Report(:managementAccess, :dealerId, :branchId, "
			+ ":product,:series,:model,:subModel,:variant,:activityDate,:activityNumber,:activityStatus,"
			+ ":page,:size,:hierId,:userCode,:flag)}", nativeQuery = true)
	List<ActivityPropoalStatusReportResponse> activityProposalStatusReport(
			@Param("managementAccess") Boolean managementAccess,
			@Param("dealerId") Long dealerId,
			@Param("branchId") Long branchId, 
			@Param("product") String product, 
			@Param("series") String series, 
			@Param("model") String model, 
			@Param("subModel") String subModel,
			@Param("variant") String variant,  
			@Param("activityDate") String activityDate,
			@Param("activityNumber") String activityNumber,
			@Param("activityStatus") String activityStatus,
			@Param("page") Integer page, 
			@Param("size") Integer size,  
			@Param("hierId") Long hierId,
			@Param("userCode") String userCode, 
			@Param("flag") String flag);

	@Query(value = "{call sp_Sales_Marketing_Claim_Status_Report(:managementAccess, :dealerId, :branchId, :product,:series,:model,:subModel,:variant,:activityDate,:activityNumber,:activityStatus,:page,:size,:hierId,:userCode,:flag)}", nativeQuery = true)
	List<ActivityClaimStatusReportResponse> activityClaimStatusReport(
			@Param("managementAccess") Boolean managementAccess,
			@Param("dealerId") Long dealerId,
			@Param("branchId") Long branchId, 
			@Param("product") String product, 
			@Param("series") String series, 
			@Param("model") String model, 
			@Param("subModel") String subModel,
			@Param("variant") String variant,  
			@Param("activityDate") String activityDate,
			@Param("activityNumber") String activityNumber,
			@Param("activityStatus") String activityStatus,
			@Param("page") Integer page, 
			@Param("size") Integer size,  
			@Param("hierId") Long hierId,
			@Param("userCode") String userCode, 
			@Param("flag") String flag);

	@Query(value = "{call sp_Sales_Marketing_Report_Status_Report(:managementAccess, :dealerId, :branchId, "
			+ ":product,:series,:model,:subModel,:variant,:activityDate,:activityNumber,:activityStatus,"
			+ ":page,:size,:hierId,:userCode,:flag)}", nativeQuery = true)
	List<ActivityReportStatusReportReponse> activityReportStatusReport(
			@Param("managementAccess") Boolean managementAccess,
			@Param("dealerId") Long dealerId,
			@Param("branchId") Long branchId, 
			@Param("product") String product, 
			@Param("series") String series, 
			@Param("model") String model, 
			@Param("subModel") String subModel,
			@Param("variant") String variant,  
			@Param("activityDate") String activityDate,
			@Param("activityNumber") String activityNumber,
			@Param("activityStatus") String activityStatus,
			@Param("page") Integer page, 
			@Param("size") Integer size,  
			@Param("hierId") Long hierId,
			@Param("userCode") String userCode, 
			@Param("flag") String flag);
	
}
