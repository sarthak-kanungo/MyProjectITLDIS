<%--
    Document   : v_jobLocation
    Created on : Apr 30, 2014, 12:25:09 AM
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
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
<c:set var="param" value='<%=request.getParameter("param")%>'/>
<script language="JavaScript" type="text/javascript">
    function submitForm(){
        document.getElementById('searchBy').submit();
    }
    function checkValue(strObject,strValue)
    {
         var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;

        strValue=strValue.replace(objRegExp,'');
        var temp=objSpecExp.exec(strValue);

        if(strValue.length == 0)
        {
            strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Please enter value in "+strObject.id+" Description field";
            window.scrollTo(0,0);//go to top of page
            return false;

        }
        else if(temp)
        {
            if(strObject.tagName=="INPUT")
            {
                //alert("Please Enter "+elementArr[i]);
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in "+strObject.id+" Description field";
                window.scrollTo(0,0);//go to top of page
                return false;
            }

            return false;

        }
    }
    function cUDAction(str, row,strVal,locationDesc)
    {

        if(str == 'add'){
            var elementArr=new Array('Location Desc');
            var strValue=null;
            var strObject=null;

            for(var i=0;i<elementArr.length;i++)
            {
                strObject=document.getElementById(elementArr[i]);
                strValue =document.getElementById(elementArr[i]).value;
                if(checkValue(strObject, strValue)==false)
                {
                    return false;
                }
            }
            var name = TrimAll(document.getElementById("Location Desc").value);
            var url="<%=cntxpath%>/masterAction.do?option=manageJobLocation&type="+str+"&name="+name+"&t="+new Date().getTime()+"&locationDesc="+locationDesc;
        }
        else{
            var url="<%=cntxpath%>/masterAction.do?option=manageJobLocation&status="+str+"&name="+name+"&type="+str+"&id="+strVal+"&t="+new Date().getTime();
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
                    document.getElementById("msg_saveFAILED").innerHTML=res.split("@@")[1];
                    if(str != 'add' && res.split("@@")[0] =='Success'){
                        if(str=='Y'){
                            document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','"+row+"','"+strVal+"','"+locationDesc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                        }
                        else if(str=='N'){
                            document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','"+row+"','"+strVal+"','"+locationDesc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                    }
                    if(str=='Y' && res.split("@@")[0] =='LocationExist' ){
                        document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','"+row+"','"+strVal+"','"+locationDesc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                    }
                    if(str == 'add' && res.split("@@")[0] =='Success'){
                        //submitForm();
                        document.getElementById("msg_saveFAILED").innerHTML=res.split("@@")[1];
                        document.getElementById("Location Desc").value='';
                    }
                } }
        };
        xmlHttp.open("GET",url, true);
        xmlHttp.send(null);
        return false;
    }
    function editRow(name, row,id){
        document.getElementById('compType'+row).innerHTML="<input type=\"text\" name=\"name\" maxlength='200' class=\"headingSpas\" onkeypress=\"return CommentsMaxLength(this, 200);\" id=\"newName"+row+"\" style=\"width:220px\" value=\""+name+"\"/>";
        document.getElementById('imageButton'+row).innerHTML="<input type=button name=\"apply\" class=\"headingSpas\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+id+"')\"/>";
    }

    function updateAction(str, row,id)
    {
        if(document.getElementById("newName"+row).value==""){
            document.getElementById("newName"+row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Please enter value in Location Description field";
            return false;
        }

         var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var chckValue = document.getElementById("newName"+row).value;
        chckValue=chckValue.replace(objRegExp,'');
        var temp=objSpecExp.exec(chckValue);
        if(temp)
        {
            document.getElementById("newName"+row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in Location Description field";
            window.scrollTo(0,0);//go to top of page
            return false;
        }

        var name = TrimAll(document.getElementById("newName"+row).value).replace('&','@@');
        var url="<%=cntxpath%>/masterAction.do?option=manageJobLocation&id="+id+"&name="+name+"&type="+str+"&t="+new Date().getTime();

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
                    if(str != 'add' && res.split("@@")[0] =='Success'){
                        document.getElementById('compType'+row).innerHTML=""+name.replace('@@','&')+"";
                        document.getElementById('imageButton'+row).innerHTML="<img src=\"images/edit.gif\" onclick=\"editRow('"+name+"',"+row+",'"+id+"')\" title=\"Edit\"/>";
                    }
                } }
        };
        xmlHttp.open("GET",url, true);
        xmlHttp.send(null);
        return false;
    }
    function donotsubmit()
    {
        return false;
    }
    function validate()
    {
        var strObject=document.getElementById("Location Desc");
        var strValue =document.getElementById("Location Desc").value;
        if(checkValue(strObject,strValue)==false)
        {
           return false;
        }
        document.getElementById('addJobLocation').submit();
    }
</script>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a>  JOB LOCATION</li>


        </ul>
    </div>

    <div class="message" id="msg_saveFAILED">${successmsg}</div>
    <center>

        <div class="content_right1">

            <div class="con_slidediv1" style="position: relative;width: 100%">

                <h1>JOB LOCATION</h1>

                <table cellspacing=1 cellpadding=5 border=0 width=100%>

                    <%-- <tr height=25 class=grey>
                         <td align= center class="heading">
                             MANAGE CUSTOMER TYPE
                         </td>
                     </tr>--%>
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initJobLocation&param=search" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="20%" class="headingSpas" nowrap align="left">Location Description</td>
                                        <td style="padding-left:10px" width="30%">
                                            <html:text property="nameSrch" styleId="nameSrch" styleClass="headingSpas"  style="width:170px"/>
                                        </td>
                                        <td style="padding-left:10px" align="left" >
                                            <input type=button name="Go" value="Search" class="headingSpas" onClick = "submitForm()"/>
                                        </td>

                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${!empty JobLocationList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="15">
                                    <pg:param name="option" value="initJobLocation"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr  bgcolor="#eeeeee" height="20">
                                            <td   align="center" width="25" class="headingSpas" nowrap ><b>S.No</b></td>
                                            <td   align="left" width="250"  class="headingSpas" nowrap><b>Location Description</b></td>
                                            <td   align="center" width="40" class="headingSpas" nowrap><b>Edit</b></td>
                                            <td   align="center" width="35" class="headingSpas" nowrap><b>isActive</b></td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="JobLocationList" name="JobLocationList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${JobLocationList.name}" width="250" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                            ${JobLocationList.name}
                                                        </span>
                                                    </td>

                                                    <td align="center" bgcolor="#FFFFFF" width="40" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${JobLocationList.name}', ${sno},'${JobLocationList.id}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="35" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${JobLocationList.status}', ${sno},'${JobLocationList.id}','${JobLocationList.name}')"  class="headingSpas" style="color: blue;" >
                                                                ${JobLocationList.status}&nbsp;
                                                            </a>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr >
                                            <td colspan="4" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
                    <c:if test="${param ne null}">
                        <c:if test="${empty JobLocationList}">
                            <tr bgcolor="#FFFFFF">
                                <td valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                    <%if (request.getParameter("nameSrch") != null) {%>
                                    Location Description '<%=request.getParameter("nameSrch") == null ? "" : request.getParameter("nameSrch")%>' not found
                                    <%}%>
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                    <tr bgcolor="#FFFFFF">
                        <td width=100% valign="top">
                            <form action="masterAction.do?option=manageJobLocation&type=add" method="post" id="addJobLocation">
                                <table width=100% bgcolor=#000000 border="0" cellpadding="0" cellspacing="0" >
                                    <tr   bgcolor="#eeeeee" height="20">
                                        <td align= left class="headingSpas" style="padding-left: 5px;">
                                            <b>ADD NEW JOB LOCATION</b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                                                <tr height="30" bgcolor="#FFFFFF">

                                                    <td  style="padding-left:10px" width="20%" class="headingSpas" nowrap align="left">Location Description</td>
                                                    <td style="padding-left:10px;padding-top:5px" width="30%">
                                                        <input type="text" name="locationDesc"  id="Location Desc" class="headingSpas" onkeypress="return CommentsMaxLength(this, 200);" maxlength="200" style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                    <td style="padding-left:10px;padding-bottom: 5px" align="left" >
                                                        <input type=button name="add" class="headingSpas" value="Submit" onclick="validate()"/>
                                                    </td>

                                                </tr>

                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </td></tr></table> </div>
        </div>
    </center>
</div>


