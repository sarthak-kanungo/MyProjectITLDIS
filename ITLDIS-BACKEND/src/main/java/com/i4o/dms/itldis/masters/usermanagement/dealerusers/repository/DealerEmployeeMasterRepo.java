package com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.DealerEmployeeSearchResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DealerEmployeeMasterRepo extends JpaRepository<DealerEmployeeMaster,Long> {

	DealerEmployeeMaster findByEmployeeCodeAndDealerId(@Param("employeeCode") String employeeCode, @Param("dealerId") Long dealerId);
    
	@Query(value = "{call sp_dealer_mt_employee_get_sales_person()}", nativeQuery = true)
    List<Map<String,Object>> getSalesPerson();

    @Query(value = "{call sp_dealer_mt_employee_get_dealer_code()}", nativeQuery = true)
    List<Map<String,Object>> getDealerCodes();

    @Query(value = "{call SP_createDealerUser(:employeeId, :userCode)}", nativeQuery = true)
    Map<String,Object> createUserForEmployee(@Param("employeeId")Long employeeId, @Param("userCode")String userCode);
    
    @Query(value="{call sp_dealer_mt_employee_status_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> getStatusDropdown();

    @Query(value="{call sp_dealer_mt_employee_get_employee_name_by_code(:employeeCode)}",nativeQuery = true)
    List<Map<String,Object>> getEmployeeName(@Param("employeeCode") String employeeCode);

    @Query( value = "{call sp_dealer_mt_employee_code_autocomplete(:userCode,:mobilenumber,:employeeCode)}",nativeQuery = true)
    List<Map<String,Object>> employeeCodeForSearchPage(@Param("userCode") String userCode, @Param("mobilenumber") String mobilenumber,@Param("employeeCode") String employeeCode);
    
    @Query( value = "{call sp_dealer_mt_employee_autocomplete_bydealercode(:dealerId,:employeeCode)}",nativeQuery = true)
    List<Map<String,Object>> findByEmployeeCodeContainingByDealerCode(@Param("dealerId") Integer dealerId,@Param("employeeCode") String employeeCode);

    @Query(value="{call sp_dealer_mt_employee_highest_qualification_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> getQualificationDropdown();

    @Query(value="{call sp_dealer_mt_employee_marital_status_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> getMaritalStatusDropdown();

    @Query(value="{call sp_dealer_mt_employee_gender_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> getGenderDropdown();

    @Query(value="{call sp_dealer_mt_employee_blood_group_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> getBloodGroupDropdown();

    @Query(value="{call sp_dealer_mt_employee_dealer_relationship_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> getRelationshipDropdown();

    @Query( value = "{call sp_dealer_mt_employee_name_autocomplete(:firstName)}",nativeQuery = true)
    List<Map<String,Object>> findByFirstNameContaining(@Param("firstName") String firstName);

    @Query(value = "{call sp_dealer_mt_employee_search(:userCode,:employeeCode,:mobileNo,:employeeName,:state,:dealeId,:departmentId,:page,:size)}",nativeQuery = true)
    List<DealerEmployeeSearchResponse> searchDealerEmployee(
    		@Param("userCode") String userCode,
    		@Param("employeeCode") String employeeCode,
    		@Param("mobileNo") Long mobileNo,
    		@Param("employeeName") String employeeName,
    		@Param("state") String state,
    		@Param("dealeId") Long dealeId,
    		@Param("departmentId") Long departmentId,
            @Param("page")Integer page,
            @Param("size")Integer size);

    @Query(value = "{call sp_dealer_mt_employee_search_count(:employeeCode,:mobileNo,:page,:size)}",nativeQuery = true)
    Long searchDealerEmployeeCount(@Param("employeeCode") String employeeCode,
                             @Param("mobileNo") Long mobileNo,
                             @Param("page")Integer page,
                             @Param("size")Integer size);

    @Query(value="select top 1 branch_id from ADM_BRANCH_USER where dealer_employee_id=:dealerEmpId and isActive='Y'", nativeQuery=true)
    Long getBranchId(@Param("dealerEmpId") Long dealerEmpId);
    
    @Query(value="select distinct id, department_name as department from adm_dealer_mst_department",nativeQuery = true)
    List<Map<String,Object>> getEmployeeMasterdepartment();
    
    @Query(value="select distinct id, designation as designation from adm_dealer_mst_designation",nativeQuery = true)
    List<Map<String,Object>> getEmployeeMasterDesignation();
    
    @Query(value = "{call sp_dealer_autocomplete_byHO(:dealervalue,:userName,:isFor)}",nativeQuery = true)
    List<Map<String,Object>> getDealerDetails(@Param("dealervalue") String dealervalue,@Param("userName")String userName,@Param("isFor")String isFor);
    
    
//    @Query(value ="{call sp_Dealer_mt_employee_get_reporting_to(:reportingEmployee,:dealerid)}",nativeQuery = true)
//    List<Map<String , Object>> getReportingEmployee(@Param("reportingEmployee") String employeeCode, @Param("dealerid") int dealerid);
    
    @Query(value ="{call sp_dealer_reporting_emp(:dealerCode,:searchText,:empCode)}",nativeQuery = true)
    List<Map<String , Object>> getReportingEmployee(@Param("dealerCode") String dealerCode,@Param("searchText") String searchText, @Param("empCode") String empCode);
    
//    @Query(value = "select * from ADM_DEALER_EMP where id IN(:dealerEmpId)", nativeQuery = true)
    @Query(value = "{call SP_GET_EMP_DETAILS(:dealerEmpId)}", nativeQuery = true)	//Suraj--06-02-2023
    DealerEmployeeMaster findByDealerEmpId(@Param("dealerEmpId") String  dealerEmpId);
    
    @Query(value = "select id, concat (dealer_code,' | ',dealer_name) as dealerDetails , dealer_code as dealerCode from ADM_DEALER_master where id IN(:dealerId)", nativeQuery = true)
    Map<String , Object> getDealerDetailsById(@Param("dealerId") Long  dealerId);
    
//    @Query(value = "select distinct employee_code as employeeCode from ADM_DEALER_EMP where employee_code IN(:employeeCode)", nativeQuery = true)
//    String getDuplicateEmpCode(@Param("employeeCode") String  employeeCode);
    @Query(value="select top 1 mobile_no from ADM_DEALER_EMP where mobile_no=:mobileno and (isnull(:id,0)=0 or id<>:id)", nativeQuery=true)
    String validateMobileNo(@Param("mobileno")String mobileno, @Param("id")Integer id);
    
}
