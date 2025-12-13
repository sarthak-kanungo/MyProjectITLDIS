package com.i4o.dms.itldis.masters.dealermaster.createdealeruser.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.masters.dealermaster.createdealeruser.domain.DealerRoleFunction;
import com.i4o.dms.itldis.masters.dealermaster.createdealeruser.dto.DealerSearchResponse;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.DealerEmployeeSearchResponse;

@Transactional
public interface CreateDealerUserRepo extends JpaRepository<DealerRoleFunction, Long>{
	
	
	 @Query(value = "{call sp_dealer_autocomplete_byHO(:dealervalue,:userName)}",nativeQuery = true)
	 List<Map<String,Object>> getNewDealer(@Param("dealervalue") String dealervalue,@Param("userName")String userName);
	   
	
	@Query(value = "{call sp_Dealer_mt_employee_DTL(:employeeCode, :dealerId)}", nativeQuery = true)
    List<Map<String,Object>> getReportingEmployee(@Param("employeeCode") String employeeCode, @Param("dealerId") int dealerid );
	
	@Query(value = "{call sp_role_menu_list_User(:dealerId,:hoOrDealer)}", nativeQuery = true)
    List<Map<String,Object>> getDealerRole(@Param("dealerId") Long dealerid, @Param("hoOrDealer") String hoOrDealer );
	
	@Query(value = "select user_name from ADM_USER where user_name IN(:userName)", nativeQuery = true)
	String tofindDuplicateUserName(@Param("userName") String  userName);
	
	@Query(value = "{call sp_search_user(:userCode,:employeeCode,:employeeName,:applicablefor, :page,:size)}",nativeQuery = true)
	List<DealerSearchResponse> getSearchDealer(@Param("userCode") String userCode,
													    @Param("employeeCode") String employeeCode,
	                                                   @Param("employeeName") String employeeName,
	                                                   @Param("applicablefor") String applicablefor,
	                                                   @Param("page")Integer page,
	                                                   @Param("size")Integer size);
	
	 
	 @Query(value = "{call sp_user_dtl_by_id(:empId)}", nativeQuery = true)
	  List<Map<String,Object>> getDealerDetailsForView(@Param("empId") String empId);

	 List<DealerRoleFunction> findByLoginUserId(Long loginUserId);
	 @Modifying
	 @Query(value = "update ADM_MENU_ROLE_USER_HDR set IsActive=:IsActive where login_user_id=:loginUserId and role_id=:roleId", nativeQuery = true)
	 Integer roleManueId(@Param("IsActive") char  IsActive,@Param("loginUserId") Long  loginUserId,@Param("roleId") Integer  roleId);
}
