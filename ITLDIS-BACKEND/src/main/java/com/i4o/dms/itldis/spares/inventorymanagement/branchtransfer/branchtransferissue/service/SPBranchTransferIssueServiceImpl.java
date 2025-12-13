package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.service;

import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4o.dms.itldis.common.service.JasperPrintService;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.domain.SPBranchTransferIssue;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.BTIssueSearchRequestDto;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.BTIssueSearchResponseDto;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.BTIssueViewDto;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.BranchTransferIndentDto;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.IndentItemDetailsDto;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.SparePartMasterDto;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferissue.repository.SPBranchTransferIssueRepo;
import com.i4o.dms.itldis.utils.ApiResponse;

import net.sf.jasperreports.engine.JasperPrint;

@Service
public class SPBranchTransferIssueServiceImpl implements SPBranchTransferIssueService {

	@Autowired
    private UserAuthentication userAuthentication;
	
	@Autowired
	private SPBranchTransferIssueRepo issueRepo;
	
	@Autowired
	private JasperPrintService jasperPrintService;
	
	@Override
	public ApiResponse<?> getIssueToBranchDetails() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(issueRepo.getIssueToBranchDetails(userAuthentication.getBranchId()));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> getIssueingBranch() {
		ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(issueRepo.getIssueingBranch(userAuthentication.getBranchId()));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> getIndentNos(Long reqFromBranchId) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(issueRepo.getIndentNos(reqFromBranchId, userAuthentication.getBranchId()));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> autoGetIssueNo(String issueNo) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(issueRepo.autoGetIssueNo(issueNo));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> getIndentItemsDetailsByIds(String indentIds) {
		ApiResponse<List<IndentItemDetailsDto>> apiResponse = new ApiResponse<>();
		
		List<Map<String, Object>> result = issueRepo.getIndentItemsDetailsByIds(indentIds, userAuthentication.getBranchId());
		
		List<IndentItemDetailsDto> itemDetailsDtos = new ArrayList<>(); 
		
		if(result != null) {
			result.forEach(item -> {
				IndentItemDetailsDto detailsDto = new IndentItemDetailsDto();
				BranchTransferIndentDto transferIndent = new BranchTransferIndentDto();
				SparePartMasterDto sparePartMaster = new SparePartMasterDto();
				
				detailsDto.setCurrentStockQty((Integer)item.get("currentStockQty"));
				detailsDto.setReqQty((BigInteger)item.get("reqQty"));
				detailsDto.setItemMrp((Double)item.get("itemMrp"));
				
				transferIndent.setId((BigInteger)item.get("indentId"));
				detailsDto.setTransferIndent(transferIndent);
				
				sparePartMaster.setId((BigInteger)item.get("itemId"));
				sparePartMaster.setItemNo((String)item.get("itemNo"));
				sparePartMaster.setItemDescription((String)item.get("itemDescription"));
				detailsDto.setSparePartMaster(sparePartMaster);
				
				itemDetailsDtos.add(detailsDto);
			});
		}
		
		apiResponse.setResult(itemDetailsDtos);
		
		return apiResponse;
	}

	@Transactional
	@Override
	public ApiResponse<?> saveBTIssue(SPBranchTransferIssue branchTransferIssue) {
		ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<>();
		
        if(branchTransferIssue.getDraftFlag()){
        	branchTransferIssue.setIssueNo("DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis());
        }
        
        branchTransferIssue.setStatus(branchTransferIssue.getDraftFlag() ? "Draft" : "Submitted");
		
		if (branchTransferIssue.getId() == null) {
			branchTransferIssue.setCreatedBy(userAuthentication.getLoginId());
			branchTransferIssue.setCreatedDate(new Date());
		} else if(branchTransferIssue.getId() != null) {
			branchTransferIssue.setLastModifiedBy(userAuthentication.getLoginId());
			branchTransferIssue.setLastModifiedDate(new Date());
        }
		
		SPBranchTransferIssue savedBTIssue = issueRepo.save(branchTransferIssue);
		
		//Managing stock details in bin table and stock tables.
		System.out.println("Saved BT Issue id---->>" + savedBTIssue.getId());
        if (savedBTIssue.getStatus().equals("Submitted")) {
        	List<Map<String,Object>> result = issueRepo.updateStockTable(savedBTIssue.getId(), userAuthentication.getLoginId());
        	
        	apiResponse.setResult(result);
		}
        
		apiResponse.setMessage(branchTransferIssue.getDraftFlag() 
				? "Saving Branch Transfer Issue Successful" : "Submit Branch Transfer Issue Successful");
		
		return apiResponse;
	}
	
	

	@Override
	public ApiResponse<?> viewBTIssue(String issueNo) {
		ApiResponse<BTIssueViewDto> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(issueRepo.findByIssueNo(issueNo));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> searchBTIssue(BTIssueSearchRequestDto issueSearchDto) {
		ApiResponse<List<BTIssueSearchResponseDto>> apiResponse = new ApiResponse<>();
		
		List<BTIssueSearchResponseDto> responceDtoList = issueRepo.searchBTIssue(
				issueSearchDto.getIssueNo(),
				issueSearchDto.getStatus(),
				issueSearchDto.getFromDate(),
				issueSearchDto.getToDate(),
				issueSearchDto.getPage(),
				issueSearchDto.getSize());
		
		Long count = 0l;
		if (responceDtoList != null && !responceDtoList.isEmpty()) {
			count = responceDtoList.get(0).getTotalCount();
		}
		
		apiResponse.setCount(count);
		apiResponse.setResult(responceDtoList);
		
		return apiResponse;
	}
	
	@Override
	public void printBTIssueReport(String issueId, String printStatus, String filePath,
			OutputStream outputStream) throws Exception {
		String jasperfile = filePath + "BranchTransferIssueReport.jasper";
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("issueId", issueId);
    	
		JasperPrint jasperPrint = jasperPrintService.getJasperPrint(jasperfile, jasperParameter);
    	
		jasperPrintService.printPdfReport(jasperPrint, printStatus, outputStream);
	}

	@Override
	public ApiResponse<?> autoGetIndentNo(String indentNo) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(issueRepo.autoGetIndentNo(indentNo));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> getSelectedIndentNos(Long issueId) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(issueRepo.getSelectedIndentNos(issueId));
		
		return apiResponse;
	}
	
}
