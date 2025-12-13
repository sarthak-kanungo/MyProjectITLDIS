package com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerDesignationMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.DealerDesignationSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

@Transactional
public interface DealerDesignationRepo extends JpaRepository<DealerDesignationMaster,Long> {

    @Query(value=" SELECT id,department_code,department_name FROM ADM_DEALER_MST_DEPARTMENT",nativeQuery = true)
    List<Map<String,Object>> getdepartmentForDealerDesignation();

    @Query(value="{call sp_dealer_mt_designation_search(:designation,:department,:page,:size)}",nativeQuery = true)
    List<DealerDesignationSearchResponse> searchDealerDesignation(@Param("designation") String designation,
    															  @Param("department") String department,
                                                                  @Param("page") Integer page,
                                                                  @Param("size") Integer size);

    @Query(value="{call sp_dealer_mt_designation_search_count(:designation,:page,:size)}",nativeQuery = true)
    Long searchDealerDesignationCount(@Param("designation") String designation,
                                         @Param("page") Integer page,
                                         @Param("size") Integer size);

   @Query(value = "{call sp_Dealer_Designation_MT_autocomplete(:designation)}",nativeQuery = true)
   List<Map<String,Object>> getDesignationForDealer(@Param("designation") String designation);
   
   @Query(value="select designationCode from ADM_DEALER_MST_DESIGNATION where designationCode=:designationCode",nativeQuery = true)
   String toCheckDesignationCode(@Param("designationCode") String designationCode);
   
   @Query(value="select designation from ADM_DEALER_MST_DESIGNATION where designation=:designationName",nativeQuery = true)
   String toCheckDesignationName(@Param("designationName") String designationName);
   
   @Query(value="select designationCode,designation  from ADM_DEALER_MST_DESIGNATION where id=:designationId",nativeQuery = true)
   List<Map<String,Object>> viewDesignationDetails(@Param("designationId") String designationId);

   @Query(value="select distinct  department_name as departmentName from ADM_DEALER_MST_DEPARTMENT where id=:designationId",nativeQuery = true)
   Map<String, Object> viewDeptForDesignationDetails(@Param("designationId") String designationId);

   @Modifying
   @Query(value=" update  ADM_DEALER_MST_DESIGNATION set department_id=:departmentId, designationCode=:designationcode,designation=:designation ,last_modified_date=getdate() where id=:id",nativeQuery=true)
   Integer updateDesignationDetails(@Param("departmentId") Long departmentId, @Param("designationcode") String designationcode, @Param("designation") String designation,@Param("id") Long id);
   
  
   
   
}
