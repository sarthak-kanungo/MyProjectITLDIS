package com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.domain.SystemApprovalFlowMaster;
import com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.dto.TranHierSearchRequestDto;
import com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.dto.TranHierSearchResponseDto;
import com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.dto.TranHierViewResponseDto;
import com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.exception.DeleteOperationFailedException;
import com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.exception.InsertFailException;
import com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.exception.TransactionNameEmptyException;
import com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.repository.SystemApprovalFlowMasterRepo;
import com.i4o.dms.kubota.utils.ApiResponse;

/**
 * @author suraj.gaur
 */
@Service
public class TranHierManagementServiceImpl implements TranHierManagementService {
	
	@Autowired
	private SystemApprovalFlowMasterRepo masterRepo;

	@Override
	public ApiResponse<?> autoGetTransName(String transName) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();		
		
		apiResponse.setResult(masterRepo.autoGetTransName(transName));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> tranHierManagementSearch(TranHierSearchRequestDto hierSearchDto) {
		ApiResponse<List<TranHierSearchResponseDto>> apiResponse = new ApiResponse<>();		
		
		List<TranHierSearchResponseDto> responseDtos = masterRepo.tranHierManagementSearch(
				hierSearchDto.getTransName(),
//				hierSearchDto.getFinalApp(),
				hierSearchDto.getPage(), 
				hierSearchDto.getSize());
		
		Long count = 0L;
		if (responseDtos != null && responseDtos.size() > 0) {
			count = responseDtos.get(0).getTotalCount();
		}
		
		apiResponse.setCount(count);
		apiResponse.setResult(responseDtos);
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> autoGetHoDesigLevel(String desigStr) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();		
		
		apiResponse.setResult(masterRepo.autoGetHoDesigLevel(desigStr));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> tranHierManagementView(String transName) {
		ApiResponse<List<TranHierViewResponseDto>> apiResponse = new ApiResponse<>();		
		
		List<TranHierViewResponseDto> responseDtos = masterRepo.tranHierManagementView(transName);
		
		apiResponse.setResult(responseDtos);
		
		return apiResponse;
	}

	@Transactional
	@Override
	public ApiResponse<?> updateHierarchySeq(List<SystemApprovalFlowMaster> approvalFlowMasters) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		if(!approvalFlowMasters.isEmpty() && !approvalFlowMasters.get(0).getTransactionName().isEmpty() && 
				approvalFlowMasters.get(0).getTransactionName() != null) {
			
			String msg = masterRepo.deleteHierTransaction(approvalFlowMasters.get(0).getTransactionName());
			
			if(!msg.equalsIgnoreCase("Success")) {
				if(msg.equals("Failed"))
					msg = "Delete Operation failed due to some reasone";
				
				throw new DeleteOperationFailedException(msg);
			}
			
			approvalFlowMasters.forEach(appMaster -> {
				String message = masterRepo.insertHierTransaction(
						appMaster.getTransactionName(), 
						appMaster.getApproverLevelSeq(), 
						appMaster.getDesignationLevelMaster().getId(), 
						appMaster.getGrpSeqNo(),
						appMaster.getIsFinalApprovalStatus());
				
				if(!message.equalsIgnoreCase("Success"))
					throw new InsertFailException(message);
			});
			
		} else {
			throw new TransactionNameEmptyException();
		}
		
		return apiResponse;
	}

}
