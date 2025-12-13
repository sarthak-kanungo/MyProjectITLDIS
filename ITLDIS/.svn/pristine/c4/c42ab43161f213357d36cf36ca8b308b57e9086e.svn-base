<%--
    Document   : exportViewOrder
    Created on : 04-jan-2016, 11:26:21
    Author     : ashutosh.kumar
--%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Content-Disposition", "attachment; filename=View_Order_List.xls");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");

%>

<html lang="en">
    <head> <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
    </head>
    <body>
        <div class="contentmain1">
            <center>
                <div class="content_right1">
                    <div class="con_slidediv2" style="position: relative;width: 100%">
                        <h2 class="hText"><bean:message key="label.spare.viewOrder" /></h2>
                        <c:if test="${empty viewOrderList}">
                            <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                                <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.noRecordFound" /></td>
                            </tr>
                        </c:if>
                        <c:if test="${!empty viewOrderList}">

                            <tr height=25 bgcolor="#FFFFFF">
                                <td align= center class="headingSpas">
                                    <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td   align="center" class="headingSpas" width="4%" nowrap style="padding-left:5px; "><b><bean:message key="label.warranty.S.No" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.spare.poNo" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.spare.PODate" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="8%" nowrap style="padding-left:5px; "><b><bean:message key="label.spare.noOfLines" /> </b></td>
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <%} else {%>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.dealerName" /></b></td>
                                            <%}%>
                                            <td   align="left"  class="headingSpas" width="8%" nowrap style="padding-left:5px; "><b><bean:message key="label.spare.orderType" /></b></td>
                                            <td   align="left"  class="headingSpas" width="8%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.status" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.spare.consigneeName" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.spare.consigneeCountry" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.spare.destinationPlace" /></b></td>
                                        </tr>

                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="dataList" name="viewOrderList">


                                            <tr  bgcolor="#eeeeee" height="20">
                                                <td align="center" bgcolor="#FFFFFF" width="4%" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.newPoNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                   <span>
                                                     ${dataList.newPoNo}<!-- <a href="#" class="viewOrderDetails"> -->
                                                     </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.poDate}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.poDate}
                                                    </span>
                                                </td>

                                                <td align="left" bgcolor="#FFFFFF" width="8%" title="${dataList.partCount}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span >
                                                        ${dataList.partCount}
                                                    </span>
                                                </td>
                                                <%if (userFunctionalities.contains("101")) {%>
                                                <%} else {%>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.dealerName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px; white-space: nowrap;">
                                                    <span>
                                                        ${dataList.dealerCode} [${dataList.dealerName}] [${dataList.location}]
                                                    </span>
                                                </td>
                                                <%}%>
                                                <td align="left" bgcolor="#FFFFFF" width="8%" title=" ${dataList.orderType}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.orderType}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" width="8%" title=" ${dataList.status}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                                                    ${dataList.status}
                                                </td>
                                                <%if (userFunctionalities.contains("101")) {%>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.consigneeName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">  <%--;white-space: nowrap;--%>

                                                    ${dataList.consigneeName}

                                                    <%} else {%>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.consigneeCode} [ ${dataList.consigneeName} ]"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">  <%--;white-space: nowrap;--%>

                                                    <c:if test="${dataList.consigneeCode ne '-'}">
                                                        ${dataList.consigneeCode} [ ${dataList.consigneeName} ]

                                                    </c:if>
                                                    <c:if test="${dataList.consigneeCode eq '-'}">
                                                        - -
                                                    </c:if>
                                                    <%}%>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${inventoryForm.consigneeCountry}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                    ${dataList.consigneeCountry}
                                                </td>

                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${inventoryForm.destinationPlace}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                    ${dataList.destinationPlace}
                                                </td>


                                            </tr>


                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                    </table>
                                </td>
                            </tr>
                        </c:if>
                    </div>



                </div>

            </center>
        </div>
    </body>
</html>