<%--
    Document   : v_contentMaster
    Created on : May 01, 2014, 02:25:09 PM
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
            document.getElementById("msg_saveFAILED").innerHTML="Please enter value in "+strObject.id+" field";
            window.scrollTo(0,0);//go to top of page
            return false;

        }
        else if(temp)
       {
            if(strObject.tagName=="INPUT")
            {
////                //alert("Please Enter "+elementArr[i]);
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in "+strObject.id+" Description field";
                window.scrollTo(0,0);//go to top of page
                return false;
            }
////
             return false;
//
        }
    }
    
    function cUDAction(str, row,strVal,contentDesc)
    {
          var name="";
        if(str == 'add'){
           
            var contents = document.getElementsByName("contentList");
             //alert(contents.length);
          for (var i = 0; i < contents.length; i++) {
                 name+=TrimAll(contents[i].value)+":";
         }
         //alert(name);
            var elementArr=new Array('Content Description');
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


             var oRows = document.getElementById("myTable").getElementsByTagName("TR");
                   var rowLength = oRows.length - 1;
                  // alert(rowLength);

               var objSpecExp = /['\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
               for(var i=2;i<=rowLength;i++){
                if(document.getElementById("Content Description"+i).value==""){
                    document.getElementById("Content Description"+i).focus();
                    document.getElementById("msg_saveFAILED").style.display = "";
                    document.getElementById("msg_saveFAILED").innerHTML="Please enter value in Content Description field";
                    return false;
                }
                <%--if(!trim(document.getElementById("Content Description"+i).value).match(/^[a-z A-Z._-]+$/)){--%>
                if(objSpecExp.test(document.getElementById("Content Description"+i).value)){
                    document.getElementById("Content Description"+i).value="";
                    document.getElementById("Content Description"+i).focus();
                    document.getElementById("msg_saveFAILED").style.display = "";
                    document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in Content Description field";
                    return false;
                }
                }

              document.getElementById("addContentDesc").submit();
            //var name = TrimAll(document.getElementById("Content Description").value);
            <%--var url="<%=cntxpath%>/masterAction.do?option=manageContentMaster&type="+str+"&name="+name+"&t="+new Date().getTime();--%>
        }
        else{
            var url="<%=cntxpath%>/masterAction.do?option=manageContentMaster&status="+str+"&name="+contentDesc+"&type="+str+"&id="+strVal+"&t="+new Date().getTime();
        
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
                            document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','"+row+"','"+strVal+"','"+contentDesc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                        }
                        else if(str=='N'){
                            document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','"+row+"','"+strVal+"','"+contentDesc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                    }
                    else if(str=='Y' && res.split("@@")[0] =='contentExist' ){
                        document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','"+row+"','"+strVal+"','"+contentDesc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                    }
                    else if(str == 'add' && res.split("@@")[0] =='Success'){
                        //submitForm();
                        document.getElementById("msg_saveFAILED").innerHTML=res.split("@@")[1];
                        //document.getElementById("Content Description").value='';
                          var contents = document.getElementsByName("name");
             //alert(contents.length);
                        for (var i = 0; i < contents.length; i++) {
                         
                          contents[i].value='';
                
                        }
                    }
                } }
        };
        xmlHttp.open("GET",url, true);
        xmlHttp.send(null);
        return false;
      }
    }
    function editRow(name, row,id){
      
        document.getElementById('compType'+row).innerHTML="<input type=\"text\" name=\"name\" size='200' maxlength='200' class=\"headingSpas\" onkeypress=\"return CommentsMaxLength(this, 200);\" id=\"newName"+row+"\" style=\"width:220px\" value=\""+name+"\"/>";
        document.getElementById('imageButton'+row).innerHTML="<input type=button name=\"apply\" class=\"headingSpas\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+id+"')\"/>";
    }

    function updateAction(str, row,id)
    {
        if(document.getElementById("newName"+row).value==""){
            document.getElementById("newName"+row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Please enter value in Content Description field";
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
            document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in Content Description field";
            window.scrollTo(0,0);//go to top of page
            return false;
        }

        var name = TrimAll(document.getElementById("newName"+row).value).replace('&','@@');
       
        var url="<%=cntxpath%>/masterAction.do?option=manageContentMaster&id="+id+"&name="+name+"&type="+str+"&t="+new Date().getTime();

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
    
    function uploadExcel()
    {
        str=document.getElementById('uploadfile').value;
        alert(str);
    var url="<%=cntxpath%>/masterAction.do?option=uploadContentMaster&file="+str;
     
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
                    alert(res);
//                    document.getElementById("msg_saveFAILED").innerHTML=res.split("@@")[1];
//                    if(str != 'add' && res.split("@@")[0] =='Success'){
//                        if(str=='Y'){
//                            document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','"+row+"','"+strVal+"','"+contentDesc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
//                        }
//                        else if(str=='N'){
//                            document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','"+row+"','"+strVal+"','"+contentDesc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
//                        }
//                    }
//                    if(str=='Y' && res.split("@@")[0] =='contentExist' ){
//                        document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','"+row+"','"+strVal+"','"+contentDesc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
//                    }
//                    if(str == 'add' && res.split("@@")[0] =='Success'){
//                        //submitForm();
//                        document.getElementById("msg_saveFAILED").innerHTML=res.split("@@")[1];
//                        document.getElementById("Content Description").value='';
//                    }
                } }
        };
    xmlHttp.open("GET",url, true);
        xmlHttp.send(null);
        return false;
    }
    var count = "2";
    function addRowContent(in_tbl_name)
    {
         var count = (document.getElementById(in_tbl_name).rows.length);
        // alert(count)
        var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];
        // create row
        var row = document.createElement("TR");
       
        row.style.backgroundColor="white";
        var td1 = document.createElement("TD")
        var strHtml1 = "";
        td1.innerHTML = strHtml1.replace(/!count!/g,count);

        var td2 = document.createElement("TD")
        td2.style.paddingTop="5px";
        var strHtml2="&nbsp;&nbsp;&nbsp;<input type=\"text\" name=\"contentList\"  id=\"Content Description"+count+"\" size='200' class=\"headingSpas\" onkeypress=\"return CommentsMaxLength(this, 250);\" maxlength=\"200\" style=\"width:170px\" onblur=\"this.value=TrimAll(this.value)\"/>"
       // var strHtml2 = "<input name=\"partDesc\" type=\"text\" id=\"partDesc"+count+"\" readonly style=\"width:140px !important\" onblur=\"getPartPrice(document.getElementById('partNo"+count+"'),"+count+");\"/><input type=\"hidden\" name=\"comptype\" id=\"comptype"+count+"\" value=\"PRT\">";
        td2.innerHTML = strHtml2.replace(/!count!/g,count);
         var td3 = document.createElement("TD")
        var strHtml3 = "";
        td3.innerHTML = strHtml3.replace(/!count!/g,count);

        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
       
        count = parseInt(count) + 1;
        tbody.appendChild(row);

    }
     function deleteContentRow(in_tbl_name)
    {
        var oRows = document.getElementById(in_tbl_name).getElementsByTagName('tr') ;
        var rowLength=oRows.length-1;
       
        if(rowLength>1){
           
            document.getElementById(in_tbl_name).deleteRow(rowLength-1);
        }
    }
</script>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a>MANAGE CHECKLIST CONTENT</li>


        </ul>
    </div>

    <div class="message" id="msg_saveFAILED">${successmsg}</div>
    <center>

        <div class="content_right1">

            <div class="con_slidediv1" style="position: relative;width: 100%">

                <h1>MANAGE CHECKLIST CONTENT</h1>

                <table cellspacing=1 cellpadding=5 border=0 width=100%>

                    <%-- <tr height=25 class=grey>
                         <td align= center class="heading">
                             MANAGE CUSTOMER TYPE
                         </td>
                     </tr>--%>
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initContentMaster&param=search" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="20%" class="headingSpas" nowrap align="left">Content Description</td>
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
                        <c:if test="${!empty contentList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initContentMaster"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr  bgcolor="#eeeeee" height="20">
                                            <td   align="center" width="25" class="headingSpas" nowrap ><b>S.No</b></td>
                                            <td   align="left" width="150"  class="headingSpas" nowrap><b>Content Description</b></td>
                                            <td   align="center" width="20" class="headingSpas" nowrap><b>Edit</b></td>
                                            <td   align="center" width="35" class="headingSpas" nowrap ><b>isActive</b></td>  <%--style="display:none"--%>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="contentList" name="contentList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${contentList.name}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                            ${contentList.name}
                                                        </span>
                                                    </td>

                                                    <td align="center" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${contentList.name}', ${sno},'${contentList.id}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                   <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${contentList.status}', ${sno},'${contentList.id}','${contentList.name}')"  class="headingSpas" style="color: blue;" >
                                                                ${contentList.status}&nbsp;
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
                        <c:if test="${empty contentList}">
                            <tr bgcolor="#FFFFFF">
                                <td valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                    <%if (request.getParameter("nameSrch") != null) {%>
                                    Content Description '<%=request.getParameter("nameSrch") == null ? "" : request.getParameter("nameSrch")%>' not found
                                    <%}%>
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                    <tr bgcolor="#FFFFFF">
                        <td width=100% valign="top">
                            <form action="masterAction.do?option=manageContentMaster&type=add" method="post"  name="addContentDesc"  id="addContentDesc"><%--enctype = "multipart / form-data"--%>
                                <table width=100% bgcolor=#000000 border="0" cellpadding="0" cellspacing="0" >
                                    
                                    
                                    <tr   bgcolor="#eeeeee" height="20">
                                        <td align= left class="headingSpas" style="padding-left: 5px;">
                                            <b>ADD NEW CONTENT</b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table id="myTable" width="100%" border="0" cellpadding="0" cellspacing="0" >
                                                
                                                <tbody>
                                                
                                                <tr height="30" bgcolor="#FFFFFF">

                                                    <td  style="padding-left:10px" width="20%" class="headingSpas" nowrap align="left">Content Description</td>
                                                    <td style="padding-left:10px;padding-top:5px" width="30%">
                                                        <input type="text" name="contentList"  id="Content Description" size='200' class="headingSpas" onkeypress="return CommentsMaxLength(this, 250);" maxlength="200" style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                    <td style="padding-left:10px;padding-bottom: 5px" align="left" >
                                                       
                                                    </td>

                                                </tr>
                                                                                               
                                                </tbody>
                                                 <tr height="30" bgcolor="#FFFFFF">

                                                    <td  style="padding-left:10px" width="20%" class="headingSpas" nowrap align="">
                                                        <%--<input type=button name="add" class="headingSpas" value="Add" onclick="addRowContent('myTable')"/>--%>
                                                    </td>
                                                    <td style="padding-left:10px" width="30%" align="center">
                                                    <input type=button name="add" class="headingSpas" value="Add" onclick="addRowContent('myTable')"/> &nbsp;&nbsp;  <input type=button name="add" class="headingSpas" value="Delete" onclick="deleteContentRow('myTable')"/>
                                                    </td>
                                                    <td style="padding-left:10px;padding-bottom: 5px" align="left" >
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


