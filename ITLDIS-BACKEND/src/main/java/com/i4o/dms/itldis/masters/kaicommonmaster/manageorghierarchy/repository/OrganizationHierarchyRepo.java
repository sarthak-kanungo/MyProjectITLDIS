package com.i4o.dms.itldis.masters.kaicommonmaster.manageorghierarchy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.itldis.masters.kaicommonmaster.assignorghierarchytodealer.domain.AssignOrgLevelHierarchyMaster;

public interface OrganizationHierarchyRepo extends JpaRepository<AssignOrgLevelHierarchyMaster, Long>{

	AssignOrgLevelHierarchyMaster findByHierarchyCode(String hierarchyCode);
}
