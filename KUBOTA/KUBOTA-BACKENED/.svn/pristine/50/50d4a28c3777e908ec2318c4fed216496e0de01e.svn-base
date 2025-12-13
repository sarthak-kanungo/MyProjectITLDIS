package com.i4o.dms.kubota.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.kubota.security.domain.SysUserToken;

/**
 * @author suraj.gaur
 */
public interface SysUserTokenRepo extends JpaRepository<SysUserToken, Long> {

	SysUserToken findByUserName(String username);

	SysUserToken findByUserNameAndToken(String userName, String authToken);

}
