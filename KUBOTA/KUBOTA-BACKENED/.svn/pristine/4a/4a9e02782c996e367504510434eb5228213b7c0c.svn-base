package com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.DesignationLevelMaster;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.dto.DesignationLevelSearchResponse;

@Repository
public interface DesignationLevelRepository extends JpaRepository<DesignationLevelMaster, Long>{
	
	 	@Query("SELECT id as id,designationlevel as designationlevel FROM DesignationLevelMaster where designationlevel like %:designationlevel%")
	    List<Map<String,Object>> searchByDesignationLevel(@Param("designationlevel") String designationlevel);

	    @Query(value = "{call sp_kai_mt_designation_level_search(:designation,:Department,:page,:size)}",nativeQuery = true)
	    List<DesignationLevelSearchResponse> searchDesignationLevel(@Param("designation") String designation,
	                                                      @Param("Department") String Department,
	                                                      @Param("page")Integer page,
	                                                      @Param("size")Integer size);

	    @Query(value = "{call sp_kai_mt_designation_level_count(:designation,:Department,:page,:size)}",nativeQuery = true)
	    Long searchDesignationLevelCount(@Param("designation") String designation,
	                                                      @Param("Department") String Department,
	                                                      @Param("page")Integer page,
	                                                      @Param("size")Integer size);

}
