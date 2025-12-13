<%-- 
    Document   : v_userList_master
    Created on : Apr 8, 2014, 11:37:01 AM
    Author     : kundan.rastogi
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
        var objSpecExp = /['&\\@]/g;

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

    <%--if (str == 'add') {
        var elementArr = new Array('User Type', 'User Type Description');
        var strValue = null;
        var strObject = null;

            for (var i = 0; i < elementArr.length; i++)
            {
                strObject = document.getElementById(elementArr[i]);
                strValue = document.getElementById(elementArr[i]).value;
                if (checkValue(strObject, strValue) == false)
                    return false;
            }

            var name = TrimAll(document.getElementById("User Type").value);
            var desc = TrimAll(document.getElementById("User Type Description").value);
            var url = "<%=cntxpath%>/UserManagement.do?option=manageUser&type=" + str + "&name=" + name + "&description=" + desc + "&t=" + new Date().getTime();
        }
        else {--%>
                var url = "<%=cntxpath%>/UserManagement.do?option=manageUser&status=" + str + "&type=" + str + "&id=" + strVal + "&t=" + new Date().getTime();
    <%-- }--%>
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
                            if (str == '0') {
                                document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('1','" + row + "','" + strVal + "')\"  class=\"headingSpas\" style=\"color: blue;\" >Active</a>";
                            } else {
                                document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('0','" + row + "','" + strVal + "')\"  class=\"headingSpas\" style=\"color: blue;\" >Inactive</a>";
                            }
                        }
    <%--if (str == 'add' && res.split("@@")[0] == 'Success') {
        // submitForm();
        document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
        document.getElementById("User Type").value = '';
        document.getElementById("User Type Description").value = '';
    }--%>
                    }
                }
            };
            xmlHttp.open("GET", url, true);
            xmlHttp.send(null);
            return false;
        }
        function editRow(name, row, id, desc) {
            document.getElementById('compType' + row).innerHTML = "<input type=\"text\" name=\"name\" maxlength='15' class=\"headingSpas\" id=\"newName" + row + "\" style=\"width:140px\" value=\"" + name + "\"/>";
            document.getElementById('desc' + row).innerHTML = "<input type=\"text\" name=\"description\" maxlength='225' class=\"headingSpas\" id=\"newDesc" + row + "\" style=\"width:200px\" value=\"" + desc + "\"/>";
            document.getElementById('imageButton' + row).innerHTML = "<input type=button name=\"apply\" value=\"Apply\" class=\"headingSpas\" onclick=\"updateAction('edit', " + row + ",'" + id + "')\"/>";
        }

        function updateAction(str, row, id)
        {
            if (document.getElementById("newName" + row).value == "") {
                document.getElementById("newName" + row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Please enter value in User Type field";
                return false;
            }
            if (document.getElementById("newDesc" + row).value == "") {
                document.getElementById("newDesc" + row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Please Enter User Type Description";
                return false;
            }
            var objRegExp = /^(\s*)$/g;
            var objSpecExp = /['&\\@]/g;
            var chckValue = document.getElementById("newName" + row).value;
            chckValue = chckValue.replace(objRegExp, '');
            var temp = objSpecExp.exec(chckValue);
            if (temp)
            {
                document.getElementById("newName" + row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in User Type field";
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
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in User Type Description field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }

            var name = TrimAll(document.getElementById("newName" + row).value);
            var desc = TrimAll(document.getElementById("newDesc" + row).value);
            var url = "<%=cntxpath%>/UserManagement.do?option=manageUserType&id=" + id + "&name=" + name + "&description=" + desc + "&type=" + str + "&t=" + new Date().getTime();

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
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/UserManagement.do?option=initUserInformation'>USER MANAGEMENT</a></li>
            <li class="breadcrumb_link">MANAGE USER</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1>MANAGE USER </h1>


                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="UserManagement.do?option=initUser" method="post" styleId="searchBy" >
                                <table width=100%  border=0 cellspacing=0 cellpadding=0>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="20%" class="headingSpas" nowrap align="left">User Name</td>
                                        <td style="padding-left:10px" width="30%" align="left">
                                            <html:text property="nameSrch" styleClass="headingSpas" maxlength="15" style="width:170px"/>
                                        </td>
                                        <td style="padding-left:10px" width="30%" align="left" >
                                            <input type=button name="Go" value="Search" class="headingSpas" onClick = "submitForm()"/>
                                        </td>
                                        <td style="padding-left:10px" width="30%" align="left" >
                                            <B><a href='<%=cntxpath%>/UserManagement.do?option=addUser'>ADD NEW USER</a></B>
                                        </td>

                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${empty userList}">
                        <tr bgcolor="#FFFFFF">
                            <td valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                User not Found
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty userList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="UserManagement.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initUser"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td   align="center"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;" >S.No</td>
                                            <td   align="left"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 90px;"  nowrap>User ID</td>
                                            <td   align="left"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 100px;"  nowrap>User Type</td>
                                            <td   align="left"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 110px;"  nowrap>User Name</td>
                                            <%--<td   align="left" width="20" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Region</td>--%>
                                            <td   align="left"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 250px;"  nowrap>Address</td>
                                            <td   align="left"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 80px;"  nowrap>Contact No.</td>
                                            <td   align="center"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;" nowrap>Edit</td>
                                            <td   align="center"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 50px;" nowrap>Status</td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="userList" name="userList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="10" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td title="${userList.id}" align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                            ${userList.id}
                                                        </span>
                                                    </td>
                                                    <td title="${userList.userTypeDesc}" align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                            ${userList.userTypeDesc}
                                                        </span>
                                                    </td>
                                                    <td title="${userList.name}" align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                         <c:set var="userNameStr" value="${userList.name}"/>
                                                            <c:set var="userNameStr2" value="${fn:substring(userNameStr, 0, 15)}" />
                                                            ${userNameStr2}
                                                        </span>
                                                    </td>
                                                    <%--<td  title="${userList.regionName}" align="left" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                            ${userList.regionName}
                                                        </span>
                                                    </td>--%>

                                                    <td title="${userList.address1}" align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="desc${sno}" >
                                                         <%--   ${userList.designation}--%>

                                                             <c:set var="designStr" value=" ${userList.address1}"/>
                                                            <c:set var="designStr2" value="${fn:substring(designStr, 0, 19)}" />
                                                            ${designStr2}
                                                        </span>
                                                    </td>
                                                    <td title="${userList.mobile}" align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                            ${userList.mobile}
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <%--<img src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${userList.name}', ${sno}, '${userList.id}', '${userList.designation}')" title="Edit"/>--%>
                                                            <a href='<%=cntxpath%>/UserManagement.do?option=getUserData&user_id=${userList.id}'><img src="${pageContext.request.contextPath}/images/edit.gif"  title="Edit"/></a>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${userList.status}', ${sno}, '${userList.id}')"  class="headingSpas" style="color: blue;" >
                                                                <c:if test="${userList.status=='A'}">Active</c:if>
                                                                <c:if test="${userList.status=='I'}">Inactive</c:if>
                                                                <%--  ${userList.status}&nbsp;--%>
                                                            </a>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr >
                                            <td colspan="8" align="center" bgcolor="#FFFFFF" class="textPaging" >
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

                    <%--<tr bgcolor="#FFFFFF">
                        <td width=100% valign="top" >
                            <form action="#" >
                                <table width=100% border="0" cellpadding="0" cellspacing="0" >
                                    <tr  bgcolor="#eeeeee" height="20">
                                        <td  style="padding-left: 5px" align= left class="headingSpas">
                                            ADD NEW USER TYPE
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >

                                                <tr height="30">
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">User Type</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="name"  id="User Type" class="headingSpas" maxlength="15" style="width:170px" />
                                                    </td>

                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">User Type Description</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="description"  id="User Type Description" class="headingSpas" maxlength="225" style="width:170px" />
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
                    </tr>--%>
                </table>
            </div>
        </div>
    </center>
</div>




