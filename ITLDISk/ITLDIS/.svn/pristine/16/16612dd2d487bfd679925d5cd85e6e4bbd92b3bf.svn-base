<%-- 
    Document   : manage_inventoryOptions
    Created on : Aug 20, 2014, 6:15:54 PM
    Author     : sreeja.vijayakumaran
--%>

<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/css/login.css" type="text/css" />
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            //  String imagesURL = (String) session.getAttribute("imagesURL");
            ArrayList lockedDealerList= (ArrayList) session.getAttribute("lockedDealerlist");
            String dealerCode = (String) session.getAttribute("dealerCode");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");

%>
<c:set var="contextPath" value='<%=request.getContextPath()%>'/>
<c:set var="dashboardValues" value="${fn:split(dashboardvalues,'@@')}"/>
<link rel="stylesheet" href="${contextPath}/css/login.css" type="text/css" />
<script src="${pageContext.request.contextPath}/js/base64.js"></script>

<div class="contentmain1">
    <div class="dashboard" style="position: relative;">

        <div class="dashboardcs hText">

         
            <%if (userFunctionalities.contains("903")) {%>
            <div class="dashbox">
                <div class="dashimg3">                    
                    <a href="<%=cntxpath%>/reportAction.do?option=initMisReport"><bean:message key="label.report.misfordealer" /></a>
                </div>
            </div>

            <%}%>
            <%if (userFunctionalities.contains("913")) {%>
            <div class="dashbox">
                <div class="dashimg1">
                    <a href="<%=cntxpath%>/reportAction.do?option=getListSpareLubeReport&flag=DLR"><bean:message key="label.report.spareAndLube" /></a>
                </div>
            </div>
            <%}%>

            <%if (userFunctionalities.contains("914")) {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href="<%=cntxpath%>/reportAction.do?option=getListSpareLubeRolling&flag=DLR"><bean:message key="label.report.spareAndLubeRolling" /></a>
                </div>
            </div>
            <%}%>

          <%if (userFunctionalities.contains("915")) {%>
            <div class="dashbox">
                <div class="dashimg1">
                    <a href="<%=cntxpath%>/reportAction.do?option=getListSpareLubeReportSTK&flag=STK"><bean:message key="label.report.spareAndLubeSTK" /></a>
                </div>
            </div>
          <%}%>
          <%if (userFunctionalities.contains("916")) {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href="<%=cntxpath%>/reportAction.do?option=getListSpareLubeRollingSTK&flag=STK"><bean:message key="label.report.spareAndLubeRollingSTK" /></a>
                </div>
            </div>
           <%}%>
           <%if (userFunctionalities.contains("918")) {%>
            <div class="dashbox">
                <div class="dashimg">
                    <a href="<%=cntxpath%>/reportAction.do?option=viewSaleInvoiceReport"><bean:message key="label.report.SaleInvoiceReport" /></a>
                </div>
            </div>
           <%}%>
           <%if (userFunctionalities.contains("918")) {%>
            <div class="dashbox">
                <div class="dashimg1">
                    <a href="<%=cntxpath%>/reportAction.do?option=viewPedningPIConfirmationReport"><bean:message key="label.report.PendingPiConfirmation" /></a>
                </div>
            </div>
           <%}%>
            <%if (userFunctionalities.contains("920")) {%>
            <div class="dashbox">
                <div class="dashimg3">
                    <a href="<%=cntxpath%>/reportAction.do?option=initGstr1Report"><bean:message key="label.report.gstr1" /></a>
                </div>
            </div>

            <%}%>
            <%if (userFunctionalities.contains("920")) {%>
            <div class="dashbox">
                <div class="dashimg">
                    <a href="<%=cntxpath%>/reportAction.do?option=initGstr2Report"><bean:message key="label.report.gstr2" /></a>
                </div>
            </div>

            <%}%>

        </div>
    </div>
</div>





