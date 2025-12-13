package com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferissue.service;

import java.io.OutputStream;

import javax.transaction.Transactional;

import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferissue.domain.SPBranchTransferIssue;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.BTIssueSearchRequestDto;
import com.i4o.dms.kubota.utils.ApiResponse;

@Transactional
public interface SPBranchTransferIssueService {
	
	ApiResponse<?> getIssueToBranchDetails();
	
	ApiResponse<?> getIssueingBranch();
	
	ApiResponse<?> getIndentNos(Long reqFromBranchId);
	
	ApiResponse<?> autoGetIssueNo(String issueNo);
	
	ApiResponse<?> getIndentItemsDetailsByIds(String indentIds);
	
	ApiResponse<?> saveBTIssue(SPBranchTransferIssue branchTransferIssue);
	
	ApiResponse<?> viewBTIssue(String issueNo);
	
	ApiResponse<?> searchBTIssue(BTIssueSearchRequestDto issueSearchDto);
	
	ApiResponse<?> autoGetIndentNo(String indentNo);
	
	ApiResponse<?> getSelectedIndentNos(Long issueId);
	
	void printBTIssueReport(String issueId, String printStatus, String filePath, OutputStream outputStream) throws Exception;
	
}
