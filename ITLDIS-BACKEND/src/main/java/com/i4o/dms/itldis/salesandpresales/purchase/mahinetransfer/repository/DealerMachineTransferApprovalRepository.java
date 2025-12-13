package com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.domain.DealerMachineTransferApproval;

public interface DealerMachineTransferApprovalRepository extends JpaRepository<DealerMachineTransferApproval, Long> {

	@Query(value="{call sp_machine_transfer_get_approval_hierarchy_level(:dealerID)}", nativeQuery=true)
	List<Map<String,Object>> getMachineTransferApprovalHierarchyLevel(@Param("dealerID")Long dealerID);
	
	@Query(value="{call sp_machine_transfer_get_approval_details(:requestNo)}", nativeQuery=true)
	List<Map<String,Object>> machineTransferApprovalDetails(@Param("requestNo")String requestNo);
	
	@Query(value="{call sp_machine_transfer_approval(:requestNo, :approvalType, :remarks, :hoUserId, :userCode)}", nativeQuery=true)
	String machineTransferApproval(@Param("requestNo")String requestNo, @Param("approvalType")String approvalType, @Param("remarks")String remarks, @Param("hoUserId")Long hoUserId, @Param("userCode")String userCode);
}
