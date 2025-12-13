package com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.service;

import java.io.OutputStream;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.domain.SpPartDiscrepancyClaimHdr;
import com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.dto.ClaimApprovalRequestDto;
import com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.dto.DiscrepancyClaimSearchRequestDto;
import com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.dto.PrintPdfRequestDto;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * @author suraj.gaur
 */
@Transactional
public interface SpPartDiscrepancyClaimService {
	
	ApiResponse<?> saveDiscrepancyClaim(SpPartDiscrepancyClaimHdr discrepancyClaim, List<MultipartFile> multipartFileList);
	
	ApiResponse<?> autoGetDiscrepancyClaimNo(String claimNoString);
	
	ApiResponse<?> autoGetDiscrepancyGrnNo(String descrClaimType,String grnStr);
	
	ApiResponse<?> getDiscrepancyItems(Long grnId);
	
	ApiResponse<?> getHeaderGrnDetails(Long grnId);

	ApiResponse<?> autoGetGrnItemsNo(Long grnId, String itemStr);

	ApiResponse<?> getGrnItemDiscrDetails(Long grnId, String itemNo);

	ApiResponse<?> searchDiscrepancyClaim(DiscrepancyClaimSearchRequestDto requestDto);

	ApiResponse<?> getDiscrepancyClaimView(String discrClaimNo);

	ApiResponse<?> claimApproveReject(ClaimApprovalRequestDto approvalRequestDto);

	void printDMCReport(PrintPdfRequestDto printPdfDto, String filePath, OutputStream outputStream) throws Exception;

}
