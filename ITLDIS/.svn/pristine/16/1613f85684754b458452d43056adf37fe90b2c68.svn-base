<%-- 
    Document   : v_causalCodeMaster
    Created on : Apr 18, 2014, 10:26:24 AM
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
        var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
        var objSpecExpDes = /['*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var ck_code = /^[a-zA-Z0-9]+$/;
        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);
        var tempD= objSpecExpDes.exec(strValue);
       
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
        else if (strObject.id == 'Causal Code') {
            text = 'Causal Code';
            if (!ck_code.test(strValue)) {
                       strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
           document.getElementById("msg_saveFAILED").innerHTML = "Special Characters or Space are not allowed in " + text + " Field";
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
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + strObject.id + " Field";
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
            var elementArr = new Array('Causal Code','Causal Description','Defect Code');
            var strValue = null;
            var strObject = null;

            for (var i = 0; i < elementArr.length; i++)
            {
                strObject = document.getElementById(elementArr[i]);
                strValue = document.getElementById(elementArr[i]).value;
                if (checkValue(strObject, strValue) == false)
                    return false;
            }

            var causalCode = TrimAll(document.getElementById("Causal Code").value);
            var causalDesc = TrimAll(document.getElementById("Causal Description").value);
             var tempDes=replaceall(causalDesc,'&','`');
            var compCode = TrimAll(document.getElementById("Defect Code").value);
            url = "<%=cntxpath%>/masterAction.do?option=manageCausalCode&type=" + str + "&causalCode=" + causalCode + "&causalDesc=" + tempDes + "&compCode=" + compCode +"&t=" + new Date().getTime();
        }
        else {
            url = "<%=cntxpath%>/masterAction.do?option=manageCausalCode&status=" + str + "&type=" + str + "&causalCode=" + strVal + "&t=" + new Date().getTime();
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
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','" + row + "','" + strVal + "')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                        } else {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','" + row + "','" + strVal + "')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                    }
                    if (str == 'add' && res.split("@@")[0] == 'Success') {
                        document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                        document.getElementById("Causal Code").value='';
                        document.getElementById("Causal Description").value='';
                        document.getElementById("Defect Code").value='';
                        // setTimeout(function() { submitForm(); }, 3000);
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }

    function editRow(causalCode, row,causalDesc,compCodeid,causal_Id){
        var url = "<%=cntxpath%>/masterAction.do?option=manageCausalCode&type=check&causalCode="+causalCode+"&t="+new Date().getTime();
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
                       // document.getElementById('causalCodee').innerHTML="<input type=\"hidden\" name=\"causalCode\" maxlength='5' class=\"headingSpas\" id=\"newCausalCode"+row+"\" style=\"width:80px\" value=\""+causalCode+"\"/>";
                        document.getElementById('causalDesc'+row).innerHTML="<input type=\"text\" name=\"causalDesc\" maxlength='150' class=\"headingSpas\" id=\"newCausalDesc"+row+"\" style=\"width:200px\" value=\""+causalDesc+"\"/>";
                        flag = true;
                    }else{
                        document.getElementById('causalDesc'+row).innerHTML="<input type=\"text\" name=\"causalDesc\" maxlength='150' class=\"headingSpas\" id=\"newCausalDesc"+row+"\" style=\"width:200px\" value=\""+causalDesc+"\"/>";
                    }
                }
            }

        };
        xmlHttp.open("GET", url, false);
        xmlHttp.send(null);
        document.getElementById('icompCode' + row).innerHTML = "<select name='name' class=\"headingSpas\" id='newName" + row + "' style='width:220px'><option value='' >--Select Here--</option><c:forEach items='${causalList}'  var='labelValue'  varStatus='status'><option value='${labelValue.value}@@${labelValue.label}' title='${labelValue.label}'>${labelValue.value} [ ${labelValue.label} ]</option></c:forEach></select>";
        document.getElementById('newName'+row).value = compCodeid;
        document.getElementById('imageButton'+row).innerHTML="<input type=button class=\"headingSpas\" name=\"apply\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+causalCode+"','"+flag+"','"+causalDesc+"','"+causal_Id+"')\"/>";

    }
    function updateAction(str, row, oldCausalCode,flag,causalDesc,causal_Id)
    {
        //alert(causal_Id+" flag   :: "+flag);
        var causalDesc="";
        var newCausalCode='';
         var objRegExp = /^(\s*)$/g;
       // var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
        var objSpecExp = /['*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var ck_code = /^[a-zA-Z0-9]+$/;
       <%-- if(flag == 'true'){
            if(document.getElementById("newCausalCode"+row).value==""){
                document.getElementById("newCausalCode"+row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML="Please enter value in Cause Code field";
                return false;
            }
            var chckValue = document.getElementById("newCausalCode"+row).value;
//            chckValue=chckValue.replace(objRegExp,'');
//            var temp=objSpecExp.exec(chckValue);
//            if(temp)
//            {
//                document.getElementById("newCausalCode"+row).focus();
//                document.getElementById("msg_saveFAILED").style.display = "";
//                document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in Cause Code field";
//                window.scrollTo(0,0);//go to top of page
//                return false;
//            }
               text = 'Cause Code';
               if (!ck_code.test(chckValue)) {
               
                document.getElementById("newCausalCode"+row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + text + " field";
                window.scrollTo(0, 0);//go to top of page
               return false;
                        
              }
            newCausalCode = TrimAll(document.getElementById("newCausalCode" + row).value);
        }--%>
        if (document.getElementById("newCausalDesc" + row).value == "") {
            document.getElementById("newCausalDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Causal Description";
            return false;
        }

        var chckValue = document.getElementById("newCausalDesc" + row).value;
        chckValue = chckValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(chckValue);
        if (temp)
        {
            document.getElementById("newCausalDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Special Characters or Space are not allowed in Causal Description field";
            window.scrollTo(0, 0);//go to top of page
            return false;
        }
        causalDesc = TrimAll(document.getElementById("newCausalDesc" + row).value);
        var tempDes=replaceall(causalDesc,'&','`');

        if(document.getElementById("newName"+row).value==""){
            document.getElementById("newName"+row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Please Select Defect Code";
            return false;
        }
        var compCode = TrimAll(document.getElementById("newName"+row).value);

        var url = "<%=cntxpath%>/masterAction.do?option=manageCausalCode&id=" + oldCausalCode + "&causalCode=" + newCausalCode + "&causalDesc=" + tempDes + "&compCode=" + compCode + "&primary_id=" + causal_Id + "&type=" + str + "&t=" + new Date().getTime();

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
                         //   document.getElementById('causalCode'+row).innerHTML=""+newCausalCode+"";
                            document.getElementById('causalCode'+row).innerHTML=""+oldCausalCode+"";
                        }else{
                            document.getElementById('causalCode'+row).innerHTML=""+oldCausalCode+"";
                        }
                        document.getElementById('causalDesc' + row).innerHTML = "" + causalDesc + "";
                        document.getElementById('icompCode' + row).innerHTML = "" + compCode.split("@@")[0] + " [" +compCode.split("@@")[1]+"]";
                        document.getElementById('imageButton' + row).innerHTML = "<img src=\"images/edit.gif\" onclick=\"editRow('" + oldCausalCode + "'," + row + ",'" + causalDesc + "','"+compCode+"','"+causal_Id+"')\" title=\"Edit\"/>";
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
            <li class="breadcrumb_link">MANAGE CAUSE CODE</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1>MANAGE CAUSE CODE </h1>


                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initCausalCode" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="left">Cause Description</td>
                                        <td style="padding-left:10px" width="140px">
                                            <html:text property="nameSrch" styleClass="headingSpas"  style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <td style="padding-left:10px" align="left" >
                                            <input type=button name="Go" class="headingSpas" value="Search" onClick = "submitForm()"/>
                                        </td>
                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${empty causalCodeList}">
                        <tr bgcolor="#FFFFFF">
                            <td valign="top" align="center" class="headingSpas" style="padding-top:10px;color: red">
                                Cause Description not Found. !
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty causalCodeList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initCausalCode"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td  nowrap align="center" width="35" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>
                                            <td   align="left" width="80" class="headingSpas" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Cause Code</td>
                                            <td   align="left" width="200" class="headingSpas" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Cause Description</td>
                                            <td   align="left" width="200" class="headingSpas" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Defect Code</td>
                                            <td   align="center" width="75" class="headingSpas" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Edit</td>
                                            <td   align="center" width="35" class="headingSpas" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>is Active</td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="causalCodeList" name="causalCodeList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20" >
                                                    <td align="center" bgcolor="#FFFFFF" width="35" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" title=" ${causalCodeList.causalCode}" width="80" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="causalCode${sno}" >
                                                            ${causalCodeList.causalCode}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${causalCodeList.causalDesc}" width="220" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="causalDesc${sno}" >
                                                            ${causalCodeList.causalDesc}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" width="120" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <input type="hidden" name="compCode" title="${causalCodeList.compCode}[${causalCodeList.compDesc}]" id="icompCode" value="${causalCodeList.compCode}@@${causalCodeList.compDesc}"/>
                                                        <span id ="icompCode${sno}" >
                                                            ${causalCodeList.compCode}[${causalCodeList.compDesc}]
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="40" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img alt="" src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${causalCodeList.causalCode}', ${sno}, '${causalCodeList.causalDesc}', '${causalCodeList.compCode}@@${causalCodeList.compDesc}','${causalCodeList.primary_id}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="35" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${causalCodeList.status}', ${sno}, '${causalCodeList.causalCode}')"  class="headingSpas" style="color: blue;" >
                                                                ${causalCodeList.status}&nbsp;
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
                                            <b>ADD NEW CAUSE CODE</b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >

                                                <tr height="30">
                                                    <td  style="padding-left:10px" width="25%" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Cause Code</td>
                                                    <td style="padding-left:10px;padding-top:5px" width="75%" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="causalCode"  id="Causal Code" class="headingSpas"  maxlength="5" style="width:120px" onblur="this.value=TrimAll(this.value)"/>
                                                        <div id="causalCodee"></div>
                                                    </td>
                                                </tr>
                                                <tr height="30">

                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Cause Description</td>
                                                    <td style="padding-left:10px;padding-top: 5px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="causalDesc"  id="Causal Description" class="headingSpas"  maxlength="150" style="width:240px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="30">
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Select Defect Code</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <select name='name' id='Defect Code' class="headingSpas" style='width:245px !important'>
                                                            <option value='' >--Select Here--</option>
                                                            <c:forEach items='${causalList}'  var='labelValue'  varStatus='status'>
                                                                <option value='${labelValue.value}' title='${labelValue.value} [${labelValue.label}]'>${labelValue.value} [${labelValue.label}]</option>
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
