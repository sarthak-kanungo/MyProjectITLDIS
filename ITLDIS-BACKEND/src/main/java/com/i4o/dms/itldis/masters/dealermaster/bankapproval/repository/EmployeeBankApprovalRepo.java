package com.i4o.dms.itldis.masters.dealermaster.bankapproval.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;

public interface EmployeeBankApprovalRepo extends JpaRepository<DealerEmployeeMaster,Long> {
	
	@Query(value ="{call SP_EMPLOYEE_BANK_APPROVAL_SEARCH(:userCode,:dealerId,:employeeCode,:employeeName,:fromDate,:toDate,:page,:size)}", nativeQuery = true)
    //List<EmployeeBankApprovalSearchResponse>
	List<Map<String, Object>> searchEmployeeBankApproval(
    		@Param("userCode") String userCode, @Param("dealerId") Long dealerId, 
    		@Param("employeeCode") String employeeCode, @Param("employeeName") String employeeName,
    		@Param("fromDate") String fromDate, @Param("toDate") String toDate,
            @Param("page")Integer page, @Param("size")Integer size);
	
	@Transactional
	@Query(value ="{call SP_APPROVE_EMPLOYEE_BANK_DETAILS(:hoUserId,:employeeId,:userCode,:approvalStatus,:remark)}", nativeQuery = true)
	String approveEmployeeBankDetails(
    		@Param("hoUserId") Long hoUserId, @Param("employeeId") Long employeeId, 
    		@Param("userCode") String userCode, @Param("approvalStatus") String approvalStatus,
    		@Param("remark") String remark);
}
