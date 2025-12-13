package com.i4o.dms.kubota.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.i4o.dms.kubota.security.service.UserTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

@Slf4j
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${javatab.token.header}")
    private String tokenHeader;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserTokenService userTokenService;	//Suraj-06/08/2024. Added for checking expired token from database.

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String encryptedToken = httpRequest.getHeader(tokenHeader);
        
        /**
         * @author suraj.gaur
         * @implNote implemented on 04-10-2024 for setting response header for each request. Requested by client 
         * in VAPT points. Getting userName from decrypted token.
         */
        String decryptedToken = null;
        if(encryptedToken != null) {
    		try {
    			decryptedToken = jwtProvider.decryptToken(encryptedToken);
    		} catch (Exception e) {
    			log.error("Error occured in AuthenticationTokenFilter.doFilter() while decrypting the token: ", e);
    		}
        }
        
        String userName = this.jwtProvider.getUsernameFromToken(decryptedToken);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            log.info("Servlet Request URL: "+(httpRequest.getRequestURL()));
            log.info("Login UserName: "+userDetails.getUsername());

            if (this.jwtProvider.validateToken(decryptedToken, userDetails) && !userTokenService.isExpiredToken(userName, encryptedToken)) {	//Suraj-06/08/2024. Added: '&& !userTokenService.isExpiredToken(userName, authToken)'
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}

