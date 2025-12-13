<%-- 
    Document   : v_viewalljobcard
    Created on : May 15, 2014, 1:52:57 PM
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
<script>
    window.onload =function(){
        var status = '${status}';
        if(status != ""){
            document.getElementById("Job Card Status").value=status;
        }
    }
    function submitForm() {
        var checkedtype = '';
        var searchvalue =document.getElementById('nameSrch').value;
        if(document.getElementById('radiobutton_Job').checked)
        { 
            checkedtype='jobCardNo';
        }
        else
            if(document.getElementById('radiobutton_VinNo').checked)
        {
            checkedtype='vinno';
        }
        else if(document.getElementById('radiobutton_MobileNo').checked)
        {
            checkedtype='mobilePhone';
        }
        document.getElementById('Srch').value = checkedtype + ':'+searchvalue;
        //        alert(''+document.getElementById('nameSrch').value);

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
                    <li class="breadcrumb_link"><bean:message key="label.common.viewalljobcard" /></li>
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
                        <h1><bean:message key="label.common.viewalljobcard" /></h1>
                    </c:if>
                    <c:if test="${closeflag eq 'true'}">
                        <h1><bean:message key="label.common.closejobcard" /></h1>
                    </c:if>

                    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" >
                        <c:if test="${closeflag ne 'true'}">
                            <tr>
                                <html:form action="serviceProcess.do?option=init_viewallJobcarddetails&flag=check" method="post" styleId="searchBy" >
                                    <td valign="middle" bgcolor="#FFFFFF" align="left">
                                        <table width=100%  border=0 cellspacing=0 cellpadding=3 bgcolor=#cccccc>
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <tr height=20 bgcolor="#FFFFFF">
                                                <c:choose>

                                                    <c:when test="${columnName eq 'jobCardNo'}">
                                                        <td ><input name="radiobutton" id="radiobutton_Job" type="radio" class="checknew" value="job" checked="checked"/>
                                                            <span class="formLabel"><bean:message key="label.common.jobcardno" /></span></td>
                                                        <td ><input name="radiobutton" id="radiobutton_VinNo"type="radio" class="checknew" value="vinno" />
                                                            <span class="formLabel"><bean:message key="label.common.Chassisno" /></span></td>
                                                        <td ><input name="radiobutton" id="radiobutton_MobileNo"type="radio" class="checknew" value="mobileno" />
                                                            <span class="formLabel"><bean:message key="label.common.contactno" /></span></td>
                                                        </c:when>
                                                        <c:when test="${columnName eq 'vinno'}">
                                                        <td ><input name="radiobutton" id="radiobutton_Job"type="radio" class="checknew" value="job"/>
                                                            <span class="formLabel" ><bean:message key="label.common.jobcardno" /></span></td>
                                                        <td ><input name="radiobutton" id="radiobutton_VinNo"type="radio" class="checknew" value="vinno" checked="checked" />
                                                            <span class="formLabel"><bean:message key="label.common.Chassisno" /></span></td>
                                                        <td ><input name="radiobutton" id="radiobutton_MobileNo"type="radio" class="checknew" value="mobileno" />
                                                            <span class="formLabel"><bean:message key="label.common.contactno" /></span></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <td ><input name="radiobutton" id="radiobutton_Job"type="radio" class="checknew" value="job" />
                                                            <span class="formLabel" ><bean:message key="label.common.jobcardno" /></span></td>
                                                        <td ><input name="radiobutton" id="radiobutton_VinNo"type="radio" class="checknew" value="vinno"/>
                                                            <span class="formLabel"><bean:message key="label.common.Chassisno" /></span></td>
                                                        <td ><input name="radiobutton" id="radiobutton_MobileNo"type="radio" class="checknew" value="mobileno" checked="checked"/>
                                                            <span class="formLabel"><bean:message key="label.common.contactno" /></span></td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <%--<html:text property="nameSrch" styleClass="headingSpas" maxlength="10" style="width:170px" onblur="this.value=TrimAll(this.value)"/>--%>
                                                <td ><input name="nameSrch" type="text" id="nameSrch" value="${nameSrch}" onblur="this.value=TrimAll(this.value)"/></td>
                                                <td    class="headingSpas" nowrap align="right"><bean:message key="label.common.status"/></td>
                                                <td   class="headingSpas" nowrap align="left">
                                                    <select id="Job Card Status" name="jobCardStatus">
                                                        <option value="ALL">ALL</option>
                                                        <option value="open">OPEN</option>
                                                        <option value="reject">REJECT</option>
                                                        <option value="close">CLOSE</option>
                                                        <option value="billed">BILLED</option>
                                                    </select>
                                                </td>
                                                <td   class="headingSpas" nowrap align="right">
                                                    <c:if test="${range eq '1'}">
                                                        <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                                    </c:if>
                                                    <c:if test="${range ne '1' }">
                                                        <input type="checkbox" name="range" value="1"  id="Range" >
                                                    </c:if>
                                                    <bean:message key="label.common.tractorindate"/></td>
                                                <td  align="left">
                                                    <input name="fromdate" type="text" class="datepicker" id="From Date"   value="${fromdate}" onkeydown="return false;"/>
                                                </td>
                                                <td    class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/></td>
                                                <td align="left">
                                                    <input name="todate" type="text" class="datepicker" id="To Date" value="${todate}" onkeydown="return false;"/>
                                                </td>
                                                <td >
                                                    <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                                    <input name="go" type="button" value="search" style="float:none; " onClick = "submitForm()"/>
                                                </td>
                                            </tr>

                                            <%} else {%>
                                            <tr height=20 bgcolor="#FFFFFF">
                                                <c:choose>

                                                    <c:when test="${columnName eq 'jobCardNo'}">
                                                        <td  style="white-space: nowrap" width="5%"><input name="radiobutton" id="radiobutton_Job" type="radio" class="checknew" value="job" checked="checked"/>
                                                            <span class="formLabel" ><bean:message key="label.common.jobcardno" /></span></td>
                                                        <td style="white-space: nowrap" width="10%"><input name="radiobutton" id="radiobutton_VinNo"type="radio" class="checknew" value="vinno" />
                                                            <span class="formLabel"><bean:message key="label.common.Chassisno" /></span></td>
                                                        <td style="white-space: nowrap" width="10%"><input name="radiobutton" id="radiobutton_MobileNo"type="radio" class="checknew" value="mobileno" />
                                                            <span class="formLabel"><bean:message key="label.common.contactno" /></span></td>
                                                        </c:when>
                                                        <c:when test="${columnName eq 'vinno'}">
                                                        <td style="white-space: nowrap" width="5%"><input name="radiobutton" id="radiobutton_Job"type="radio" class="checknew" value="job"/>
                                                            <span class="formLabel" ><bean:message key="label.common.jobcardno" /></span></td>
                                                        <td style="white-space: nowrap" width="10%"><input name="radiobutton" id="radiobutton_VinNo"type="radio" class="checknew" value="vinno" checked="checked" />
                                                            <span class="formLabel"><bean:message key="label.common.Chassisno" /></span></td>
                                                        <td style="white-space: nowrap" width="10%"><input name="radiobutton" id="radiobutton_MobileNo"type="radio" class="checknew" value="mobileno" />
                                                            <span class="formLabel"><bean:message key="label.common.contactno" /></span></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <td style="white-space: nowrap" width="5%"><input name="radiobutton" id="radiobutton_Job"type="radio" class="checknew" value="job" />
                                                            <span class="formLabel" ><bean:message key="label.common.jobcardno" /></span></td>
                                                        <td style="white-space: nowrap" width="10%"><input name="radiobutton" id="radiobutton_VinNo"type="radio" class="checknew" value="vinno"/>
                                                            <span class="formLabel"><bean:message key="label.common.Chassisno" /></span></td>
                                                        <td style="white-space: nowrap" width="10%"><input name="radiobutton" id="radiobutton_MobileNo"type="radio" class="checknew" value="mobileno" checked="checked"/>
                                                            <span class="formLabel"><bean:message key="label.common.contactno" /></span></td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <%--<html:text property="nameSrch" styleClass="headingSpas" maxlength="10" style="width:170px" onblur="this.value=TrimAll(this.value)"/>--%>
                                                <td ><input name="nameSrch" type="text" id="nameSrch" value="${nameSrch}" onblur="this.value=TrimAll(this.value)"/></td>
                                                <td    class="headingSpas" nowrap align="right"><bean:message key="label.common.status"/></td>
                                                <td   class="headingSpas" nowrap align="left">
                                                    <select id="Job Card Status" name="jobCardStatus">
                                                        <option value="ALL">ALL</option>
                                                        <option value="open">OPEN</option>
                                                        <option value="reject">REJECT</option>
                                                        <option value="close">CLOSE</option>
                                                        <option value="billed">BILLED</option>
                                                    </select>
                                                </td>
                                                <td   class="headingSpas" nowrap align="right">
                                                    <c:if test="${range eq '1'}">
                                                        <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                                    </c:if>
                                                    <c:if test="${range ne '1' }">
                                                        <input type="checkbox" name="range" value="1"  id="Range" >
                                                    </c:if>
                                                    <bean:message key="label.common.tractorindate"/></td>
                                                <td  align="left">
                                                    <input name="fromdate" type="text" class="datepicker" id="From Date"   value="${fromdate}" onkeydown="return false;"/>
                                                </td>
                                                <td    class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/></td>
                                                <td align="left">
                                                    <input name="todate" type="text" class="datepicker" id="To Date" value="${todate}" onkeydown="return false;"/>
                                                </td>
                                            </tr>
                                            <%-- <td >
                                                 <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                                 <input name="go" type="button" value="search" style="float:none; " onClick = "submitForm()"/>
                                             </td>--%>

                                            <tr height=20 bgcolor="#FFFFFF">

                                                <td align="right" width="5%" style="white-space: nowrap;"> <bean:message key="label.common.dealerName" />    </td>
                                                <td width="20%" colspan="3" style="padding-right: 10px;">
                                                    <select id="Dealer Name" name="dealercode" style="width:300px !important">
                                                        <option value="ALL" >ALL</option>
                                                        <%--<option value='' >--Select Dealer--</option>--%>
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
                                                <td colspan="7" align="center" style="padding-right: 350px;">
                                                    <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                                    <input name="go" type="button" value="search" style="float:none; " onClick = "submitForm()"/>
                                                </td>
                                            </tr>
                                            <%}%>

                                        </table>
                                    </td>
                                </html:form>
                            </tr>
                        </c:if>
                        <c:if test="${empty jobcardDetails}">
                            <tr bgcolor="#FFFFFF" >   <%--class="formCatHeading"--%>
                                <td valign="top" align="center"  style="padding-top:10px;color: red">
                                    <bean:message key="label.common.noRecordFound" />
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${!empty jobcardDetails}">
                            <tr  bgcolor="#FFFFFF">
                                <td align= center class="headingSpas">

                                    <pg:pager url="serviceProcess.do" maxIndexPages="10" maxPageItems="15">
                                        <c:if test="${closeflag eq 'true'}">
                                            <pg:param name="option" value="initCloseJobCards"/>
                                        </c:if>
                                        <c:if test="${closeflag ne 'true'}">
                                            <pg:param name="option" value="init_viewallJobcarddetails"/>
                                        </c:if>
                                        <pg:param name="Srch"  value='<%=request.getParameter("Srch")%>'/>
                                        <pg:param name="fromdate"  value="${fromdate}"/>
                                        <pg:param name="todate"  value="${todate}"/>
                                        <pg:param name="jobCardStatus"  value="${status}"/>
                                        <pg:param name="closeflag"  value="${closeflag}"/>
                                        <pg:param name="dealercode"  value="${serviceForm.dealercode}"/>
                                        <pg:param name="flag"  value='check'/>
                                        <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>
                                        <table width=98%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc align="center" bgcolor=#cccccc>
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td nowrap align="center" ><bean:message key="label.common.sno" /> </td>
                                                <td  align="left"><bean:message key="label.common.jobcardno" /></td>
                                                <td  align="left"><bean:message key="label.common.Chassisno" /></td>
                                                <td  align="center"><bean:message key="label.common.tractorindate" /></td>
                                                <td  align="left"><bean:message key="label.common.JobcardPayeeName" /></td>
                                                <td  align="left" ><bean:message key="label.common.contactno" /> </td>
                                                <td  align="center" ><bean:message key="label.common.status" /></td>
                                                <%if (userFunctionalities.contains("101")) {%>
                                                <%} else {%>
                                                <td nowrap align="left"  >
                                                    <bean:message key="label.common.dealerName" />
                                                </td>
                                                <td nowrap align="left"  >
                                                    <bean:message key="label.common.location" />
                                                </td>
                                                <%}%>
                                                <!--<td width="22%">&nbsp;</td>-->
                                            </tr>
                                            <c:set var="sno" value="1"></c:set>
                                            <logic:iterate id="jobcardDetails" name="jobcardDetails">
                                                <pg:item>
                                                    <tr  bgcolor="#FFFFFF" height="20">
                                                        <td nowrap align="center"  >${sno}</td>
                                                        <td nowrap align="left">
                                                            <c:if test="${closeflag eq 'true'}">
                                                                <%--<a href="<%=cntxpath%>/serviceProcess.do?option=viewJobcard&vinNo=${jobcardDetails.vinNo}&jobCardNo=${jobcardDetails.jobCardNo}&flag=closeAll" >--%>
                                                                <a href="#" class="viewJobcardCloseAll" data-vinNo="${jobcardDetails.vinNo}">
                                                                    <span id ="jobCardNo${sno}" >${jobcardDetails.jobCardNo}</span></a>
                                                                </c:if>
                                                                <c:if test="${closeflag ne 'true'}">
                                                                    <%-- <a href="<%=cntxpath%>/serviceProcess.do?option=viewJobcard&vinNo=${jobcardDetails.vinNo}&jobCardNo=${jobcardDetails.jobCardNo}&flag=viewAll" >--%>
                                                                <a href="#" class="viewJobcardViewAll" data-vinNo="${jobcardDetails.vinNo}">
                                                                    <span id ="jobCardNo${sno}" >${jobcardDetails.jobCardNo}</span></a>
                                                                </c:if>
                                                        </td>
                                                        <td nowrap align="left"><span id="vinNo${sno}">${jobcardDetails.vinNo}</span></td>
                                                        <td nowrap align="center"><span id="jobCardDate${sno}">${jobcardDetails.jobCardDate}</span></td>
                                                        <td nowrap align="left" ><span id="payeeName${sno}">${jobcardDetails.payeeName}</span></td>
                                                        <td nowrap align="left" ><span id="mobilePhone${sno}">${jobcardDetails.mobilePhone}</span></td>
                                                        <c:if test="${jobcardDetails.jobCardStatus eq 'OPEN'}">
                                                            <td nowrap align="center"  >
                                                                <%--<a href="${pageContext.request.contextPath}/serviceProcess.do?option=show_SingleJobcarddetails&jobCardNo=${jobcardDetails.jobCardNo}&vinNo=${jobcardDetails.vinNo}&pathNm=ViewJC" >--%>
                                                                <%if (userFunctionalities.contains("608")) {%>
                                                                <a href="#" class="initVehicleInformation" data-vinNo="${jobcardDetails.vinNo}" data-jobCardNo="${jobcardDetails.jobCardNo}" >
                                                                    <span id="status${sno}">${jobcardDetails.jobCardStatus}</span>
                                                                </a>
                                                                <%} else {%>
                                                                <span id="status${sno}">${jobcardDetails.jobCardStatus}</span>
                                                                <%}%>
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${jobcardDetails.jobCardStatus ne 'OPEN'}">
                                                            <td align="center">
                                                                <a href="#" class="viewJobcard" data-vinNo="${jobcardDetails.vinNo}" data-jobCardNo="${jobcardDetails.jobCardNo}" data-dealerCode="${jobcardDetails.dealercode}" data-status="${jobcardDetails.jobCardStatus}">
                                                                    <span id ="status${sno}" >${jobcardDetails.jobCardStatus}</span></a>
                                                            </td>
                                                        </c:if>

                                                        <%if (userFunctionalities.contains("101")) {%>
                                                        <%} else {%>
                                                        <td align="left"><span>${jobcardDetails.dealercode} [${jobcardDetails.dealerName}]</span></td>
                                                        <td align="left"><span>${jobcardDetails.locationName}</span></td>
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
                        </c:if>
                    </table>
                </div>
            </div>
        </center>

        <p><script src="JS/jquery-ui.js"></script>
            <script>  $(function() {
                $("#datepicker").datepicker();
            });
            $(document).ready(function(){
                $('.viewJobcardCloseAll').click(function(){
                    var url = "serviceProcess.do?option=viewJobcard&jobCardNo="+Base64.encode($(this).text().trim())+"&vinNo="+ Base64.encode($(this).attr('data-vinNo').trim())+"&flag="+Base64.encode("closeAll");
                    $(this).attr('href',url);
                });
                $('.viewJobcardViewAll').click(function(){
                    var url = "serviceProcess.do?option=viewJobcard&jobCardNo="+Base64.encode($(this).text().trim())+"&vinNo="+ Base64.encode($(this).attr('data-vinNo').trim())+"&flag="+Base64.encode("viewAll");
                    $(this).attr('href',url);
                });
                $('.viewJobcard').click(function(){
                   var url = "serviceProcess.do?option=viewJobcard&vinNo="+Base64.encode($(this).attr('data-vinNo').trim())+"&jobCardNo="+Base64.encode($(this).attr('data-jobCardNo').trim())+"&flag="+Base64.encode("viewAll")+"&dealerCode="+Base64.encode($(this).attr('data-dealerCode').trim())+"&status="+Base64.encode($(this).attr('data-status').trim());
                   $(this).attr('href',url);
                });

                $('.initVehicleInformation').click(function(){
                    var url = "serviceCreateJC.do?option=initVehicleInformation&vinNo="+$(this).attr('data-vinNo').trim()+"&jobCardNo="+$(this).attr('data-jobCardNo').trim()+"&openJC=viewAll";
                    $(this).attr('href',url);
                });
            });
            </script>
        </p>
    </body>
</html>

