<%--
    Document   : createNewOrder
    Created on : 31-Dec-2014, 11:31:18
    Author     : kundan.rastogi
--%>


<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*,dao.LoginDAO,beans.serviceForm" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            String dealerCode = (String) session.getAttribute("dealerCode");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String dealerFlag = "";
            if (!userFunctionalities.contains("101")) {
                dealerFlag = "true";
            }

%>
<c:set var="dCode" value='<%=dealerCode%>'/>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_6.js"></script>
<script src="${pageContext.request.contextPath}/js/intermediate.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
    function expValidatedVOR(str) {
        var regularExpression = /^[ A-Za-z0-9_@./#&+-]*$/;
        var valid = regularExpression.test(str);
        return valid;
    }
    function expValidatedVORNEW(str) {
        var regularExpression = /^[a-zA-Z0-9\-_]{0,40}$/;
        var valid = regularExpression.test(str);
        return valid;
    }
    function expValidatedVORModelNO(str) {
        var regularExpression = /^[ A-Za-z0-9_&()-:,]*$/;     //-,()&:
        var valid = regularExpression.test(str);
        return valid;
    }
    function expValidatedVORJobCartNO(str) {
        var regularExpression = /^[a-zA-Z0-9\-_-]{0,40}$/;     //-,()&:/^[a-zA-Z0-9-_]+$/;
        var valid = regularExpression.test(str);
        return valid;
    }
    function expValidatedSTD(str) {
        var regularExpression = /^[ A-Za-z0-9_@./#&+-]*$/;
        var valid = regularExpression.test(str);
        return valid;
    }
    function validateExcel()
    {

        if(document.getElementById("Delivery").value==""){
            alert(not_blank_dropdown_validation_msg+' Mode of Dispatch ');
            document.getElementById("Delivery").focus();
            return false;
        }
    <%--if(document.getElementById("Dealer Ref No").value==""){
        alert(not_blank_dropdown_validation_msg+'Customer Reference No.');
        document.getElementById("Dealer Ref No").focus();
        return false;
    }else{
        if(!expValidatedSTD(document.getElementById("Dealer Ref No").value)){
            alert(validation_msg+' Customer Reference No.');
            document.getElementById("Dealer Ref No").focus();
            return false;
        }
    }--%>
    <c:if test="${odType eq 'VOR'}">

            if(document.getElementById("Fir No").value==""){
                alert(not_blank_validation_msg+' Job Card No');
                document.getElementById("Fir No").focus();
                return false;
            }else{
                if(!expValidatedVORJobCartNO(document.getElementById("Fir No").value)){
                    alert(validation_msg+' Job Card No.');
                    document.getElementById("Fir No").focus();
                    return false;
                }
            }
            if(document.getElementById("Chassis No").value==""){
                alert(not_blank_validation_msg+' Chassis No');
                document.getElementById("Chassis No").focus();
                return false;
            }else{
                if(!expValidatedVORNEW(document.getElementById("Chassis No").value)){
                    alert(validation_msg+' Chassis No');
                    document.getElementById("Chassis No").focus();
                    return false;
                }
            }
            if(document.getElementById("Model NO").value==""){
                alert(not_blank_validation_msg+' Model No');
                document.getElementById("Model NO").focus();
                return false;
            }else{
                if(!expValidatedVORModelNO(document.getElementById("Model NO").value)){
                    alert(not_blank_validation_msg+' Model No');
                    document.getElementById("Model NO").focus();
                    return false;
                }
            }
            if(document.getElementById("Tractor Engine No").value==""){
                alert(not_blank_validation_msg+' Tractor Engine No');
                document.getElementById("Tractor Engine No").focus();
                return false;
            }else{
                if(!expValidatedVORNEW(document.getElementById("Tractor Engine No").value)){
                    alert(validation_msg+' Tractor Engine No');
                    document.getElementById("Tractor Engine No").focus();
                    return false;
                }
            }

    </c:if>
            if(document.getElementById("Consignee Name").value==""){
                alert(not_blank_dropdown_validation_msg+'Consignee Name.');
                document.getElementById("Consignee Name").focus();
                return false;
            }
            if(document.getElementById("Payment Terms").value==""){
                alert(not_blank_dropdown_validation_msg+'Payment Terms');
                document.getElementById("Payment Terms").focus();
                return false;
            }
            if(document.getElementById("Inco Terms").value==""){
                alert(not_blank_dropdown_validation_msg+'Delivery Terms');
                document.getElementById("Inco Terms").focus();
                return false;
            }
            if(document.getElementById("Discharge Port").value==""){
                alert(not_blank_dropdown_validation_msg+'Port of Discharge');
                document.getElementById("Discharge Port").focus();
                return false;
            }
            if(document.getElementById("Destination Place").value==""){
                alert(not_blank_dropdown_validation_msg+'Destination Place');
                document.getElementById("Destination Place").focus();
                return false;
            }else{
                if(!expValidatedSTD(document.getElementById("Destination Place").value)){
                    alert(validation_msg+' Destination Place');
                    document.getElementById("Destination Place").focus();
                    return false;
                }
            }
            
    <%--if(document.getElementById("Pre Carriage By").value==""){
        alert(not_blank_dropdown_validation_msg+'Pre Carriage By.');
        document.getElementById("Pre Carriage By").focus();
        return false;
    }else{--%>
            if(document.getElementById("Pre Carriage By").value!="") {
                if(!expValidatedSTD(document.getElementById("Pre Carriage By").value)){
                    alert(validation_msg+' Pre Carriage By');
                    document.getElementById("Pre Carriage By").focus();
                    return false;
                }
            }
    <%-- if(document.getElementById("Place Pre Carrier").value==""){
         alert(not_blank_dropdown_validation_msg+'Place Pre Carrier');
         document.getElementById("Place Pre Carrier").focus();
         return false;
     }else{--%>
             if(document.getElementById("Place Pre Carrier").value!="") {
                 if(!expValidatedSTD(document.getElementById("Place Pre Carrier").value)){
                     alert(validation_msg+' Place Pre Carrier');
                     document.getElementById("Place Pre Carrier").focus();
                     return false;
                 }
             }
    <%--if(document.getElementById("SpecialIns").value==""){
        alert(not_blank_dropdown_validation_msg+'Special Instructions');
        document.getElementById("SpecialIns").focus();
        return false;
    }else{
        if(!expValidatedSTD(document.getElementById("SpecialIns").value)){
            alert(validation_msg+' Special Instructions');
            document.getElementById("SpecialIns").focus();
            return false;
        }
    }--%>
    

    
           
            if( document.getElementById("ByExcelUpload").checked){
                var filePath= document.getElementById("excelFile").value;
                if(filePath == '')
                {
                    alert('Please Browse Excel  File.');
                    return false;
                }
    <%--else if(filePath.indexOf(':\\') ==-1 || filePath.indexOf(':\\')==0)
    {
        alert('Invalid Excel File. Please Browse only .xls file.');
        return false;
    }--%>
                else if(filePath.indexOf('.xls') != (filePath.length-4) ) //&& filePath.indexOf('.xlsx') != (filePath.length-5)
                {
                    alert('Invalid Excel File. Please Browse only .xls file.');
                    return false;
                }
            }
           
    <%-- var filePath= document.getElementById("excelDocumentFile").value;
     if(filePath == '')
     {
         alert('Please Browse Excel  File.');
         return false;
     }
     else if(filePath.indexOf(':\\') ==-1 || filePath.indexOf(':\\')==0)
     {
         alert('Invalid Excel File. Please Browse only .xls file.');
         return false;
     }
     else if(filePath.indexOf('.xls') != (filePath.length-4) ) //&& filePath.indexOf('.xlsx') != (filePath.length-5)
     {
         alert('Invalid Excel File. Please Browse only .xls file.');
         return false;
     }--%>
            
             SpacialInsTrim();
             document.form1.action="<%=cntxpath%>/inventoryEXPAction.do?option=addNewOrder";
             document.form1.submit();
         }

         function makeEnable(val){

             if(val.split("@@")[0]=='VOR'){
                 document.getElementById("orderSpan").style.display='block';
             }
             else{
                 document.getElementById("orderSpan").style.display='none';
             }
         }

         function selectPreviousRadio(){
             document.getElementById("PreviousPO").checked ='true';
         }
        
         function GetXmlHttpObject()
         {
             var objXmlHttp = null;
             if (navigator.userAgent.indexOf('Opera') >= 0)
             {
                 var xmlHttp=new XMLHttpRequest();
                 return xmlHttp;
             }
             if (navigator.userAgent.indexOf('MSIE') >= 0)
             {
                 var strName = 'Msxml2.XMLHTTP';
                 if (navigator.appVersion.indexOf('MSIE 5.5') >=0)
                 {
                     strName = 'Microsoft.XMLHTTP';
                 }
                 try
                 {
                     objXmlHttp = new ActiveXObject(strName);
                     // objXmlHttp.onreadystatechange = handler;
                     return objXmlHttp;
                 }
                 catch(e)
                 {
                     alert('Your Browser Does Not Support Ajax');
                     return;
                 }
             }
             if (navigator.userAgent.indexOf('Mozilla') >= 0)
             {
                 objXmlHttp = new XMLHttpRequest();
                 return objXmlHttp;
             }
         }

         function caMaxLength(ca, MaxLen)
         {
             if(ca.value.length > MaxLen){
                 alert("Special Instructions can not be more than "+MaxLen+" characters.");
                 ca.value=ca.value.substring(0,100);ca.focus();
                 return false;
             }else{
                 return (ca.value.length <= MaxLen);
             }
         }
         function SpacialInsTrim()
         {
             $('.spacialInsTrim').each(function(idx,val){
                 val.value = val.value.replace(/\s+/g, ' ');
             });

         }
         function getdischargePort(deliveryCode,dischargePort,consigneeCountry)
         {
                
             var url = "<%=cntxpath%>/inventoryEXPAction.do?option=getdischargePortBydeliveryCode&deliveryCode=" + deliveryCode+"&dischargePort=" + dischargePort+"&consigneeCountry=" + consigneeCountry+"&tm="+new Date().getTime() ;
             xmlHttp = GetXmlHttpObject();
             xmlHttp.onreadystatechange = function()
             {
                 if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                 {
                     res = trimS(xmlHttp.responseText);
                     document.getElementById('Discharge PortId').innerHTML= res;
                    
                 }
             };
             xmlHttp.open("GET", url, true);
             xmlHttp.send(null);
             return false;
                 
         }
         function getConsigneeDetailByConsigneeName(consigneeName)
         {
             
             if(consigneeName==""){
                 document.getElementById("Consignee Address").value="";
                 document.getElementById("Consignee Country").value="";
                 document.getElementById("Destination Place").value="";
                 document.getElementById("Consignee Code").value="";
                 document.getElementById("con_country_name").value="";
                 document.getElementById("Consignee Country Name").value="";
             }else{
                 var url = "<%=cntxpath%>/inventoryEXPAction.do?option=getConsigneeDetailByConsigneeName&dealerCode="+consigneeName+"&tm="+new Date().getTime() ;
                 xmlHttp = GetXmlHttpObject();
                 ajaxindicatorstart('loading data.. please wait..');
                 xmlHttp.onreadystatechange = function()
                 {
                     if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                     {
                        
                         var res = trimS(xmlHttp.responseText);
                         if(res!='' && res.indexOf('@@') !=-1){
                             var result= res.split("@@");
                             document.getElementById("Consignee Address").value=result[0];
                             document.getElementById("Consignee Country").value=result[1];
                             document.getElementById("Destination Place").value=result[2];
                             document.getElementById("Consignee Code").value=result[3];
                             document.getElementById("con_country_name").value=result[4];
                             document.getElementById("Consignee Country Name").value=result[1]+ " [ " +result[4]+ " ] ";
                         }else{
                             document.getElementById("Consignee Address").value="";
                             document.getElementById("Consignee Country").value="";
                             document.getElementById("Destination Place").value="";
                             document.getElementById("Consignee Code").value="";
                             document.getElementById("con_country_name").value="";
                             document.getElementById("Consignee Country Name").value="";
                         }

                         getdischargePort(document.getElementById("Delivery").value,document.getElementById("Discharge Port").value,document.getElementById("Consignee Country").value);

                     }
                 };
                 
                 xmlHttp.open("GET", url, true);
                 xmlHttp.send(null);
                 ajaxindicatorstop();
                 return false;
                 
             }
         }

</script>

<!doctype html>
<html lang="en">
    <head>  <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >

        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
        <script>

            $(document).ready(function(){

                $("#datepicker1").attr("readonly",true).datepicker({ minDate: 'getDate',dateFormat: "dd/mm/yy"});
                $('#datepicker1').attr("readonly",true).datepicker("setDate", "getDate()");
                  
            });

            $(document).ready(function(){
                $('#excelFile').click(function(){
                    $('#ByExcelUpload').prop("checked",true);
                });
            });


        </script>
    </head>
    <body>
        <c:set var="buyerList" value="${fn:split(buyerDetail,'@@')}"/>
        <div class="contentmain1">
            <div class="breadcrumb_container">
                <ul class="hText">
                    <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a><a href='${pageContext.request.contextPath}/inventoryEXPAction.do?option=initCreateEXPOrder'><bean:message key="label.spare.initCreateOrderEXP" /></a></li>
                    <c:if test="${odType eq 'STD'}">
                        <li><bean:message key="label.spare.createNewOrderEXP" />  </li>
                    </c:if>
                    <c:if test="${odType eq 'VOR'}">
                        <li><bean:message key="label.spare.createNewOrderVOREXP" />  </li>
                    </c:if>

                </ul>
            </div>
            <div class="message" id="msg_saveFAILED"></div>
            <center>
                <div class="content_right1">
                    <div class="con_slidediv1" style="position: relative;width: 100%">
                        <h1 class="hText">
                            <c:if test="${odType eq 'STD'}">
                                <bean:message key="label.spare.createNewOrderEXP" />
                            </c:if>
                            <c:if test="${odType eq 'VOR'}">
                                <bean:message key="label.spare.createNewOrderVOREXP" />
                            </c:if>
                        </h1>
                        <form name="form1" id="form1" method="post" onsubmit="return check_method_select();" ENCTYPE="multipart/form-data">
                            <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryEXPAction.do?option=initCreateEXPOrder'><bean:message key='label.spare.initCreateOrderEXP'/></a>@@<bean:message key="label.spare.createNewOrderEXP" />"/>
                            <input type="hidden" name="con_country_name" id="con_country_name" value=""/>
                            <input type="hidden" name="prevPoNo" id="prevPoNo" value="${inventoryForm.prevPoNo}"/>
                            <input type="hidden" name="poNoRadio" id="poNoRadio" value="${inventoryForm.poNoRadio}"/>
                            <table width="100%" border="0" cellspacing="2" cellpadding="2" align="left" class="LiteBorder">

                                <tr>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                        <bean:message key="label.spare.orderType" /><span class="mandatory">*</span> :
                                    </td>
                                    <td width="20%" align="left">
                                        ${odType}
                                        <input type="hidden" name="orderType" value="${odType}" id="OrderType">
                                    </td>

                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                        <c:if test="${poNoRadio eq 'newPO'}">
                                            <input type="hidden" name="poNoRadio" value="newPO" >
                                            <input type="hidden" name="refNo" value="${refNo}">
                                            <bean:message key="label.spare.newOrder" /><span class="mandatory">*</span> :
                                        </c:if>
                                        <c:if test="${poNoRadio eq 'oldPO'}">
                                            <input type="hidden" name="poNoRadio" value="oldPO" >
                                            <input type="hidden" name="prevPoNo" value="${refNo}">
                                            <input type="hidden" name="documentName" value="${inventoryForm.documentName}">
                                            <input type="hidden" name="dischargePort" value="${inventoryForm.dischargePort}">
                                            <bean:message key="label.spare.addPrevPoNo" /><span class="mandatory">*</span> :
                                        </c:if>
                                    </td>
                                    <td width="20%" align="left">
                                        <input type="hidden" name="newPoNo" value="">
                                        ${refNo}


                                    </td>
                                </tr>
                                <tr>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                        <bean:message key="label.common.modeOfDispatch" /><span class="mandatory">*</span> :
                                    </td>
                                    <td width="20%" align="left">
                                        <select id="Delivery" name="deliveryCode" class="headingSpas"   onchange='getdischargePort(this.value,"",document.getElementById("Consignee Country").value)' style="width:180px !important; margin:0px!important">
                                            <option value="" >--Select Here--</option>
                                            <c:forEach items="${deliveryTermsList}" var="deliveryTermsList">
                                                <c:if test="${inventoryForm.deliveryTerms eq deliveryTermsList.value}">
                                                    <option value='${deliveryTermsList.value}' title='${deliveryTermsList.label}' selected>${deliveryTermsList.label} </option>
                                                </c:if>
                                                <c:if test="${inventoryForm.deliveryTerms ne deliveryTermsList.value}">
                                                    <option value='${deliveryTermsList.value}' title='${deliveryTermsList.label}' >${deliveryTermsList.label} </option>
                                                </c:if>
                                            </c:forEach>

                                        </select>
                                        <script type="text/javascript" language="javascript">getdischargePort('${inventoryForm.deliveryTerms}','${inventoryForm.dischargePort}','${inventoryForm.consigneeCountry}')</script>
                                    </td>
                                    <td  width="30%" align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                        <bean:message key="label.spare.dealerRefNo" /> :
                                    </td>
                                    <td width="30%" align="left">
                                        <input type="text" class="spacialInsTrim" name="dealerRefNo"  id="Dealer Ref No" value="${inventoryForm.dealerRefNo}" maxlength="65"  style="width:150px; margin:0px!important;" onblur="this.value=TrimAll(this.value)"/>
                                    </td>
                                </tr>
                                <c:if test="${odType eq 'VOR'}">
                                    <tr>
                                        <td  align="right" class="headingSpas" style="padding-right:10px">
                                            <bean:message key="label.newOrder.firNo" /><span class="mandatory">*</span> :
                                        </td>
                                        <td  align="left" class="headingSpas" style="padding-right:10px">
                                            <input type="text" id="Fir No" name="firNo" maxlength="50" value="${inventoryForm.firNo}" style="width:90px; margin:1px!important;" >
                                        </td>
                                        <td  align="right" class="headingSpas" nowrap style="padding-right:10px">
                                            <bean:message key="label.catalogue.chassisNo" /><span class="mandatory">*</span>  :
                                        </td>
                                        <td align="left" style="padding-right:10px">
                                            <input type="text" id="Chassis No" maxlength="50" name="chassisNo" value="${inventoryForm.chassisNo}" style="width:90px; margin:1px!important;" >       <!-- onkeydown="return false;" -->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                            <bean:message key="label.catalogue.model" /><span class="mandatory">*</span> :
                                        </td>
                                        <td align="left" style="padding-left:1px" class="headingSpas">
                                            <input type="text" id="Model NO" name="modelNo" maxlength="50" value="${inventoryForm.modelNo}" style="width:90px; margin:1px!important;">
                                        </td>
                                        <td align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                            <bean:message key="label.catalogue.engineNo" /><span class="mandatory">*</span> :
                                        </td>
                                        <td align="left" style="padding-right:10px">
                                            <input  type="text" id="Tractor Engine No" name="engineNo" maxlength="50" value="${inventoryForm.engineNo}" style="width:90px; margin:1px!important;">
                                        </td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td width="20%" align="right" class="headingSpas" nowrap style="padding-right: 10px;" >
                                        <bean:message key="label.spare.buyerName" /><span class="mandatory">*</span> :
                                    </td>
                                    <td width="20%" align="left" class="headingSpas" nowrap  >
                                        <input type="hidden" name="dealerCode" value="${buyerList[3]}"/>
                                        <input type="hidden" name="dealerName" value="${buyerList[2]}"/>
                                        <input type="text" name="buyerName" id="Buyer Name" value="${buyerList[3]} [${buyerList[2]}]"  style="width:150px; margin:0px!important;" onblur="this.value=TrimAll(this.value)"  readonly maxlength="100" >
                                    </td>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px;" >
                                        <bean:message key="label.spare.consigneeName" /><span class="mandatory">*</span> :
                                    </td>
                                    <td width="20%" align="left" class="headingSpas" nowrap style="padding-right:10px" >
                                        <select id="Consignee Name" name="consigneeName" class="headingSpas" onchange='getConsigneeDetailByConsigneeName(this.value);'  style="width:180px !important; margin:0px!important">
                                            <option value="" >--Select Here--</option>
                                            <c:if test="${poNoRadio ne 'oldPO'}">
                                               <%-- <c:forEach items="${consigneeList}" var="consigneeList">--%>
                                                    <%--<c:if test="${buyerList[3] eq consigneeList.value}">--%>
                                                        <option value='${buyerList[3]}' title='${buyerList[3]} [${buyerList[2]}]' selected>${buyerList[3]} [${buyerList[2]}]</option>
                                                    <%--</c:if>--%>
                                                    <%--<c:if test="${buyerList[3] ne consigneeList.value}">
                                                        <option value='${consigneeList.value}' title='${consigneeList.value} [${consigneeList.label}]'>${consigneeList.value} [${consigneeList.label}] </option>
                                                    </c:if>--%>
                                                <%--</c:forEach>--%>
                                            </c:if>
                                            <c:if test="${poNoRadio eq 'oldPO'}">
                                                <option value='${buyerList[3]}' title='${buyerList[3]} [${buyerList[2]}]' selected>${buyerList[3]} [${buyerList[2]}]</option>

                                                <%--<c:forEach items="${consigneeList}" var="consigneeList">
                                                    <c:if test="${inventoryForm.consigneeCode eq consigneeList.value}">
                                                        <option value='${consigneeList.value}' title='${consigneeList.value} [${consigneeList.label}]' selected>${consigneeList.value} [${consigneeList.label}] </option>
                                                    </c:if>
                                                    <c:if test="${inventoryForm.consigneeCode ne consigneeList.value}">
                                                        <option value='${consigneeList.value}' title='${consigneeList.value} [${consigneeList.label}]'>${consigneeList.value} [${consigneeList.label}] </option>
                                                    </c:if>
                                                </c:forEach>--%>
                                            </c:if>
                                        </select>
                                        <script type="text/javascript" language="javascript">getConsigneeDetailByConsigneeName('${buyerList[3]}')</script>
                                        <input type="hidden" name="consigneeCode" id="Consignee Code" value=""/>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" align="right" class="headingSpas" nowrap style="padding-right: 10px;" >
                                        <bean:message key="label.spare.buyerAddress" /><span class="mandatory">*</span> :
                                    </td>
                                    <td width="20%" align="left" class="headingSpas" nowrap  >
                                        <textarea  name="buyerAddress" id="Buyer Address" readonly rows="2" cols="3"  style="width:220px;margin:0px!important" >${buyerList[0]}</textarea>
                                    </td>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px;" >
                                        <bean:message key="label.spare.consigneeAddEXP" /><span class="mandatory">*</span> :
                                    </td>
                                    <td width="20%" align="left" class="headingSpas" nowrap style="padding-right:10px" >
                                        <textarea name="consigneeAddress" id="Consignee Address" readonly rows="2" cols="3"  style="width:220px;margin:0px!important">${inventoryForm.consigneeAddress}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px;" >
                                        <bean:message key="label.spare.buyerCountry" /><span class="mandatory">*</span> :
                                    </td>
                                    <td width="20%" align="left" class="headingSpas" nowrap style="padding-right:10px" ><input type="hidden" name="buyerCountry"  value="${buyerList[4]}"/>
                                        <input type="text" name="buyerCountry" id="Buyer Country" readonly value="${buyerList[1]} [ ${buyerList[4]} ]"  style="width:150px; margin:0px!important;" onblur="this.value=TrimAll(this.value)"  maxlength="15" >
                                    </td>
                                    <td width="20%" align="right" class="headingSpas" nowrap style="padding-right: 10px;" >
                                        <bean:message key="label.spare.consigneeCountry" /><span class="mandatory">*</span> :
                                    </td>
                                    <td width="20%" align="left" class="headingSpas" nowrap  > <input type="hidden" name="consigneeCountry" id="Consignee Country" value="${inventoryForm.consigneeCountry}">
                                        <input type="text" name="consigneeCountryName" id="Consignee Country Name" readonly value="${inventoryForm.consigneeCountry}" style="width:150px; margin:0px!important;" onblur="this.value=TrimAll(this.value)" onblur="this.value=TrimAll(this.value)"  maxlength="15" >

                                    </td>
                                </tr>
                                <tr>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                        <bean:message key="label.spare.paymentTerms" /><span class="mandatory">*</span> :
                                    </td>
                                    <td width="20%" align="left">
                                        <select id="Payment Terms" name="paymentTerms" class="headingSpas" style="width:180px !important; margin:0px!important">
                                            <option value="" >--Select Here--</option>
                                            <c:forEach items="${paymentTermsList}" var="paymentTermsList">
                                                <c:if test="${inventoryForm.paymentTerms eq paymentTermsList.value}">
                                                    <option value='${paymentTermsList.value}' title='${paymentTermsList.label}' selected>${paymentTermsList.label} </option>
                                                </c:if>
                                                <c:if test="${inventoryForm.paymentTerms ne paymentTermsList.value}">
                                                    <option value='${paymentTermsList.value}' title='${paymentTermsList.label}'>${paymentTermsList.label} </option>
                                                </c:if>
                                            </c:forEach>

                                        </select>
                                    </td>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                        <bean:message key="label.spare.dischargePort" /><span class="mandatory">*</span> :
                                    </td>
                                    <td width="20%" align="left">
                                        <span id="Discharge PortId" >
                                            <select id="Discharge Port" name="dischargePort" class="headingSpas" style="width:180px !important; margin:0px!important">
                                                <option value="" >--Select Here--</option>
                                                <c:set var="list" value='${result}'/>
                                                <c:forEach items="${list}" var="dataList">
                                                    <c:if test="${inventoryForm.dischargePort eq dataList.value}">
                                                        <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>
                                                    </c:if>
                                                    <c:if test="${inventoryForm.dischargePort ne dataList.value}">
                                                        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                                    </c:if>
                                                </c:forEach>

                                            </select>
                                        </span>
                                    </td>

                                </tr>
                                <tr>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                        <bean:message key="label.spare.incoTerms" /><span class="mandatory">*</span> :
                                    </td>
                                    <td width="20%" align="left">
                                        <select id="Inco Terms" name="incoTerms" class="headingSpas" style="width:180px !important; margin:0px!important">
                                            <option value="" >--Select Here--</option>
                                            <c:forEach items="${incoTermsList}" var="incoTermsList">
                                                <c:if test="${inventoryForm.incoTerms eq incoTermsList.value}">
                                                    <option value='${incoTermsList.value}' title='${incoTermsList.label}' selected>${incoTermsList.label} </option>
                                                </c:if>
                                                <c:if test="${inventoryForm.incoTerms ne incoTermsList.value}">
                                                    <option value='${incoTermsList.value}' title='${incoTermsList.label}'>${incoTermsList.label} </option>
                                                </c:if>
                                            </c:forEach>

                                        </select>
                                    </td>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right: 10px;" >
                                        <bean:message key="label.spare.destinationPlace" /><span class="mandatory">*</span> :
                                    </td>
                                    <td   width="20%" align="left" class="headingSpas"  style="padding-right:10px ; margin:0px!important;" >
                                        <input type="text" class="spacialInsTrim" name="destinationPlace"  id="Destination Place" value="${inventoryForm.destinationPlace}"  style="width:150px; margin:0px!important;" onblur="this.value=TrimAll(this.value)" maxlength="40" />
                                    </td>
                                    <%--<td  width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                        <bean:message key="label.spare.finalDestination" /> :
                                    </td>
                                    <td width="20%" align="left">
                                        <select id="final Dest Country" name="finalDestCountry" class="headingSpas" style="width:180px !important; margin:0px!important" onchange='getdischargePort(this.value)'>
                                            <option value="" >--Select Here--</option>
                                            <c:forEach items="${countriesEXPList}" var="countriesEXPList">

                                                <option value='${countriesEXPList.value}' title='${countriesEXPList.label}'>${countriesEXPList.label} </option>

                                            </c:forEach>

                                        </select>
                                    </td>--%>
                                </tr>

                                <tr>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right: 10px;" >
                                        <bean:message key="label.spare.preCarriageBy" /> :
                                    </td>
                                    <td   width="20%" align="left" class="headingSpas"  style="padding-right:10px;  margin:0px!important" >
                                        <input type="text" class="spacialInsTrim" name="precarriageBy"  id="Pre Carriage By"  value="${inventoryForm.precarriageBy}" maxlength="100" style="width:150px; margin:0px!important;" onblur="this.value=TrimAll(this.value)" />
                                    </td>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right: 10px;" >
                                        <bean:message key="label.spare.pacePreCarrier" /> :
                                    </td>
                                    <td   width="20%" align="left" class="headingSpas"  style="padding-right:10px ; margin:0px!important;" >
                                        <input type="text" class="spacialInsTrim" name="placePreCarrier"  id="Place Pre Carrier" value="${inventoryForm.placePreCarrier}" style="width:150px; margin:0px!important;" onblur="this.value=TrimAll(this.value)" maxlength="150" />
                                    </td>

                                </tr>

                                <tr>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right: 10px;" >
                                        <bean:message key="label.spare.specInstr" /> :
                                    </td>
                                    <td  colspan="3" width="20%" align="left" class="headingSpas" nowrap style="padding-right:10px" >
                                        <textarea class="spacialInsTrim" name="specInstr" rows="2" cols="3" id="SpecialIns"  onblur="return caMaxLength(this, 100);this.value=TrimAll(this.value);" onkeypress="return caMaxLength(this, 100);">${inventoryForm.specInstr}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" align="right" class="headingSpas" style="padding-right: 10px;">
                                        <bean:message key="label.spare.addPartOrder" /> :
                                    </td>
                                    <td width="3%" align="left" class="headingSpas" style="padding-right: 22px;">
                                        <input type="radio" name="excelUploadRadio" value="ByExcel" id="ByExcelUpload" >
                                        <bean:message key="label.spare.byExcelUpload" /> :
                                    </td>
                                    <td width="80%" colspan="2" align="left" class="headingSpas" style="padding-right:10px">
                                        <input name="excelFile" type="file" id="excelFile" style="width: 280px"/>
                                        (<a href = '<%=cntxpath%>/templates/NewOrder_Excel.xls' style="color:red !important; text-decoration: none"><bean:message key="label.common.clickhere" /></a> <bean:message key="label.common.ToDownloadExcel" />)
                                    </td>
                                </tr>

                                <tr>
                                    <td width="20%" align="right" class="headingSpas" style="padding-right: 10px;">
                                    </td>
                                    <td width="5%" align="left" class="headingSpas" style="padding-right: 10px;">
                                        <input type="radio" name="excelUploadRadio" value="ByCart" checked id="">
                                        <bean:message key="label.spare.byManual" /> :
                                    </td>
                                    <td width="75%" colspan="2" align="left" class="headingSpas" style="padding-left:9px;">
                                    </td>
                                </tr>
                                <%--<tr>
                                    <td width="20%" align="right" class="headingSpas" style="padding-right: 10px;">
                                        <bean:message key="label.spare.documentUpload" /> :
                                    </td>
                                    <td  width="80%" colspan="3" align="left" class="headingSpas" style="padding-right:10px">
                                        <input name="excelDocumentFile" type="file" id="excelDocumentFile" style="width: 280px"/> ${inventoryForm.documentName}

                                    </td>
                                </tr>--%>
                                <tr>
                                    <td colspan="4" align="center">
                                        <input type="button" value="Submit" style="float:none; margin: 5px 5px 5px 5px" onclick="validateExcel()"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </center>
        </div>

