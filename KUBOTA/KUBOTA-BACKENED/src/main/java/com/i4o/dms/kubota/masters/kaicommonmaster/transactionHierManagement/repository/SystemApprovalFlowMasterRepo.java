package com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.domain.SystemApprovalFlowMaster;
import com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.dto.TranHierSearchResponseDto;
import com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.dto.TranHierViewResponseDto;

/**
 * @author suraj.gaur
 */
@Transactional
public interface SystemApprovalFlowMasterRepo extends JpaRepository<SystemApprovalFlowMaster, Long> {

	@Query(value = "{call SP_AUTO_GET_TRANSACTION_NAME(:transName)}", nativeQuery = true)
	List<Map<String, Object>> autoGetTransName(@Param("transName") String transName);

	@Query(value = "{call SP_TRANSACTION_HIERARCHY_MNGMT_SEARCH(:transName, :page, :size)}", nativeQuery = true)
	List<TranHierSearchResponseDto> tranHierManagementSearch(
			@Param("transName") String transName, 
//			@Param("finalApp") String finalApp, 
			@Param("page") Integer page, 
			@Param("size") Integer size);

	@Query(value = "{call SP_AUTO_GET_HO_DESIGNATION_LEVEL(:desigStr)}", nativeQuery = true)
	List<Map<String, Object>> autoGetHoDesigLevel(@Param("desigStr") String desigStr);

	@Query(value = "{call SP_TRANSACTION_HIERARCHY_MNGMT_VIEW(:transName)}", nativeQuery = true)
	List<TranHierViewResponseDto> tranHierManagementView(String transName);
	
	@Query(value = "{call SP_DELETE_HIER_TRANS_BY_TRAN_NAME(:transactionName)}", nativeQuery = true)
	String deleteHierTransaction(@Param("transactionName") String transactionName);
	
	@Query(value = "{call SP_INSERT_HIER_TRANSACTION(:transactionName, :approverLevelSeq, :designationLevelId, :grpSeqNo, :isFinalApprovalStatus)}", nativeQuery = true)
	String insertHierTransaction(
			@Param("transactionName") String transactionName,
			@Param("approverLevelSeq") Integer approverLevelSeq,
			@Param("designationLevelId") Long designationLevelId,
			@Param("grpSeqNo") Integer grpSeqNo,
			@Param("isFinalApprovalStatus") Character isFinalApprovalStatus);

}
