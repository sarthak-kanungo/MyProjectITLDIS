/**
 * 
 */
package com.i4o.dms.itldis.common.sys.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.common.model.SystemLookUpEntity;

/**
 * @author dnsh87
 *
 */
public interface SysLookupRepo extends JpaRepository<SystemLookUpEntity, Long>{
	
	SystemLookUpEntity findByLookupId(Long lookupId);
	
	List<SystemLookUpEntity> findByLookuptypecode(String lookuptypecode);
	
	@Query(value="select * from SYS_LOOKUP where LookupTypeCode=:lookuptypecode and ISACTIVE = 'Y'", nativeQuery=true)
	List<SystemLookUpEntity> lookupByTypeCode(@Param("lookuptypecode")String lookuptypecode);	//Suraj-23/12/2022
}
