package com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository;

import com.i4o.dms.kubota.masters.dbentities.user.DesignationHierarchy;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.KubotaEmployeeMasterOrgHier;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.dto.EmployeeSearchResponse;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.dto.EmployeeViewResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface KubotaEmployeeRepository extends JpaRepository<KubotaEmployeeMaster, Long> {

    @Query(value = "{call sp_kai_mt_employee_get_md_dmd()}", nativeQuery = true)
    List<KubotaEmployeeMaster> getMdAndDmd();

    @Query(value = "{call sp_kai_mt_employee_designation_name_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> designationLevel();

    @Query( value = "{call sp_kai_mt_employee_autocomplete_designation_level(:desiganationLevel, :departmentid)}",nativeQuery = true)
    List<Map<String,Object>> findByDesiganationLevelContaining(@Param("desiganationLevel") String desiganationLevel,
    														   @Param("departmentid") Integer departmentid);

    @Query( value = "{call sp_kai_mt_employee_autocomplete_employee_code(:employeeCode)}",nativeQuery = true)
    List<Map<String,Object>> findByEmployeeCodeContaining(@Param("employeeCode") String employeeCode);

    @Query( value = "{call sp_kai_mt_employee_autocomplete_employee_name(:employeeName)}",nativeQuery = true)
    List<Map<String,Object>> findByEmployeeNameContaining(@Param("employeeName") String employeeName);

    @Query(value = "{call sp_kai_mt_employee_name_by_code(:employeeCode)}",nativeQuery = true)
    List<Map<String,Object>> findByEmployeeCode(@Param("employeeCode") String employeeCode);

    //for reportingTo
    @Query(value = "{call sp_kai_mt_employee_get_reporting_to(:employeeCode)}",nativeQuery = true)
    List<Map<String,Object>> reportingToHierarchy(@Param("employeeCode") String employeeCode);

    @Query("select h from DesignationHierarchy h where h.hierarchy=:hierarchy")
    DesignationHierarchy findByHierarchy(@Param("hierarchy") String hierarchy);

    @Query(value= "{call sp_sales_po_checkSalesAdmin(:houserid)}",nativeQuery = true)
    Character isSaleAdmin(Long houserid);
    
    @Query(value= "{call sp_kai_mt_employee_get_zonal_managers(:employeeCode,:management)}",nativeQuery = true)
    List<Map<String,Object>> findZonalManagers(@Param("employeeCode") Long id,@Param("management") Boolean access);

    @Query(value= "{call sp_kai_mt_employee_get_regional_managers(:employeeCode,:management,:zonalManagerId)}",nativeQuery = true)
    List<Map<String,Object>> findRegionalManagers(@Param("employeeCode") Long hoEmployeeId,@Param("management") Boolean access,@Param("zonalManagerId")Long zonalManagerId);

    @Query(value = "{call sp_kai_mt_employee_search(:employeeCode,:employeeName,:page,:size)}",nativeQuery = true)
    List<EmployeeSearchResponse> searchEmployee(@Param("employeeCode") String employeeCode,
                                                @Param("employeeName") String employeeName,
                                                @Param("page")Integer page,
                                                @Param("size")Integer size);

//    @Query(value = "{call sp_kai_mt_employee_search_count(:employeeCode,:employeeName,:page,:size)}",nativeQuery = true)
//    Long searchEmployeeCount(@Param("employeeCode") String employeeCode,
//                                                @Param("employeeName") String employeeName,
//                                                @Param("page")Integer page,
//                                                @Param("size")Integer size);

    @Query(value="{call sp_kai_mt_employee_get_details_by_employee_code(:employeeCode)}",nativeQuery = true)
    List<Map<String,Object>> findDetailsByEmployeeCode(@Param("employeeCode") String employeeCode);

   //change sp names
    @Query(value="{call sp_kai_mt_employee_sales_dropdown(:departmentName)}",nativeQuery = true)
    List<Map<String,Object>> findEmployeeCodeByDepartmentName(@Param("departmentName") String departmentName);

    @Query(value="{call sp_kai_mt_employee_get_details_by_code(:employeeCode)}",nativeQuery = true)
    List<Map<String,Object>> findEmployeeDetailsByEmployeeCode(@Param("employeeCode") String employeeCode);


    @Query(value="{call sp_kai_mt_employee_get_reporting_employee_names(:id)}",nativeQuery = true)
    List<Map<String,Object>> findReportingEmployeeNames(@Param("id") Long id);
    
    @Query(value = "{call sp_kai_mt_get_employee_details(:employeeCode)}",nativeQuery = true)
    List<Map<String,Object>> getEmployeeByEmployeeCode(@Param("employeeCode") String employeeCode);
    
    @Query(value = "{call sp_kai_mt_get_employee_details_Update(:employeeCode,:employee_name,:Active_status,:Contact_no,:Email_id,"
    					+ ":ho_department_id,:ho_designation_id,:ho_designation_level_id,:reporting_user_id,:Management_access)}",nativeQuery = true)
    Object updateEmployee(@Param("employeeCode") String employeeCode,
                                                @Param("employee_name") String employee_name,
                                                @Param("Active_status") Character Active_status,
                                                @Param("Contact_no")String Contact_no,
                                                @Param("Email_id") String Email_id,
                                                @Param("ho_department_id") Long ho_department_id,
                                                @Param("ho_designation_id") Long ho_designation_id,
                                                @Param("ho_designation_level_id") Long ho_designation_level_id,
                                                @Param("reporting_user_id") Long reporting_user_id,
                                                @Param("Management_access") Boolean Management_access);
    
    @Query(value = "{call sp_get_department_list(:applicableTo)}",nativeQuery = true)
    List<Map<String,Object>> getempMasterdepartmentForOrgHier(@Param("applicableTo") String applicableTo);
    
    @Query(value = "{call sp_getOrgLevelByHODeptForMaster(:deptCode)}",nativeQuery = true)
    List<Map<String,Object>> getLevelForOrgHier(@Param("deptCode") String deptCode);
    
    @Query(value = "{call sp_getOrgLevelHierForMaster(:userCode,:levelId,:orgHierId,:includeInactive)}",nativeQuery = true)
    List<Map<String,Object>> getLevelDetailsForOrgHier(@Param("userCode") String userCode,
    										   @Param("levelId") Long levelId,
    										   @Param("orgHierId") Long orgHierId,
    										   @Param("includeInactive") String includeInactive);
    




}


