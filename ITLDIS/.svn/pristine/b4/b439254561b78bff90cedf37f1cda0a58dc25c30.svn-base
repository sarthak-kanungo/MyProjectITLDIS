<%--
    Document   : viewExtWarrPopup
    Created on : 20 Apr, 2017, 03:02:50 PM
    Author     : prashant.kumar
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*,beans.serviceForm" %>
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ page import="java.util.Date" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");      
%>

<link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>
<c:set var="today" value="<%=new Date()%>"/>

<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>

<style>td{line-height:20px;}</style>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>ITLDIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
        
    </head>
    <body>       
        <br/>        
        <center>
            <div class="content_right1">
                <div class="con_slidediv2" >                   
                    <center>
                        <div class="content_right1">
                            <div class="con_slidediv1" style="position: relative;width: 100%">
                                <table id="data" width=98% border=0 cellspacing=1 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                                    <tr height=25 bgcolor="#FFFFFF">
                                        <td align= center class="headingSpas" style="padding-left: 5px;padding-right: 5px;">
                                            <table width=100% border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>                                                
                                                <tr align="left" bgcolor="#FFFFFF" >
                                                    <td colspan="6" align="center">
                                                        <table width=100% border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                                                            <tr bgcolor="#eeeeee">
                                                                <td>
                                                                    <font class="headingSpas"><b><bean:message key="label.common.viewExtWarranty" /></b></font>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr bgcolor="#FFFFFF" >
                                                    <td class="headingSpas" nowrap align="right" width="132px" style="padding-right: 5px;">
                                                        <bean:message key="label.common.itlEwRef"/>&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="right" width="132px" style="padding-right: 5px;">${serviceForm.ewLoaderId}</td>
                                                    <td class="headingSpas" nowrap align="right" width="132px" style="padding-right: 5px;">
                                                        <bean:message key="label.common.dealerName"/>&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.dealerCode} [${serviceForm.dealerName}]</td>
                                                    <td class="headingSpas" align="right" nowrap>
                                                        <bean:message key="label.common.dateSaleOfCertificate"/>&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.invoiceDate}</td>
                                                </tr>                                                
                                                <tr bgcolor="#FFFFFF">
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.catalogue.chassisNo" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.chassisNo}</td>
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.catalogue.engineNo" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.engineNumber}</td>
                                                    <td class="headingSpas" nowrap align="right">
                                                         <bean:message key="label.common.fuelType"/>&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.fuelType}</td>
                                                </tr>
                                                <tr  bgcolor="#FFFFFF" >
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.common.makeName" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.makeName}</td>
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.common.modelName" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.modelCodeDesc}</td>
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.common.Registrationno" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.regNo}</td>
                                                </tr>
                                                <tr  bgcolor="#FFFFFF" >
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.common.dateSaleOfVehicleEqui" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left"> ${serviceForm.deliveryDate}</td>
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.warranty.dateofDelivery"/>&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.deliveryDate}</td>
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.common.hrm" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.hmrNo}</td>
                                                </tr>
                                                <tr bgcolor="#FFFFFF" >
                                                    <td class="headingSpas" width="15%" nowrap align="right">
                                                        <bean:message key="label.common.title" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.title}</td>
                                                    <td class="headingSpas" width="15%" nowrap align="right">
                                                        <bean:message key="label.common.nameOfCust" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.customerName}</td>
                                                    <td class="headingSpas" nowrap align="right" width="15%">
                                                        <bean:message key="label.delivery.Address" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.customerLocation}</td>
                                                </tr>
                                                <tr  bgcolor="#FFFFFF">
                                                    <td class="headingSpas" nowrap align="right" width="10%">
                                                        <bean:message key="label.common.city" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.customerDistrict}</td>
                                                    <td class="headingSpas" nowrap align="right" width="15%">
                                                        <bean:message key="label.common.state" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.customerState}</td>
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.common.pincode" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.pinCode}</td>
                                                </tr>
                                                <tr bgcolor="#FFFFFF">
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.common.mobilephone" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.mobileNo}</td>
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.common.landline" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.contactno}</td>
                                                    <td class="headingSpas" nowrap align="right">
                                                         <bean:message key="label.common.email" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.emailId}</td>
                                                </tr >
                                                <tr  bgcolor="#FFFFFF">
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.common.sumInsured"/>&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.sumInsured}</td>
                                                    <td class="headingSpas" width="15%" nowrap align="right">
                                                        <bean:message key="label.common.policyType" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.policyType}</td>
                                                    <td class="headingSpas" nowrap align="right"  >
                                                        <bean:message key="label.common.premiumAmount" />&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.creditAmount}</td>
                                                </tr>
                                                <tr bgcolor="#FFFFFF">
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.common.bajajPolicyNo"/>&nbsp;&nbsp;: 
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.bajajPolicyNo}</td>
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.common.bajajPolicyDate"/>&nbsp;&nbsp;:
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.bajajPolicyDate}</td>
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.common.status"/>&nbsp;&nbsp;:
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.status}</td>
                                                </tr>
                                                <tr bgcolor="#FFFFFF">
                                                    <td class="headingSpas" nowrap align="right">
                                                        <bean:message key="label.warranty.machanicname"/>&nbsp;&nbsp;:
                                                    </td>
                                                    <td class="headingSpas" nowrap align="left">${serviceForm.mechanicName}</td>
                                                    <td class="headingSpas" nowrap align="left" colspan="4"></td>

                                                </tr>
                                            </table>                                      
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </center>
                </div>
            </div>
        </center>
    </body>
</html>