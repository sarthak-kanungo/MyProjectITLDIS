package com.i4o.dms.itldis.masters.dealermaster.bankdetails.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.dealermaster.bankdetails.dto.EmployeeBankDetailSearchResponse;

public interface EmployeeBankDetailsRepo extends JpaRepository<DealerEmployeeMaster,Long> {
	
	@Query(value ="{call SP_DEALER_EMP_BY_CODE_OR_MOB(:dealerCode, :dealerMob)}", nativeQuery = true)
    Map<String , Object> getDealerEmployeeByCodeOrMob(@Param("dealerCode") String dealerCode, 
    		@Param("dealerMob") String dealerMob);
	
    @Query(value = "select top 1 * from ADM_DEALER_EMP where employee_code IN(:empCode)", nativeQuery = true)
    DealerEmployeeMaster getEmpIdByCode(@Param("empCode") String empCode);
    
    @Query(value ="{call sp_employee_bank_details_search(:userCode,:employeeCode,:mobileNo,:employeeName,"
    		+ ":designation,:departmentName,:page,:size)}", nativeQuery = true)
    List<EmployeeBankDetailSearchResponse> searchEmployeeBankDetails(
    		@Param("userCode") String userCode, @Param("employeeCode") String employeeCode, 
    		@Param("mobileNo") Long mobileNo, @Param("employeeName") String employeeName,
    		@Param("designation") String designation, @Param("departmentName") String departmentName,
            @Param("page")Integer page, @Param("size")Integer size);
	
    @Modifying
    @Transactional
	@Query(value = "update ADM_DEALER_EMP set bank_account_no=:bankAccountNo, bank_branch=:bankBranch, "
			+ "bank_name=:bankName, ifs_code=:ifsCode, approval_status=:approvalStatus, "
			+ "last_modified_date=:lastModifiedDate, last_modified_by=:lastModifiedBy where employee_code=:employeeCode", 
			nativeQuery = true)
	Integer updateEmployeeBankDetailsById(
			@Param("employeeCode") String employeeCode,
			@Param("bankAccountNo") String bankAccountNo,
			@Param("bankBranch") String bankBranch,
			@Param("bankName") String bankName,
			@Param("ifsCode") String ifsCode,
			@Param("approvalStatus") String approvalStatus,
			@Param("lastModifiedDate") Date lastModifiedDate,
			@Param("lastModifiedBy") Long lastModifiedBy);
}
