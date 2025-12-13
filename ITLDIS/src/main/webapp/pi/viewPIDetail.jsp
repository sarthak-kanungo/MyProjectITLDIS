<%--
    Document   : viewPIDetail
    Created on : 12 Jan, 2016, 12:02:50 PM
    Author     : yogasmita.patel
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" />

<script type="text/javascript">
    function enableLeadTime(index) {
        var status=document.getElementById("statusId"+index).value;
        if(status=="Available with Lead Time"){
            document.getElementById("leadId"+index).readOnly=false;
        }else{
            document.getElementById("leadId"+index).value="";
            document.getElementById("leadId"+index).readOnly=true;
        }
        if(status == 'Not Available'){
            document.getElementById("piQtyList"+index).value='0';
            document.getElementById("piQtyList"+index).readOnly=true;
            calculateAmount(index);
        }else{
            document.getElementById("piQtyList"+index).readOnly=false;
        }
    }
    function validateForm() {
        var rows=document.getElementById("partPITable").rows.length;
        for(var index=1;index<rows-3;index++){
            if(document.getElementById("statusId"+index).value == "Available with Lead Time"){
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
            if((!(document.getElementById("statusId"+index).value.indexOf("Not") > -1)) && document.getElementById("piQtyList"+index).value == 0){
                alert(qty_msg);
                document.getElementById("piQtyList"+index).focus();
                return false;
            }else{
                document.getElementById("leadId"+index).removeAttribute("disabled");
            }
        }
    }
    function checkQty(index){
        if(index != '0'){
            var status=document.getElementById("statusId"+index).value;
            var orderedQty=document.getElementById("orderedQtyList"+index).value;
            var piQty=document.getElementById("piQtyList"+index).value;
            if(status != 'Alternate Available' && (parseInt(piQty)>parseInt(orderedQty))){
                alert(qty_msg1);
                document.getElementById("piQtyList"+index).value=document.getElementById("finalQtyList"+index).value;
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
        var total=0.0;
        var otherChargeList=0.0;
        var totalWithOthercharges=0.0;
        var amount=0.0;
        for(var index1=1;index1<(rows-3);index1++){
            priice=document.getElementById("priceList"+index1).value;
            quantity=document.getElementById("piQtyList"+index1).value;
            amount=parseFloat(priice*quantity).toFixed(2);
            total =parseFloat(total)+parseFloat(amount);
        }
        for(var index2=1;index2<=rowsOtherCharge;index2++){
            otherChargeList=document.getElementById("otherChargeList"+index2).value;
            if(!checkForChar("otherChargeList"+index2,"Other Charges",otherChargeList)){
                return false;
            }
            if(otherChargeList == ''){
                otherChargeList=0;
            }
            totalWithOthercharges = parseFloat(totalWithOthercharges)+parseFloat(otherChargeList);
        }

        document.getElementById("totalValue").value=parseFloat(total).toFixed(2);
        document.getElementById("totalAmount").value=(parseFloat(total)+parseFloat(totalWithOthercharges)).toFixed(2);

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

    function submitForm(status){
        document.getElementById("status").value=status;
    }

    function viewPrint(piNo){
        var url="createPIAction.do?option=viewPrintPI_Tibco&piNo="+piNo;
        window.open(url);
    }

    $(document).ready(function(){
        $('.viewOrderDetails').click(function(){
            var url = "inventoryEXPAction.do?option=viewOrderDetail&poNo="+Base64.encode($(this).text().trim());
            $(this).attr("href",url);
        });
    });
</script>

<html>
    <head><title>ITLDIS</title></head>

    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/createPIAction.do?option=getPIListForView&orderType=ALL'><bean:message key="label.spare.initViewPI" /></a></li>
            <li><bean:message key="label.spare.initViewPIDetail" /></li>
        </ul>
    </div>

    <div class="message" id="msg_saveFAILED"><html:errors/>${message}</div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" >
                <h1 class="hText"><bean:message key="label.spare.initViewPIDetail" /></h1>
                <div style="overflow: auto">
                <table  width=100%  border=0 cellspacing=4 cellpadding=6 bordercolor=#cccccc bgcolor=#FFFFFF>
                    <tr height=50 bgcolor="#FFFFFF" style="padding-top: 10px; padding-bottom: 10px">
                        <td align= center class="headingSpas">
                            <html:form action="createPIAction.do?option=updatePIDetails" method="post" styleId="searchBy">
                                <html:hidden property="status" styleId="status" value=""/>
                                <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a><a href='${pageContext.request.contextPath}/createPIAction.do?option=getPIListForView&orderType=ALL'><bean:message key="label.spare.initViewPI" /></a>@@@@<bean:message key="label.spare.initViewPIDetail" />"/>
                                <table width=100%  border=0 cellspacing=3 cellpadding=3 bgcolor=#FFFFFF>
                                    <c:set var="status" value=""/>
                                    <logic:iterate id="piList" name="piList" >
                                        <tr>
                                            <td class="headingSpas" width="10%" align="right" style="padding-left: 2px"><label><bean:message key="label.spare.piNo" /> :</label></td>
                                            <td width="10%" align="left" nowrap>${piList.piNo}
                                                <c:set var="editAllowed" value="${piList.buyerEditAllowed}"/>
                                                <html:hidden property="piNo" styleId="piNo" value="${piForm.piNo}"/></td>
                                            <td width="10%" align="right" nowrap><label><bean:message key="label.spare.piDate" /> :</label></td>
                                            <td width="20%" align="left">${piList.piDate}</td>
                                            <td width="10%" align="right" nowrap><label><bean:message key="label.spare.orderType" /> :</label></td>
                                            <td width="10%" align="left"> ${piList.orderType}</td>
                                            <td width="10%" align="right" nowrap><label><bean:message key="label.spare.orderStatus" /> :</label></td>
                                            <td width="20%" align="left">${piList.status}
                                                <c:set var="status" value="${piList.status}"/>
                                            </td>
                                            <%if (request.getParameter("buyerEditAllowed").equals("OnlyView")) {%>
                                            <td style="padding-left:5px" align="left" >
                                                <input type="button" name="print"  value="Print" class="headingSpas" onclick="viewPrint('${piList.piNo}')"/>
                                            </td>
                                            <%}%>
                                        </tr>
                                        <tr>
                                            <td width="10%" align="right" nowrap ><label><bean:message key="label.spare.dealRefNo" /> :</label></td>
                                            <td width="10%" align="left">${piList.dealerCode}</td>
                                            <td width="10%" align="right" nowrap><label><bean:message key="label.spare.consigneeName" /> :</label></td>
                                            <td  width="20%" align="left">
                                                ${piList.consigneeName}
                                            </td>
                                            <html:hidden property="consigneeCode" value="${piList.consigneeCode}"/>
                                            <html:hidden property="consigneeName" value="${piList.consigneeName}"/>
                                            <td width="10%" align="right" nowrap><label><bean:message key="label.spare.consigneeCountry" /> :</label></td>
                                            <td width="10%" align="left">${piList.consigneeCountry}</td>
                                            <td width="10%" align="right" nowrap><label><bean:message key="label.spare.destinationPlace" /> :</label></td>
                                            <td width="20%" align="left">${piList.destinationPlace}</td>
                                        </tr>
                                        <c:if test="${piForm.orderType eq 'VOR'}">
                                            <tr>
                                                <td width="10%" align="right" style="height: 10px" nowrap><label><bean:message key="label.common.capital.jobcardno" /> :</label></td>
                                                <td width="10%" align="left"> ${piList.firNo}</td>
                                                <td width="10%" align="right" nowrap><label><bean:message key="label.catalogue.chassisNo" /> :</label></td>
                                                <td width="20%" align="left">${piList.chassisNo}</td>
                                                <td width="10%" align="right" nowrap><label><bean:message key="label.common.engineno" /> :</label></td>
                                                <td width="10%" align="left">${piList.engineNo}</td>
                                                <td width="10%" align="right" nowrap><label><bean:message key="label.warranty.modelNo" /> :</label></td>
                                                <td width="20%" align="left"> ${piList.modelNo}</td>
                                            </tr>
                                        </c:if>
                                        <tr class="headingSpas">
                                            <td width="10%" align="right" nowrap><label><bean:message key="label.common.modeOfDispatch" /> :</label></td>
                                            <td width="10%" align="left"> ${piList.deliveryCode}</td>
                                            <td width="10%" align="right" nowrap><label><bean:message key="label.spare.incoTerms" /> :</label></td>
                                            <td width="20%" align="left">${piList.incoTerms}</td>
                                            <td width="10%" align="right" nowrap><label><bean:message key="label.spare.dischargePortName" /> :</label></td>
                                            <td width="10%" align="left">${piList.dischargePort}</td>
                                            <td width="10%" align="right" >
                                                <span class="formLabel"><label><bean:message key="label.spare.paymentTerms" /> :</label></span>
                                            </td>
                                            <td width="20%" align="left" nowrap>
                                                ${piList.paymentTerms}
                                            </td>
                                        </tr>
                                        <tr class="headingSpas">
                                            <td width="10%" align="right" nowrap><label><bean:message key="label.common.order.remark" /> :</label></td>
                                            <td width="40%" align="left" colspan="3">${piList.erpRemarks}</td>
                                            <td width="10%" align="right" nowrap>
                                                <span class="formLabel"><label><bean:message key="label.common.preCarriageBy" /> :</label></span>
                                            </td>
                                            <td width="10%" align="left" >
                                                ${piList.precarriageBy}
                                            </td>
                                            <td width="10%" align="right" style="padding-left:10px" >
                                                <span class="formLabel"><label><bean:message key="label.common.placeOfReceipt" /> :</label></span>
                                            </td>
                                            <td width="20%" align="left"  >${piList.placePreCarrier}</td>
                                        </tr>
                                        <c:if test="${piList.erpOrderNo ne '-'}">
                                        <tr class="headingSpas">
                                            <td width="10%" align="right" nowrap><label><bean:message key="label.order.suppOrderNo" /> :</label></td>
                                            <td width="40%" align="left" colspan="4">${piList.erpOrderNo} [ ${piList.erpOrderDate} ]</td>

                                        </tr>
                                        </c:if>
                                    </logic:iterate>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
                    <div style="overflow: auto">
                        <table align="center" cellspacing=1 cellpadding=5 border=0 width=100% >
                            <c:if test="${!empty partDetailList}">
                                <tr height=25 bgcolor="#FFFFFF">
                                    <td align= center class="headingSpas">
                                        <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc id="partPITable">
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;">
                                                    <bean:message key="label.common.sno" />
                                                </td>
                                                <td align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.orderedPart" />
                                                </td>
                                                <td align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px; width: 200px" >
                                                    <bean:message key="label.common.partDescPI" />
                                                </td>
                                                <td align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px; width: 170px" >
                                                    <bean:message key="label.spare.poNo" />
                                                </td>
                                                <td align="center" width="8%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.orderedQtyPI" />
                                                </td>
                                                <td align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 100px" >
                                                    <bean:message key="label.common.selectStatus" />
                                                </td>
                                                <td align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.leadTime" />
                                                </td>
                                                <td align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.availablePartNo" />
                                                </td>
                                                <td align="center" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.avlPartDescPI" />
                                                </td>
                                                <td align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.basePIQty" />
                                                </td>
                                                <c:if test="${editAllowed eq 'N'}">
                                                    <td align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.acceptedPIQty" />
                                                    </td>
                                                </c:if>
                                                <td align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.piQty" />
                                                </td>
                                                <td align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.pricePI" /><br>(${piForm.currency})
                                                </td>
                                                <td align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.amountPI" /><br>(${piForm.currency})
                                                </td>
                                                <%if (request.getParameter("buyerEditAllowed").equals("OnlyView")) {%>
                                                <td align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.spare.BookedQty" />
                                                </td>
                                                <td align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.spare.PendingQty" />
                                                </td>
                                                <%}%>
                                            </tr>
                                            <c:set var="sno" value="1"></c:set>
                                            <c:set var="total" value="0"></c:set>
                                            <logic:iterate id="partList" name="partDetailList" >
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <html:hidden property="poIdList" styleId="poIdList${sno}" value="${partList.poId}"/>
                                                    <html:hidden property="piIdList" styleId="piIdList${sno}" value="${partList.piId}"/>
                                                    <td align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                    <td align="left" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${partList.partNo}
                                                        <html:hidden property="orderPartNoList" styleId="orderPartNoList${sno}" value="${partList.partNo}"/></td>
                                                    <td align="left" width="15%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${partList.partDesc}</td>
                                                    <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <span>
                                                            <a href="#" class="viewOrderDetails">${partList.poNo}</a>
                                                        </span>
                                                        <html:hidden property="poDetailIdList" styleId="poDetailIdList${sno}" value="${partList.poNo}"/></td>
                                                    <td align="center" width="10%" bgcolor="#FFFFFF" style="padding-left:0px;FONT-SIZE: 11px;">${partList.orderedQty}
                                                        <input type="hidden" name="orderedQtyList" id="orderedQtyList${sno}" value="${partList.orderedQty}"/>
                                                    </td>
                                                    <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:0px;FONT-SIZE: 11px;">
                                                        <%if (!request.getParameter("buyerEditAllowed").equals("OnlyView") && userFunctionalities.contains("861")) {%>
                                                        <select name="statusList" id="statusId${sno}" class="headingSpas" style="background-color: #FFFFFF; width:130px !important" onchange="enableLeadTime('${sno}');">
                                                            <c:if test="${partList.status eq 'AVAILABLE'}">
                                                                <option value="Available" selected>Available</option>
                                                                <option value="Available with Lead Time">Available with Lead Time</option>
                                                                <option value="Not Available">Not Available</option>
                                                            </c:if>
                                                            <c:if test="${partList.status eq 'AVAILABLE WITH LEAD TIME'}">
                                                                <option value="Available">Available</option>
                                                                <option value="Available with Lead Time" selected>Available with Lead Time</option>
                                                                <option value="Not Available">Not Available</option>
                                                            </c:if>
                                                            <c:if test="${partList.status eq 'NOT AVAILABLE'}">
                                                                <option value="Available">Available</option>
                                                                <option value="Available with Lead Time">Available with Lead Time</option>
                                                                <option value="Not Available" selected>Not Available</option>
                                                            </c:if>
                                                            <c:if test="${partList.status eq 'ALTERNATE AVAILABLE'}">
                                                                <option value="Alternate Available" selected>Alternate Available</option>
                                                            </c:if>
                                                        </select>
                                                        <%} else {%>${partList.status}
                                                        <html:hidden property="statusList" styleId="statusId${sno}" value="${partList.status}"/>
                                                        <%}%>
                                                    </td>
                                                    <td align="center" width="10%"  bgcolor="#FFFFFF" style="padding-left:0px;FONT-SIZE: 11px;">
                                                        <%if (!request.getParameter("buyerEditAllowed").equals("OnlyView")) {%>
                                                        <html:text property="leadTimeList" styleId="leadId${sno}" styleClass="headingSpas" style="height: 18px;" readonly="true" value="${partList.leadTime}" />
                                                        <%} else {%>${partList.leadTime}<%}%></td>
                                                    <td align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${partList.availablePartNoPI}</td>
                                                    <td align="left" width="15%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${partList.avlPartDescPI}</td>
                                                    <td align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${partList.piQty}</td>
                                                    <c:if test="${editAllowed eq 'N'}">
                                                        <td align="center" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${partList.acceptPiQty}</td>
                                                    </c:if>
                                                    <%if (((userFunctionalities.contains("863") && request.getParameter("buyerEditAllowed").equals("Y")
                                                                        || userFunctionalities.contains("861"))) && !request.getParameter("buyerEditAllowed").equals("OnlyView")
                                                                        && !request.getParameter("buyerEditAllowed").equals("GenerateOrder") && !request.getParameter("acceptFlag").equals("Accept/Reject PI")) {%>
                                                    <td align="center" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <c:if test="${fn:containsIgnoreCase(partList.status,'Not Available')}">
                                                            <html:text property="piQtyList" styleId="piQtyList${sno}" value="${partList.finalPiQty}" readonly="true" onblur="calculateAmount('${sno}');" style="width :30px;"/>
                                                        </c:if>
                                                        <c:if test="${!fn:containsIgnoreCase(partList.status,'Not Available')}">
                                                            <html:text property="piQtyList" styleId="piQtyList${sno}" value="${partList.finalPiQty}" onblur="calculateAmount('${sno}');" style="width :30px;"/>
                                                            <html:hidden property="finalQtyList" styleId="finalQtyList${sno}" value="${partList.finalPiQty}"/>
                                                        </c:if>
                                                    </td>
                                                    <%} else {%>
                                                    <td align="center" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        ${partList.finalPiQty}
                                                    </td>
                                                    <%}%>
                                                    <td align="right" width="10%" bgcolor="#FFFFFF" style="padding-right:3px;FONT-SIZE: 11px;">${partList.price}
                                                        <html:hidden property="priceList" styleId="priceList${sno}" value="${partList.price}"/></td>
                                                    <td align="right" width="10%" bgcolor="#FFFFFF" style="padding-right:3px;FONT-SIZE: 11px;">
                                                        <c:set var="partPrice"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${partList.finalPiQty * partList.price}" /></c:set>
                                                        <span id="amount${sno}">${partPrice}</span></td>
                                                        <%if (request.getParameter("buyerEditAllowed").equals("OnlyView")) {%>
                                                    <td align="center" width="10%" bgcolor="#FFFFFF" style="padding-right:3px;FONT-SIZE: 11px;">${partList.bookesQty}</td>
                                                    <td align="center" width="10%" bgcolor="#FFFFFF" style="padding-right:3px;FONT-SIZE: 11px;">${partList.pendingQty}</td>
                                                    <%}%>
                                                </tr>
                                                <c:set var="total" value="${total + (partList.finalPiQty*partList.price)}"></c:set>
                                                <c:set var="sno" value="${sno + 1 }" />
                                            </logic:iterate>
                                            <c:if test="${editAllowed eq 'N'}">
                                                <c:set var="colSpan" value="14" />
                                            </c:if><c:if test="${editAllowed ne 'N'}">
                                                <c:set var="colSpan" value="13" />
                                            </c:if>
                                            <tr height=20 bgcolor="#FFFFFF">
                                                <td align="right" colspan="${colSpan}">
                                                    <B> <bean:message key="label.common.MaterialValue" /> </B>
                                                    <c:set var="totalMaterialValue"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${total}" /></c:set>
                                                    <input type="text" name="totalValue" id="totalValue" value="${totalMaterialValue}" id="Total" readonly style="text-align: right"/>
                                                </td>
                                                <%if (request.getParameter("buyerEditAllowed").equals("OnlyView")) {%>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <%}%>
                                            </tr>
                                            <tr  bgcolor="#FFFFFF">
                                                <td colspan="${colSpan}">
                                                    <table align="right" width="20%" id="otherChargesTable">
                                                        <c:set var="chargeSno" value="1"></c:set>
                                                        <c:set var="grandtotal" value="${total}"></c:set>
                                                        <c:forEach var="otherCharges" items="${piForm.otherCharges}">
                                                            <tr bgcolor="#FFFFFF">
                                                                <td class="headingSpas" style="padding-right:3px;" align="right">${otherCharges.key.label}
                                                                    <html:hidden property="otherchargeType" styleId="otherchargeType${chargeSno}" value="${otherCharges.key.value}"/></td>
                                                                <td class="headingSpas" style="padding-left:3px;" align="right">
                                                                    <%if (userFunctionalities.contains("861") && !request.getParameter("buyerEditAllowed").equals("OnlyView") && !request.getParameter("buyerEditAllowed").equals("GenerateOrder")) {%>
                                                                    <html:text property="otherChargeList" styleId="otherChargeList${chargeSno}" value="${otherCharges.value}" onblur="calculateAmount('0');"/>
                                                                    <%} else {%>
                                                                    <html:hidden property="otherChargeList" styleId="otherChargeList${chargeSno}" value="${otherCharges.value}"/>${otherCharges.value}
                                                                    <%}%>
                                                                </td>
                                                                <c:set var="grandtotal" value="${grandtotal + otherCharges.value}"></c:set>
                                                            </tr>
                                                            <c:set var="chargeSno" value="${chargeSno+1}"></c:set>
                                                        </c:forEach>
                                                    </table>
                                                </td>
                                                <%if (request.getParameter("buyerEditAllowed").equals("OnlyView")) {%>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <%}%>
                                            </tr>
                                            <tr  bgcolor="#FFFFFF">
                                                <td align="right" class="headingSpas" colspan="${colSpan}" >
                                                    <B> <bean:message key="label.common.TotalAmount" /> (${piForm.currency})</B>
                                                    <c:set var="totalAmount"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${grandtotal}" /></c:set>
                                                    <input type="text" name="totalAmount" id="totalAmount" value="${totalAmount}" id="Total" readonly style="text-align: right"/>
                                                </td>
                                                <%if (request.getParameter("buyerEditAllowed").equals("OnlyView")) {%>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <%}%>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                           </div>
                            <div style="overflow: auto">
                                <table align="center" cellspacing=1 cellpadding=5 border=0 width=100% >
                                    <%if ((userFunctionalities.contains("863") || userFunctionalities.contains("861")) && !request.getParameter("buyerEditAllowed").equals("OnlyView")) {%>
                                    <c:if test="${fn:containsIgnoreCase(status,'Pending At Buyer') || fn:containsIgnoreCase(status,'Pending At Ho') || fn:containsIgnoreCase(status,'Accepted By Buyer')}">
                                        <tr>
                                            <td align="center">
                                                <%if (userFunctionalities.contains("861")) {%>
                                                <%if (request.getParameter("buyerEditAllowed").equals("GenerateOrder")) {%>
                                                <html:submit value="GENERATE ORDER" styleClass="headingSpas" onclick="submitForm('GenerateOrder');"/>
                                                <%} else {%>
                                                <html:submit value="REJECT" styleClass="headingSpas" onclick="submitForm('Rejected');"/>&nbsp;&nbsp;
                                                <html:submit value="UPDATE" styleClass="headingSpas" onclick="submitForm('Update');return validateForm();"/>
                                                <%}%>
                                                <%} else if (userFunctionalities.contains("863") && (request.getParameter("acceptFlag").equals("Accept/Reject PI") || request.getParameter("buyerEditAllowed").equals("N"))) {%>
                                                <html:submit value="REJECT" styleClass="headingSpas" onclick="submitForm('Rejected');"/>&nbsp;&nbsp;
                                                <html:submit value="ACCEPT" styleClass="headingSpas" onclick="submitForm('Accepted');"/>
                                                <%} else if (userFunctionalities.contains("863") && !request.getParameter("acceptFlag").equals("Accept/Reject PI")
                                                && request.getParameter("buyerEditAllowed").equals("Y")) {%>
                                                <html:submit value="CANCEL" styleClass="headingSpas" onclick="submitForm('Cancel');"/>&nbsp;&nbsp;
                                                <html:submit value="UPDATE" styleClass="headingSpas" onclick="submitForm('Update');"/>
                                                <%}%>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <%}%>
                                </c:if>
                                <%if (request.getParameter("buyerEditAllowed").equals("OnlyView")) {%>
                                <c:if test="${empty partDetailList}">
                                    <tr  bgcolor="#eeeeee" height="10">
                                        <td align= "center" style="color: red;">
                                            <bean:message key="label.common.noRecordFound"/></td>
                                    </tr>
                                </c:if>

                                <c:if test="${!empty invList}">
                                    <td>
                                    <tr bgcolor="#eeeeee" height="20" >
                                        <td colspan="6" class="headingSpas" align="left" >
                                            <label >
                                                <B><bean:message key="label.spare.viewSAPInvPartDetail" /></B>
                                            </label>
                                        </td>
                                    </tr>
                                    <tr height=20 bgcolor="#FFFFFF">
                                        <td align="center" class="headingSpas">
                                            <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc id="partPITable">
                                                <tr bgcolor="#eeeeee" height="20">
                                                    <td align="center" width="4%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;">
                                                        <bean:message key="label.common.sno" />
                                                    </td>
                                                    <td align="left" width="7%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px; width: 200px" >
                                                        <bean:message key="label.order.suppOrderNo" />
                                                    </td>
                                                    <td align="left" width="8%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px; width: 200px" >
                                                        <bean:message key="label.common.InvoiceNo" />
                                                    </td>
                                                    <td align="left" width="7%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px; width: 200px" >
                                                        <bean:message key="label.common.InvoiceDate" />
                                                    </td>
                                                    <td align="left" width="7%" style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px; width: 170px" >
                                                        <bean:message key="label.common.invoice.ordered.part" />
                                                    </td>
                                                    <td align="left" width="7%" style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.invoice.shiped.part" />
                                                    </td>
                                                    <td align="left" width="10%" style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 100px" >
                                                        <bean:message key="label.common.invoice.shiped.part.desc" />
                                                    </td>
                                                    <td align="left" width="4%" style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.qty" />
                                                    </td>
                                                    <td align="right" width="7%" style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.unitprice" />(${piForm.currency})
                                                    </td>
                                                    <td align="right" width="7%" style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.amount" />(${piForm.currency})
                                                    </td>
                                                    <td align="left" width="7%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px; width: 200px" >
                                                        <bean:message key="label.common.AWB/BOLNo" />
                                                    </td>
                                                    <td align="left" width="7%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px; width: 200px" >
                                                        <bean:message key="label.common.AWB/BOLDate" />
                                                    </td>
                                                    <td align="left" width="7%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px; width: 200px" >
                                                        <bean:message key="label.common.DispatchMode" />
                                                    </td>
                                                    <td align="left" width="7%" style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.status" />
                                                    </td>
                                                    <td align="left" width="8%"  style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.remarks" />
                                                    </td>
                                                </tr>
                                                <c:set var="sno" value="1"></c:set>
                                                <c:forEach items="${invList}" var="invList">
                                                    <tr  bgcolor="#eeeeee" height="20">
                                                        <td align="center" width="4%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                        <td align="left" width="7%" bgcolor="#FFFFFF" style=" padding-left:5px;FONT-SIZE: 11px;">${invList.erpOrderNo}</td>
                                                        <td align="left" width="8%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invList.invoiceNo}</td>
                                                        <td align="left" width="7%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invList.invoiceDate}</td>
                                                        <td align="left" width="7%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invList.orderedPart}</td>
                                                        <td align="left" width="7%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invList.shippedPartNo}
                                                        <td align="left" width="10%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invList.partDescInvoice}</td>
                                                        <td align="left" width="4%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invList.qtyShipped}</td>
                                                        <td align="right" width="7%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invList.partPriceInvoice}</td>
                                                        <td align="right" width="7%" bgcolor="#FFFFFF" style="padding-right:3px;FONT-SIZE: 11px;">
                                                            <fmt:formatNumber type="number" maxFractionDigits="2" value="${invList.qtyShipped*invList.partPriceInvoice}"/>
                                                            <%--${invList.qtyShipped*invList.partPriceInvoice}--%></td>
                                                        <td align="left" width="7%"  bgcolor="#FFFFFF" style=" padding-left:5px;FONT-SIZE: 11px;">${invList.awbBolNo}</td>
                                                        <td align="left" width="7%"  bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invList.awbBolDate}</td>
                                                        <td align="left" width="7%"  bgcolor="#FFFFFF" style=" padding-left:5px;FONT-SIZE: 11px;">${invList.dispatchMode}</td>
                                                        <td align="left" width="7%"  bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invList.statusInvoice}</td>
                                                        <td align="left" width="8%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invList.remarkInvoice}</td>
                                                    </tr>
                                                    <c:set var="sno" value="${sno + 1 }" />
                                                </c:forEach>
                                            </table>
                                        </td>
                                    </tr>
                                </c:if>
                                    <c:if test="${!empty invCancelledList}">
                                    <td>
                                    <tr bgcolor="#eeeeee" height="20" >
                                        <td colspan="6" class="headingSpas" align="left" >
                                            <label >
                                                <B><bean:message key="label.spare.viewSAPInvCancelledPartDetail" /></B>
                                            </label>
                                        </td>
                                    </tr>
                                    <tr height=20 bgcolor="#FFFFFF">
                                        <td align="center" class="headingSpas">
                                            <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc id="partPITable">
                                                <tr bgcolor="#eeeeee" height="20">
                                                    <td align="center" width="4%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;">
                                                        <bean:message key="label.common.sno" />
                                                    </td>
                                                    <td align="left" width="7%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px; width: 200px" >
                                                        <bean:message key="label.order.suppOrderNo" />
                                                    </td>
                                                 <%--   <td align="left" width="7%" style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px; width: 170px" >
                                                        <bean:message key="label.common.invoice.ordered.part" />
                                                    </td>
                                                 --%>   <td align="left" width="7%" style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.cancel.part" />
                                                    </td>
                                                    <td align="left" width="10%" style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 100px" >
                                                        <bean:message key="label.common.cancel.partDesc" />
                                                    </td>
                                                    <td align="left" width="4%" style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.qty" />
                                                    </td>
                                                    <td align="left" width="7%" style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.status" />
                                                    </td>
                                                    <td align="left" width="8%"  style=" padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.remarks" />
                                                    </td>
                                                </tr>
                                                <c:set var="sno" value="1"></c:set>
                                                <c:forEach items="${invCancelledList}" var="invCancelledList">
                                                    <tr  bgcolor="#eeeeee" height="20">
                                                        <td align="center" width="4%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                        <td align="left" width="7%" bgcolor="#FFFFFF" style=" padding-left:5px;FONT-SIZE: 11px;">${invCancelledList.erpOrderNo}</td>
                                                       <%-- <td align="left" width="7%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invCancelledList.orderedPart}</td>--%>
                                                        <td align="left" width="7%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invCancelledList.shippedPartNo}
                                                        <td align="left" width="10%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invCancelledList.partDescInvoice}</td>
                                                        <td align="left" width="4%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invCancelledList.qtyShipped}</td>
                                                        <td align="left" width="7%"  bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invCancelledList.statusInvoice}</td>
                                                        <td align="left" width="8%" bgcolor="#FFFFFF" style=" padding-left:3px;FONT-SIZE: 11px;">${invCancelledList.remarkInvoice}</td>
                                                    </tr>
                                                    <c:set var="sno" value="${sno + 1 }" />
                                                </c:forEach>
                                            </table>
                                        </td>
                                    </tr>
                                </c:if>
                                <%}%>
                            </html:form>
                        </table>
                    </div>
                </div>
            </div>
    </center>
</body>
</html>
