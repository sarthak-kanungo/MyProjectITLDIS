/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * *******************************************************************************
 * Filter to handle session.
 *
 * @author Amandeep Juneja
 ********************************************************************************
 */
public class sessionFilter implements Filter {

    private FilterConfig _filterConfig = null;
    private final static String FILTER_APPLIED = "_security_filter_applied";
    public static String check_login = "login";
    public static String check_logout = "logout";

    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }

    public void destroy() {
        _filterConfig = null;
    }

    public sessionFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String checkforloginpage = req.getRequestURI();
        
        if (checkforloginpage.contains("DigitalSignatureAction.do")) {
        System.out.println(checkforloginpage);
        }
        
        String contextPath = req.getContextPath();//D8EA56D0818EFC3DCB87E98AFE34C589
        
        // added by aman for DSE - 14-05-2025
        if (checkforloginpage.contains("DigitalSignatureAction.do")) {
        	System.out.println("inside if DigitalSignatureAction.do");
            chain.doFilter(request, response);
            return;
        }


        else if (!checkforloginpage.contains("login.do") && !checkforloginpage.endsWith("index.jsp") &&  !checkforloginpage.endsWith("SessionExpired.jsp") && !checkforloginpage.endsWith(contextPath+"/"))    
        {
            request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
            //If the session bean is not null get the session bean property session_active.
            if ((session.getAttribute("session_active")) == null) {
                res.sendRedirect(contextPath + "/login/SessionExpired.jsp");
                return;
            }
            check_login = "login";
        }else if(checkforloginpage.endsWith("index.jsp") || checkforloginpage.endsWith(contextPath+"/")){

             if ((session.getAttribute("session_active")) != null) {
                res.sendRedirect(contextPath + "/login.do?option=homePage");
                return;
            }
        }

        //deliver request to next filter
        chain.doFilter(request, response);
    }
}