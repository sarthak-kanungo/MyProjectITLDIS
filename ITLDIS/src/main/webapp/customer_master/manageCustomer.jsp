<%-- 
    Document   : manageCustomer
    Created on : 6 Feb, 2016, 2:31:51 PM
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
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
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
        <script src="<%=cntxpath%>/js/Master_290414.js"></script>
        <script src="<%=cntxpath%>/js/intermediate.js"></script>
        <link rel="stylesheet" href="css/Master_290414.css" type="text/css" >
        <script type="text/javascript" language="javascript">
            
            function expValidated(str) {                
               var regularExpression = /^[ A-Za-z0-9_/-].*$/;
                var valid = regularExpression.test(str);
                return valid;
            }            
            
            function validateCustForm(){

                var elementArr = new Array('Customer Category','Customer Code','Customer Name','Contact Person','Contact No.'
                ,'E-Mail','Country','State','District','Tehsil','CustomerBlock','Location / Village','Location','Pin Code','Date of Birth'
                ,'PAN No','TIN No','Target','Credit Limit','Additional Discount Percentage','Transporter in Use','GST No');//,'Dealer Code'

                var strObject = null;
                for (var i = 0; i < elementArr.length; i++)
                {
                    strObject = document.getElementById(elementArr[i]);
                    if (strObject){
                         if(elementArr[i] == 'Customer Category'){
                            if (!isChosenCust(strObject, elementArr[i])){
                                strObject.focus();
                                return false;
                            }
                        }
                        if(elementArr[i] == 'Customer Code' || elementArr[i] == 'Customer Name'
                            || elementArr[i] == 'Contact No.' || elementArr[i] == 'Target' || elementArr[i] == 'Credit Limit'
                            || elementArr[i] == 'Additional Discount Percentage' || elementArr[i] =='Contact Person' || elementArr[i] == 'Location'){//|| elementArr[i] == 'Dealer Code'
                            if (!isNotEmptyCust(strObject, elementArr[i])){
                                strObject.focus();
                                return false;
                            }
                        }
                        if(elementArr[i] == 'Customer Code'){
                            if (strObject.value !='' && !isProperSerialCustCode(strObject.value)){
                                document.getElementById("msg_saveFAILED").style.display = "";
                                document.getElementById("msg_saveFAILED").innerHTML="Special characters 'Space' and (-, /) are  allowed in "+elementArr[i]+" field";
                                strObject.focus();
                                return false;
                            }
                        }
                        if(elementArr[i] == 'Contact No.'){
                            if (isNaN(strObject.value)){
                                document.getElementById("msg_saveFAILED").style.display = "";
                                document.getElementById("msg_saveFAILED").innerHTML="Only numbers are allowed in "+elementArr[i]+" field";
                                strObject.value='';
                                strObject.focus();
                                return false;
                            }
                        }
                        if(elementArr[i] == 'PAN No' || elementArr[i] == 'TIN No' || elementArr[i] == 'GST No'){
                            if (strObject.value !='' && !isProperSerialCust(strObject.value)){
                                document.getElementById("msg_saveFAILED").style.display = "";
                                document.getElementById("msg_saveFAILED").innerHTML="Only Special characters (-, /, _) are allowed in "+elementArr[i]+" field";
                                strObject.focus();
                                return false;
                            }
                        }
                        //|| elementArr[i] == 'Country' || elementArr[i] == 'State' || elementArr[i] == 'District' || elementArr[i] == 'Tehsil' ||  elementArr[i] == 'Customer Block'
                        if(elementArr[i] == 'Customer Name' || elementArr[i] == 'Contact person') {
                           if(strObject.value !='' && !isProperSerialCust(strObject.value)){
                                document.getElementById("msg_saveFAILED").style.display = "";
                                document.getElementById("msg_saveFAILED").innerHTML="Only Special characters (-, /, _) are allowed in "+elementArr[i]+" field";
                                strObject.focus();
                                return false;
                            }
                         }
                        if(elementArr[i] == 'Location') {
                           if(!isProperSerialCust(strObject.value)){
                                document.getElementById("msg_saveFAILED").style.display = "";
                                document.getElementById("msg_saveFAILED").innerHTML="Only Special characters (-, /, _) are allowed in "+elementArr[i]+" field";
                                strObject.focus();
                                return false;
                            }
                         }


                        if(elementArr[i] == 'Pin Code'){
                            if (isNaN(strObject.value)){
                                document.getElementById("msg_saveFAILED").style.display = "";
                                document.getElementById("msg_saveFAILED").innerHTML="Only numbers are allowed in "+elementArr[i]+" field";
                                strObject.value='';
                                strObject.focus();
                                return false;
                            }
                        }
                       <%-- if(elementArr[i] =='Location / Village'){
                            if (!isChosenCust(strObject, elementArr[i])){
                                strObject.focus();
                                return false;
                            }
                        }--%>
                        if(elementArr[i] == 'Target' || elementArr[i] == 'Credit Limit' || elementArr[i] == 'Additional Discount Percentage'){
                            if (!checkNumberCust(strObject)){
                                strObject.value='';
                                strObject.focus();
                                return false;
                            }
                        }
                         
                        if(elementArr[i] == 'E-Mail'){
                            if (strObject.value != '' && !isEMailAddr(strObject)){
                                strObject.value='';
                                strObject.focus();
                                return false;
                            }
                        }
                        if(elementArr[i] == 'Date of Birth'){
                            if (strObject.value != '' && !checkDate(strObject)){
                                strObject.focus();
                                return false;
                            }
                        }
                         if(elementArr[i] == 'Additional Discount Percentage'){
                            if (strObject.value !='' && strObject.value>100){
                                document.getElementById("msg_saveFAILED").style.display = "";
                                document.getElementById("msg_saveFAILED").innerHTML="" + strObject.value + "" + "field must be less then 100";
                                strObject.focus();
                                return false;
                            }
                        }
                        if(elementArr[i] == 'Transporter in Use'){
                            if (strObject.value !='' && !isProperSerialCust(strObject.value)){
                                document.getElementById("msg_saveFAILED").style.display = "";
                                document.getElementById("msg_saveFAILED").innerHTML="Only Special characters (-, /, _) are allowed in "+elementArr[i]+" field";
                                strObject.focus();
                                return false;
                            }
                        }
                    }
                }
                
            }
            function checkDate(field)
            {
                var maxYear = (new Date()).getFullYear();
                var maxMonth = (new Date()).getMonth()+1;
                var maxDay = (new Date()).getDate();
                var errorMsg = "";

                // regular expression to match required date format
                var re = /^(\d{1,2})\/(\d{1,2})\/(\d{4})$/;

                if(field.value != '') {
                    var regs=field.value.split("/");
                    if(field.value.match(re)) {
                        var month=regs[1];
                        var day=regs[0];
                        var year=regs[2];
                        if((month < 1) || (month > 12)){
                            errorMsg = "Invalid value for day: " + month;
                        }else if((day < 1) || (day > 31)){
                            errorMsg = "Invalid value for day: " + day;
                        }else if(((month == 4) || (month == 6) || (month == 9) || (month == 11)) && (day > 30)) {
                            errorMsg = "Invalid value date: " + field.value;
                        }else if((month == 2) && (((year % 400) == 0) || ((year % 4) == 0)) && ((year % 100) != 0) && (day > 29)) {
                            errorMsg = "Invalid value date: " + field.value;
                        }else if((month == 2) && ((year % 100) == 0) && (day > 29)) {
                            errorMsg = "Invalid value date: " + field.value;
                        }else if((month == 2) && (day > 28)){
                            errorMsg = "Invalid value date: " + field.value;
                        }else if(year>maxYear){
                            errorMsg = "Date of Birth can not be future date.";
                        }else if(year==maxYear){
                            if(maxMonth<month){
                                errorMsg = "Date of Birth can not be future date.";
                            }else if(maxMonth==month){
                                if(maxDay<day){
                                    errorMsg = "Date of Birth can not be future date.";
                                }
                            }
                        }
                    } else {
                        errorMsg = "Invalid date format: " + field.value;
                    }
                }

                if(errorMsg != "") {
                    alert(errorMsg);
                    field.focus();
                    return false;
                }

                return true;
            }
            $(function() {
                $( ".datepicker" ).datepicker();
            });


            function getStandardDiscount(val)
            {
                    var todate=new Date().getTime();
                    if(val!=undefined)
                        var custCategoryID=val;
                    var strURL = '<%=cntxpath%>/manageCustomerAction.do?option=getStandardDiscount&custCategoryID=' + custCategoryID + '&todate=' + todate;
                    xmlHttp = GetXmlHttpObject();
                    ajaxindicatorstart('loading data.. please wait..');
                    xmlHttp.onreadystatechange = function()
                    {
                        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                        {
                            res = trim(xmlHttp.responseText);
                            //alert(res+"&&"+strURL);
                            if(res!=''){
                            document.getElementById("standardDiscount").innerHTML=" "+res;
                            }else{
                             document.getElementById("standardDiscount").innerHTML="0";
                            }
                        }
                    }
                    xmlHttp.open("POST", strURL, true);
                    xmlHttp.send(null);
                    ajaxindicatorstop();


            }
            function commonValidate()
            {
                var tempStr;
                tempStr=document.forms[0].locationVillageID.options[document.forms[0].locationVillageID.selectedIndex].innerHTML;
                var locationVillageID=tempStr.replace("&amp;" ,"&");
                if(document.forms[0].locationVillageID.value!=""){
                    document.forms[0].customerLocation.value=locationVillageID;
                    document.forms[0].customerLocation.readOnly=true;
                }else{
                    document.forms[0].customerLocation.value="";
                    document.forms[0].customerLocation.readOnly=false;
                }
                tempStr=document.forms[0].countryID.options[document.forms[0].countryID.selectedIndex].innerHTML;
                var countryID=tempStr.replace("&amp;" ,"&");
                if(document.forms[0].countryID.value!=""){
                    document.forms[0].customerCountry.value=countryID;
                    document.forms[0].customerCountry.readOnly=true;
                }else{
                    document.forms[0].customerCountry.value="";
                    document.forms[0].customerCountry.readOnly=false;
                }
                tempStr=document.forms[0].stateID.options[document.forms[0].stateID.selectedIndex].innerHTML
                var stateID=tempStr.replace("&amp;" ,"&");
                if(document.forms[0].stateID.value!=""){
                    document.forms[0].customerState.value=stateID
                    document.forms[0].customerState.readOnly=true;
                }else{
                    document.forms[0].customerState.value="";
                    document.forms[0].customerState.readOnly=false;
                }

                tempStr=document.forms[0].districtID.options[document.forms[0].districtID.selectedIndex].innerHTML;
                var districtID=tempStr.replace("&amp;" ,"&");
                if(document.forms[0].districtID.value!=""){
                    document.forms[0].customerDistrict.value=districtID;
                    document.forms[0].customerDistrict.readOnly=true;
                }else{
                    document.forms[0].customerDistrict.value="";
                    document.forms[0].customerDistrict.readOnly=false;
                }
                tempStr=document.forms[0].tehsilID.options[document.forms[0].tehsilID.selectedIndex].innerHTML;
                var tehsilID=tempStr.replace("&amp;" ,"&");
                if(document.forms[0].tehsilID.value!=""){
                    document.forms[0].customerTehsil.value=tehsilID;
                    document.forms[0].customerTehsil.readOnly=true;
                }else{
                    document.forms[0].customerTehsil.value="";
                    document.forms[0].customerTehsil.readOnly=false;
                }
                tempStr=document.forms[0].blockID.options[document.forms[0].blockID.selectedIndex].innerHTML;
                var blockID=tempStr.replace("&amp;" ,"&");
                if(document.forms[0].blockID.value!=""){
                    document.forms[0].customerBlock.value=blockID;
                    document.forms[0].customerBlock.readOnly=true;
                }else{
                    document.forms[0].customerBlock.value="";
                    document.forms[0].customerBlock.readOnly=false;
                }
            }

           function getStateOnCountry(obj) {
            var conId=obj.toString();
            
            if(conId==''){
                $('Select[name="stateID"] option').remove();
                $('Select[name="stateID"]').append($("<option>").attr("value", '').text('Select'));
            }else{
                $.ajax({
                    type: "POST",
                    url: "manageCustomerAction.do?option=getSubFieldDetails&getColName=stateId,state&whereColName=countryId&colValue="+conId+"&orderBy=state",
                    success: function(response){
                        if(response!=""){
                            var array = response.split("@");
                            $('Select[name="stateID"] option').remove();
                            $('Select[name="stateID"]').append($("<option>").attr("value", '').text('Select'));
                            for (var i=0;i<array.length-1;i=i+2){                                
                                $('Select[name="stateID"]').append($("<option>").attr("value", array[i]).text(array[i+1]));
                            }
                        }else{
                            $('Select[name="stateID"] option').remove();
                            $('Select[name="stateID"]').append($("<option>").attr("value", '').text('Select'));
                        }
                    },
                    error: function(e){
                        alert('Error: ' + e);
                    }
                });
            }
            $('Select[name="districtID"] option').remove();
            $('Select[name="districtID"]').append($("<option>").attr("value", '').text('Select'));

            $('#State').val("");
            $('#District').val("");
            $('#Tehsil').val("");
            $('#CustomerBlock').val("");
            $('#Location').val("");

            $('#State').attr("readonly", false);
            $('#District').attr("readonly", false);
            $('#Tehsil').attr("readonly", false);
            $('#CustomerBlock').attr("readonly", false);
            $('#Location').attr("readonly", false);
            
            $('Select[name="tehsilID"] option').remove();
            $('Select[name="tehsilID"]').append($("<option>").attr("value", '').text('Select'));

            $('Select[name="blockID"] option').remove();
            $('Select[name="blockID"]').append($("<option>").attr("value", '').text('Select'));

            $('Select[name="locationVillageID"] option').remove();
            $('Select[name="locationVillageID"]').append($("<option>").attr("value", '').text('Select'));
            
        }

          function getDistOnState(obj) {              
            var stateId=obj.toString();            

            if(stateId=='Select'){
                $('Select[name="districtID"] option').remove();
                $('Select[name="districtID"]').append($("<option>").attr("value", '').text('Select'));
            }else{
                $.ajax({
                    type: "POST",
                    url: "manageCustomerAction.do?option=getSubFieldDetails&getColName=districtId,district&whereColName=stateId&colValue="+stateId+"&orderBy=district",
                    success: function(response){
                        if(response!=""){
                            var array = response.split("@");
                            $('Select[name="districtID"] option').remove();
                            $('Select[name="districtID"]').append($("<option>").attr("value", '').text('Select'));
                            for (i=0;i<array.length-1;i=i+2){
                                $('Select[name="districtID"]').append($("<option>").attr("value", array[i]).text(array[i+1]));
                            }
                        }else{
                            $('Select[name="districtID"] option').remove();
                            $('Select[name="districtID"]').append($("<option>").attr("value", '').text('Select'));
                        }
                    },
                    error: function(e){
                        alert('Error: ' + e);
                    }
                });
            }

            $('Select[name="tehsilID"] option').remove();
            $('Select[name="tehsilID"]').append($("<option>").attr("value", '').text('Select'));
           
            $('#District').val("");
            $('#Tehsil').val("");
            $('#CustomerBlock').val("");
            $('#Location').val("");

            $('#District').attr("readonly", false);
            $('#Tehsil').attr("readonly", false);
            $('#CustomerBlock').attr("readonly", false);
            $('#Location').attr("readonly", false);

            $('Select[name="blockID"] option').remove();
            $('Select[name="blockID"]').append($("<option>").attr("value", '').text('Select'));

            $('Select[name="locationVillageID"] option').remove();
            $('Select[name="locationVillageID"]').append($("<option>").attr("value", '').text('Select'));
        }
        
           function getTehsilOnDist(obj) {
            var distId=obj.toString();            

            if(distId=='Select'){
                $('Select[name="tehsilID"] option').remove();
                $('Select[name="tehsilID"]').append($("<option>").attr("value", '').text('Select'));
            }else{
                $.ajax({
                    type: "POST",
                    url: "manageCustomerAction.do?option=getSubFieldDetails&getColName=tehsilId,tehsil&whereColName=districtId&colValue="+distId+"&orderBy=tehsil",
                    success: function(response){
                        if(response!=""){
                            var array = response.split("@");
                            $('Select[name="tehsilID"] option').remove();
                            $('Select[name="tehsilID"]').append($("<option>").attr("value", '').text('Select'));
                            for (i=0;i<array.length-1;i=i+2){
                                $('Select[name="tehsilID"]').append($("<option>").attr("value", array[i]).text(array[i+1]));
                            }
                        }else{
                            $('Select[name="tehsilID"] option').remove();
                            $('Select[name="tehsilID"]').append($("<option>").attr("value", '').text('Select'));
                        }
                    },
                    error: function(e){
                        alert('Error: ' + e);
                    }
                });
            }
            $('Select[name="blockID"] option').remove();
            $('Select[name="blockID"]').append($("<option>").attr("value", '').text('Select'));
            
            $('#Tehsil').val("");
            $('#CustomerBlock').val("");
            $('#Location').val("");

            $('#Tehsil').attr("readonly", false);
            $('#CustomerBlock').attr("readonly", false);
            $('#Location').attr("readonly", false);

            $('Select[name="locationVillageID"] option').remove();
            $('Select[name="locationVillageID"]').append($("<option>").attr("value", '').text('Select'));
            }

           function getBlockOnTehsil(obj) {
            var tehId=obj.toString();            
            
            if(tehId=='Select'){
                $('Select[name="blockID"] option').remove();
                $('Select[name="blockID"]').append($("<option>").attr("value", '').text('Select'));
            }else{
                $.ajax({
                    type: "POST",
                    url: "manageCustomerAction.do?option=getSubFieldDetails&getColName=blockId,block&whereColName=tehsilId&colValue="+tehId+"&orderBy=block",
                    success: function(response){
                        if(response!=""){
                            var array = response.split("@");
                            $('Select[name="blockID"] option').remove();
                            $('Select[name="blockID"]').append($("<option>").attr("value", '').text('Select'));
                            for (i=0;i<array.length-1;i=i+2){
                                $('Select[name="blockID"]').append($("<option>").attr("value", array[i]).text(array[i+1]));
                            }
                        }else{
                            $('Select[name="blockID"] option').remove();
                            $('Select[name="blockID"]').append($("<option>").attr("value", '').text('Select'));
                        }
                    },
                    error: function(e){
                        alert('Error: ' + e);
                    }
                });
            }
            $('Select[name="locationVillageID"] option').remove();
            $('Select[name="locationVillageID"]').append($("<option>").attr("value", '').text('Select'));
           
            $('#CustomerBlock').val('');           
            $('#Location').val("");
            $('#CustomerBlock').attr("readonly", false);
            $('#Location').attr("readonly", false);

        }

            function getVillageOnBlock(obj) {
            var vilId=obj.toString();            
            
            if(vilId=='Select'){
                $('Select[name="locationVillageID"] option').remove();
                $('Select[name="locationVillageID"]').append($("<option>").attr("value", '').text('Select'));
            }else{
                $.ajax({
                    type: "POST",
                    url: "manageCustomerAction.do?option=getSubFieldDetails&getColName=villageId,villageName&whereColName=blockId&colValue="+vilId+"&orderBy=villageName",
                    success: function(response){
                        if(response!=""){
                            var array = response.split("@");
                            $('Select[name="locationVillageID"] option').remove();
                            $('Select[name="locationVillageID"]').append($("<option>").attr("value", '').text('Select'));
                            for (i=0;i<array.length-1;i=i+2){
                                $('Select[name="locationVillageID"]').append($("<option>").attr("value", array[i]).text(array[i+1]));
                            }
                        }
                    },
                    error: function(e){
                        alert('Error: ' + e);
                    }
                });
            }
             $('#Location').val("");
             $('#Location').attr("readonly", false);
        }         
           
        </script>
    </head>
    <body>
        <div class="contentmain1">
            <div class="breadcrumb_container">
                <ul>
                    <li class="breadcrumb_link"><a href = '<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
                    <li class="breadcrumb_link"><a href = '<%=cntxpath%>/manageCustomerAction.do?option=initCustomerMasters'>MANAGE CUSTOMER</a></li>
                    <li class="breadcrumb_link">REGISTER / UPDATE CUSTOMER</li>

                </ul>
            </div>
            <br/>

            <div class="message" id="msg_saveFAILED">${message}</div>
            <div class="content_right1">
                <div class="con_slidediv1" style="position: relative;width: 100%">
                    <h1>REGISTER / UPDATE CUSTOMER </h1>
                    <table align="center" cellspacing=1 cellpadding=5 border=0 width=80% >
                        <tr bgcolor="#FFFFFF">
                            <td align="center" class="headingSpas">
                                <html:form action="manageCustomerAction.do?option=saveCustomer" styleId="custForm">
                                    <html:hidden property="customerId" styleId="customerId"/>
                                    <input type="hidden" name="<%= Constants.TOKEN_KEY%>" value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" />
                                    <table cellspacing=1 cellpadding=5 border=0 width=100% >
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas">Customer Category <span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas" >
                                                <html:select property="custCategoryID" styleId="Customer Category" styleClass="headingSpas"  onchange="getStandardDiscount(this.value);" style="width:120px!important">
                                                    <html:option value="">Select</html:option>
                                                    <html:optionsCollection property="customerCatgryList" name="customerForm" label="label" value="value"/>
                                                </html:select>
                                            </td>
                                            <td align="right" class="headingSpas">Customer Code <span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas">
                                                <html:text property="customerCode" styleId="Customer Code" styleClass="headingSpas" maxlength="49" />
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas">Customer Name <span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas">
                                                <html:text property="customerName" styleId="Customer Name" styleClass="headingSpas" maxlength="149" />
                                            </td>
                                            <td align="right" class="headingSpas">Contact Person <span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas">
                                                <html:text property="contactPerson" styleId="Contact Person" maxlength="149" styleClass="headingSpas"  onblur="this.value=TrimAll(this.value)"/>
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                             <td align="right" class="headingSpas">Contact No. <span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas">
                                                <html:text property="contactMobile" styleId="Contact No." maxlength="11" onblur="this.value=TrimAll(this.value)" />
                                            </td>

                                            <td align="right" class="headingSpas">E-Mail</td>
                                            <td align="left" class="headingSpas">
                                                <html:text property="contactEmail" styleId="E-Mail" maxlength="99" styleClass="headingSpas" onblur="this.value=TrimAll(this.value)" />
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas">Country</td>
                                            <td align="left" class="headingSpas">
                                                <html:select property="countryID" styleId="Country Id" styleClass="headingSpas" onchange="commonValidate();getStateOnCountry(this.value);" style="width:96px!important">
                                                <html:option value="">Select</html:option>
                                                    <html:optionsCollection property="countryList" name="customerForm" label="label" value="value"/>
                                                </html:select>
                                                    <html:text property="customerCountry" styleId="Country" maxlength="49" styleClass="headingSpas" onblur="this.value=TrimAll(this.value)" />
                                            </td>
                                            <td align="right" class="headingSpas">State</td>
                                            <td align="left" class="headingSpas">
                                                <html:select property="stateID" styleId="State Id" styleClass="headingSpas" onchange="commonValidate();getDistOnState(this.value);" style="width:96px!important">
                                                    <html:option value="">Select</html:option>
                                                    <html:optionsCollection property="stateList" name="customerForm" label="label" value="value"/>
                                                </html:select>
                                                <html:text property="customerState" styleId="State" maxlength="49" styleClass="headingSpas" onblur="this.value=TrimAll(this.value)" />
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas">District</td>
                                            <td align="left" class="headingSpas">
                                                <html:select property="districtID" styleId="District Id"  styleClass="headingSpas" onchange="commonValidate();getTehsilOnDist(this.value)" style="width:96px!important">
                                                    <html:option value="">Select</html:option>
                                                   <html:optionsCollection property="districtList" name="customerForm" label="label" value="value"/>
                                                </html:select>
                                                <html:text property="customerDistrict" styleId="District" maxlength="199" styleClass="headingSpas" onblur="this.value=TrimAll(this.value)" />
                                            </td>
                                            <td align="right" class="headingSpas">Tehsil</td>
                                            <td align="left" class="headingSpas">
                                                 <html:select property="tehsilID" styleId="Tehsil Id" styleClass="headingSpas" onchange="commonValidate();getBlockOnTehsil(this.value);" style="width:96px!important">
                                                    <html:option value="">Select</html:option>
                                                    <html:optionsCollection property="tehsilList" name="customerForm" label="label" value="value"/>
                                                </html:select>
                                                <html:text property="customerTehsil" styleId="Tehsil" maxlength="199" styleClass="headingSpas" onblur="this.value=TrimAll(this.value)" />
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas">Block</td>
                                            <td align="left" class="headingSpas">
                                                <html:select property="blockID" styleId="Customer Block Id" styleClass="headingSpas" onchange="commonValidate();getVillageOnBlock(this.value);" style="width:96px!important">
                                                    <html:option value="">Select</html:option>
                                                    <html:optionsCollection property="blockList" name="customerForm" label="label" value="value"/>
                                                </html:select>
                                                <html:text property="customerBlock" styleId="CustomerBlock" maxlength="199" styleClass="headingSpas" onblur="this.value=TrimAll(this.value)" />
                                            </td>
                                            <td align="right" class="headingSpas">Location / Village <span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas">
                                                <html:select property="locationVillageID" styleId="Location / Village" styleClass="headingSpas" onchange="commonValidate()" style="width:96px!important">
                                                    <html:option value="">Select</html:option>
                                                    <html:optionsCollection property="villageList" name="customerForm" label="label" value="value"/>
                                                </html:select>
                                                <html:text property="customerLocation" styleId="Location" maxlength="199" styleClass="headingSpas" onblur="this.value=TrimAll(this.value)" />
                                            </td>
                                        </tr>
                                        
                                        <tr bgcolor="#FFFFFF">
                                           <td align="right" class="headingSpas">Pin Code</td>
                                            <td align="left" class="headingSpas">
                                                <html:text property="pincode" styleId="Pin Code" maxlength="10" styleClass="headingSpas" onblur="this.value=TrimAll(this.value)" />
                                            </td>
                                            <td align="right" class="headingSpas">Date of Birth</td>
                                            <td align="left" class="headingSpas">
                                                <html:text property="contactDOB" styleId="Date of Birth" onblur="checkDate(this);" styleClass="datepicker"   maxlength="14"/>&nbsp;(dd/mm/yyyy)
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas">PAN No.</td>
                                            <td align="left" class="headingSpas">
                                                <html:text property="panNo" styleId="PAN No" maxlength="49"  onblur="this.value=TrimAll(this.value)" />
                                            </td>
                                            <td align="right" class="headingSpas">TIN No.</td>
                                            <td align="left" class="headingSpas" colspan="2">
                                                <html:text property="tinNo" styleId="TIN No" maxlength="49"  onblur="this.value=TrimAll(this.value)" />
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas">Target <span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas">
                                                <html:text property="customerTarget" styleId="Target" maxlength="10"  onblur="this.value=TrimAll(this.value)" />
                                            </td>
                                            <td align="right" class="headingSpas">Credit Limit <span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas">
                                                <html:text property="creditLimit" styleId="Credit Limit" maxlength="10" onblur="this.value=TrimAll(this.value)"  />
                                            </td>
                                        </tr>
                                       
                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas">Standard Discount %</td>
                                            <td align="left" class="headingSpas" id="standardDiscount">&nbsp;0
                                                <%--<html:text property="discountPercentage" styleId="Discount Percentage" maxlength="30" value="0.0"/>--%>
                                            </td>
                                            <td align="right" class="headingSpas">Additional Discount % <span class="mandatory">*</span></td>
                                            <td align="left" class="headingSpas">
                                                <html:text property="discountPercentage" styleId="Additional Discount Percentage" maxlength="10" onblur="this.value=TrimAll(this.value)"/>
                                            </td>
                                        </tr>
                                         <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas">Transporter in Use</td>
                                            <td align="left" class="headingSpas" >
                                                <html:text property="transporterInUse" styleId="Transporter in Use" maxlength="49" onblur="this.value=TrimAll(this.value)" />
                                            </td>
                                            <td align="right" class="headingSpas"   ><span id="sonalikaLable" style="display: none">Sonalika ID</span></td>
                                            <td align="left" class="headingSpas" >
                                                <span id="sonalikaValue"  style="display: none">
                                                    <html:text property="tms_CustID" styleId="Sonalika ID" maxlength="49" onblur="this.value=TrimAll(this.value)"  readonly="true"/>
                                                </span>
                                            </td>
                                        </tr>
                                         <tr bgcolor="#FFFFFF">
                                            <td align="right" class="headingSpas" ><bean:message key="label.common.gstNo" /></td>
                                            <td align="left" class="headingSpas" colspan="3">
                                                <html:text property="gstNo" styleId="GST No" maxlength="49"  onblur="this.value=TrimAll(this.value)" />
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
            <script>
                if(document.forms[0].custCategoryID!=undefined && !document.forms[0].custCategoryID!=null){
                var custCategoryID=document.forms[0].custCategoryID.value;
                if(document.forms[0].custCategoryID.value!=''){
                    document.getElementById("sonalikaLable").style.display = "block";
                    document.getElementById("sonalikaValue").style.display = "block";
                }
                if(custCategoryID=="4"){
                    document.forms[0].custCategoryID.readOnly=true;
                    document.forms[0].customerCode.readOnly=true;
                    document.forms[0].customerName.readOnly=true;
                }
                    getStandardDiscount(custCategoryID);
                }
                var tempStr;
                tempStr=document.forms[0].locationVillageID.options[document.forms[0].locationVillageID.selectedIndex].innerHTML;
                var locationVillageID=tempStr.replace("&amp;" ,"&");
                if(document.forms[0].locationVillageID.value!=""){
                    document.forms[0].customerLocation.value=locationVillageID;
                    document.forms[0].customerLocation.readOnly=true;
                }else{
                    document.forms[0].customerLocation.readOnly=false;
                }
                tempStr=document.forms[0].countryID.options[document.forms[0].countryID.selectedIndex].innerHTML;
                var countryID=tempStr.replace("&amp;" ,"&");
                if(document.forms[0].countryID.value!=""){
                    document.forms[0].customerCountry.value=countryID;
                    document.forms[0].customerCountry.readOnly=true;
                }else{
                    document.forms[0].customerCountry.readOnly=false;
                }

                tempStr=document.forms[0].stateID.options[document.forms[0].stateID.selectedIndex].innerHTML
                var stateID=tempStr.replace("&amp;" ,"&");
                if(document.forms[0].stateID.value!=""){
                    document.forms[0].customerState.value=stateID;
                    document.forms[0].customerState.readOnly=true;
                }else{
                    document.forms[0].customerState.readOnly=false;
                }
                
                tempStr=document.forms[0].districtID.options[document.forms[0].districtID.selectedIndex].innerHTML;
                var districtID=tempStr.replace("&amp;" ,"&");
                if(document.forms[0].districtID.value!=""){
                    document.forms[0].customerDistrict.value=districtID;
                    document.forms[0].customerDistrict.readOnly=true;
                }else{
                    document.forms[0].customerDistrict.readOnly=false;
                }
                tempStr=document.forms[0].tehsilID.options[document.forms[0].tehsilID.selectedIndex].innerHTML;
                var tehsilID=tempStr.replace("&amp;" ,"&");
                if(document.forms[0].tehsilID.value!=""){
                    document.forms[0].customerTehsil.value=tehsilID;
                    document.forms[0].customerTehsil.readOnly=true;
                }else{
                    document.forms[0].customerTehsil.readOnly=false;
                }
                tempStr=document.forms[0].blockID.options[document.forms[0].blockID.selectedIndex].innerHTML;
                var blockID=tempStr.replace("&amp;" ,"&");
                if(document.forms[0].blockID.value!=""){
                    document.forms[0].customerBlock.value=blockID;
                    document.forms[0].customerBlock.readOnly=true;
                }else{
                    document.forms[0].customerBlock.readOnly=false;
                }       

          </script>
    </body>
</html>
