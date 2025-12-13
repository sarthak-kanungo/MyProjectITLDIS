package com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.domain.SpareStockCurrent;
import com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.dto.AuctionPartsListReqDto;
import com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.dto.AuctionPartsListResDto;
import com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.dto.NonMovInvSearchReqDto;
import com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.dto.NonMovInvSearchResDto;
import com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.exception.StockIdNullException;
import com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.exception.StockListEmptyException;
import com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.exception.StockNotFoundException;
import com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.repository.SpareStockCurrentRepo;
import com.i4o.dms.itldis.utils.ApiResponse;

@Service
public class NonMovInventoryServiceImpl implements NonMovInventoryService {
	
	@Autowired
	private SpareStockCurrentRepo stockCurrentRepo;
	
	@Autowired
    private UserAuthentication userAuthentication;

	@Override
	public ApiResponse<?> nonMovInvSearch(NonMovInvSearchReqDto reqDto) {
		ApiResponse<List<NonMovInvSearchResDto>> apiResponse = new ApiResponse<>();
		
		List<NonMovInvSearchResDto> response = stockCurrentRepo.nonMovInvSearch(
				userAuthentication.getBranchId(),
				userAuthentication.getUsername(),
				reqDto.getAging(),
				reqDto.getItemNo());
		
		Long count = 0L;
		if (response != null && response.size() > 0) {
			count = response.get(0).getTotalCount();
		}
		
		apiResponse.setCount(count);
		apiResponse.setResult(response);
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> autoGetNonMovSpareItems(String itemStr) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(stockCurrentRepo.autoGetNonMovSpareItems(userAuthentication.getBranchId(), itemStr));
		
		return apiResponse;
	}

	@Transactional
	@Override
	public ApiResponse<?> saveNonMovInventory(List<SpareStockCurrent> stockCurrents) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		if(!stockCurrents.isEmpty()) {
			stockCurrents.forEach(currStk -> {
				if(currStk.getId() != null) {
					
					SpareStockCurrent stockCurrent = stockCurrentRepo.getOne(currStk.getId());
					if(stockCurrent != null) {
						stockCurrent.setIsNonMoving(currStk.getIsNonMoving());
						
						stockCurrentRepo.save(stockCurrent);
					}else {
						throw new StockNotFoundException();
					}
				}else {
					throw new StockIdNullException();
				}
			});
		}else {
			throw new StockListEmptyException();
		}
		
		return apiResponse;
	}
	
	
	
	@Override
	public ApiResponse<?> auctionPartsList(AuctionPartsListReqDto requestDto) {
		ApiResponse<List<AuctionPartsListResDto>> apiResponse = new ApiResponse<>();
		
		List<AuctionPartsListResDto> response = stockCurrentRepo.auctionPartsList(
				requestDto.getPage(),
				requestDto.getSize());
		
		Long count = 0L;
		if (response != null && response.size() > 0) {
			count = response.get(0).getTotalCount();
		}
		
		apiResponse.setCount(count);
		apiResponse.setResult(response);
		
		return apiResponse;
	}
	
	
	@Override
	public ApiResponse<?> nonMovInvSearchForHo(AuctionPartsListReqDto requestDto) {
		ApiResponse<List<AuctionPartsListResDto>> apiResponse = new ApiResponse<>();
		
		List<AuctionPartsListResDto> response = stockCurrentRepo.nonMovInvSearchForHo(
				requestDto.getIsAuction(),
				requestDto.getPage(),
				requestDto.getSize());
		
		Long count = 0L;
		if (response != null && response.size() > 0) {
			count = response.get(0).getTotalCount();
		}
		
		apiResponse.setCount(count);
		apiResponse.setResult(response);
		
		return apiResponse;
	}

}
