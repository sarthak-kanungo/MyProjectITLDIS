package com.i4o.dms.itldis.masters.kaicommonmaster.transactionHierManagement.service;

import java.util.List;

import javax.transaction.Transactional;

import com.i4o.dms.itldis.masters.kaicommonmaster.transactionHierManagement.domain.SystemApprovalFlowMaster;
import com.i4o.dms.itldis.masters.kaicommonmaster.transactionHierManagement.dto.TranHierSearchRequestDto;
import com.i4o.dms.itldis.utils.ApiResponse;

@Transactional
public interface TranHierManagementService {

	ApiResponse<?> autoGetTransName(String transName);

	ApiResponse<?> tranHierManagementSearch(TranHierSearchRequestDto hierSearchDto);

	ApiResponse<?> autoGetHoDesigLevel(String desigStr);

	ApiResponse<?> tranHierManagementView(String transName);

	ApiResponse<?> updateHierarchySeq(List<SystemApprovalFlowMaster> approvalFlowMasters);

}
