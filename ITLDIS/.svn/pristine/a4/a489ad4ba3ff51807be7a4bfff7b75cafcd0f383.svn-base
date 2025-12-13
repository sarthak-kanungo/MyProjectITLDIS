<%-- 
    Document   : exportviewMisReportDMS
    Created on : Jul 27, 2015, 12:44:26 PM
    Author     : Ashutosh.Kumar1
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*" %>

<%
            response.setHeader("Content-Disposition", "attachment; filename=viewMisReport.xls");
            //Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/reportAction.do?option=options'><bean:message key="label.report.report" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.report.misfordealer" /></li>
        </ul>
    </div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.report.misfordealer" />&nbsp;for &nbsp;<bean:write name="reportForm" property="dealerNameWithCode" /> &nbsp;Report From <bean:write name="reportForm" property="fromDate" />&nbsp;to&nbsp;<bean:write name="reportForm" property="toDate" /> </h1>
                <%@include file="jasperReport/dmis_spareLubes_Report1.jsp" %>
            </div>
        </div>
    </center>
</div>