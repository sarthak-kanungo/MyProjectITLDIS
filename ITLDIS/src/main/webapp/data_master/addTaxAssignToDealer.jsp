<%--
    Document   : addTaxAssignToDealer
    Created on : 15 April, 2016, 1:31:51 PM
    Author     : avinash.pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Tax Assigned To Dealer</title>
        <link rel="stylesheet" href="layout/css/login.css" type="text/css" />

        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css" media="screen" />
        <link href="${pageContext.request.contextPath}/css/dynCalendar.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ui.dropdownchecklist.themeroller.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/dhtmlgoodies_calendar.js"></script>
        <script language="JavaScript" src='${pageContext.request.contextPath}/js/browserSniffer.js'></script>
        <script language="JavaScript" src='${pageContext.request.contextPath}/js/validation.js'></script>
        <script language="JavaScript" src='${pageContext.request.contextPath}/js/dynCalendar.js'></script>
        <script language="JavaScript" src='${pageContext.request.contextPath}/js/alert_message.js'></script>

       
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Master_290414.css" type="text/css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css"  type="text/css"/>

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>



        <script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script type="text/javascript"src="${pageContext.request.contextPath}/js/Master_290414.js"></script>
        
        <script type="text/javascript" type="text/javascript" >

            $(function() {
                $( ".datepicker" ).datepicker();
            });
        </script>
        <c:set var="today" value="<%=new Date()%>"/>
        <script type="text/javascript" language="javascript">

            function expValidated(str) {
                var regularExpression = /^[ A-Za-z0-9_/-].*$/;
                var valid = regularExpression.test(str);
                return valid;
            }
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

                var elementArr = new Array('Commodity Code','State Name','Effective Date','Tax Category');
                var strObject = null;
                for (var i = 0; i < elementArr.length; i++)
                {
                    strObject = document.getElementById(elementArr[i]);
                    if (strObject){
                        if(elementArr[i] == 'Commodity Code' || elementArr[i] == 'State Name' || elementArr[i] == 'Tax Category'){
                            if (!isChosenCust(strObject, elementArr[i])){
                                strObject.focus();
                                return false;
                            }
                        }
                        if(elementArr[i] == 'Effective Date'){
                            if(strObject.value > today){
                                alert(elementArr[i]+ " must be less then or equal to Current Date.");
                                strObject.focus();
                                return false;
                            }
                        }
                    }
                }
            }
        </script>
    </head>
    <body>
        <div class="contentmain1">
            <%-- <div class="breadcrumb_container">
                 <ul>
                     <li class="breadcrumb_link"><a href = '<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
                     <li class="breadcrumb_link"><a href = '<%=cntxpath%>/masterAction.do?option=initTaxAssignToDealerMasters'><label class="hText"><bean:message key="label.common.viewTaxAssignedToDealer"/></label></a></li>
                     <li class="breadcrumb_link"><label class="hText"><bean:message key="label.common.viewTaxAssignedToDealer"/></label></li>

                </ul>
            </div>
            <br/>
            --%>
            <div class="message" id="msg_saveFAILED">${message}</div>
            <div class="content_right1">
                <div class="con_slidediv1" style="position: relative;width: 100%">
                    <h1><label class="hText"><bean:message key="label.common.newTaxAssignedToDealer"/></label></h1>
                    <table align="center" cellspacing=1 cellpadding=5 border=0 width=80% >
                        <tr bgcolor="#FFFFFF">
                            <td align="center" class="headingSpas">
                                <html:form action="masterAction.do?option=saveTaxAssignedToDealer" styleId="masterForm">
                                    <table cellspacing=1 cellpadding=5 border=0 width=100% >
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas" style="padding-left: 20px;width: 41%">Select <bean:message key="label.common.commodityCode"/> <span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas"  >
                                                <html:select property="commodityCodeTax" styleId="Commodity Code" styleClass="headingSpas"  style="width:150px!important">
                                                    <html:option value=""><bean:message key="label.common.selectoption"/></html:option>
                                                    <html:optionsCollection property="commodityCodeList" name="masterForm" label="label" value="value"/>
                                                </html:select>
                                            </td>
                                        </tr>
                                        <tr  bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas" style="padding-left: 25px">Select <bean:message key="label.common.stateName"/> <span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas">
                                                <html:select property="stateName" styleId="State Name" styleClass="headingSpas"  style="width:150px!important">
                                                    <html:option value=""><bean:message key="label.common.selectoption"/></html:option>
                                                    <html:optionsCollection property="stateNameList" name="masterForm" label="label" value="value"/>
                                                </html:select>
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas" style="padding-left: 25px"><bean:message key="label.common.effectiveDate"/> <span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas" style="padding-left: 10px">
                                                <fmt:formatDate type="date" value="${today}" pattern="dd/MM/yyyy" var="fmtToDate"/>
                                                <html:text property="effectiveDate" styleId="Effective Date"  styleClass="datepicker" readonly="true" value="${fmtToDate}"/>
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas" style="padding-left: 25px">Select <bean:message key="label.master.taxCategory"/> <span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas"><html:select property="taxCategory" styleId="Tax Category" styleClass="headingSpas"  style="width:300px!important">
                                                    <html:option value=""><bean:message key="label.common.selectoption"/></html:option>
                                                    <html:optionsCollection property="taxCategoryList" name="masterForm" label="label" value="value"/>
                                                </html:select>
                                            </td>
                                        </tr>

                                        <tr bgcolor="#FFFFFF">
                                            <td align="center" class="headingSpas" colspan="4">
                                                <html:submit property="submitBtn" styleId="submitBtn" styleClass="headingSpas" value="Submit" onclick=" return validateCustForm();"/>
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
