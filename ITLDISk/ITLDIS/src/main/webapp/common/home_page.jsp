<%-- 
    Document   : home_page
    Created on : May 21, 2014, 1:08:17 PM
    Author     : jasmeen.kaur
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8"  />
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<script>

/*

Block right-click

Block F12

Block Ctrl+Shift+I (Inspect)

Block Ctrl+U (View Source)
	
	*/
	
	document.addEventListener('contextmenu', e => e.preventDefault());
	document.onkeydown = function(e) {
	    if (e.keyCode == 123) { // F12
	        return false;
	    }
	    if (e.ctrlKey && e.shiftKey && e.keyCode == 73) { // Ctrl+Shift+I
	        return false;
	    }
	    if (e.ctrlKey && e.keyCode == 85) { // Ctrl+U
	        return false;
	    }
	};
	

</script>

<c:set var="contextPath" value='<%=request.getContextPath()%>'/>
<link rel="stylesheet" href="${contextPath}/css/login.css" type="text/css" />
<%

            System.out.println("sessiondf inside homepage==" + session.getAttribute("user_id"));
%>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            //  String imagesURL = (String) session.getAttribute("imagesURL");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");

%>
<div class="contentmain1">
    <div class="dashboard hText" style="position: relative;">

        <div class="dashboardcs">
            <%if (userFunctionalities.contains("600")) {%>
            <div class="dashbox">
                <div class="dashimg">
                    <html:link action="/initService">  <p>  <bean:message key="label.common.Service" /> </p>
                        <div class="count"><span>${dashboardValues[0]}</span></div></html:link>
                    </div>
                </div>
            <%}%>
            <%if (userFunctionalities.contains("700")) {%>
            <div class="dashbox">
                <div class="dashimg1">
                    <html:link action="/initWarranty"><p>  <bean:message key="label.common.warranty" /></p>
                        <div class="count"><span>${dashboardValues[1]}</span></div></html:link>
                    </div>
                </div>
            <%}%>
             <%if (userFunctionalities.contains("1000")) {%>
            <div class="dashbox">
                <div class="dashimg">
                    <a href="<%=cntxpath%>/initExtWarranty.do"><p><bean:message key="label.common.extWarrantyReg" /></p>
                        <div class="count"><span>${dashboardValues[2]}</span></div></a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("800")) {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href="<%=cntxpath%>/inventoryAction.do?option=initInvOptions"><p>  <bean:message key="label.common.spares" /></p>
                        <div class="count"><span>${dashboardValues[2]}</span></div></a>
                </div>
            </div>
            <%}%>           
            <%if (userFunctionalities.contains("2")) {%>
            <div class="dashbox">
                <div class="dashimg3">
                    <a href="changelogin" target="_blank"> <p> <bean:message key="label.common.catalogue" /> </p>
                        <div class="count"><span>${dashboardValues[3]}</span></div></a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("300")) {%>
             <div class="dashbox">
                <div class="dashimg1">
                    <a href="<%=cntxpath%>/masterAction.do?option=initSales"><p>  <bean:message key="label.common.sales" /></p>
                        <div class="count"><span>${dashboardValues[1]}</span></div></a>
                    </div>
                </div>
            
            <%}%>
           <%if (userFunctionalities.contains("902")) {%>
             <div class="dashbox">
                <div class="dashimg4">
                    <a href="<%=cntxpath%>/reportAction.do?option=options"><p><bean:message key="label.report.report" /></p>
                        <div class="count"><span>${dashboardValues[1]}</span></div></a>
                    </div>
                </div>

            <%}%>

        </div>
    </div>
</div>

