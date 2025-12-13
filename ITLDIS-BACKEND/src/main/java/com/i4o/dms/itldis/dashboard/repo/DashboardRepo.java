package com.i4o.dms.itldis.dashboard.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.dashboard.dto.DashboardActivityReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardComplaintReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardInstallationReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardJobCardReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardPresaleServiceReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardSalesActivityReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardSalesComplaintReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardSalesEnquiryReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardWarrantyActivityReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardWarrantyComplaintReportDto;
import com.i4o.dms.itldis.dashboard.dto.DashboardWarrantyEnquiryReportDto;
import com.i4o.dms.itldis.dashboard.dto.SalesRetailReportDto;
import com.i4o.dms.itldis.dashboard.dto.SalesStockReportDto;
import com.i4o.dms.itldis.dashboard.dto.WarrantyRetailReportDto;
import com.i4o.dms.itldis.service.servicebooking.domain.ServiceBooking;

public interface DashboardRepo  extends JpaRepository<ServiceBooking,Long>{
	
	@Query(value = "{call sp_dashboard_jobcard_report(:userCode,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:dealerId,:orgHierId,:branchId)}", nativeQuery = true)
	List<DashboardJobCardReportDto> getDashboardJobCardReport(@Param("userCode") String userCode,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("branchId") Long branchId);

	@Query(value = "{call sp_dashboard_service_complaint_report(:userCode,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:dealerId,:orgHierId,:branchId)}", nativeQuery = true)
	List<DashboardComplaintReportDto> getDashboardServiceComplaintReport(@Param("userCode") String userCode,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("branchId") Long branchId);
	
	@Query(value = "{call sp_dashboard_service_activity_report(:userCode,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:dealerId,:orgHierId,:branchId)}", nativeQuery = true)
	List<DashboardActivityReportDto> getDashboardServiceActivityReport(@Param("userCode") String userCode,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("branchId") Long branchId);
	
	@Query(value = "{call sp_dashboard_service_installation_report(:userCode,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:dealerId,:orgHierId,:branchId)}", nativeQuery = true)
	List<DashboardInstallationReportDto> getDashboardInstallationReport(@Param("userCode") String userCode,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("branchId") Long branchId);
	
	@Query(value = "{call sp_dashboard_presales_service_report(:userCode,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:dealerId,:orgHierId,:branchId)}", nativeQuery = true)
	List<DashboardPresaleServiceReportDto> getDashboardPresalesServiceReport(@Param("userCode") String userCode,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("branchId") Long branchId);
	
	@Query(value = "{call sp_dashboard_sales_enquiry_report(:userCode,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:dealerId,:orgHierId,:branchId)}", nativeQuery = true)
	List<DashboardSalesEnquiryReportDto> getDashboardEnquiryReport(@Param("userCode") String userCode,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("branchId") Long branchId);
	
	@Query(value = "{call sp_dashboard_sales_complaint_report(:userCode,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:dealerId,:orgHierId,:branchId)}", nativeQuery = true)
	List<DashboardSalesComplaintReportDto> getDashboardSalesComplaintReport(@Param("userCode") String userCode,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("branchId") Long branchId);
	
	@Query(value = "{call sp_dashboard_sales_activity_dealer_own_report(:userCode,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:dealerId,:orgHierId,:branchId)}", nativeQuery = true)
	List<DashboardSalesActivityReportDto> getDashboardSalesActivityDealerOwnReport(@Param("userCode") String userCode,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("branchId") Long branchId);

	@Query(value = "{call sp_dashboard_sales_activity_kubota_support_report(:userCode,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:dealerId,:orgHierId,:branchId)}", nativeQuery = true)
	List<DashboardSalesActivityReportDto> getDashboardSalesActivityKubotaSupportReport(@Param("userCode") String userCode,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("branchId") Long branchId);

	@Query(value = "{call sp_dashboard_sales_stock_report(:salesReportOption, :userCode,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:dealerId,:orgHierId,:branchId)}", nativeQuery = true)
	List<SalesStockReportDto> getSalesStockReport(@Param("salesReportOption") String salesReportOption,
			@Param("userCode") String userCode,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("branchId") Long branchId);
	
	@Query(value = "{call sp_dashboard_sales_retail_report(:salesReportOption, :userCode,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:dealerId,:orgHierId,:branchId)}", nativeQuery = true)
	List<SalesRetailReportDto> getSalesRetailReport(@Param("salesReportOption") String salesReportOption,
			@Param("userCode") String userCode,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("branchId") Long branchId);
	
	@Query(value = "{call sp_dashboard_warranty_report(:userCode,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:dealerId,:orgHierId,"
			+ ":branchId,:reportType)}", nativeQuery = true)
	List<Map<String,Object>> getDashboardWarrantyReport(
			@Param("userCode") String userCode,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("branchId") Long branchId,
			@Param("reportType") String reportType);
	
	@Query(value = "{call sp_dashboard_marketing_report(:ReportOption,:userCode,:fromDate,:toDate,:product,:series,:model,:subModel,:variant,:dealerId,:orgHierId,:branchId,:reportType)}", nativeQuery = true)
	List<Map<String,Object>> getDashboardMarketingReport(@Param("ReportOption") String ReportOption,
			@Param("userCode") String userCode,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("product") String product,
			@Param("series") String series,
			@Param("model") String model,
			@Param("subModel") String subModel,
			@Param("variant") String variant,
			@Param("dealerId") Long dealerId,
			@Param("orgHierId") Long orgHierId,
			@Param("branchId") Long branchId,
			@Param("reportType") String reportType);
	}
