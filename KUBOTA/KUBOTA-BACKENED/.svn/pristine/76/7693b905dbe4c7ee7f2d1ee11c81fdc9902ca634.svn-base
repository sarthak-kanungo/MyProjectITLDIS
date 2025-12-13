package com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerDepartmentMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.DealerDepartmentSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

@Transactional
public interface DealerDepartmentRepo extends JpaRepository<DealerDepartmentMaster,Long> {

    @Query (value="{call sp_dealer_mt_department_name_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> getDepartmentName();

    @Query(value = "{call sp_dealer_mt_department_name_autocomplete(:departmentName)}",nativeQuery = true)
    List<Map<String,Object>> findByDepartmentNameContaining(@Param("departmentName") String departmentName);
    
    @Query(value = "{call sp_Dealer_Department_MT_autocomplete(:deptCodeAndName)}",nativeQuery = true)
    List<Map<String,Object>> departmentCodeAndName(@Param("deptCodeAndName") String deptCodeAndName);

//    @Query(value = "{call sp_dealer_mt_department_search(:departmentName,:page,:size)}",nativeQuery = true)
//    List<DealerDepartmentSearchResponse> searchDepartment(@Param("departmentName") String departmentName,
//                                                          @Param("page") Integer page,
//                                                          @Param("size") Integer size);
    
    @Query(value = "{call sp_dealer_mt_department_search(:departmentCode,:departmentName,:page,:size)}",nativeQuery = true)
    List<DealerDepartmentSearchResponse> searchDepartment(@Param("departmentCode") String departmentCode,
    													  @Param("departmentName") String departmentName,
                                                          @Param("page") Integer page,
                                                          @Param("size") Integer size);

    @Query(value = "{call sp_dealer_mt_department_search_count(:departmentName,:page,:size)}",nativeQuery=true)
     Long searchDealerDepartmentCount(@Param("departmentName") String departmentName,
                                         @Param("page") Integer page,
                                         @Param("size") Integer size);
    
    @Query(value="select distinct  department_code as departmentCode from ADM_DEALER_MST_DEPARTMENT where department_code=:departmentCode",nativeQuery = true)
    String toCheckDepartmentCode(@Param("departmentCode") String departmentCode);
    
    @Query(value="select distinct  department_name as departmentName from ADM_DEALER_MST_DEPARTMENT where department_name=:departmentName",nativeQuery = true)
    String toCheckDepartmentName(@Param("departmentName") String departmentName);
    
    @Query(value="select distinct  department_code as departmentCode,department_name as departmentName from ADM_DEALER_MST_DEPARTMENT where id=:departmentId",nativeQuery = true)
    Map<String, Object> viewDepartmentDetails(@Param("departmentId") String departmentId);
    
    @Modifying
    @Query(value=" update  ADM_DEALER_MST_DEPARTMENT set department_code=:departmentCode, department_name=:departmentname,last_modified_date=getdate() where id=:id",nativeQuery=true)
    Integer updateDepartmentDetails(@Param("departmentCode") String departmentCode, @Param("departmentname") String departmentname,@Param("id") Long id);
    
    
    
}


