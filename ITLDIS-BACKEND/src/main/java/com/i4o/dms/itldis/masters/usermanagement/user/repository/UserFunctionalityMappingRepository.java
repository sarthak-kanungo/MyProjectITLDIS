package com.i4o.dms.itldis.masters.usermanagement.user.repository;

import com.i4o.dms.itldis.masters.usermanagement.user.domain.UserFunctionMapping;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.MappedFunctionsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserFunctionalityMappingRepository extends JpaRepository<UserFunctionMapping,Long> {

    @Deprecated
    List<UserFunctionMapping> findByLoginUserId(Long userId);

    @Query(value = "{call sp_master_user_get_first_level_functionality(:userId)}",nativeQuery = true)
    List<MappedFunctionsResponse> getFirstLevelFunctionality(@Param("userId") Long userId);

    @Query(value = "{call sp_master_user_get_functionality_by_parent_id(:parentId,:userId)}",nativeQuery = true)
    List<MappedFunctionsResponse> getFunctionalityByParentIdAndUserId(@Param("parentId")Long parentId,@Param("userId")Long userId);


    @Query(value = "{call sp_master_user_check_accessibility_by_user(:userId,:routerLink)}",nativeQuery = true)
    Long checkUserRouterAccessibility(@Param("userId")Long userId, @Param("routerLink")String routerLink);
    
    UserFunctionMapping findByLoginUserIdAndRoleId(Long loginUserId, Long roleId);
}
