<%-- 
    Document   : v_labourCodeMaster
    Created on : May 13, 2014, 3:47:13 PM
    Author     : megha.pandya
--%>

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

<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>

<script language="JavaScript" type="text/javascript">
    function submitForm() {
        document.getElementById('searchBy').submit();
    }
    function checkValue(strObject, strValue)
    {
       var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
        var ck_code = /^[a-zA-Z0-9]+$/;
        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);

        if (strValue.length == 0)
        {
            strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            if (strObject.tagName == "INPUT")
            {
                document.getElementById("msg_saveFAILED").innerHTML = "Please enter value in " + strObject.id + " field";
            } else {
                document.getElementById("msg_saveFAILED").innerHTML = "Please select value in " + strObject.id + " field";
            }
            window.scrollTo(0, 0);//go to top of page
            return false;

        }
        else if (strObject.id == 'Labour Code') {
            text = 'Labour Code';
            if (!ck_code.test(strValue)) {
                       strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
           document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + text + " field";
            window.scrollTo(0, 0);//go to top of page
            return false;
                          }
        }
        else if (temp)
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
    
    function cUDAction(str, row, strVal)
    {

        var url = null;

        if (str == 'add') {
            var elementArr = new Array('Labour Code','Labour Description','Labour Hrs','Complaint Code');
            var strValue = null;
            var strObject = null;

            for (var i = 0; i < elementArr.length; i++)
            {
                strObject = document.getElementById(elementArr[i]);
                strValue = document.getElementById(elementArr[i]).value;
                if (checkValue(strObject, strValue) == false)
                    return false;
            }

            var labourCode = TrimAll(document.getElementById("Labour Code").value);
            var labourDesc = TrimAll(document.getElementById("Labour Description").value);
            var labourHrs = TrimAll(document.getElementById("Labour Hrs").value);
            var compCode = TrimAll(document.getElementById("Complaint Code").value);
            url = "<%=cntxpath%>/masterAction.do?option=manageLaborCode&type=" + str + "&labourCode=" + labourCode + "&labourDesc=" + labourDesc + "&compCode=" + compCode+ "&labourhrs=" + labourHrs  +"&t=" + new Date().getTime();
        }
        else {
            url = "<%=cntxpath%>/masterAction.do?option=manageLaborCode&status=" + str + "&type=" + str + "&labourCode=" + strVal + "&t=" + new Date().getTime();
        }
//        alert(url);
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
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','" + row + "','" + strVal + "')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                        } else {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','" + row + "','" + strVal + "')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                    }
                    if (str == 'add' && res.split("@@")[0] == 'Success') {
                        document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                        document.getElementById("Labour Code").value='';
                        document.getElementById("Labour Description").value='';
                        document.getElementById("Complaint Code").value='';
                        // setTimeout(function() { submitForm(); }, 3000);
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }

    function editRow(labourCode, row,labourDesc,compCodeid,labourId,labourhrs){
        //alert(compId);
        var url = "<%=cntxpath%>/masterAction.do?option=manageLaborCode&type=check&labourCode="+labourCode+"&t="+new Date().getTime();
        var flag = false;
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {
                if (xmlHttp.responseText.indexOf("Session has been expired") != -1)
                {
                    document.location.href = "${contextPath}/login/SessionExpired.jsp";
                }
                else
                {
                    res = trim(xmlHttp.responseText);
                    if(res == 'notexist'){
                        document.getElementById('labourCode'+row).innerHTML="<input type=\"text\" name=\"labourCode\" maxlength='5' class=\"headingSpas\" id=\"newlabourCode"+row+"\" style=\"width:80px\" value=\""+labourCode+"\"/>";
                        document.getElementById('labourDesc'+row).innerHTML="<input type=\"text\" name=\"labourDesc\" maxlength='200' class=\"headingSpas\" id=\"newlabourDesc"+row+"\" style=\"width:200px\" value=\""+labourDesc+"\"/>";
//                        document.getElementById('labourhrs'+row).innerHTML="<input type=\"text\" name=\"labourhrs\" maxlength='200' class=\"headingSpas\" id=\"newlabourhrs"+row+"\" style=\"width:200px\" value=\""+labourhrs+"\"/>";
                        flag = true;
                    }else{
                        document.getElementById('labourDesc'+row).innerHTML="<input type=\"text\" name=\"labourDesc\" maxlength='200' class=\"headingSpas\" id=\"newlabourDesc"+row+"\" style=\"width:200px\" value=\""+labourDesc+"\"/>";
                    }
                }
            }
            
        };
        xmlHttp.open("GET", url, false);
        xmlHttp.send(null);
        document.getElementById('icompCode' + row).innerHTML = "<select name='name' class=\"headingSpas\" id='newName" + row + "' style='width:220px'><option value='' >--Select Here--</option><c:forEach items='${compCodeList}'  var='labelValue'  varStatus='status'><option value='${labelValue.value}@@${labelValue.label}' title='${labelValue.value} [ ${labelValue.label} ]'>${labelValue.value} [ ${labelValue.label} ]</option></c:forEach></select>";
        document.getElementById('labourHrs'+row).innerHTML="<input type=\"text\" name=\"labourDesc\" maxlength='5' class=\"headingSpas\" id=\"newlabourHrs"+row+"\" style=\"width:40px\" value=\""+labourhrs+"\"/>";
        document.getElementById('newName'+row).value = compCodeid;
        document.getElementById('imageButton'+row).innerHTML="<input type=button class=\"headingSpas\" name=\"apply\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+labourCode+"','"+flag+"','"+labourDesc+"','"+labourId+"','"+labourhrs+"')\"/>";

    }
    function updateAction(str, row, oldlabourCode,flag,labourDesc,labourId,labourHrs)
    {
        var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
        var ck_code = /^[a-zA-Z0-9]+$/;
        var newlabourCode='';
        if(flag == 'true'){
            if(document.getElementById("newlabourCode"+row).value==""){
                document.getElementById("newlabourCode"+row).focus();
                document.getElementById("msg_save+FAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML="Please enter value in Labour Code field";
                return false;
            }
            var chckValue = document.getElementById("newlabourCode"+row).value;
//            chckValue=chckValue.replace(objRegExp,'');
//            var temp=objSpecExp.exec(chckValue);
//            if(temp)
//            {
//                document.getElementById("newlabourCode"+row).focus();
//                document.getElementById("msg_saveFAILED").style.display = "";
//                document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in Labour Code field";
//                window.scrollTo(0,0);//go to top of page
//                return false;
//            }
         text = 'Labour Code';
               if (!ck_code.test(chckValue)) {
               
                document.getElementById("newCausalCode"+row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + text + " field";
                window.scrollTo(0, 0);//go to top of page
               return false;
                        
              }
            newlabourCode = TrimAll(document.getElementById("newlabourCode" + row).value);
        }
        if (document.getElementById("newlabourDesc" + row).value == "") {
            document.getElementById("newlabourDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Labour Description";
            return false;
        }
            
        var chckValue = document.getElementById("newlabourDesc" + row).value;
        chckValue = chckValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(chckValue);
        if (temp)
        {
            document.getElementById("newlabourDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in Labour Description field";
            window.scrollTo(0, 0);//go to top of page
            return false;
        }
        var labourDesc = TrimAll(document.getElementById("newlabourDesc" + row).value);
        
        //labour hrs
        if (document.getElementById("newlabourHrs" + row).value == "") {
            document.getElementById("newlabourHrs" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Labour Hour";
            return false;
        }
            
        var chckValue = document.getElementById("newlabourHrs" + row).value;
        chckValue = chckValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(chckValue);
        if (temp)
        {
            document.getElementById("newlabourHrs" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in Labour Description field";
            window.scrollTo(0, 0);//go to top of page
            return false;
        }
        var labourhrs = TrimAll(document.getElementById("newlabourHrs" + row).value);
        
        if(document.getElementById("newName"+row).value==""){
            document.getElementById("newName"+row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Please Select Labour Code";
            return false;
        }
        var compCode = TrimAll(document.getElementById("newName"+row).value);
        
        var url = "<%=cntxpath%>/masterAction.do?option=manageLaborCode&id=" + oldlabourCode + "&labourCode=" + newlabourCode + "&labourDesc=" + labourDesc + "&labourhrs=" + labourhrs + "&compCode=" + compCode + "&primary_id=" + labourId + "&type=" + str +"&t=" + new Date().getTime();
//        alert(''+url);
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
                        if(flag == 'true'){
                            document.getElementById('compCode'+row).innerHTML=""+newlabourCode+"";
                        }else{
                            document.getElementById('labourCode'+row).innerHTML=""+oldlabourCode+"";
                        }
                        document.getElementById('labourDesc' + row).innerHTML = "" + labourDesc + "";
                        document.getElementById('icompCode' + row).innerHTML = "" + compCode.split("@@")[0] + " [" +compCode.split("@@")[1]+"]";
                        document.getElementById('labourHrs' + row).innerHTML = "" + labourhrs+"";
                        document.getElementById('imageButton' + row).innerHTML = "<img src=\"images/edit.gif\" onclick=\"editRow('" + oldlabourCode + "'," + row + ",'" + labourDesc + "','"+compCode+"','"+labourId+"')\" title=\"Edit\"/>";
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
        <ul>
            <li class="breadcrumb_link"><a href ='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
            <li class="breadcrumb_link">MANAGE LABOUR CODE</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 80%">
                <h1>MANAGE LABOUR CODE </h1>


                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initLabourCode" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="left">Labour Code</td>
                                        <td style="padding-left:10px" width="140px">
                                            <html:text property="nameSrch" styleClass="headingSpas" maxlength="5" style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                        </td>
<!--                                        <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Defect Code</td>
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                            <select name='compcodefilter' id='Complaint Code' class="headingSpas" style='width:260px'>
                                                <option value='' >--Select Here--</option>
                                                <c:forEach items='${compCodeList}'  var='labelValue'  varStatus='status'>
                                                    <option value='${labelValue.value}' title='${labelValue.label}'>${labelValue.value} [${labelValue.label}]</option>
                                                </c:forEach></select>
                                        </td>-->
                                        <td style="padding-left:10px" align="left" >
                                            <input type=button name="Go" class="headingSpas" value="Search" onClick = "submitForm()"/>
                                        </td>
                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${empty labourCodeList}">
                        <tr bgcolor="#FFFFFF">
                            <td valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                Labour Code not Found
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty labourCodeList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initLabourCode"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td  nowrap align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>
                                            <td   align="left" width="80" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>labour&nbsp;Code</td>
                                            <td   align="left" width="120" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Labour&nbsp;Description</td>
                                            <td   align="left" width="220" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Defect Code</td>
                                            <td   align="center" width="20" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Hrs</td>
                                            <td   align="center" width="20" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Edit</td>
                                            <td   align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>isActive</td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="labourCodeList" name="labourCodeList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20" >
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" title=" ${labourCodeList.labourCode}" width="80" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="labourCode${sno}" >
                                                            ${labourCodeList.labourCode}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${labourCodeList.labourDesc}" width="120" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="labourDesc${sno}" >
                                                            ${labourCodeList.labourDesc}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" width="220" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <input type="hidden" name="compCode" title="${labourCodeList.compCode}[${labourCodeList.compDesc}]" id="icompCode" value="${labourCodeList.compCode}@@${labourCodeList.compDesc}"/>
                                                        <span id ="icompCode${sno}" >
                                                            ${labourCodeList.compCode}[${labourCodeList.compDesc}]
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" title="${labourCodeList.labourhrs}" maxlength='5' width="40" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="labourHrs${sno}" >
                                                            ${labourCodeList.labourhrs}
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img alt="" src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${labourCodeList.labourCode}', ${sno}, '${labourCodeList.labourDesc}','${labourCodeList.compCode}@@${labourCodeList.compDesc}','${labourCodeList.primary_id}','${labourCodeList.labourhrs}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${labourCodeList.status}', ${sno}, '${labourCodeList.labourCode}')"  class="headingSpas" style="color: blue;" >
                                                                ${labourCodeList.status}&nbsp;
                                                            </a>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr >
                                            <td colspan="6" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
                            <form action="#" >
                                <table width=100% border="0" cellpadding="0" cellspacing="0" >
                                    <tr bgcolor="#eeeeee" height="20">
                                        <td  style="padding-left: 5px" align= left class="headingSpas">
                                            <b>ADD NEW LABOUR CODE</b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >

                                                <tr height="30">
                                                    <td  style="padding-left:10px" width="25%" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Labour Code</td>
                                                    <td style="padding-left:10px" width="75%" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="labourCode"  id="Labour Code" class="headingSpas"  maxlength="5" style="width:120px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="30">

                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Labour Description</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="labourDesc"  id="Labour Description" class="headingSpas"  maxlength="200" style="width:240px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="30">
                                                    <td  style="padding-left:10px" width="25%" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Labour Hour</td>
                                                    <td style="padding-left:10px" width="75%" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="labourHrs"  id="Labour Hrs" class="headingSpas"  maxlength="5" style="width:40px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="30">
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Select Defect Code</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <select name='name' id='Complaint Code' class="headingSpas" style='width:260px'>
                                                            <option value='' >--Select Here--</option>
                                                            <c:forEach items='${compCodeList}'  var='labelValue'  varStatus='status'>
                                                                <option value='${labelValue.value}' title='${labelValue.label}'>${labelValue.value} [${labelValue.label}]</option>
                                                            </c:forEach></select>
                                                    </td>
                                                </tr>
                                                <tr height="20">
                                                    <td colspan="6" style="padding-left:10px;padding-bottom: 5px" align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                </tr>
                                                <tr height="20">
                                                    <td colspan="6" style="padding-left:10px;padding-bottom: 5px" align="center" bgcolor="#FFFFFF">
                                                        <input type=button name="add" value="Submit" class="headingSpas" onclick="cUDAction('add')"/>
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
