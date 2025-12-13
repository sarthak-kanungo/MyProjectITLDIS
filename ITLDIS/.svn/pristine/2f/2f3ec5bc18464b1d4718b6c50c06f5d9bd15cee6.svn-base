<%-- 
    Document   : exportBillingDetailsList
    Created on : Nov 9, 2015, 11:10:02 AM
    Author     : Ashutosh.Kumar1
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*,dao.LoginDAO,beans.serviceForm" %>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>

<%
            response.setHeader("Content-Disposition", "attachment; filename=BillingDetailsList.xls");
         
           

%>




<c:if test="${!empty viewOrderList}">
    <div style="width:99%; overflow:auto">
        <h3 class="hText" align="center"> Bill Details</h3>
        <table width=100%  border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
            <tr bgcolor="#eeeeee" height="20">
                <td   align="center"class="headingSpas" width="4%" nowrap style="padding-left:5px; "><b><bean:message key="label.warranty.S.No" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.InvoiceNo" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.InvoiceDate" /> </b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.totalInvoiceAmount" /> </b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.shippmentDate" /> </b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.dealerCode"/></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.dealerName" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.spare.orderDate" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.order.transporterName" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.lrNo" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.order.permitNo" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.order.suppOrderNo" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.spare.orderType" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.invoice.ordered.part" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.partType" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.invoice.shiped.part" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.partDesc" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.Qty" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.unitprice_small" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.invoice.amount" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.totalAmmount" /></b></td>
                <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.order.totalInvoiceAmount" />&nbsp;&nbsp;<bean:message key="label.orderInvice.inclusiveAllCharges" /></b></td>
            </tr>
            <c:set var="sno" value="1"></c:set>
            <c:set var="totalAmount" value="0"></c:set>
             <c:set var="color" value="Blue"></c:set>
            <logic:iterate id="dataList" name="viewOrderList" indexId="current">
                <c:if test="${color=='Blue'}">
                <tr  bgcolor="#FFFFFF" height="20">
                </c:if>
                 <c:if test="${color=='White'}">
                <tr height="20" bgcolor="#DADCDF">
                </c:if>

                    <td align="center"  width="4%" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                    <td align="left"  width="10%" title="${dataList.invNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                        <span>
                            ${dataList.invNo}
                        </span>
                    </td>
                    <td align="left"  title="${dataList.invDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                        <span>
                            ${dataList.invDate}
                        </span>
                    </td>
                    <td align="left"  title="${dataList.finalamount}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                        <span>
                            ${dataList.finalamount}
                        </span>
                    </td>

                    <td align="left"  width="10%" title="${dataList.shipmentDate} "  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                        <span >
                            ${dataList.shipmentDate}
                        </span>
                    </td>
                    <td align="left"  width="10%" title=" ${dataList.dealerCode}"  class="headingSpas" style="padding-left:5px; padding-right: 5px; white-space: nowrap;">
                        <span>
                            ${dataList.dealerCode}
                        </span>
                    </td>
                  <td align="left"  width="10%" title=" ${dataList.dealerName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px; white-space: nowrap;">
                        <span>
                            ${dataList.dealerName}
                        </span>
                    </td>
                     <td align="left"  width="10%" title=" ${dataList.erpOrderDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px; white-space: nowrap;">
                        <span>
                            ${dataList.erpOrderDate}
                        </span>
                    </td>
                    
                    <td align="left"  width="10%" title=" ${dataList.transporterName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                        ${dataList.transporterName}
                    </td>
                    <td align="left"  width="10%" title=" ${dataList.lrNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                        <span>
                            ${dataList.lrNo}
                        </span>
                    </td>
                    <td align="left"  width="10%" title=" ${dataList.permitNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                        ${dataList.permitNo}
                    </td>
                     <td align="left"  width="10%" title=" ${dataList.erpOrderNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                        ${dataList.erpOrderNo}
                    </td>
                     <td align="left"  width="10%" title=" ${dataList.orderType}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                        ${dataList.orderType}
                    </td>
                     <td align="left"  width="10%" title=" ${dataList.orderedPartNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                        ${dataList.orderedPartNo}
                    </td>
                      <td align="left"  width="10%" title=" ${dataList.partTypeStr}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                        ${dataList.partTypeStr}
                    </td>
                    <td align="left"  width="10%" title=" ${dataList.shippedPartNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                        ${dataList.shippedPartNo}
                    </td>
                     <td align="left"  width="10%" title=" ${dataList.partDescription}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                        ${dataList.partDescription}
                    </td>
                     <td align="left"  width="10%" title=" ${dataList.qty}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                        ${dataList.qty}
                    </td>
                      <td align="left"  width="10%" title=" ${dataList.unitValue}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                        ${dataList.unitValue}
                    </td>
                       <td align="left"  width="10%" title=" ${dataList.amountPerPrice}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                        ${dataList.amountPerPrice}
                    </td>
                     <td align="left"  width="10%" title=" ${dataList.totalAmont}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                           <%--<c:if test="${fn:length(viewOrderList) == sno}">${dataList.totalAmont}</c:if>--%>
                           <logic:equal name="dataList" property="invNo" value="${viewOrderList[current + 1].invNo}">
                             <c:set var="totalAmount" value="${totalAmount + dataList.amountPerPrice}"></c:set>
                            </logic:equal>
                            <logic:notEqual name="dataList" property="invNo" value="${viewOrderList[current + 1].invNo}">
                           <b> ${totalAmount + dataList.amountPerPrice}</b>
                            <c:set var="totalAmount" value="0"></c:set>
                            <c:choose>
                                <c:when test="${color=='Blue'}">
                                    <c:set var="color" value="White"></c:set>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="color" value="Blue"></c:set>
                                </c:otherwise>
                            </c:choose>
                            </logic:notEqual>
                    </td>
                     <td align="left"  width="10%" title=" ${dataList.finalamount}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                       <%-- <c:if test="${fn:length(viewOrderList) == sno}">${dataList.finalamount}</c:if>--%>
                       <logic:notEqual name="dataList" property="invNo" value="${viewOrderList[current + 1].invNo}">
                           <b> ${dataList.finalamount}</b>
                            </logic:notEqual>
                    </td>
                </tr>
                 
                <c:set var="sno" value="${sno + 1 }" />
            </logic:iterate>
        </table>
    </div>
</c:if>

