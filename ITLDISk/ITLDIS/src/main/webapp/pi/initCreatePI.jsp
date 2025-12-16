<%-- 
    Document   : createPI
    Created on : Jan 5, 2016, 10:54:05 AM
    Author     : prashant.kumar
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
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

    
    function submitSearchByForm() {
               
        if(document.getElementById("dealerId").value=="ALL"){
            alert(not_blank_dropdown_validation_msg+" Buyer");
            document.getElementById("dealerId").focus();
            return false;
        }        
        if(document.getElementById("orderTypeId").value==""){
            alert(not_blank_dropdown_validation_msg+" Order type");
            document.getElementById("orderTypeId").focus();
            return false;
        }
        var el = document.getElementById('dealerId');
        var text = el.options[el.selectedIndex].innerHTML;
        document.getElementById("buyer").value=text;
    }    
    function submitDetailSearchForm() {
        if($("input[type=checkbox]:checked").length == 0){
            alert(atleastOneCheck_validation_msg+" PO to create PI.");
            return false;
        }
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
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/createPIAction.do?option=getListForInitPI'><bean:message key="label.spare.initCreatePI" /></a></li>
            <li><bean:message key="label.common.orderPendingPI" /></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED"><html:errors/>
        <c:if test="${message eq 'PIFail'}">
            <bean:message key="label.spare.PIFailMessage" />
        </c:if>
        <c:if test="${message ne 'PIFail'}">
            ${message}
        </c:if>
    </div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" >
                <h1 class="hText"><bean:message key="label.common.orderPendingPI" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <%--<tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="createPIAction.do?option=getorderListForCreatePI&searchFlag=true" method="post" styleId="searchBy" onsubmit="return submitSearchByForm();">
                                <input type="hidden" name="dealerName" id="dealerName" value="${piForm.dealerName}"/>
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#FFFFFF>
                                    <tr bgcolor="#FFFFFF">
                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="right">
                                            <span class="formLabel"><bean:message key="label.common.selectBuyer" /></span>
                                        </td>
                                        <td colspan="4" align="left">
                                            <select id="dealerId" name="dealerCode" style="width:300px !important">
                                                 <option value='' >--Select Dealer--</option>
                                                <option value="ALL">ALL</option>
                                                <c:forEach items="${dealerList}"  var="labelValue">
                                                    <c:if test="${piForm.dealerCode eq labelValue[0]}">
                                                        <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]" selected>${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                    </c:if>
                                                    <c:if test="${piForm.dealerCode ne labelValue[0]}">
                                                        <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]">${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="right">
                                                    <input type="hidden" name="buyer" id="buyer" />
                                        </td>

                                        <td  style="padding-left:10px" class="headingSpas" nowrap align="left" width="110px">
                                            <span class="formLabel"><bean:message key="label.common.selectOrderType" /></span>
                                        </td>
                                        <td align="left">
                                            <html:select property="orderType" styleId="orderTypeId" styleClass="headingSpas" style="width:250px !important">
                                                <html:option value="">-Select-</html:option>
                                                <html:option value="STD">STD</html:option>
                                                <html:option value="VOR">VOR</html:option>
                                            </html:select>
                                        </td>
                                        <td style="padding-left:10px" align="center" >
                                            <input name="go" type="submit"  value="Search" class="headingSpas"/>
                                        </td>
                                    </tr>
                                    <tr><td>&nbsp;</td></tr>
                                    <c:if test="${not empty buyer}">
                                    <tr bgcolor="#FFFFFF" width="100%">
                                        <td bgcolor="#FFFFFF" colspan="5" style="padding-left:35px" width="100px" class="headingSpas" nowrap align="left">
                                            <span class="formLabel"><bean:message key="label.common.orderForBuyer" />&nbsp;:&nbsp;${buyer}</span>
                                        </td>                                       
                                    </tr>
                                    </c:if>
                                    <tr><td>&nbsp;</td></tr>
                                </table></html:form>
                            </td>                        
                        </tr>--%>
                   
                    <c:if test="${!empty orderList}">
                        <html:form action="createPIAction.do?option=getorderDetailList" method="post" styleId="detailSearch" onsubmit="return submitDetailSearchForm();">
                            <input type="hidden" name="dealerCode" id="dealerCode" value="${piForm.dealerCode}"/>
                            <input type="hidden" name="orderType" id="orderTypeId" value="${piForm.orderType}"/>
                            <input type="hidden" name="dealerName" id="dealerName" value="${piForm.dealerName}"/>
                            <input type="hidden" name="buyer" id="buyer" value="${buyer}" />
                            <tr height=25 bgcolor="#FFFFFF" >
                                <td align= center class="headingSpas" style="padding-top: 10px">
                                    <table id="ordHdrTable" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20" >
                                            <td nowrap align="center" width="3%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;">
                                                <bean:message key="label.warranty.S.No" />
                                            </td>
                                            <td nowrap align="left" width="16%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.poNo" />
                                            </td>
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.orderType" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.PODate" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.consigneeCode" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.consigneeName" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.consigneeCountry" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.dischargePortName" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.destinationPlace" />
                                            </td>
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.nooflines" />
                                            </td>
                                            <td nowrap align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.orderedAmount" />
                                            </td>
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"></td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="custPoList" name="orderList" >
                                            <tr  bgcolor="#eeeeee" height="20">
                                                <td nowrap align="center" width="3%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                <td align="left" nowrap width="16%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                    <span>
                                                        <a href="#" class="viewOrderDetails">  ${custPoList.poNo} </a>
                                                    </span>
                                                </td>
                                                <td align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPoList.orderType}</td>
                                                <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPoList.custPoDate}</td>
                                                <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPoList.consigneeCode}</td>
                                                <td align="left" width="15%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPoList.consigneeName}</td>
                                                <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPoList.consigneeCountry}</td>
                                                <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPoList.dischargePort}</td>
                                                <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPoList.destinationPlace}</td>
                                                <td align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPoList.lineItem}</td>
                                                <td align="right" width="10%" bgcolor="#FFFFFF" style="padding-right:3px;FONT-SIZE: 11px;">
                                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${custPoList.totalPrice}"/>
                                                    <%--${custPoList.totalPrice}--%></td>
                                                <td align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" ><input title="Select for PI" type="checkbox" name="checkedPOList"   id="checkId${sno}" value="${fn:trim(custPoList.poNo)}"/></td>
                                                    <c:set var="sno" value="${sno + 1 }" />
                                                </logic:iterate>
                                    </table>
                                </td>
                            </tr>
                            <tr  bgcolor="#eeeeee" height="20">
                                <td style="padding-left:10px" align="center" >
                                    <input type="submit" name="submit"  value="Submit" class="headingSpas"/>
                                </td>
                            </tr>                            
                        </html:form>
                    </c:if>
                    <c:if test="${empty orderList}">
                        <tr  bgcolor="#eeeeee" height="10">
                            <td align= "center" style="color: red;">
                                <bean:message key="label.common.noRecordFound"/></td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </div>
    </center>
</body>
</html>

