<%-- 
    Document   : searchCustomer
    Created on : 8 Feb, 2016, 11:28:59 AM
    Author     : yogasmita.patel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Customer</title>
        <script src="<%=cntxpath%>/js/intermediate.js"></script>
        <script type="text/javascript">
            function openPaymentScreen(customerId,customerName,customerCode,customerLocation,paymentDue,availableCreditLimit){
                var url="<%=cntxpath%>/manageCustomerAction.do?option=initReceivePayment&customerId="+customerId+"&customerName="+customerName+"&customerCode="+customerCode+"&customerLocation="+customerLocation+"&paymentDue="+paymentDue+"&availableCreditLimit="+availableCreditLimit;
                var left = (screen.width/2)-(700/2);
                var top = (screen.height/2)-(500/2);
                window.open(url, "Receive", 'directories=no,height=450,width=650,resizable=no,scrollbars=no,location=no ,status=no,titlebar=no,toolbar=no, addressbar=no, top='+top+', left='+left+"'");
            }
            
            function openHistoryScreen(customerId){
                var url="<%=cntxpath%>/manageCustomerAction.do?option=showPaymentHistory&customerId="+customerId;                
                var left = (screen.width/2)-(800/2);
                var top = (screen.height/2)-(500/2);
                window.open(url, "History", 'directories=no,height=450,width=850,resizable=no,scrollbars=no,location=no ,status=no,titlebar=no,toolbar=no, addressbar=no, top='+top+', left='+left+"'");
            }
            $(document).ready(function(){
                $('.counterSale').click(function(){
                    var url = "inventoryAction.do?option=counterSale&customerId="+Base64.encode($(this).text().trim())+"&custcategoryID="+ Base64.encode($(this).attr('data-custCategory').trim())+"&flag="+Base64.encode("cSale");
                    $(this).attr('href',url);
                });
            });
        </script>
    </head>
    <body>
        <div class="contentmain1">
            <div class="breadcrumb_container">
                <ul>
                    <li class="breadcrumb_link"><a href = '<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
                    <li class="breadcrumb_link">MANAGE CUSTOMER</li>
                </ul>
            </div>
            <br/>

            <div class="message" id="msg_saveFAILED">
                <c:if test="${not empty message}">
                    ${message}
                </c:if>
            </div>
            <div class="content_right1">
                <div class="con_slidediv1" style="position: relative;width: 100%">
                    <h1>MANAGE CUSTOMER </h1>
                    <table align="center" cellspacing=1 cellpadding=5 border=0 width=100% >
                        <tr bgcolor="#FFFFFF">
                            <td align="center" class="headingSpas">
                                <html:form action="manageCustomerAction.do?option=initCustomerMasters" method="post" styleId="custForm" >
                                    <table width=100%  border=0 cellspacing=0 cellpadding=0>
                                        <tr bgcolor="#FFFFFF">
                                            <td  width="10%" class="headingSpas" nowrap align="right" style="padding-left: 3px;">Cust Code</td>
                                            <td style="padding-left:2px" width="5%" align="left">
                                                <html:text property="searchCode" styleClass="headingSpas" maxlength="15" onblur="this.value=TrimAll(this.value);"/>
                                            </td>
                                            <td  width="10%" class="headingSpas" nowrap align="right" style="padding-left: 3px;">Cust Name</td>
                                            <td style="padding-left:2px" width="5%" align="left">
                                                <html:text property="searchName" styleClass="headingSpas" maxlength="15" onblur="this.value=TrimAll(this.value);"/>
                                            </td>
                                            <td  width="10%" class="headingSpas" nowrap align="right" style="padding-left: 3px;">Location</td>
                                            <td style="padding-left:2px" width="5%" align="left">
                                                <html:text property="searchLoc" styleClass="headingSpas" maxlength="15" onblur="this.value=TrimAll(this.value);"/>
                                            </td>
                                            <td  width="10%" class="headingSpas" nowrap align="right" style="padding-left: 3px;">Category</td>
                                            <td style="padding-left:2px" width="5%" align="left">
                                                <html:select property="custCategory" styleId="Customer Category">
                                                    <html:option value="">Select</html:option>
                                                    <html:optionsCollection property="customerCatgryList" name="customerForm" label="label" value="value"/>
                                                </html:select>
                                            </td>
                                            <td  width="8%" class="headingSpas" nowrap align="right" style="padding-left: 2px;">Contact No.</td>
                                            <td style="padding-left:2px" width="3%" align="left">
                                                <html:text property="searchCont" styleClass="headingSpas" maxlength="15" onblur="this.value=TrimAll(this.value);"/>
                                            </td>
                                            <td style="padding-left:5px" width="15%" align="left" >
                                                <html:submit property="Go" value="Search" styleId="Go" styleClass="headingSpas"/>
                                            </td>
                                            <%if (userFunctionalities.contains("540")) {%>
                                            <td style="padding-right:5px" width="15%" align="right" >
                                                <B><a href='<%=cntxpath%>/manageCustomerAction.do?option=addCustomer'>ADD NEW CUSTOMER</a></B>
                                            </td>
                                            <%}%>
                                        </tr>
                                    </table>
                                </html:form>
                            </td>
                        </tr>
                        <c:if test="${empty customerList}">
                            <tr bgcolor="#FFFFFF">
                                <td valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                    Customer not Found
                                </td>
                            </tr>
                        </c:if>
                    </table>
                    <div style="overflow: auto">
                        <table align="center" cellspacing=1 cellpadding=5 border=0 width=100% >
                            <c:if test="${!empty customerList}">

                                <tr bgcolor="#FFFFFF">
                                    <td  align= center class="headingSpas">
                                        <pg:pager url="manageCustomerAction.do" maxIndexPages="10" maxPageItems="10">
                                            <pg:param name="option" value="initCustomerMasters"/>
                                            <pg:param name="searchName" value="${customerForm.searchName}"/>
                                            <pg:param name="searchLoc" value="${customerForm.searchLoc}"/>
                                            <pg:param name="custCategory" value="${customerForm.custCategory}"/>
                                            <pg:param name="searchCont" value="${customerForm.searchCont}"/>
                                            <pg:param name="searchCode" value="${customerForm.searchCode}"/>
                                            <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bgcolor=#cccccc>
                                                <tr bgcolor="#eeeeee" height="25px">
                                                    <td   align="center" width="5%"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >S.No</td>
                                                    <td   align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >Customer Name</td>
                                                    <td   align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >Customer Code</td>
                                                    <td   align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >Customer Category</td>
                                                    <td   align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >Location</td>
                                                    <td   align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >Contact No.</td>
                                                    <td   align="right" width="8%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >Credit Limit</td>
                                                    <td   align="right" width="8%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >Avl Credit Limit</td>
                                                    <td   align="right" width="8%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >Payment Due</td>
                                                    <td   align="right" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >Add. Discount %</td>
                                                    <td   align="right" width="8%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >Sonalika ID</td>
                                                    <%if (userFunctionalities.contains("540") || userFunctionalities.contains("541") || userFunctionalities.contains("542") || userFunctionalities.contains("806")) {%>
                                                    <td   align="center" width="15%"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >Action</td>
                                                    <%}%>
                                                </tr>
                                                <c:set var="sno" value="1"></c:set>
                                                <logic:iterate id="customer" name="customerList">
                                                    <pg:item>
                                                        <tr bgcolor="#FFFFFF" >
                                                            <td   align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >${sno}</td>
                                                            <td   align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >${customer.customerName}</td>
                                                            <td   align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >${customer.customerCode}</td>
                                                            <td   align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >${customer.custCategory}</td>
                                                            <td   align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >${customer.customerLocation}</td>
                                                            <td   align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >${customer.contactMobile}</td>
                                                            <td   align="right" width="8%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" ><fmt:parseNumber type="number" integerOnly="true"   value="${customer.creditLimit}"/></td>
                                                            <td   align="right" width="8%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" ><fmt:parseNumber type="number" integerOnly="true"   value="${customer.availableCreditLimit}"/></td>
                                                            <td   align="right" width="8%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" ><fmt:parseNumber type="number" integerOnly="true"   value="${customer.paymentDue}"/></td>
                                                            <td   align="right" width="10%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" ><fmt:parseNumber type="number" integerOnly="true"   value="${customer.discountPercentage}"/></td>
                                                            <td   align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >${customer.sonalikaId}</td>
                                                            <td align="center" width="5%" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                                <%if (userFunctionalities.contains("540")) {%>
                                                                <span id ="imageButton${sno}" >
                                                                    <a href='<%=cntxpath%>/manageCustomerAction.do?option=editCustomer&customerId=${customer.customerId}&custcategoryID=${customer.custCategoryID}&custCategory=${customer.custCategory}'><img src="${pageContext.request.contextPath}/images/edit.gif" title="Edit"/></a>
                                                                </span>
                                                                <%}
                                                                            if (userFunctionalities.contains("541")) {%>
                                                                <span id ="imageButton${sno}" >
                                                                    <a href='javascript:void(0);' onclick="openPaymentScreen('${customer.customerId}','${customer.customerName}','${customer.customerCode}','${customer.customerLocation}','${customer.paymentDue}','${customer.availableCreditLimit}');"><img src="${pageContext.request.contextPath}/images/recieve.jpg"  title="Add Payment" id="Add Payment"/></a>
                                                                </span>
                                                                <%}
                                                                            if (userFunctionalities.contains("542")) {%>
                                                                <span id ="imageButton${sno}" >
                                                                    <a href='javascript:void(0);' onclick="openHistoryScreen('${customer.customerId}');"><img src="${pageContext.request.contextPath}/images/history1.jpg"  title="History"/></a>
                                                                </span>
                                                                <%}
                                                                            if (userFunctionalities.contains("806")) {%>
                                                                <span id ="imageButton${sno}" >
                                                                    <%-- <a href="#" class="counterSale"  data-custCategory="${custcategoryID}" ><img src="${pageContext.request.contextPath}/images/counterSale.png"  title="Counter Sale"/></a>--%>
                                                                    <a href="<%=cntxpath%>/inventoryAction.do?option=counterSale&customerId=${customer.customerId}&custcategoryID=${customer.custCategoryID}" ><img src="${pageContext.request.contextPath}/images/counterSale.png"  title="Counter Sale"/></a>
                                                                </span>
                                                                <%}%>
                                                            </td>
                                                            <%--<td   align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;" >
                                                                <c:if test="${customer.isActive eq 'Y'}">Active</c:if>
                                                                <c:if test="${customer.isActive ne 'Y'}">InActive</c:if>
                                                            </td>--%>
                                                        </tr>
                                                    </pg:item>
                                                    <c:set var="sno" value="${sno + 1}"></c:set>
                                                </logic:iterate>
                                                <tr bgcolor="#FFFFFF">
                                                    <%if (userFunctionalities.contains("540") || userFunctionalities.contains("541") || userFunctionalities.contains("542") || userFunctionalities.contains("806")) {%>
                                                    <td colspan="12" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                        <%} else {%>
                                                    <td colspan="11" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                        <%}%>
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
                                    </td>
                                </tr>                                
                            </c:if>
                        </table>
                    </div>
                </div>                
            </div><br>
            <span style="color:red; padding-left:20px; font-size: 11px;"> <b>* Note:- Only first 50 Records are displayed.</b></span>
        </div>
    </body>
</html>
