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
            LoginDAO dao = new LoginDAO();
            serviceForm sf = dao.getDealerAddress(dealerCode);
            String stockistId = sf.getStockistId();
            String stockistName = sf.getStockistName();
            // System.out.println(" sf.getStockistId()  "+ sf.getStockistId());
            // System.out.println( " sf.getStockistName()  "+ sf.getStockistName());
%>
<c:set var="dCode" value='<%=dealerCode%>'/>
<c:set var="stockistId" value='<%=stockistId%>'/>
<c:set var="stockistName" value='<%=stockistName%>'/>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_6.js"></script>
<script src="${pageContext.request.contextPath}/js/intermediate.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

    function validateExcel()
    {
  
            if(document.getElementById("Delivery").value==""){
                alert(not_blank_dropdown_validation_msg+' Delivery Terms ');
                document.getElementById("Delivery").focus();
                return false;
            }
   
            if(document.getElementById("PreviousPO").checked ){  //Previous Po No
    <c:if test="${fn:length(prevPoList)>0}" >
                if(document.getElementById("Previous Po No").value==""){
                    alert(not_blank_dropdown_validation_msg+'Previous Po No');
                    document.getElementById("Previous Po No").focus();                 
                    return false;
                }
    </c:if>
    <c:if test="${fn:length(prevPoList)==0}" >
                alert("Previous Po No is Not available ! Please create New Order. ");
                return false;
    </c:if>
            }

    <c:if test="${odType eq 'VOR'}">
              if(document.getElementById("Fir No").value==""){
                alert(not_blank_validation_msg+' Job Card No');
                document.getElementById("Fir No").focus();
                return false;
            }
    </c:if>
    
    
    if(document.getElementById("OrderType").value==""){
        alert(not_blank_validation_msg+' Order Type');
        document.getElementById("OrderType").focus();
        return false;
    }
    
            if(document.getElementById("Delivery Address").value==""){
                alert(not_blank_validation_msg+' Delivery Address');
                document.getElementById("Delivery Address").focus();
                return false;
            }
            

            var dealerFlag='<%=dealerFlag%>';
            if(dealerFlag=='true')
            {
                var dealer=document.getElementById('Dealer Name').value;
                if(dealer=='')
                {
                    alert(not_blank_dropdown_validation_msg+'Dealer Name');
                    return false;
                }
            }
            
            <c:if test="${odType ne 'VOR'}">
            if( document.getElementById("ByExcelUpload").checked){
                var filePath= document.getElementById("excelFile").value;
                if(filePath == '')
                {
                    alert('Please Browse Excel  File.');
                    return false;
                }
                
                else if(filePath.indexOf('.xls') != (filePath.length-4) ) //&& filePath.indexOf('.xlsx') != (filePath.length-5)
                {
                    alert('Invalid Excel File. Please Browse only .xls file.');
                    return false;
                }
            }
            </c:if>
   
            SpacialInsTrim();
            document.form1.action="<%=cntxpath%>/inventoryAction.do?option=addNewOrder";
            document.form1.submit();
        }

        function makeEnable(val){

            //alert(val.split("@@")[0])
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
        function freshPO(val){
            if(val=='VOR'){
                document.getElementById("Delivery").value ="";
                document.getElementById("Previous Po No").value ="";
                document.getElementById("Tractor Serial No").value="";
                document.getElementById("Tractor Model Segment").value="";
                document.getElementById("Tractor Engine No").value="";
                document.getElementById("Fir No").value="";
                document.getElementById("Delivery Address").value=document.getElementById("DeliveryAddress").value;

            }else{
                document.getElementById("Delivery").value ="";
                document.getElementById("Previous Po No").value ="";
            }
        }

        function getChassisNoData(id)
        {
            //  alert( " in chassisData() "+id.value);
            var chassisNo= document.getElementById("Tractor Serial No").value;
            if(chassisNo!=""){

                var vinno= id.value;
                var strURL="<%=cntxpath%>/inventoryAction.do?option=getChassisDetails&vinNo=" + vinno+"&tm=" + new Date().getTime();
                xmlHttp = GetXmlHttpObject();
                xmlHttp.onreadystatechange = function()
                {
                    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                    {
                        res = trim(xmlHttp.responseText);
                        if(res!='' && res.indexOf('@@') !=-1){
                            result= res.split("@@");
                            document.getElementById("Tractor Model Segment").value=result[0];
                            document.getElementById("Tractor Engine No").value=result[1];
                        }
                        else{                           
                            document.getElementById("Tractor Model Segment").value="";
                            document.getElementById("Tractor Engine No").value="";
                            return false;
                        }
                    }
                }
                xmlHttp.open("POST", strURL, true);
                xmlHttp.send(null);
            }
        }

        function getJobCardNo(id)   
        {
            var jobcardNo= document.getElementById("Fir No").value;
            if(jobcardNo!="")
            {
                var jobCardNo= id.value;
                var strURL="<%=cntxpath%>/inventoryAction.do?option=getJobCardNo&jobCardNo=" + jobCardNo ;
                xmlHttp = GetXmlHttpObject();
                xmlHttp.onreadystatechange = function()
                {
                    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                    {
                        res = trim(xmlHttp.responseText);
                        if(res!='' && res.indexOf('@@') !=-1){                           
                            result= res.split("@@");
                            if(result[3] && result[3].trim().length > 0 && result[3].toUpperCase() == 'OPEN'){
                                document.getElementById("Tractor Serial No").value=result[0];
                                document.getElementById("Tractor Model Segment").value=result[1];
                                document.getElementById("Tractor Engine No").value=result[2];
                            }else{
                                alert(jobCard_not_open_message);
                                document.getElementById("Tractor Serial No").value="";
                                document.getElementById("Tractor Model Segment").value="";
                                document.getElementById("Tractor Engine No").value="";
                                document.getElementById("Fir No").value="";
                                document.getElementById("Fir No").focus();
                            }
                            
                        }
                        else{
                            alert('Invalid Job card No.! Please Enter Valid Job Card No.');
                            document.getElementById("Tractor Serial No").value="";
                            document.getElementById("Tractor Model Segment").value="";
                            document.getElementById("Tractor Engine No").value="";
                            document.getElementById("Fir No").value=""; 
                            document.getElementById("Fir No").focus();
                            return false;
                        }
                    }
                }
                xmlHttp.open("POST", strURL, true);
                xmlHttp.send(null);
            }
        }

        function getDealAddress(id)                 // not used
        {        
            var dealer= document.getElementById("Dealer code").value;
            if(dealer!=""){
                var vinno= id.value;
                var strURL="<%=cntxpath%>/inventoryAction.do?option=getDealAddress&dealerCode=" +dealerCode;
                xmlHttp = GetXmlHttpObject();
                xmlHttp.onreadystatechange = function()
                {
                    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                    {
                        res = trim(xmlHttp.responseText);
                        if(res!='' && res.indexOf('@@') !=-1){
                            document.getElementById("Delivery Address").value=result;
                        }
                        else{
                            // alert('Invalid Chassis No.! Please Enter Valid dealer address No.');                        
                            document.getElementById("Delivery Address").value="";
                            return false;
                        }
                    }
                }
                xmlHttp.open("POST", strURL, true);
                xmlHttp.send(null);
            }
        }

        function getCustPoNo(id)
        {            
            var custNo= document.getElementById("Previous Po No").value;
            //alert("Po No is Selected : " + id);
            if(custNo!=""){
                var prePo= id.value;
                var strURL="<%=cntxpath%>/inventoryAction.do?option=getCustPoNo&custPoNo=" + custNo;
                xmlHttp = GetXmlHttpObject();
                xmlHttp.onreadystatechange = function()
                {
                    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                    {
                        res = trim(xmlHttp.responseText);
                        if(res!='' && res.indexOf('@@') !=-1){
                            result= res.split("@@");

                            //  document.getElementById("Delivery").value();
    <c:if test="${odType eq 'VOR'}">

                            document.getElementById("Fir No").value=result[0];
                            document.getElementById("Tractor Serial No").value=result[1];
                            document.getElementById("Tractor Model Segment").value=result[2];
                            document.getElementById("Tractor Engine No").value=result[3];

    </c:if>
                            document.getElementById("Delivery Address").value=result[4];
                            document.getElementById("datepicker1").value=result[5];
                            document.getElementById("SpecialIns").value=result[6];
                            $('#Delivery').val(result[7]);
                            //   document.getElementById("Delivery").value=="";
                            
                            document.getElementById("OrderType").value=result[8];
                           
                           
                        }
                        else{
    <c:if test="${odType eq 'VOR'}">
                            document.getElementById("Fir No").value="";
                            document.getElementById("Tractor Serial No").value="";
                            document.getElementById("Tractor Model Segment").value="";
                            document.getElementById("Tractor Engine No").value="";
                            
    </c:if>
                            document.getElementById("Delivery Address").value="";
                            document.getElementById("datepicker1").value="".readOnly;                              
                            return false;
                        }
                    }
                }
                xmlHttp.open("POST", strURL, true);
                xmlHttp.send(null);
            }
        }

        function getNewPo(id)
        {
            var deladd= document.getElementById("freshPO").value;
            if(deladd!=""){
                var add= id.value;
                var strURL="<%=cntxpath%>/inventoryAction.do?option=getNewPo&dealercode=" + deladd;
                xmlHttp = GetXmlHttpObject();
                xmlHttp.onreadystatechange = function()
                {
                    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                    {
                        res = trim(xmlHttp.responseText);
                        if(res!='' && res.indexOf('@@') !=-1){
                            result= res.split("@@");

                            document.getElementById("Delivery Address").value=result[0];
                        }
                        else{
                            document.getElementById("Delivery Address").value="";
                            return false;
                        }
                    }
                }
                xmlHttp.open("POST", strURL, true);
                xmlHttp.send(null);
            }
        }

        function getOrderDes(poNo)
        {
            //alert(poNo)
            var strURL="<%=cntxpath%>/inventoryAction.do?option=getOrderDesc&poNo=" + poNo +"&tm=" + new Date().getTime();
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    res = trim(xmlHttp.responseText);
                    // alert(res)
                    if(res!='' && res.indexOf('@@') !=-1){
                        result= res.split("@@");
                        document.getElementById("Delivery").value=result[0]+"@@"+result[1];
                        if(result[3]=='VOR'){
                            document.getElementById("Tractor Serial No").value=result[4].trim();
                            document.getElementById("Tractor Model Segment").value=result[5];
                            document.getElementById("Tractor Engine No").value=result[6];
                            document.getElementById("Fir No").value=result[7];
                        }
                    }
                    else{
                        document.getElementById("Delivery").value="";
                        if(result[3]=='VOR'){
                            document.getElementById("Tractor Serial No").value="";
                            document.getElementById("Tractor Model Segment").value="";
                            document.getElementById("Tractor Engine No").value="";
                            document.getElementById("Fir No").value="";
                        }
                        return false;
                    }
                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);
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
            return (ca.value.length <= MaxLen);
        }
        function SpacialInsTrim()
        {
            $('.spacialInsTrim').each(function(idx,val){
                val.value = val.value.replace(/\s+/g, ' ');
            });

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

            <%--$("#datepicker1" ).datepicker({
                minDate: 0,
                      dateFormat: "dd/mm/yy",
                      onClose:function(){
                          var date2 = $('#datepicker1').datepicker('getDate');
                          date2.setDate(date2.getDate()+7)
                          $( "#datepicker1" ).attr("readonly",true).datepicker("setDate", date2);
                      }
                  });
                  $( "#datepicker1" ).attr("readonly",true).datepicker({
                      minDate: date2, dateFormat: "dd/mm/yy"});
            --%>

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
        <div class="contentmain1">
            <div class="breadcrumb_container">
                <ul class="hText">
                    <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
                    
                        <li><bean:message key="label.spare.createNewOrder" />  </li>
                    
                    <c:if test="${odType eq 'VOR'}">
                        <li><bean:message key="label.spare.createNewOrderVOR" />  </li>
                    </c:if>

                </ul>
            </div>
            <div class="message" id="msg_saveFAILED"></div>
            <center>
                <div class="content_right1">
                    <div class="con_slidediv1" style="position: relative;width: 100%">
                        <h1 class="hText">
                            <c:if test="${odType eq 'STD'}">
                                <bean:message key="label.spare.createNewOrder" />
                            </c:if>
                            <c:if test="${odType eq 'VOR'}">
                                <bean:message key="label.spare.createNewOrderVOR" />
                            </c:if>
                        </h1>
                        <form name="form1" id="form1" method="post" onsubmit="return check_method_select();" ENCTYPE="multipart/form-data">
                            <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key='label.common.spares'/></a>@@<bean:message key="label.spare.createNewOrder" />"/>
                            <table width="100%" border="0" cellspacing="2" cellpadding="2" align="left" class="LiteBorder">

                                <tr>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                        <bean:message key="label.spare.poTo" /> :
                                    </td>
                                    <td colspan="3" width="20%" align="left">
                                        ${stockistName}
                                        <input type="hidden" name="stockistId" value="${stockistId}" id="">
                                        <input type="hidden" name="stockistName" value="${stockistName}" id="">
                                    </td>
                                </tr>
                                <tr>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                        <bean:message key="label.spare.orderType" /> :
                                    </td>
                                    
                                    
                                    <c:choose>
                                    
                                    <c:when test="${odType == 'ALL'}">
                                    <td width="20%" align="left">
                                        <select id="OrderType" name="orderType" class="headingSpas" style="width:180px !important;">
                                            <option value="" >--Select Here--</option>
                                            <c:forEach items="${orderTypeList}" var="orderTypeList">
                                                
                                                <option value='${orderTypeList.value}' title='${orderTypeList.label}'>${orderTypeList.label} </option>

                                            </c:forEach>

                                        </select>
                                    </td>
                                    </c:when>
                                    
                                    <!-- CASE 2: Show single OrderType VOR -->
                                    <c:otherwise>
                                    <td width="20%" align="left">
                                        ${odType}
                                        <input type="hidden" name="orderType" value="${odType}" id="OrderType">
                                    </td>
                                    </c:otherwise>
                                    
                                 </c:choose>
                                    
                                    
                                    <td  width="30%" align="right" class="headingSpas" nowrap style="padding-right:82px" >
                                        <input type="radio" name="poNoRadio" value="newPO" checked id="NewPO" onclick="freshPO('${odType}');">
                                        <bean:message key="label.spare.newOrder" /> :
                                    </td>
                                    <td width="30%" align="left">
                                        <input type="hidden" name="newPoNo" value="">
                                        ( <bean:message key="label.spare.poNo" /> : ${refNo})
                                        <input type="hidden" name="refNo" value="${refNo}">
                                    </td>
                                </tr>
                                <tr>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                        <bean:message key="label.common.modeOfDispatch" /> :
                                    </td>
                                    <td width="20%" align="left">
                                        <select id="Delivery" name="deliveryCode" class="headingSpas" style="width:180px !important;">
                                            <option value="" >--Select Here--</option>
                                            <c:forEach items="${deliveryList}" var="deliveryList">
                                                <%--<c:choose>
                                                    <c:when test="${delvry eq deliveryList.value}">
                                                        <option selected value='${deliveryList.value}@@${deliveryList.label}' title='${deliveryList.label}'>${deliveryList.label}</option>
                                                    </c:when>
                                                    <c:otherwise>--%>
                                                <option value='${deliveryList.value}' title='${deliveryList.label}'>${deliveryList.label} </option>
                                                <%-- </c:otherwise>
                                             </c:choose>--%>

                                            </c:forEach>

                                        </select>
                                    </td>
                                    <td  width="30%" align="right" class="headingSpas" nowrap style="padding-right:10px" >
                                        <input type="radio" name="poNoRadio" value="oldPO" id="PreviousPO">
                                        <bean:message key="label.spare.addPrevPoNo" /> :
                                    </td>
                                    <td width="30%" align="left">
                                        <select id="Previous Po No" name="prevPoNo" class="headingSpas" style="width:200px !important;" onClick="selectPreviousRadio();" onchange="getCustPoNo(this.value);" >   <%--onchange="getOrderDes(this.value)"--%>
                                            <option value="" >--Select Previous PO No--</option>
                                            <c:forEach items="${prevPoList}" var="prevPoList">
                                                <option value='${prevPoList.value}' title='${prevPoList.label}'>${prevPoList.label}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <c:if test="${odType eq 'VOR'}">
                                    <%-- <span id="orderSpan" style="display: none">--%>
                                    <tr>
                                        <td colspan="4">
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" >
                                                <tr>
                                                    <td width="18%" align="right" class="headingSpas" >
                                                        <bean:message key="label.newOrder.firNo" /> :
                                                    </td>
                                                    
                                                    <!-- 
                                                    <td width="15%" align="left" class="headingSpas">
                                                        <input type="text" id="Fir No" name="firNo" value="" onblur="getJobCardNo(this)">
                                                    </td>
                                                     -->
                                                    
                                                    <td  width="20%" align="left" class="headingSpas" nowrap style="padding-right:10px" >
                                                    <select id="Fir No" name="firNo" class="headingSpas" style="width:200px !important;"  onchange="getJobCardNo(this);" >   
                                                    <option value="" >--Select Job Card No--</option>
                                                    <c:forEach items="${jobcardList}" var="jobcardList">
                                                        <option value='${jobcardList.value}' title='${jobcardList.label}'>${jobcardList.label}</option>
                                                    </c:forEach>
                                                    </select>
                                                    
                                                    </td>
                                                    

                                                    <td  width="14%" align="right" class="headingSpas" nowrap>
                                                        <bean:message key="label.catalogue.chassisNo" />  :
                                                    </td>
                                                    <td width="9%" align="left" >
                                                        <input type="text" id="Tractor Serial No" name="chassisNo" value="" readonly onkeydown="return false;">        <%--onblur="getChassisNoData(this)">--%>
                                                    </td>

                                                    <td  width="4%" align="left" class="headingSpas" nowrap  >
                                                        <bean:message key="label.catalogue.model" /> :
                                                    </td>
                                                    <td width="10%" align="left">
                                                        <input type="text" id="Tractor Model Segment" name="modelNo" value="" readonly  onkeydown="return false;" >
                                                    </td>
                                                    <td  width="6%" align="left" class="headingSpas" nowrap  >
                                                        <bean:message key="label.catalogue.engineNo" /> :
                                                    </td>
                                                    <td width="8%" align="left">
                                                        <input  type="text" id="Tractor Engine No" name="engineNo" value="" readonly onkeydown="return false;">
                                                    </td>
                                            </table>
                                        </td>
                                    </tr>
                                    <%-- </span>--%>
                                </c:if>
                                <tr>
                                    <td width="20%" align="right" class="headingSpas" nowrap style="padding-right: 10px;" >
                                        <bean:message key="label.spare.deliveryAdd" /> :
                                    </td>
                                    <td width="20%" align="left" class="headingSpas" nowrap style="padding-right:10px" >
                                        <textarea  id="Delivery Address" name="deliveryAdd" rows="2" cols="3" onkeypress="return caMaxLength(this, 101);" >${deliveryAddress}</textarea>
                                        <c:if test="${odType eq 'VOR'}">
                                            <input type="hidden" name="DeliveryAddress" id="DeliveryAddress" value="${deliveryAddress}" id=""></c:if>
                                        </td>

                                        <td width="20%" align="right" class="headingSpas" nowrap style="padding-right:10px;" >
                                        <bean:message key="label.spare.deliveryDate" /> :
                                        <input type="text" name="deliveryDate" class="datepicker1" id="datepicker1" />            <%--onkeydown="return false;"--%>
                                    </td>
                                </tr>
                                <tr>
                                    <td  width="20%" align="right" class="headingSpas" nowrap style="padding-right: 10px;" >
                                        <bean:message key="label.spare.specInstr" /> :
                                    </td>
                                    <td  colspan="3" width="20%" align="left" class="headingSpas" nowrap style="padding-right:10px" >
                                        <textarea class="spacialInsTrim" name="specInstr" rows="2" cols="3" id="SpecialIns" onkeypress="return caMaxLength(this, 101);"></textarea>
                                    </td>
                                </tr>
                                
                                <c:if test="${odType ne 'VOR'}">
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
                                </c:if>
                               
                                <tr>
                                    <td width="20%" align="right" class="headingSpas" style="padding-right: 10px;">
                                    </td>
                                    <td width="5%" align="left" class="headingSpas" style="padding-right: 10px;">
                                        <input type="radio" name="excelUploadRadio" value="ByCart" checked id="">
                                        <bean:message key="label.spare.byCartorManual" /> :
                                    </td>
                                    <td width="75%" colspan="2" align="left" class="headingSpas" style="padding-left:9px;">
                                    </td>
                                </tr>
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