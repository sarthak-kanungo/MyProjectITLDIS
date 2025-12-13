<%--
    Document   : v_pending_InstallList_Excel
    Created on : Oct 30, 2015, 2:46:39 PM
    Author     : Avinash.Pandey
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*" %>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>


<%
            response.setHeader("Content-Disposition", "attachment; filename=PENDING_INSTALLATION_LIST.xls");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String cntxpath = request.getContextPath();
%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>ITLDIS</title>
        <script src="<%=cntxpath%>/js/Master_290414.js"></script>
    </head>
    <body>
        <div class="contentmain1">
            <center>
                <div class="content_right1">
                    <div class="con_slidediv2" style="position: relative;width: 100%">
                        <h2><bean:message key="label.common.pendinginstallation" /></h2>
                        <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                            <c:if test="${!empty chassisList}">
                                <tr height=25 bgcolor="#FFFFFF">
                                    <td align= center class="headingSpas">
                                        <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;">
                                                    <bean:message key="label.common.sno" />
                                                </td>

                                                <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.Chassisno" />
                                                </td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.spare.deliveryDate" />
                                                </td>
                                                <td nowrap align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.Modelfamily" />
                                                </td>
                                                <td nowrap align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.Modelcode" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.CustomerName" />
                                                </td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.mobilephone" />
                                                </td>
                                                <%if (userFunctionalities.contains("101")) {%>

                                                <%} else {%>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.dealerName" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.location" />
                                                </td>
                                                <%}%>
                                            </tr>
                                            <c:set var="sno" value="1"></c:set>
                                            <logic:iterate id="chassisList" name="chassisList">
                                                <pg:item>
                                                    <tr  bgcolor="#eeeeee" height="20">
                                                        <td nowrap align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>

                                                        <td align="left" nowrap width="10%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                            
                                                            ${chassisList.vinNo}
                                                            
                                                        </td>

                                                        <td align="left" width="10%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.deliveryDate}<span ></span></td>
                                                        <td align="left" width="12%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.modelFamily}<span ></span></td>
                                                        <td align="left" width="12%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.modelCode}<span ></span></td>
                                                        <td align="left" width="15%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.customerName}<span></span></td>
                                                        <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.mobilePhone}<span></span></td>
                                                        <%if (userFunctionalities.contains("101")) {%>

                                                        <%} else {%>
                                                        <td align="left" width="15%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.dealercode} [${chassisList.dealerName}]<span></span></td>
                                                        <td align="left" width="15%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.locationName}<span></span></td>
                                                        <%}%>
                                                </pg:item>
                                                <c:set var="sno" value="${sno + 1 }" />
                                            </logic:iterate>
                                            <tr >

                                                <%if (userFunctionalities.contains("101")) {%>
                                                <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                    <%} else {%>
                                                <td colspan="9" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                    <%}%>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${empty chassisList}">
                                <tr  bgcolor="#eeeeee" height="10">
                                    <td align= "center" style="color: red;"> <bean:message key="label.common.noRecordFound"/></td>
                                </tr>
                            </c:if>
                        </table>
                    </div>
                </div>
            </center>
        </div>
    </body>
</html>

