package com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.service;

import java.util.List;

import com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.domain.SpareStockCurrent;
import com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.dto.AuctionPartsListReqDto;
import com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.dto.NonMovInvSearchReqDto;
import com.i4o.dms.itldis.utils.ApiResponse;

public interface NonMovInventoryService {

	ApiResponse<?> nonMovInvSearch(NonMovInvSearchReqDto reqDto);

	ApiResponse<?> autoGetNonMovSpareItems(String itemStr);

	ApiResponse<?> saveNonMovInventory(List<SpareStockCurrent> stockCurrents);
	
	ApiResponse<?> auctionPartsList(AuctionPartsListReqDto requestDto);
	
	ApiResponse<?> nonMovInvSearchForHo(AuctionPartsListReqDto requestDto);

}
