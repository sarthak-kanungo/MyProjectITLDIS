<%-- 
    Document   : ExportServiceReminder
    Created on : Dec 16, 2014, 6:17:55 PM
    Author     : megha.pandya
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*,dao.LoginDAO,beans.serviceForm" %>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>

<%
            response.setHeader("Content-Disposition", "attachment; filename=serviceReminder.xls");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            LoginDAO dao = new LoginDAO();
            String dealerCode = (String) session.getAttribute("dealerCode");
            serviceForm sf = dao.getDealerAddress(dealerCode);
            String dealerName = sf.getDealerName() + " [" + sf.getLocationName() + "] [" + dealerCode + "]";
%>
<c:set var="dealer" value="<%=dealerName%>" />
<html lang="en">
    <head>  <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
    </head>
    <body>
        <div class="contentmain1">

            <center>
                <div class="content_right1">
                    <div class="con_slidediv1" style="position: relative;width: 100%">
                        <h3> <bean:message key="label.common.serviceReminder" />
                            <%if (userFunctionalities.contains("101")) {%>
                            for <%=dealerName%>
                            <%} else {%>
                            <c:forEach items="${dealerList}"  var="labelValue">
                                <c:if test="${serviceForm.dealercode eq labelValue[0]}">
                                    for ${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]
                                </c:if>
                            </c:forEach>
                            <%}%>
                            From ${serviceForm.fromdate} To ${serviceForm.todate}
                        </h3>
                        <table width=100%  border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc   >
                            <c:if test="${empty sercviceReminderList}">
                                <tr bgcolor="#FFFFFF">
                                    <td valign="top" align="center" class="headingSpas" style="padding-top:10px;color: red">
                                        <bean:message key="label.common.noRecordFound" />
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty sercviceReminderList}">
                                <tr>
                                    <td>
                                        <table width="100%" border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td nowrap align="center" width="2%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.sno" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.Chassisno" />
                                                </td>
                                                <%--<td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.Modelcode" />
                                                </td>--%>
                                                <td nowrap align="left" width="13%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.Modelcodedesc" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.Jobtype" />
                                                </td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.CustomerName" />
                                                </td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.contactno" />
                                                </td>
                                                <td nowrap align="left" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.dueDate" />
                                                </td>
                                                <%if (userFunctionalities.contains("101")) {%>
                                                <%} else {%>
                                                <td   align="left" width="20%"  class="headingSpas" nowrap ><b><bean:message key="label.common.dealerName" /></b></td>
                                                <%}%>
                                                <td nowrap align="left" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.followUpStatus" />
                                                </td>
                                                <td nowrap align="left" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.followUpDate" />
                                                </td>
                                            </tr>
                                            <c:set var="sno" value="1"></c:set>
                                            <logic:iterate id="sercviceReminderList" name="sercviceReminderList">
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                    <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <%--<a href="<%=cntxpath%>/warrantyAction.do?option=packingDetails4View&packingNo=${packingList.packingNo}" >--%>
                                                        <span >${sercviceReminderList.vinNo}</span><%--</a>--%></td>
                                                    <%--<td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${sercviceReminderList.modelCode}</td>--%>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.modelCodeDesc}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.jobTypeDesc}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.customerName}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.contactno}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.dueDate}</span></td>
                                                    <%if (userFunctionalities.contains("101")) {%>
                                                    <%} else {%>
                                                    <td align="left" bgcolor="#FFFFFF" title=" ${sercviceReminderList.dealerName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span>
                                                            ${sercviceReminderList.dealercode} [${sercviceReminderList.dealerName}] [${sercviceReminderList.locationName}]
                                                        </span>
                                                    </td>
                                                    <%}%>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.followUpStatus}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.lastFollowUpDate}</span></td>
                                                </tr>
                                                <c:set var="sno" value="${sno + 1 }" />
                                            </logic:iterate>
                                        </table>
                                    </td>
                                </tr>
                            </c:if>
                        </table>

                    </div>
                </div>
            </center>
        </div>
    </body>
</html>