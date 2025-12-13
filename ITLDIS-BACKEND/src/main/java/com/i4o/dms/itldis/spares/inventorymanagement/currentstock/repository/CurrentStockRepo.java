package com.i4o.dms.itldis.spares.inventorymanagement.currentstock.repository;


import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.spares.inventorymanagement.btbt.domain.SparePartBinTransferHDR;
import com.i4o.dms.itldis.spares.inventorymanagement.currentstock.dto.CurrentStockResponse;
import com.i4o.dms.itldis.spares.stock.domain.SpareStockStoreBinDetail;

@Transactional
@Repository
public interface CurrentStockRepo extends JpaRepository<SpareStockStoreBinDetail, Long> {


//	@Query(value =  "{call sp_spares_BinTransferNo_autocomplete(:tranferno,:usercode)}", nativeQuery = true)
//	List<Map<String,Object>> searchAutoTransferNumber( @Param("tranferno") String tranferno,
//            @Param("usercode") String usercode);


	@Query(value="{call sp_spare_Current_Stock_Search(:itemno,:branchId)}", nativeQuery=true)
	List<CurrentStockResponse> getCurrentStockSearchResult(
																	@Param("itemno")String itemno,
																	@Param("branchId")Long branchId);
	
}
