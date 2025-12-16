<%--
    Document   : createPI
    Created on : Jan 22, 2016, 05:04:05 PM
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
            <li><bean:message key="label.spare.initCreatePI" /></li>
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
                <h1 class="hText"><bean:message key="label.spare.initCreatePI" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                    <c:if test="${!empty orderList}">
                        <html:form action="createPIAction.do?option=getorderListForCreatePI" method="post" styleId="detailSearch" onsubmit="return submitDetailSearchForm();">                           
                            <tr height=25 bgcolor="#FFFFFF">
                                <td align= center class="headingSpas" style="padding-top: 10px">
                                    <table id="ordHdrTable" width=100%  border=0 cellspacing=1 cellpadding=1 bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">                                            
                                            <td nowrap align="left"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.buyerName" />
                                            </td>
                                            <td nowrap align="left"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.selectBuyerCode" />
                                            </td>
                                            <td nowrap align="left"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.selectBuyerLoc" />
                                            </td>
                                            <td nowrap align="center"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.orderType" />
                                            </td>
                                            <td nowrap align="center"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.noOfOrder" />
                                            </td>
                                            <td nowrap align="center"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.createPI" />
                                            </td>                                                                                      
                                        </tr>

                                        <logic:iterate id="order" name="orderList" >
                                            <tr  bgcolor="#eeeeee" height="20">
                                                <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${order.dealerName}</td>
                                                <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${order.dealerCode}</td>
                                                <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${order.location}</td>
                                                <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${order.orderType}</td>
                                                <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${order.noOfOrder}</td>
                                                <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                    <span>
                                                        <a href="createPIAction.do?option=getorderListForCreatePI&searchFlag=true&dealerCode=${order.dealerCode}&orderType=${order.orderType}">
                                                            <img src="${pageContext.request.contextPath}/images/invoice.jpg" title="Create PI" width="20" height="20"/></a>
                                                    </span>                                                   
                                                    <c:set var="sno" value="${sno + 1 }" />
                                            </tr>
                                        </logic:iterate>
                                    </table>
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

