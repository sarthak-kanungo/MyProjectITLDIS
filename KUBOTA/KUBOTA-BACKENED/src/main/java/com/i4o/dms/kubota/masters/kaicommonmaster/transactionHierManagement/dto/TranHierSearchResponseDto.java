package com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface TranHierSearchResponseDto {
	
//	Long getId();
	String getTransactionName();
//	String getDesignationLevel();
//	Character getIsFinalApprovalStatus();
//	Integer getGrpSeqNo();
//	Integer getApproverLevelSeq();
	
	@JsonIgnore
	Long getTotalCount();
	
}
