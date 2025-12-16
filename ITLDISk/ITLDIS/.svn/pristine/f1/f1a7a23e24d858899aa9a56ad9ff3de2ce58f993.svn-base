<%-- 
    Document   : v_closejobcard
    Created on : May 23, 2014, 3:08:52 PM
    Author     : jasmeen.kaur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>


<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>
<script src="${pageContext.request.contextPath}/JS/Master_290414.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Master_290414.css" type="text/css" >
<script>


    function GetXmlHttpObject()
    {
        var objXmlHttp = null;
        if (navigator.userAgent.indexOf('Opera') >= 0)
        {
            var xmlHttp=new XMLHttpRequest();
            return xmlHttp;
        }
        if (navigator.userAgent.indexOf('MSIE') >= 0)
        {
            var strName = 'Msxml2.XMLHTTP';
            if (navigator.appVersion.indexOf('MSIE 5.5') >=0)
            {
                strName = 'Microsoft.XMLHTTP';
            }
            try
            {
                objXmlHttp = new ActiveXObject(strName);
                // objXmlHttp.onreadystatechange = handler;
                return objXmlHttp;
            }
            catch(e)
            {
                alert(browser_validation_msg);
                return;
            }
        }
        if (navigator.userAgent.indexOf('Mozilla') >= 0)
        {
            objXmlHttp = new XMLHttpRequest();
            return objXmlHttp;
        }
    }

    function getStatus(vinNo,jobcard,row){

        var x="false";
        var jobid=document.getElementById("jobid"+row).value;
        var jobStatus=document.getElementById("jobCardStatus"+row).value;
        if(jobStatus!="CLOSE"){
           <%-- if(jobStatus=='OPEN'){}
            if(jobStatus=='REJECT'){rejectConfirm_validation_msg}--%>
            var r=jobStatus=='OPEN'?confirm(reopenConfirm_validation_msg+jobcard +" ?"):confirm(rejectConfirm_validation_msg+jobcard +" ?");
            if (r==true)
            {
                x="true";
            }
            else
            {
                x="false";
                document.getElementById("jobCardStatus"+row).value='CLOSE';
            }
            if(x=="true"){
                window.location.href="<%=cntxpath%>/serviceProcess.do?option=viewJobcard&vinNo="+Base64.encode(vinNo)+"&jobCardNo="+Base64.encode(jobcard)+"&flag="+Base64.encode("ReOpenUpdate")+"&status="+Base64.encode(jobStatus);
                <%--var strURL="<%=cntxpath%>/serviceProcess.do?option=setJobCardNoStatus&jobcard=" + jobcard+"&jobStatus="+jobStatus+"&tm=" + new Date().getTime();
                xmlHttp = GetXmlHttpObject();
                xmlHttp.onreadystatechange = function()
                {
                    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                    {
                        res = trim(xmlHttp.responseText);
                       
                        if(res=='SUCCESS' ){
                            document.getElementById("reopenJobCard").submit();
                        }
                    }
                }
                xmlHttp.open("POST", strURL, true);
                xmlHttp.send(null);--%>
            }

            
        }

    }

    function submitForm() {
        var checkedtype = '';
        var searchvalue =document.getElementById('nameSrch').value;
        if(document.getElementById('radiobutton_Job').checked)
        { 
            checkedtype='JobCardNo';
        }
        else
            if(document.getElementById('radiobutton_VinNo').checked)
        {
            checkedtype='vinno';
        }
        else if(document.getElementById('radiobutton_MobileNo').checked)
        {
            checkedtype='MobilePhone';
        }
        document.getElementById('Srch').value = checkedtype + ':'+searchvalue;
        //        alert(''+document.getElementById('nameSrch').value);
        document.getElementById('searchBy').submit();
    }
    function donotsubmit()
    {
        return false;
    }
    $(document).ready(function(){
                $('.viewJobcardReOpen').click(function(){
                    var url = "serviceProcess.do?option=viewJobcard&jobCardNo="+Base64.encode($(this).text().trim())+"&vinNo="+ Base64.encode($(this).attr('data-vinNo').trim())+"&flag="+Base64.encode("ReOpen");
                    $(this).attr('href',url);
                });
            });
</script>

<html >
    <head><title>ITLDIS</title></head>
    <body> 
       <div class="breadcrumb_container">
           <ul class="hText">
                <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link> </li>
                <li class="breadcrumb_link"><bean:message key="label.common.reOpenJobCard" /></li>
         </ul>
        </div>
   
<div class="message" id="msg_saveFAILED"><html:errors/>${message}</div>
  <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="width: 100%">
                <h1 class="hText"><span class="formLabel"><bean:message key="label.common.reOpenJobCard" /></span></h1>
        <form action="serviceProcess.do?option=initReOpenJobCards" method="post" id="reopenJobCard" name="reopenJobCard">
        <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc >

            <c:if test="${empty jobcardDetails}">
                <tr class="headingSpas" bgcolor="#FFFFFF" height="50">
                    <td valign="bottom" align="center" style="padding-top:10px;color: red">
                         <bean:message key="label.common.noRecordFound" />
                    </td>
                </tr>
            </c:if>
            <c:if test="${!empty jobcardDetails}">
                <tr bgcolor="#eeeeee" height="20">
                    <td align= center class="headingSpas">
                        <%--<pg:pager url="serviceProcess.do" maxIndexPages="10" maxPageItems="15">
                            <pg:param name="option" value="initReOpenJobCards"/>--%>
                            <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                <tr bgcolor="#eeeeee" height="20">
                                    <td nowrap align="center" width="5%" style="padding-left:10px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><bean:message key="label.common.sno" /></td>
                                    <td nowrap align="left" width="15%" style="padding-left:10px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><bean:message key="label.common.jobcardno" /> </td>
                                    <td nowrap align="left" width="15%" style="padding-left:10px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><bean:message key="label.common.Chassisno" /></td>
                                    <td nowrap align="center" width="10%" style="padding-left:10px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><bean:message key="label.common.tractorindate" /> </td>
                                    <td nowrap align="left" width="10%" style="padding-left:10px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><bean:message key="label.common.Jobcardpriority" /> </td>
                                    <td nowrap align="left" width="15%" style="padding-left:10px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><bean:message key="label.common.JobcardPayeeName" />  </td>
                                    <td nowrap align="left" width="10%" style="padding-left:10px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><bean:message key="label.common.contactno" /> </td>
                                    <td nowrap align="center" width="10%" style="padding-left:10px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><bean:message key="label.common.status" /> </td>
                               </tr>
                                <c:set var="sno" value="1"></c:set>
                                <c:forEach items="${jobcardDetails}" var="jobcardDetails">
                               <%-- <logic:iterate id="jobcardDetails" name="jobcardDetails">
                                    <pg:item>--%>
                                        <tr bgcolor="#FFFFFF" height="20">
                                            <td nowrap align="center"  style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;">${sno}</td>
                                            <td nowrap align="left"  style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;">
                                                <%--<a href="<%=cntxpath%>/serviceProcess.do?option=viewJobcard&vinNo=${jobcardDetails.vinNo}&jobCardNo=${jobcardDetails.jobCardNo}&flag=ReOpen" >--%>
                                                 <a href="#" class="viewJobcardReOpen" data-vinNo="${jobcardDetails.vinNo}">
                                                <span id ="jobCardNo${sno}" >${jobcardDetails.jobCardNo}</span></a>
                                            </td>
                                            <td nowrap align="left"  style="padding-left:10px;"><span id="vinNo${sno}">${jobcardDetails.vinNo}</span></td>
                                            <td nowrap align="center"  style="padding-left:10px;"><span id="jobcardDate{sno}">${jobcardDetails.jobCardDate}</span></td>
                                            <td nowrap align="left"  style="padding-left:10px;"><span id="jobacrdpriority{sno}">${jobcardDetails.jobcardpriority}</span></td>
                                            <td nowrap align="left" style="padding-left:10px;"><span id="payeeName${sno}">${jobcardDetails.payeeName}</span></td>
                                            <td nowrap align="left"  style="padding-left:10px;"><span id="mobilePhone${sno}">${jobcardDetails.mobilePhone}</span><input type="hidden" id="jobid${sno}" value="${jobcardDetails.jobId}"></td>
                                            <td nowrap align="center"  style="padding-left:10px;" id="statusid${sno}">
                                                <c:if test="${jobcardDetails.status eq 'CLOSE'}">
                                                    <select name="jobCardStatus"  id="jobCardStatus${sno}" onchange="getStatus('${jobcardDetails.vinNo}','${jobcardDetails.jobCardNo}','${sno}')">
                                                                <option value='CLOSE' title='close' selected>CLOSE</option>
                                                                <option value='OPEN' title='open' >OPEN</option>
                                                                <option value='REJECT' title='reject'>REJECT</option>
                                                    </select>
                                                </c:if>
                                            </td>
                                        </tr>
                                    <%--</pg:item>--%>
                                    <c:set var="sno" value="${sno + 1 }" />
                                  </c:forEach>
                                <%--</logic:iterate>--%>
                                <%--<tr >
                                    <td colspan="8" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                            <pg:index>
                                            <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle"/></a></pg:first>&nbsp;
                                            <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle"/></a></pg:prev>&nbsp;
                                            <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                            <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle"/></a></pg:next>&nbsp;
                                            <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle"/></a></pg:last>
                                            </pg:index>
                                    </td>
                                </tr>--%>
                            </table>
                        <%--</pg:pager>--%>
                    </td>
                </tr>
            </c:if>
        </table></form>
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

