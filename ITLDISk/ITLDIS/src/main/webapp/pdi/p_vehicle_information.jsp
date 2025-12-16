<%--
    Document   : vehicle_information.jsp
    Created on : Apr 30, 2014, 03:25:09 PM
    Author     : Avinash.Pandey..
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DATE);
%>

<script type="text/javascript" language="javascript">

    var contextPath = '${pageContext.request.contextPath}';
    var noOfDays = '${pdiform.noOfDays}';

    function submitForm(len,tractorCount)
    {
        var cks_code = /^[a-zA-Z\d\-\/\.\,\\\ ]+$/;
        var bool="false";
        if(document.getElementById("Invoice Date").value==""){
           // document.getElementById("Invoice Date").style.border="1px solid #ff0000";
           document.getElementById("Invoice Date").focus();
            alert(not_blank_validation_msg+'Invoice Date');
            document.getElementById("Invoice Date").select();
            //bool="true";
            return false;
        }
          if(document.getElementById("Tractor In Date").value==""){
            <%--document.getElementById("Tractor In Date").style.border="1px solid #ff0000";--%>
            document.getElementById("Tractor In Date").focus();
            alert(not_blank_validation_msg+'Tractor In Date');
            document.getElementById("Tractor In Date").select();
            //bool="true";
            return false;
        }
        if(document.getElementById("PDI Date").value==""){
            <%--document.getElementById("PDI Date").style.border="1px solid #ff0000";--%>
            document.getElementById("PDI Date").focus();
            alert(not_blank_validation_msg+'PDI Date');
            document.getElementById("PDI Date").select();
            //bool="true";
            return false;
        }
        //var idate = new Date(document.getElementById("PDI Date").value).getTime();

        //var d1=new Date();
        var ttdate="";
        var tmon="<%=month%>";
        var tdays="<%=day%>";

        if(eval(tmon)<10)
            tmon="0"+tmon;

        if(eval(tdays)<10)
            tdays="0"+tdays;

        ttdate=<%=year%>+'/'+tmon+'/'+tdays;

        var today = new Date(ttdate);
        var backDate= new Date(ttdate);
        backDate.setDate(backDate.getDate()-noOfDays);
        <%--alert('***'+backDate)--%>
         
        var pdidate=document.getElementById("PDI Date").value.split('/');
        var sdate=pdidate[2]+'/'+pdidate[1]+'/'+pdidate[0];
        var pdidt=new Date(sdate).getTime();

        if(today < pdidt || backDate > pdidt){
            document.getElementById("PDI Date").focus();
            alert(pdiDate_currentDate_validation_msg+noOfDays+dat_validation_msg2);
            return false;

        }

        var tid=document.getElementById("Tractor In Date").value.split('/');
        var trdate=tid[2]+'/'+tid[1]+'/'+tid[0];
        var trd=new Date(trdate).getTime();

        <%--if(today < trd || backDate > trd){

            //document.getElementById("PDI Date").style.border="1px solid #ff0000";
            document.getElementById("Tractor In Date").focus();
            alert(tractorReceivedDate_currentDate_validation_msg+noOfDays+dat_validation_msg2);
            //bool="true";
            return false;

        }--%>
        if(pdidt<trd){

            document.getElementById("Tractor In Date").focus();
            alert(tractorReceivedDate_pdiDate_validation_msg);
            return false;

        }

        var idate=document.getElementById("Invoice Date").value.split('/');
        var invoicedate = idate[2]+'/'+idate[1]+'/'+idate[0];
        invoicedate=new Date(invoicedate).getTime();
        <%--if(today < invoicedate || backDate > invoicedate){
            //document.getElementById("Invoice Date").style.border="1px solid #ff0000";
            document.getElementById("Invoice Date").focus();
            alert(invoice_currentDate_validation_msg+noOfDays+dat_validation_msg2);
            return false;

        }--%>
        if(trd<invoicedate){
            //document.getElementById("Tractor In Date").style.border="1px solid #ff0000";
            document.getElementById("Invoice Date").focus();
            alert(invoice_pdiDate_validation_msg);
            //bool="true";
            return false;

        }

        if(document.getElementById("Invoice No").value==""){
           // document.getElementById("Invoice Date").style.border="1px solid #ff0000";
           document.getElementById("Invoice No").focus();
            alert(not_blank_validation_msg+'Invoice No');
            document.getElementById("Invoice No").select();
            //bool="true";
            return false;
        }
         var ck_code = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
        if(ck_code.test(document.getElementById("Invoice No").value)){
           // document.getElementById("Invoice Date").style.border="1px solid #ff0000";
           document.getElementById("Invoice No").focus();
            alert(specialChar_validation_msg+"Invoice No");
            document.getElementById("Invoice No").select();
            return false;
        }

       if(document.getElementById("PDI DONE BY").value==""){
            document.getElementById("PDI DONE BY").focus();
            alert(not_blank_dropdown_validation_msg+'PDI DONE BY');
            return false;
        }

        /* used when vendor name input type not from table
            var c=0;
            if(count==0)
                count =document.getElementById("ListSize").value;

            for(var i=1;i<count;i++){
                if(document.getElementById("Part No"+i)!=null)
                {
                    if(document.getElementById("Part No"+i).value!=""){ c=1; }
                    else if(document.getElementById("PartVendor Name"+i).value!=""){ c=1; }
                    else if(document.getElementById("PartSerial No"+i).value!=""){ c=1; }
                }
            }

            if(c==0){
                alert("please fill atleast one Part Name ");
                document.getElementById("Part No1").focus();
                return false;
            }



            partlist=new Array();
            var j=0;
            for(var i=1;i<count;i++){

                if(document.getElementById("Part No"+i)!=null)
                {

                    if(document.getElementById("Part No"+i).value!=""){
                        partlist[j]=document.getElementById("Part No"+i).value;
                        j++;
                        if(cks_code.test(document.getElementById("Part No"+i).value)==false){
                        alert("Invalid Part Name");
                        document.getElementById("Part No"+i).focus();
                        return false;
                       }
                        if(document.getElementById("PartVendor Name"+i).value==""){alert("Make should not be empty"); document.getElementById("PartVendor Name"+i).focus();    return false; }
                        if(document.getElementById("PartSerial No"+i).value==""){alert("Serial No. should not be empty"); document.getElementById("PartSerial No"+i).focus();return false;}

                    }
                    if(document.getElementById("PartVendor Name"+i).value!=""){

                        if(cks_code.test(document.getElementById("PartVendor Name"+i).value)==false){

                        alert("Invalid Make");
                        document.getElementById("PartVendor Name"+i).focus();
                        return false;
                       }
                        if(document.getElementById("Part No"+i).value==""){alert("Part Name should not be empty"); document.getElementById("Part No"+i).focus();c=1;return false;}
                        if(document.getElementById("PartSerial No"+i).value==""){alert("Serial No should not be empty"); document.getElementById("PartSerial No"+i).focus();return false;}
                    }
                     if(document.getElementById("PartSerial No"+i).value!=""){

                         if(cks_code.test(document.getElementById("PartSerial No"+i).value)==false){

                         alert("Invalid Serial No");
                         document.getElementById("PartSerial No"+i).focus();
                         return false;
                         }
                        if(document.getElementById("Part No"+i).value==""){alert("Part Name should not be empty"); document.getElementById("Part No"+i).focus();return false;}
                        if(document.getElementById("PartVendor Name"+i).value==""){alert("Make should not be empty"); document.getElementById("PartVendor Name"+i).focus();return false;}
                    }

                }
            }



            //for unique partno

            for (var k = 2; k < count; k++) {
                if (document.getElementById("Part No"+k).value != "")
                {
                    var res1 = true;
                    for (var m = 0; m < k-1; m++)
                    {

                        if (partlist[m] == trimS(document.getElementById("Part No"+k).value))
                        {
                            res1 = false;
                        }
                    }
                    if (res1 == false)
                    {
                        alert("Same Part Name can not be enter twice");
                        document.getElementById("Part No" + k).focus();

                        return false;
                    }
                }
            }*/
// for tractor part name and Make validation

    for(j=1;j<tractorCount;j++){
        if(document.getElementById("Make"+j).value==""){
            document.getElementById("Make"+j).focus();
            alert(not_blank_dropdown_validation_msg+'Make')
            return false;
        }
        if(document.getElementById("PartSerial No"+j).value==""){
            document.getElementById("PartSerial No"+j).focus();
            alert(not_blank_validation_msg+'Serial No')
            return false;
        }
        if(ck_code.test(document.getElementById("PartSerial No"+j).value)){
           document.getElementById("PartSerial No"+j).focus();
            alert(specialChar_validation_msg+"Serial No");
            return false;
        }

    }

// for check list

    if(len<=1)
    {

        alert(standardCheckList_empty_validation_msg);
        return false;
    }

//checkpoints
        //var checkpoints=new Array();
        var lent=parseInt(len);
        for(i=1;i<len;i++){
            //alert(document.getElementById("checkpoints"+i).value);
            if(document.getElementById("checkpoints"+i).value==="NOT OK" && document.getElementById("Action Taken"+i).value===""){
                <%--document.getElementById("Action Taken"+i).style.border="1px solid #ff0000";--%>
                document.getElementById("Action Taken"+i).focus();
                alert(actiontaken_validation_msg);
                document.getElementById("Action Taken"+i).select();
                // bool="true";
                //break;
                return false;
            }
            else if(document.getElementById("checkpoints"+i).value==="NOT OK" && document.getElementById("Observation"+i).value===""){
                <%--document.getElementById("Observation"+i).style.border="1px solid #ff0000";--%>
                document.getElementById("Observation"+i).focus();
                alert(remark_validation_msg);
                document.getElementById("Observation"+i).select();
                //bool="true";
                //break;
                return false;
            }

        }

            var x="false";
            var r=confirm(pdiConfirmation_msg);
            if (r==true)
            {
             document.getElementById("StandardChecksForm").submit();
            }

    }
function CommentsMaxLength(text,maxLength)
{
    var diff = maxLength - text.value.length;

    if (diff < 0){
        alert(notgreater_validation_msg + maxLength);
        return false;
    }
     return true;
}


 var count=0;
        var rows=0;

        function addRows(rowno){

            if(count==0)
                count=parseInt(rowno)+1;

            var tbody = document.getElementById("tractorinfo");

            var row = document.createElement("TR");

            row.style.backgroundColor="#FFFFFF";

            var td0 = document.createElement("TD");
            var strHtml1 = count;
            td0.setAttribute("align", "center");
            td0.setAttribute("height", "20");
            td0.innerHTML = strHtml1;

            //Part No, PartVendor Name,PartSerial No
            var td1 = document.createElement("TD");
            var strHtml1 = "<input id=\"Part No"+ count +"\" type=\"text\"   maxlength=\"20\" name=\"partNo" + count + "\" style=\"width:300px\" /><input type=\"hidden\" name=\"actiontakens\"   value=\""+count+"\"/>";
            td1.setAttribute("align", "left");
            td1.style.paddingLeft = "3px";
            // "padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;padding-top: 2px;padding-bottom: 2px";
            td1.style.paddingTop="2px";
            td1.style.paddingRight="3px";
            td1.style.paddingBottom="2px";

            td1.innerHTML = strHtml1;



            var td2 = document.createElement("TD");
            var strHtml2 = "<input name=\"partVendorName"+ count +"\" type=\"text\" id=\"PartVendor Name"+ count +"\" maxlength=\"40\" style=\"width:300px\" />";
            td2.setAttribute("align", "left");
            td2.innerHTML = strHtml2;
            td2.style.paddingLeft = "3px";
            // "padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;padding-top: 2px;padding-bottom: 2px";
            td2.style.paddingTop="2px";
            td2.style.paddingRight="3px";
            td2.style.paddingBottom="2px";

            var td3 = document.createElement("TD");
            var strHtml3 = "<input name=\"partSerialNo"+ count +"\" type=\"text\" id=\"PartSerial No"+ count +"\"  maxlength=\"40\"  style=\"width:250px\" />";
            td3.setAttribute("align", "left");
            td3.style.paddingLeft = "3px";
            td3.style.paddingTop="2px";
            td3.style.paddingRight="3px";
            td3.style.paddingBottom="2px";
            td3.innerHTML = strHtml3;
            //partVendorName,partSerialNo
            row.appendChild(td0);
            row.appendChild(td1);
            row.appendChild(td2);
            row.appendChild(td3);

            count = parseInt(count) + 1;

            tbody.appendChild(row);


        }
  function textLimit(field,maxlen) {
            if(field.value.length > maxlen){
                while(field.value.length > maxlen){
                    field.value=field.value.replace(/.$/,'');
                }
                alert('Any Observation'+maxLength_validation_msg+maxlen);
            }
        }
         $(document).ready(function () {

            $(function() {
                var ttdate="";
                var tmon="<%=month%>";
                var tdays="<%=day%>";
                if(eval(tmon)<10)
                    tmon="0"+tmon;

                if(eval(tdays)<10)
                tdays="0"+tdays;

                ttdate=+tdays+'/'+tmon+ '/'+<%=year%>;
                var tractorindate=document.getElementById("Tractor In Date").value;
                var pdiDate=document.getElementById("PDI Date").value;
                if(tractorindate!=""){
                    $( ".datepicker" ).datepicker();
                }
                if(pdiDate!=""){
                    $( ".datepicker" ).datepicker();
                }else{
                  //$( ".datepicker" ).datepicker( "setDate", ttdate);
                }
            });
        });

</script>

<div class="breadcrumb_container">
    <ul class="hText">

        <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link></li>
        <li class="breadcrumb_link"><a href ='<%=cntxpath%>/pdiServiceProcess.do?option=init_viewallPendingChassisdetails'><bean:message key="label.common.pendingforpdi" /></a></li>
        <li class="breadcrumb_link"><b>${pdiform.vinNo}</b></li>
    </ul>
</div>




<div class="message" id="msg_saveFAILED">${message}</div>

<center>
    <div class="content_right1">
        <div class="con_slidediv2" style="position: relative;width: 100%">
            <h1 class="hText"><bean:message key="label.common.PDIForChassisno" />  ${pdiform.vinNo}</h1>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="4" class="LiteBorder" >

                <tr   bgcolor="#eeeeee" height="20" >
                    <td  class="headingSpas" align="left" >
                        <label >
                            <B>  <bean:message key="label.common.vehicleinfo" /></B>
                        </label>
                    </td>
                </tr>

                <tr>
                    <td>
                        <form action="<%=cntxpath%>/pdiServiceProcess.do?option=managePDIStandardChecksdetail" method="post" id="StandardChecksForm">
                            <input type="hidden" name="upperLink" value="<a href = '<%=cntxpath%>/initService.do'><bean:message key="label.common.Service" /></a>@@<a href ='<%=cntxpath%>/initService.do?option=init_viewallPendingChassisdetails&jobType=PDI'><bean:message key="label.common.pendingforpdi" /></a>"/>

                            <table width="100%" border="0" align="center" cellpadding="1" cellspacing="5" >
                                <tr>
                                    <td  align="right">
                                        <span class="headingSpas" >
                                            <label> <bean:message key="label.common.Productcategory" /></label>
                                        </span>
                                    </td>
                                    <td  align="left" >
                                        <select name="productCategory"  id="productCategory" disabled="disabled">
                                            <c:forEach items="${productcategoryList}" var="dataList">
                                                <c:if test="${pdiform.productionCategory eq dataList.value }">
                                                    <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>
                                                </c:if>
                                                <c:if test="${pdiform.productionCategory ne dataList.value }">
                                                    <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" name="vinid" value="${pdiform.vinId}"/>
                                    </td>
                                    <td  align="right"><span class="headingSpas">
                                            <label><bean:message key="label.common.Modelfamily" /></label>
                                        </span>
                                    </td>

                                    <td  align="left">
                                        <input name="modelFamily" type="text" id="Model Family"  maxlength="" value="${pdiform.modelFamily}" readonly="readonly" onkeydown="return false;"/>
                                        <input name="jobType" type="hidden" value="${pdiform.jobType }">
                                        <input name="modelFamily" type="hidden" value="${pdiform.modelFamily}">
                                    </td>
                                    <td align="right"><span class="formLabel">
                                            <label><bean:message key="label.common.Modelcode" /></label>
                                        </span></td>
                                    <td align="left" >
                                        <input name="modelCode" type="text" id="Model Code"  maxlength="" value="${pdiform.modelCode}" readonly="true" onkeydown="return false;"/>

                                    </td>
                                </tr>
                                <tr>
                                    <td  align="right"><span class="headingSpas">
                                            <label><bean:message key="label.common.Chassisno" /></label>&nbsp</span></td>

                                    <td align="left"  >
                                        <input name="vinNo" type="text" id="VIN No." maxlength="50" value="${pdiform.vinNo}" readonly="readonly" onkeydown="return false;"/>

                                    </td>
                                    <td align="right" style=""><div align="right"> <span class="formLabel">
                                                <label><bean:message key="label.common.Modelfamilydesc" /></label>
                                            </span></div></td>
                                    <td align="left">
                                        <input name="modelFamilyDesc" type="text" id="Model Family Desc"  maxlength="" value="${pdiform.modelFamilyDesc}" readonly="true" onkeydown="return false;"/>
                                    </td>
                                    <td align="right" style="" ><div align="right"> <span class="headingSpas">
                                                <label><bean:message key="label.common.Modelcodedesc" /></label>
                                            </span></div></td>
                                    <td align="left">
                                        <input name="modelCodeDesc" type="text" id="Model Code Desc." maxlength="" value="${pdiform.modelCodeDesc}" readonly="readonly" onkeydown="return false;"/>
                                    </td>

                                </tr>
                                <tr>
                                    <td align="right"><span class="headingSpas">
                                            <label><bean:message key="label.common.InvoiceDate" /> </label>&nbsp<span class="mandatory">*</span>
                                        </span>
                                    </td>
                                    <td align="left" style="white-space: nowrap">
                                        <input name="invoiceDate" type="text" readOnly="readonly" id="Invoice Date" size="15" value="${pdiform.invoiceDate}"  onkeydown="return false;"/>
                                    </td>
                                    <td align="right"><span class="headingSpas">
                                            <label><bean:message key="label.common.tractorrecieveddate" /> </label>&nbsp<span class="mandatory">*</span>
                                        </span>
                                    </td>
                                    <td align="left" style="white-space: nowrap">
                                        <input name="jobCardDate" type="text" class="datepicker" id="Tractor In Date" size="15" value="${jobCardDate}"  onkeydown="return false;"/>
                                    </td>
                                     <td align="right" style="" ><span class="headingSpas">
                                            <label><bean:message key="label.common.pdi" />&nbsp;<bean:message key="label.common.date" /></label>&nbsp;<span class="mandatory">*</span>
                                        </span></td>
                                    <td align="left" id="saledatetd">

                                        <input name="pdiDate" type="text" class="datepicker" id="PDI Date" value="${pdiDate}"  onkeydown="return false;"/>

                                    </td>
                                </tr>

                                <tr>
                                    <td align="right" style="" ><span class="headingSpas">
                                            <label><bean:message key="label.common.InvoiceNo" /></label>&nbsp<span class="mandatory">*</span>
                                        </span>
                                    </td>
                                    <td align="left">
                                        <c:if test="${empty pdiform.invoiceNo}">
                                            <input name="invoiceNo" type="text" id="Invoice No"  maxlength="50" value=""  />
                                        </c:if>

                                        <c:if test="${!empty pdiform.invoiceNo}">
                                            <input name="invoiceNo" type="text" id="Invoice No"  maxlength="50" value="${pdiform.invoiceNo}"  readOnly="readonly"/>
                                        </c:if>

                                             
                                    </td>
                                    <td align="right">
                                            <label><bean:message key="label.common.engineno" /></label>
                                        </td>
                                    <td align="left" >
                                        <input name="engineNumber" type="text" id="Engine Number" value="${pdiform.engineNumber}" readonly="readonly" onkeydown="return false;"/>
                                    </td>
                                    <td width="14%" align="right" style="" ><div align="right"> <span class="headingSpas">
                                                    <label><bean:message key="label.pdi.pdiDoneBy" />&nbsp;<span class="mandatory">*</span></label>    <%--<bean:message key="label.common.installer" />&nbsp;<bean:message key="label.common.name" />--%>
                                                </span></div></td>
                                        <td width="12%" align="left">
                                            <select name="repname" id="PDI DONE BY" >
                                                <option value="">--select--</option>
                                                <c:forEach items='${mechanicList}'  var='labelValue'  varStatus='status'>
                                                    <option value='${labelValue.value}' title='${labelValue.label}'>${labelValue.label}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    <%--<td align="right" style="" ><span class="formLabel">

                                            <label><bean:message key="label.common.warrantyapplicable" /></label>
                                        </span></td >
                                    <td align="left" id="Warranty App">
                                        : Yes
                                    </td>--%>
                                </tr>



                            </table>


                            <%--<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
                                         <tr>
                            <td id="travelcardData">--%>
                                <table  width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc >
                                    <tbody id="tractorinfo">
                                        <tr bgcolor="#eeeeee" height="20"  class="headingSpas">
                                            <td width="10%" align="center" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.sno" /></B></td>
                                            <td width="40%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.partname" /></B></td>
                                            <td width="20%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.partvendorname" />&nbsp;<span class="mandatory">*</span></B></td>
                                            <td width="20%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.partSerialNo" />&nbsp;<span class="mandatory">*</span></B></td>
                                        </tr>
                                        <c:set var="srNo" value="1"/>
                                        <c:set var="tsrNo" value="5"/>
                                        <c:set var="totallist" value="0"/>
                                         <c:if test="${!empty travelcardPartList}">
                                        <c:forEach items="${travelcardPartList}" var="travelcardPartList">
                                          <tr bgcolor="#FFFFFF" height="20">
                                            <input type="hidden" name="actiontakens"  value="${srNo}"/>
                                            <td align="center">${srNo}</td>
                                            <td align="left" nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                                <input name="partNo${srNo}" type="text" id="Part No${srNo}" style="width:300px ; background-color:#E6E4E4;display:none;"  value="${travelcardPartList.key}"/>
                                                <span>${travelcardPartList.key}</span>
                                            </td>

                                            <td align="left" nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                                <select name="partVendorName${srNo}" type="text" id="Make${srNo}" style="width:150px !important">
                                                <option value="">--Select--</option>
                                                <c:forEach items="${travelcardPartList.value}" var="makeList">
                                                <option value='${makeList}' title='${makeList}'>${makeList}</option>
                                                </c:forEach>
                                                </select>
                                                <%--<input name="partVendorName${srNo}" type="text" id="PartVendor Name${srNo}" style="width:300px !important" title="" value="" maxlength="40"/>--%>

                                            </td>

                                            <td align="left"  nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px">
                                                <input name="partSerialNo${srNo}" type="text" id="PartSerial No${srNo}" style="width:250px !important" title="" value="" maxlength="40"/>
                                            </td>
                                </tr>
                                <c:set var="srNo" value="${srNo+1}"/>
                                <c:set var="totallist" value="${totallist+1}"/>
                        </c:forEach></c:if>
                                
                        <%--<c:if test="${srNo<=5}">
                        <c:forEach begin="${srNo}" end="${tsrNo}" var="tsrNo">
                            
                                <tr bgcolor="#FFFFFF" height="20">
                                <input type="hidden" name="actiontakens" value="${srNo}"/>
                                <td align="center">${srNo}</td>
                                <td align="left" nowrap  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                    <input name="partNo${srNo}" type="text" id="Part No${srNo}" style="width:300px !important" title="" value="" maxlength="20"/>
                                </td>

                                <td align="left" nowrap  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                    <input name="partVendorName${srNo}" type="text" id="PartVendor Name${srNo}" style="width:300px !important" title="" value="" maxlength="40"/>

                                </td>

                                <td align="left"  nowrap  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;padding-top: 2px;padding-bottom: 2px">
                                    <input name="partSerialNo${srNo}" type="text" id="PartSerial No${srNo}" style="width:250px !important" title="" value="" maxlength="40"/>
                                </td>
                                </tr>
                                <c:set var="totallist" value="${totallist+1}"/>
                                <c:set var="srNo" value="${srNo+1}"/>
                        </c:forEach></c:if>--%>

                        <%--<c:if test="${srNo==1}">
                            <c:forEach begin="1" end="${tsrNo}" var="tsrNo">
                                <tr bgcolor="#FFFFFF" height="20">
                                <input type="hidden" name="actiontakens" value="${srNo}"/>
                                <td align="center">${tsrNo}</td>
                                <td align="left" nowrap  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                    <input name="partNo${tsrNo}" type="text" id="Part No${tsrNo}" style="width:300px !important" title="" value="" maxlength="20"/>
                                </td>

                                <td align="left" nowrap  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                    <input name="partVendorName${tsrNo}" type="text" id="PartVendor Name${tsrNo}" style="width:300px !important" title="" value="" maxlength="40"/>

                                </td>

                                <td align="left"  nowrap  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;padding-top: 2px;padding-bottom: 2px">
                                    <input name="partSerialNo${tsrNo}" type="text" id="PartSerial No${tsrNo}" style="width:250px !important" title="" value="" maxlength="40"/>
                                </td>
                                </tr>
                                <c:set var="srNo" value="${srNo+1}"/>
                                <c:set var="totallist" value="${totallist+1}"/>
                            </c:forEach>
                        </c:if>--%>

                        </tbody>

                        <%--<tr bgcolor="#FFFFFF">
                            <td align="right" colspan="4" style="padding-right:12px;">
                                <input name="Button" type="button" value="+" onclick="addRows(${totallist})"/>
                            </td>
                        </tr>--%>

                        <%--<tr bgcolor="#FFFFFF" align="center" height="5">
                            <td  colspan="4" >
                                <input name="ListSize" id="ListSize" type="hidden" value="${srNo}"/>

                            </td>
                        </tr>--%>

                    </table><%--</td>
                    </tr>
                                </table>--%>


                            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >

                                <tr  height="20">
                                    <td align="left" class="headingSpas"><input name="ListSize" id="ListSize" type="hidden" value="${srNo}"/>
                                        <B><bean:message key="label.common.pdilabel4checklist" /></B>
                                    </td>
                                </tr>
                                <tr bgcolor="#FFFFFF" height="5"></tr>

                               <%-- <div class="message1" id="msg_saveFAILED"></div>--%>
                                <tr>
                                    <td>
                                        <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td nowrap align="center" width="7%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.sno" /></label></td>
                                                <td width="33%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.activity" /></label></td>
                                                <td width="10%" align="center" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.ok" /> </label></td>
                                                <td width="25%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.actiontaken" /></label></td>
                                                <td width="25%"  align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.remarks" /></label></td>
                                                <!--<td width="13%"><label>Status</label></td>-->
                                           </tr>

                                            <c:set var="count" value="1"/>
                                            <c:forEach items="${pdiform.dataMap}" var="contentList">
                                                <c:choose>
                                                    <c:when test="${empty contentList.value}">
                                                        <tr bgcolor="#FFFFFF" height="20">
                                                            <td width="7%" align="center">&nbsp;</td>
                                                            <td align="left" ><label><strong>${contentList.key.contentDesc}</strong></label><input type="hidden" name="contentId" value="${contentList.key.contentId}"/></td>
                                                            <td align="center">${contentList.key.contentId}
                                                                <select name="${contentList.key.contentId}checkpoints" style="width:75px !important ">
                                                                    <option value="OK" selected>OK</option>
                                                                    <option value="NOT OK">NOT OK</option>
                                                                </select>
                                                            </td>
                                                            <td align="left" style="padding-bottom:3px;padding-top:2px;padding-left:3px;padding-right:3px;"><input type="text" name="${contentList.key.contentId}actionTaken" id="Action Taken" maxlength="100" style="width:240px !important "/></td>
                                                            <td align="left" style="padding-bottom:3px;padding-top:2px;padding-left:3px;padding-right:3px;"><input type="text" name="${contentList.key.contentId}observations" id="Observation" maxlength="100" style="width:240px !important "/></td>
                                                        </tr>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <tr bgcolor="#FFFFFF" >
                                                            <td height="25" colspan="5">
                                                                <div align="left" class="headingSpas">
                                                                    <label><strong> &nbsp;${contentList.key.contentDesc}</strong></label>
                                                                </div>
                                                               <input type="hidden" name="contentId" value="${contentList.key.contentId}"/>
                                                            </td>
                                                        </tr>
                                                        <%-- <c:set var="count" value="1"/>--%>
                                                        <c:set var="i" value="1"/>
                                                        <c:forEach items="${contentList.value}" var="subCList">
                                                            <tr bgcolor="#FFFFFF">
                                                                <td width="3%" align="center">${i}.</td>
                                                                <td align="left" style="padding-left:3px;padding-right:3px;">${subCList.subContentDesc}<input type="hidden" name="${contentList.key.contentId}SubContent" value="${subCList.subContentId}"/></td>
                                                                <td align="center">
                                                                    <select name="${contentList.key.contentId}checkpoints" id="checkpoints${count}" style="width:75px !important " >
                                                                        <c:if test="${subCList.textBoxOption eq 'NOT OK'}">
                                                                            <option value="OK">OK</option>
                                                                            <option  value="NOT OK" selected>NOT OK</option>
                                                                        </c:if>
                                                                        <c:if test="${subCList.textBoxOption ne 'NOT OK'}">
                                                                            <option value="OK" selected>OK</option>
                                                                            <option  value="NOT OK">NOT OK</option>
                                                                        </c:if>

                                                                    </select>
                                                                </td>
                                                                <td align="left" style="padding-bottom:3px;padding-top:2px;padding-left:3px;padding-right:3px;"><input type="text" name="${contentList.key.contentId}actionTaken" value="${subCList.actiontaken}" id="Action Taken${count}" maxlength="100"  style="width:240px !important "/></td>
                                                                <td align="left" style="padding-bottom:3px;padding-top:2px;padding-left:3px;padding-right:3px;"><input type="text" name="${contentList.key.contentId}observations" value="${subCList.observations}" id="Observation${count}" maxlength="100"  style="width:240px !important "/></td>
                                                            </tr>
                                                            <c:set var="i" value="${i+1}"/>
                                                            <c:set var="count" value="${count+1}"/>
                                                        </c:forEach>
                                                   </c:otherwise>
                                               </c:choose>
                                            </c:forEach>
                                             <tr bgcolor="#FFFFFF" height="40">
                                                 <td align="left" >
                                                  <span class="headingSpas">
                                                      <label><B><bean:message key="label.common.anyobservation" /></B></label>
                                                 </span></td>
                                               <td colspan="4" align="left"  width="80%">
                                                   <textarea name="remark" rows="4" id="Any Observation" onkeyup="textLimit(this,400)">${serviceform.compVerbatim[count]}</textarea>
                                               </td>
                                           </tr>

                                           <tr bgcolor="#FFFFFF" height="40">
                                                <td  align="center" colspan="2" >
                                                <label><B><bean:message key="label.common.createjobcard" /></B></label>
                                                <input type="radio" name="createJC" id="CreateJC" value="Yes"> <B>Yes</B>
                                                <input type="radio" name="createJC"  id="CreateJC" value="No" checked> <B> No</B>
                                                </td>
                                                <td colspan="3" align="left" style="padding-left: 20px;" >
                                                    <input name="input" type="button" value="Submit" onclick="submitForm(${count},${srNo});"/>
                                                </td>
                                            </tr>
                                        </table>

                                    </td>
                                </tr>
                            </table>
                            </form>
                            </td></tr>
                            </table>
                                </div>
                                </div>
                                </center>
                            