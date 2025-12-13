<%-- 
    Document   : sercviceReminder
    Created on : Dec 16, 2014, 10:55:38 AM
    Author     : megha.pandya
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
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String dealerFlag = "";
            if (!userFunctionalities.contains("101")) {
                dealerFlag = "true";
            }
%>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_6.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

    function submitForm()
    {

        var dealerFlag='<%=dealerFlag%>';
        if(dealerFlag=='true')
        {
            var dealer=document.getElementById('Dealer Name').value;
            if(dealer=='')
            {
                alert(not_blank_dropdown_validation_msg+'Dealer Name');
                return false;
            }
        }

        if(document.getElementById("Range").checked==true){
            if(document.getElementById("From Date").value==""){
                alert("Please Select From Date .");
                document.getElementById("From Date").focus();
                return false;
            }
            if(document.getElementById("To Date").value==""){
                alert("Please Select To Date .");
                document.getElementById("To Date").focus();
                return false;
            }
        }

        var  fDate=document.getElementById("From Date").value;
        fDate = trim(fDate);
        var x =fDate;     //'1-1-2015';
        var a = x.split('/');
        var dateF = new Date (a[2], a[1] - 1,a[0]);

        var  tDate=document.getElementById("To Date").value;
        tDate = trim(tDate);
        var y =tDate;     //'17-1-2015';
        var b = y.split('/');
        var dateT = new Date (b[2], b[1] - 1,b[0]);

        //alert(dateF)
        //alert(dateT)
        if(dateF>dateT){
            alert(date_validation_msg)
            document.getElementById('From Date').focus();
            return false;
        }
  
        document.getElementById('etype').value="view";
        document.getElementById("serviceReminderList").submit();
    }

    function ExportForm()
    {
        document.getElementById('etype').value="export";
        document.getElementById("serviceReminderList").submit();
    }
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
    function tractorHistory(vinNo,flag)
    {
        window.location.href='<%=cntxpath%>/serviceProcess.do?option=initSearchHistory&flag=flag&vinNo='+vinNo+'&flag='+flag;
    <%--var url=contextPath+'/serviceProcess.do?option=initSearchHistory&flag=flag&vinNo='+vinNo+'&flag='+flag;
    var win=window.open(url, '_blank',"directories=no,menubar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,top=0,location=no");
    win.focus();--%>
           }
   
</script>
           <style>
               *{margin:0px; padding:0px;}
           </style>

<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link></li>
            <li class="breadcrumb_link"><bean:message key="label.common.serviceReminder" /></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED" align="center">${message}</div>

    <center>


        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.common.serviceReminder" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <form name="serviceReminderList" id="serviceReminderList" method="post" action="<%=cntxpath%>/serviceProcess.do?option=initsercviceReminder&flag=check" onsubmit="">
                                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
                                    <tbody >
                                        <tr bgcolor="#FFFFFF">
                                            <td  style="padding-left:5px;width:5% "  class="headingSpas" nowrap align="right">
                                                <c:if test="${range eq '1'}">
                                                    <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                                </c:if>
                                                <c:if test="${range ne '1' }">
                                                    <input type="checkbox" name="range" value="1"  id="Range" >
                                                </c:if>
                                                <bean:message key="label.common.dueDate"/></td>
                                            <td style="padding-left:5px;width:5%" align="left">
                                                <input name="fromdate" type="text" class="datepicker" id="From Date" style="width:60px"  value="${serviceForm.fromdate}" onkeydown="return false;"/>
                                            </td>
                                            <td  style="padding-left:5px;width:2% "  class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/></td>
                                            <td style="padding-left:5px;width:5%" align="left">
                                                <input name="todate" type="text" class="datepicker" id="To Date" value="${serviceForm.todate}" style="width:60px" onkeydown="return false;"/>
                                            </td>
                                            <td  style="padding-left:5px;width:5% "  class="headingSpas" nowrap align="center"><bean:message key="label.common.Jobtype"/></td>
                                            <td align="left" style="padding-left:10px;width:10%">
                                                <select name="jobType"  id="Job Type" >
                                                    <option value='' >ALL</option>
                                                    <c:forEach items="${jobTypeList}" var="dataList">
                                                        <c:if test="${serviceForm.jobType eq dataList.value}">
                                                            <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>
                                                        </c:if>
                                                        <c:if test="${serviceForm.jobType ne dataList.value}">
                                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <%} else {%>
                                            <td align="right" style="white-space: nowrap;"> <bean:message key="label.common.dealerName" />    </td>
                                            <td width="15%">
                                                <select id="Dealer Name" name="dealercode" style="width:300px !important">
                                                    <option value="ALL" >ALL</option>
                                                    <%--  <option value='' >--Select Dealer--</option>--%>
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
                                            <td style="padding-left:5px" align="left"  >
                                                <input name="go" type="button" id="go" class="headingSpas1" value="search" onclick="submitForm()"/>
                                                <input type="button" value="Export" name="export" id="export" class="headingSpas1" onclick="ExportForm()"/> <input type="hidden" name="etype" id="etype" />
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </td>
                    </tr>
                </table>
                    <div style="overflow: auto; width:100%">
                        <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                        <c:if test="${!empty sercviceReminderList}">
                            <tr height=25 bgcolor="#FFFFFF">
                                <td align= center class="headingSpas">
                                   
                                        <pg:pager url="serviceProcess.do" maxIndexPages="10" maxPageItems="15">
                                            <pg:param name="option" value="initsercviceReminder"/>
                                            <pg:param name="jobType"  value='${serviceForm.jobType}'/>
                                            <pg:param name="fromdate"  value='${serviceForm.fromdate}'/>
                                            <pg:param name="todate"  value='${serviceForm.todate}'/>
                                            <pg:param name="dealercode"  value='${serviceForm.dealercode}'/>
                                            <pg:param name="flag"  value='check'/>
                                            <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>

                                            <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                                <tr bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" width="2%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.sno" />
                                                    </td>
                                                    <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.Chassisno" />
                                                    </td>
                                                    <%-- <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                         <bean:message key="label.common.Modelcode" />
                                                     </td>--%>
                                                    <td nowrap align="left" width="13%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.Modelcodedesc" />
                                                    </td>
                                                    <td nowrap align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.Jobtype" />
                                                    </td>
                                                    <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.CustomerName" />
                                                    </td>
                                                    <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.contactno" />
                                                    </td>
                                                    <td nowrap align="left" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.dueDate" />
                                                    </td>
                                                    <%if (userFunctionalities.contains("101")) {%>
                                                    <%} else {%>
                                                    <td   align="left" width="20%"  class="headingSpas" nowrap ><b><bean:message key="label.common.dealerName" /></b></td>
                                                    <%}%>
                                                    <td nowrap align="left" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.followUpStatus" />
                                                    </td>
                                                    <td nowrap align="left" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.followUpDate" />
                                                    </td>
                                                    <td  align="center" class="headingSpas" ><b><bean:message key="label.common.FollowUp" /></b></td>
                                                </tr>
                                                <c:set var="sno" value="1"></c:set>
                                                <logic:iterate id="sercviceReminderList" name="sercviceReminderList">
                                                    <pg:item>
                                                        <tr  bgcolor="#eeeeee" height="20">
                                                            <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                            <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;white-space: nowrap" >
                                                                <%--<a href="<%=cntxpath%>/warrantyAction.do?option=packingDetails4View&packingNo=${packingList.packingNo}" >--%>
                                                                <span style="float:left; line-height: 25px;">${sercviceReminderList.vinNo}</span>
                                                                <%if (userFunctionalities.contains("620")) {%>
                                                                <span style="float:left; line-height: 25px;"><img src='${pageContext.request.contextPath}/images/History_Icon.gif' border='0'   title="Click here view Tractor History" onclick="tractorHistory('${sercviceReminderList.vinNo}','view')"/> </span>
                                                                <%}%>
                                                               
                                                            </td>
                                                            <%--<td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${sercviceReminderList.modelCode}</td>--%>
                                                            <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.modelCodeDesc}</span></td>
                                                            <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;white-space: nowrap;"><span >${sercviceReminderList.jobTypeDesc}</span></td>
                                                            <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;white-space: nowrap;"><span >${sercviceReminderList.customerName}</span></td>
                                                            <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.contactno}</span></td>
                                                            <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.dueDate}</span></td>
                                                            <%if (userFunctionalities.contains("101")) {%>
                                                            <%} else {%>
                                                            <td align="left" bgcolor="#FFFFFF" title=" ${sercviceReminderList.dealerName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap;">
                                                                <span>
                                                                    ${sercviceReminderList.dealercode} [${sercviceReminderList.dealerName}] [${sercviceReminderList.locationName}]
                                                                </span>
                                                            </td>
                                                            <%}%>
                                                            <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.followUpStatus}</span></td>
                                                            <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.lastFollowUpDate}</span></td>
                                                            <td align="center" style="background:#fff;" > <a href="serviceProcess.do?option=getFollowup&scheduleID=${sercviceReminderList.scheduleID}"><img src="images/follow_up.png"  alt="follow-up" /></a></td>
                                                        </tr>
                                                    </pg:item>
                                                    <c:set var="sno" value="${sno + 1 }" />
                                                </logic:iterate>
                                                <tr>
                                                    <%if (userFunctionalities.contains("101")) {%>
                                                    <td colspan="10" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                        <%} else {%>
                                                    <td colspan="11" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                        <%}%>
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
                                        </pg:pager>
                                  
                                </td>
                            </tr>
                            </table>
                        </div>
                    </c:if>
                    <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <c:if test="${empty sercviceReminderList}">
                        <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                            <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.noRecordFound" /></td>
                        </tr>
                    </c:if>
                    </table>
            </div>
        </div>
    </center>
</div>