<%-- 
    Document   : createPI
    Created on : Jan 5, 2016, 5:15:28 PM
    Author     : prashant.kumar
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%> 
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script src="JS/Master_290414.js"></script>
<link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >

<script type="text/javascript">
    var partList="";
    function enableLeadTime(rowIndex,index,priceCode) {
        var partNo=document.getElementById("orderPartNoList"+index).value;
        var status=document.getElementById("statusId"+index).value;
        if(partList.indexOf(partNo)>-1){
            if(status != "Alternate Available"){
                alert(not_change_status_msg+" for Part No. "+partNo);
                document.getElementById("statusId"+index).value='Alternate Available';
            }
        }
        if(status=="Available with Lead Time"){
            document.getElementById("leadId"+index).readOnly=false;
        }else{
            document.getElementById("leadId"+index).value="";
            document.getElementById("leadId"+index).readOnly=true;
        }
        if(status=="Alternate Available"){
            document.getElementById("leadId"+index).readOnly=true;
            if(partList.indexOf(partNo+"@@"+index)>-1){
                alert("Alternate for "+partNo+" already added.");
            }else{
                getAlternateParts(partNo,index,rowIndex,priceCode);
                
            }
        }
        if(status == 'Not Available'){
            document.getElementById("piQtyList"+index).value='0';
            document.getElementById("piQtyList"+index).readOnly=true;
            calculateAmount(index);
        }else{
            document.getElementById("piQtyList"+index).readOnly=false;
        }
    }

    function getAlternateParts(partNo,index,rowIndex,priceCode){
        var alternatePartList="";
        if(partNo != ""){
            var todate = new Date().getTime();
            var strURL = '<%=cntxpath%>/createPIAction.do?option=getAlternateParts&partNo=' + partNo.toUpperCase() + '&dealerCode=${piForm.userId}&priceCode='+priceCode+'&tm=' + todate;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete"){
                    var alternatePart=xmlHttp.responseText;
                    if(alternatePart != ""){
                        partList = partList+","+partNo+"@@"+index;
                        alternatePartList=alternatePart.split("##");
                        addAlternatePartToTable(alternatePartList,index,rowIndex);
                    }else{
                        alert(no_alternate_part_available+partNo);
                        document.getElementById("statusId"+index).value="Available";
                    }
                }
            };
            xmlHttp.open("GET", strURL, true);
            xmlHttp.send(null);
        }
    }

    function addAlternatePartToTable(alternatePartList,index,rowIndex){
        var table=document.getElementById("partPITable");
        var rowLength=table.rows.length;
        var lastIndex=rowLength - 4;
        var partDetail;
        if(alternatePartList[0] != ""){
            document.getElementById("availablePart"+index).innerHTML=alternatePartList[0].split("@@")[0];
            document.getElementById("avlPartList"+index).value=alternatePartList[0].split("@@")[0];
            document.getElementById("availablePartDesc"+index).innerHTML=alternatePartList[0].split("@@")[1];
            document.getElementById("priceList"+index).value=alternatePartList[0].split("@@")[3];
            document.getElementById("priceListspan"+index).innerHTML=alternatePartList[0].split("@@")[3];
            document.getElementById("amount"+index).innerHTML=parseFloat(alternatePartList[0].split("@@")[3])*parseInt(document.getElementById("piQtyList"+index).value);
            calculateAmount(index);
        }
        var rowCount=parseInt(lastIndex)+1;
        for(var i=1;i<alternatePartList.length;i++){
            if(alternatePartList[i] != ""){
                partDetail=alternatePartList[i].split("@@");
                var row = table.insertRow(parseInt(rowIndex)+1);
                var cellCount=0;
                row.height="30px";
                row.bgColor="#FFFFFF";
                var cell = row.insertCell(cellCount);   //1
                cell.align ='center';
                cell.width ='5%';
                cell.className="headingSpas";
                cell.innerHTML ="<input type=\"hidden\" name=\"poIdList\" id=\"poId"+rowCount+"\" value='"+document.getElementById("poId"+index).value+"'/>";

                cellCount +=1;//2
                cell = row.insertCell(cellCount);
                cell.align ='left';
                cell.width ='10%';
                cell.className="headingSpas";
                cell.innerHTML =document.getElementById("orderPartNoList"+index).value+
                    "<input type=\"hidden\" name=\"orderPartNoList\" id=\"orderPartNoList"+rowCount+"\" value='"+document.getElementById("orderPartNoList"+index).value+"'/>";

                cellCount +=1;//3
                cell = row.insertCell(cellCount);
                cell.align ='left';
                cell.width ='10%';
                cell.className="headingSpas";
                cell.innerHTML =document.getElementById("orderedPartDesc"+index).innerHTML;

                cellCount +=1;//4
                cell = row.insertCell(cellCount);
                cell.align ='left';
                cell.width ='10%';
                cell.className="headingSpas";
                var poNo=document.getElementById("poDetailIdList"+index).value+"<input type=\"hidden\" name=\"poDetailIdList\" id=\"poDetailIdList"+rowCount+"\" value='"+document.getElementById("poDetailIdList"+index).value+"'/>";
                cell.innerHTML =poNo;

                cellCount +=1;//5
                cell = row.insertCell(cellCount);
                cell.align ='center';
                cell.width ='10%';
                cell.className="headingSpas";
                cell.innerHTML ="0"+"<input type=\"hidden\" name=\"pendingQtyList\" id=\"pendingQtyList"+rowCount+"\" value=\"0\"/>";

                cellCount +=1;//6
                cell = row.insertCell(cellCount);
                cell.align ='center';
                cell.width ='10%';
                cell.className="headingSpas";
                cell.innerHTML ="<select name=\"statusList\" id=\"statusId"+rowCount+"\" class=\"headingSpas\" style=\"background-color: #FFFFFF; width:130px !important\">"+
                    "<option value=\"Alternate Available\">Alternate Available</option></select>";
                cellCount +=1;//7
                cell = row.insertCell(cellCount);
                cell.align ='center';
                cell.width ='5%';
                cell.className="headingSpas";
                cell.innerHTML ="<input type=\"text\" name=\"leadTimeList\" id=\"leadId"+rowCount+"\" class=\"headingSpas\" readOnly=\"true\" style=\"height: 18px\"/>";

                cellCount +=1;//8
                cell = row.insertCell(cellCount);
                cell.align ='left';
                cell.width ='10%';
                cell.className="headingSpas";
                cell.innerHTML =partDetail[0]+"<input type=\"hidden\" name=\"avlPartList\" id=\"avlPartList"+rowCount+"\" value='"+partDetail[0]+"'/>";

                cellCount +=1;//9
                cell = row.insertCell(cellCount);
                cell.align ='left';
                cell.width ='10%';
                cell.className="headingSpas";
                cell.innerHTML =partDetail[1];

                cellCount +=1;//10
                cell = row.insertCell(cellCount);
                cell.align ='center';
                cell.width ='10%';
                cell.className="headingSpas";
                cell.innerHTML ="<input type=\"text\" name=\"piQtyList\" id=\"piQtyList"+rowCount+"\" value=\"0\" onblur=\"calculateAmount("+rowCount+");\"/>";

                cellCount +=1;//11
                cell = row.insertCell(cellCount);
                cell.align ='right';
                cell.width ='10%';
                cell.className="headingSpas";
                cell.innerHTML ="<span id=\"priceListspan"+rowCount+"\">"+partDetail[3]+"</span>"+
                    "<input type=\"hidden\" name=\"priceList\" id=\"priceList"+rowCount+"\" value=\""+partDetail[3]+"\"/>";
                cellCount +=1;//12
                cell = row.insertCell(cellCount);
                cell.align ='right';
                cell.width ='10%';
                cell.className="headingSpas";
                cell.innerHTML ="<span id=\"amount"+rowCount+"\">0.0</span>";
                rowCount++;
            }
        }
    }

    function validateForm() {
        var rows=document.getElementById("partPITable").rows.length;
        if(document.getElementById("Payment Terms").value==''){
            alert(not_blank_dropdown_validation_msg+" Terms of Delivery Payment.");
            document.getElementById("Payment Terms").focus();
            return false;
        }else if(!expValidatedSTD(document.getElementById("preCarriageId").value)){
            alert(specialChar_validation_msg+" Pre Carriage By.");
            document.getElementById("preCarriageId").value='';
            document.getElementById("preCarriageId").focus();
            return false;

        }else if(!expValidatedSTD(document.getElementById("placeId").value)){
            alert(specialChar_validation_msg+" Place of Receipt of Pre-Carrier.");
            document.getElementById("placeId").value='';
            document.getElementById("placeId").focus();
            return false;
        }else{
            for(var index=1;index<rows-3;index++){
                if(document.getElementById("statusId"+index).value == ''){
                    alert(not_blank_dropdown_validation_msg+" Status.");
                    document.getElementById("statusId"+index).focus();
                    return false;
                }else if(document.getElementById("statusId"+index).value == "Available with Lead Time"){
                    if(document.getElementById("leadId"+index).value==''){
                        alert(please_Enter_msg+" Lead Time field.");
                        document.getElementById("leadId"+index).focus();
                        return false;
                    }else if(!expValidatedSTD(document.getElementById("leadId"+index).value)){
                        alert(specialChar_validation_msg+" Lead Time.");
                        document.getElementById("leadId"+index).value='';
                        document.getElementById("leadId"+index).focus();
                        return false;
                    }
                }
                if(!(document.getElementById("statusId"+index).value=="Not Available"||document.getElementById("statusId"+index).value=="Alternate Available") && document.getElementById("piQtyList"+index).value == 0){
                    alert(qty_msg);
                    document.getElementById("piQtyList"+index).focus();
                    return false;
                }else{
                    document.getElementById("leadId"+index).removeAttribute("disabled");
                }
            }
        }
    }
    
    function clcAmount(index){
        if(index != '0'){
            var priice=document.getElementById("priceList"+index).value;
            var quantity=document.getElementById("piQtyList"+index).value;
            quantity=parseInt(quantity);
            document.getElementById("piQtyList"+index).value=quantity;
            if(!checkForChar("piQtyList"+index,"PI Quantity",quantity)){
                return false;
            }
            document.getElementById("amount"+index).innerHTML=parseFloat(priice*quantity).toFixed(2);
        }
    }
    function checkQty(index){
        if(index != '0'){
            var status=document.getElementById("statusId"+index).value;
            var pendingQty=document.getElementById("pendingQtyList"+index).value;
            var piQty=document.getElementById("piQtyList"+index).value;
            if(status != 'Alternate Available' && (parseInt(piQty)>parseInt(pendingQty))){
                alert(qty_msg1);
                document.getElementById("piQtyList"+index).value='0';
                document.getElementById("piQtyList"+index).focus();
                return false;
            }
        }
    }

    function calculateAmount(index){
        if(checkQty(index) == false){
            return false;
        }
        var rows=document.getElementById("partPITable").rows.length;
        var rowsOtherCharge=document.getElementById("otherChargesTable").rows.length;
        clcAmount(index)

        var total=0.0;
        var otherChargeList=0.0;
        var totalWithOthercharges=0.0;
        var amount=0.0;
        for(var i=1;i<rows-3;i++){
            amount=document.getElementById("amount"+i).innerHTML;
            total =parseFloat(total)+parseFloat(amount);
        }
        document.getElementById("partValueId").value=parseFloat(total).toFixed(2);

        for(var i=1;i<=rowsOtherCharge;i++){
            otherChargeList=document.getElementById("otherChargeList"+i).value;
            if(!checkForChar("otherChargeList"+i,"Other Charges",otherChargeList)){
                return false;
            }
            if(otherChargeList == ''){
                otherChargeList=0;
            }
            totalWithOthercharges = parseFloat(totalWithOthercharges)+parseFloat(otherChargeList);
        }
        document.getElementById("totalAmountFinalId").value=(parseFloat(total)+parseFloat(totalWithOthercharges)).toFixed(2);  //totalAmount
    }
    function checkForChar(idIndex,label,value){
        if(isNaN(value)){
            alert(only_numbers+label);
            document.getElementById(idIndex).value="";
            document.getElementById(idIndex).focus();
            return false;
        }else{
            return true;
        }
    }
    function expValidatedSTD(str) {
        var regularExpression = /^[ A-Za-z0-9_@./#&+-]*$/;
        var valid = regularExpression.test(str);
        return valid;
    }
</script>

<html>
    <head><title>ITLDIS</title></head>

    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/createPIAction.do?option=getListForInitPI'><bean:message key="label.spare.initCreatePI" /></a></li>
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/createPIAction.do?option=getorderListForCreatePI&searchFlag=true&dealerCode=${piForm.dealerCode}&orderType=${piForm.orderType}'><bean:message key="label.common.orderPendingPI" /></a></li>
            <li><bean:message key="label.spare.createPI" /></li>
        </ul>
    </div>

    <div class="message" id="msg_saveFAILED"><html:errors/>${message}</div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" >
                <h1 class="hText"><bean:message key="label.spare.createPI" /></h1>

                <form action="createPIAction.do?option=savePIDetails" method="post" id="searchBy" onsubmit="return validateForm();">
                    <input type="hidden" name="currency" value="${piForm.currency}"/>
                    <input type="hidden" name="priceListCode" value="${piForm.priceListCode}"/>
                    <table  width=100%  border=0 cellspacing=4 cellpadding=6 bordercolor=#cccccc bgcolor=#FFFFFF>
                        <tr height=50 bgcolor="#FFFFFF" style="padding-top: 10px; padding-bottom: 10px">
                            <td align= center class="headingSpas">
                                <input type="hidden" name="<%= Constants.TOKEN_KEY%>" value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" />
                                <input type="hidden" name="dealerCode" id="dealerCode" value="${piForm.dealerCode}"/>
                                <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a><a href='${pageContext.request.contextPath}/createPIAction.do?option=getListForInitPI'><bean:message key="label.spare.initCreatePI" /></a>@@@@<bean:message key="label.spare.createPI" />"/>
                                <table width=100%  border=0 cellspacing=3 cellpadding=3 bgcolor=#FFFFFF>
                                    <tr>
                                        <td width="15%" align="right">
                                            <input type="hidden" name="poNo" id="poNo" value="${piForm.poNo}"/>
                                            <label><bean:message key="label.spare.orderType" /> :</label></td>
                                        <td width="15%" align="left"> ${piForm.orderType}</td>
                                        <td width="15%" align="right" ><label><bean:message key="label.spare.orderStatus" /> :</label></td>
                                        <td width="15%" align="left">${piForm.status}</td>
                                        <td width="15%" align="right" ><label><bean:message key="label.spare.dealRefNo" /> :</label></td>
                                        <td width="25%" align="left">${piForm.dealerRefNo}</td>
                                    </tr>
                                    <tr>

                                        <td align="right"><label><bean:message key="label.spare.consigneeName" /> :</label></td>
                                        <td  align="left">
                                            <c:if test="${piForm.consigneeCode eq '-' && piForm.consigneeName eq '-'}">
                                                - -
                                            </c:if>
                                            <c:if test="${piForm.consigneeCode ne '-' && piForm.consigneeName ne '-'}">
                                                ${piForm.consigneeCode} [ ${piForm.consigneeName} ]
                                            </c:if>
                                            <input type="hidden" name="consigneeCode" value="${piForm.consigneeCode}"/>
                                            <input type="hidden" name="consigneeName" value="${piForm.consigneeName}"/>
                                        </td>
                                        <td  align="right" ><label><bean:message key="label.spare.consigneeCountry" /> :</label></td>
                                        <td  align="left">${piForm.consigneeCountry}</td>
                                        <td  align="right" ><label><bean:message key="label.spare.destinationPlace" /> :</label></td>
                                        <td  align="left">${piForm.destinationPlace}</td>
                                    </tr>
                                    <c:if test="${piForm.orderType eq 'VOR'}">
                                        <tr>
                                            <td  align="right" style="height: 10px"><label><bean:message key="label.common.capital.jobcardno" /> :</label></td>
                                            <td  align="left"> ${piForm.firNo}</td>
                                            <td  align="right" ><label><bean:message key="label.catalogue.chassisNo" /> :</label></td>
                                            <td  align="left">${piForm.chassisNo}</td>
                                            <td align="right"><label><bean:message key="label.common.engineno" /> :</label></td>
                                            <td  align="left">${piForm.engineNo}</td>
                                        </tr>
                                        <tr>
                                            <td  align="right"><label><bean:message key="label.warranty.modelNo" /> :</label></td>
                                            <td  align="left"> ${piForm.modelNo}</td>
                                            <td  align="left">&nbsp;</td>
                                            <td  align="left">&nbsp;</td>
                                            <td  align="left">&nbsp;</td>
                                            <td align="left">&nbsp;</td>
                                        </tr>
                                    </c:if>
                                    <tr>
                                        <td  align="right"><label><bean:message key="label.common.modeOfDispatch" /> :</label></td>
                                        <td align="left"> ${piForm.deliveryCode}</td>
                                        <td  align="right" ><label><bean:message key="label.spare.incoTerms" /> :</label></td>
                                        <td  align="left">${piForm.incoTerms}</td>
                                        <td  align="right" ><label><bean:message key="label.spare.dischargePortName" /> :</label></td>
                                        <td  align="left">${piForm.dischargePort}</td>
                                    </tr>
                                    <tr>
                                        <td  align="right" ><label><bean:message key="label.spare.comments" /> :</label></td>
                                        <td  align="left" colspan="3">${piForm.comments}</td>
                                        <td  align="right" ><label><bean:message key="label.common.order.remark" /> :</label></td>
                                        <td  align="left" >${piForm.erpRemarks}</td>
                                    </tr>
                                    <tr bgcolor="#FFFFFF" style="padding-top: 10px;">
                                        <td align="right"width="100px" class="headingSpas">
                                            <label><bean:message key="label.common.preCarriageBy" /> :</label>
                                        </td>
                                        <td align="left" style="padding-top: 5px">
                                            <input type="text" name="precarriageBy" id="preCarriageId" value="${piForm.precarriageBy}" style="height: 18px; width:100px !important"/>
                                        </td>                                    
                                        <td align="right"class="headingSpas"  width="110px">
                                            <label><bean:message key="label.common.placeOfReceipt" /> :</label>
                                        </td>
                                        <td align="left" style="padding-top: 5px" >
                                            <input type="text" name="placePreCarrier" id="placeId" value="${piForm.placePreCarrier}" style="height: 18px; width:100px !important"/>
                                        </td>                                                                           
                                        <td align="right" class="headingSpas" width="20%">
                                            <label><bean:message key="label.spare.paymentTerms" /></label><span class="mandatory"> *</span> :
                                        </td>
                                        <td align="left" style="padding-top: 5px" >
                                            <select id="Payment Terms" name="paymentTerms" class="headingSpas" style="width:180px !important; margin:0px!important">
                                                <option value="" >--Select Here--</option>
                                                <c:forEach items="${paymentTermsList}" var="paymentTermsList">
                                                    <c:if test="${piForm.paymentTerms eq paymentTermsList.value}">
                                                        <option value='${paymentTermsList.value}' title='${paymentTermsList.label}' selected>${paymentTermsList.label} </option>
                                                    </c:if>
                                                    <c:if test="${piForm.paymentTerms ne paymentTermsList.value}">
                                                        <option value='${paymentTermsList.value}' title='${paymentTermsList.label}'>${paymentTermsList.label} </option>
                                                    </c:if>
                                                </c:forEach>

                                            </select>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <c:if test="${!empty orderList}">
                            <tr height=25 bgcolor="#FFFFFF">
                                <td align= center class="headingSpas">
                                    <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc id="partPITable">
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;">
                                                <bean:message key="label.common.sno" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.orderedPart" />
                                            </td>
                                            <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px; width: 200px" >
                                                <bean:message key="label.common.partDescPI" />
                                            </td>
                                            <td nowrap align="left"width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.poNo" />
                                            </td>
                                            <td nowrap align="left" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.orderedQtyPI" />
                                            </td>
                                            <td nowrap align="left"width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.selectStatus" />
                                            </td>
                                            <td nowrap align="left" width="8%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >

                                                <bean:message key="label.common.leadTime" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.availablePartNo" />
                                            </td>
                                            <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.partDescPI" />
                                            </td>
                                            <td nowrap align="center" width="8%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.piQty" />
                                            </td>
                                            <td nowrap align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.pricePI" /><br/>(${piForm.currency})
                                            </td>
                                            <td nowrap align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.amountPI" /><br/>(${piForm.currency})
                                            </td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <c:set var="total" value="0"></c:set>
                                        <logic:iterate id="partList" name="orderList" >
                                            <tr  bgcolor="#eeeeee" height="20">
                                            <input type="hidden" name="poIdList" id="poId${sno}" value="${partList.poId}"/>
                                            <td nowrap align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                            <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${partList.partNo}
                                                <input type="hidden" name="orderPartNoList" id="orderPartNoList${sno}" value="${partList.partNo}"/></td>
                                            <td align="left" width="15%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span id="orderedPartDesc${sno}">${partList.partDesc}</span></td>
                                            <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${partList.poNo}
                                                <input type="hidden" name="poDetailIdList" id="poDetailIdList${sno}" value="${partList.poNo}"/></td>
                                            <td align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:0px;FONT-SIZE: 11px;">${partList.pendingQty}
                                                <input type="hidden" name=="pendingQtyList" id="pendingQtyList${sno}" value="${partList.pendingQty}"/>
                                            </td>
                                            <td bgcolor="#FFFFFF" align="left" width="10%">
                                                <select name="statusList" id="statusId${sno}" class="headingSpas" style="background-color: #FFFFFF; width:130px !important" onchange="enableLeadTime(this.parentNode.parentNode.rowIndex,'${sno}','${piForm.priceListCode}');">
                                                    <%--<option value="">-Select-</option>--%>
                                                    <option value="Available">Available</option>
                                                    <option value="Available with Lead Time">Available with Lead Time</option>
                                                    <option value="Alternate Available">Alternate Available</option>
                                                    <option value="Not Available">Not Available</option>
                                                </select>
                                            </td>
                                            <td align="left" width="5%"  bgcolor="#FFFFFF">
                                                <input type="text" name="leadTimeList" id="leadId${sno}" class="headingSpas" readOnly=true style="height: 18px" />
                                            </td>
                                            <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span id="availablePart${sno}">${partList.availablePartNoPI}</span>
                                                <input type="hidden" name="avlPartList" id="avlPartList${sno}"/></td>
                                            <td align="left" width="15%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span id="availablePartDesc${sno}">${partList.avlPartDescPI}</span></td>
                                            <td align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                <input type="text" name="piQtyList" id="piQtyList${sno}" value="${partList.pendingQty}" onblur="calculateAmount('${sno}');"/></td>
                                            <td align="right" width="10%" bgcolor="#FFFFFF" style="padding-right:3px;FONT-SIZE: 11px;"><span id="priceListspan${sno}">${partList.price}</span>
                                                <input type="hidden" name="priceList" id="priceList${sno}" value="${partList.price}"/></td>                                                
                                            <td align="right" width="10%" bgcolor="#FFFFFF" style="padding-right:3px;FONT-SIZE: 11px;" ><span id="amount${sno}">
                                                    <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${partList.price * partList.pendingQty}"/>
                                                    <%--${partList.price * partList.pendingQty}--%></span>
                                            </td>
                                </tr>
                                <c:set var="sno" value="${sno + 1 }" />
                                <c:set var="total" value="${total + partList.price * partList.pendingQty}"></c:set>
                            </logic:iterate>
                            <tr height=20 bgcolor="#FFFFFF">
                                <td align="right" colspan="12">
                                    <B> <bean:message key="label.common.MaterialValue" /> </B>
                                    <fmt:formatNumber var="total" type="number" maxFractionDigits="2" minFractionDigits="2" value="${total}"/>
                                    <input type="text" name="totalValue" id="partValueId" value="${total}" readonly style="text-align: right"/>
                                </td>
                            </tr>
                            <tr  bgcolor="#FFFFFF">
                                <td colspan="12">
                                    <table align="right" width="20%" id="otherChargesTable">
                                        <c:set var="chargeSno" value="1"></c:set>
                                        <logic:iterate id="chargeList" name="otherChargeList" >
                                            <tr bgcolor="#FFFFFF">
                                                <td class="headingSpas" style="padding-right:3px;" align="right">${chargeList.label}
                                                    <input type="hidden" name="otherchargeType" id="otherchargeType${chargeSno}" value="${chargeList.value}"/></td>
                                                <td class="headingSpas" style="padding-left:3px;" align="right">
                                                    <input type="text" name="otherChargeList" id="otherChargeList${chargeSno}" onblur="calculateAmount('0');"/>
                                                </td>
                                            </tr>
                                            <c:set var="chargeSno" value="${chargeSno+1}"></c:set>
                                        </logic:iterate>
                                    </table>
                                </td>
                            </tr>
                            <tr  bgcolor="#FFFFFF">
                                <td align="right" class="headingSpas" colspan="12" >
                                    <B> <bean:message key="label.common.TotalAmount" /> </B>
                                    <input type="text" name="totalAmountFinal" id="totalAmountFinalId" value="${total}"  readonly style="text-align: right"/>
                                </td>
                            </tr>
                        </table>
                        </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <input type="reset"  value=" Reset " class="headingSpas" onclick="location.reload();"/>
                                &nbsp;
                                <input type="submit"  value="Submit" class="headingSpas"/>
                            </td>
                        </tr>
                    </c:if>

                    <c:if test="${empty orderList}">
                        <tr  bgcolor="#eeeeee" height="10">
                            <td align= "center" style="color: red;">
                                <bean:message key="label.common.noRecordFound"/></td>
                        </tr>
                    </c:if>
                    </table>
                </form>

            </div>
        </div>
    </center>
</body>
</html>
