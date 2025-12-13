package com.i4o.dms.kubota.security.service;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i4o.dms.kubota.security.domain.SysUserToken;
import com.i4o.dms.kubota.security.dto.LoggedInUserDetailsResDto;
import com.i4o.dms.kubota.security.jwt.JwtProvider;
import com.i4o.dms.kubota.security.repository.SysUserTokenRepo;

/**
 * @author suraj.gaur
 * @implNote created as per client request for not reusing token after logout successfully.
 */
@Service
public class UserTokenServiceImpl implements UserTokenService{

	@Value("${javatab.token.header}")
    private String tokenHeader;
	
	@Autowired
	private SysUserTokenRepo sysUserTokenRepo;
	
	@Autowired
    private JwtProvider jwtProvider;
	
	@Autowired
    private UserAuthentication userAuthentication;

	public String logoutUser(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

        String decryptedToken = null;
		try {
			decryptedToken = jwtProvider.decryptToken(httpRequest.getHeader(tokenHeader));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		String userName = jwtProvider.getUsernameFromToken(decryptedToken);
        SysUserToken sysUserToken = sysUserTokenRepo.findByUserName(userName);
        
        if(sysUserToken != null) {
            sysUserToken.setIsLoggedIn(false);
            sysUserToken.setLastModifiedDate(new Date());
            
            sysUserTokenRepo.save(sysUserToken);
        } else {
        	return "User Token Does Not Exist.";
        }
		
        return "Success";
	}
	
	public String logoutUser(String userName) {
        SysUserToken sysUserToken = sysUserTokenRepo.findByUserName(userName);
        
        if(sysUserToken != null) {
            sysUserToken.setIsLoggedIn(false);
            sysUserToken.setLastModifiedDate(new Date());
            
            sysUserTokenRepo.save(sysUserToken);
        } else {
        	return "User Token Does Not Exist.";
        }
		
        return "Success";
	}
	
	public Boolean isExpiredToken(String username, String token) {
		SysUserToken sysUserToken = sysUserTokenRepo.findByUserNameAndToken(username, token);
		
		if(sysUserToken == null)
			return true;
		
		return !sysUserToken.getIsLoggedIn();
	}
	
	@Transactional
	public void recordToken(String userName, String token) {
		SysUserToken sysUserToken = sysUserTokenRepo.findByUserName(userName);
		
		if(sysUserToken != null) {
			throw new IllegalStateException("Someone is already logged in with this user. "+sysUserToken.getIsLoggedIn());//added on 16-12-24 by Mahesh.Kumar
//			sysUserToken.getIsLoggedIn();
//			System.out.print("Someone already logedIn with this user");
//			sysUserToken.setToken(token);
//			sysUserToken.setIsLoggedIn(true);
//			sysUserToken.setLastModifiedDate(new Date());
		} 
		else {
			sysUserToken = new SysUserToken();
			sysUserToken.setUserName(userName);
			sysUserToken.setToken(token);
			sysUserToken.setIsLoggedIn(true);
			sysUserToken.setCreatedDate(new Date());
		}
		sysUserTokenRepo.save(sysUserToken);
	}

	@Override
	public LoggedInUserDetailsResDto getLoggedInUser() {
		LoggedInUserDetailsResDto resDto = new LoggedInUserDetailsResDto();
		
		resDto.setLoginId(userAuthentication.getLoginId());
		resDto.setBranchId(userAuthentication.getBranchId());
		resDto.setDealerId(userAuthentication.getDealerId());
		resDto.setUsername(userAuthentication.getUsername());
		resDto.setKubotaEmployeeId(userAuthentication.getKubotaEmployeeId());
		resDto.setDealerEmployeeId(userAuthentication.getDealerEmployeeId());
		
		return resDto;
	}

	@Override
	public Boolean isUserLoggedIn(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

        String encryptedToken = httpRequest.getHeader(tokenHeader);
        String decryptedToken = null;
        
		try {
			decryptedToken = jwtProvider.decryptToken(encryptedToken);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		String userName = jwtProvider.getUsernameFromToken(decryptedToken);
        
		return jwtProvider.getUsernameFromToken(decryptedToken) == null ? false : true 
				&& !this.isExpiredToken(userName, encryptedToken);
	}

	@Override
	public Boolean isUserLoggedIn(String userName) {
		SysUserToken sysUserToken = sysUserTokenRepo.findByUserName(userName);

		if(sysUserToken == null)
			return true;
		
		String decryptedToken = null;
		try {
			decryptedToken = jwtProvider.decryptToken(sysUserToken.getToken());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return jwtProvider.getUsernameFromToken(decryptedToken) == null ? false : true 
				&& sysUserToken.getIsLoggedIn();
	}
}
