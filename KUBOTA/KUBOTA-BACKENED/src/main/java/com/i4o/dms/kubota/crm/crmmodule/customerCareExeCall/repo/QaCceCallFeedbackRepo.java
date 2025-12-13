package com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.domain.QaCrmCceCallFeedback;

public interface QaCceCallFeedbackRepo extends JpaRepository<QaCrmCceCallFeedback, Long>{
	
	List<QaCrmCceCallFeedback> findByCallHdrIdAndJcId(Long callHdrId, Long jcId);
	
	List<QaCrmCceCallFeedback> findByCallHdrIdAndDcId(Long callHdrId, Long dcId);
	
}
