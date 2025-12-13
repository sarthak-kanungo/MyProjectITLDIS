<%-- 
    Document   : receivePayment
    Created on : 10 Feb, 2016, 11:11:31 AM
    Author     : yogasmita.patel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.Date" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<c:set var="today" value="<%=new Date()%>"/>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" language="JavaScript">
            $(function() {
                $( ".datepicker" ).datepicker();
            });


            function validateCustForm(){

                var today = new Date();
                var dd = today.getDate();
                var mm = today.getMonth()+1; //January is 0!
                var yyyy = today.getFullYear();
                if(dd<10) {
                    dd='0'+dd
                }
                if(mm<10) {
                    mm='0'+mm
                }
                today = dd+'/'+mm+'/'+yyyy;                

                var elementArr = new Array('Payment Date','Amount','Payment Mode','Remarks');
                var strObject = null;
                for (var i = 0; i < elementArr.length; i++)
                {
                    strObject = document.getElementById(elementArr[i]);
                    if (strObject){
                        if(elementArr[i] == 'Amount'){ //elementArr[i] == 'Payment Date' || 
                            if (!isNotEmpty(strObject, elementArr[i])){
                                strObject.focus();
                                return false;
                            }
                        }
                        if(elementArr[i] == 'Payment Mode'){
                            if (!isChosen1(strObject, elementArr[i])){
                                strObject.focus();
                                return false;
                            }
                        }
                        if(elementArr[i] == 'Amount'){
                            if (!checkNumberPayment(strObject)){
                                strObject.value='';
                                strObject.focus();
                                return false;
                            }
                        }
                        if(elementArr[i] == 'Remarks'){
                            if (strObject.value !='' && !isProperAll(strObject.value)){
                                alert("Special characters are not allowed in "+elementArr[i]+" field");
                                strObject.focus();
                                return false;
                            }
                        }
                        if(elementArr[i] == 'Payment Date'){
                            if(strObject.value > today){
                                alert(elementArr[i]+ " must be less then or equal to Current Date.");
                                strObject.focus();
                                return false;
                            }
                        }
                    }
                }
            }

            function caMaxLength(ca, MaxLen)
            {
                if(ca.value.length > MaxLen){
                    alert("Remarks can not be more than "+MaxLen+" characters.");
                    ca.value=ca.value.substring(0,250);
                    ca.focus();
                    return false;
                }else{
                    return (ca.value.length <= MaxLen);
                }
            }
        </script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="contentmain1">
            <div class="breadcrumb_container">
            </div>
            <br/>
            <div class="content_right1">
                <div class="con_slidediv1" style="position: relative;width: 100%">
                    <h1 style="text-align:left"> <font class="headingSpas">Add Payment against Customer : ${customerForm.customerName} [${customerForm.customerCode}] [${customerForm.customerLocation}]</font></h1>        <%--ADD PAYMENT--%>
                    <table align="center" cellspacing=1 cellpadding=5 border=0 width=100%>
                        <tr bgcolor="#FFFFFF">
                            <td align="center" class="headingSpas">
                                <html:form action="manageCustomerAction.do?option=saveReceivedPayment" styleId="paymentForm" method="post">
                                    <html:hidden property="customerId" styleId="customerId" value="${customerForm.customerId}"/>
                                    <table align="center" cellspacing=1 cellpadding=5 border=0 width=100%>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas">Payment Date<span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas">
                                                <fmt:formatDate type="date" value="${today}" pattern="dd/MM/yyyy" var="fmtToDate"/>
                                                <html:text styleClass="datepicker" property="paymentDate" styleId="Payment Date" readonly="true" value="${fmtToDate}"/>
                                            </td>
                                            <td align="right" style="color:red"  class="headingSpas"><bean:message key="label.spare.amountDue" /> :<br><bean:message key="label.spare.availableCreditLimit" /> :</td>
                                            <td align="left" style="color:red"  class="headingSpas">
                                               <fmt:parseNumber type="number" integerOnly="true"   value="${customerForm.paymentDue}"/> <br><fmt:parseNumber type="number" integerOnly="true"   value="${customerForm.availableCreditLimit}"/>
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas">Amount<span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas" colspan="3">
                                                <html:text property="amount" styleId="Amount" maxlength="20"/><span class="mandatory">&nbsp;(Put '-', If Payment is given to Customer)</span>
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas">Payment Mode<span class="mandatory">*</span></td>
                                            <td style="padding-left:0px" align="left" class="headingSpas" colspan="3">
                                          &nbsp;<html:select property="paymentMode" styleId="Payment Mode">
                                                    <html:option value="Select Here">--Select Here--</html:option>
                                                    <html:optionsCollection  property="paymentModeList" name="customerForm" label="label" value="value"/>
                                                </html:select>
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas" style="padding-right: 8px">Remarks</td>
                                            <td align="left" class="headingSpas" colspan="3" style="padding-left:10px">
                                                <html:textarea property="remarks" styleId="Remarks" cols="5" rows="2" onblur="return caMaxLength(this, 250);" style="width: 300px;"/>
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="center" colspan="4" class="headingSpas">
                                                <html:submit property="submit" styleId="makePayment" value="Submit" onclick="return validateCustForm();"/>
                                            </td>
                                        </tr>
                                    </table>
                                </html:form>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

        </div>
    </body>
</html>
