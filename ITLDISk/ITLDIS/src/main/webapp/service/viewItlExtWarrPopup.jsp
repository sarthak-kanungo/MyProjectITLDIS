<%-- 
    Document   : addExtendedWarrenty
    Created on : Apr 14, 2017, 11:23:56 AM
    Author     : prashant.kumar
--%>

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

<script type="text/javascript" src="${pageContext.request.contextPath}/greybox/AJS.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/greybox/gb_scripts.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/greybox/gb_scripts.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Master_290414.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css"  type="text/css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>



<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/Master_290414.js"></script>
<script src="${pageContext.request.contextPath}/js/base64.js"></script>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*,beans.serviceForm"%>
<%@ page import="org.apache.struts.util.LabelValueBean"%>
<%@ page
	import="java.util.Date,viewEcat.comEcat.ConnectionHolder,viewEcat.comEcat.PageTemplate"%>

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

<link rel="stylesheet" href="CSS/Master_290414.css" type="text/css">
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/calendar.css"
	type="text/css" rel="stylesheet" media="screen" />
<link href="${pageContext.request.contextPath}/css/dynCalendar.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validation.js"></script>
<script src="js/intermediate.js"></script>
<c:set var="today" value="<%=new Date()%>" />
<script type="text/javascript" language="javascript"> 
    var contextPath = '${pageContext.request.contextPath}';
</script>

<script type="text/javascript" type="text/javascript">
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

       
                      
        var elementArr = new Array('payee Name','payeeState','districtDropdown' ,'tehsilDropdown','payeeVillage'
        ,'payeePinCode','Registration No','HMR','payee Mobile Phone');

        var strObject = null;
        for (var i = 0; i < elementArr.length; i++) {
            strObject = document.getElementById(elementArr[i]);
            if (strObject){
                if(elementArr[i] == 'payee Name' ||  elementArr[i] == 'payeeState' || elementArr[i] == 'districtDropdown' 
                    || elementArr[i] == 'tehsilDropdown' || elementArr[i] == 'payeeVillage' || elementArr[i] == 'payeePinCode'
                    || elementArr[i] == 'Registration No' || elementArr[i] == 'HMR' || elementArr[i] == 'payee Mobile Phone'){

                    if (!isNotEmptyCust(strObject, elementArr[i])){
                        strObject.focus();
                        return false;
                    }
                }
                if(elementArr[i] == 'Registration No'){
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
                
                var regName =/^[a-zA-Z0-9\s,'-_]*$/;
                if(elementArr[i] == 'payeeVillage'){
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
                if(elementArr[i] == 'payeePinCode'){                    
                    if (!regPin.test(strObject.value)){
                        document.getElementById("msg_saveFAILED").style.display = "";
                        document.getElementById("msg_saveFAILED").innerHTML="Only numbers with length of 6 are allowed in Payee Pincode field";
                        strObject.focus();
                        return false;
                    }
                }                
                var regMobile= /^[123456789]\d{9}$/;
                if(elementArr[i] == 'payee Mobile Phone'){                    
                    if (!regMobile.test(strObject.value)){
                        document.getElementById("msg_saveFAILED").style.display = "";
                        document.getElementById("msg_saveFAILED").innerHTML="Length of the number must be exactly 10 digits in " + elementArr[i] + " field";
                        strObject.value='';
                        strObject.focus();
                        return false;
                    }
                }
             
            }
        }
        
         var res = validateHMR(document.getElementById("HMR"));
         if(res == false)
        	 return false;
         
      
        if (confirm('Do you want to Submit.') == true && res == true) {
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
            var strURL=contextPath +"/serviceProcess.do?option=isChassisExistInItlExtWty&vinNo="+ vinNo ;
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
        
        function showDistricts(state) {
        	 
        	var url = "<%=cntxpath%>/serviceProcess.do?option=fetchDistricts&stateId=" + state;
		var xmlHttp = new XMLHttpRequest();

		ajaxindicatorstart('loading data.. please wait..');

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
				var res = trimS(xmlHttp.responseText);
				if (res != '') {
					var districtDropdown = document
							.getElementById("districtDropdown");
					districtDropdown.innerHTML = "<option value=''>--Select District--</option>"; // Clear existing options
					var districts = res.split(",");
					districts.forEach(function(district) {
						var parts = district.split(":");
						var option = document.createElement("option");
						option.value = parts[0];
						option.text = parts[1];
						districtDropdown.add(option);
					});
				}
				ajaxindicatorstop();
			}
		};

		xmlHttp.open("GET", url, true);
		xmlHttp.send(null);
		return false;
	}
        
        function showCities(district) {
       	 
        	var url = "<%=cntxpath%>/serviceProcess.do?option=fetchCities&districtId="+ district;
		   var xmlHttp = new XMLHttpRequest();

		
	ajaxindicatorstart('loading data.. please wait..');

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
				var res = trimS(xmlHttp.responseText);
				if (res != '') {
					var cityDropdown = document.getElementById("cityDropdown");
					cityDropdown.innerHTML = "<option value=''>--Select City--</option>"; // Clear existing options
					var cities = res.split(",");
					cities.forEach(function(city) {
						var parts = city.split(":");
						var option = document.createElement("option");
						option.value = parts[0];
						option.text = parts[1];
						cityDropdown.add(option);
					});
				}
				ajaxindicatorstop();
			}
		};

		xmlHttp.open("GET", url, true);
		xmlHttp.send(null);
		return false;
	}
        
        function showTehsils(city) {
          	 
        var url = "<%=cntxpath%>/serviceProcess.do?option=fetchTehsils&cityId="+ city;
		var xmlHttp = new XMLHttpRequest();

		
	    ajaxindicatorstart('loading data.. please wait..');

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
				var res = trimS(xmlHttp.responseText);
				if (res != '') {
					var tehsilDropdown = document.getElementById("tehsilDropdown");
					tehsilDropdown.innerHTML = "<option value=''>--Select City--</option>"; // Clear existing options
					var tehsils = res.split(",");
					tehsils.forEach(function(tehsil) {
						var parts = tehsil.split(":");
						var option = document.createElement("option");
						option.value = parts[0];
						option.text = parts[1];
						tehsilDropdown.add(option);
					});
				}
				ajaxindicatorstop();
			}
		};

		xmlHttp.open("GET", url, true);
		xmlHttp.send(null);
		return false;
	}
        
        
        var approvedHMR = ${serviceForm.hmrNo};
        function validateHMR(input) {
            input.value = TrimAll(input.value);
            var hmrValue = parseFloat(input.value);
            if (isNaN(hmrValue)) {
                alert("Please enter a valid hmr");
                input.focus();
                return false;
            }
            if (hmrValue < approvedHMR) {
                alert("HMR value should not be less than " + approvedHMR);
                input.focus();
                return false;
            }
            return true;
        }

        
        //showTehsils
</script>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ITLDIS</title>
<script src="JS/Master_290414.js"></script>
<link rel="stylesheet" href="CSS/Master_290414.css" type="text/css">
</head>
<body>
	
	
	<div class="message" id="msg_saveFAILED">${message}</div>
	<center>
		<div class="content_right1">
			<div class="con_slidediv2">
				<h1>
					<bean:message key="label.common.viewExtWarranty" />
				</h1>
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2">
					
					<tr>
						
							<center>
								<div class="content_right1">
									<div class="con_slidediv1"
										style="position: relative; width: 100%">
										<table id="data" width=100% border=0 cellspacing=1
											cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
											<tr height=25 bgcolor="#FFFFFF">
												<td colspan="6" align=center class="headingSpas"><html:form
														action="serviceProcess.do?option=addItlExtWarranty"
														method="post" styleId="form2" onsubmit="donotsubmit();">
														<table width=100% border=0 cellspacing=0 cellpadding=4
															bgcolor=#cccccc>
															<tr align="left" bgcolor="#FFFFFF">

																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.catalogue.chassisNo" /></td>
																<td class="headingSpas" align="left" colspan="3">
																	&nbsp;&nbsp;${serviceForm.chassisNo}<input
																	type="hidden" name="chassisNo" id="Chassis No"
																	value="${serviceForm.chassisNo}"
																	style="padding-left: 5px; width: 150px !important"
																	onblur="this.value=TrimAll(this.value)" />
																</td>
																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.catalogue.engineNo" /></td>
																<td align="left" class="headingSpas">
																	&nbsp;&nbsp;${serviceForm.engineNumber}<input
																	type="hidden" name="engineNumber" id="Engine No"
																	value="${serviceForm.engineNumber}"
																	style="padding-left: 5px; width: 150px !important"
																	onblur="this.value=TrimAll(this.value)" />
																</td>
																

																<td class="headingSpas" align="right"><bean:message
																		key="label.common.ExtendedWarrantyRegistrationDate" />
																</td>
																<td align="left" colspan="1" class="headingSpas"
																	style="padding-left: 10px; padding-bottom: 5px;">
																	<fmt:formatDate type="date" value="${today}" pattern="dd-MMM-yyyy" var="fmtToDate"/>${fmtToDate}
																	<html:hidden property="itlExtRegDate" styleId="Date of Sale Certificate" value="${fmtToDate}"/>
																	
																</td>
															</tr>
															
													
															<tr align="left" bgcolor="#FFFFFF">
																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.ProductCategory" /></td>
																<td align="left" class="headingSpas" colspan="3">
																	&nbsp;&nbsp;TRACTOR<input
																	type="hidden" name="productionCategory_Desc" id="productionCategory_Desc"
																	value="Tractor"
																	style="padding-left: 5px; width: 150px !important"
																	onblur="this.value=TrimAll(this.value)" />
																</td>
																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.TractorModel" /></td>
																<td align="left" class="headingSpas">
																	&nbsp;&nbsp;${serviceForm.modelCodeDesc}<input
																	type="hidden" name="modelFamilyDesc" id="modelFamilyDesc"
																	value="${serviceForm.modelCodeDesc}"
																	style="padding-left: 5px; width: 150px !important"
																	onblur="this.value=TrimAll(this.value)" />
																</td>
																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.fuelType" /></td>
																		
																<td align="left"><input type="text" name="fuelType" readonly
																	id="Fuel Typ" value="${serviceForm.fuelType}"
																	style="width: 150px !important"
																	onblur="this.value=TrimAll(this.value)" maxlength="30" />
																</td>		
																
															</tr>
															<tr bgcolor="#FFFFFF">
																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.CustomerName" /></td>
																<td align="left" colspan="3"><input type="text" name="customerName"
																	id="customer Name" value="${serviceForm.customerName}"
																	style="width: 350px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="50" />
																</td>
																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.PayeeName" /></td>
																<td align="left"><input type="text" readonly
																	name="payeeName" id="payee Name"
																	value="${serviceForm.customerName}"
																	style="width: 150px !important"
																	onblur="this.value=TrimAll(this.value)" maxlength="50" />
																</td>
																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.TractorRegistrationno" /></td>
																<td align="left"><input type="text" name="regNo" readonly
																	id="Registration No" value="${serviceForm.regNo}"
																	style="width: 150px !important"
																	onblur="this.value=TrimAll(this.value)" maxlength="30" />
																</td>
															</tr>
															<tr bgcolor="#FFFFFF">
																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.village" /></td>
																<td align="left" ><input type="text" name="village"
																	id="customer village" value="${serviceForm.village}"
																	style="width: 150px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="50" />
																</td>

																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.mobilephone" /></td>
																<td align="left"><input type="text" name="mobileNo"
																	id="Mobile" value="${serviceForm.mobileNo}"
																	style="width: 150px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="10" />
																</td>
																
																
																<td class="headingSpas" nowrap align="right">
																	<bean:message key="label.common.state" />
																</td>
																<td align="left"><input type="text" name="payeeState"
																	id="payeeState" value="${serviceForm.payeeState}"
																	style="width: 150px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="10" />
																</td>
																

															
																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.hrm" /></td>
																<td align="left"><input type="text" name="hmrNo" readonly
																	id="HMR" value="${serviceForm.hmrNo}" style="width: 150px !important"
																	onblur="this.value=TrimAll(this.value)" maxlength="18" onchange="validateHMR(this)"/>
																</td>
															</tr>
															<tr bgcolor="#FFFFFF">
																<td class="headingSpas" width="15%" nowrap align="right">
																	<bean:message key="label.common.talukatahsil" />
																</td>
																<td align="left"><input type="text"
																	name="tehsil" id="tehsil"
																	value="${serviceForm.tehsil}"
																	style="width: 150px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="250" />
																</td>

																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.landline" /></td>
																<td align="left"><input type="text" name="landline" readonly
																	id="landline" value="${serviceForm.landline}"
																	style="width: 150px !important" 
																	onblur="this.value=TrimAll(this.value)" maxlength="10" />
																</td>
																
																<td class="headingSpas" nowrap align="right" width="15%">
																	<bean:message key="label.common.district" />
																</td>
																<td align="left"><input type="text" name="payeeDistrict"
																	readonly id="payeeState" value="${serviceForm.payeeDistrict}"
																	style="width: 150px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="10" />
																</td>
														      
																<td class="headingSpas" nowrap align="right" width="15%">
																	<bean:message key="label.common.EWType" />
																</td>
																<td align="left" width="20%"><input type="text"
																	name="ewType" id="ewType"
																	value="${serviceForm.ewType}"
																	style="width: 150px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="250" />
																</td>

															</tr>
															<tr align="left" bgcolor="#FFFFFF">
																<td class="headingSpas" nowrap align="right" width="10%">
																	<bean:message key="label.common.district" />
																</td>
																<td align="left" width="20%"><input type="text"
																	name="district" id="District"
																	value="${serviceForm.district}"
																	style="width: 150px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="50" />
																</td>
																
																<td class="headingSpas" nowrap align="right" width="10%">
																	<bean:message key="label.common.emailid" />
																</td>
																<td align="left" width="20%"><input type="text" readonly
																	name="emailId" id="emailId"
																	value="${serviceForm.emailId}"
																	style="width: 150px !important" 
																	onblur="this.value=TrimAll(this.value)" maxlength="50" />
																</td>

																
																
                                                                <td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.city" /></td>
																
																<td align="left"><input type="text" name="payeeCity" readonly
																	id="payeeCity" value="${serviceForm.payeeCity}"
																	style="width: 150px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="10" />
																</td>		
															    
																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.EWRegistrationAmount" /></td>
																<td align="left"><input type="text" name="ewRegistrationAmount" 
																	id="ewRegistrationAmount" value="1800.00"
																	style="width: 150px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="6" />
																</td>
															</tr>
															<tr class="headingSpas" align="left" bgcolor="#FFFFFF">
																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.pincode" /></td>
																<td align="left"><input type="text" name="pincode" readonly
																	id="pincode" value="${serviceForm.pinCode}"
																	style="width: 150px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="10" />
																</td>

																<td align="right" ><label><bean:message
																			key="label.common.sizeoflandhold" /></label></td>

																<td align="left"><input type="text" name="sizeoflandhold"
																	id="sizeoflandhold" value="${serviceForm.sizeoflandhold}" readonly
																	style="width: 150px !important" 
																	onblur="this.value=TrimAll(this.value)"  />
																</td>

																
																
																<td class="headingSpas" width="15%" nowrap align="right">
																	<bean:message key="label.common.talukatahsil" />
																</td>
																<td align="left"><input type="text" name="payeeTehsil"
																	readonly id="payeeTehsil" value="${serviceForm.payeeTehsil}"
																	style="width: 150px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="10" />
																</td>
																
																<td class="headingSpas" width="15%" nowrap align="right">
																	<bean:message key="label.common.PayeeMobilePhone" /></td>
																<td align="left"><input type="text" name="payeeMobilePhone" readonly
																	id="payee Mobile Phone" value="${serviceForm.mobileNo}"
																	style="width: 150px !important" 
																	onblur="this.value=TrimAll(this.value)" maxlength="50" />
																</td>
															</tr>
															<tr align="left" bgcolor="#FFFFFF">
																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.state" /></td>
																<td align="left"><input type="text"
																	name="state" id="state" value="${serviceForm.state}" readonly
																	style="width: 150px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="18" />
																</td>

																
																<td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.maincrop" /></td>
																<td align="left">
																		<input type="text" name="maincrops"
																	id="maincrops" value="${serviceForm.maincrops}" readonly
																	style="width: 150px !important" 
																	onblur="this.value=TrimAll(this.value)"  />
																</td>


																<td class="headingSpas" align="right"><bean:message
																		key="label.common.village" /></td>
																<td align="left"><input type="text"
																	name="payeeVillage" id="payeeVillage" value="${serviceForm.payeeVillage}" readonly
																	style="width: 150px !important" 
																	onblur="this.value=TrimAll(this.value)" maxlength="18" />
																</td>
																
																<td class="headingSpas" nowrap align="right">
																	
																</td>
																<td align="left">
																</td>
																
																
															</tr>
															<tr class="headingSpas" align="left" bgcolor="#FFFFFF">
																<td align="right"><bean:message
																		key="label.common.Country" /></td>
																<td class="headingSpas" align="left"><input type="text" readonly
																	name="country" id="country" value="India"
																	style="width: 150px !important" readonly
																	onblur="this.value=TrimAll(this.value)" maxlength="18" />
																</td>

																<td align="right" style="white-space: nowrap"><label><bean:message
																			key="label.common.occupation" /></label></td>

																<td align="left">

																		<input name="occupation" type="text" id="Occupation" readonly
																			name="occupation" value="${serviceform.occupation}" 
																			style="width: 150px !important" 
																	onblur="this.value=TrimAll(this.value)" 
																			 /></td>

																 <td class="headingSpas" nowrap align="right"><bean:message
																		key="label.common.pincode" /></td>
																<td align="left"><input type="text" readonly
																	name="payeePinCode" id="payeePinCode"
																	value="${serviceForm.payeePinCode}"
																	style="width: 150px !important"
																	onblur="this.value=TrimAll(this.value)" maxlength="11" />
																</td> 

																<td align="right" colspan="4" align="left"
																	bgcolor="#FFFFFF"></td>

															</tr>
															<input type="hidden" name="ewLoaderId" id="EwLoader Id"
																value="${serviceForm.ewLoaderId}" />
															<input type="hidden" id="Policy Type Id"
																name="policyTypeId" value="" />

															
														</table>
													</html:form></td>
											</tr>
										</table>
									</div>
								</div>
							</center>
						
					</tr>
				</table>
			</div>
		</div>
	</center>
</body>
</html>