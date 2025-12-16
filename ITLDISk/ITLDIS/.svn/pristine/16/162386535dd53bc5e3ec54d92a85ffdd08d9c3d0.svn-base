<%-- 
    Document   : v_vinValidation
    Created on : Sep 24, 2014, 4:30:30 PM
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
%>

<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
function editRow(vinNo,row,jobcardNo,dealerCode)
{
    document.getElementById('vinNo'+row).innerHTML="<input type=\"text\" name=\"name\" size='200' maxlength='200' class=\"headingSpas\" id=\"newName"+row+"\" style=\"width:220px\" value=\""+vinNo+"\"/>";
    document.getElementById('imageButton'+row).innerHTML="<input type=button name=\"apply\" class=\"headingSpas\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+vinNo+"','"+jobcardNo+"','"+dealerCode+"')\"/>";
}

function updateAction(type, row,oldvinNo,jobcardNo,dealerCode)
{
        if(document.getElementById("newName"+row).value==""){
            document.getElementById("newName"+row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            <%--document.getElementById("msg_saveFAILED").innerHTML=not_blank_validation_msg+"Chassis No.";--%>
            alert(not_blank_validation_msg+"Chassis No.")
            return false;
        }
        var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['\&\{\}\(\)\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var chckValue = document.getElementById("newName"+row).value;
        chckValue=chckValue.replace(objRegExp,'');
        var temp=objSpecExp.exec(chckValue);
        if(temp)
        {
            document.getElementById("newName"+row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            <%--document.getElementById("msg_saveFAILED").innerHTML=specialChar_validation_msg+"Chassis No.";--%>
       <%--window.scrollTo(0,0);//go to top of page--%>
           alert(specialChar_validation_msg+"Chassis No.");
            return false;
        }

     var vinno = TrimAll(document.getElementById("newName"+row).value);
     var url="<%=cntxpath%>/serviceProcess.do?option=validateVinNoNupdate&vinNo="+vinno+"&jobCardNo="+jobcardNo+"&oldvinNo="+oldvinNo+"&dealerCode="+dealerCode+"&t="+new Date().getTime();
     <%--alert(row+'--'+jobcardNo+'--'+type+'--'+url)--%>
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {
                if (xmlHttp.responseText.indexOf("Session has been expired") != -1)
                {
                    document.location.href = contextPath + "/login/SessionExpired.jsp";
                }
                else
                { res = trim(xmlHttp.responseText);
                    document.getElementById("msg_saveFAILED").innerHTML=res.split("@@")[1];
                    if(type != 'add' && res.split("@@")[0] =='Success'){
                        document.getElementById('vinNo'+row).innerHTML=""+vinno+"";
                        document.getElementById('imageButton'+row).innerHTML="<img src=\"images/edit.gif\" onclick=\"editRow('"+vinno+"',"+row+",'"+jobcardNo+"','"+dealerCode+"')\" title=\"Edit\"/>";
                    }
                }
            }
        };
        xmlHttp.open("GET",url, true);
        xmlHttp.send(null);
        return false;
}

            $(document).ready(function(){
                $('.viewJobcard').click(function(){
                    var url = "serviceProcess.do?option=viewJobcard&jobCardNo="+Base64.encode($(this).text().trim())+"&vinNo="+ Base64.encode($(this).attr('data-vinNo').trim())+"&flag="+Base64.encode("validateVinNo");
                    $(this).attr('href',url);
                });
            });
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link></li>
            <li class="breadcrumb_link"><bean:message key="label.common.pending4chassisvalidate" /></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED" align="center"></div>

    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.common.pending4chassisvalidate" /></h1>
                <form action="serviceProcess.do?option=initPending4validateVinNo" method="post" id="vinNoValidate" name="vinNoValidate">
                <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr bgcolor="#eeeeee" height="20">
                        <td nowrap align="center" width="5%" style="padding-left:3px;padding-right:3px;font-weight:bold;" >
                            <bean:message key="label.common.sno" />
                        </td>
                        <td nowrap align="left" width="18%" style="padding-left:3px;padding-right:3px;font-weight:bold;" >
                            <bean:message key="label.common.jobcardno" />
                        </td>
                        <td nowrap align="left" width="10%" style="padding-left:3px;padding-right:3px;font-weight:bold;" >
                            <bean:message key="label.common.jobcardDate" />
                        </td>
                        <td nowrap align="left" width="17%" style="padding-left:3px;padding-right:3px;font-weight:bold;" >
                            <bean:message key="label.common.Chassisno" />
                        </td>
                        <td nowrap align="left" width="15%" style="padding-left:3px;padding-right:3px;font-weight:bold;" >
                            <bean:message key="label.common.JobcardPayeeName" />
                        </td>
                        <%if (userFunctionalities.contains("101")) {%>
                        <td nowrap align="center" width="5%" style="padding-left:3px;padding-right:3px;font-weight:bold;" >
                            <bean:message key="label.common.action" />
                        </td>
                        <%} else {%>
                        <td nowrap align="left" width="15%" style="padding-left:3px;padding-right:3px;font-weight:bold;" >
                            <bean:message key="label.common.dealerName" />
                        </td>
                        <td nowrap align="left" width="15%" style="padding-left:3px;padding-right:3px;font-weight:bold;" >
                            <bean:message key="label.common.location" />
                        </td>
                        <%}%>
                    </tr>
                    <c:if test="${!empty vinList4Validate}">

                        <pg:pager url="serviceProcess.do" maxIndexPages="10" maxPageItems="15">
                            <pg:param name="option" value="initPending4validateVinNo"/>
                            <c:set var="sno" value="1"></c:set>
                            <logic:iterate id="vinList4Validate" name="vinList4Validate">
                                <pg:item>
                                    <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                                        <td nowrap align="center"  bgcolor="#FFFFFF" style="padding-left:3px;" >${sno}</td>
                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;">
                                            <a href="#" class="viewJobcard" data-vinNo="${vinList4Validate.vinNo}">
                                            <span id ="jobCardNo${sno}" >${vinList4Validate.jobCardNo}</span></a>
                                        </td>
                                        <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px"><span >${vinList4Validate.jobCardDate}</span>
                                        </td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;"><span id="vinNo${sno}">${vinList4Validate.vinNo}</span></td>
                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;"><span >${vinList4Validate.payeeName}</span></td>
                                        <%if (userFunctionalities.contains("101")) {%>
                                        <td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">
                                            <span id ="imageButton${sno}" >
                                                <img src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${vinList4Validate.vinNo}', ${sno},'${vinList4Validate.jobCardNo}','${vinList4Validate.dealercode}')" title="Edit"/>
                                            </span>
                                        </td>
                                        <%} else {%>
                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;"><span>${vinList4Validate.dealerName}[ ${vinList4Validate.dealercode} ]</span></td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;"><span>${vinList4Validate.locationName}</span></td>
                                        <%}%>
                                    </tr>
                                </pg:item>
                                <c:set var="sno" value="${sno + 1 }" />
                            </logic:iterate>
                            <tr>
                                <%if (userFunctionalities.contains("101")) {%>
                                <td colspan="6" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
                        </pg:pager></c:if>
                    <c:if test="${empty vinList4Validate}">
                        <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                            <%if (userFunctionalities.contains("101")) {%>
                            <td colspan="6" valign="bottom" align="center" style="padding-top:10px;color: red"> <bean:message key="label.common.noRecordFound" /></td>
                             <%} else {%>
                             <td colspan="7" valign="bottom" align="center" style="padding-top:10px;color: red"> <bean:message key="label.common.noRecordFound" /></td>
                             <%}%>
                        </tr>
                    </c:if>
                </table>
              </form>
            </div>
        </div>
    </center>
</div>
