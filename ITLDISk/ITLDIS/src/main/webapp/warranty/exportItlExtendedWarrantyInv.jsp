<%--
    Document   : exportBajajExtendedWarrantyInv
    Created on : 21-Apr-2017, 11:51:33
    Author     : prashant.kumar
--%>

<!DOCTYPE html>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*,dao.LoginDAO,beans.serviceForm" %>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=Extented_Warranty_Invoice.xls");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            LoginDAO dao = new LoginDAO();
            String dealerCode = (String) session.getAttribute("dealerCode");
            serviceForm sf = dao.getDealerAddress(dealerCode);
            String dealerName = sf.getDealerName() + "[" + dealerCode + "]";
%>
<center>
    <div class="content_right1">
        <div align="left" class="con_slidediv1" style="position: relative;width: 100%">
            <%--<h3><bean:message key="label.common.bajajExtendedWarrantyInvoice" /> for Dealer :
                <%if (userFunctionalities.contains("101")) {%>
                <%=dealerName%>,
                <%} else {%>
                <c:if test="${serviceForm.dealercode eq 'All'}">
                    All,
                </c:if>                
                <c:forEach items="${dealerList}"  var="labelValue">
                    <c:if test="${serviceForm.dealercode eq labelValue[0]}">
                        ${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}],
                    </c:if>
                </c:forEach>
                <%}%>
                <c:if test="${serviceForm.chassisNo ne ''}">
                    Chassis No. : ${serviceForm.chassisNo},
                </c:if>                
                <c:if test="${serviceForm.fromdate ne ''}">                    
                    From  ${serviceForm.fromdate} to ${serviceForm.todate},
                </c:if>
                Ext warranty Claim No : ${serviceForm.warrantyClaimNo}
            </h3>--%>
            <table width=100%  border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc>
                <c:if test="${!empty expWarrListExport}">
                    <tr height=25 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <table width=100% border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                <tr bgcolor="#eeeeee" height="20">
                                    <td nowrap align="center" width="2%" style="font-weight:bold;" ><bean:message key="label.common.sno" /></td>
                                    <td nowrap align="left" width="14%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.ClaimNo"/></td>
                                    <%if (!userFunctionalities.contains("101")) {%>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;white-space: nowrap" ><bean:message key="label.common.dealerCode" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;white-space: nowrap" ><bean:message key="label.common.dealerName" /></td>
                                    <%}%>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.warranty.claimDate"/></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.jobcardno"/></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.Chassisno" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.hrm" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.location" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.stateName" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.extWarrBookNo" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.bajajPolicyNo" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.bajajPolicyDate" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.InvoiceNo" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.InvoiceDate" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.claimInvoicedAmount" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.catalogue.aggregate" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.warranty.subAggregate" /></td>
                                   

                                </tr>
                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="extWarr" name="expWarrListExport">
                                    <tr bgcolor="#eeeeee" height="20">                                             
                                        <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;">${sno}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.warrantyClaimNo}</td>
                                        <%if (!userFunctionalities.contains("101")) {%>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.dealer_code}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.dealerName}</td>
                                        <%}%>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.claimDate}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.jobCardNo}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.vinNo}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.hmr}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.location}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.stateName}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.extWarrantyBookletNo}</td>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.bajajPolicyNo}</td>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.bajajPolicyDate}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.invNo}</td>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.invDate}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.claimInvoicedAmount}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.aggDisc}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.subAggDesc}</td>
                                    </tr>
                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>
                            </table>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${empty expWarrListExport}">
                    <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                        <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.bajajExtendedWarrantyInvoice" />Not Found.!</td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
</center>