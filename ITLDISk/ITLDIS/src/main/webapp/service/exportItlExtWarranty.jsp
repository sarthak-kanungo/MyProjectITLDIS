<%--
    Document   : exportExpWarranty
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
            response.setHeader("Content-Disposition", "attachment; filename=Extented_Warranty.xls");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            LoginDAO dao = new LoginDAO();
            String dealerCode = (String) session.getAttribute("dealerCode");
            serviceForm sf = dao.getDealerAddress(dealerCode);
            String dealerName = sf.getDealerName() + "[" + dealerCode + "]";
%>
<center>
    <div class="content_right1">
        <div align="left" class="con_slidediv1" style="position: relative;width: 100%">
            <h3><bean:message key="label.common.extWarrantyReport" /> for Dealer :
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
                Status : ${serviceForm.status}
            </h3>
            <table width=100%  border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc>
                <c:if test="${!empty expWarrListExport}">
                    <tr height=25 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <table width=100% border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                <tr bgcolor="#eeeeee" height="20">
                                    <td nowrap align="center" width="2%" style="font-weight:bold;" ><bean:message key="label.common.sno" /></td>
                                    <td nowrap align="left" width="14%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.itlEwRef"/></td>
                                    <%if (!userFunctionalities.contains("101")) {%>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;white-space: nowrap" ><bean:message key="label.common.dealerCode" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;white-space: nowrap" ><bean:message key="label.common.dealerName" /></td>
                                    <%}%>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.dateSaleOfCertificate"/></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.Chassisno"/></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.catalogue.engineNo" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.fuelType" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.makeName" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.modelName" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.Registrationno" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.dateSaleOfVehicleEqui" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.warranty.dateofDelivery" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.hrm" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.title" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.nameOfCust" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.delivery.Address" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.city" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.state" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.pincode" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.mobilephone" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.landline" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.email" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.sumInsured" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.policyTerm" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.policyType" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.premiumAmount" /></td>                                   
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.bajajPolicyNo" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.bajajPolicyDate" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.status" /></td>
                                    <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.warranty.machanicname" /></td>
                                    <%if (!userFunctionalities.contains("101")) {%>
                                        <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.amtPaidToBajaj" /></td>                                        
                                        <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.extWarrBookNo" /></td>
                                        <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.typeName" /></td>
                                        <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.imdCode" /></td>
                                        <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.floatType" /></td>
                                        <td nowrap align="left" width="10%" style="padding-left:3px;font-weight:bold;"><bean:message key="label.common.ppId" /></td>                                        
                                    <%}%>

                                </tr>
                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="extWarr" name="expWarrListExport">
                                    <tr bgcolor="#eeeeee" height="20">                                             
                                        <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;">${sno}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.ewLoaderId}</td>
                                        <%if (!userFunctionalities.contains("101")) {%>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.dealercode}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.dealerName}</td>
                                        <%}%>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.dateOfSaleOfCertificate}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.chassisNo}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.engineNumber}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.fuelType}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.makeName}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.modelCodeDesc}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.regNo}</td>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.saleDate}</td>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.deliveryDate}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.hmrNo}</td>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.title}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.customerName}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.customerLocation}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.customerDistrict}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.customerState}</td>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.pinCode}</td>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.mobileNo}</td>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.contactno}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.emailId}</td>
                                        <td align="right"  bgcolor="#FFFFFF" style="padding-right:5px;">${extWarr.sumInsured}</td>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.policyType}</td>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.policyTerm}</td>
                                        <td align="right"  bgcolor="#FFFFFF" style="padding-right:5px;">${extWarr.creditAmount}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.bajajPolicyNo}</td>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.bajajPolicyDate}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.status}</td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.mechanicName}</td>
                                        <%if (!userFunctionalities.contains("101")) {%>
                                            <td align="right"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.amtToBajaj}</td>                                            
                                            <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.extWarrantyBookletNo}</td>
                                            <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.typeName}</td>
                                            <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.imdCode}</td>
                                            <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.floatType}</td>
                                            <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">${extWarr.ppId}</td>
                                        <%}%>
                                    </tr>
                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>
                            </table>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${empty expWarrListExport}">
                    <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                        <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.noRecordFound" /></td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
</center>