package com.i4o.dms.itldis.masters.usermanagement.user.service;

import com.i4o.dms.itldis.masters.usermanagement.user.dto.AddLoginUserDto;
import com.i4o.dms.itldis.security.model.AuthenticatedUser;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LoginUserService {
    void saveUserFunctions(AddLoginUserDto loginUserDto);
    void updateUserFunctions(AddLoginUserDto loginUserDto);
    AuthenticatedUser getAuthenticatedUser(String loginUser);
}
