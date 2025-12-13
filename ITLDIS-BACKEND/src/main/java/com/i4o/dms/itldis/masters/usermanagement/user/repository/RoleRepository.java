package com.i4o.dms.itldis.masters.usermanagement.user.repository;

import com.i4o.dms.itldis.masters.usermanagement.user.domain.RoleMaster;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.MainTab;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.RoleSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface RoleRepository extends JpaRepository<RoleMaster, Long> {

	@Query(value = "{call sp_get_tab_module(:user)}", nativeQuery = true)
	List<MainTab> getTabModuleByApplicableUser(@Param("user") String user);

//	@Query(value = "{call sp_get_assigned_role(:applicableTo,:parentId,:roleName)}", nativeQuery = true)
//	List<Map<String, Object>> getAssignedRole(@Param("applicableTo") String applicableTo,
//			@Param("parentId") Long parentId, @Param("roleName") String roleName);
	
	@Query(value = "{call sp_get_assigned_role(:applicableTo,:parentId)}", nativeQuery = true)
	List<Map<String, Object>> getAssignedRole(@Param("applicableTo") String applicableTo,
			@Param("parentId") Long parentId);

	@Query("select r.id as id,r.roleName as roleName from RoleMaster r where r.applicableTo=:applicableTo and r.activeStatus='Y'")
	List<Map<String, Object>> getApplicableRole(@Param("applicableTo") String applicableTo);

	@Query(value = "{call sp_assigned_role_function_to_user(:userId,:roleId)}", nativeQuery = true)
	List<MainTab> assignRoleFunctionToUser(@Param("userId") Long userId, @Param("roleId") Long roleId);

	@Query(value = "{call sp_get_permission_by_functionality_for_kubota_user(:functionId)}", nativeQuery = true)
	List<Map<String, Object>> getPermissionsByFunctionalityForKubotaUser(@Param("functionId") Long functionId);

	@Query(value = "{call sp_master_user_get_applicable}", nativeQuery = true)
	List<Map<String, Object>> getApplicable();

	@Query(value = "{call sp_role_code_auto(:Value,:Type)}", nativeQuery = true)
	List<Map<String, Object>> findByRoleCodeContaining(@Param("Value") String roleCode,@Param("Type")String type);

	@Query(value = "{call sp_role_description_auto(:roleName)}", nativeQuery = true)
	List<Map<String, Object>> findByRoleNameContaining(@Param("roleName") String roleName);

	@Query(value = "{call sp_search_role_master(:roleCode,:roleName,:isActive,:applicableFor,:page,:size)}", nativeQuery = true)
	List<RoleSearchResponse> searchRole(@Param("roleCode") String roleCode, @Param("roleName") String roleName,
			@Param("isActive") String isActive, @Param("applicableFor") String applicableFor,
			@Param("page") Integer page, @Param("size") Integer size);

	@Query(value = "{call sp_search_role_master_count(:roleCode,:roleName,:page,:size)}", nativeQuery = true)
	Long searchRoleCount(@Param("roleCode") String roleCode, @Param("roleName") String roleName,
			@Param("page") Integer page, @Param("size") Integer size);

}
