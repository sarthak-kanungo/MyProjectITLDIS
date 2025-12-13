package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.service;

import java.io.IOException;
import java.io.OutputStream;

import javax.transaction.Transactional;

import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.domain.SPBranchTransferIndent;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.dto.BTIndentSearchRequestDto;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * @author suraj.gaur
 */
@Transactional
public interface SPBranchTransferIndentService {
	
	String[] PreDefinedColumns = new String[]{"Item No","Quantity"};
	
	ApiResponse<?> findSubBranch();
	
	ApiResponse<?> getReqToBranchDeatilsById();
	
	ApiResponse<?> getReqByEmployeeDetail();
	
	ApiResponse<?> getAllStatus();
	
	ApiResponse<?> autoGetIndentNo(String indentNo);
	
	ApiResponse<?> saveBTIndent(SPBranchTransferIndent branchTransferIndent);
	
	ApiResponse<?> getSpareItemDetails(String itemNo, Long suppliedByBranchId);
	
	ApiResponse<?> autoCompleteItemNumber(String itemNo);
	
	ApiResponse<?> uploadExcel(MultipartFile file, Long suppByBranchId, String existingItems) throws IOException;
	
	ApiResponse<?> searchBTIndent(BTIndentSearchRequestDto indentSearchDto);
	
	ApiResponse<?> viewBTIndentByReqNo(String reqNo);
	
	void printBTIndentReport(String reqId, String printStatus, String filePath, OutputStream outputStream) throws Exception;
	
}
