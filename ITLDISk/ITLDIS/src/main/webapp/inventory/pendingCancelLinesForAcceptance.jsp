<%-- 
    Document   : pendingCancelLinesForAcceptance
    Created on : Dec 23, 2015, 12:21:22 PM
    Author     : apurv.singh
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
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");

            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String dealerFlag = "";
            if (!userFunctionalities.contains("101")) {
                dealerFlag = "true";
            }
%>

<script  type="text/javascript" language="javascript">
    function validate()
    {
        document.getElementById("myForm").action ="${pageContext.request.contextPath}/inventoryAction.do?option=pendingCancelLinesForAcceptance";
        document.getElementById("myForm").submit();
    }
</script>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.spare.pendingCancelLinesForAcceptance" /></li>
        </ul>
    </div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.spare.pendingCancelLinesForAcceptance" /></h1>

                <c:if test="${!empty dataList}">
                    <%--<form id="myForm" method="post" action="${pageContext.request.contextPath}/inventoryAction.do?option=pendingCancelLinesForAcceptance">--%>
                    <form id="myForm" method="post">
                        <div style="width:99%;">
                            <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                <tr bgcolor="#eeeeee" height="20">
                                    <td   align="center" class="headingSpas" width="4%" nowrap style="padding-left:5px; "><b><bean:message key="label.warranty.S.No" /></b></td>
                                    <td   align="left"  class="headingSpas"  nowrap style="padding-left:5px; "><b> <bean:message key="label.spare.poNo" /></b></td>
                                    <td   align="left"  class="headingSpas"  nowrap style="padding-left:5px; "><b><bean:message key="label.spare.PODate" /> </b></td>
                                    <td   align="left"  class="headingSpas"  nowrap style="padding-left:5px; "><b><bean:message key="label.common.stockist.orderno" /> </b></td>
                                    <td   align="left"  class="headingSpas"  nowrap style="padding-left:5px; "><b><bean:message key="label.common.invoice.ordered.part" /></b></td>
                                    <td   align="left"  class="headingSpas"  nowrap style="padding-left:5px; "><b><bean:message key="label.common.partdesc" /></b></td>
                                    <td   align="left"  class="headingSpas"  nowrap style="padding-left:5px; "><b><bean:message key="label.common.cancelledQty" /></b></td>
                                    <td   align="left"  class="headingSpas"  nowrap style="padding-left:5px; "><b><bean:message key="label.common.remarks" /></b></td>
                                    <td   align="center"  class="headingSpas"  nowrap style="padding-left:5px; "><b><bean:message key="label.common.yes" /></b></td>
                                    <td   align="center"  class="headingSpas"  nowrap style="padding-left:5px; "><b><bean:message key="label.common.no" /></b></td>
                                </tr>
                                <div style="width:99%; overflow:auto">
                                    <c:set var="sno" value="1"></c:set>
                                    <logic:iterate id="dataList" name="dataList">
                                        <tr  bgcolor="#eeeeee" height="20">
                                            <td align="center" bgcolor="#FFFFFF" width="4%" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                            <td align="left" bgcolor="#FFFFFF"  title="${dataList.newPoNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                ${dataList.newPoNo}
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.poDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                ${dataList.poDate}
                                            </td>

                                            <td align="left" bgcolor="#FFFFFF"  title=" ${dataList.stockistId}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                ${dataList.stockistId}
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF"  title=" ${dataList.partno}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                                                ${dataList.partno}
                                            </td>

                                            <td align="left" bgcolor="#FFFFFF"  title="${dataList.part_desc}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                ${dataList.part_desc}
                                            </td>

                                            <td align="left" bgcolor="#FFFFFF"  title="${dataList.qty}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                ${dataList.qty}
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF"  title="${dataList.erpRemarks}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                ${dataList.erpRemarks}
                                            </td>
                                            <td align="center" bgcolor="#FFFFFF"  title="${dataList.qty}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                <input type="radio" name="checkedArr${sno}" value="YES@@${dataList.stockistId}@@${dataList.erpOrderNo}@@${dataList.invNo}@@${dataList.partno}" style="background:none; border:none; width:12px; height:12px;" checked >
                                                <%--<input type="hidden" name="checkedArr" value="${inventoryForm.stockistId}'@@'${inventoryForm.erpOrderNo}'@@'${inventoryForm.invNo}'@@'${inventoryForm.partno}">--%>
                                            </td>
                                            <td align="center" bgcolor="#FFFFFF"  title="${inventoryForm.qty}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                <input type="radio" name="checkedArr${sno}" value="NO@@${dataList.stockistId}@@${dataList.erpOrderNo}@@${dataList.invNo}@@${dataList.partno}" style="background:none; border:none; width:12px; height:12px;">
                                            </td>
                                        </tr>
                                        <c:set var="sno" value="${sno + 1 }" />
                                    </logic:iterate>
                                    <tr>
                                        <td align="center" colspan="10" bgcolor="#FFFFFF" class="headingSpas">
                                            <input type="button" value="SUBMIT" name="submitForm" onClick="validate()"/>
                                            <input type="hidden" name="listCount" value="${sno}">
                                        </td>
                                    </tr>
                                </div>
                            </table>
                        </div>
                    </form>
                </c:if>

                <c:if test="${empty dataList}">
                    <tr  bgcolor="#FFFFFF" height="20">
                        <c:if test="${empty message}">
                            <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.noRecordFound" /></td>
                        </c:if>

                        <c:if test="${not empty message}">
                            <c:set var="result" value="${fn:split(message,'@@')}"/>
                            <c:if test="${result[0] eq 'SUCCESS'}">
                                <td valign="bottom" align="center" style="padding-top:10px;">
                                    <img alt="" src="${pageContext.request.contextPath}/images/success.gif"/>  ${result[1]}
                                </td>
                            </c:if>
                            <c:if test="${result[0] eq 'FAILURE'}">
                                <td valign="bottom" align="center" style="padding-top:10px;">
                                    <img alt="" src="${pageContext.request.contextPath}/images/failure.gif"/>  ${result[1]}
                                    <a href='${pageContext.request.contextPath}/inventoryAction.do?option=initPendingCancelLinesForAcceptance' style="text-decoration: none;color: blue" >Try Again....</a>
                                </td>
                            </c:if>
                        </c:if>
                    </tr>
                </c:if>
            </div>
        </div>
    </center>
</div>
