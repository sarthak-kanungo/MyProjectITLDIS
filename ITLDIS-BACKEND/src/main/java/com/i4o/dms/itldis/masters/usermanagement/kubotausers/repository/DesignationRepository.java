package com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository;

import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KaiDesignationMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.dto.DesignationSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DesignationRepository extends JpaRepository<KaiDesignationMaster,Long> {

//    @Query(value = "select id as id,department_name as department_name FROM ADM_HO_MST_DEPARTMENT ",nativeQuery = true)
//    List<Map<String,Object>> getDepartmentName();
    
    @Query("SELECT id as id,designation as designation FROM KaiDesignationMaster where designation like %:designation%")
    List<Map<String,Object>> searchByDesignation(@Param("designation") String designation);

    @Query(value = "{call sp_kai_mt_designation_search(:designation,:Department,:page,:size)}",nativeQuery = true)
    List<DesignationSearchResponse> searchDesignation(@Param("designation") String designation,
                                                      @Param("Department") String Department,
                                                      @Param("page")Integer page,
                                                      @Param("size")Integer size);


//    @Query(value = "{call sp_kai_mt_designation_count(:designation,:Department,:page,:size)}",nativeQuery = true)
//    Long searchDesignationCount(@Param("designation") String designation,
//                                                      @Param("Department") String Department,
//                                                      @Param("page")Integer page,
//                                                      @Param("size")Integer size);
    
    @Query(value="select designation from ADM_HO_MST_DESIGNATION where designation=:designation",nativeQuery = true)
    String toCheckKaiDesignation(@Param("designation") String designation);
    
    @Query(value="select designationCode from ADM_HO_MST_DESIGNATION where designationCode=:designationCode",nativeQuery = true)
    String tocheckKaiDesignationCode(@Param("designationCode") String designationCode);

}
