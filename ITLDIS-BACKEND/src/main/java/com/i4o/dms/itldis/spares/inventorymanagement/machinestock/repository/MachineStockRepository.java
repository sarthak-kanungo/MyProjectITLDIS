package com.i4o.dms.itldis.spares.inventorymanagement.machinestock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.spares.inventorymanagement.machinestock.domain.MachineStockSearchResponse;
import com.i4o.dms.itldis.spares.stock.domain.SpareStockStoreBinDetail;

public interface MachineStockRepository extends JpaRepository<SpareStockStoreBinDetail, Long>{

	@Query(value="{call sp_sales_machine_stock_search(:product,:series,:model,:subModel,:variant,:itemno,:engineNo,:chassisNo,:orgHierId,:userCode,:invFromDate,:invToDate,:grnDoneFlag,:includeInactive,:page,:size,:dealercode)}", nativeQuery=true)
	List<MachineStockSearchResponse> getMachineStockSearchResult(@Param("dealercode")String dealercode,
															@Param("product")String product,
															@Param("series")String series,
															@Param("model")String model,
															@Param("subModel")String subModel,
															@Param("variant")String variant,
															@Param("itemno")String itemno,
															@Param("engineNo")String engineNo,
															@Param("chassisNo")String chassisNo,
															@Param("orgHierId")Long orgHierId,
															@Param("userCode")String userCode,
															@Param("invFromDate")String invFromDate,	//Suraj--13-12-2022
															@Param("invToDate")String invToDate,	//Suraj--13-12-2022
															@Param("grnDoneFlag")String grnDoneFlag,	//Suraj--02-01-2023
															@Param("includeInactive")String includeInactive,
															@Param("page")Integer page,
															@Param("size")Integer size);
	
}
