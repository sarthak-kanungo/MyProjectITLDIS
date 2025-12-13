package com.i4o.dms.kubota.service.report.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.service.report.model.CustomerMachineMasterReportResponse;
import com.i4o.dms.kubota.service.report.model.FillRatioItemReportResponseDto;
import com.i4o.dms.kubota.service.report.model.FillRatioReportResponseDto;
import com.i4o.dms.kubota.service.report.model.InstallationMonitoringBoardResponseDto;
import com.i4o.dms.kubota.service.report.model.ServiceMonitoringBoardResponseDto;
import com.i4o.dms.kubota.service.servicebooking.domain.ServiceBooking;

public interface ServiceReportRepo extends JpaRepository<ServiceBooking,Long> {

	@Query(value="Select * From FN_GetBranchesUnderUser(:userCode,:includeInactive) where DEALER_ID=(case when isnull(:dealerID,0)=0 then DEALER_ID else :dealerID end)",nativeQuery=true)
	public List<Map<String, Object>> getBranchesUnderUser(@Param("userCode")String userCode, @Param("includeInactive")Character includeInactive, @Param("dealerID")Long dealerID);
	
	@Query(value="{call SP_Get_Service_Monitoring_Board (:userCode, :dealerId, :branchId, :orgHierId, :product, "
			+ ":series, :model, :subModel, :variant, :chassis, :fromDueDate, :toDueDate, :page, :size)}", nativeQuery=true)
	public List<ServiceMonitoringBoardResponseDto> getMonitoringBoardRecords(
			@Param("userCode")String userCode,
			@Param("dealerId")Long dealerId,
			@Param("branchId")Long branchId,
			@Param("orgHierId")Long orgHierId,
			@Param("product")String product,
			@Param("series")String series,
			@Param("model")String model,
			@Param("subModel")String subModel,
			@Param("variant")String variant,
			@Param("chassis")String chassis,
			@Param("fromDueDate")String fromDueDate,
			@Param("toDueDate")String toDueDate,
			@Param("page")Integer page,
			@Param("size")Integer size);
	
	@Query(value="{call SP_Get_Installation_Monitoring_Board (:userCode, :dealerId, :branchId, :orgHierId, :product, "
			+ ":series, :model, :subModel, :variant, :chassis, :fromDueDate, :toDueDate, :page, :size)}", nativeQuery=true)
	public List<InstallationMonitoringBoardResponseDto> getInstallationMonitoringBoardRecords(
			@Param("userCode")String userCode,
			@Param("dealerId")Long dealerId,
			@Param("branchId")Long branchId,
			@Param("orgHierId")Long orgHierId,
			@Param("product")String product,
			@Param("series")String series,
			@Param("model")String model,
			@Param("subModel")String subModel,
			@Param("variant")String variant,
			@Param("chassis")String chassis,
			@Param("fromDueDate")String fromDueDate,
			@Param("toDueDate")String toDueDate,
			@Param("page")Integer page,
			@Param("size")Integer size);
	
	
	@Query(value="{call SP_RPT_FILL_RATIO (:fromDateJobcard, :toDateJobcard, :fromDateCountersales, :toDateCountersales, :dealerId, :branchId, :orgHierId, :userCode, :page, :size)}", nativeQuery=true)
	public List<FillRatioReportResponseDto> getFillRatioRecords(
			@Param("fromDateJobcard")String fromDateJobcard,
			@Param("toDateJobcard")String toDateJobcard,
			@Param("fromDateCountersales")String fromDateCountersales,
			@Param("toDateCountersales")String toDateCountersales,
			@Param("dealerId")Long dealerId,
			@Param("branchId")Long branchId,
			@Param("orgHierId")Long orgHierId,
			@Param("userCode")String userCode,
			@Param("page")Integer page,
			@Param("size")Integer size);
	
	@Query(value="{call SP_RPT_FILL_RATIO (:fromDateJobcard, :toDateJobcard, :fromDateCountersales, :toDateCountersales, :dealerId, :branchId, :orgHierId, :userCode, :page, :size, :flag)}", nativeQuery=true)
	List<FillRatioItemReportResponseDto> getFillRatioRecordsItemDetails(@Param("fromDateJobcard")String fromDateJobcard,
			@Param("toDateJobcard")String toDateJobcard,
			@Param("fromDateCountersales")String fromDateCountersales,
			@Param("toDateCountersales")String toDateCountersales,
			@Param("dealerId")Long dealerId,
			@Param("branchId")Long branchId,
			@Param("orgHierId")Long orgHierId,
			@Param("userCode")String userCode,
			@Param("page")Integer page,
			@Param("size")Integer size,
			@Param("flag")String flag);
	
	@Query(value="{call SP_RPT_CUSTOMER_MACHINE_MASTER (:customerId, :dealerId, :branchId, :orgHierId, :userCode, :fromDate, :toDate, :page, :size, :flag)}", nativeQuery=true)
	List<CustomerMachineMasterReportResponse> getCustomerMachineMasterRecords(@Param("customerId")Long customerId,
			@Param("dealerId")Long dealerId,
			@Param("branchId")Long branchId,
			@Param("orgHierId")Long orgHierId,
			@Param("userCode")String userCode,
			@Param("fromDate")String fromDate,		//Suraj--09/12/2022
			@Param("toDate")String toDate,		//Suraj--09/12/2022
			@Param("page")Integer page,
			@Param("size")Integer size,
			@Param("flag")String flag);
}	
