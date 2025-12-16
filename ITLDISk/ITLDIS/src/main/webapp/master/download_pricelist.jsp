<%--
    Document   : download_pricelist
    Created on : Jun 29, 2016, 12:12:06 PM
    Author     : ashutosh.kumar1
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
            response.setHeader("Content-Disposition", "attachment; filename=PriceListReport.xls");
%>

<html lang="en">
    <head>  <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        <%--<link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >--%>
    </head>
    <body>
        <div class="contentmain1">
            <center>
                <div class="content_right1">
                    <div class="con_slidediv1" style="position: relative;width: 100%">
                        <table width=100%  border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                            <c:if test="${empty priceArrayList}">
                                <tr  bgcolor="#FFFFFF" height="20">
                                    <td colspan="4" valign="bottom" align="center" class="headingSpas" style="padding-top:10px;color: red">
                                        <h3><bean:message key="label.common.noRecordFound" /></h3>
                                    </td>
                                </tr>
                            </c:if>

                            <c:if test="${!empty priceArrayList}">
                                <tr>
                                    <td>
                                        <table width="100%" border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
                                            <%--<tr bgcolor="#cccccc" >
                                                <td colspan="5" align="center" class="headingSpas" valign="center"><h3>Price List Report</h3> </td>
                                            </tr>  --%>
                                            <tr bgcolor="#eeeeee" height="20">
                                                <%--<td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px;font-size: 15" >
                                                    <bean:message key="label.warranty.S.No" />
                                                </td>--%>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <b><bean:message key="label.common.priceListCode" /></b>
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <b><bean:message key="label.common.partno" /></b>
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <b><bean:message key="label.common.partdesc" /></b>
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <b><bean:message key="label.common.orderPrice" /></b>
                                                </td>
                                                
                                                
                                            </tr>

                                            <c:set var="sno" value="1"></c:set>
                                            <logic:iterate id="PiOrderList" name="priceArrayList">
                                                <tr  height="20" bgcolor="#FFFFFF">                                                    
                                                    <%--<td nowrap align="left">
                                                        <span>${sno}</span>
                                                    </td>--%>
                                                    <td nowrap align="left">
                                                        <span>${PiOrderList.priceList_code}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${PiOrderList.partNo}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${PiOrderList.description}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${PiOrderList.orderPrice}</span>
                                                    </td>
                                                    
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