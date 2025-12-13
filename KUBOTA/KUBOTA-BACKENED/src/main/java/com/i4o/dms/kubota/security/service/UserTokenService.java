package com.i4o.dms.kubota.security.service;

import javax.servlet.ServletRequest;

import com.i4o.dms.kubota.security.dto.LoggedInUserDetailsResDto;

/**
 * @author suraj.gaur
 */
public interface UserTokenService {
	
	String logoutUser(ServletRequest request);
	
	String logoutUser(String userName);
	
	Boolean isExpiredToken(String username, String token);
	
	void recordToken(String userName, String token);

	LoggedInUserDetailsResDto getLoggedInUser();

	Boolean isUserLoggedIn(ServletRequest request);
	
	Boolean isUserLoggedIn(String userName);
	
}
