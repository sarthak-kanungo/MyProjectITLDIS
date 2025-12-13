package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferreciept.service;
import java.io.OutputStream;

import javax.transaction.Transactional;

import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferreciept.dto.BTReceiptSearchRequestDto;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferreciept.domain.SPBranchTransferReceipt;
import com.i4o.dms.itldis.utils.ApiResponse;

@Transactional
public interface SPBranchTransferReceiptService {
	
	ApiResponse<?> autoGetIssueNoInReceipt(String issueNo);
	
	ApiResponse<?> getIssueingBranchName(String issueId);

	ApiResponse<?> getReceivingBranch();
	
	ApiResponse<?> searchBTReceipt(BTReceiptSearchRequestDto receiptSearchDto);
	
	ApiResponse<?> autoPopulateReceiptNo(String receiptNo);
	
	ApiResponse<?> getReceiptItemDetails(Long issueId);
	
	ApiResponse<?> viewBTReceipt(String receiptNo);
	
	ApiResponse<?> saveBTReceipt(SPBranchTransferReceipt transferReceipt);
	
	void printBTReceiptReport(String receiptId, String printStatus, String filePath, OutputStream outputStream) throws Exception;
	
	ApiResponse<?> autoPopulateIssueNo(String issueNo);

}
