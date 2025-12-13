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

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script src="JS/Master_290414.js"></script>
<script src="${pageContext.request.contextPath}/js/base64.js"></script>
<link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >

<script>
    function submitForm() {
        var checkedtype = '';
        var searchvalue =document.getElementById('nameSrch').value;
        var fromdate=document.getElementById('From Date').value
        var todate=document.getElementById('To Date').value

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
    <%-- else{
         if(document.getElementById("From Date").value!="" || document.getElementById("To Date").value!=""){
         alert("Please Select Check Box .");
         return false;
         }
     }--%>

             var fdate=fromdate.split('/');
             var fdate=fdate[2]+'/'+fdate[1]+'/'+fdate[0];
             var d1=new Date(fdate);

             var tdate=todate.split('/');
             var tdate=tdate[2]+'/'+tdate[1]+'/'+tdate[0];
             var d2=new Date(tdate);

             if(d1>d2){

                 alert(date_validation_msg)
                 document.getElementById('From Date').focus();
                 return false;
             }

             document.getElementById('eType').value=" ";
             document.getElementById('searchBy').submit();
         }
         function donotsubmit()
         {
             return false;
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

         // BY APURV SINGH
         function Export()
         {
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
             document.getElementById('eType').value="export";
             document.getElementById("searchBy").submit();
         }
</script>
<html>
    <head><title>ITLDIS</title></head>

    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link></li>
            <li class="breadcrumb_link"><bean:message key="label.common.viewinstallation" /></li>
        </ul>
    </div>

    <div class="message" id="msg_saveFAILED">${message}</div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1><bean:message key="label.common.viewinstallation" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="installProcess.do?option=init_viewDetails&flag=check" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=2 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">
                                        <td  style="padding-left:10px" width="8%" class="headingSpas" nowrap align="right">
                                            <span class="formLabel"><bean:message key="label.common.Chassisno" />&nbsp;&nbsp;</span>
                                        </td>
                                        <td align="left" width="10%">
                                            <input name="nameSrch" type="text" id="nameSrch" value="${nameSrch}"  onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <td  style="width:5%"  class="headingSpas" nowrap align="right">
                                            <c:if test="${range eq '1'}">
                                                <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                            </c:if>
                                            <c:if test="${range ne '1'}">
                                                <input type="checkbox" name="range" value="1"  id="Range" >
                                            </c:if>
                                            <bean:message key="label.common.installationdate"/></td>
                                        <td width="8%" align="left">
                                            <input name="fromDate" type="text" class="datepicker" id="From Date" style="width:60px" value="${fromDate}" onkeydown="return false;"/>
                                        </td>
                                        <td  style="width:2% "  class="headingSpas" nowrap align="left"><bean:message key="label.common.to"/></td>
                                        <td width="8%" align="left">
                                            <input name="toDate" type="text" class="datepicker" id="To Date" style="width:60px" value="${toDate}"  onkeydown="return false;"/>
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
                                    </tr>
                                    <tr  bgcolor="#FFFFFF">
                                        <td style="padding-left:10px" align="center" colspan="9">
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
                                    <pg:param name="option" value="init_viewDetails"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <pg:param name="fromDate"  value='${fromDate}'/>
                                    <pg:param name="toDate"  value='${toDate}'/>
                                    <pg:param name="dealercode"  value='${serviceForm.dealercode}'/>
                                    <pg:param name="flag"  value='check'/> 
                                    <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>

                                    <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;">
                                                <bean:message key="label.common.sno" />
                                            </td>
                                            <td nowrap align="left" width="17%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.installationno" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.installationdate" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.Chassisno" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.Modelfamily" />
                                            </td>

                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.CustomerName" />
                                            </td>
                                            <td nowrap align="left" width="7%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.mobilephone" />
                                            </td>
                                            <%if (userFunctionalities.contains("101")) {%>

                                            <%} else {%>
                                            <td nowrap align="left" width="16%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.dealerName" />
                                            </td>
                                            <td nowrap align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.location" />
                                            </td>
                                            <%}%>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="chassisList" name="chassisList">
                                            <pg:item>
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>

                                                    <td align="left" nowrap width="17%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <%--<a href="${pageContext.request.contextPath}/installProcess.do?option=initInstallInfoPrint&vinNo=${chassisList.vinNo}&insNo=${chassisList.insNo}&dealerCode=${chassisList.dealercode}" >--%>
                                                        <a href="#" class="initInstallInfoPrint" data-vinNo="${chassisList.vinNo}" data-dealerCode="${chassisList.dealercode}">
                                                            <span id="vinNo${sno}">${chassisList.insNo}</span>
                                                        </a>
                                                    </td>
                                                    <td align="left" nowrap width="10%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        ${chassisList.insDate}
                                                    </td>
                                                    <td align="left" width="10%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.chassisNo}<span ></span></td>
                                                    <td align="left" width="10%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.modelFamily}<span ></span></td>

                                                    <td align="left" width="15%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.customerName}<span></span></td>
                                                    <td align="left" width="7%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.mobilePhone}<span></span></td>
                                                    <%if (userFunctionalities.contains("101")) {%>

                                                    <%} else {%>
                                                    <td align="left" width="17%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.dealercode} [${chassisList.dealerName}]<span></span></td>
                                                    <td align="left" width="13%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${chassisList.locationName}<span></span></td>
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
                                <bean:message key="label.common.insviewNote"/>
                            </td></tr>--%>
                    </c:if>
                    <c:if test="${empty chassisList}">
                        <tr  bgcolor="#eeeeee" height="20">
                            <td align= "center" style="color: red;">  <bean:message key="label.common.noRecordFound"/></td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </div>
    </center>
    <p><script src="JS/jquery-ui.js"></script>
        <script>
            $(function() {
                $("#datepicker").datepicker();
            });
            
            $(document).ready(function(){
                $('.initInstallInfoPrint').click(function(){
                    var url = "installProcess.do?option=initInstallInfoPrint&vinNo="+ Base64.encode($(this).attr('data-vinNo').trim())+"&insNo="+Base64.encode($(this).text().trim())+"&dealerCode="+Base64.encode($(this).attr('data-dealerCode').trim());
                    $(this).attr('href',url);

                });
            });



        </script>
    </p>
</body>
</html>

