<%-- 
    Document   : warrantyDashBoard
    Created on : Sep 19, 2014, 5:58:15 PM
    Author     : kundan.rastogi
--%>

<%@ page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8"  />

<%
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");

%>

<c:set var="contextPath" value='<%=request.getContextPath()%>'/>
<c:set var="dashboardValues" value="${fn:split(dashboardvalues,'@@')}"/>
<link rel="stylesheet" href="${contextPath}/css/login.css" type="text/css" />


<div class="contentmain1">
    <div class="dashboard" style="position: relative;">

        <div class="dashboardcs hText">

            <%if (userFunctionalities.contains("701"))
                     {%>
            <div class="dashbox">
                <div class="dashimg6">
                    <a href="${contextPath}/warrantyAction.do?option=initWarrantyList"><p> <bean:message key="label.warranty.pendingforwarranty" /></p></a>

                </div>
            </div>
 <%}%>
            <%if (userFunctionalities.contains("703"))
                                                         {%>
            <div class="dashbox">
                <div class="dashimg5">
                    <a href="${contextPath}/warrantyAction.do?option=viewWarrantyClaim"><p> <bean:message key="label.warranty.viewWarrantyClaim" /></p></a> <%--${contextPath}/warrantyAction.do?option=viewWarrantyClaim--%>

                </div>
            </div>
            <%}%>
          

            <%if (userFunctionalities.contains("706"))
             {%>
            <div class="dashbox">
                <div class="dashimg3" >
                    <a href="${contextPath}/warrantyAction.do?option=pendingForDispatch"><p class="hText"> <bean:message key="label.warranty.pendingforPackGen" /></p></a>
                </div>
            </div>
            <%}%>

         


             <%if (userFunctionalities.contains("707"))
             {%>
            <div class="dashbox">
                <div class="dashimg4">
                    <a href="${contextPath}/warrantyAction.do?option=initPackedWarrantyList"><p> <bean:message key="label.common.pending4Dispatch" /></p></a>
                </div>
            </div>
            <%}%>

          <%if (userFunctionalities.contains("708"))
             {%>
            <div class="dashbox">
                <div class="dashimg6">
                    <a href="${contextPath}/warrantyAction.do?option=viewPackingList"><p><bean:message key="label.common.viewPackingList" /></p></a>
                </div>
            </div>
            <%}%>

             <%if (userFunctionalities.contains("709"))
             {%>
            <div class="dashbox">
                <div class="dashimg1">
                    <a href="${contextPath}/warrantyAction.do?option=pendingForAcknow"><p> <bean:message key="label.common.pendingForAcknow" /></p></a>
                </div>
            </div>
            <%}%>
              <%if (userFunctionalities.contains("705"))
             {%>
            <div class="dashbox">
                <div class="dashimg4">
                    <a href="${contextPath}/warrantyAction.do?option=viewPendingWarrantyClaim"><p> <bean:message key="label.warranty.pendingWarrantyClaim" /></p></a>
                </div>
            </div>
            <%}%>
             <%if (userFunctionalities.contains("711"))
             {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href="${contextPath}/warrantyAction.do?option=pendingSAPList"><p> <bean:message key="label.common.pendingSAPgeneration" /></p></a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("712")) {%>
            <div class="dashbox">
                <div class="dashimg4">
                    <a href="${contextPath}/warrantyAction.do?option=viewCreditNote"><p><bean:message key="label.common.viewCreditNote" /></p></a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("713")) {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href="${contextPath}/warrantyAction.do?option=viewClaimReoprt"><p><bean:message key="label.common.claimReport" /></p></a>
                </div>
            </div>
              <%}%>
              <%if (userFunctionalities.contains("714")) {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href="${contextPath}/warrantyAction.do?option=viewSAPDumpClaimReoprt"><p><bean:message key="label.common.SAPDumpClaimReport" /></p></a>
                </div>
            </div>
              <%}%>
              <%if (userFunctionalities.contains("715")) {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href="${contextPath}/warrantyAction.do?option=viewTaxInvoiceUpdate"><p><bean:message key="label.common.updateTaxInvoice" /></p></a>
                </div>
            </div>
              <%}%>
              <%if (userFunctionalities.contains("716")) {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href="${contextPath}/warrantyAction.do?option=viewTaxInvoiceUpload"><p><bean:message key="label.common.uploadTaxInvoice" /></p></a>
                </div>
            </div>
              <%}%>
               <%if (userFunctionalities.contains("1013")) {%>
            <div class="dashbox">
                <div class="dashimg4">
                    <a href="${contextPath}/warrantyAction.do?option=warrantyClaimStatusDashboard"><p><bean:message key="label.common.warrantyDashboard" /></p></a>
                </div>
            </div>
              <%}%>
               <%if (userFunctionalities.contains("1016")) {%>
            <div class="dashbox">
                <div class="dashimg1">
                    <a href="${contextPath}/warrantyAction.do?option=taxAcknowledgement"><p><bean:message key="label.common.taxAcknowledgement" /></p></a>
                </div>
            </div>
              <%}%>
              
        </div>
    </div>
</div>