<%-- 
    Document   : rejectionCodemaster
    Created on : Dec 3, 2014, 11:32:46 AM
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
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>
<script type="" language="JavaScript" src='${pageContext.request.contextPath}/js/validation.js'></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>

<script language="JavaScript" type="text/javascript">
    function submitForm() {
        document.getElementById('searchBy').submit();
    }
    function checkValue(strObject, strValue)
    {
//        var objRegExp = /^(\s*)$/g;
//        var objSpecExp = /['&\\@\!\~\`\#\$\%\(\)\=\{\}\[\]\|\:\;\<\>\,\.\?\+\"\^\*/]/g;
        var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
        var objSpecExpDes = /['#\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var ck_code = /^[a-zA-Z0-9]+$/;

        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);
        var tempD= objSpecExpDes.exec(strValue);
        var text = strObject.id;
        <%--if (strObject.id == 'aggCode') {
            text = 'Rejection Code';

        }
        if (strObject.id == 'aggDesc') {
            text = 'Rejection Code Description';
        }--%>
        if (strValue.length == 0)
        {

            strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Value in " + strObject.id + " Field";
            window.scrollTo(0, 0);//go to top of page
            return false;

        }
        else if (strObject.id == 'Rejection Code') {
            
            if (!ck_code.test(strValue)) {
                       strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
           document.getElementById("msg_saveFAILED").innerHTML = "Special Characters or Space are not allowed in " + strObject.id + " field";
            window.scrollTo(0, 0);//go to top of page
            return false;
                          }
        }
        else if (tempD)
        {
            if (strObject.tagName == "INPUT")
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + strObject.id + " field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }

            return false;

        }
    }
    function cUDAction(str, row, rejCode)
    {

        var url = null;

        if (str == 'add') {
            var elementArr = new Array('Rejection Code', 'Rejection Desc.');
            var strValue = null;
            var strObject = null;

            for (var i = 0; i < elementArr.length; i++)
            {
                strObject = document.getElementById(elementArr[i]);
                strValue = document.getElementById(elementArr[i]).value;
                if (checkValue(strObject, strValue) == false)
                    return false;
            }

            var rejCode = TrimAll(document.getElementById("Rejection Code").value);
            var rejDesc = TrimAll(document.getElementById("Rejection Desc.").value);
            var tempDes=replaceall(rejDesc,'&','`');
            url = "<%=cntxpath%>/masterAction.do?option=manageRejectionMaster&type=" + str + "&rejCode=" + rejCode + "&rejDesc=" + tempDes +"&t=" + new Date().getTime();
            document.getElementById("rejcodemaster").submit();
        }
        else {
            url = "<%=cntxpath%>/masterAction.do?option=manageRejectionMaster&status=" + str + "&type=" + str + "&rejCode=" + rejCode +"&t=" + new Date().getTime();

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
                {
                    res = trim(xmlHttp.responseText);
                    document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                    if (str != 'add' && res.split("@@")[0] == 'Success') {
                        if (str == 'Y') {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','" + row + "','" + rejCode + "')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                        } else {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','" + row + "','" + rejCode + "')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                    }
                    
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
       }
    }

    function editRow(RejCod, row,RejDesc){

        document.getElementById('RejDesc'+row).innerHTML="<input type=\"text\" name=\"rejDesc\"  maxlength=\"200\" class=\"headingSpas\" id=\"newRejDesc"+row+"\" style=\"width:220px\" value=\""+RejDesc+"\"/>";
        document.getElementById('imageButton'+row).innerHTML="<input type=button name=\"apply\" class=\"headingSpas\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+RejCod+"','"+RejDesc+"')\"/>";
    }

        function updateAction(str, row, RejCod,RejDesc)
        {
            var newRejCode='';
            var objRegExp = /^(\s*)$/g;
           // var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
            var objSpecExp = /['#\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
             var ck_code = /^[a-zA-Z0-9]+$/;

            if (document.getElementById("newRejDesc" + row).value == "") {
                document.getElementById("newRejDesc" + row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Rejection Code Description";
                return false;
            }
            var chckValue = document.getElementById("newRejDesc" + row).value;
            chckValue = chckValue.replace(objRegExp, '');
            var temp = objSpecExp.exec(chckValue);
            if (temp)
            {
                document.getElementById("newRejDesc" + row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in Rejection Code Description field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }

            //  var item_No = TrimAll(document.getElementById("newMechanin_Code"+row).value);
            var rejDesc = TrimAll(document.getElementById("newRejDesc" + row).value);
             var tempDes=replaceall(rejDesc,'&','`');

            var url = "<%=cntxpath%>/masterAction.do?option=manageRejectionMaster&id=" + RejCod + "&rejCode=" + RejCod + "&rejDesc=" + rejDesc + "&type=" + str +"&t=" + new Date().getTime();
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
                    {
                        res = trim(xmlHttp.responseText);
                        document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                        if (str != 'add' && res.split("@@")[0] == 'Success') {
                            document.getElementById('RejDesc' + row).innerHTML = "" + rejDesc + "";
                            document.getElementById('imageButton' + row).innerHTML = "<img src=\"images/edit.gif\" onclick=\"editRow('" + RejCod + "'," + row + ",'" + rejDesc + "')\" title=\"Edit\"/>";
                        }
                    }
                }
            };
            xmlHttp.open("GET", url, true);
            xmlHttp.send(null);
            return false;
        }
        function donotsubmit()
        {
            return false;
        }
</script>



<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href ='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
            <li class="breadcrumb_link">Manage Warranty Rejection Codes</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED">${successmsg}</div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText">Manage Warranty Rejection Codes</h1>


                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initRejectionCodeMaster" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="10%"  class="headingSpas" nowrap align="left">Rejection Description</td>
                                        <td style="padding-left:10px" width="10%">
                                            <html:text property="nameSrch" styleClass="headingSpas"  style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <td style="padding-left:10px" align="left" >
                                            <input type=button name="Go" class="headingSpas" value="Search" onClick = "submitForm()"/>
                                        </td>
                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${empty rejectionCodeList}">
                        <tr bgcolor="#FFFFFF">
                            <td valign="top" align="center" class="headingSpas" style="padding-top:10px;color: red">
                                Rejection Code Description not Found. !
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty rejectionCodeList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initRejectionCodeMaster"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td  nowrap align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>
                                            <td   align="left" class="headingSpas" width="150"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Rejection Code</td>
                                            <td   align="left" class="headingSpas"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Rejection Description</td>
                                            <td   align="center" class="headingSpas" width="75" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Edit</td>
                                            <td   align="center" class="headingSpas" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>isActive</td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="rejectionCodeList" name="rejectionCodeList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" title=" ${rejectionCodeList.rejCode}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="RejCode${sno}" >
                                                            ${rejectionCodeList.rejCode}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${rejectionCodeList.rejDesc}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="RejDesc${sno}" >
                                                            ${rejectionCodeList.rejDesc}
                                                        </span>
                                                    </td>

                                                    <td align="center" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img alt="" src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${rejectionCodeList.rejCode}', ${sno}, '${rejectionCodeList.rejDesc}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${rejectionCodeList.status}', ${sno}, '${rejectionCodeList.rejCode}')"  class="headingSpas" style="color: blue;" >
                                                                ${rejectionCodeList.status}&nbsp;
                                                            </a>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr >
                                            <td colspan="5" align="center" bgcolor="#FFFFFF" class="textPaging" >
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

                    <tr bgcolor="#FFFFFF">
                        <td width=100% valign="top" >
                            <form action="masterAction.do" id="rejcodemaster" name="rejcodemaster" method="post">
                                <input type="hidden" style="display: none" value="manageRejectionMaster" name="option" />
                                <input type="hidden"  value="add" name="type" />
                                <table width=100% border="0" cellpadding="0" cellspacing="0" >
                                    <tr bgcolor="#eeeeee" height="20">
                                        <td  style="padding-left: 5px" align= left class="headingSpas">
                                            <b>ADD NEW REJECTION CODE</b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >

                                                <tr height="30">
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Rejection Code</td>
                                                    <td style="padding-left:10px;padding-top: 5px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="rejCode"  id="Rejection Code" class="headingSpas"  maxlength="5" style="width:120px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>

                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Rejection Code Description</td>
                                                    <td style="padding-left:10px;padding-top: 5px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="rejDesc"  id="Rejection Desc." class="headingSpas" size="150"  maxlength="200" style="width:240px"  onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="20">
                                                    <td colspan="4" style="padding-left:10px;padding-bottom: 5px" align="center" bgcolor="#FFFFFF">
                                                        <input type=button name="add" value="Submit" class="headingSpas" onclick="cUDAction('add','','')"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </center>
</div>

