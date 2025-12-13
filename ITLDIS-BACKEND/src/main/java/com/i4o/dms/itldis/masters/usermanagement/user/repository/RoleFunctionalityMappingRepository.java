package com.i4o.dms.itldis.masters.usermanagement.user.repository;

import com.i4o.dms.itldis.masters.usermanagement.user.domain.RoleFunctionalityMapping;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleFunctionalityMappingRepository extends JpaRepository<RoleFunctionalityMapping,Long> {

    @Query("select m from RoleFunctionalityMapping m where m.roleMaster.id=?1 and m.functionalityMaster.id=?2")
    RoleFunctionalityMapping findByRoleAndFunctionalityMapping(Long roleId, Long functionId);
    
//    @Query("select m from RoleFunctionalityMapping m where m.roleMaster.id=?1 ")
//    List<RoleFunctionalityMapping> findByRoleMapping(Long roleId);
//    
    @Query(value = "select * from ADM_MENU_ROLE_DTL where role_id=:id", nativeQuery = true)
    List<RoleFunctionalityMapping> findByRoleMapping(@Param("id") Long id);
    
    @Modifying
    @Query(value = "delete from ADM_MENU_ROLE_DTL where role_id=:roleId", nativeQuery = true)
    Integer deleteByRoleId(@Param("roleId") Long roleId);
}
