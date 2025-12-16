<%-- 
    Document   : paymentHistory
    Created on : 10 Feb, 2016, 4:39:37 PM
    Author     : yogasmita.patel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.Date" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="today" value="<%=new Date()%>"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment History</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/css/login.css" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
        <script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css" media="screen" />
        <link href="${pageContext.request.contextPath}/css/dynCalendar.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Master_290414.css" type="text/css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css"  type="text/css"/>
         <script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>
        <script type="text/javascript" language="JavaScript">
            $(function() {                              
                $( ".datepicker" ).datepicker();
            });
        </script>        
    </head>
    <body>
        <div class="contentmain1">
            <%--<div class="breadcrumb_container">
                <ul>
                    <li class="breadcrumb_link"><a href = '${pageContext.request.contextPath}/masterAction.do?option=initOptions'>MASTERS</a></li>
                    <li class="breadcrumb_link"><a href = '${pageContext.request.contextPath}/manageCustomerAction.do?option=initCustomerMasters'>MANAGE CUSTOMER</a></li>
                    <li class="breadcrumb_link">PAYMENT HISTORY</li>
                </ul>
            </div>
            <br/>--%>

            <div class="message" id="msg_saveFAILED">
                <c:if test="${not empty message}">
                    ${message}
                </c:if>
            </div>
            <div class="content_right1">
                <div class="con_slidediv1" style="position: relative;width: 100%;">
                    <h1>PAYMENT HISTORY</h1>
                    <html:form action="manageCustomerAction.do?option=showPaymentHistory" method="post" styleId="custForm" >
                        <html:hidden property="customerId" value="${customerForm.customerId}"/>
                        <table align="center" cellspacing=1 cellpadding=5 border=0 width=100%>
                            <tr>
                                <td   align="right" width="15%"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;;" > Customer Name :</td>
                                <td   align="left" width="45%" > ${customerForm.customerName} [${customerForm.customerCode}] [${customerForm.customerLocation}]</td>
                                <td align="left" width="40%">
                                    <html:text property="fromdate" styleClass="datepicker" styleId="From Date" style="width:60px !important " value="${customerForm.fromdate}"  readonly="readonly"/>      <%--${inventoryForm.fromdate}--%>
                                    &nbsp; to &nbsp;
                                    <fmt:formatDate type="date" value="${today}" pattern="dd/MM/yyyy" var="fmtToDate"/>
                                    <html:text property="todate" styleClass="datepicker" styleId="To Date" style="width:60px !important " value="${customerForm.todate}"   readonly="readonly"/>           <%--${inventoryForm.todate}--%>

                                    <span style="padding-left: 40px"><html:submit property="search" value="Search" styleId="search"/></span>
                                </td>
                            </tr>
                        </table>
                    </html:form>
                    <table align="center" cellspacing=1 cellpadding=5 border=0 width=100%>
                        <c:if test="${empty paymentList}">
                            <tr bgcolor="#FFFFFF">
                                <td valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                    No record Found
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty paymentList}">
                            <tr bgcolor="#FFFFFF">
                                <td>
                                    <pg:pager url="manageCustomerAction.do" maxIndexPages="10" maxPageItems="10">
                                        <pg:param name="option" value="showPaymentHistory"/>
                                        <pg:param name="customerId" value="${customerForm.customerId}"/>
                                        <table align="center" cellspacing=1 cellpadding=5 border=0 width=100% bgcolor=#cccccc >
                                            <tr bgcolor="#eeeeee">
                                                <td   align="center" width="10%"  class="headingSpas" align="center" ><b>S. No.</b></td>
                                                <%--<td   align="left" width="20%"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;" >Customer Name</td>
                                                <td   align="left" width="20%"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;" >Customer Code</td>--%>
                                                <td   align="center" width="25%"  class="headingSpas" align="center" ><b>Payment Date</b></td>
                                                <td   align="right" width="15%" class="headingSpas" align="center" style="padding-right:3px;"><b>Amount</b></td>
                                                <td   align="center" width="25%"  class="headingSpas" align="left" ><b>Payment Mode</b></td>
                                                <td   align="left" width="25%"  class="headingSpas" align="left" style="padding-left:3px;" ><b>Remarks</b></td>
                                            </tr>
                                            <c:set var="sno" value="1"/>
                                            <logic:iterate id="paymentList" name="paymentList">
                                                <pg:item>
                                                    <tr bgcolor="#FFFFFF">
                                                        <td   align="center" width="10%" class="headingSpas" align="center" >${sno}</td>
                                                        <%--<td   align="left" width="20%"  style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;" >${paymentList.customerName}</td>
                                                        <td   align="left" width="20%"  style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;" >${paymentList.customerCode}</td>--%>
                                                        <td   align="center" width="25%" class="headingSpas" align="center" >${paymentList.paymentDate}</td>
                                                        <td   align="right" width="15%" class="headingSpas" align="right" style="padding-right:3px;" >${paymentList.amount}</td>
                                                        <td   align="center" width="25%" class="headingSpas" align="left" >${paymentList.paymentMode}</td>
                                                        <td   align="left" width="25%" class="headingSpas"  align="left" style="padding-left:3px;">${paymentList.remarks}</td>
                                                    </tr>
                                                </pg:item>
                                                <c:set var="sno" value="${sno + 1}"/>
                                            </logic:iterate>
                                            <tr bgcolor="#FFFFFF">
                                                <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                    <pg:index>
                                                        <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle"/></a></pg:first>&nbsp;
                                                        <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle"/></a></pg:prev>&nbsp;
                                                        <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                        <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle"/></a></pg:next>&nbsp;
                                                        <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle"/></a></pg:last>
                                                        </pg:index>
                                                </td>
                                            </tr>
                                        </table>
                                    </pg:pager>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
