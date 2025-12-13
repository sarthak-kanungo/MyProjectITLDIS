package com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.AssignUserToBranchMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.AssignUserToBranchSearchResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface AssignUserToBranchRepo extends JpaRepository<AssignUserToBranchMaster,Long> {

   @Query(value = "{call sp_mt_assign_user_to_branch_user_id_autocomplete(:userId)}",nativeQuery = true)
    List<Map<String,Object>> findByUserIdContaining(@Param("userId") Long userId);
   
   @Query(value = "{call sp_dealer_autocomplete_byHO(:dealervalue,:userName)}",nativeQuery = true)
   List<Map<String,Object>> getDealerToAssignBranch(@Param("dealervalue") String dealervalue,@Param("userName")String userName);
   
   @Query(value = "{call sp_Dealer_mt_employee_DTL(:userIdVal, :dealerId,:isFor)}", nativeQuery = true)
   List<Map<String,Object>> getUserIdToAssignBranch(@Param("userIdVal") String userIdVal, @Param("dealerId") Long dealerId, @Param("isFor") String isFor );
   
   @Query(value = "{call sp_mt_assign_user_branch_list(:dealerId)}", nativeQuery = true)
   List<Map<String,Object>> getBranchToAssignUser(@Param("dealerId") Long dealerId);
   
   @Query(value = "{call sp_mt_assign_user_to_branch_search(:userId, :dealerId, :page,:size)}", nativeQuery = true)
   List<AssignUserToBranchSearchResponse> searchAssignUserToBranch(@Param("userId") String userId, @Param("dealerId") String dealerid,
           @Param("page")Integer page,
           @Param("size")Integer size);
   
   @Query(value = "select dealer_employee_id as id from ADM_BRANCH_USER where dealer_employee_id IN(:branchId)", nativeQuery = true)
	Long tofindDuplicateDealerEmployeeIdForAutb(@Param("branchId") Long  branchId);
   
   
   @Query(value = "select distinct id as employeeId,  dealer_id as dealerId, first_name as firstName from ADM_DEALER_EMP where id IN(:empId)", nativeQuery = true)
   List<Map<String,Object>> getDealerIdAndName(@Param("empId") Long  empId);
     
   



}
