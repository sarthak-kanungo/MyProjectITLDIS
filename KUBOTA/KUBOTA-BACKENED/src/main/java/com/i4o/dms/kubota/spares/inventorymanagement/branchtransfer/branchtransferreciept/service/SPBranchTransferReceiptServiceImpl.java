package com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferreciept.service;

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

import com.i4o.dms.kubota.common.service.JasperPrintService;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.BinLocationMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.BinLocationMasterRepository;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferreciept.dto.BTReceiptSearchRequestDto;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferreciept.dto.BTReceiptSearchResponseDto;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.SparePartMasterDto;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferreciept.domain.SPBranchTransferReceipt;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferreciept.dto.BTReceiptItemDetailsDto;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferreciept.dto.BTReceiptViewDto;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferreciept.repository.SPBranchTransferReceiptRepo;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.repository.StockAdjustmentRepo;
import com.i4o.dms.kubota.utils.ApiResponse;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author suraj.gaur
 */
@Service
public class SPBranchTransferReceiptServiceImpl implements SPBranchTransferReceiptService {
	
	@Autowired
    private UserAuthentication userAuthentication;
	
	@Autowired
	private SPBranchTransferReceiptRepo receiptRepo;
	
	@Autowired
	private StockAdjustmentRepo stockAdjustmentRepo;
	
	@Autowired
	private BinLocationMasterRepository binLocationMasterRepo;
	
	@Autowired
	JasperPrintService jasperPrintService;
	
	@Override
	public ApiResponse<?> autoGetIssueNoInReceipt(String issueNo) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(receiptRepo.autoGetIssueNoInReceipt(userAuthentication.getBranchId(), issueNo));
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> getIssueingBranchName(String issueId) {
		ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(receiptRepo.getIssueingBranchName(issueId));
		
		return apiResponse;
	}
	
	
	@Override
	public ApiResponse<?> getReceivingBranch() {
		ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(receiptRepo.getReceivingBranch(userAuthentication.getBranchId()));
		
		return apiResponse;
	}
	
	
	@Override
	public ApiResponse<?> searchBTReceipt(BTReceiptSearchRequestDto receiptSearchDto) {
		ApiResponse<List<BTReceiptSearchResponseDto>> apiResponse = new ApiResponse<>();
		
		List<BTReceiptSearchResponseDto> responceDtoList = receiptRepo.searchBTReceipt(
				receiptSearchDto.getReceiptNo(),
				receiptSearchDto.getStatus(),
				receiptSearchDto.getFromDate(),
				receiptSearchDto.getToDate(),
				receiptSearchDto.getPage(),
				receiptSearchDto.getSize());
		
		Long count = 0l;
		if (responceDtoList != null && !responceDtoList.isEmpty()) {
			count = responceDtoList.get(0).getTotalCount();
		}
		
		apiResponse.setCount(count);
		apiResponse.setResult(responceDtoList);
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> autoPopulateReceiptNo(String receiptNo) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(receiptRepo.autoPopulateReceiptNo(userAuthentication.getBranchId(), receiptNo));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> getReceiptItemDetails(Long issueId) {
		ApiResponse<List<BTReceiptItemDetailsDto>> apiResponse = new ApiResponse<>();
		
		List<Map<String, Object>> result = receiptRepo.getReceiptItemDetails(issueId);
		
		List<BTReceiptItemDetailsDto> itemList = new ArrayList<>(); 
		
		if(result != null) {
			result.forEach(item -> {
				
				BTReceiptItemDetailsDto detailsDto = new BTReceiptItemDetailsDto();
				SparePartMasterDto sparePartMaster = new SparePartMasterDto();
				
				detailsDto.setIssuedQty((Integer)item.get("issuedQty"));
				detailsDto.setReceiptQty((Integer)item.get("receiptQty"));
				detailsDto.setReceiptMrpValue((Double)item.get("receiptMrpValue"));
				detailsDto.setItemMrp((Double)item.get("itemMrp"));
				
				sparePartMaster.setId((BigInteger)item.get("itemId"));
				sparePartMaster.setItemNo((String)item.get("itemNo"));
				sparePartMaster.setItemDescription((String)item.get("itemDescription"));
				detailsDto.setSparePartMaster(sparePartMaster);
				
				itemList.add(detailsDto);
			});
		}
		
		apiResponse.setResult(itemList);
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> viewBTReceipt(String receiptNo) {
		ApiResponse<BTReceiptViewDto> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(receiptRepo.findByReceiptNo(receiptNo));
		
		return apiResponse;
	}

	@Transactional
	@Override
	public ApiResponse<?> saveBTReceipt(SPBranchTransferReceipt transferReceipt) {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		
        if(transferReceipt.getDraftFlag()){
        	transferReceipt.setReceiptNo("DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis());
        }
        
        transferReceipt.setStatus(transferReceipt.getDraftFlag() ? "Draft" : "Submitted");
		
		if (transferReceipt.getId() == null) {
			transferReceipt.setCreatedBy(userAuthentication.getLoginId());
			transferReceipt.setCreatedDate(new Date());
		} else if(transferReceipt.getId() != null) {
			transferReceipt.setLastModifiedBy(userAuthentication.getLoginId());
			transferReceipt.setLastModifiedDate(new Date());
        }
		
		//Creating new bin location when bin is not found.
		transferReceipt.getReceiptItems().stream().forEach(stockDtl -> {
			 if(stockDtl.getBinLocationMaster().getId() == null){
				 
				 Long binId = stockAdjustmentRepo.createBinLocation(stockDtl.getSparePartMaster().getItemNo(),
						 stockDtl.getStoreMaster().getId(), stockDtl.getBinLocationMaster().getBinLocation(),
						 userAuthentication.getBranchId()).longValue();
				 
				 BinLocationMaster binLocationMaster = binLocationMasterRepo.findById(binId).orElse(null);
				 stockDtl.setBinLocationMaster(binLocationMaster);
			 }
		 });
		
		SPBranchTransferReceipt savedBTReceipt = receiptRepo.save(transferReceipt);
		
		//Managing stock details in bin table and stock tables after BT Receipt saved.
		System.out.println("Saved BTReceipt id---->>" + savedBTReceipt.getId());
        if (savedBTReceipt.getStatus().equals("Submitted")) {
        	receiptRepo.updateStockTable(savedBTReceipt.getId(), userAuthentication.getLoginId());
		}
        
		apiResponse.setResult("Submitted");
		apiResponse.setMessage(transferReceipt.getDraftFlag() 
				? "Saving Branch Transfer Receipt Successful" : "Submit Branch Transfer Reciept Successful");
		
		return apiResponse;
	}

	@Override
	public void printBTReceiptReport(String receiptId, String printStatus, String filePath, OutputStream outputStream)
			throws Exception {
		String jasperfile = filePath + "BranchTransferReceiptReport.jasper";
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("receiptId", receiptId);
    	
		JasperPrint jasperPrint = jasperPrintService.getJasperPrint(jasperfile, jasperParameter);
    	
		jasperPrintService.printPdfReport(jasperPrint, printStatus, outputStream);
	}
	
	
	@Override
	public ApiResponse<?> autoPopulateIssueNo(String issueNo) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(receiptRepo.autoPopulateIssueNo(userAuthentication.getBranchId(), issueNo));
		
		return apiResponse;
	}

}
