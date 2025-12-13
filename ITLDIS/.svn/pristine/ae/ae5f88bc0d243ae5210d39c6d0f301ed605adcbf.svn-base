<%--
    Document   : p_viewallpendingjobcard
    Created on : Oct 8, 2014, 10:55:41 AM
    Author     : megha.pandya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>

<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script src="${pageContext.request.contextPath}/JS/Master_290414.js"></script>
<script src="${pageContext.request.contextPath}/js/base64.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Master_290414.css" type="text/css" >
<script>
    function submitForm() {
        var searchvalue =document.getElementById('nameSrch').value;
        document.getElementById('Srch').value = searchvalue;
        document.getElementById('eType').value="";
        document.getElementById('searchBy').submit();
    }
    function donotsubmit()
    {
        return false;
    }
    $(document).ready(function(){
    $('.pendingChasisNo').click(function(){
        var url = "pdiServiceProcess.do?option=show_SinglePendingChassisdetails&vinNo="+Base64.encode($(this).text().trim())+"&modelCode="+Base64.encode($(this).attr("data-modelCode").trim());
        $(this).attr('href',url);
    });
    });
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
            <li class="breadcrumb_link"><B><bean:message key="label.common.pendingforpdi" /></B></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED">${message}</div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1><bean:message key="label.common.pendingforpdi" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="pdiServiceProcess.do?option=init_viewallPendingChassisdetails" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="right">
                                            <bean:message key="label.common.Chassisno" />
                                          <%--  <input name="radiobutton" id="radiobutton_VinNo" type="radio" class="checknew" value="vinno" checked="checked"/>--%>
                                        </td>
                                        <td style="padding-left:10px" width="140px" align="left">
                                            <input name="nameSrch" type="text" id="nameSrch" value="${nameSrch}" style="width:200px !important " onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <%if (userFunctionalities.contains("101")) {%>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <%} else {%>
                                        <td align="right" style="white-space: nowrap;"> <bean:message key="label.common.dealerName" />    </td>
                                        <td align="left">
                                            <select id="Dealer Name" name="dealerCode" style="width:300px !important">
                                                <option value="ALL">ALL</option>
                                                <c:forEach items="${dealerList}"  var="labelValue">
                                                    <c:if test="${pdiForm.dealerCode eq labelValue[0]}">
                                                        <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]" selected>${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                    </c:if>
                                                    <c:if test="${pdiForm.dealerCode ne labelValue[0]}">
                                                        <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]">${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <%}%>
                                        <td style="padding-left:10px" align="left" >
                                            <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                            <input name="go" type="button" value="search" style="float:none; " onClick = "submitForm()"/>
                                            <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="eType" id="eType" />
                                        </td>
                                    </tr>
                                </table>
                            </html:form>
                        </td>
                    </tr>
                    <c:if test="${!empty chassisList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">

                                <pg:pager url="pdiServiceProcess.do" maxIndexPages="10" maxPageItems="15">
                                    <pg:param name="option" value="init_viewallPendingChassisdetails"/>
                                    <pg:param name="jobType" value="PDI"/>
                                    <pg:param name="Srch"  value='<%=request.getParameter("Srch")%>'/>
                                     <pg:param name="dealerCode"  value='<%=request.getParameter("Dealer Name")%>'/>
                                    <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.sno" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.Chassisno" />
                                            </td>
                                            <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.Modelfamily" />
                                            </td>
                                            <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.Modelcode" />
                                            </td>
                                           <%if (userFunctionalities.contains("101")) {%>
                                           <%} else{%>
                                            <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.dealerName" />
                                            </td>
                                            <td nowrap align="left" width="25%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.location" />
                                            </td>
                                            <%}%>
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.pendingDays" />
                                            </td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="chassisList" name="chassisList">
                                            <pg:item>
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>

                                                    <%-- <td align="left" nowrap width="15%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <%if (userFunctionalities.contains("604")) {%>
                                                        <a href="#" class="pendingChasisNo" data-modelCode="${chassisList.modelCode}">
                                                            ${chassisList.vinNo}</a>
                                                         <%} else{%>
                                                         ${chassisList.vinNo}
                                                         <%}%>
                                                    </td> --%>
                                                    
                                                    <td align="left" nowrap width="15%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                         ${chassisList.vinNo}
                                                    </td>
                                                    
                                                    <td align="left" width="20%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${chassisList.modelFamily}</span></td>
                                                    <td align="left" width="20%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${chassisList.modelCode}</span></td>
                                                    <%if (userFunctionalities.contains("101")) {%>
                                                    <%} else{%>
                                                    <td align="left" width="20%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${chassisList.dealerCode} [${chassisList.dealerName}]</span></td>
                                                    <td align="left" width="25%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${chassisList.locationName}</span></td>
                                                    <%}%>
                                                    <td nowrap align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" ><span>${chassisList.pdiPendingDays}</span></td>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr>
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <td colspan="5" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                            <%} else {%>
                                            <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
                        <%--<tr bgcolor="#FFFFFF">
                            <td style="padding-top:10px;color: red"><bean:message key="label.common.pendingPDINote" /></td>
                        </tr>--%>
                    </c:if>
                        <c:if test="${empty chassisList}">
                            <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                                <td valign="bottom" align="center" style="padding-top:10px;color: red"> <bean:message key="label.common.noRecordFound" /></td>
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
    <script type='text/javascript'>
        ${message}
    </script>

</html>
