<%-- 
    Document   : v_manageCustCategory
    Created on : Feb 4, 2016, 3:24:42 PM
    Author     : ashutosh.kumar1
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
    function cUDAction(str, row,strVal,Desc)
    {

        if(str == 'add'){
            var elementArr=new Array('Desc');
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
            var name = TrimAll(document.getElementById("Desc").value);
            var url="<%=cntxpath%>/masterAction.do?option=manageCustCategory&type="+str+"&name="+name+"&t="+new Date().getTime()+"&Desc="+Desc;
        }
        else{
            var url="<%=cntxpath%>/masterAction.do?option=manageCustCategory&status="+str+"&name="+name+"&type="+str+"&id="+strVal+"&t="+new Date().getTime();
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
                            document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','"+row+"','"+strVal+"','"+Desc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                        }
                        else if(str=='N'){
                            document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','"+row+"','"+strVal+"','"+Desc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                    }
                    if(str=='Y' && res.split("@@")[0] =='LocationExist' ){
                        document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','"+row+"','"+strVal+"','"+Desc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                    }
                    if(str == 'add' && res.split("@@")[0] =='Success'){
                        //submitForm();
                        document.getElementById("msg_saveFAILED").innerHTML=res.split("@@")[1];
                        document.getElementById("Desc").value='';
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
            document.getElementById("msg_saveFAILED").innerHTML="Please enter value in Description field";
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
            document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in Description field";
            window.scrollTo(0,0);//go to top of page
            return false;
        }

        var name = TrimAll(document.getElementById("newName"+row).value).replace('&','@@');
        var url="<%=cntxpath%>/masterAction.do?option=manageCustCategory&id="+id+"&name="+name+"&type="+str+"&t="+new Date().getTime();

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
        var strObject=document.getElementById("Desc");
        var strValue =document.getElementById("Desc").value;
        if(checkValue(strObject,strValue)==false)
        {
            return false;
        }
        document.getElementById('addCustCategory').submit();
    }
</script>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a>  CUSTOMER CATEGORY</li>


        </ul>
    </div>

    <div class="message" id="msg_saveFAILED">${successmsg}</div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1>CUSTOMER CATEGORY</h1>
                <table cellspacing=1 cellpadding=5 border=0 width=100%>

                    <%-- <tr height=25 class=grey>
                         <td align= center class="heading">
                             MANAGE CUSTOMER TYPE
                         </td>
                     </tr>--%>
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initCustCategory&param=search" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">
                                        <td  style="padding-left:10px" width="20%" class="headingSpas" nowrap align="left">Description</td>
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
                        <c:if test="${!empty CustCategoryList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="15">
                                    <pg:param name="option" value="initJobLocation"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr  bgcolor="#eeeeee" height="20">
                                            <td   align="center" width="10%" class="headingSpas"  nowrap ><b>S.No</b></td>
                                            <td   align="left" width="60%"  class="headingSpas" style="padding-left: 5px" nowrap><b>Description</b></td>
                                            <td   align="center" width="15%" class="headingSpas" nowrap><b>Edit</b></td>
                                            <td   align="center" width="15%" class="headingSpas" nowrap><b>isActive</b></td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="CustCategoryList" name="CustCategoryList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${CustCategoryList.name}" width="250" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                            ${CustCategoryList.name}
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="40" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${CustCategoryList.name}', ${sno},'${CustCategoryList.id}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="35" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${CustCategoryList.status}', ${sno},'${CustCategoryList.id}','${CustCategoryList.name}')"  class="headingSpas" style="color: blue;" >
                                                                ${CustCategoryList.status}&nbsp;
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
                        <c:if test="${empty CustCategoryList}">
                            <tr bgcolor="#FFFFFF">
                                <td valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                    <%if (request.getParameter("nameSrch") != null) {%>
                                    Description '<%=request.getParameter("nameSrch") == null ? "" : request.getParameter("nameSrch")%>' not found
                                    <%}%>
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                    <tr bgcolor="#FFFFFF">
                        <td width=100% valign="top">
                            <form action="masterAction.do?option=manageCustCategory&type=add" method="post" id="addCustCategory">
                                <table width=100% bgcolor=#000000 border="0" cellpadding="0" cellspacing="0" >
                                    <tr   bgcolor="#eeeeee" height="20">
                                        <td align= left class="headingSpas" style="padding-left: 5px;">
                                            <b>ADD NEW CUSTOMER CATEGORY</b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                                                <tr height="30" bgcolor="#FFFFFF">
                                                    <td  style="padding-left:10px" width="20%" class="headingSpas" nowrap align="left">Description</td>
                                                    <td style="padding-left:10px;padding-top:5px" width="30%">
                                                        <input type="text" name="description"  id="Desc" class="headingSpas" onkeypress="return CommentsMaxLength(this, 200);" maxlength="200" style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                    <td style="padding-left:10px;padding-bottom: 5px; padding-top: 10px" align="left" >
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


