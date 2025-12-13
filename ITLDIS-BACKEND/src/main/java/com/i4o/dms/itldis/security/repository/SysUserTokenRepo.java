package com.i4o.dms.itldis.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.itldis.security.domain.SysUserToken;

/**
 * @author suraj.gaur
 */
public interface SysUserTokenRepo extends JpaRepository<SysUserToken, Long> {

	SysUserToken findByUserName(String username);

	SysUserToken findByUserNameAndToken(String userName, String authToken);

}
