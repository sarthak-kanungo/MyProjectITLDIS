package com.i4o.dms.kubota.spares.inventorymanagement.nonMovementInventory.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.spares.inventorymanagement.nonMovementInventory.domain.SpareStockCurrent;
import com.i4o.dms.kubota.spares.inventorymanagement.nonMovementInventory.dto.AuctionPartsListResDto;
import com.i4o.dms.kubota.spares.inventorymanagement.nonMovementInventory.dto.NonMovInvSearchResDto;

public interface SpareStockCurrentRepo extends JpaRepository<SpareStockCurrent, Long> {

	@Query(value = "{call SP_NON_MOVEMENT_INV_SEARCH(:branchId, :usercode, :aging, :itemNo)}", nativeQuery = true)
	List<NonMovInvSearchResDto> nonMovInvSearch(
			@Param("branchId") Long branchId, 
			@Param("usercode")String usercode, 
			@Param("aging")String aging, 
			@Param("itemNo")String itemNo);
	
	@Query(value = "{call SP_AUTO_GET_NON_MOV_SPARE_ITEMS(:branchId, :itemStr)}", nativeQuery = true)
	List<Map<String, Object>> autoGetNonMovSpareItems(@Param("branchId") Long branchId, 
			@Param("itemStr") String itemStr);
	
	@Query(value = "{call SP_NON_MOVMENT_CURRENT_STOCK(:branchId)}", nativeQuery = true)
	List<Map<String, Object>> getNonMovementCurrentStock(@Param("branchId") Long branchId);
	
	List<SpareStockCurrent> findByBranchIdAndIsNonMoving(Long branchId, Boolean isNonMoving);
	
	
	@Query(value = "{call SP_All_AUCTION_PARTS_LIST(:page, :size)}", nativeQuery = true)
	List<AuctionPartsListResDto> auctionPartsList(
			@Param("page") Integer page, 
			@Param("size")Integer size);
	
	
	@Query(value = "{call SP_All_AUCTION_PARTS_LIST_FOR_HO(:isAuction, :page, :size)}", nativeQuery = true)
	List<AuctionPartsListResDto> nonMovInvSearchForHo(
			@Param("isAuction") Boolean isAuction,
			@Param("page") Integer page, 
			@Param("size")Integer size);
}
