package com.i4o.dms.kubota.masters.usermanagement.user.repository;

import com.i4o.dms.kubota.masters.usermanagement.user.domain.LoginUser;
import com.i4o.dms.kubota.masters.usermanagement.user.dto.KubotaUserSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface KubotaUserRepository extends JpaRepository<LoginUser, Long> {

    @Query(value = "{call sp_master_login(:username,:password)}", nativeQuery = true)
    Optional<Map<String, Object>> userLogin(@Param("username") String username, @Param("password") String password);

    @Query(value = "{call sp_master_app_login(:username,:password)}", nativeQuery = true)
    Optional<Map<String, Object>> userAppLogin(@Param("username") String username, @Param("password") String password);

    Optional<LoginUser> findByUserName(String username);
    
    @Query(value = "{call sp_user_profile(:username)}", nativeQuery = true)
    Map<String,Object> getUserProfileDtls(String username);

    @Query(value = "{call sp_search_user(:userCode,:employeeCode,:employeeName,:Applicablefor,:page,:size)}",nativeQuery = true)
    List<KubotaUserSearchResponse> searchKubotaUser(@Param("userCode") String userCode,
    												@Param("employeeCode") String employeeCode,
                                                    @Param("employeeName") String employeeName,
                                                    @Param("Applicablefor") String Applicablefor,
                                                    @Param("page")Integer page,
                                                    @Param("size")Integer size);


    @Query(value = "{call sp_search_user_count(:employeeCode,:employeeName,:page,:size)}",nativeQuery = true)
    Long searchKubotaUserCount(@Param("employeeCode") String employeeCode,
                                                         @Param("employeeName") String employeeName,
                                                         @Param("page")Integer page,
                                                         @Param("size")Integer size);

    @Query(value = "{call sp_role_menu_list_User(:userid,:ApplicableforDealer)}", nativeQuery = true)
    List<Map<String, Object>> assignRole(@Param("userid") Integer userid,
            							 @Param("ApplicableforDealer") Character page);
    
    @Query(value = "{call sp_role_menu_list_User()}", nativeQuery = true)
    List<Map<String, Object>> assignRole();

    @Query(value = "{call sp_login_id_status_dropdown()}", nativeQuery = true)
    List<Map<String, Object>> getLoginIdStatus();
    
    @Query(value = "{call sp_KAI_mt_user_autocomplete_employee_code(:employeeId,:forCreate)}", nativeQuery = true)
    List<Map<String, Object>> getEmployeeCodeList(@Param("employeeId") String employeeId,@Param("forCreate") String forCreate);
    
    @Query(value = "{call SP_Password_Reset(:oldPassword, :newPassword, :count, :username)}", nativeQuery = true)
    Map<String,Object> resetPassword(@Param("oldPassword")String oldPassword,
    		@Param("newPassword")String newPassword,
    		@Param("count")Integer count,
    		@Param("username")String username);
    
    @Query(value = "{call SP_Password_Forgot(:username)}", nativeQuery = true)
    Map<String,Object> forgotPassword(@Param("username")String username);
}

