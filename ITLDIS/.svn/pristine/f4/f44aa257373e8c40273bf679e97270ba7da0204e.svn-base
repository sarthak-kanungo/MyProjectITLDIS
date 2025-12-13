<%--
    Document   : viewPIlist
    Created on : 11 Jan, 2016, 9:43:33 AM
    Author     : yogasmita.patel
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>

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
    function disableDate(dis)
    {
        if(dis.checked){
            document.getElementById("From Date").disabled=false;
            document.getElementById("To Date").disabled=false;
        }else{
            document.getElementById("From Date").value="";
            document.getElementById("From Date").disabled=true;
            document.getElementById("To Date").value="";
            document.getElementById("To Date").disabled=true;
        }
    }
</script>

<html>
    <head><title>ITLDIS</title></head>
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
            <li><bean:message key="label.spare.initViewPI" /></li>
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
                <h1 class="hText"><bean:message key="label.spare.initViewPI" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="createPIAction.do?option=getPIListForView&searchFlag=true&flag=check" method="post" styleId="searchBy">         <%--onsubmit="return submitSearchByForm();"--%>
                                <input type="hidden" name="dealerName" id="dealerName" value="${piForm.dealerName}"/>
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#FFFFFF>
                                    <tr bgcolor="#FFFFFF">
                                        <td class="headingSpas" nowrap align="right" width="10%"> <bean:message key="label.spare.piNo" /></td>
                                        <td align="left" width="10%"><input name="piNo" type="text" id="Pi No" value="${piForm.piNo}" style="width:140px !important " onblur="this.value=TrimAll(this.value)"/></td>
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <%} else {%>
                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="right">
                                            <span class="formLabel"><bean:message key="label.common.selectBuyer" /></span>
                                        </td>
                                        <td colspan="3" align="left">
                                            <select id="Dealer Name" name="dealerCode" style="width:230px !important">
                                                <%-- <option value='' >--Select Dealer--</option>--%>
                                                <option value="">-ALL-</option>
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
                                        <%}%>

                                        <td  style="padding-left:10px" class="headingSpas" nowrap align="left" width="110px">
                                            <span class="formLabel"><bean:message key="label.common.selectOrderType" /></span>
                                        </td>
                                        <td align="left">
                                            <html:select property="orderType" styleId="orderTypeId" styleClass="headingSpas" style="width:150px !important">
                                                <html:option value="%">-ALL-</html:option>
                                                <html:option value="STD">STD</html:option>
                                                <html:option value="VOR">VOR</html:option>
                                            </html:select>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td  style="padding-left:10px;padding-top:10px" class="headingSpas" nowrap align="right" width="10%">
                                            <span class="formLabel"><bean:message key="label.common.status" /></span>
                                        </td>
                                        <td align="left" style="padding-top:10px">
                                            <html:select property="status" styleId="statusId" styleClass="headingSpas" style="width:220px !important">
                                                <html:option value="">-ALL-</html:option>
                                                <html:option value="PENDING AT BUYER">PENDING AT BUYER</html:option>
                                                <html:option value="PENDING AT HO">PENDING AT HO</html:option>
                                                <html:option value="ACCEPTED BY BUYER">ACCEPTED BY BUYER</html:option>
                                                <%--<html:option value="PENDING FOR ORDER GENERATION">PENDING FOR ORDER GENERATION</html:option>--%>
                                                <html:option value="SENT TO SAP">SENT TO SAP</html:option>
                                                <html:option value="ORDER REGISTERED">ORDER REGISTERED</html:option>
                                                <html:option value="REJECTED BY BUYER">REJECTED BY BUYER</html:option>
                                                <html:option value="REJECTED BY HO">REJECTED BY HO</html:option>
                                                <html:option value="ORDER CANCELLED">ORDER CANCELLED</html:option>
                                            </html:select>
                                        </td>
                                        <td align="right"  style="padding-left:10px;padding-top:10px" class="headingSpas" nowrap width="110px">
                                            <c:if test="${range eq '1'}">
                                                <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                            </c:if>
                                            <c:if test="${range ne '1' }">
                                                <input type="checkbox" name="range" value="1"  id="Range" >
                                            </c:if>
                                            <bean:message key="label.spare.piDate" />
                                        </td>
                                        <td align="left" width="5%" style="padding-right:5px;padding-top:10px">
                                            <input type="text" name="fromDate" class="datepicker" id="From Date" style="width:60px !important " class="txtGeneralNW" value="${piForm.fromDate}"  readonly="readonly"/>
                                        </td>
                                        <td style="padding-top:10px" class="headingSpas" nowrap align="center" width="1%"  >
                                            To
                                        </td>
                                        <td style="padding-top:10px" align="left" width="10%">
                                            <input type="text" name="toDate" class="datepicker" id="To Date" style="width:60px !important " class="txtGeneralNW" value="${piForm.toDate}"  readonly="readonly"/>
                                        </td>

                                        <td colspan="2" style="padding-right:300px!important;padding-top:10px!important" align="left" >
                                            <input name="go" type="submit"  value="Search" class="headingSpas"/>
                                        </td>
                                    </tr>
                                    <tr><td>&nbsp;</td></tr>
                                </table></html:form>
                            </td>
                        </tr>
                    </table>
                    <div style="overflow: auto">
                        <table align="center" cellspacing=1 cellpadding=5 border=0 width=100% >
                            <tr height=25 bgcolor="#FFFFFF">
                                <td align= center class="headingSpas">
                                <c:if test="${!empty piList}">

                                    <pg:pager url="createPIAction.do" maxIndexPages="10" maxPageItems="10">
                                        <pg:param name="option" value="getPIListForView"/>
                                        <pg:param name="dealerCode"  value='${piForm.dealerCode}'/>
                                        <pg:param name="orderType"  value='${piForm.orderType}'/>
                                        <pg:param name="status"  value='${piForm.status}'/>

                                        <pg:param name="dealerName"  value='${piForm.dealerName}'/>
                                        <pg:param name="fromDate"  value='${piForm.fromDate}'/>
                                        <pg:param name="toDate"  value='${piForm.toDate}'/>
                                        <pg:param name="searchFlag"  value='true'/>
                                        <pg:param name="flag"  value='check'/>
                                        <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>
                                        <div style="width:99%; overflow:auto">
                                            <table id="ordHdrTable" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                                <tr bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;">
                                                        <bean:message key="label.warranty.S.No" />
                                                    </td>
                                                    <td nowrap align="left" width="18%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.spare.piNo" />
                                                    </td>
                                                    <td nowrap align="center" width="8%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.spare.orderType" />
                                                    </td>
                                                    <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.spare.piDate" />
                                                    </td>
                                                    <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.dealerCode" />
                                                    </td>
                                                    <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.spare.consigneeName" />
                                                    </td>
                                                    <td nowrap align="left" width="8%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.spare.consigneeCountry" />
                                                    </td>
                                                    <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.nooflines" />
                                                    </td>
                                                    <td nowrap align="left" width="14%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.status" />
                                                    </td>
                                                    <td nowrap align="center" width="12%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.action" />
                                                    </td>
                                                    <%--     <%if (userFunctionalities.contains("861")) {%>
                                                         <td nowrap align="left" width="8%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                             <bean:message key="label.spare.GenerateOrder" />
                                                         </td>
                                                         <%}%>--%>
                                                </tr>
                                                <c:set var="sno" value="1"></c:set>
                                                <logic:iterate id="custPiList" name="piList" >
                                                    <pg:item>
                                                        <tr  bgcolor="#eeeeee" height="20">
                                                            <td nowrap align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                            <td align="left" nowrap width="18%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                                <span>
                                                                    <a href="createPIAction.do?option=getPIDetailForView&piNo=${custPiList.piNo}&orderType=${custPiList.orderType}&buyerEditAllowed=OnlyView" >${custPiList.piNo}</a>
                                                                </span>
                                                            </td>
                                                            <td align="center" width="8%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPiList.orderType}</td>
                                                            <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPiList.piDate}</td>
                                                            <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPiList.dealerCode}</td>
                                                            <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPiList.consigneeName}</td>
                                                            <td align="left" width="8%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPiList.consigneeCountry}</td>
                                                            <td align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPiList.lineItem}</td>
                                                            <td align="left" width="14%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${custPiList.status}</td>

                                                            <td align="center" width="12%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                                <%if (userFunctionalities.contains("863")) {%>
                                                                <c:if test="${fn:containsIgnoreCase(custPiList.status,'Pending At Buyer')}">
                                                                    <span>
                                                                        <a href="createPIAction.do?option=getPIDetailForView&piNo=${custPiList.piNo}&orderType=${custPiList.orderType}&buyerEditAllowed=${custPiList.buyerEditAllowed}&acceptFlag=Accept/Reject PI"><%--Accept/Reject PI--%>
                                                                            <img src="${pageContext.request.contextPath}/images/accept_status.png" title="Accept/Reject PI" width="15" height="15"/></a>
                                                                    </span>&nbsp;&nbsp;
                                                                    <c:if test="${custPiList.buyerEditAllowed eq 'Y'}">
                                                                        <span>
                                                                            <a href="createPIAction.do?option=getPIDetailForView&piNo=${custPiList.piNo}&orderType=${custPiList.orderType}&buyerEditAllowed=${custPiList.buyerEditAllowed}&acceptFlag=Edit PI"><%--Edit PI--%>
                                                                                <img src="${pageContext.request.contextPath}/images/edit.gif" title="Edit PI" /></a>
                                                                        </span>
                                                                    </c:if>
                                                                </c:if>
                                                                <%} else if (userFunctionalities.contains("861")) {%>
                                                                <c:if test="${fn:containsIgnoreCase(custPiList.status,'Pending At HO')||fn:containsIgnoreCase(custPiList.status,'Accepted By Buyer')||fn:containsIgnoreCase(custPiList.status,'Pending At Buyer')}">
                                                                    <span>
                                                                        <a href="createPIAction.do?option=getPIDetailForView&piNo=${custPiList.piNo}&orderType=${custPiList.orderType}&buyerEditAllowed=${custPiList.buyerEditAllowed}&acceptFlag=Edit PI"><%--Edit PI width="20" height="20"--%>
                                                                            <img src="${pageContext.request.contextPath}/images/edit.gif" title="Edit PI" /></a>
                                                                    </span>
                                                                    &nbsp;&nbsp;
                                                                    <c:if test="${fn:containsIgnoreCase(custPiList.status,'Accepted By Buyer')}">
                                                                        <span>
                                                                            <a href="createPIAction.do?option=getPIDetailForView&piNo=${custPiList.piNo}&orderType=${custPiList.orderType}&buyerEditAllowed=GenerateOrder"><%--Generate Order--%>
                                                                                <img src="${pageContext.request.contextPath}/images/generate.jpg" title="Generate Order" width="15" height="15"/></a>
                                                                        </span>
                                                                    </c:if>
                                                                </c:if>
                                                                <%}%>
                                                            </td>

                                                            <%-- <%if (userFunctionalities.contains("861")) {%>
                                                             <td align="left" width="8%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                             </td>
                                                             <%}%>--%>
                                                        </tr>
                                                    </pg:item>
                                                    <c:set var="sno" value="${sno + 1 }" />
                                                </logic:iterate>
                                                <tr>
                                                    <%if (userFunctionalities.contains("861")) {%>
                                                    <c:set var="colSpan" value="10" />
                                                    <%} else {%>
                                                    <c:set var="colSpan" value="10" />
                                                    <%}%>
                                                    <td colspan="${colSpan}" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                        <table  align="center" cellspacing="0" cellpadding="0">
                                                            <tr>
                                                                <td>
                                                                    <pg:index>
                                                                        <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle" style="float:left;"/></a></pg:first>&nbsp;
                                                                        <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle" style="float:left;"/></a></pg:prev>&nbsp;
                                                                        <pg:pages>&nbsp;<a style="color: blue!important;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                                        <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle" style="float:right"/></a></pg:last>
                                                                        <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle" style="float:right;"/></a></pg:next>&nbsp;
                                                                        </pg:index>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </pg:pager>
                                </c:if>
                            </td>
                        </tr>

                        <c:if test="${empty piList}">
                            <tr  bgcolor="#eeeeee" height="10">
                                <td align= "center" style="color: red;">
                                    <bean:message key="label.common.noRecordFound"/></td>
                            </tr>
                        </c:if>
                    </table>
                </div>
            </div>
        </div>
    </center>
</body>
</html>


