package com.i4o.dms.itldis.spares.inventorymanagement.btbt.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.spares.inventorymanagement.btbt.domain.SparePartBinTransferDTL;
import com.i4o.dms.itldis.spares.inventorymanagement.btbt.domain.SparePartBinTransferHDR;
import com.i4o.dms.itldis.spares.inventorymanagement.btbt.dto.SpareBinTransferSearchResponseDto;

@Transactional
@Repository
public interface BinToBinTransferRepository extends JpaRepository<SparePartBinTransferHDR, Long> {

	@Query(value =  "SELECT * FROM FN_GetAvailableQtyForStoreBin(:Branch_id,:itemNo,:Stockstoreid,:StockBInId)", nativeQuery = true)
	List<Map<String,Object>> getAvlQtyForStoreBin(@Param("Branch_id")Long Branch_id, @Param("itemNo")String itemNo, 
												  @Param("Stockstoreid")Long Stockstoreid, @Param("StockBInId")Long StockBInId);
	
	@Query(value =  "{call sp_spares_BinTransferNo_autocomplete(:tranferno,:usercode)}", nativeQuery = true)
	List<Map<String,Object>> searchAutoTransferNumber( @Param("tranferno") String tranferno,
            @Param("usercode") String usercode);
	
	@Query(value = "{call sp_spares_mt_get_bin_location_by_store_id(:storeId,:binLocation,:branchId,:item_no,:tranType,:SelectedBin)}", nativeQuery = true)
    List<Map<String, Object>> getBinToLocations(@Param("storeId") Long storeId,
                                                 @Param("binLocation") String binLocation,
                                                 @Param("branchId") Long branchId,
                                                 @Param("item_no") String item_no,
                                                 @Param("tranType") String tranType,
                                                 @Param("SelectedBin") String SelectedBin);
	
	@Query(value="{call sp_spares_UpdateStockBin2BinDetail(:branchId,:itemNo,:stockStoreid,:stockBInId,:transactiontype,:refTransHdrId,:transferQty,:toStockStoreid,:toStockBInId,:userId)}", nativeQuery=true)
	String sparesUpdateStockBin2BinDetail(@Param("branchId") Long branchId,
            @Param("itemNo") String itemNo,
            @Param("stockStoreid") Long stockStoreid,
            @Param("stockBInId") Long stockBInId,
            @Param("transactiontype") String transactiontype,
            @Param("refTransHdrId") Long refTransHdrId,
            @Param("transferQty") Integer transferQty,
            @Param("toStockStoreid") Long toStockStoreid,
            @Param("toStockBInId") Long toStockBInId,
            @Param("userId") Long userId);
	
	@Query(value="{call sp_spares_binTransfer_search(:trnasferNo,:fromDate,:toDate,:branchId,:dealerEmpId,:page,:size,:userId)}", nativeQuery=true)
	List<SpareBinTransferSearchResponseDto> getBinTransferSearchResult(
																	@Param("trnasferNo")String trnasferNo,
																	@Param("fromDate")String fromDate,
																	@Param("toDate")String toDate,
																	@Param("branchId")Long branchId,
																	@Param("dealerEmpId")Long dealerEmpId,
																	@Param("page")Integer page,
																	@Param("size")Integer size,
																	@Param("userId")String userId);
	
	@Query(value="{call sp_spare_bin2bin_get_item_details_by_excel(:itemList,:qtyList,:fromStoreList,:toStoreList,:fromLocationList,:toLocationList,:existingItemsID,:branchId)}", nativeQuery=true)
	List<Map<String, Object>> uploadExcelBinTransfer(
			@Param("itemList")String itemList,
			@Param("qtyList")String qtyList,
			@Param("fromStoreList")String fromStoreList,
			@Param("toStoreList")String toStoreList,
			@Param("fromLocationList")String fromLocationList,
			@Param("toLocationList")String toLocationList,
			@Param("existingItemsID")String existingItemsID,
			@Param("branchId")Long branchId);
}
