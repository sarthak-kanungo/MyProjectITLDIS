package com.i4o.dms.kubota.spares.purchase.backordercancellation.service;

import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4o.dms.kubota.common.service.JasperPrintService;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.spares.inventorymanagement.branchtransfer.branchtransferissue.dto.SparePartMasterDto;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.domain.SPBackOrderCancellation;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.domain.SPBackOrderCancellationApproval;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.dto.SPBackOrderCancellationViewDto;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.dto.SpPurchaseOrderDto;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.dto.BOCancellationApprovalRequestDto;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.dto.SPBackOrderCancellationItemsDto;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.dto.SPBackOrderCancellationRequestDto;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.dto.SPBackOrderCancellationResponseDto;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.repository.SPBackOrderCancellationApprovalRepo;
import com.i4o.dms.kubota.spares.purchase.backordercancellation.repository.SPBackOrderCancellationRepo;
import com.i4o.dms.kubota.utils.ApiResponse;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author suraj.gaur
 */
@Service
public class SPBackOrderCancellationServiceImpl implements SPBackOrderCancellationService {
	
    @Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    SPBackOrderCancellationRepo cancellationRepo;
    
    @Autowired
	SPBackOrderCancellationApprovalRepo approvalRepo;
	
	@Autowired
    private JasperPrintService jasperPrintService;

	@Override
	public ApiResponse<?> autoGetBOCNo(String bocNo) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(cancellationRepo.autoGetBOCNo(bocNo));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> autoGetDealerCode(String dealerCode) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();		
		
		apiResponse.setResult(cancellationRepo.autoGetDealerCode(dealerCode, userAuthentication.getDealerId()));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> getBOCItemDetails(String dealerCode) {
		ApiResponse<List<SPBackOrderCancellationItemsDto>> apiResponse = new ApiResponse<>();
		
		List<Map<String, Object>> list = cancellationRepo.getBOCItemDetails(dealerCode);
		List<SPBackOrderCancellationItemsDto> itemsDto = new ArrayList<>();
		
		if(list != null && !list.isEmpty()) {
			list.forEach(item -> {
				SPBackOrderCancellationItemsDto itemDetails = new SPBackOrderCancellationItemsDto();
				SparePartMasterDto partMasterDto = new SparePartMasterDto();
				SpPurchaseOrderDto orderDto = new SpPurchaseOrderDto();
				
				partMasterDto.setId((BigInteger)item.get("itemId"));
				partMasterDto.setItemNo((String)item.get("itemNo"));
				partMasterDto.setItemDescription((String)item.get("itemDescription"));
				itemDetails.setSparePartMaster(partMasterDto);
				
				orderDto.setId((BigInteger)item.get("poId"));
				orderDto.setPurchaseOrderNumber((String)item.get("purchaseOrderNumber"));
				itemDetails.setPurchaseOrder(orderDto);
				
				itemDetails.setAccpacPoNo((String)item.get("accpacOrderNo"));
				itemDetails.setPendingOrderQty((Integer)item.get("pendingOrderQty"));
				itemDetails.setCancelQty((Integer)item.get("cancelQty"));
				itemDetails.setKaiAcceptedQty((Integer)item.get("kaiAcceptedQty"));	
				
				itemsDto.add(itemDetails);
			});
		}
		
		apiResponse.setResult(itemsDto);
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> searchBOCancellation(SPBackOrderCancellationRequestDto boCancellationDto) {
		ApiResponse<List<SPBackOrderCancellationResponseDto>> apiResponse = new ApiResponse<>();
		
		List<SPBackOrderCancellationResponseDto> responceDtoList = cancellationRepo.spBackOrderCancellationSearch(
				boCancellationDto.getBocno(),
				boCancellationDto.getDealercode(),
				boCancellationDto.getFromDate(),
				boCancellationDto.getToDate(),
				boCancellationDto.getPage(),
				boCancellationDto.getSize(),
				userAuthentication.getUsername(),
				'N');
		
		Long count = 0l;
		if (responceDtoList != null && !responceDtoList.isEmpty()) {
			count = responceDtoList.get(0).getTotalCount();
		}
		
		apiResponse.setCount(count);
		apiResponse.setResult(responceDtoList);
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> viewBOCancellation(String bocNo) {
		ApiResponse<SPBackOrderCancellationViewDto> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(cancellationRepo.findByBocNo(bocNo));
		
		return apiResponse;
	}

	@Transactional
	@Override
	public ApiResponse<?> saveBOCancellation(SPBackOrderCancellation orderCancellation) {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		
        if(orderCancellation.getDraftFlag()){
        	orderCancellation.setBocNo("DRA/"+ ThreadLocalRandom.current().nextInt(1000) + "/" + System.currentTimeMillis());
        }
        
        orderCancellation.setStatus(orderCancellation.getDraftFlag() ? "Draft" : "Pending for Approval");
		
		if(orderCancellation.getId() == null) {
			orderCancellation.setCreatedBy(userAuthentication.getLoginId());
			orderCancellation.setCreatedDate(new Date());
		} else if(orderCancellation.getId() != null) {
			orderCancellation.setLastModifiedBy(userAuthentication.getLoginId());
			orderCancellation.setLastModifiedDate(new Date());
        }

		cancellationRepo.save(orderCancellation);

		List<SPBackOrderCancellationApproval> approvalList = new ArrayList<SPBackOrderCancellationApproval>();
		cancellationRepo.getBackOrderCancellationApprovalHierarchyLevel()
				.forEach(map -> {
					SPBackOrderCancellationApproval approval = new SPBackOrderCancellationApproval();
					approval.setBocId(orderCancellation.getId());
					approval.setApproverLevelSeq((Integer) map.get("approver_level_seq"));
					approval.setDesignationLevelId((BigInteger) map.get("designation_level_id"));
					approval.setGrpSeqNo((Integer) map.get("grp_seq_no"));
					approval.setApprovalStatus((String) map.get("approvalStatus"));
					approval.setIsFinalApprovalStatus((Character) map.get("isFinalApprovalStatus"));
					approval.setRejectedFlag('N');
					approvalList.add(approval);
				});
		approvalRepo.saveAll(approvalList);
		
		apiResponse.setResult("Saved");
		apiResponse.setMessage(orderCancellation.getDraftFlag() 
				? "Back Order Cancellation Details Saved Successfully" 
				: "Back Order Cancellation Details Submited Successfully");
		
		return apiResponse;
	}

	@Override
	public void printBOCReport(String bocId, String printStatus, String filePath, OutputStream outputStream)
			throws Exception {
		String jasperfile = filePath + "ORDERCANCELLATIONREPORT.jasper";
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("bocId", bocId);
    	
		JasperPrint jasperPrint = jasperPrintService.getJasperPrint(jasperfile, jasperParameter);
    	
		jasperPrintService.printPdfReport(jasperPrint, printStatus, outputStream);
		
	}

	@Override
	public ApiResponse<?> autoCompleteDealerCode(String dealerCode) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();		
		
		apiResponse.setResult(cancellationRepo.autoCompleteDealerCode(dealerCode));
		
		return apiResponse;
	}

	@Transactional
	@Override
	public ApiResponse<?> boCancellationApproval(BOCancellationApprovalRequestDto approvalRequestDto) throws Exception {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		
		//Setting status of BO Cancellation items
		if(approvalRequestDto.getBackOrderCancellation() != null) {
			approvalRequestDto.getBackOrderCancellation().getCancellationDtls().forEach(item -> {
				
				if(item.getKaiAcceptedQty() == item.getCancelQty() && item.getKaiAcceptedQty() != 0) {
					item.setStatus("Accepted");
				} else if(item.getKaiAcceptedQty() < item.getCancelQty() && item.getKaiAcceptedQty() != 0) {
					item.setStatus("Partially Accepted");
				} else if(item.getKaiAcceptedQty() == 0) {
					item.setStatus("Rejected");
				}
			});
			
			cancellationRepo.save(approvalRequestDto.getBackOrderCancellation());
			
			String msg = cancellationRepo.boCancellationApproval(
					approvalRequestDto.getBackOrderCancellation().getId(),
					userAuthentication.getKubotaEmployeeId(), approvalRequestDto.getRemark(),
					userAuthentication.getUsername(), approvalRequestDto.getApprovalStatus());
			
			if(msg.equals("FAIL")) {
				throw new UnsupportedOperationException("Approval failed due to some reason");
			}
			
			apiResponse.setResult(msg);
		} else {
			throw new EmptyStackException();
		}
        
        return apiResponse;
	}

	@Override
	public ApiResponse<?> getBOCApproverHierarchyDetails(Long backorderId) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(cancellationRepo.getBOCApproverHierarchyDetails(backorderId));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> searchBOCApproval(SPBackOrderCancellationRequestDto boCancellationDto) {
		ApiResponse<List<SPBackOrderCancellationResponseDto>> apiResponse = new ApiResponse<>();
		
		List<SPBackOrderCancellationResponseDto> responceDtoList = cancellationRepo.spBackOrderCancellationSearch(
				boCancellationDto.getBocno(),
				boCancellationDto.getDealercode(),
				boCancellationDto.getFromDate(),
				boCancellationDto.getToDate(),
				boCancellationDto.getPage(),
				boCancellationDto.getSize(),
				userAuthentication.getUsername(),
				'N');
		
		Long count = 0l;
		if (responceDtoList != null && !responceDtoList.isEmpty()) {
			count = responceDtoList.get(0).getTotalCount();
		}
		
		apiResponse.setCount(count);
		apiResponse.setResult(responceDtoList);
		
		return apiResponse;
	}

}
