<%--
    Document   : exportViewInvoiceList
    Created on : 17-Mar-2015, 16:59:33
    Author     : kundan.rastogi
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>

<%
            response.setHeader("Content-Disposition", "attachment; filename=View_Invoices_List.xls");
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
                    <div class="con_slidediv1" style="position: relative;width: 100%">
                        <h3 class="hText"> <bean:message key="label.common.viewInvoiceList" /> </h3>
                        <table id="data" width=100%  border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc   >
                            <c:if test="${empty invoiceList4print}">
                                <tr class="headingSpas" bgcolor="#FFFFFF" height="20">
                                    <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.noRecordFound" /></td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty invoiceList4print}">
                                <tr height=25 bgcolor="#FFFFFF">
                                    <td align= center class="headingSpas">
                                        <table width=100%  border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td nowrap align="center" width="2%" style="font-weight:bold;" ><bean:message key="label.common.sno" /></td>
                                                <td nowrap align="left" width="14%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.InvoiceNo"/></td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.InvoiceDate"/></td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.CustomerName"/></td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.contactno" /></td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.referenceNo"/></td>
                                                <td nowrap align="right" width="10%" style="font-weight:bold;padding-right:3px;"><bean:message key="label.common.invoice" /> <bean:message key="label.common.amt_small" /></td>
                                                <%if (!userFunctionalities.contains("101")) {%>
                                                <%--<td nowrap align="right" width="10%" style="font-weight:bold;padding-right:3px;" ><bean:message key="label.creditNote.creditAmount" /></td>
                                                <%} else {%>
                                                   <td nowrap align="right" width="10%" style="font-weight:bold;padding-right:3px;" ><bean:message key="label.creditNote.creditAmount" /></td>--%>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;white-space: nowrap" ><bean:message key="label.common.dealerName" /></td>
                                                <%}%>
                                            </tr>
                                            <c:set var="sno" value="1"></c:set>
                                            <logic:iterate id="invoiceList4print" name="invoiceList4print">
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;" >${sno}</td>
                                                    <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${invoiceList4print.invoiceno}</td>
                                                    <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;">${invoiceList4print.invoiceDate}</td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;">${invoiceList4print.customerName}</td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;">${invoiceList4print.mobilePhone}</td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;">${invoiceList4print.refNo}</td>
                                                    <td align="right"   bgcolor="#FFFFFF" style="padding-left:3px;"><fmt:formatNumber  type="number" minFractionDigits="2" maxFractionDigits="2"  value="${invoiceList4print.totalEstimate}"/></td>
                                                    <%if (!userFunctionalities.contains("101")) {%>
                                                   <%-- <td align="right"   bgcolor="#FFFFFF" style="padding-left:3px;">${invoiceList4print.creditValue}</td>
                                                    <%} else {%>
                                                    <td align="right"   bgcolor="#FFFFFF" style="padding-left:3px;">${invoiceList4print.creditValue}</td>--%>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;white-space: nowrap">${invoiceList4print.dealercode} [${invoiceList4print.dealerName}] [${invoiceList4print.locationName}]</td>
                                                    <%}%>
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

