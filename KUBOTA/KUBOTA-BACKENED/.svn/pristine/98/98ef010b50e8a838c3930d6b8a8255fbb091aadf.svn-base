package com.i4o.dms.kubota.spares.purchase.backordercancellation.service;

import java.io.OutputStream;

import javax.transaction.Transactional;

import com.i4o.dms.kubota.spares.purchase.backordercancellation.domain.SPBackOrderCancellation;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.dto.BOCancellationApprovalRequestDto;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.dto.SPBackOrderCancellationRequestDto;
import com.i4o.dms.kubota.utils.ApiResponse;

@Transactional
public interface SPBackOrderCancellationService {
	
	ApiResponse<?> autoGetBOCNo(String bocNo);
	
	ApiResponse<?> autoGetDealerCode(String dealerCode);
	
	ApiResponse<?> getBOCItemDetails(String dealerCode);
	
	ApiResponse<?> searchBOCancellation(SPBackOrderCancellationRequestDto boCancellationDto);
	
	ApiResponse<?> searchBOCApproval(SPBackOrderCancellationRequestDto boCancellationDto);
	
	ApiResponse<?> viewBOCancellation(String bocNo);
	
	ApiResponse<?> saveBOCancellation(SPBackOrderCancellation orderCancellation);
	
	ApiResponse<?> autoCompleteDealerCode(String dealerCode);
	
	ApiResponse<?> boCancellationApproval(BOCancellationApprovalRequestDto approvalRequestModel) throws Exception;
	
	ApiResponse<?> getBOCApproverHierarchyDetails(Long backorderId);
	
	void printBOCReport(String bocId, String printStatus, String filePath, OutputStream outputStream) throws Exception;
	
}
