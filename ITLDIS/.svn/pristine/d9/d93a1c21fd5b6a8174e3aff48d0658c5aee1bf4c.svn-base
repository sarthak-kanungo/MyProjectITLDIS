<%--
    Document   : exportViewOrderInvDetReport
    Created on : 07-Sep-2017, 15:26:21
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
            String cce = request.getParameter("cce");
            String fileName = cce.equals("ordDet") ? "Order_Details_Report.xls" : "Invoice_Details_Report.xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + "");
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
                        <h2 class="hText">
                            <c:if test="${reportForm.cce eq 'ordDet'}"><bean:message key="label.report.OrderDet" /></c:if>
                            <c:if test="${reportForm.cce eq 'invDet'}"><bean:message key="label.report.InvDet" /></c:if>
                        </h2>
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
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <%} else {%>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.dealerName" /></b></td>
                                            <%}%>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.stockistName" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.jobcardno" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.jobcardDate" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.spare.poNo" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.spare.PODate" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.spare.orderType" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.spare.orderStatus" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.adOrderNo" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.adOrderDate" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b>ERP <bean:message key="label.common.remarks" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.ordPartNo" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b>Ordered <bean:message key="label.common.partdesc_small" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.catalogue.ordered.quantity" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.backOrdQty" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.InvoiceNo" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.InvoiceDate" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.order.totalInvoiceAmount" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.shipStatus" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.ShippedPart" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b>Shipped <bean:message key="label.common.partdesc_small" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b>Shipped <bean:message key="label.common.qty_small" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.invRate" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.partInValue" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.report.ShipmentDate" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.lrNo" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.transporterName" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.permitNo" /> </b></td>


                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="dataList" name="viewOrderList">

                                            <tr  bgcolor="#eeeeee" height="20">
                                                <td align="center" bgcolor="#FFFFFF" width="4%" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                <%if (userFunctionalities.contains("101")) {%>
                                                <%} else {%>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.dealerCode} [${dataList.dealerName}] [${dataList.location}]"  class="headingSpas" style="padding-left:5px; padding-right: 5px; white-space: nowrap;">
                                                    <span>
                                                        ${dataList.dealerCode} [${dataList.dealerName}] [${dataList.location}]
                                                    </span>
                                                </td>
                                                <%}%>

                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.stockistName}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.stockistName}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.jobCardNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.jobCardNo}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.jobCardDate}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.jobCardDate}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.newPoNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.newPoNo}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.poDate}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.poDate}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.orderType}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.orderType}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.orderStatus}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.orderStatus}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.erpOrderNo}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.erpOrderNo}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.erpOrderDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                    ${dataList.erpOrderDate}
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.erpRemarks}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                    ${dataList.erpRemarks}
                                                </td>

                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.partno}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                    ${dataList.partno}
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.part_desc}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                    ${dataList.part_desc}
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.qty}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                    ${dataList.qty}
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.backOrdQty}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                    ${dataList.backOrdQty}
                                                </td>


                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.invNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.invNo}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.invoiceDate}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.invoiceDate}
                                                    </span>
                                                </td>

                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.totalAmont}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.totalAmont}
                                                    </span>
                                                </td>

                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.status}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.status}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.shippedpartno}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.shippedpartno}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.shippedpartDesc}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.shippedpartDesc}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.shippedQty}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.shippedQty}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.invoiceRate}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.invoiceRate}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.partInvoiceValue}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.partInvoiceValue}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.shipmentDate}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.shipmentDate}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.lrNo}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.lrNo}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.transporterName}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.transporterName}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${dataList.permitNo}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span>
                                                        ${dataList.permitNo}
                                                    </span>
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