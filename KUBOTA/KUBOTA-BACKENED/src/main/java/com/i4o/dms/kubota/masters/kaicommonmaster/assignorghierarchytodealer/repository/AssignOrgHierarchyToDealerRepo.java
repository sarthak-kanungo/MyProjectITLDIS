package com.i4o.dms.kubota.masters.kaicommonmaster.assignorghierarchytodealer.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.masters.kaicommonmaster.assignorghierarchytodealer.domain.AssignOrgHierarchyToDealerMap;
import com.i4o.dms.kubota.masters.kaicommonmaster.assignorghierarchytodealer.domain.DealerHoDepartmentId;
import com.i4o.dms.kubota.masters.kaicommonmaster.assignorghierarchytodealer.dto.AssignOrgHierarchyToDealerResponse;

@Transactional
public interface AssignOrgHierarchyToDealerRepo extends JpaRepository<AssignOrgHierarchyToDealerMap, Long> {
    @Query(value = "{call sp_getOrgLevelByHODeptForMaster(:departmentcode)}", nativeQuery = true)
	List<Map<String, Object>> getLevelByDepartment(@Param("departmentcode") String departmentcode);
    
    @Query(value = "{call sp_getOrgLevelHierForMaster(:UserCode,:LEVELID,:ORGHIERID,:IncludeInactive)}", nativeQuery = true)
    List<Map<String, Object>> getHierarchyByLevel(@Param("UserCode") String UserCode,
    		@Param("LEVELID") String LEVELID,@Param("ORGHIERID") String ORGHIERID,
    		@Param("IncludeInactive") String IncludeInactive);
    
    @Query(value = "{call getKubotaDealerFieldHierarchySearch(:dealerid,:departmentid,:Page,:Size)}", nativeQuery = true)
    List<AssignOrgHierarchyToDealerResponse> searchAllAssignOrgHierarchyToDealer(@Param("dealerid") Integer dealerid,
    		@Param("departmentid") Integer departmentid,@Param("Page") Integer Page,
    		@Param("Size") Integer Size);
    
    AssignOrgHierarchyToDealerMap findByDealerHoDepartmentId(DealerHoDepartmentId dealerHoDepartmentId);
    
    AssignOrgHierarchyToDealerMap deleteByDealerHoDepartmentId(DealerHoDepartmentId dealerHoDepartmentId);
    
    @Modifying
    @Query(value = "delete from ADM_DEALER_ORG_HIER_MAP where dealer_id=:dealerid", nativeQuery = true)
    Integer deleteByDealerAssignOrgHier(@Param("dealerid") Long dealerid);

}