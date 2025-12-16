<%-- 
    Document   : v_region_master
    Created on : Feb 12, 2014, 4:19:25 PM
    Author     : vandana.singla
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
    function imposeMaxLength(obj) {
        var mlength = obj.getAttribute ? parseInt(obj.getAttribute("maxlength")) : ""
        if (obj.getAttribute && obj.value.length > mlength) {
            //obj.value = obj.value.substring(0, mlength)
            return false;
        }
    }
    function checkValue(strObject, strValue)
    {
        var objRegExp = /^(\s*)$/g;
        //var objSpecExp = /['&\\@\!\~\`\#\$\%\(\)\=\{\}\[\]\|\:\;\<\>\,\?\+\"\^\*/]/g;
         var objSpecExp = /['\\@\~^\#\$\%\!\*\+\=\:\;\|\?]/g;
        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);

        if (strValue.length == 0)
        {
            strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please enter value in " + strObject.id + " field";
            window.scrollTo(0, 0);//go to top of page
            return false;

        }
        else if (temp)
        {
            if (strObject.tagName == "INPUT")
            {
                //alert("Please Enter "+elementArr[i]);
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

        if (str == 'add') {
            var elementArr = new Array('Region Name', 'Address');
            var strValue = null;
            var strObject = null;

            for (var i = 0; i < elementArr.length; i++)
            {
                strObject = document.getElementById(elementArr[i]);
                strValue = document.getElementById(elementArr[i]).value;
                if (checkValue(strObject, strValue) == false)
                    return false;
            }

            var name = TrimAll(document.getElementById("Region Name").value);
            var desc = TrimAll(document.getElementById("Address").value);
            var numeric = /[0-9]/g;
            var temp = numeric.exec(name);
            if (temp) {
                document.getElementById("Region Name").focus();
                document.getElementById("msg_saveFAILED").innerHTML = "Numerics are not allowed in region name.";
                return false;
            }
            if (imposeMaxLength(document.getElementById("Address")) == false)
            {
                document.getElementById("Address").focus();
                document.getElementById("msg_saveFAILED").innerHTML = "Address field length should not be greater than 350 characters.";
                return false;
            }

            var url = "<%=cntxpath%>/UserManagement.do?option=manageRegion&type=" + str + "&name=" + name + "&description=" + desc + "&t=" + new Date().getTime();
        }
        else {
            var url = "<%=cntxpath%>/UserManagement.do?option=manageRegion&status=" + str + "&type=" + str + "&id=" + strVal + "&t=" + new Date().getTime();
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
                        if (str == 'Active') {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('Inactive','" + row + "','" + strVal + "')\"  class=\"headingSpas\" style=\"color: blue;\" >Inactive</a>";
                        } else {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('Active','" + row + "','" + strVal + "')\"  class=\"headingSpas\" style=\"color: blue;\" >Active</a>";
                        }
                    }
                    if (str == 'add' && res.split("@@")[0] == 'Success') {
                        //  submitForm();
                        document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                        document.getElementById("Region Name").value = '';
                        document.getElementById("Address").value = '';
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }
    function editRow(name, row, id, desc) {
        document.getElementById('compType' + row).innerHTML = "<input type=\"text\" name=\"name\" class=\"headingSpas\" maxlength='50' id=\"newName" + row + "\" style=\"width:140px\" value=\"" + name + "\"/>";
        document.getElementById('desc' + row).innerHTML = "<textarea name=\"description\" maxlength='350' class=\"headingSpas\" id=\"newDesc" + row + "\" style=\"width:200px\" value=\"" + desc + "\"/></textarea>";
        document.getElementById('imageButton' + row).innerHTML = "<input type=button name=\"apply\" class=\"headingSpas\" value=\"Apply\" onclick=\"updateAction('edit', " + row + ",'" + id + "')\"/>";
        document.getElementById('newDesc' + row).value = desc;
    }

    function updateAction(str, row, id)
    {
        if (document.getElementById("newName" + row).value == "") {
            document.getElementById("newName" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please enter value in Region field";
            return false;
        }
        var numeric = /[0-9]/g;
        var chckValue = document.getElementById("newName" + row).value;
        var temp = numeric.exec(chckValue);
        if (temp) {
            document.getElementById("newName" + row).focus();
            document.getElementById("msg_saveFAILED").innerHTML = "Numerics are not allowed in region name.";
            return false;
        }
        if (document.getElementById("newDesc" + row).value == "") {
            document.getElementById("newDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter value in Address field";
            return false;
        }
        if (imposeMaxLength(document.getElementById("newDesc" + row)) == false)
        {
            document.getElementById("newDesc" + row).focus();
            document.getElementById("msg_saveFAILED").innerHTML = "Address field length should not be greater than 350 characters.";
            return false;
        }
        var objRegExp = /^(\s*)$/g;
        //var objSpecExp = /['&\\@\!\~\`\#\$\%\(\)\=\{\}\[\]\|\:\;\<\>\,\.\?\+\"\^\*/]/g;
         // var objSpecExp = /['&\\@\~^\#\$\%\!\*]/g;
          var objSpecExp = /['\\@\~^\#\$\%\!\*\+\=\:\;\|\?]/g;
        var chckValue = document.getElementById("newName" + row).value;
        chckValue = chckValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(chckValue);
        if (temp)
        {
            document.getElementById("newName" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in Region field";
            window.scrollTo(0, 0);//go to top of page
            return false;
        }
        var chckValue = document.getElementById("newDesc" + row).value;
        chckValue = chckValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(chckValue);
        if (temp)
        {
            document.getElementById("newDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in Address field";
            window.scrollTo(0, 0);//go to top of page
            return false;
        }

        var name = TrimAll(document.getElementById("newName" + row).value);
        var desc = TrimAll(document.getElementById("newDesc" + row).value);
        var url = "<%=cntxpath%>/UserManagement.do?option=manageRegion&id=" + id + "&name=" + name + "&description=" + desc + "&type=" + str + "&t=" + new Date().getTime();

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
                        document.getElementById('compType' + row).innerHTML = "" + name + "";
                        document.getElementById('desc' + row).innerHTML = "" + desc + "";
                        document.getElementById('imageButton' + row).innerHTML = "<img src=\"images/edit.gif\" onclick=\"editRow('" + name + "'," + row + ",'" + id + "','" + desc + "')\" title=\"Edit\"/>";
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/UserManagement.do?option=initUserInformation'>User Management</a></li>
            <li class="breadcrumb_link">MANAGE REGION</li>

        </ul>
    </div>
    <br/>        

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1>MANAGE REGION</h1>

                <table cellspacing=1 cellpadding=5 border=0 width=100%>
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="UserManagement.do?option=initRegion" method="post" styleId="searchBy" >
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="20%" class="headingSpas" nowrap align="left">Region</td>
                                        <td style="padding-left:10px" width="30%" align="left">
                                            <html:text property="nameSrch" maxlength="50" styleClass="headingSpas"  style="width:170px"/>
                                        </td>
                                        <td style="padding-left:10px" width="50%" align="left" >
                                            <input type=button name="Go" value="Search" class="headingSpas" onClick = "submitForm()"/>
                                        </td>

                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${empty regionList}">
                        <tr bgcolor="#FFFFFF">
                            <td valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                Region not Found
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty regionList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="UserManagement.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initRegion"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td  nowrap align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>
                                            <td  nowrap align="left" width="100" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Region</td>
                                            <td  nowrap align="left" width="80" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Address</td>
                                            <td  nowrap align="center" width="20" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Edit</td>
                                            <td  nowrap align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Status</td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="regionList" name="regionList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" width="100" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                            ${regionList.name}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" width="100" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="desc${sno}" >
                                                            ${regionList.description}
                                                        </span>
                                                    </td>

                                                    <td align="center" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${regionList.name}', ${sno}, '${regionList.id}', '${regionList.description}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${regionList.status}', ${sno}, '${regionList.id}')"  class="headingSpas" style="color: blue;" >
                                                                ${regionList.status}&nbsp;
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
                            <form action="#" >
                                <table width=100% border="0" cellpadding="0" cellspacing="0" >
                                    <tr  bgcolor="#eeeeee" height="20">
                                        <td  style="padding-left: 5px;font-weight:bold;" align= left class="headingSpas">
                                            ADD NEW REGION
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >

                                                <tr style="height: 65px;padding-top: 10px">
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Region Name</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="name"  id="Region Name" maxlength="50" class="headingSpas"  style="width:170px" />
                                                    </td>
                                                    <%-- </tr>

                                    <tr height="20">--%>
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Address</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                                        <textarea name="description" rows="2" id="Address" maxlength="350" class="tabletextarea"></textarea>
                                                    </td>
                                                </tr>

                                                <tr height="20">
                                                    <td colspan="4" style="padding-left:10px;padding-bottom: 5px;" align="center" bgcolor="#FFFFFF">
                                                        <input type=button name="add" class="headingSpas" value="Submit" onclick="cUDAction('add')"/>
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






