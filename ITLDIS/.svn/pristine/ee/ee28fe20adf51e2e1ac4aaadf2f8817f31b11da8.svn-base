<%-- 
    Document   : addExtendedWarrenty
    Created on : Apr 14, 2017, 11:23:56 AM
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
<%@ page import="java.util.Date,viewEcat.comEcat.ConnectionHolder,viewEcat.comEcat.PageTemplate" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();

            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);

            String sumInsured = object_pageTemplate.sumInsured;
%>

<link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/calendar.css" type="text/css" rel="stylesheet" media="screen" />
<link href="${pageContext.request.contextPath}/css/dynCalendar.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>
<script src="js/intermediate.js"></script>
<c:set var="today" value="<%=new Date()%>"/>
<script type="text/javascript" language="javascript"> 
    var contextPath = '${pageContext.request.contextPath}';
</script>

<script type="text/javascript" type="text/javascript" >
    $(function() {
        $(".datepicker").datepicker();
    });
</script>
<script>

    function isSpecialchar(obj){
        var regexp=/^[a-zA-Z\s]+$/;
        if (!regexp.test(obj.value)) {
            return false;
        }else{
            return true;
        }
    }

    function isSpecialwithNoSpace(obj){
        var regexp=/^[a-zA-Z0-9]+$/;
        if (!regexp.test(obj.value)) {
            return false;
        }else{
            return true;
        }
    }

    function submitForm() {

        var title= document.getElementById("title").value;
        var policyType= document.getElementById("Policy Type").value;
        if(document.getElementById("Fuel Type").value == ""){
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Please select value in Fuel Type field";
            document.getElementById("Fuel Type").value='';
            document.getElementById("Fuel Type").focus();
            return false;
        }
                      
        var elementArr = new Array('Make Name','Model Name','Registration No' ,'HMR','Customer Name'
        ,'Address','District','State','Pincode','Mobile','Contact No','Email','Engine No','SUM Insured');

        var strObject = null;
        for (var i = 0; i < elementArr.length; i++) {
            strObject = document.getElementById(elementArr[i]);
            if (strObject){
                if(elementArr[i] == 'Model Name' ||  elementArr[i] == 'Make Name' || elementArr[i] == 'Engine No' 
                    || elementArr[i] == 'HMR' || elementArr[i] == 'Customer Name' || elementArr[i] == 'Address'
                    || elementArr[i] == 'District' || elementArr[i] == 'State' || elementArr[i] == 'Pincode'
                    || elementArr[i] == 'Mobile' || elementArr[i] == 'SUM Insured'){

                    if (!isNotEmptyCust(strObject, elementArr[i])){
                        strObject.focus();
                        return false;
                    }
                }
                if(elementArr[i] == 'Make Name' || elementArr[i] == 'Model Name' || elementArr[i] == 'Registration No'){
                    if(strObject.value != '' && !isProperSerialCust(strObject.value)){
                        document.getElementById("msg_saveFAILED").style.display = "";
                        document.getElementById("msg_saveFAILED").innerHTML="Only alpha-numeric and special characters (-, /, _) are allowed in "+elementArr[i]+" field";
                        strObject.focus();
                        return false;
                    }
                }                
                if(elementArr[i] == 'HMR'){
                    if (isNaN(strObject.value)){
                        document.getElementById("msg_saveFAILED").style.display = "";
                        document.getElementById("msg_saveFAILED").innerHTML="Only numbers are allowed in " + elementArr[i] + " field";
                        strObject.focus();
                        return false;
                    }
                }
                if(title == ""){
                    document.getElementById("msg_saveFAILED").style.display = "";
                    document.getElementById("msg_saveFAILED").innerHTML="Please select value in Title field";
                    title='';
                    document.getElementById("title").focus();
                    return false;
                }
                var regName =/^[a-zA-Z0-9\s,'-_]*$/;
                if(elementArr[i] == 'Customer Name' || elementArr[i] == 'District' || elementArr[i] == 'State'){
                    if (!regName.test(strObject.value)){
                        document.getElementById("msg_saveFAILED").style.display = "";
                        document.getElementById("msg_saveFAILED").innerHTML="Special characters are not allowed in " + elementArr[i] + " field";
                        strObject.focus();
                        return false;
                    }
                }                
                var regAddress =/^[a-zA-Z0-9\s,'-_]*$/;
                if(elementArr[i] == 'Address'){
                    if (!regAddress.test(strObject.value)){
                        document.getElementById("msg_saveFAILED").style.display = "";
                        document.getElementById("msg_saveFAILED").innerHTML="Only special characters (- , / ' _ .) are allowed in " + elementArr[i] + " field";
                        strObject.focus();
                        return false;
                    }
                }
                
                var regPin= /^[1-9][0-9]{5}$/;
                if(elementArr[i] == 'Pincode'){                    
                    if (!regPin.test(strObject.value)){
                        document.getElementById("msg_saveFAILED").style.display = "";
                        document.getElementById("msg_saveFAILED").innerHTML="Only numbers with length of 6 are allowed in " + elementArr[i] + " field";
                        strObject.focus();
                        return false;
                    }
                }                
                var regMobile= /^[123456789]\d{9}$/;
                if(elementArr[i] == 'Mobile'){                    
                    if (!regMobile.test(strObject.value)){
                        document.getElementById("msg_saveFAILED").style.display = "";
                        document.getElementById("msg_saveFAILED").innerHTML="Length of the number must be exactly 10 digits in "+elementArr[i]+" field";
                        strObject.value='';
                        strObject.focus();
                        return false;
                    }
                }
                
                // var regLandline= /^\(0\d{1,2}\)\d{3}-\d{4}$/;
                if(elementArr[i] == 'Contact No'){
                    if (strObject.value !='' && isNaN(strObject.value)){
                        document.getElementById("msg_saveFAILED").style.display = "";
                        document.getElementById("msg_saveFAILED").innerHTML="Only numbers are allows in Landline field";
                        strObject.focus();
                        return false;
                    }
                }
                if(elementArr[i] == 'Email'){
                    if (strObject.value !='' && !isEMailAddr(strObject)){
                        strObject.value='';
                        strObject.focus();
                        return false;
                    }
                }
                
                if(elementArr[i] == 'SUM Insured'){
                    if (isNaN(strObject.value)){
                        document.getElementById("msg_saveFAILED").style.display = "";
                        document.getElementById("msg_saveFAILED").innerHTML="Only numbers are allows in SUM Insured field";
                        strObject.focus();
                        return false;
                    }
                }

                if(elementArr[i] == 'SUM Insured'){
                    if(strObject.value <= <%=sumInsured%>){
                        document.getElementById("msg_saveFAILED").style.display = "";
                        document.getElementById("msg_saveFAILED").innerHTML="Sum Insured should be greater than 3.5 Lakh.";
                        strObject.focus();
                        return false;
                    }
                }
            }
        }
        if(policyType == ""){
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Please select value in Policy Type field";
            policyType='';
            document.getElementById("Policy Type").focus();
            return false;
        }
         if(document.getElementById("Premium Amount")!=undefined && document.getElementById("Premium Amount").value=="0.00"){
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Premium Amount zero are not allowed";
            return false;
        }

        if (confirm('Do you want to Submit.') == true) {
            document.getElementById("btn").disabled = true;
            document.getElementById('form2').submit();
        }
    }
         
    function submitForm1(){

        var vehicleNo = document.getElementById('Vin No').value;
        if(vehicleNo == ''){
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Please Select value in Vehicle No. field";
            document.getElementById('Vin No').focus();
            vehicleNo='';
            return false;
        }
        isVehicleExist();
    }

    function donotsubmit()
    {
        return false;
    }

    function getPremiumAmt()
    {
        var todate=new Date().getTime();
        var policyType= document.getElementById("Policy Type").value;
        var delDate= document.getElementById("Date of Delivery").value;
        var strURL=contextPath +"/serviceProcess.do?option=getPremiumAmt&policyType="+ policyType + "&delDate="+ delDate+"&todate="+todate;
        xmlHttp = GetXmlHttpObject();
        ajaxindicatorstart('loading data.. Please wait..');
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {
                res = trim(xmlHttp.responseText);
                var valArr = res.split('@@');
                if (valArr.length > 0 && res != '')
                {
                    document.getElementById("premDiv").innerHTML = valArr[0];
                    document.getElementById("Premium Amount").value = valArr[0];
                    document.getElementById("Policy Type Id").value = valArr[1];
                }                   
    <%--if(valArr[0] == 0.0){
        alert("Delivery Date should be under 1 year range from Current Date.");
        document.getElementById("Policy Type").value='';
        document.getElementById("Premium Amount").value='';
        document.getElementById("premDiv").innerHTML = '';
        return false;
    }--%>
                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);
            ajaxindicatorstop();
        }

        function isVehicleExist(){
                  
            var vinNo= document.getElementById("Vin No").value;
            var strURL=contextPath +"/serviceProcess.do?option=isVehicleExist&vinNo="+ vinNo ;
            xmlHttp = GetXmlHttpObject();
            ajaxindicatorstart('loading data.. Please wait..');
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    res = trim(xmlHttp.responseText);
                    var valArr = res.split('@@');
                    if(valArr[0] == 'false'){
                        alert('Vehicle No. not Exist in Database');
                        return false;
                    }else{
                        if(valArr[1] == 'false'){
                            alert('Delivery date is more than 2 year from Current date.');
                            return false;
                        }else{
                            isChassisExist();
                    }  
                }
            }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);
            ajaxindicatorstop();
        }

        function isChassisExist(){

            var vinNo= document.getElementById("Vin No").value;
            var strURL=contextPath +"/serviceProcess.do?option=isChassisExist&vinNo="+ vinNo ;
            xmlHttp = GetXmlHttpObject();           
            xmlHttp.onreadystatechange = function() {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete"){
                    res = trim(xmlHttp.responseText);
                    if(res == 'true'){
                        alert('Warranty already done for Vehicle No. : '+ vinNo);
                        return false;
                    }else{
                        document.getElementById('form1').submit();
                    }
                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);           
        }

</script>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>ITLDIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
    </head>
    <body>
        <div class="breadcrumb_container">
            <ul class="hText">
                <li class="breadcrumb_link"><html:link action="/initExtWarranty"><bean:message key="label.common.extWarrantyReg" /></html:link></li>
                <li class="breadcrumb_link"><bean:message key="label.common.addExtWarranty" /></li>
            </ul>
        </div>
        <br/>
        <div class="message" id="msg_saveFAILED">${message}</div>
        <center>
            <div class="content_right1">
                <div class="con_slidediv2" >
                    <h1><bean:message key="label.common.addExtWarranty" /></h1>
                    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" >
                        <tr>
                            <html:form action="serviceProcess.do?option=initAddExtWarranty&srch=viewDiv" method="post" styleId="form1" >
                                <td valign="middle" bgcolor="#FFFFFF" align="left">
                                    <table width=100% border=0 cellspacing=0 cellpadding=3 bgcolor=#cccccc>
                                        <tr bgcolor="#FFFFFF">
                                            <td class="headingSpas" nowrap align="right" width="132px" style="padding-right: 5px;">
                                                <bean:message key="label.catalogue.chassisNo"/></td>
                                            <td width="220px">
                                                <input name="vinNo" type="text" id="Vin No" value="${vinNo}" onblur="this.value=TrimAll(this.value)" style="width: 220px;" maxlength="50"/>
                                            </td>
                                            <td style="padding-left: 20px">                                                 
                                                <input name="go" type="button" value="    Go    " style="float:none;" onClick="submitForm1();"/>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </html:form>
                        </tr>
                        <tr>
                            <c:if test="${flagCheck ne null}">
                            <center>
                                <div class="content_right1">
                                    <div class="con_slidediv1" style="position: relative;width: 100%">                                        
                                        <table id="data" width=100% border=0 cellspacing=1 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                                            <tr height=25 bgcolor="#FFFFFF">
                                                <td colspan="6" align= center class="headingSpas">
                                                    <html:form action="serviceProcess.do?option=addExtWarranty" method="post" styleId="form2" onsubmit="donotsubmit();">
                                                        <table width=100% border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                                                            <tr align="left" bgcolor="#FFFFFF" >
                                                                <td class="headingSpas" colspan="5" align="right">
                                                                    <bean:message key="label.common.dateSaleOfCertificate"/>
                                                                </td>
                                                                <td align="left" colspan="1" style="padding-left: 10px;padding-bottom: 5px;">
                                                                    <fmt:formatDate type="date" value="${today}" pattern="dd-MMM-yyyy" var="fmtToDate"/>${fmtToDate}
                                                                    <html:hidden property="dateOfSaleOfCertificate" styleId="Date of Sale Certificate" value="${fmtToDate}"/>
                                                                </td>
                                                            </tr>
                                                            <tr align="left" bgcolor="#FFFFFF">
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.catalogue.chassisNo" />
                                                                </td>
                                                                <td align="left">
                                                                    &nbsp;&nbsp;${serviceForm.chassisNo}<input type="hidden" name="chassisNo" id="Chassis No" value="${serviceForm.chassisNo}" style="padding-left: 5px;width:150px !important " onblur="this.value=TrimAll(this.value)"/>
                                                                </td>
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.catalogue.engineNo" />
                                                                </td>
                                                                <td align="left">
                                                                    &nbsp;&nbsp;${serviceForm.engineNumber}<input type="hidden" name="engineNumber" id="Engine No" value="${serviceForm.engineNumber}" style="padding-left: 5px;width:150px !important " onblur="this.value=TrimAll(this.value)"/>
                                                                </td>
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.common.fuelType"/>
                                                                </td>
                                                                <td align="left">
                                                                    <select name="fuelType" id="Fuel Type" class="selectnewbox" style="width:155px !important ">
                                                                        <%--<option value="">--Select--</option>--%>
                                                                        <%--<option value="Petrol">Petrol</option>--%>
                                                                        <option value="Diesel" selected>Diesel</option>
                                                                    </select>                                                                   
                                                                </td>
                                                            </tr>
                                                            <tr bgcolor="#FFFFFF" >
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.common.makeName" />
                                                                </td>
                                                                <td align="left">
                                                                    <input type="text" name="makeName" id="Make Name" value="${serviceForm.makeName}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="50"/>
                                                                </td>
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.common.modelName" />
                                                                </td>
                                                                <td align="left">
                                                                    <input type="text" name="modelCodeDesc" id="Model Name" value="${serviceForm.modelCodeDesc}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="50"/>
                                                                </td>
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.common.Registrationno" />
                                                                </td>
                                                                <td align="left">
                                                                    <input type="text" name="regNo" id="Registration No" value="" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="30"/>
                                                                </td>
                                                            </tr>
                                                            <tr bgcolor="#FFFFFF" >
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.common.dateSaleOfVehicleEqui" />
                                                                </td>
                                                                <td align="left" style="padding-left: 10px;">                                                                    
                                                                    ${serviceForm.deliveryDate}<html:hidden property="saleDate" styleId="Sale Date of Vehicle Equipment" value="${serviceForm.deliveryDate}"/>
                                                                </td>
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.warranty.dateofDelivery"/>
                                                                </td>
                                                                <td align="left" style="padding-left: 10px;">                                                                    
                                                                    ${serviceForm.deliveryDate}<html:hidden property="deliveryDate" styleId="Date of Delivery" value="${serviceForm.deliveryDate}"/>
                                                                </td>
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.common.hrm" /></td>
                                                                <td align="left">
                                                                    <input type="text" name="hmrNo" id="HMR" value="" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="18"/>
                                                                </td>
                                                            </tr>
                                                            <tr bgcolor="#FFFFFF" >
                                                                <td class="headingSpas" width="15%" nowrap align="right">
                                                                    <bean:message key="label.common.title" />
                                                                </td>
                                                                <td align="left" width="20%" >
                                                                    <select name="title" id="title" class="selectnewbox" class="txtGeneralNW" style="width:155px !important ">
                                                                        <option value="">--Select--</option>
                                                                        <option value="Mr">Mr.</option>
                                                                        <option value="Mrs">Mrs.</option>
                                                                    </select>
                                                                </td>
                                                                <td class="headingSpas" width="15%" nowrap align="right">
                                                                    <bean:message key="label.common.nameOfCust" />
                                                                </td>
                                                                <td align="left" >
                                                                    <input type="text" name="customerName" id="Customer Name" value="${serviceForm.customerName}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="250"/>
                                                                </td>                                                            
                                                                <td class="headingSpas" nowrap align="right" width="15%">
                                                                    <bean:message key="label.delivery.Address" />
                                                                </td>
                                                                <td align="left" width="20%">
                                                                    <input type="text" name="customerLocation" id="Address" value="${serviceForm.customerLocation}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="250"/>
                                                                </td>
                                                            </tr>
                                                            <tr align="left" bgcolor="#FFFFFF">
                                                                <td class="headingSpas" nowrap align="right" width="10%">
                                                                    <bean:message key="label.common.city" />
                                                                </td>
                                                                <td align="left" width="20%">
                                                                    <input type="text" name="customerDistrict" id="District" value="${serviceForm.customerDistrict}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="50"/>
                                                                </td>
                                                                <td class="headingSpas" nowrap align="right" width="15%">
                                                                    <bean:message key="label.common.state" />
                                                                </td>
                                                                <td align="left" width="20%">
                                                                    <input type="text" name="customerState" id="State" value="${serviceForm.customerState}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="50"/>
                                                                </td>
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.common.pincode" />
                                                                </td>
                                                                <td align="left">
                                                                    <input type="text" name="pinCode" id="Pincode" value="${serviceForm.pinCode}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="6"/>
                                                                </td>
                                                            </tr>
                                                            <tr align="left" bgcolor="#FFFFFF">
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.common.mobilephone" />
                                                                </td>
                                                                <td align="left" >
                                                                    <input type="text" name="mobileNo" id="Mobile" value="${serviceForm.mobileNo}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="10"/>
                                                                </td>
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.common.landline" />
                                                                </td>
                                                                <td align="left" >
                                                                    <input type="text" name="contactno" id="Contact No" value="${serviceForm.contactno}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="11"/>
                                                                </td>
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.common.email" />
                                                                </td>
                                                                <td align="left">
                                                                    <input type="text" name="emailId" id="Email" value="${serviceForm.emailId}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="50"/>
                                                                </td>
                                                            </tr >                                                                                                                 
                                                            <tr align="left" bgcolor="#FFFFFF">                                                                
                                                                <td class="headingSpas" nowrap align="right">
                                                                    <bean:message key="label.common.sumInsured"/></td>
                                                                <td align="left">
                                                                    <input type="text" name="sumInsured" id="SUM Insured" value="400000" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="18"/>
                                                                </td>                                                                                                                                
                                                                <td class="headingSpas" width="15%" nowrap align="right">
                                                                    <bean:message key="label.common.policyType" />
                                                                </td>
                                                                <td align="left" bgcolor="#FFFFFF" class="text">
                                                                    <%--<select id="Policy Type" name="policyType" class="headingSpas" style="width:155px !important;" onChange="getPremiumAmt();" >
                                                                        <option value="" >--Select--</option>
                                                                        <c:forEach items="${policyTypeList}" var="policyTypeList">
                                                                            <option value='${policyTypeList.value}' title='${policyTypeList.label}'>${policyTypeList.label}</option>
                                                                        </c:forEach>
                                                                    </select>--%>
                                                                    <html:select property ="policyType" styleId="Policy Type" styleClass="headingSpas" style="width:155px !important;" onchange="getPremiumAmt();" >
                                                                        <html:option value="">--Select--</html:option>
                                                                        <html:options collection="policyTypeList" property="value" labelProperty="label"></html:options>
                                                                    </html:select>
                                                                </td>
                                                                <td class="headingSpas" nowrap align="right"  >
                                                                    <bean:message key="label.common.premiumAmount" /></td>
                                                                <td align="left" style="padding-left: 15px;" colspan="5">
                                                                    <div id="premDiv">0</div>
                                                                    <input type="hidden" name="creditAmount" id="Premium Amount" value="0" onblur="this.value=TrimAll(this.value)"/>
                                                                </td>
                                                            </tr>
                                                            <tr align="left" bgcolor="#FFFFFF">
                                                                <td align="right"><bean:message key="label.warranty.machanicname" /></td>
                                                                <td align="left">
                                                                    <select name="mechanicName"   id="Mechanic Name" style="width:150px !important" onchange="">
                                                                        <option value="">--Select--</option>
                                                                        <c:forEach items="${mechanicList}" var="dataList">
                                                                        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>
                                                                <td align="right" colspan="4" align="left" bgcolor="#FFFFFF"></td>

                                                            </tr>
                                                            <input type="hidden" name="ewLoaderId" id="EwLoader Id" value="${serviceForm.ewLoaderId}" />
                                                            <input type="hidden" id="Policy Type Id" name="policyTypeId" value="" />

                                                            <tr bgcolor="#FFFFFF" >
                                                                <td colspan="6" align="center" style="padding-top: 20px;padding-left: 80px">                                                                   
                                                                    <input name="next"  id="btn" type="button" value="Submit" style="float:none;" onClick = "submitForm()"/>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </html:form>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </center>
                        </c:if>
                        </tr>
                    </table>
                </div>
            </div>
        </center>
    </body>
</html>