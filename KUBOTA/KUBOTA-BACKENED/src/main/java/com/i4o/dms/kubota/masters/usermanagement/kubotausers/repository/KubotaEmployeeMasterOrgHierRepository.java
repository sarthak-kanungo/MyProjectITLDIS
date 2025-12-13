package com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.KubotaEmployeeMasterOrgHier;

@Transactional
public interface KubotaEmployeeMasterOrgHierRepository extends JpaRepository<KubotaEmployeeMasterOrgHier, Long>{
	
	  @Query(value = "select distinct UsrID_vs_FieldRoleID as orgHierId from ADM_HO_USER_ORG_HIER where ho_usr_id=:hoUsrId and org_hierarchy_id=:orgHierId", nativeQuery = true)
	  Long checkForHoUserStatus(@Param("hoUsrId") Long  hoUsrId, @Param("orgHierId") Long orgHierId);
	  
	  @Query(value = "{call getKubotaemployeeFieldHierarchySearch(:hoEmpCode)}",nativeQuery = true)
	    List<Map<String,Object>> getOrgHierDetailsForHoEmp(@Param("hoEmpCode") Long hoEmpCode);
	  
	  @Modifying
	  @Query(value = "update  ADM_HO_USER_ORG_HIER set  IsActive='N' where ho_usr_id=:hoUsrId and org_hierarchy_id=:orgHierId", nativeQuery = true)
	  Integer updateOrgHierDetailsForHoEmp(@Param("hoUsrId") Long hoUsrId, @Param("orgHierId") Long orgHierId);

}
