<%-- 
    Document   : v_actionMaster
    Created on : Apr 18, 2014, 10:24:57 AM
    Author     : Avinash.Pandey
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
        var objRegExp = /^(\s*)$/g;
       // var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
        var objSpecExp = /['#\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var ck_code = /^[a-zA-Z0-9]+$/;

        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);

        if (strValue.length == 0)
        {
            strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            if (strObject.tagName == "INPUT")
            {
                document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Value in " + strObject.id + " Field";
            } else {
                document.getElementById("msg_saveFAILED").innerHTML = "Please Select Value in " + strObject.id + " Field";
            }
            window.scrollTo(0, 0);//go to top of page
            return false;

        }
        else if (strObject.id == 'Action Code') {
            text = 'Action Code';
            if (!ck_code.test(strValue)) {
                       strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
           document.getElementById("msg_saveFAILED").innerHTML = "Special Characters or Space are not allowed in " + text + " Field";
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
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + strObject.id + " Field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }
            return false;
        }
        else if (strObject.id == 'Service Hrs') {

            {
                if (validfloatnumber(strObject) == false)
                {
                    strObject.focus();
                    return false;
                }
            }
        }
    }

    function cUDAction(str, row, actionCode)
    {

        var url = null;

        if (str == 'add') {
            var elementArr = new Array('Action Code', 'Action Description', 'Defect Code','Service Hrs');
            var strValue = null;
            var strObject = null;

            for (var i = 0; i < elementArr.length; i++)
            {
                strObject = document.getElementById(elementArr[i]);
                strValue = document.getElementById(elementArr[i]).value;
                if (checkValue(strObject, strValue) == false)
                {
                    return false;
                }
            }

            var actionCode = TrimAll(document.getElementById("Action Code").value);
            var compCode = TrimAll(document.getElementById("Defect Code").value);
            var actionDesc = TrimAll(document.getElementById("Action Description").value);
              var tempDes=replaceall(actionDesc,'&','`');
            var serviceHrs = TrimAll(document.getElementById("Service Hrs").value);
            url = "<%=cntxpath%>/masterAction.do?option=manageAction&complaintCode=" + compCode + "&actionCode=" + actionCode + "&type=" + str + "&actionDesc=" + tempDes + "&serviceHrs=" + serviceHrs +"&t=" + new Date().getTime();
        }
        else {
            url = "<%=cntxpath%>/masterAction.do?option=manageAction&status=" + str + "&type=" + str + "&actionCode=" + actionCode + "&t=" + new Date().getTime();
        }
        //alert(url);
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
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','" + row + "','" + actionCode + "')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                        } else {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','" + row + "','" + actionCode + "')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                    }
                    if (str == 'add' && res.split("@@")[0] == 'Success') {
                        //submitForm();
                        document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                        document.getElementById("Action Code").value = '';
                        document.getElementById("Defect Code").value = '';
                        document.getElementById("Action Description").value = '';
                        document.getElementById("Service Hrs").value = '';
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }
    function editRow(actionCode, row, actionDesc, serviceHrs,compCodeId) {

        document.getElementById('desc' + row).innerHTML = "<input type=\"text\" name=\"actionDesc\" size='200' maxlength='200' class=\"headingSpas\" id=\"newActionDesc" + row + "\" style=\"width:200px\" value=\"" + actionDesc + "\"/>";
        document.getElementById('icompCode' + row).innerHTML = "<select name='name' class=\"headingSpas\" id='newName" + row + "' style='width:220px'><option value='' >--Select Here--</option><c:forEach items='${complaintCodeList}'  var='labelValue'  varStatus='status'><option value='${labelValue.value}@@${labelValue.label}' title='${labelValue.value} [ ${labelValue.label} ]'>${labelValue.value} [ ${labelValue.label} ]</option></c:forEach></select>";
        document.getElementById('iserviceHrs' + row).innerHTML = "<input type=\"text\" name=\"serviceHrs\" maxlength='9' class=\"headingSpas\" id=\"newServiceHrs" + row + "\" style=\"width:100px\" value=\"" + serviceHrs + "\"/>";
        document.getElementById('imageButton' + row).innerHTML = "<input type=button name=\"apply\" class=\"headingSpas\" value=\"Apply\" onclick=\"updateAction('edit', " + row + ",'" + actionCode + "')\"/>";
        document.getElementById('newName' + row).value = compCodeId;
    }
    
    function updateAction(str, row, actionCode)
    {
        if (document.getElementById("newActionDesc" + row).value == "")
        {
            document.getElementById("newActionDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Action Description";
            return false;
        }
        var objRegExp = /^(\s*)$/g;
      //  var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
         var objSpecExp = /['#\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var chckValue = document.getElementById("newActionDesc" + row).value;
        chckValue = chckValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(chckValue);
        if (temp)
        {
            document.getElementById("newActionDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in Action Description Field";
            window.scrollTo(0, 0);//go to top of page
            return false;
        }
        if (document.getElementById("newName" + row).value == "")
        {
            document.getElementById("newName" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Select Defect Code";
            return false;
        }
        var newServiceHrs = document.getElementById("newServiceHrs" + row);
        var serviceHrs = newServiceHrs.value;
        if (validfloat(newServiceHrs) == false)
        {
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Only decimal value in Service Hrs Field.";
            newPrice.value = "";
            newPrice.focus();
            return false;
        }
        var actionDesc = TrimAll(document.getElementById("newActionDesc" + row).value);
        var tempDes=replaceall(actionDesc,'&','`');
        var id = TrimAll(document.getElementById("newName" + row).value);
        var url = "<%=cntxpath%>/masterAction.do?option=manageAction&actionCode=" + actionCode + "&actionDesc=" + tempDes + "&complaintCode=" + id + "&serviceHrs=" + serviceHrs+"&type=" + str + "&t=" + new Date().getTime();

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
                       
                        //document.getElementById('iactionCode' + row).innerHTML = "" + actionCode + "";
                        document.getElementById('desc' + row).innerHTML = "" + actionDesc + "";
                        document.getElementById('icompCode' + row).innerHTML = "" + id.split("@@")[0] + " [" +id.split("@@")[1]+"]";
                        document.getElementById('iserviceHrs' + row).innerHTML = "" + serviceHrs + "";
                        document.getElementById('imageButton' + row).innerHTML = "<img src=\"images/edit.gif\" onclick=\"editRow('" + actionCode + "'," + row + ",'" + actionDesc + "',"+parseFloat(serviceHrs)+",'" + id + "')\" title=\"Edit\"/>";
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }
    
    
        
    function validfloatnumber(obj) {
        var regExp = /^[0-9]*(\.[0-9]+)?$/g
        if (regExp.test(obj.value)) {
            return true;
        } else {
            document.getElementById("msg_saveFAILED").innerHTML ="Please Enter Only Numeric Value in " + obj.id + " Field.";
            obj.value = "";
            obj.focus();
            return false;
        }
    }
    function validfloat(obj) {
        var regExp = /^[0-9]*(\.[0-9]+)?$/g
        if (regExp.test(obj.value)) {
            return true;
        } else {
            return false;
        }
    }
    function donotsubmit()
    {
        return false;
    }
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
            <li class="breadcrumb_link">MANAGE ACTION</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1>MANAGE ACTION</h1>

                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initManageAction" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 >
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="20%" class="headingSpas" nowrap align="left">Action Description</td>
                                        <td style="padding-left:10px" width="30%" align="left">
                                            <html:text property="nameSrch" styleClass="headingSpas"  style="width:170px"/>
                                        </td>
                                        <td style="padding-left:10px" width="50%" align="left" >
                                            <input type=button name="Go" class="headingSpas" value="Search" onClick = "submitForm()"/>
                                        </td>
                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${empty actionList}">
                        <tr bgcolor="#FFFFFF">
                            <td valign="top" align="center" class="headingSpas" style="padding-top:10px;color: red">
                                Action Description not Found. !
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty actionList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initManageAction"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td  nowrap align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>
                                            <td   align="left" width="80" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Action Code</td>
                                            <td   align="left" width="120" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Action Description</td>
                                            <td   align="left" width="120" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Defect Code</td>
                                            <td   align="left" width="80" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Service Hrs</td>
                                            <td   align="center" width="75" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Edit</td>
                                            <td   align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>isActive</td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="actionList" name="actionList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${actionList.actionCode}" width="80" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="iactionCode${sno}" >
                                                            ${actionList.actionCode}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF"  title="${actionList.actionDesc}" width="120" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="desc${sno}" >
                                                            ${actionList.actionDesc}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${actionList.complaintCode}[${actionList.compDesc}]" width="120" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="icompCode${sno}" >
                                                            ${actionList.complaintCode}[${actionList.compDesc}]
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" width="80" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="iserviceHrs${sno}" >
                                                            ${actionList.serviceHrs}
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${actionList.actionCode}',${sno}, '${actionList.actionDesc}','${actionList.serviceHrs}','${actionList.complaintCode}@@${actionList.compDesc}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${actionList.status}', ${sno}, '${actionList.actionCode}')"  class="headingSpas" style="color: blue;" >
                                                                ${actionList.status}&nbsp;
                                                            </a>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr >
                                            <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
                        <td width=100% valign="top"  >
                            <form action="#" >
                                <table width=100% border="0" cellpadding="0" cellspacing="0" >
                                    <tr height="20" bgcolor="#eeeeee">
                                        <td align= left style="padding-left: 5px" class="headingSpas">
                                            <b>ADD NEW ACTION</b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >

                                                <tr height="30">
                                                    <td  style="padding-left:10px" width="20%" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Action Code</td>
                                                    <td style="padding-left:10px;padding-top:5px" width="80%" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="actionCode"  id="Action Code" class="headingSpas"  maxlength="5" style="width:100px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="30">
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Action Description</td>
                                                    <td style="padding-left:10px;padding-top:5px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="actionDesc"  id="Action Description" class="headingSpas" size="200"  maxlength="200" style="width:220px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="30">
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Select Defect Code</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <select name='complaintCode' id='Defect Code' class="headingSpas" style='width:260px !important'>
                                                            <option value='' >--Select Here--</option>
                                                            <c:forEach items='${complaintCodeList}'  var='labelValue'  varStatus='status'>
                                                                <option value='${labelValue.value}' title='${labelValue.value} [ ${labelValue.label} ]'>${labelValue.value} [ ${labelValue.label} ]</option>
                                                            </c:forEach></select>
                                                    </td>
                                                </tr>
                                                <tr height="30">
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Service Hrs</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="serviceHrs"  id="Service Hrs" class="headingSpas"  maxlength="9" style="width:100px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="20">
                                                    <td colspan="7" style="padding-left:10px;padding-bottom: 5px;" align="center" bgcolor="#FFFFFF">
                                                        <input type=button class="headingSpas" name="add" value="Submit" onclick="cUDAction('add')"/>
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
