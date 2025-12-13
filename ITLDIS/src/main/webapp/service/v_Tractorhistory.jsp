<%-- 
    Document   : v_Tractorhistory
    Created on : Nov 27, 2014, 12:39:01 PM
    Author     : vijay.mishra
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*" %>


<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script>
    window.onload =function(){
        var status = '${status}';
        if(status != ""){
        document.getElementById("Job Card Status").value=status;
        }
    }
    function submitForm() {
      
        if(document.getElementById("nameSrch").value.length<4){
        alert(chassisno_validation_msg);
        return false
        }
        
        document.getElementById('searchBy').submit();
    }
    function donotsubmit()
    {
        return false;
    }
    function printInvoice(jobCardNo)
    {
        //alert(jobCardNo)
        var vinNo='${nameSrch}';
        // var jobCardNo=jcNo;
        var strURL = "${pageContext.request.contextPath}/serviceProcess.do?option=viewJobcard&vinNo="+Base64.encode(vinNo)+"&jobCardNo="+Base64.encode(jobCardNo)+"&flag="+Base64.encode("print");
        //alert(strURL)
        window.open(strURL,'iji','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
    }
</script>
<!doctype html>
<html lang="en">
    <head>  <meta charset="utf-8">
        <title>ITLDIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
    </head>
    <body>

        <div class="breadcrumb_container">
            <ul class="hText">
                <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link></li>
                <c:if test="${closeflag ne 'true'}">
                <li class="breadcrumb_link"><bean:message key="label.common.tractorhistory" /></li>
                </c:if>
                <c:if test="${closeflag eq 'true'}">
                 <li class="breadcrumb_link"><bean:message key="label.common.closejobcard" /></li>
                </c:if>

            </ul>
        </div>
        <br />
          <div class="message" id="msg_saveFAILED">${message}</div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" >
                <c:if test="${closeflag ne 'true'}">
                <h1><bean:message key="label.common.tractorhistory" /></h1>
                </c:if>
                <c:if test="${closeflag eq 'true'}">
                 <h1><bean:message key="label.common.closejobcard" /></h1>
                </c:if>

        <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" >
            <c:if test="${closeflag ne 'true'}">
            <tr>
                <html:form action="serviceProcess.do?option=initSearchHistory" method="post" styleId="searchBy" >
                <td valign="middle" bgcolor="#FFFFFF" align="left">
                    <table width=40%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                        <tr height=20 bgcolor="#FFFFFF">
                        
                            <%--<html:text property="nameSrch" styleClass="headingSpas" maxlength="10" style="width:170px" onblur="this.value=TrimAll(this.value)"/>--%>
                           <td  align="center"><bean:message key="label.common.Chassisno" /></td>
                           <td align="left"><input name="vinNo" type="text" style="width: 150px" id="nameSrch" value="${nameSrch}" onblur="this.value=TrimAll(this.value)"/></td>
                            <td align="left">
                                <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                <input name="go" type="button" value="search" style="float:none; " onClick = "submitForm()"/>
                            </td>
                        </tr>
                    </table>
                </td>
                 </html:form>
            </tr>
            </c:if>
            <c:if test="${empty vehicle_HistoryList}">
        <tr bgcolor="#eeeeee" height="20">
            <td align= "center" style="color: red;">
                <bean:message key="label.common.historynotfound"/>
            </td>
        </tr>
    </c:if>
    <c:if test="${!empty vehicle_HistoryList}">
    <tr><td><%--<form name="form1" id="form1" method="post" action="">--%>
             <pg:pager url="serviceProcess.do" maxIndexPages="10" maxPageItems="10">
             <pg:param name="option" value="initSearchHistory"/>
             <pg:param name="vinNo"  value='${nameSrch}'/>
             
            <table width="100%" border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                <tr gbgcolor="#FFFFFF" height="20">

                    <td  align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.sno" /></label></td>
                    <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.jobcardno" /></label></td>
                    <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.Jobtype" /></label></td>
                    <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.jobcardDate" /></label></td>
                    <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.contactname" /></label></td>
                    <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.contactno" /></label></td>
                    <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.hrmworking" /></label></td>
                    <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.status" /></label></td>
                    <%if (userFunctionalities.contains("101")) {%>
                            <%} else {%>
                            <td nowrap align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;">
                               <label> <bean:message key="label.common.dealerName" /></label>
                            </td>
                            <td nowrap align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;">
                                <label><bean:message key="label.common.location" /></label>
                            </td>
                            <%}%>
                    <%--<td width="10%" align="center"><label>View Details</label></td>--%>
                   <!-- <td style="background-color:#f9f9f9 "></td>-->
                </tr>
                <c:set var="sno" value="1"></c:set>
                <logic:iterate id="vehicle_HistoryList" name="vehicle_HistoryList">
                    <pg:item>
                        <tr>
                            <td align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${sno}</td>
                            <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                               <%-- <a href="<%=cntxpath%>/serviceProcess.do?option=viewJobcard&productionCategory_Desc=${serviceform.productionCategory_Desc}&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${vehicle_HistoryList.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&pagename=searchhistory&flag=searchhistory" >
                                <span id ="jobCardNo${sno}" >${vehicle_HistoryList.jobCardNo}</span></a>--%>
                              <a href="#" onclick="printInvoice('${vehicle_HistoryList.jobCardNo}')">     ${vehicle_HistoryList.jobCardNo}</a>
                            </td>
                            <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${vehicle_HistoryList.jobTypeDesc}</span></td>
                            <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span  >${vehicle_HistoryList.jobCardDate}</span></td>

                            <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${vehicle_HistoryList.customerName}</span></td>
                            <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${vehicle_HistoryList.mobilePhone}</span></td>
                           

                            <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                            <c:if test="${vehicle_HistoryList.hmeRadio eq 'Y'}">
                            <span >YES</span>
                            </c:if>
                            <c:if test="${vehicle_HistoryList.hmeRadio ne 'Y'}">
                            <span >NO</span>
                            </c:if>
                            <c:if test="${!empty vehicle_HistoryList.hmr}">
                            <span >(${vehicle_HistoryList.hmr})</span>
                            </c:if>

                            </td>
                            <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${vehicle_HistoryList.status}</span></td>
                           <%if (userFunctionalities.contains("101")) {%>
                                    <%} else {%>
                                    <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${vehicle_HistoryList.dealercode} [${vehicle_HistoryList.dealerName}]</span></td>
                                    <td align="left" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${vehicle_HistoryList.locationName}</span></td>
                                    <%}%>
                        </tr>
                   </pg:item>
                    <c:set var="sno" value="${sno + 1 }" />
                </logic:iterate>
                <tr >
                    <%if (userFunctionalities.contains("101")) {%>
                                    <td colspan="8" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                    <%} else {%>
                                    <td colspan="10" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                    <%}%>
                    <%--<td colspan="6" align="center" bgcolor="#FFFFFF" class="textPaging" >--%>
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
            <%--  </form>--%>
        </td>
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

