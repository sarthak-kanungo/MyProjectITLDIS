package com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository;

import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.KubotaDepartmentMaster;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.dto.DepartmentSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DepartmentRepository extends JpaRepository<KubotaDepartmentMaster, Long> {
    @Query(value = "{call sp_get_department_list(:applicableTo)}", nativeQuery = true)
    List<Map<String, Object>> getDepartmentByApplicableTo(@Param("applicableTo") String applicableTo);

    @Query("SELECT id as id,departmentCode as departmentCode FROM KubotaDepartmentMaster where departmentCode like %:departmentCode%")
    List<Map<String,Object>> searchByDepartmentCode(@Param("departmentCode") String departmentCode);

    @Query(value = "{call sp_kai_mt_department_search(:departmentCode,:departmentName,:linkedToDealer,:dealerCode,:page,:size)}",nativeQuery = true)
    List<DepartmentSearchResponse> searchDepartment(@Param("departmentCode") String departmentCode,
                                                    @Param("departmentName") String departmentName,
                                                    @Param("linkedToDealer")String linkedToDealer,
                                                    @Param("dealerCode")String dealerCode,
                                                    @Param("page")Integer page,@Param("size")Integer size);

//    @Query(value = "{call sp_kai_mt_department_search_count(:departmentCode,:departmentName,:linkedToDealer,:dealerCode)}",nativeQuery = true)
//    Long searchDepartmentCount(@Param("departmentCode") String departmentCode,
//                                                    @Param("departmentName") String departmentName,
//                                                    @Param("linkedToDealer")String linkedToDealer,
//                                                    @Param("dealerCode")String dealerCode);

    @Query(value="{call sp_kai_mt_department_name_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> departmentName();

    @Query(value="{call sp_kai_mt_department_linkedToDealer_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> linkedToDealer();

    @Query(value="{call sp_kai_mt_department_dealerCode_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> dealerCodeDropdown();

    //linked_to_dealer= 'Y' department list
    @Query(value="{call sp_kai_mt_department_name_list_for_dealer()}",nativeQuery = true)
    List<Map<String,Object>> getdepartment();
    
    KubotaDepartmentMaster findByDepartmentCodeAndDepartmentName(String departmentCode, String departmentName);
    
    @Query(value="select distinct  department_code as departmentCode from adm_ho_mst_department where department_code=:departmentCode",nativeQuery = true)
    String toCheckKaiDepartmentCode(@Param("departmentCode") String departmentCode);
    
    @Query(value="select distinct  department_name as departmentName from adm_ho_mst_department where department_name=:departmentName",nativeQuery = true)
    String toCheckKaiDepartmentName(@Param("departmentName") String departmentName);
}

