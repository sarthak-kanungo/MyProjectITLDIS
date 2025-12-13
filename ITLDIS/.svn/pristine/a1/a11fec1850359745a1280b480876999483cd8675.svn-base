<%--
    Document   : updateExtWarranty
    Created on : Apr 24, 2017, 10:13:22 AM
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
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DATE);
%>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.9.2.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/calendar.css" type="text/css" rel="stylesheet" media="screen" />
<link href="${pageContext.request.contextPath}/css/dynCalendar.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css"  type="text/css"/>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery-ui-1.9.2.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ui.dropdownchecklist.standalone.css"  type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.dropdownchecklist-1.4-min.js"></script>
<script src="js/intermediate.js"></script>

<c:set var="today" value="<%=new Date()%>"/>

<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>

<script type="text/javascript" >
    $(function() {
        $(".datepicker").datepicker({ dateFormat: 'dd/mm/yy'}).val();        
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
        ,'Address','District','State','Pincode','Mobile','Contact No','Email','Sum Insured'
        ,'Bajaj Policy No','Bajaj Policy Date');

        var strObject = null;
        for (var i = 0; i < elementArr.length; i++) {
            strObject = document.getElementById(elementArr[i]);
            if (strObject){
                if(elementArr[i] == 'Model Name' ||  elementArr[i] == 'Make Name'
                    || elementArr[i] == 'HMR' || elementArr[i] == 'Customer Name' || elementArr[i] == 'Address'
                    || elementArr[i] == 'District' || elementArr[i] == 'State' || elementArr[i] == 'Pincode'
                    || elementArr[i] == 'Mobile' || elementArr[i] == 'Sum Insured' 
                    || elementArr[i] == 'Bajaj Policy No' || elementArr[i] == 'Bajaj Policy Date'){

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
                if(elementArr[i] == 'Address' || elementArr[i]== 'Bajaj Policy No'){
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

                    if(elementArr[i] == 'Sum Insured'){
                        if (isNaN(strObject.value)){
                            document.getElementById("msg_saveFAILED").style.display = "";
                            document.getElementById("msg_saveFAILED").innerHTML="Only numbers are allows in Sum Insured field";
                            strObject.focus();
                            return false;
                        }
                    }

                    if(elementArr[i] == 'Sum Insured'){
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

            var certDate = document.getElementById("Certificate Date").value.split('/');            
            var bPolicyDate = document.getElementById("Bajaj Policy Date").value.split('/');            
            var certDate1=new Date(certDate[2]+'/'+certDate[1]+'/'+certDate[0]);
            var bPolicyDate1=new Date(bPolicyDate[2]+ '/'+ bPolicyDate[1] +'/'+bPolicyDate[0]);
           
            if (bPolicyDate1 < certDate1){
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML="Bajaj Policy Date should be greater than Certificate Date";
                bPolicyDate='';
                document.getElementById("Bajaj Policy Date").focus();
                return false;
            }
            if (confirm('Do you want to Submit.') == true) {
                document.getElementById("btn").disabled = true;
                document.getElementById('form1').submit();
            }
        }

        function donotsubmit()
        {
            return false;
        }

        function getPremiumAmt(dealerCode)
        {
            var todate=new Date().getTime();
            var policyType= document.getElementById("Policy Type").value;
            var delDate= document.getElementById("Date of Delivery").value;
            var strURL=contextPath +"/serviceProcess.do?option=getPremiumAmt&dealerCode="+dealerCode+"&policyType="+ policyType + "&delDate="+ delDate+"&todate="+todate;
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
                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);
            ajaxindicatorstop();
        }

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
        <div align="center" class="message" id="msg_saveFAILED" style="font-size:12px; color: red">${message}</div>
        <center>
            <div class="content_right1">
                <div class="con_slidediv2" >             
                    <table id="data" width=98% border=0 cellspacing=1 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                        <tr height=25 bgcolor="#FFFFFF">
                            <td colspan="6" align= center class="headingSpas">
                                <html:form action="serviceProcess.do?option=updateExtWarranty" method="post" styleId="form1" onsubmit="donotsubmit();">
                                    <table width=100% border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                                        <tr align="left" bgcolor="#FFFFFF" >
                                            <td colspan="6" align="center">
                                                <table width=100% border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                                                    <tr bgcolor="#eeeeee">
                                                        <td>
                                                            <font class="headingSpas"><b><bean:message key="label.common.editExtWarranty" /></b></font>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <div class="message" id="msg_saveFAILED">${message}</div>

                                        <tr align="left" bgcolor="#FFFFFF" >
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.dealerCode"/>&nbsp;&nbsp;: 
                                            </td>
                                            <td class="headingSpas"  align="left">${serviceForm.dealerCode}</td>
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.dealerName"/>&nbsp;&nbsp;: 
                                            </td>
                                            <td align="left" class="headingSpas">${serviceForm.dealerName}</td>

                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.status"/>&nbsp;&nbsp;: 
                                            </td>
                                            <td  class="headingSpas" align="left">${serviceForm.status}</td>
                                        </tr>
                                        <tr align="left" bgcolor="#FFFFFF" >
                                            <td class="headingSpas" nowrap align="right" width="132px" style="padding-right: 5px;">
                                                <bean:message key="label.common.itlEwRef"/>&nbsp;&nbsp;: 
                                            </td>
                                            <td class="headingSpas" nowrap align="left" width="132px" style="padding-left: 5px;">
                                                ${serviceForm.ewLoaderId}</td>
                                            <td class="headingSpas" align="right" nowrap>
                                                <bean:message key="label.common.dateSaleOfCertificate"/>&nbsp;&nbsp;: 
                                            </td>
                                            <td class="headingSpas" nowrap align="left" colspan="3">                                                
                                                <html:hidden property="dateOfSaleOfCertificate" styleId="Certificate Date" value="${serviceForm.dateOfSaleOfCertificate}"/>
                                                ${serviceForm.invoiceDate}
                                            </td>
                                        </tr>
                                        <tr align="left" bgcolor="#FFFFFF">
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
                                            <td align="left">
                                                <html:select property="fuelType" styleId="Fuel Type" styleClass="headingSpas" style="width:150px !important">
                                                    <%-- <html:option value="">--Select--</html:option>
                                                     <html:option value ="Petrol">Petrol</html:option>--%>
                                                    <html:option value ="Diesel">Diesel</html:option>
                                                </html:select>
                                            </td>                                           
                                        </tr>
                                        <tr bgcolor="#FFFFFF" >
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.makeName" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left">
                                                <input type="text" name="makeName"  class="headingSpas" id="Make Name" value="${serviceForm.makeName}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="50"/>
                                            </td>
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.modelName" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left" >
                                                <input type="text"  class="headingSpas" name="modelCodeDesc" id="Model Name" value="${serviceForm.modelCodeDesc}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="50"/>
                                            </td>
                                            <td class="headingSpas" nowrap align="right" >
                                                <bean:message key="label.common.Registrationno" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left">
                                                <input class="headingSpas" type="text" name="regNo" id="Registration No" value="${serviceForm.regNo}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="30"/>
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF" >
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.dateSaleOfVehicleEqui" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left" style="padding-left: 5px;"  class="headingSpas">
                                                ${serviceForm.deliveryDate}<html:hidden property="saleDate" styleId="Sale Date of Vehicle Equipment" value="${serviceForm.deliveryDate}"/>
                                            </td>
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.warranty.dateofDelivery"/>&nbsp;&nbsp;:
                                            </td>
                                            <td align="left" style="padding-left: 5px;" class="headingSpas">
                                                ${serviceForm.deliveryDate}<html:hidden property="deliveryDate" styleId="Date of Delivery" value="${serviceForm.deliveryDate}"/>
                                            </td>
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.hrm" />&nbsp;&nbsp;:</td>
                                            <td align="left">
                                                <input type="text" name="hmrNo" class="headingSpas" id="HMR" value="${serviceForm.hmrNo}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="18"/>
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF" >
                                            <td class="headingSpas" width="15%" nowrap align="right">
                                                <bean:message key="label.common.title" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left" class="headingSpas" >
                                                <html:select property="title" styleId="title" styleClass="headingSpas" style="width:150px !important">
                                                    <html:option value="">--Select--</html:option>
                                                    <html:option value ="Mr">Mr.</html:option>
                                                    <html:option value ="Mrs">Mrs.</html:option>
                                                </html:select>                                    
                                            </td>
                                            <td class="headingSpas" width="15%" nowrap align="right">
                                                <bean:message key="label.common.nameOfCust" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left" >
                                                <input type="text" class="headingSpas" name="customerName" id="Customer Name" value="${serviceForm.customerName}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="250"/>
                                            </td>
                                            <td class="headingSpas" nowrap align="right" width="15%">
                                                <bean:message key="label.delivery.Address" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left" width="20%">
                                                <input type="text" class="headingSpas" name="customerLocation" id="Address" value="${serviceForm.customerLocation}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="250"/>
                                            </td>
                                        </tr>
                                        <tr align="left" bgcolor="#FFFFFF">
                                            <td class="headingSpas" nowrap align="right" width="10%">
                                                <bean:message key="label.common.city" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left" width="20%">
                                                <input type="text" class="headingSpas" name="customerDistrict" id="District" value="${serviceForm.customerDistrict}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="50"/>
                                            </td>
                                            <td class="headingSpas" nowrap align="right" width="15%">
                                                <bean:message key="label.common.state" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left" width="20%" >
                                                <input type="text" class="headingSpas" name="customerState" id="State" value="${serviceForm.customerState}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="50"/>
                                            </td>
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.pincode" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left" >
                                                <input type="text" class="headingSpas" name="pinCode" id="Pincode" value="${serviceForm.pinCode}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="6"/>
                                            </td>
                                        </tr>
                                        <tr align="left" bgcolor="#FFFFFF">
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.mobilephone" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left">
                                                <input type="text" class="headingSpas" name="mobileNo" id="Mobile" value="${serviceForm.mobileNo}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="10"/>
                                            </td>
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.landline" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left">
                                                <input type="text" class="headingSpas" name="contactno" id="Contact No" value="${serviceForm.contactno}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="11"/>
                                            </td>
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.email" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left">
                                                <input type="text" class="headingSpas" name="emailId" id="Email" value="${serviceForm.emailId}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="50"/>
                                            </td>
                                        </tr >
                                        <tr bgcolor="#FFFFFF" >
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.sumInsured"/>&nbsp;&nbsp;:</td>
                                            <td align="left">
                                                <input type="text" class="headingSpas" name="sumInsured" id="Sum Insured" value="${serviceForm.sumInsured}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="18"/>
                                            </td>
                                            <td class="headingSpas" width="15%" nowrap align="right" >
                                                <bean:message key="label.common.policyType" />&nbsp;&nbsp;:
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" class="headingSpas">
                                                <html:select property ="policyTermId" styleId="Policy Type" styleClass="headingSpas" style="width:150px !important;" onchange="getPremiumAmt('${serviceForm.dealerCode}');" >
                                                    <html:option value="">--Select--</html:option>
                                                    <html:options collection="policyTypeList" property="value" labelProperty="label"></html:options>
                                                </html:select>
                                            </td>
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.premiumAmount" />&nbsp;&nbsp;: </td>
                                            <td align="left" style="padding-left: 5px;" class="headingSpas" >
                                                <div id="premDiv">${serviceForm.creditAmount}</div>
                                                <input type="hidden" name="creditAmount" id="Premium Amount" value="${serviceForm.creditAmount}" onblur="this.value=TrimAll(this.value)"/>
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF" >
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.bajajPolicyNo"/>&nbsp;&nbsp;: 
                                            </td>
                                            <td align="left" >
                                                <input type="text" class="headingSpas" name="bajajPolicyNo" id="Bajaj Policy No" value="${serviceForm.bajajPolicyNo}" style="width:150px !important " onblur="this.value=TrimAll(this.value)" maxlength="50"/>
                                            </td>
                                            <td class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.bajajPolicyDate"/>&nbsp;&nbsp;: 
                                            </td>
                                            <td align="left"  class="headingSpas">                                               
                                                <html:text styleClass="headingSpas datepicker" property="bajajPolicyDate" styleId="Bajaj Policy Date" readonly="true" value="${serviceForm.bajajPolicyDate}" style="width:150px !important "/>
                                            </td>
                                            <td align="right" class="headingSpas"><bean:message key="label.warranty.machanicname" /></td>
                                            <td align="left" class="headingSpas">
                                                <select name="mechanicName"   id="Mechanic Name" style="width:150px !important" onchange="" class="headingSpas">
                                                    <option value="">--Select--</option>
                                                    <c:forEach items="${mechanicList}" var="dataList">
                                                        <c:choose>
                                                            <c:when test="${serviceForm.mechanicDealerKey eq dataList.value }">
                                                                <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                                            </c:otherwise>
                                                        </c:choose>`
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <input type="hidden" id="Policy Type Id" name="policyTypeId" value="" />
                                        <input type="hidden" id="EW Loader Id" name="ewLoaderId" value="${serviceForm.ewLoaderId}" />

                                        <tr bgcolor="#FFFFFF" >
                                            <td colspan="6" align="center" style="padding-top: 20px;padding-left: 80px">
                                                <input name="next" type="button" value="Submit" id="btn" style="float:none;" onClick = "submitForm()"/>
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
    </body>
</html>
