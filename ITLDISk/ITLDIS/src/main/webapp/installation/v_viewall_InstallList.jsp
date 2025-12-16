<%-- 
    Document   : v_ins_standard_check
    Created on : May 27, 2014, 1:35:17 PM
    Author     : megha.pandya
--%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
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

<script>
    function submitForm() {
       
        document.getElementById('eType').value=" ";
        document.getElementById('searchBy').submit();
    }
    function donotsubmit()
    {
        return false;
    }
    // BY AVINASH PANDEY
    function Export()
    {
        document.getElementById('eType').value="export";
        document.getElementById('searchBy').submit();
    }
</script>
<html>
    <head><title>ITLDIS</title></head>

    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link></li>
            <li class="breadcrumb_link"><bean:message key="label.common.pendinginstallation" /></li>
        </ul>
    </div>

    <div class="message" id="msg_saveFAILED"><html:errors/>${message}</div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1><bean:message key="label.common.pendinginstallation" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="installProcess.do?option=initViewAllInstallList" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">
                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="right">
                                            <span class="formLabel"><bean:message key="label.common.Chassisno" />&nbsp;&nbsp;</span>
                                        </td>
                                        <td style="padding-left:10px" width="140px" align="left">
                                            <input name="nameSrch" type="text" id="nameSrch" value="${nameSrch}"style="width:200px !important " onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <%if (userFunctionalities.contains("101")) {%>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <%} else {%>
                                        <td align="right" style="white-space: nowrap;"> <bean:message key="label.common.dealerName" />    </td>
                                        <td align="left">
                                            <select id="Dealer Name" name="dealercode" style="width:300px !important">
                                                <%-- <option value='' >--Select Dealer--</option>--%>
                                                <option value="ALL">ALL</option>
                                                <c:forEach items="${dealerList}"  var="labelValue">
                                                    <c:if test="${serviceForm.dealercode eq labelValue[0]}">
                                                        <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]" selected>${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                    </c:if>
                                                    <c:if test="${serviceForm.dealercode ne labelValue[0]}">
                                                        <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]">${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <%}%>

                                        <td style="padding-left:10px" align="left" >
                                            <input name="go" type="button"  value="Search" style="float:none; " onclick="submitForm();"/>
                                            <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="eType" id="eType" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </html:form>
                    </tr>

                    <c:if test="${!empty chassisList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="installProcess.do" maxIndexPages="10" maxPageItems="15">
                                    <pg:param name="option" value="initViewAllInstallList"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <pg:param name="dealercode"  value='<%=request.getParameter("Dealer Name")%>'/>
                                    <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;">
                                                <bean:message key="label.common.sno" />
                                            </td>

                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.Chassisno" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spare.deliveryDate" />
                                            </td>
                                            <td nowrap align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.Modelfamily" />
                                            </td>
                                            <td nowrap align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.Modelcode" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.CustomerName" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.mobilephone" />
                                            </td>
                                            <%if (userFunctionalities.contains("101")) {%>

                                            <%} else {%>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.dealerName" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.location" />
                                            </td>
                                            <%}%>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="chassisList" name="chassisList">
                                            <pg:item>
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>

                                                    <td align="left" nowrap width="10%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <%if (userFunctionalities.contains("606")) {%>
                                                        <a href="${pageContext.request.contextPath}/installProcess.do?option=initInstallInfo&vinNo=${chassisList.vinNo}" >
                                                            <span id="vinNo${sno}">${chassisList.vinNo}</span>
                                                        </a>
                                                        <%} else {%>
                                                        ${chassisList.vinNo}
                                                        <%}%>
                                                    </td>

                                                    <td align="left" width="10%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.deliveryDate}<span ></span></td>
                                                    <td align="left" width="12%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.modelFamily}<span ></span></td>
                                                    <td align="left" width="12%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.modelCode}<span ></span></td>
                                                    <td align="left" width="15%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.customerName}<span></span></td>
                                                    <td align="left" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.mobilePhone}<span></span></td>
                                                    <%if (userFunctionalities.contains("101")) {%>

                                                    <%} else {%>
                                                    <td align="left" width="15%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.dealercode} [${chassisList.dealerName}]<span></span></td>
                                                    <td align="left" width="15%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.locationName}<span></span></td>
                                                    <%}%>
                                                </pg:item>
                                                <c:set var="sno" value="${sno + 1 }" />
                                            </logic:iterate>
                                        <tr >

                                            <%if (userFunctionalities.contains("101")) {%>
                                            <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                <%} else {%>
                                            <td colspan="9" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                <%}%>

                                                <pg:index>
                                                    <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle"/></a></pg:first>&nbsp;
                                                    <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle"/></a></pg:prev>&nbsp;
                                                    <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                    <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle"/></a></pg:next>&nbsp;
                                                    <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle"/></a></pg:last>
                                                    </pg:index>
                                            </td>
                                        </tr>
                                    </table>
                                </pg:pager>
                            </td>

                        </tr>

                        <%-- <tr bgColor="#ffffff"><td style="color: red;  padding-top: 10px;" align="left">
                                 <bean:message key="label.common.insNote"/>
                             </td></tr>--%>

                    </c:if>
                    <c:if test="${empty chassisList}">
                        <tr  bgcolor="#eeeeee" height="10">
                            <td align= "center" style="color: red;"> <bean:message key="label.common.noRecordFound"/></td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </div>
    </center>
    <p><script src="JS/jquery-ui.js"></script>
        <script>  $(function() {
            $("#datepicker").datepicker();
        });</script>
    </p>
</body>
</html>

