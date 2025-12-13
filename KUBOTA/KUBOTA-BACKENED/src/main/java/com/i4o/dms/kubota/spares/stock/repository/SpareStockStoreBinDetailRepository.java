package com.i4o.dms.kubota.spares.stock.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.kubota.spares.reports.dto.BackOrderPartsReportsDto;
import com.i4o.dms.kubota.spares.reports.dto.ClosingStockReportDto;
import com.i4o.dms.kubota.spares.reports.dto.InventoryMovementDto;
import com.i4o.dms.kubota.spares.reports.dto.ItemMovementDto;
import com.i4o.dms.kubota.spares.reports.dto.NonMovingPartsStockReportDto;
import com.i4o.dms.kubota.spares.stock.domain.SpareStockStoreBinDetail;

@Transactional
@Repository
public interface SpareStockStoreBinDetailRepository extends JpaRepository<SpareStockStoreBinDetail, Long>{
	
	@Query(value="{call SP_RPT_CLOSING_STOCK (:onDate,:product,:model,:submodel,:series,:variant,:dealerId,"
			+ ":branchId,:stateId,:orgId,:page,:size,:usercode)}", nativeQuery=true)
	List<ClosingStockReportDto> getClosingStockReport(
			@Param("onDate")String onDate,
			@Param("product")String product,
			@Param("model")String model,
			@Param("submodel")String submodel,
			@Param("series")String series,
			@Param("variant")String variant,
			@Param("dealerId")Long dealerId,
			@Param("branchId")Long branchId,
			@Param("stateId")Long stateId,
			@Param("orgId")Long orgId,
			@Param("page")Integer page,
			@Param("size")Integer size,
			@Param("usercode")String usercode);
	

	@Query(value="{call SP_RPT_NON_MOVIN_PARTS_STOCK (:product,:model,:submodel,:series,:variant,:dealerId,"
			+ ":branchId,:stateId,:orgId,:page,:size,:usercode)}", nativeQuery=true)
	List<NonMovingPartsStockReportDto> getNonMovingPartsStockReport(
			@Param("product")String product,
			@Param("model")String model,
			@Param("submodel")String submodel,
			@Param("series")String series,
			@Param("variant")String variant,
			@Param("dealerId")Long dealerId,
			@Param("branchId")Long branchId,
			@Param("stateId")Long stateId,
			@Param("orgId")Long orgId,
			@Param("page")Integer page,
			@Param("size")Integer size,
			@Param("usercode")String usercode);
	
	@Query(value="{call SP_RPT_BAKC_ORDER_PARTS (:fromDate,:toDate,:product,:model,:submodel,:series,:variant,:dealerId,"
			+ ":branchId,:stateId,:orgId,:page,:size,:usercode)}", nativeQuery=true)
	List<BackOrderPartsReportsDto> getBackOrderPartsReport(
			@Param("fromDate")String fromDate,
			@Param("toDate")String toDate,
			@Param("product")String product,
			@Param("model")String model,
			@Param("submodel")String submodel,
			@Param("series")String series,
			@Param("variant")String variant,
			@Param("dealerId")Long dealerId,
			@Param("branchId")Long branchId,
			@Param("stateId")Long stateId,
			@Param("orgId")Long orgId,
			@Param("page")Integer page,
			@Param("size")Integer size,
			@Param("usercode")String usercode);
	
	@Query(value="{call SP_RPT_ITEM_MOVEMENT (:fromDate,:toDate,:product,:model,:submodel,:series,:variant,:partNumber,:dealerId,"
			+ ":branchId,:stateId,:orgId,:page,:size,:usercode)}", nativeQuery=true)
	List<ItemMovementDto> getItemMovementReport(
			@Param("fromDate")String fromDate,
			@Param("toDate")String toDate,
			@Param("product")String product,
			@Param("model")String model,
			@Param("submodel")String submodel,
			@Param("series")String series,
			@Param("variant")String variant,
			@Param("partNumber")String partNumber,
			@Param("dealerId")Long dealerId,
			@Param("branchId")Long branchId,
			@Param("stateId")Long stateId,
			@Param("orgId")Long orgId,
			@Param("page")Integer page,
			@Param("size")Integer size,
			@Param("usercode")String usercode);
	
	@Query(value="{call SP_RPT_INVENTORY_MOVEMENT (:fromDate,:toDate,:product,:model,:submodel,:series,:variant,:partNumber,:dealerId,"
			+ ":branchId,:stateId,:orgId,:page,:size,:usercode)}", nativeQuery=true)
	List<InventoryMovementDto> getInventoryMovementReport(
			@Param("fromDate")String fromDate,
			@Param("toDate")String toDate,
			@Param("product")String product,
			@Param("model")String model,
			@Param("submodel")String submodel,
			@Param("series")String series,
			@Param("variant")String variant,
			@Param("partNumber")String partNumber,
			@Param("dealerId")Long dealerId,
			@Param("branchId")Long branchId,
			@Param("stateId")Long stateId,
			@Param("orgId")Long orgId,
			@Param("page")Integer page,
			@Param("size")Integer size,
			@Param("usercode")String usercode);
}
